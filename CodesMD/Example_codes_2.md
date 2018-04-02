#### //覆盖与隐藏
 * 1):在满足继承的访问权限下，隐藏父类静态方法：若子类定义的静态方法的签名和父类中的静态方法
 * 签名相同，那么此时就是隐藏父类方法。注意：仅仅是静态方法，子类和父类存在一模一样的静态方法
 * 2）：满足继承的访问权限下，隐藏父类字段：若子类中定义的字段和父类中的字段名相同（不管类型）
 * 此时就隐藏父类的字段，此时只能通过super访问被隐藏的字段
 * 3）：隐藏本类字段：若本类中某局部变量名和字段名相同，此时就是隐藏本类字段（成员变量），
 * 此时只能通过this访问被隐藏的字段。
 * static不可以和super和this共存
```java
//父类
class SuperClass{
	public String name="supername";
	public static void ooxx(){
		
	}
}
//子类
class SubClass extends SuperClass{
	public int name=18;//隐藏了父类的name字段
	public void doWork(){
		boolean name=false;//隐藏了本类中的字段
		System.out.println(name);//false
		System.out.println(this.name);//18
		System.out.println(super.name);//supername
	}
	public static void ooxx(){//隐藏，不叫覆盖
		
	}
}
public class ExtendsDemo{
	public static void main(String[] args){
		new SubClass().doWork();
	}
}
```
输出：
```
false
18
supername
```

#### //演示引用类型转换
>需求：动物吃完食物后就去做事
```java
//动物类
class Animal{
	public void eat(){
		System.out.println("吃食物");
	}
}
//狗类
class Dog extends Animal{
	public void eat(){
		System.out.println("吃肉骨头");
	}
	public void watch(){
		System.out.println("看门");
	}
}
//猫类
class Cat extends Animal{
	public void eat(){
		System.out.println("吃鱼");
	}
	public void catchMouse(){
		System.out.println("逮老鼠");
	}
}
//饲养员
class Person{
	//喂养所有动物
	public void feed(Animal a){
		System.out.println("feeding....");
		a.eat();
		//干活去
		//instanceof运算符：判断该对象是否是某一个类的实例
		if(a instanceof Dog){
			Dog d=(Dog)a;
			d.watch();
		}
		else if(a instanceof Cat){
			Cat c=(Cat)a;
			c.catchMouse();
		}
	}
}
public class Demo{
	public static void main(String[] args){
		//创建饲养员对象
		Person p=new Person();
		//创建狗对象
		Dog d=new Dog();
		p.feed(d);
		//创建猫对象
		Cat c=new Cat();
		p.feed(c);
		System.out.println("-------------------");
		Object obj="ABC";//把String对象赋值给Object类型
		//instanceof运算符：判断该对象是否是某一个类的实例
		//若对象时类的实例返回true,若对象是类的父类的实例也返回true
		System.out.println(obj instanceof Object);//TRUE
		System.out.println(obj instanceof String);//TRUE
		//getClass()获取对象的真实类型
		System.out.println(obj.getClass());//获取对象的真实类型
		System.out.println(obj.getClass()==String.class);//true
		System.out.println(obj.getClass()==Object.class);//false
	}
}
```
输出：
```
feeding....
吃肉骨头
看门
feeding....
吃鱼
逮老鼠
-------------------
true
true
class java.lang.String
true
false
```

 * 以下只讨论运行的结果，具体实现过程略
 * 多态时：父类方法不为静态和私有方法时，子类可以重写（覆盖）父类方法，此时执行的子类的方法
 * 当子类为静态方法，并且不为私有修饰时，父类中static修饰的方法并不会被覆盖，而是隐藏，执行的为父类的方法
 * 字段（成员变量）没有覆盖这个概念，只有隐藏，此时也是执行父类中的字段属性。
 ```java
class SuperClass{
	public String name="super.name";
	public void doWork(){
		System.out.println("super.doWork");
	}
	static public void doThing(){
		System.out.println("super.doThing");
	}
}
class SubClass extends SuperClass{
	public String name="sub.name";
	public void doWork(){
		System.out.println("sub.doWork");
	}
	static public void doThing(){
		System.out.println("sub.doThing");
	}
}
public class Test{
	public static void main(String[] args){
		SuperClass s1=new SubClass();
		System.out.println(s1.name);//super.name
		s1.doWork();//sub.doWork
		s1.doThing();//super.doThing,会报出提示：应该以静态方式访问类型 SuperClass 中的静态方法 doThing（）
		//SubClass.doThing();//sub.doThing,这种访问方式不存在多态，使用到上面创建的对象才会有多态，才会有上面的性质

	}
}


//演示代码块
public  class Test{
	{//初始化代码块
		System.out.println("初始化代码块");
	}
	Test()//构造器
	{
		System.out.println("构造器....");
	}
	static{//静态初始化代码块
		System.out.println("HeloWorld");
	}
	public static void main(String[] args){
		System.out.println("进入main方法");
		//创建三个Test对象
		new Test();
		new Test();
		new Test();
	}
}
```
输出：
```
HeloWorld
进入main方法
初始化代码块
构造器....
初始化代码块
构造器....
初始化代码块
构造器....
```

