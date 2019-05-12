package base64;

import java.util.Base64;

public class Base64Test {
	private static final char[] toBase64 = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};
	public static void main(String[] args) {
		Base64.Encoder encoder = Base64.getEncoder();
		String string = "中文sdf";
		byte[] bytes = encoder.encode(string.getBytes());
		System.out.println(new String(bytes));

		System.out.println(int2binarychar('s',2));
		System.out.println(new String(base64_3(string)));
//		System.out.println(int2binarychar(257,16));
	}

	public static byte[] base64_3(String s){
		byte[] bytes = s.getBytes();
		int i = 0;
		int j = 0;
		byte[] des = new byte[4*(bytes.length+2)/3];
		int total = bytes.length/3*3;
		while (i < total){
			int bits = ((bytes[i++] & 0xff) << 16 )|
					((bytes[i++] & 0xff) << 8) |
					(bytes[i++] & 0xff);
			des[j++] = (byte) toBase64[bits >>> 18];
			des[j++] = (byte) toBase64[(bits >>> 12) & 0x3f];
			des[j++] = (byte) toBase64[(bits >>> 6) & 0x3f];
			des[j++] = (byte) toBase64[bits & 0x3f];
		}
		if(i < bytes.length){ //表示还有剩余的1位或者2位
			des[j++] = (byte) toBase64[bytes[i++] & 0xff>>> 2];
			if(i == bytes.length){//表示只多了一位
				des[j++] = (byte) toBase64[(bytes[i-1] & 0x3) << 4];
				des[j++] = '=';
				des[j++] = '=';
			}else{//表示多了2位
				des[j++] = (byte) toBase64[(bytes[i-1] & 0x3) << 4 | (bytes[i] & 0xff) >>> 4];
				des[j++] = (byte) toBase64[((bytes[i] & 0xff) << 2) & 0x3f];
				des[j++] = '=';
			}
		}

		return  des;

	}

	public static byte[] base64_2(String s){
		byte[] bytes = s.getBytes();
		int i = 0;
		int j = 0;
		byte[] des = new byte[4*(bytes.length+2)/3];
		int total = bytes.length/3;
		int left = bytes.length%3;
		while (i < total){
			int bits = ((bytes[i++] & 0xff) << 16 )|
					((bytes[i++] & 0xff) << 8) |
					(bytes[i++] & 0xff);
			des[j++] = (byte) toBase64[bits >>> 18];
			des[j++] = (byte) toBase64[(bits >>> 12) & 0x3f];
			des[j++] = (byte) toBase64[(bits >>> 6) & 0x3f];
			des[j++] = (byte) toBase64[bits & 0x3f];
		}
		if(left>0){ //表示还有剩余的1位或者2位
			if(left == 1){
				int bits = ((bytes[i++] & 0xff) << 16 );
				des[j++] = (byte) toBase64[bits >>> 18];
				des[j++] = (byte) toBase64[(bits >>> 12) & 0x3f];
				des[j++] = '=';
				des[j++] = '=';
			}else if(left == 2){
				int bits = ((bytes[i++] & 0xff) << 16 )|
						((bytes[i++] & 0xff) << 8);
				des[j++] = (byte) toBase64[bits >>> 18];
				des[j++] = (byte) toBase64[(bits >>> 12) & 0x3f];
				des[j++] = (byte) toBase64[(bits >>> 6) & 0x3f];
				des[j++] = '=';
			}
		}

		return  des;

	}
	public static byte[] base64(String s){
		byte[] bytes = s.getBytes();
		int i = 0;
		int j = 0;
		byte[] des = new byte[4*(bytes.length+2)/3];
		int buwei = 0;
		while (i < bytes.length){
			int s1;
			int s2;
			int s3;
			s1 = bytes[i++] & 0xff;
			if(i>= bytes.length){s2 = 0;buwei++;}else {s2 = bytes[i++] & 0xff;}
			if(i>= bytes.length){s3 = 0;buwei++;}else {s3 = bytes[i++] & 0xff;}

			des[j++] = (byte) toBase64[s1 >>> 2];

			byte b1 = (byte) ((s1 & 0x3) << 4);
			byte b2 = (byte) (s2 >>> 4);
			des[j++] = (byte) toBase64[b1 | b2];

			b1 = (byte) ((s2 & 0xf) << 2);
			b2 = (byte) (s3 >>> 6);
			des[j++] = (byte) toBase64[b1 | b2];

			b1 = (byte) (s3 & 0x3f );
			des[j++] = (byte) toBase64[b1];
		}
		while (buwei>0){
			des[j-buwei] = '=';
			buwei--;
		}
		return des;

	}

	public static String int2binarychar(int a,int radix){
		int i = a;
		String des = "";
		while (i>0){
			des = i%radix + des;
			i = i/radix;
		}
		return des;
	}
}
