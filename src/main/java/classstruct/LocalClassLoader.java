package classstruct;

import java.io.*;

public class LocalClassLoader extends ClassLoader {
	public LocalClassLoader(){
		super(ClassLoader.getSystemClassLoader());
	}

	private String localPath;
	public void setLocalPath(String path){
		assert path.endsWith(System.getProperty("file.separator"));
		this.localPath=path;
	}
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = getClassBytes(name);
		Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
		return clazz;
	}
	private byte[] getClassBytes(String file) {
		InputStream file1 = null;
		byte[] _r = null;
		try {
			file1 = new FileInputStream(localPath+file+".class");
			byte[] bytes = new byte[1024];
			int readed = file1.read(bytes);
			_r = new byte[readed];
			System.arraycopy(bytes,0,_r,0,readed);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return _r;
	}

}
