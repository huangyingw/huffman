public class huffman {
	
	typedef struct
	{
	  char data; //����ֵ
	  int weight; //
	  int parent; //�����
	  int left; //�����
	  int right;//�ҽ��
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
	    void CreateHuffman();
	    void HuffmanEncode();//use the created tree to encode Huffman
	    void HuffmanDecode();//use the created tree to decode Huffman
	};

	void Huffman::CreateHuffman()
	{
	  //�Ȱ�������ʼ��
	  for(int i=1;i<=2*nodeNum-1;i++)
	    huffNode[i].parent=huffNode[i].left=huffNode[i].right=0;

	  //���²�������huffman������Ӧ�û��иĽ������ء�
	  //�˴�Ϊʲô��2*nodeNum-1?
	  for(int i=nodeNum+1;i<=2*nodeNum-1;i++)
	  {
	    //l�洢���ӽڵ��ı��ţ�r�洢���ӽڵ��ı��
	    //�������ӽڵ�ҪС�����ӽڵ
	    //l����С�ڵ
	    int l,r;
	    l=r=0;
	    //�˴�m1�洢��Сֵ��m2�洢��Сֵ
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
	  //�����Ǹ���huffman�����б��
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
	static public void main(String[] args) {
		Huffman huffman;
		  huffman.CreateHuffman();
	}
}
