package me.huzorro.simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum SingletonTest implements Runnable  {
	INSTANCE("test");
	private String str;
	private SingletonTest(String str) {
		this.str = str;
		System.out.println(str);
	}
	public static SingletonTest getInstance() {
		return INSTANCE;
	}
	public void t() {
		System.out.println("hi");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		t();
	}	
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		new Thread(SingletonTest.INSTANCE).start();
		new Thread(SingletonTest.INSTANCE).start();

		Class<SingletonTest> classz = null;
		try {
			classz = (Class<SingletonTest>) Class
					.forName("me.huzorro.simple.SingletonTest");
			SingletonTest singletonTest = Enum.valueOf(classz, "INSTANCE");
			new Thread(singletonTest).start();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Method m = classz.getMethod("getInstance", null);
			SingletonTest singletonTest = (SingletonTest) m.invoke(m, null);
			
			new Thread(singletonTest).start();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
