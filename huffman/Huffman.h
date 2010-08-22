#include<iostream>
using namespace std;

#define MAXVALUE 10000  /*权值最大值*/
#define MAXLEAF 30 /*叶子最多个数*/
#define MAXNODE MAXLEAF*2-1    /* 结点数的个数*/
#define MAXBIT 50        /*编码的最大位数*/

typedef struct node   /*结点类型定义*/
{
    char letter;
    int weight;
    int parent;
    int lchild;
    int rchild;
}HNodeType;

typedef struct    /*编码类型定义*/
{
    char letter;
    int bit[MAXBIT];
    int start;
}HCodeType;

typedef struct 
{
    char data; //结点值
    int weight; //
    int parent; //父结点
    int left; //左结点
    int right;//右结点
}HuffNode;

typedef struct  /*输入符号的类型*/
{
    char s;
    int num;
}lable;

typedef struct
{
    char cd[MAX];
    int start;
}HuffCode;

class Huffman
{
private:
	int nodeNum;
	HuffNode *huffNode;
	HuffCode *huffCode;
	int count;
	ofstream fout;
	

public:
	lable data[30];
	Huffman();
	~Huffman();
	void PrintWeight();
	void HuffmanCode();
	void HuffmanTree(HNodeType HuffNode[]);
};

void Huffman::HuffmanTree(HNodeType HuffNode[])
{
  	int i,j,m1,m2,x1,x2,temp1;
  	char temp2;
  	for (i=0;i<2*count-1;i++)      /*结点初始化*/
  	{
  	  HuffNode[i].letter=0;
  	  HuffNode[i].weight=0;
  	  HuffNode[i].parent=-1;
  	  HuffNode[i].lchild=-1;
  	  HuffNode[i].rchild=-1;
  	}
  	for (i=0;i<count-1;i++)
		{
  	  for (j=i+1;j<count-1;j++) /*对输入字符按权值大小进行排序*/
  		{  	
  	    if (data[j].num>data[i].num)
  	    {
  	      temp1=data[i].num;
  	      data[i].num=data[j].num;
  	      data[j].num=temp1;
  	      temp2=data[i].s;
  	      data[i].s=data[j].s;
  	      data[j].s=temp2;
  	    }
  	  }
		}
  	for (i=0;i<count;i++)
  	{
  	  HuffNode[i].weight=data[i].num;
  	  HuffNode[i].letter=data[i].s;
  	}
  	for (i=0;i<count-1;i++)        /*构造huffman树*/
  	{
  	  m1=m2=MAXVALUE;
  	  x1=x2=0;
  	  for (j=0;j<count+i;j++)/*寻找权值最小与次小的结点*/
  	  {
  	    if (HuffNode[j].parent==-1&&HuffNode[j].weight<m1)
  	    {
  	      m2=m1;
  	      x2=x1;
  	      m1=HuffNode[j].weight;
  	      x1=j;
  	    }
  	    else if (HuffNode[j].parent==-1&&HuffNode[j].weight<m2)
  	    {
  	      m2=HuffNode[j].weight;
  	      x2=j;
  	    }
  	  }
  	  HuffNode[x1].parent=count+i;
  	  HuffNode[x2].parent=count+i;         /*权值最小与次小的结点进行组合*/
  	  HuffNode[count+i].weight=HuffNode[x1].weight+HuffNode[x2].weight;
  	  HuffNode[count+i].lchild=x1;
  	  HuffNode[count+i].rchild=x2;
  	}
} 	

void Huffman::HuffmanCode()
{
    HNodeType HuffNode[MAXNODE];
    HCodeType HuffCode[MAXLEAF],cd;
    int i,j,c,p;

    HuffmanTree(HuffNode);

    for (i=0;i<count;i++)     /*按结点位置进行编码*/
    {
        cd.start=count-1;
        c=i;
        p=HuffNode[c].parent;
        while (p!=-1)
        {
            if (HuffNode[p].lchild==c)
                cd.bit[cd.start]=0;
            else cd.bit[cd.start]=1;
            cd.start--;
            c=p;
            p=HuffNode[c].parent;
        }
        for (j=cd.start+1;j<count;j++)    /*储存编码*/
            HuffCode[i].bit[j]=cd.bit[j];
        HuffCode[i].start=cd.start;
    }
    for (i=0;i<count;i++)
    {
        HuffCode[i].letter=HuffNode[i].letter;
        fout<<HuffCode[i].letter<<" ";

        for (j=HuffCode[i].start+1;j<count;j++)
            fout<<HuffCode[i].bit[j];
        fout<<endl;
    }
}

Huffman::~Huffman()
{
	fout.close();
}

Huffman::Huffman()
{
	count=0;
	fout.open("output.txt");
	char *user_input;
	int i;

	char s[100]="feifjakdfjioejfkdsifwfasdfawffawefawefawfafds";
	for (i=0;i<30;i++)
	{
    data[i].s=0;
		data[i].num=0;
	}
	user_input=s;
  while (*user_input)     /*计算字符个数与出现次数(即权值)*/
  {
      for (i=0;i<=count+1;i++)
      {
          if (data[i].s==0)
          {
              data[i].s=*user_input;
              data[i].num++;
              count++;
              break;
          }
          else if (data[i].s==*user_input)
          {
              data[i].num++;
              break;
          }
      }
      user_input++;
  }
}

void Huffman::PrintWeight()
{
	fout<<"different letters:"<<count<<endl;
  for (int nav=0;nav<count;nav++)
  {
      fout<<data[nav].s<<" ";
      fout<<"weight:"<<data[nav].num<<endl;
  }
}