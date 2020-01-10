#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define max 10
int PN;//Process number 进程数量
int RT;//Resource types 资源种类
int Resourse[max];//资源
int Request[max];//申请
int Avaliable[max];//系统剩余资源
int	MAX[max][max];//进程最大资源需求
int Need[max][max];//进程目前资源需求
int Allocation[max][max];//进程已分配资源
int SafeOrder[max];//安全序列
int choice;//进程申请选择
void Menu()
{
	printf("------------------what you want-------------------\n");
	printf("*              1.初始化数据                      *\n");
	printf("*              2.检验T0时刻安全性                *\n");
	printf("*              3.资源申请                        *\n");
	printf("*              4.退出                           *\n");
	printf("------------------------------------------------\n");
}
void Input(int a, int b, int c[max][max])
{
	int i;
	for (i = 0; i < b; i++)
	if (i == 0) printf("   R%d ", i + 1);
	else printf("R%d ", i + 1);
	printf("\n");
	for (i = 0; i < a; i++)
	{
		printf("P%d  ", i + 1);
		for (int j = 0; j < b; j++)
			scanf("%d", &c[i][j]);
	}
}
void init()
{
	int i, j;
	printf("初始化: 请输入进程个数和资源总类: ");
	scanf("%d%d", &PN, &RT);
	printf("请输入各类资源数量: \n");
	for (i = 0; i < RT; i++)
		printf("R%d ", i + 1);
	printf("\n");
	for (i = 0; i < RT; i++)
		scanf("%d", &Resourse[i]);
	printf("请输入每个进程对每种资源的最大需求量:\n");
	Input(PN, RT, MAX);
	printf("请输入各类资源已分配量:\n");
	Input(PN, RT, Allocation);
	for (i = 0; i < PN; i++)
	for (j = 0; j < RT; j++)
		Need[i][j] = MAX[i][j] - Allocation[i][j];
	for (i = 0; i < RT; i++)
	{
		int sum[max] = { 0 };
		for (j = 0; j < PN; j++)
			sum[i] += Allocation[j][i];
		Avaliable[i] = Resourse[i] - sum[i];
	}
}
bool  Apply()//进程资源申请
{
	int i;
	printf("选择申请资源的进程: P");
	scanf("%d", &choice);
	printf("输入各类资源申请数量\n");
	for (i = 0; i < RT; i++)
		printf("R%d ", i + 1);
	printf("\n");
	for (i = 0; i < RT; i++)
		scanf("%d", &Request[i]);
	for (i = 0; i < RT; i++)
	if (Request[i] <= Need[choice - 1][i])
	{
		if (Request[i] > Avaliable[i])
		{
			printf("资源不足！"); return false;
		}
	}
	else
	{
		printf("申请超出所需！"); return false;
	}
	return true;
}
bool IsSafe()
{
	int i, j, k = 0, count, Work[max];
	bool Finish[max];
	for (i = 0; i < PN; i++)
		Finish[i] = false;
	for (i = 0; i < RT; i++)
		Work[i] = Avaliable[i];



	for (i = 0; i < PN; i++)
	{
		count = 0, j = 0;


		if (Finish[i] == false)
		{
			for (j = 0; j < RT; j++)
			if (Need[i][j] <= Work[j])
				count++;
		}


		if (count == RT)
		{
			for (j = 0; j<RT; j++)
				Work[j] += Allocation[i][j];
			Finish[i] = true;
			SafeOrder[k++] = i + 1;
			i = -1;//关键,也可以对i取模
		}

	}
	for (i = 0; i < PN; i++)
	if (Finish[i] == false)
		return false;
	return true;
}



bool banker()
{
	bool temp;
	int i = choice - 1, j;
	for (j = 0; j < RT; j++)//试分配
	{
		Avaliable[j] -= Request[j];
		Allocation[i][j] += Request[j];
		Need[i][j] -= Request[j];
	}





	for (j = 0; j < RT; j++){
		if (Need[i][j] == 0){
			temp = true;
		}
		else{
			temp = false;
			break;
		}

	}
	for( j = 0; j < RT;j++){
		if (temp){
			Avaliable[j] += Allocation[i][j];
			Allocation[i][j] = 0;
		}
	}






	if (IsSafe()) return true;
	else//操作撤回，回复原来状态
	{
		for (j = 0; j<RT; j++)
		{
			Avaliable[j] += Request[j];
			Allocation[i][j] -= Request[j];
			Need[i][j] += Request[j];
		}
		return false;
	}
	return true;
}
void printOrder()//输出安全序列
{
	int i;
	for (i = 0; i < PN; i++)
	{
		if (i == 0)
			printf("P%d", SafeOrder[i]);
		else
			printf("->P%d", SafeOrder[i]);
	}
	printf("\n");
}












void print()
{
	int i, j;
	printf("系统剩余: \n");
	for (i = 0; i < RT; i++)
		printf(" R%d", i + 1);
	printf("\n");
	for (i = 0; i < RT; i++)
		printf("%3d", Avaliable[i]);
	printf("\n系统已分配: \n");
	for (i = 0; i < RT; i++)
	if (i == 0) printf("    R%d", i + 1);
	else printf(" R%d", i + 1);
	printf("\n");
	for (i = 0; i < PN; i++)
	{
		printf("P%d", i + 1);
		for (j = 0; j < RT; j++)
			printf("%3d", Allocation[i][j]);
		printf("\n");
	}
	printf("系统尚需: \n");
	for (i = 0; i < RT; i++)
	if (i == 0) printf("    R%d", i + 1);
	else printf(" R%d", i + 1);
	printf("\n");
	for (i = 0; i < PN; i++)
	{
		printf("P%d", i + 1);
		for (j = 0; j < RT; j++)
			printf("%3d", Need[i][j]);
		printf("\n");
	}
}
int  main()
{
	bool flag;
	Menu();
Initialization:init();
	if (IsSafe() == false)
	{
		printf("当前系统状态不安全!\n请重新初始化数据");
		goto Initialization;
	}
	else
	{
		printf("当前系统处于安全状态,安全序列为:  ");
		printOrder();
		print();
	}
Application: flag = Apply();
	if (flag == false)
	{
		printf("申请失败!请重新申请\n");
		goto Application;
	}
	else
	{
		if (banker() == false)
		{
			printf("资源申请造成系统处于死锁状态!申请失败!请重新申请\n");
			goto Application;
		}
		else
		{
			printf("申请成功!安全序列为:  ");
			printOrder();
			print();
		}
	}
	int n;
	printf("是否还有进程申请资源? 是请输入1,否请输入0 :");
	scanf("%d", &n);
	if (n == 1)
		goto Application;
	return 0;
}
