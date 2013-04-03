package me.huzorro.simple.classLoader;

import static java.lang.System.out;

import java.lang.reflect.Field;
/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class ClassLoaderExample {
    private String name;
    /**
     * 
     */
    public ClassLoaderExample() {
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       
       out.println(ClassLoaderExample.class.getClassLoader());
       out.println(ClassLoader.getSystemClassLoader());
       out.println(ClassLoader.getSystemClassLoader().getParent());
       out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
       try {
            Class<?> c1 = Class.forName("java.lang.String");
            Class<?> c2 = Class.forName("sun.security.tools.KeyStoreUtil");
            Class<?> c3 = Class.forName("me.huzorro.simple.classLoader.ClassLoaderExample");
            
            out.println(c1.getClassLoader());
            out.println(c2.getClassLoader());
            out.println(c3.getClassLoader());
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
