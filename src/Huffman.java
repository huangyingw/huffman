public class Huffman {

	static public void main(String[] args) {
		Huffman hman = new Huffman();
		hman.InitHuffNode();
		hman.CreateHuffman();
		hman.HuffmanEncode();
		hman.HuffmanDecode();
	}

	private HuffCode[] huffCode;
	private HuffNode[] huffNodes;

	private int nodeNum;

	public void CreateHuffman() {
		for (int i = 1; i <= 2 * nodeNum - 1; i++)
			huffNodes[i].parent = huffNodes[i].left = huffNodes[i].right = 0;

		for (int i = nodeNum + 1; i <= 2 * nodeNum - 1; i++) {
			int l, r;
			l = r = 0;
			int m1, m2;
			m1 = m2 = 20000;
			for (int j = 1; j < i; j++) {
				if (huffNodes[j].weight < m1 && huffNodes[j].parent == 0) {
					m2 = m1;
					r = l;
					m1 = huffNodes[j].weight;
					l = j;
				} else if (huffNodes[j].weight < m2 && huffNodes[j].parent == 0) {
					m2 = huffNodes[j].weight;
					r = j;
				}
			}
			huffNodes[l].parent = huffNodes[r].parent = i;
			huffNodes[i].left = l;
			huffNodes[i].right = r;
			huffNodes[i].weight = m1 + m2;
		}
	}

	void HuffmanDecode() {
		// the following are decode the Huffman using the created tree
		for (int i = 1; i <= nodeNum; i++) {
			huffCode[i].start = 0;
			int child = i;
			int parent = huffNodes[child].parent;
			while (parent != 0) {
				if (huffNodes[parent].left == child)
					huffCode[i].cd[huffCode[i].start++] = '0';
				else if (huffNodes[parent].right == child)
					huffCode[i].cd[huffCode[i].start++] = '1';
				child = parent;
				parent = huffNodes[child].parent;
			}
			for (int j = huffCode[i].start - 1; j >= 0; j--) {
				System.out.println(huffCode[i].cd[j]);
			}
			System.out.println();
		}
	}

	void HuffmanEncode() {
		for (int i = 1; i <= nodeNum; i++) {
			huffCode[i].start = 0;
			int child = i;
			int parent = huffNodes[child].parent;
			while (parent != 0) {
				if (huffNodes[parent].left == child)
					huffCode[i].cd[huffCode[i].start++] = '0';
				else if (huffNodes[parent].right == child)
					huffCode[i].cd[huffCode[i].start++] = '1';
				child = parent;
				parent = huffNodes[child].parent;
			}
			for (int j = huffCode[i].start - 1; j >= 0; j--) {
				System.out.println(huffCode[i].cd[j]);
			}
			System.out.println();
		}
	}

	public void InitHuffNode() {
		huffNodes = new HuffNode[5];
		huffNodes[0] = new HuffNode('a', 4);
		huffNodes[1] = new HuffNode('b', 6);
		huffNodes[2] = new HuffNode('c', 8);
		huffNodes[3] = new HuffNode('d', 10);
		huffNodes[4] = new HuffNode('e', 1);
	}
}
