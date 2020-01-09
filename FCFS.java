注：本代码主要来自豆丁，加入本人的部分修改。。
//本FCFS是用不带都结点的链表完成。当然也可以用其他数据结构 
#include<stdio.h>
#include<stdlib.h>



typedef struct PCB      //定义进程控制块 
{
    char ID[3];         //进程号 
    char name[10];      //进程名 
    char state;         //运行状态 
    int arrivetime;     //进程到达时间 
    int servicetime;    //进程服务时间 
    int starttime;      //进程开始时间 
    int finishtime;     //进程完成时间 
    float turnaroundtime; //进程周转时间 
    float weightedturnaroundtime;  //进程带权周转时间 
    struct PCB *next;   //指向下一个进程的指针 
}pcb;
int time; //计时器
int n;    //进程个数
pcb *head=NULL,*p,*q; //进程的指针
void run_fcfs(pcb *p1)
{
    time=p1->arrivetime>time?p1->arrivetime:time;
    p1->starttime=time;
    printf("\n现在时间是%d,开始云行作业%s\n",time,p1->name);
    time+=p1->servicetime;
    p1->state='T';
    p1->finishtime=time;
    p1->turnaroundtime=p1->finishtime-p1->arrivetime;
    p1->weightedturnaroundtime=p1->turnaroundtime/p1->servicetime;
    printf("ID  到达时间  开始时间  服务时间  完成时间  周转时间  带权周转时间\n");
    printf("%s%6d%10d%10d%8d%10.1f%10.2f\n",p1->ID,p1->arrivetime,p1->starttime,p1->servicetime,p1->finishtime, p1->turnaroundtime,p1->weightedturnaroundtime); 
 }  
void find_fcfs() //寻找未完成的pcb
{
    int i,j;
    p=head;
    for(i=0;i<n;i++)
    {
        if(p->state=='F')
        {
            q=p; //用q指针把未完成的pcb"摘"下来 
            run_fcfs(q); 
        }
        p=p->next;
        }


   } 

void found_fcfs() //获得进程信息并创建进程
{
    int num;
    printf("\n进程个数为：");
    scanf("%d",&n);
    for(num=0;num<n;num++)
    {
        p=(pcb*)malloc(sizeof(pcb));
        printf("请依次输入：\nID   进程名  到达时间  服务时间\n");
        scanf("%s\t%s\t%d\t%d",&p->ID,&p->name,&p->arrivetime,&p->servicetime);
        if(head==NULL)
        {

            head=p;q=p;time=p->arrivetime;
        }
        if(p->arrivetime<time) time=p->arrivetime;
        q->next=p;
        p->starttime=0;
        p->finishtime=0;
        p->turnaroundtime=0;
        p->weightedturnaroundtime=0;
        p->next=NULL;
        p->state='F';
        q=p;
     } 
  }
void main()
{
    printf("先来先服务模拟算法：");
    found_fcfs();
    p=head;
    find_fcfs(); 
}