#### //在主方法中不进行任何操作，打印"HelloWorld"
```java
public  class Test{
	static{
		System.out.println("HeloWorld");
	}
	public static void main(String[] args){
		
	}
}

/*
 * 面试题：分析下列代码的执行顺序
 * 输出结果：
 * 		1             //App类依赖于SubClass类，所以得先把SubClass类的字节码加载进JVM
		构造SuperClass
		2
		4
		3
		Hello
 */
public class App{
	/*
	 * 静态成员是通过静态代码块来进行初始化的，非静态的成员变量则是要通过构造器完成初始化
	 */
	private static App a=new App();//(2)相当于：private static App a=null，static初始化
	private SubClass t=new SubClass();//(1)相当于private SubClass t=null;非static字段的初始化其实都在构造器中，优先执行的
	//在A类中用到了B类，即A类依赖于B类，则执行A类之前就得执行B类
	static{
		//(2)d=new App();
		System.out.println(3);
	}
	public App(){
		//(1)t=new SubClass();
		System.out.println(4);
	}
	public static void main(String[] args){
		System.out.println("Hello");
	}
}
//父类
class SuperClass{
	SuperClass(){
		System.out.println("构造SuperClass");
	}
}
//子类
class SubClass extends SuperClass{
	static{
		System.out.println(1);
	}
	SubClass(){
		//super();//隐式，调用父类无参构造器
		System.out.println(2);
	}
}


/*
public class App{
	public static void main(String[] args){
		Person1 p1=new Person1();
		System.out.println(p1.INFO);
//		p1.INFO="hhhhh";//不能对终态字段 Person1.INFO 赋值
//		System.out.println(p1.INFO);
	}
}
class Person1{
	public final String INFO="最初始的值";
}
*/
public class App{
	public static void main(String[] args){
		final Person1 p1=new Person1();
		System.out.println(p1.INFO);
		p1.INFO="hhhhh";
		System.out.println(p1.INFO);
		p1=new Person1();//报错，不能对终态局部变量 p1 赋值。它必须为空白，并且不使用复合赋值
	}
}
class Person1{
	public String INFO="最初始的值";
}
```

