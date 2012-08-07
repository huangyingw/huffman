import java.util.LinkedHashMap;

public class test {
	public static void main(String[] args) {

		LinkedHashMap<Character, Integer> hasmap = new LinkedHashMap<Character, Integer>();
		hasmap.put('a', 4);
		// hasmap.put('b', 5);
		hasmap.put('b', 6);
		hasmap.put('c', 8);
		hasmap.put('d', 10);
		hasmap.put('e', 1);

		Huffman huffman = new Huffman(hasmap);
		// String temp = huffman.enCodeString("abcd");
		// System.out.println(temp);
		// System.out.println(huffman.deCodeString(temp));

	}

}
