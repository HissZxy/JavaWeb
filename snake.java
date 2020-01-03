//定义贪食蛇节点
public class Snake {
    Snake next;
    int x;      //节点X坐标
    int y;      //节点Y坐标
    public Snake(int x,int y) {
        this.x = x;
        this.y = y;
    }
}

//贪食蛇链表
class SnakeList{

    public Snake first;     //头节点

    public SnakeList() {
        // TODO Auto-generated constructor stub
        this.first = null;
    }

    //添加头节点
    public void addFirstSnake(int x,int y){
        Snake s = new Snake(x, y);
        s.next = first;
        first = s;
    }

    public void add(int x,int y) {
        Snake node = new Snake(x, y);
        node.next = first;
        first = node;
    }

    //通过坐标寻找是否存在该节点
    public int findSnake(int x,int y){
        Snake current = first;
        while(current.x!=x||current.y!=y){
            if(current.next==null){
                return 0;
            }
            current = current.next;
        }
        return 1;
    }

}