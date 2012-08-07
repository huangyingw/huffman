import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Huffman {

	static public void main(String[] args) {
		Huffman hman = new Huffman();
		Map<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('a', 4);
		hashMap.put('b', 6);
		hashMap.put('c', 8);
		hashMap.put('d', 10);
		hashMap.put('e', 1);
		hman.InitHuffNode(hashMap);
		hman.CreateHuffman();
		hman.PrintHuffNodes();
		hman.HuffmanEncode();
		hman.InitHuffCodes();
		hman.HuffmanDecode();
	}

	private HuffCode[] huffCodes;
	private HuffNode[] huffNodes;

	private int nodeNum;

	public void InitHuffCodes() {
		huffCodes = new HuffCode[] { new HuffCode(), new HuffCode(),
				new HuffCode(), new HuffCode(), new HuffCode() };
	}

	public void CreateHuffman() {
		for (int i = 0; i < huffNodes.length; i++)
			huffNodes[i].parent = huffNodes[i].left = huffNodes[i].right = 0;

		for (int i = 5; i < huffNodes.length; i++) {
			int l, r;
			l = r = 0;
			int m1, m2;
			m1 = m2 = 20000;
			for (int j = 0; j < i; j++) {
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

	public void PrintHuffNodes() {
		for (int i = 0; i < huffNodes.length; i++) {
			System.out.println(huffNodes[i].data + ",index->" + i + ",weight->"
					+ huffNodes[i].weight + ",parent->" + huffNodes[i].parent
					+ ",left->" + huffNodes[i].left + ",right->"
					+ huffNodes[i].right);
		}
	}

	void HuffmanDecode() {
		// the following are decode the Huffman using the created tree
		for (int i = 0; i <= 4; i++) {
			huffCodes[i].start = 0;
			int child = i;
			int parent = huffNodes[child].parent;
			while (parent != 0) {
				if (huffNodes[parent].left == child)
					huffCodes[i].cd[huffCodes[i].start++] = '0';
				else if (huffNodes[parent].right == child)
					huffCodes[i].cd[huffCodes[i].start++] = '1';
				child = parent;
				parent = huffNodes[child].parent;
			}
			System.out.print(huffNodes[i].data + "->");
			for (int j = huffCodes[i].start - 1; j >= 0; j--) {
				System.out.print(huffCodes[i].cd[j]);
			}
			System.out.println();
		}
	}

	void HuffmanEncode() {
		for (int i = 1; i <= nodeNum; i++) {
			huffCodes[i].start = 0;
			int child = i;
			int parent = huffNodes[child].parent;
			while (parent != 0) {
				if (huffNodes[parent].left == child)
					huffCodes[i].cd[huffCodes[i].start++] = '0';
				else if (huffNodes[parent].right == child)
					huffCodes[i].cd[huffCodes[i].start++] = '1';
				child = parent;
				parent = huffNodes[child].parent;
			}
			for (int j = huffCodes[i].start - 1; j >= 0; j--) {
				System.out.println(huffCodes[i].cd[j]);
			}
			System.out.println();
		}
	}

	public void InitHuffNode(Map map) {
		huffNodes = new HuffNode[2 * map.size() - 1];
		Iterator iter = map.keySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Character obj = (Character) iter.next();
			huffNodes[i++] = new HuffNode(obj, (Integer) map.get(obj));

		}
		for (int i1 = map.size(); i1 < huffNodes.length; i1++) {
			huffNodes[i1] = new HuffNode();
		}
	}
}
