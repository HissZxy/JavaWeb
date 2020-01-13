package AllocationAlg;
 
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
 
public class Partition {
	LinkedList<Free> freeLink;//����������
	LinkedList<Busy> busyLink;//�ѷ���������
	Free free;
	Busy busy;
	
	public Partition() {
		super();
		start();
	}
	void start() {
		freeLink=new LinkedList<Free>();
		busyLink=new LinkedList<Busy>();
		
		Busy os=new Busy("OS",0,64);//ϵͳռ����64K
		busyLink.add(os);//�����ѷ������
		free=new Free(64,300);//��ʼ���ڴ��������һ�飬��С300K���׵�ַ64K
		freeLink.add(free);//�������������
	}
	void requierMemo(String name,int require) {//ģ���ڴ����
		if(require<=freeLink.get(0).len) {//�ɻ����ڴ�����������Ĵ�С
			int address=freeLink.get(0).address;//��ø������������׵�ַ�����⿪ʼ����require�����ڴ�
			freeLink.get(0).address=address+require;//���¸ÿ��������׵�ַ
			freeLink.get(0).len=freeLink.get(0).len-require;//���¸ÿ������ĳ���
			busy=new Busy(name, address, require);//���ɷ�������
			busyLink.add(busy);//���÷��������������,�����ѷ������
			Collections.sort(freeLink, new compatorFree());//��Ϊԭ�����Ŀ�������������һ�����ܲ�������ˣ�������������
			System.out.println("Ϊ"+busy.name+"�����ڴ�ɹ���");
		}
		else
			System.out.println("��ǰ�޷��ҵ��㹻�ڴ���䣬����ʧ�ܣ���ȴ���");
	}
	void freeMemo(String name) {//ģ���ڴ����
		Busy recycle=new Busy();//��ʱ���Ҫ���յ��ڴ����Ϣ
		/*����û������ֱ�����������������ڽӣ�������������������㷨�ػ��Ȼ�����ִ��һ��������������»��������ظ����㣬Ҫ������Ӱ��
		�͵��ȼ�¼���������Ѿ���ɲ����Ŀ�������λ�ã��ٰ�������ϲ����ɣ���Ϊ�������ص�����*/
		int freeUp=-1;//��¼�������ڽӿ��λ�ã�-1�����ڽ�
		int freeDown=-1;//��¼�������ڽӿ��λ�ã�-1�����ڽ�
		for(int i=0;i<busyLink.size();++i) {
			busy=busyLink.get(i);
			if(busy.name==name) {
				recycle=busy;//���ҵ�Ҫ���յ��ڴ�,������Ϣ
				busyLink.remove(recycle);//������˵����������������ķ�����ɾ�Ĳ飬�����ķ�����ʧЧ
			}
		}
//		for(ListIterator<Free> iter=freeLink.listIterator();iter.hasNext();){//������Ч��O(1)Զ�����������O(N^2)
		for(int i=0;i<freeLink.size();++i) {
			free=freeLink.get(i);//���������ָ��ָ��Ķ���
			/*��һ�����������������һ�������������ڽ�*/
			if(recycle.address==(free.address+free.len)) {
				freeUp=freeLink.indexOf(free);//��¼�¸ÿ��������λ��
				free.len=free.len+recycle.len;//���¸ÿ������䳤��
				freeLink.set(freeUp, free);
			}
			/*�ڶ����������������һ�������������ڽ�*/
			if((recycle.address+recycle.len)==free.address) {
				freeDown=freeLink.indexOf(free);//��¼�¸ÿ��������λ��
				free.address=recycle.address;//���¸ÿ��������׵�ַ
				free.len=recycle.len+free.len;//���¸ÿ������䳤��
				freeLink.set(freeDown, free);
			}
			/*��������������ֱ�������������������ڽ�*/
			if(freeUp!=-1&&freeDown!=-1) {
				Free freeUpObj=freeLink.get(freeUp);
				Free freeDownObj=freeLink.get(freeDown);
				/*���ö��һ�٣�ǰ���Ѿ�������freeUp���׵�ַ��ȻС��freeDown���׵�ַ
				if(freeUpObj.address<freeDownObj.address) {//ȷ���͵�ַ��ǰ���ߵ�ַ�ں�
					Free temp;
					temp=freeUpObj;freeDownObj=freeUpObj;freeUpObj=temp;
				}*/
				freeUpObj.len=freeUpObj.len+freeDownObj.len-recycle.len;//��������ϲ���ĳ���
				freeLink.set(freeUp, freeUpObj);//�޸������������һ������տ��п�
				freeLink.remove(freeDown);//��������Ѿ��������ǿ�ϲ���������������ɾ��
			}
		}
		/*���������������������䲻���ڽ�(ע�⣺��������ܷ��������forѭ���ڣ��������ɶ��в��ϱ����ѭ��)*/
		if(freeUp==-1&&freeDown==-1) {
			Free addFree=new Free(recycle.address, recycle.len);
			freeLink.addLast(addFree);
		}
		System.out.println("����"+recycle.name+"���ڴ�ɹ���");
		Collections.sort(freeLink,new compatorFree());//���ȸı���Ҫ����
	}
	
	void printLink() {//����ڴ����
		System.out.println("********************************");
		System.out.println("�ڴ��ѷ������������");
		for(Iterator<Busy> iter=busyLink.iterator();iter.hasNext();) {
			busy=(Busy)iter.next();
			System.out.print(busy.name+":"+busy.address+"~"+(busy.address+busy.len)+"  ");
		}
		System.out.println('\n'+"�ڴ����������Ϣ�����Ӵ�С���У�");
		for(Iterator<Free> iter=freeLink.iterator();iter.hasNext();){//������Ч��O(1)Զ�����������O(N^2)
			free=(Free)iter.next();//�����������һ��Ԫ��
			System.out.print(free.address+"~"+(free.address+free.len)+"  ");//��������
		}
		System.out.println('\n'+"********************************");
	}
	
}
class compatorFree  implements Comparator<Free>{//����������Ӵ�С����
 
	@Override
	public int compare(Free o1, Free o2) {
		return o1.len<o2.len?1:-1;
	}
	
}