#### //单例设计模式
* 只需要创建一个对象，节省内存空间，不需要在其他需要用得到该类中方法的地方再创建对象
* 在外部只需要通过静态的方法获取该类中的对象即可，而后通过该对象访问该类的其他非静态方法
```java
class ArrayUtil{
	//1):必须在该类中自己创建出一个对象（私有静态）
	private static ArrayUtil instance=new ArrayUtil();
	//2)：私有化自身的构造器，防止外界通过构造器创建新的对象
	private ArrayUtil(){//使外部不能调用该构造器创建对象，而是通过ArrayUtil.getInstance()来访问，节约内存空间
		
	}
	//3):向外暴露一个公共的静态方法用于获取自身的对象
	public static ArrayUtil getInstance(){
		return instance;
	}
	//排序操作，外界不可直接获取
	public void sort(int[] arr){//该方法并没有涉及到排序的算法
	System.out.println("排序操作");
	}
}
class SigletonDemo{
	public static void main(String[] args){
		System.out.println(ArrayUtil.getInstance()==ArrayUtil.getInstance());
		//需要做排序：现在假设在不同的类中
		ArrayUtil.getInstance().sort(null);
		//需要做排序：现在假设在另一个类中
		ArrayUtil.getInstance().sort(null);
		//需要做排序：现在假设在另一个类中
		ArrayUtil.getInstance().sort(null);
	}
}
```
输出：
```
true
排序操作
排序操作
排序操作
```

#### //演示享元模式valueOf的缓存设计
//Integer.valueOf()可以缓存[-128,127]之间的数据，超过范围，则会重新开辟数据空间
//Integer.valueOF()享元模式有利于节省内存空间
```java
public class Test{
	public static void main(String[] args){
		Integer i1=new Integer(123);
		Integer i2=new Integer(123);
		System.out.println(i1==i2);//false
		
		Integer i3=Integer.valueOf(123);
		Integer i4=Integer.valueOf(123);
		System.out.println(i3==i4);//true:在[-128,127]范围，就获取缓存中的数据
		
		Integer i5=123;//此时会自动装箱，底层为：Integer.valueOf(123);
		Integer i6=123;//底层：Integer.valueOf(123);
		System.out.println(i5==i6);//true
		System.out.println("--------------------");
		Integer i7=new Integer(250);
		Integer i8=new Integer(250);
		System.out.println(i7==i8);//false
		
		Integer i9=Integer.valueOf(250);//250不在[-128,127]之间，就得new Integer();
		Integer i10=Integer.valueOf(250);
		System.out.println(i9==i10);//false
		
		Integer i11=250;
		Integer i12=250;
		System.out.println(i11==i12);//false
		System.out.println(i11.equals(i12));//true
	}
}
```

#### //操作的模板类
```java
abstract class MuBan{
	//模板方法，总体算法的骨架，子类不能修改
	final public long getTotalTime(){
		long begin=System.currentTimeMillis();//开始时间
		//具体操作（留给子类去完成）
		doWork();
		long end=System.currentTimeMillis();//结束时间
		return end-begin;//返回时间差
	}
	//具体操作，子类必须覆盖该抽象方法
	abstract public void doWork();
}
//String成员连续链接10000次
class StringOperat extends MuBan{
	public void doWork(){
		String str="";
		for(int i=0;i<10000;i++){
			str+=i;
		}
	}
}
//int的成员相加1000000次
class IntOperat extends MuBan{
	public void doWork(){
		int sum=0;
		for(int i=0;i<1000000;i++){
			sum+=1;
		}
	}
}
public class Test{
	public static void main(String[] args){
		System.out.println(new StringOperat().getTotalTime());//552
		System.out.println(new IntOperat().getTotalTime());//2
	}
}
```

 * 当几个相似，但不同类的对象要对同一个内容进行操作的时候，若需要传入参数，则不同的对象需要传入不同的对象参数
 * 则需要设计多个相似的方法以给不同数据类型的对象调用，这样做就非常的不好
 * 若多个对象都是某一个接口的实现类，则所需实现的方法的参数就以接口来修饰
