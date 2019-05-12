package ifand;

public class Test {
	static int money = 201;
	static boolean faqian(){
		if(money>100){
			money -=100;
			return true;
		}
		return false;
	}
	static boolean cunqian(){
		money +=100;
		return true;
	}
	public static void main(String[] args) {
		if(!faqian()){
			cunqian();
			faqian();
			System.out.println("钱终于够了");

		}else {
			System.out.println("钱够发，唱歌");
		}
		if(!faqian() && cunqian() && faqian()){
			System.out.println("钱终于够了");
		}else {
			System.out.println("钱够发，唱歌");
		}
	}
}
