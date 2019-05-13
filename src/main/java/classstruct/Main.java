package classstruct;

public class Main {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		LocalClassLoader classLoader1 = LocalClassLoader.class.newInstance();
		classLoader1.setLocalPath("D:/");
		Class<?> clazz = classLoader1.loadClass("Student");
		Object obj = clazz.newInstance();
		System.out.println("1:"+clazz.hashCode());
		obj = null;
		System.out.println("2:"+clazz.hashCode());
		classLoader1 = null;
		System.out.println("3:"+clazz.hashCode());
		clazz = null;
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("============");

		classLoader1 = LocalClassLoader.class.newInstance();
		classLoader1.setLocalPath("D:/");
		System.gc();
		System.out.println("4:"+clazz.hashCode());
		clazz = classLoader1.loadClass("Student");
		obj = clazz.newInstance();
		System.out.println("5:"+clazz.hashCode());

	}
}
