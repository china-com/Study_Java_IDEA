import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;

//获取字节码对象：class对象
public class ClassInstanceDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        //需求：获取java.uti.Date类的字节码对象
        // 方式l:使用class属性
        Class<java.util.Date> clz1=java.util.Date.class;
        //方式二;通过对象的getClass()方法来获取，getClass是Object类中的方法
        Object obj=new java.util.Date();
        Class<?> clz2=obj.getClass();
        //方式三：通过Class类中的静态方法，forName(String className)
        Class<?> clz3=Class.forName("java.util.Date");

        System.out.println(clz1);
        System.out.println(clz2);
        System.out.println(clz3);

    }
}
