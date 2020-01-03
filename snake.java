//����̰ʳ�߽ڵ�
public class Snake {
    Snake next;
    int x;      //�ڵ�X����
    int y;      //�ڵ�Y����
    public Snake(int x,int y) {
        this.x = x;
        this.y = y;
    }
}

//̰ʳ������
class SnakeList{

    public Snake first;     //ͷ�ڵ�

    public SnakeList() {
        // TODO Auto-generated constructor stub
        this.first = null;
    }

    //���ͷ�ڵ�
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

    //ͨ������Ѱ���Ƿ���ڸýڵ�
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