#### //规定USB规范
```java
interface IUSB{
	void swapData();
}
//USB版本鼠标
class Mouse implements IUSB{
	public void swapData(){
		System.out.println("鼠标在移动");
	}
}
//USB版本打印机
class Print implements IUSB{
	public void swapData(){
		System.out.println("打印，嘟嘟嘟....");
	}
}
//主板
class MotherBoard{
	private static IUSB[] usbs=new IUSB[6];//设该主板能插6个USB设备
	private static int index=0;//表示插入到第几个位置
	//把设备插入到主板中的功能，接受IUSB类型的对象
	public static void pluginIn(IUSB usb){//多个USB设备都可插入
		if(index==usbs.length){
			System.out.println("USB插槽已满");
			return;
		}
		usbs[index]=usb;
		index++;
	}
	//取出主板中的每一个USB设备，并工作
	public static void doWork(){
		for(IUSB usb:usbs){
			if(usb!=null){
				usb.swapData();
			}
		}
	}
}
public class TestDemo{
	public static void main(String[] args){
		//安装鼠标对象
		MotherBoard.pluginIn(new Mouse());
		//安装打印机
		MotherBoard.pluginIn(new Print());
		//调用主板的工作行为
		MotherBoard.doWork();
	}
}
```
输出：
```
鼠标在移动
打印，嘟嘟嘟....
```

#### //演示内部类
```java
//外部类
class Outter{
	String name="外部类成员";
	static String name2="name2";
	public void ooxx(){
		//外部类调用内部类成员
		System.out.println(new Inner().age);//System.out.println(new.Outter().new Inner().age);
	}
	//实例内部类
	class Inner{
		int age=17;
		String name="内部类成员";
		public void test(){
			String name="内部类局部成员";
			System.out.println(name);
			System.out.println(this.name);
			//使用外部类成员
			System.out.println(Outter.this.name);
		}
	}
//静态内部类
	static class Inner1{//static修饰的类，并不意味着可以通过类名来调用该类的字段，只有当成员、方法被static修饰时，才可以直接通过类名来访问，static修饰类，而没有修饰字段时，依然需要室友对象来完成调用
		static int age=18;
		public void run(){
			System.out.println(name2);//静态内部类可以直接访问外部静态成员
			//通过创建对象访问外部非静态成员
			System.out.println(new Outter().name);//静态类中，不能直接访问非静态成员，如要访问则需通过创建类对象来调用
		}
	}
}
public class Test{
	public static void main(String[] args){
		//访问外部类成员
		Outter out=new Outter();
		System.out.println(out.name);
		//创建Inner的对象
		//创建实例内部类之前，必须承载外部类对象，通过外部类对象创建内部类对象（当存在内部类对象时，一定存在外部类对象）
		//接受内部类对象时，类型使用外部类.内部类
		Outter.Inner in=out.new Inner();//Outter.Inner in=new Outter().new Inner();
		in.test();
		//静态内部类，在创建内部类的实例时，不必创建外部类的实例
		Outter.Inner1 in1=new Outter.Inner1();
		in1.run();
		//静态内部类的静态成员通过完整的类名直接访问
		System.out.println(Outter.Inner1.age);
	}
}
```
输出：
```
外部类成员
内部类局部成员
内部类成员
外部类成员
name2
外部类成员
18
```

#### //枚举类型的引入
//需求：定义一个Employee(员工)，使用一个变量restday来表示他哪一天休息（一周内）
//定义一个星期几的长量类
 * 不使用枚举时，采用下列格式，其实在枚举的底层也是这种格式,不够优雅，很麻烦
 ```java
class Weekday{
	private Weekday(){};//私有化该类的构造器，防止外界创建行的对象，而增加该类的值
	public static final Weekday MONDAY=new Weekday();
	public static final Weekday TUESDAY=new Weekday();
	public static final Weekday WEDNESDAY=new Weekday();
	public static final Weekday THURSDAY=new Weekday();
	public static final Weekday FRIDAY=new Weekday();
	public static final Weekday SATURDAY=new Weekday();
	public static final Weekday SUNDAY=new Weekday();
}
```
*/
```java
enum Weekday{//枚举类前缀为：public static final 所以，常量名遵循常量名的编写规范
	MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;
}
class Employee{
	private Weekday restday;//一周中的哪天休息
	public Weekday getRestday(){
		return restday;
	}
	public void setRestday(Weekday restday){
		this.restday=restday;
	}
}
public class Demo{
	public static void main(String[] args){
		//创建一个员工对象，并设置他哪一天休息
		Employee e=new Employee();
		e.setRestday(Weekday.FRIDAY);//私有化了Weekday构造器，防止传入的数值出错
		Weekday restday=e.getRestday();
		if(restday==Weekday.SATURDAY||restday==Weekday.SUNDAY){
			System.out.println("周末休息");
		}
		else{
			System.out.println("周一到周五中的一天休息");
		}
	}
}
```


