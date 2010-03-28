using namespace std;

typedef struct 
{
    char data; //结点值
    int weight; //
    int parent; //父结点
    int left; //左结点
    int right;//右结点
}HuffNode;

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

public:
	Huffman();
	void CreateHuffman();
	void HuffmanEncode();//use the created tree to encode Huffman
	void HuffmanDecode();//use the created tree to decode Huffman
};

Huffman::Huffman()
{
	
}

void Huffman::CreateHuffman()
{
	//先把数组初始化
	for(int i=1;i<=2*nodeNum-1;i++)
		huffNode[i].parent=huffNode[i].left=huffNode[i].right=0;
	
	//以下部分生成huffman树，但应该还有改进的余地。
	//此处为什么是2*nodeNum-1?
	for(int i=nodeNum+1;i<=2*nodeNum-1;i++)
	{
		//l存储左子节点的编号，r存储右子节点的编号
		//其中左子节点要小于右子节点
		//l是最小节点
		int l,r;
		l=r=0;
		//此处m1存储最小值，m2存储次小值
		int m1,m2;
		m1=m2=20000;
		for(int j=1;j<i;j++)
		{
			if(huffNode[j].weight<m1&&huffNode[j].parent==0)
			{
				m2=m1;
				r=l;
				m1=	huffNode[j].weight;
				l=j;
			}
			else if(huffNode[j].weight<m2&&huffNode[j].parent==0)
			{
				m2=	huffNode[j].weight;
				r=j;
			}
		}
		huffNode[l].parent=huffNode[r].parent=i;
		huffNode[i].left=l;
		huffNode[i].right=r;
		huffNode[i].weight=m1+m2;
	}
}

void Huffman::HuffmanEncode()
{
	//接下是根据huffman树进行编码
	for(int i=1;i<=nodeNum;i++)
	{
		huffCode[i].start=0;
		int child=i;
		int parent=huffNode[child].parent;
		while(parent!=0)
		{
			if(huffNode[parent].left==child)
				huffCode[i].cd[huffCode[i].start++]='0';
			else if(huffNode[parent].right==child)
				huffCode[i].cd[huffCode[i].start++]='1';
			child=parent;
			parent=huffNode[child].parent;
		}
		for(int j=huffCode[i].start-1;j>=0;j--)
		{
			cout<<huffCode[i].cd[j];
		}
		cout<<endl;
	}
}

void Huffman::HuffmanDecode()
{
	//the following are decode the Huffman using the created tree
	for(int i=1;i<=nodeNum;i++)
	{
		huffCode[i].start=0;
		int child=i;
		int parent=huffNode[child].parent;
		while(parent!=0)
		{
			if(huffNode[parent].left==child)
				huffCode[i].cd[huffCode[i].start++]='0';
			else if(huffNode[parent].right==child)
				huffCode[i].cd[huffCode[i].start++]='1';
			child=parent;
			parent=huffNode[child].parent;
		}
		for(int j=huffCode[i].start-1;j>=0;j--)
		{
			cout<<huffCode[i].cd[j];
		}
		cout<<endl;
	}
}
