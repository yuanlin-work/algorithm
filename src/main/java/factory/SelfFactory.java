package factory;

/**
 * 静态内部类单例模式
 */
public class SelfFactory {
	public static class Holder{
		private static Object instance = new Object();
	}

	public static Object getInstance(){
		return Holder.instance;
	}

	public static void main(String[] args) {
		System.out.println(SelfFactory.getInstance());
	}
}