#### //单例设计模式
//在学过枚举之后，编写单例模式更加简单
```java
enum ArrayUtil{
	INSTANCE;//枚举中的元素为公共静态常量，变量名一般用大写
	//排序操作，外界不可直接获取
	public void sort(int[] arr){//该方法并没有涉及到排序的算法
	System.out.println("排序操作");
	}
}
class Demo{
	public static void main(String[] args){
		System.out.println(ArrayUtil.INSTANCE==ArrayUtil.INSTANCE);
		//需要做排序：现在假设在不同的类中
		ArrayUtil.INSTANCE.sort(null);
		//需要做排序：现在假设在另一个类中
		ArrayUtil.INSTANCE.sort(null);
		//需要做排序：现在假设在另一个类中
		ArrayUtil.INSTANCE.sort(null);
	}
}

//演示编译器的优化
package com.sayschj.demo;

public class Test {
	private static String getXx(){
		return "AB";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1="ABCD";
		String str2="A"+"B"+"C"+"D";
		String str3="AB"+"CD";
		String str4=new String("ABCD");
		String temp="AB";
		String str5=temp+"CD";
		String str6=getXx()+"CD";
		System.out.println(str1==str2);//true，编译器优化
		System.out.println(str1==str3);//true，编译器优化
		System.out.println(str1==str4);//false，变量/方法编译期并不能确定里面的值，不能进行编译器的优化
		System.out.println(str1==str5);//false
		System.out.println(str1==str6);//false
	}

}
```


```java
package com.sayschj.demo;

public class Test {
	//1):获取hello开头的文件名的后缀名	
	private static void test1() {
		//多文件名称组成的一个字符串
		String fileNames="abc.java;hello.will.txt;hello.java;hello.class";
		//以分号来分割字符串，获取每一个文件的名称和拓展名
		String[] names=fileNames.split(";");
		for(String name:names){
			//判断每一个字符是否以hello开头
			if(name.startsWith("hello")){
				//获取文件名的后缀名：最后一个.点的后半截
				System.out.println("hello开头的文件名称："+name);
				int index=name.lastIndexOf(".");
				System.out.println("最后一个点的索引："+index);
				//获取字符串
				String newName=name.substring(index);
				System.out.println("文件后缀名："+newName);
			}
		}
	}
	//把单词首字母大写
	private static void test2() {
		// TODO Auto-generated method stub
		String str="willabcdef";
		//获取第一个字符，并转为大写，此时获取的虽然是字符串中的一个字符，但字符类中没有.substring()方法无法获取指定位置的字符，所以采用String类型来接受
		String letter=str.substring(0, 1).toUpperCase();
		//解决字符串，从1到最后
		String word=str.substring(1);
		System.out.println("单词首字母大写："+letter+word);
	}
//判断字符串是否为空
	private static void test3() {
		// TODO Auto-generated method stub
		//.trim()方法去除字符串有效输入前的空格
		System.out.println(" abc d ".trim().length());
		String str1="";//为空
		String str2=" ";//为空
		String str3=null;//为空
		//--------------------
		String str="";
		//判断字符串不为空：既不是引用为null，也不是空字符串
		if(str!=null&&!"".equals(str.trim())){//"".equals(str)常量放在前面，避免空指针
			System.out.println("非空");
		}else{
			System.out.println("空");
		}
	}
	public static void main(String[] args) {
		test1();
		test2();
		test()3;
	}

}
```
输出：
```
hello开头的文件名称：hello.will.txt
最后一个点的索引：10
文件后缀名：.txt
hello开头的文件名称：hello.java
最后一个点的索引：5
文件后缀名：.java
hello开头的文件名称：hello.class
最后一个点的索引：5
文件后缀名：.class
单词首字母大写：Willabcdef
5
空
```

