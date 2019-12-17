import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private static final int PORT = 9999;
    // 获取处理器核数
    private static final int COUNT = Runtime.getRuntime()
            .availableProcessors();
    // 处理的任务量和线程数量、CPU、内存等资源都相关，
    // 一般推荐处理器核数数量的线程
    private static final ExecutorService EXE
            = Executors.newFixedThreadPool(COUNT);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while(true) {
            // 获取客户端请求socket对象：阻塞方法
            Socket socket = server.accept();
            EXE.submit(new HttpTask(socket));
        }
    }
}
// Http请求任务处理类
class HttpTask implements Runnable{

    private Socket socket;

    public HttpTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //获取客户端请求数据：输入流
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //获取客户端输出流，返回响应数据
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader
                        (is, "UTF-8");
                br = new BufferedReader(isr);
                Request request = new Request();
                // 请求数据的解析：http协议报的解包
                // 1.解析请求行（第一行）：method url version
                String requestLine = br.readLine();
                String[] requestLines = requestLine.split(" ");
                request.setMethod(requestLines[0]);
                //可能为http://localhost:9999/xxx?username=stu&password=123
                String url = requestLines[1];
                if(url.contains("?")){
                    String parameters = url.substring(url.indexOf("?")+1);
                    request.parseParameters(parameters);
                    url = url.substring(0, url.indexOf("?"));
                }
                request.setUrl(url);
                request.setVersion(requestLines[2]);
                // 2.解析请求头：
                // key:value每个换行，以空行作为结尾
                String header;
                while((header=br.readLine())!=null
                        && header.length()!=0){//split或是substring获取
                    String key = header.substring(0, header.indexOf(":"));
                    String value = header.substring(header.indexOf(":")+1);
                    request.addHeader(key, value.trim());
                }
                // POST请求，需要根据请求头Content-Length获取请求体的长度
                if("POST".equals(request.getMethod())){
                    String len = request.getHeader("Content-Length");
                    if(len!=null){
                        int l = Integer.parseInt(len);
                        char[] chars = new char[l];
                        br.read(chars, 0, l);
                        //请求参数格式：username=stu&password=123
                        String requestBody = new String(chars);
                        request.parseParameters(requestBody);
                    }
                }
                System.out.println(request);

                os = socket.getOutputStream();
                pw = new PrintWriter(os, true);

                if("/302".equals(request.getUrl())){
                    pw.println("HTTP/1.1 302 重定向");
                    pw.println("Content-Type: text/html;charset=utf-8");
                    pw.println("Location: https://www.baidu.com");
                }
            } finally {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if(pw != null)
                    pw.close();
                if(os != null)
                    os.close();
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
