package complementcode;

public class Test {
	public static void main(String[] args) {
		int a = 3;
		int b = -2;
		int c = a + (((~(b << 1)) >>> 1) & (b & Integer.MIN_VALUE));
		System.out.println( c);
		c = (((~(c << 1)) >>> 1) & (c & Integer.MIN_VALUE));
		System.out.println( c);


	}
}