#### //创建自己的String工具JAR包
//用于判断字符串是否为空
```java
package com.sayschj.demo;

public class StringUtil {
	private StringUtil(){}//私有化构造器
	    /**
	     * 判断字符串非空
	     * 判断字符串不空：既不是引用为null，也不是空字符
		 */
		private static boolean hasLength(String str){
			/*代码一：代码不够优雅
			if(str!=null&&!"".equals(str.trim())){//"".equals(str)常量放在前面，避免空指针
				return true;
			}
			return false;//else{
						 //return false;
						 //}
			*/
			return str!=null&&!"".equals(str.trim());
		}
		/**
		 * 判断字符串为空
		 * 为null或者为空字符串
		 */
		private static boolean isBlank(String str) {
			//return str==null||"".equals(str.trim());
			return !hasLength(str);
		}	
	public static void main(String[] args) {
		System.out.println(hasLength(""));//false
		System.out.println(isBlank(""));//true
	}
}
```

Java中的length属性是针对数组说的,比如说你声明了一个数组,想知道这个数组的长度则用到了length这个属性。java中的length()方法是针对字符串String说的,如果想看这个字符串的长度则用到length()这个方法。

```java
package com.sayschj.demo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
//随机数的产生
public class RandomDemo {
	public static void main(String[] args) {
		mathRandom();
		random();
		threadLocalRandom();
	}
	private static void threadLocalRandom() {
		// 该方法是java7的新特性，最大的优点是可以用于产生一个范围内的随机数
		//使用ThreadLocalRandom();方法随机产生一个34到179之间的数
		ThreadLocalRandom radom4=ThreadLocalRandom.current();//ThreadLocalRandom中的构造器为缺省的只能在java.util包中才可以创建对象，其他方法都为非静态的，所以要通过current()方法来获取对象，类似于单例模式
		System.out.println(radom4.nextInt(34,179));
	}
	//使用random()方法随机产生一个34到179之间的数
	private static void random() {
		int random3=new Random().nextInt(145)+34;
		System.out.println(random3);
	}

	//使用Math.random()方法随机产生一个34到179之间的数
	private static void mathRandom() {
		int random1=34+(int)(Math.random()*(179-34));
		System.out.println(random1);
		//随机产生a~z之间的小写字母
		char radom2=(char)('a'+(Math.random()*('z'-'a'+1)));
		System.out.println(radom2);
	}
}
```

```java
package com.sayschj.demo;

import java.util.Random;
import java.util.UUID;

//运用随机数产生5位字符的验证码
public class RandomDemo {
	public static void main(String[] args) {
		//最简单的方式，利用UUID产生验证码，因为UUID使用16位数编码的，所以这种产生验证码的方式字母不会超过f，不够好
		String code=UUID.randomUUID().toString().substring(0,5);
		System.out.println(code);//fd1b3
		System.out.println("---------------------");
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		str+=str.toLowerCase();
		str+="0123456789";
		System.out.println(str);//ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
		char ch=str.charAt(new Random().nextInt(str.length()));//先随机生成上面的字符串中的一个字符,indedx必须在[0,str.length()-1]之内
		System.out.println(ch);//D
		//生成5位的验证码
		StringBuilder sb=new StringBuilder(5);//StringBuilder sb=new StringBuilder();默认会生成16位
		for (int i = 0; i <5; i++) {//循环5次，5次的字母相连
			char ch1=str.charAt(new Random().nextInt(str.length()));
			sb.append(ch1);
		}
		System.out.println(sb);//OYvcN
		//一般验证码忽略字母的大小写，使用equalsIgnoreCase()方法忽略大小写
	}
}
```

