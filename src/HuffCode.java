public class HuffCode {
	public char[] cd;
	public int start;

	public HuffCode(char[] cd, int start) {
		super();
		this.cd = cd;
		this.start = start;
	}

	public HuffCode() {
		super();
		cd = new char[10000];
	}
}
