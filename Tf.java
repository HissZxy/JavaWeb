package com.myapp.ml.nlp;
 
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
/**
 * Created by lionel on 16/12/15.
 */
public class TFIDFAlgorithm {
    /**
     * �����ļ�·�����ļ��д�ŵ�100����ַ�� url����ȡ url ·���б�
     *
     * @param path �����ļ�·��
     * @return ·���б�
     */
    public List<String> readUrlFromText(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        List<String> urls = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                urls.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
 
    /**
     * ���� Jsoup ���ߣ�������ַ��ȡ��ҳ�ı�
     *
     * @param url ��ַ
     * @return ��ҳ�ı�
     */
    public String getTextFromUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
 
        String text = "";
        try {
            Document document = Jsoup.connect(url).get();
            text = document.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.replace(" ", "");
    }
 
    /**
     * ���� ansj ���ı��ִ�
     *
     * @param text �ı�����
     * @return �ִʽ��
     */
    public List<Term> parse(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        List<Term> terms = FilterModifWord.modifResult(ToAnalysis.parse(text));
        if (terms == null || terms.size() == 0) {
            return null;
        }
        return terms;
    }
 
    /**
     * ����һƪ���·ִʺ��ȥ�����ź�ʵ�����
     *
     * @param terms �ִʺ�ļ���
     * @return һƪ���·ִʺ��ȥ�����ź�ʵ�����
     */
    private Integer countWord(List<Term> terms) {
        if (terms == null || terms.size() == 0) {
            return null;
        }
        for (int i = 0; i < terms.size(); i++) {
            if ("null".equals(terms.get(i).getNatureStr()) || terms.get(i).getNatureStr().startsWith("w")) {
                terms.remove(i);
            }
        }
        return terms.size();
    }
 
    /**
     * �����Ƶ IF
     *
     * @param word  ��
     * @param terms �ִʽ������
     * @return IF
     */
    public double computeTF(String word, List<Term> terms) {
        if (StringUtils.isBlank(word)) {
            return 0.0;
        }
        int count = 0;
        for (Term term : terms) {
            if (term.getName().equals(word)) {
                count += 1;
            }
        }
        return (double) count / countWord(terms);
    }
 
    /**
     * ͳ�ƴ�������ĵ�Ƶ�� IDF
     *
     * @param path ��� url ���ļ�·��
     * @param word IDF
     */
    public double computeIDF(String path, String word) {
        if (StringUtils.isBlank(path) || StringUtils.isBlank(word)) {
            return 0.0;
        }
 
        List<String> urls = readUrlFromText(path);
        int count = 1;
        for (String url : urls) {
            String text = getTextFromUrl(url);
            if (text.contains(word)) {
                count += 1;
            }
        }
        return Math.log10((double) urls.size() / count);
    }
 
    /**
     * �����Ƶ-���ĵ�Ƶ�� TF��IDF
     *
     * @param filePath ���url���ļ�·��
     * @param terms    �ִʽ������
     * @param word     ��
     * @return TF��IDF
     */
 
    public Double computeTFIDF(String filePath, List<Term> terms, String word) {
        return computeTF(word, terms) * computeIDF(filePath, word);
    }
}