```java
package com.sayschj.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期的工具类
//完成Date和String之间的相互转换
class StringUtil1{
	/**
     * 判断字符串非空
     * 判断字符串不空：既不是引用为null，也不是空字符
	 */
	static boolean hasLength(String str){
		return str!=null&&!"".equals(str.trim());
	}
	/**
	 * 判断字符串为空
	 * 为null或者为空字符串
	 */
	static boolean isBlank(String str) {
		return !hasLength(str);
	}
}
public class DateUtil {
	public static final String DEFAULT_DATE_PATTERN="yyyy-MM-dd HH:mm:ss";//默认时间格式
	private DateUtil(){}
	//格式化操作：把Date类型--->String类型
	//因为别人并不一定知道，方法参数的输入格式，所以设置一个标准输入格式的重载方法
	public static String date2string(Date date){
		return date2string(date,null);//即使没有输入格式，也能使用方法默认格式输出
	}
	public static String date2string(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat();
		if(StringUtil1.isBlank(pattern)){//如果pattern为空,则pattern为默认格式
			pattern=DEFAULT_DATE_PATTERN;
		}
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}
	//解析操作：把String类型--->Date类型//没有在main方法中进行测试
	public static Date date2string(String date) throws ParseException{
		return date2string(date,null);
	}
	public static Date date2string(String date,String pattern) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat();
		if(StringUtil1.isBlank(pattern)){
			pattern=DEFAULT_DATE_PATTERN;
		}
		return sdf.parse(date);
	}
	public static void main(String[] args) throws Exception{
		System.out.println(date2string(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(date2string(new Date(), "yyyy/MM/dd HH:mm:ss"));
		System.out.println(date2string(new Date(), "yyyy/MM/dd"));
		System.out.println(date2string(new Date(), null));
		System.out.println(date2string(new Date()));
	}

}
```

```java
package com.sayschj.demo;

import java.util.Calendar;
import java.util.Date;

//判断条件：hireDate >= beginTime && hireDate <endTime
public class CalendarDemo {
	public static void main(String[] args) {
		//需求：查询最近一周。。。。。的信息，如何表示最近这一周
		Date current=new Date();
		//现在的电脑时间为：2017-4-18 20:34:30
		//把系统时间增加一天，作为日历的时间
		Calendar c=Calendar.getInstance();
		c.setTime(current);
		//把日增加一天，再把时，分，秒设置为0
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date endTime=c.getTime();
		c.add(Calendar.DAY_OF_MONTH, -7);
		Date beginTime=c.getTime();
		System.out.println("开始时间为："+beginTime.toLocaleString());//开始时间为：2017-4-12 0:00:00
		System.out.println("结束时间为："+endTime.toLocaleString());//结束时间：2017-4-19 0:00:00
	}
}
```

```java
package com.sayschj.demo;
//演示异常
public class HandleDemo {

	public static void main(String[] args) {
		System.out.println("begin....");
		try{
		int ret=10/0;//异常：ArithmeticException
		System.out.println("结果："+ret);//当int ret=10/0;出现异常时这个语句不再执行，若不存在异常，则会执行该语句
		}
		catch(ArithmeticException e){
			System.out.println("出异常了！");
			System.out.println("异常信息："+e.getMessage());
			System.out.println("异常的类型和信息："+e.toString());//相当于：System.out.println(e);
			e.printStackTrace();//以后当用这个方法，技能完成上面两个方法的任务，还能打印出异常处想的位置
		}
		System.out.println("end....");
	}

}
```
无异常输出：
```
begin....
结果：10
end....
异常输出：
begin....
出异常了！
异常信息：/ by zero
异常的类型和信息：java.lang.ArithmeticException: / by zero
java.lang.ArithmeticException: / by zero
	at com.sayschj.demo.HandleDemo.main(HandleDemo.java:8)
end....
```

```java
package com.sayschj.demo;

public class FinallyDemo {

	public static void main(String[] args) {
		test1();
		int ret1=test2();
		System.out.println(ret1);//100,执行finally中的return语句
		int ret2=test3();
		System.out.println(ret2);//1
	}
	//
	private static int test3() {
		int a=1;
		try{
			return a;
		}finally{
			++a;//2
		}
	}
	//如果有finally有return语句，永远返回finally中的结果，避免这种情况
	private static int test2() {
		try{
			return 1;
		}finally{
			return 100;
		}
	}

	private static void test1() {
		System.out.println("begin....");
		try{
		int ret=10/1;
		System.out.println("结果："+ret);
		}
		catch(ArithmeticException e){
			e.printStackTrace();
		}
		finally{
			System.out.println("关闭资源");
		}
		System.out.println("end....");
	}

}
```

