#include <stdio.h>
#include <malloc.h>
#define MAX 10

typedef struct huffmanTreeNode{
 int weight;
 int parent,left,right;
}htNode;

void select(htNode *ht,int n,int *s1,int *s2){
 int i=0;
 *s1=*s2=-1;
 while(i<n){
 if(ht[i].parent!=-1){i++;continue;}
 else{
 if(ht[i].weight<ht[*s1].weight||*s1==-1){
 *s2=*s1;
 *s1=i;
 }
 else if(ht[i].weight<ht[*s2].weight||*s2==-1)
 *s2=i;
 i++;
 }
 }
}

bool createHtree(htNode *ht,int n){
 int i=n,m=2*n-1,s1,s2;
 for(;i<m;i++){
 select(ht,n++,&s1,&s2);
 if(s1==-1||s2==-1) return true;
 ht[i].left=s1; ht[i].right=s2;
 ht[s1].parent=i; ht[s2].parent=i;
 ht[i].weight=ht[s1].weight+ht[s2].weight;
 }
 return true;
}

void printfData(htNode *ht,int m){
 int i=0;
 printf("location\tparent\tleft\tright\tweight\n");
 while(i<m){
 printf("%d\t\t%d\t%d\t%d\t%d\n",i,ht[i].parent,ht[i].left,ht[i].right,ht[i].weight);
 i++;
 }
}

void initHtree(htNode *ht,int n){
 int m=2*n-1,i=0;
 printf("请输入每一个节点的权重:");
 for(;i<n;i++){
 scanf("%d",&(ht[i].weight));
 getchar();
 ht[i].left=-1;
 ht[i].right=-1;
 ht[i].parent=-1;
 } 
 for(i=n;i<m;i++){
 ht[i].left=-1;
 ht[i].right=-1;
 ht[i].parent=-1;
 ht[i].weight=-1;
 }

}

void huffmanCode(htNode *ht,int n){

 char *temp=(char *)malloc(n*sizeof(char));
 char **code=(char **)malloc(n*sizeof(char));
 int i=0,j=0,start=n,parent,t;
 for(i=0;i<n;i++)
 code[i]=(char *)malloc(n*sizeof(char));
 for(i=0;i<n;i++){
 start=n;
 parent=ht[i].parent; t=i;
 while(parent!=-1){
 temp[--start]=(ht[parent].left==t?'0':'1');
 t=parent;
 parent=ht[parent].parent;
 }
 for(j=0;start<n;j++,start++){
 code[i][j]=temp[start];
 }
 code[i][j]='\0';
 }
 free(temp);
 for(i=0;i<n;i++){
 for(j=0;j<n;j++){
 if(code[i][j]=='\0') break;
 else printf("%c",code[i][j]);
 }
 printf("\t");
 }
 printf("\n");
}

int main(void){
 htNode *ht;
 int n,m,i=0;
 printf("请输入节点数:");
 scanf("%d",&n); getchar();
 m=2*n-1;
 ht=(htNode *)malloc(m*sizeof(htNode));
 initHtree(ht,n);
 createHtree(ht,n);
 printfData(ht,m);
 printf("\n赫夫曼编码is:");
 huffmanCode(ht,n);
 return 0;
}