```java
package com.sayschj.demo;
//业务逻辑异常
class LogicException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	//三个继承与父类的构造方法
	public LogicException(){
		super();
	}
	public LogicException(String message){
		super(message);
	}
	/**
	 * @param message表示当前异常的原因/信息
	 * @param cause当前异常的根本原因
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
public class UserNameDemo{
	private static String[] names={"will","lucy","lily"};
	public static void main(String[] args) {
		try{
			//可能出现异常的代码
			checkUsername("will");//最后的输出结果：给用户看：亲.账号：will已经注册了！
			//checkUsername("will1232");//最后的输出结果:注册成功！
			System.out.println("注册成功！");
		}catch(LogicException e){
			//处理异常
			String errorMsg=e.getMessage();
			System.out.println("给用户看："+errorMsg);
		}
	}
	//判断当前账号是否存在
	public static boolean checkUsername(String username) {
		for(String name:names){
			if(name.equals(username)){
				throw new LogicException("亲.账号："+name+"已经注册了！");
			}
		}
		return true;
	}
}
```

```java
package com.sayschj.demo;

import java.io.IOException;
//在java中如何开启一个进程：运行记事本程序
public class App10_1 {
	public static void main(String[] args) throws IOException{
		//方式1:使用Runtime类的exec()方法
		Runtime runtime=Runtime.getRuntime();//Runtime类为一个单例，需要通过一个静态方法来获取对象
		runtime.exec("notepad");
		//方式2：ProcessBulider的start()方法
		ProcessBuilder pb=new ProcessBuilder("notepad");
		pb.start();
	}
}
```

```java
package com.sayschj.demo;
//利用继承方式实现继承
//利用线程知识设计程序：边打游戏，边听音乐（用简单代码代替）
class MusicThread extends Thread{
	@Override
	public void run() {
		for (int i=0;i<50;i++) {
			System.out.println("听音乐"+i);
		}
	}
}
public class ExtendsThreadDemo {
	public static void main(String[] args) {//将打游戏的进程放在主方法（主线程）里
		for(int i=0;i<50;i++){
			System.out.println("打游戏"+i);
			if(i==3){//当i=3时，启动听音乐线程，注意：在循环里面启动线程要有一定的条件约束，若果没有约束，该线程将会重复创建多次
				MusicThread mt=new MusicThread();
				mt.start();//此时启动MusicThread类中的线程
			}
		}
	}
}
```

```java
package com.google.app;

//使用匿名内部类创建线程
public class RunnableDemo {
	public static void main(String[] args) {//将打游戏的进程放在主方法（主线程）里
		for(int i=0;i<50;i++){
			System.out.println("打游戏"+i);
			if(i==3){//当i=3时，启动听音乐线程，注意：在循环里面启动线程要有一定的条件约束，若果没有约束，该线程将会重复创建多次
				new Thread(){//使用类的形式，继承于Thread的内部类，创建继承的累不累的时候花括号“{”写在圆括号“）”外
					public void run(){
						for(int i=0;i<50;i++){
							System.out.println("听音乐"+i);
						}
					}
				}.start();
			}
		}
	}
	public static  void test(){//经常使用这种接口的方式
		for(int i=0;i<50;i++){
			System.out.println("打游戏"+i);
			if(i==3){
				new Thread(new Runnable(){//使用接口的形式，实现Runnable接口的内部类，创建继承的类部类的时候花括号“{”写在圆括号“）”内
					public void run(){
						for(int i=0;i<50;i++){
							System.out.println("听音乐"+i);
						}
					}
				}).start();
			}
		}
	}
}
```