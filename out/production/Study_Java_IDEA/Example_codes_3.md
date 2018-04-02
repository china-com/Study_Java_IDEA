```java
package com.sayschj.demo;
//模拟三个人吃苹果
//使用Synchronized修饰代码块
class Apple implements Runnable {
	private int num = 50;// 苹果总数

	public void run() {
		//1）：Synchronized（this）{//哪个对象先进去即可执行完for循环，吃完所有苹果
		for (int i = 0; i < 50; i++) {
			//同步代码块
			Synchronized（this）{
			if (num > 0) {
			//2）：Synchronized（this）{
				System.out.println(Thread.currentThread().getName() + "吃了标号为" + num + "的苹果");//拿苹果
				try {
					Thread.sleep(10);// 模拟网络延迟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				num--;
			}
			}
		//}
			
		}
	//}
	}
}

public class SynchronizedBlockDemo {
	public static void main(String[] args) {
		Apple a = new Apple();
		Thread t1 = new Thread(a, "A同志");
		Thread t2 = new Thread(a, "B同志");
		Thread t3 = new Thread(a, "C同志");
		t1.start();
		t2.start();
		t3.start();
	}
}
```
* 不加通过机制，打印的结果三个人可能拿到同一个苹果，最后的苹果数可能为0或负数，显然不符合实际情况
* 将Synchronized放在1)处，则会出现一个线程对象把所有苹果吃完的情况
* 将Synchronized放在2)处，则会出现最后的苹果数可能为0或负数，在苹果数减到0前，有对象已经通过了if判断语句，在同步代码块外等待进入。

```java
package com.sayschj.demo;
//同步方法：解决吃苹果问题
class Apple1 implements Runnable {
	private int num = 50;// 苹果总数

	public void run() {
		for (int i = 0; i < 50; i++) {
			eat();
		}
	}
	synchronized private void eat(){
		if (num > 0) {
			System.out.println(Thread.currentThread().getName() + "吃了标号为" + num + "的苹果");//拿苹果
			try {
				Thread.sleep(10);// 模拟网络延迟
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			num--;
		}
	}
}
public class SynchronizedMethodDemo {
	public static void main(String[] args) {
		Apple1 a = new Apple1();
		new Thread(a, "A同志").start();
		new Thread(a, "B同志").start();
		new Thread(a, "C同志").start();
	}
}
```

```java
package com.sayschj.join_thread;

//联合线程
class JoinThread extends Thread {
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("JoinThread:" + i);
		}
	}
}

public class JoinThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("begin.....");
		JoinThread jt=new JoinThread();//创建线程join对象
		for (int i = 0; i < 30; i++) {
			System.out.println("mian:"+i);
			if(i==2){
				//i=2时会启动join线程
				jt.start();
			}
			if (i==20) {
				jt.join();//i=20时，强制执行该线程
			}
		}
	}
}
```
输出：
```
begin.....
mian:0
mian:1
mian:2
mian:3
mian:4
mian:5
mian:6
mian:7
mian:8
mian:9
mian:10
mian:11
mian:12
mian:13
mian:14
JoinThread:0
mian:15
JoinThread:1
mian:16
JoinThread:2
mian:17
JoinThread:3
mian:18
JoinThread:4
mian:19
JoinThread:5
mian:20
JoinThread:6
JoinThread:7
JoinThread:8
JoinThread:9
JoinThread:10
JoinThread:11
JoinThread:12
JoinThread:13
JoinThread:14
JoinThread:15
JoinThread:16
JoinThread:17
JoinThread:18
JoinThread:19
mian:21
mian:22
mian:23
mian:24
mian:25
mian:26
mian:27
mian:28
mian:29
```

```java
package com.sayschj.join_thread;

import java.util.Arrays;

public class BasketballPlayer {
	//存储场上球员的球衣号码
	private static Integer[] players=null;
	//场上球员的个数
	private static int size=0;
//	1):初始容量为5的线性列表，准备用来储存场上的5个球衣号码
	public static void init(int initial){
		if(initial<0){//判断输入是否违法
			throw new IllegalArgumentException("容量不能为零");
		}
		players=new Integer[initial];
	}
//	2):安排5个球员上场：[11,22,33,44,55]
	public static void add(Integer playerNum){
		//判断和扩容
		if(size==players.length){
			//为了防止添加多个球员时出现异常，数组的扩容，新建一个数组（数组长度为原数组长苏的2倍），然后将原数组中的元素复制到新数组中
			Integer[] temp=Arrays.copyOf(players, players.length*2);
			players=temp;
		}
		players[size]=playerNum;
		size++;
	}
//	3):查询指定位置的球员的球衣号码是多少
	private static Integer get(int index) {
		if(index<0||index>=size){
			throw new IllegalArgumentException("索引越界");
		}
		return players[index];
	}
//  4):根据球衣号码查询该球员在场上的索引位置
	private static int getIndexByPlayerNum(int playerNum) {
		for (int index = 0; index < size; index++) {
			if(players[index].equals(playerNum)){
				return index;
			}
		}
		return -1;
	}
//	5):替换场上索引位置为2的球员，提荒之后该位置的球衣号码为333
	private static void set(int index, Integer newPlayerNum) {
		if(index<0||index>=size){
			throw new IllegalArgumentException("索引越界");
		}
		players[index]=newPlayerNum;
	}
//	6):替换球衣号码为44的球员，替换之后为888
	private static void update(Integer oldPlayerNum, Integer newPlayerNum) {
		int index=getIndexByPlayerNum(oldPlayerNum);
		set(index, newPlayerNum);
	}
//	7):把场上索引位置为2的球衣罚下场
	private static void delete(int index) {
		if(index<0||index>=size){
			throw new IllegalArgumentException("索引越界");
		}
		for (int i = index; i < size-1; i++) {
			//index+1位置移动到index位置
			players[i]=players[i+1];
		}
		//把最后一个位置设置为null,释放这个位置所占的内存
		players[size-1]=null;
		//把场上球员总数减1
		size--;
	}
//	8):按照球员在场上的位置，打印出球衣号码，打印风格：[11,22,33,44,55]
	public static void print(){
		if(players==null){
			System.out.println("null");
			return;
		}
		if(size==0){
			System.out.println("[]");
			return;
		}
		StringBuilder sb=new StringBuilder(size*3+1);//每个字符一个字节总共16个字节
		sb.append("[");
		for (int index = 0; index < size; index++) {
			sb.append(players[index]);
			if(index!=size-1){
				sb.append(",");
			}else{
				sb.append("]");
			}
		}
		System.out.println(sb);
	}
	public static void main(String[] args) {
		init(5);
		add(11);
		add(22);
		add(33);
		add(44);
		add(55);
		print();
		//查询索引位置为2的球衣号码是：33
		Integer num=get(2);
		System.out.println(num);
		//44球衣号的球员在场上的索引位置是：3
		int index=getIndexByPlayerNum(44);
		System.out.println(index);
		//把索引为2位置的球员替换为333
		set(2,333);
		print();
		update(44,888);
		print();
		delete(2);//删除索引为2的球员
		print();
	}
}
```

输出：
```
[11,22,33,44,55]
33
3
[11,22,333,44,55]
[11,22,333,888,55]
[11,22,888,55]
```

```java
package com.sayschj.linked;

public class MyLinkedList {
	private Node first;//链表的第一个结点
	private Node last;//链表的最后一个结点
	private int size;//结点的数量
	//链表中的每一个结点
	class Node{
		Node prev;//上一个结点的对象
		Node next;//下一个结点对象
		Object ele;//当前结点中存储的数据
		public Node(Object ele) {
			this.ele = ele;
		}
	}
	public void remove(Object ele) {
		//找到被删除的节点
		Node current=this.first;
		for(int i=0;i<size;i++){
			if(!current.ele.equals(ele)){
				if(current.next==null){
					return;
				}else{
					current=current.next;//一直向后走
				}
			}
		}
		//删除结点
		if(current==first){
			this.first=current.next;
			//current.next.prev=null;
			this.first.prev=null;
		}else if(current==last){
			this.last=current.prev;
			this.last.next=null;
		}else{
			//把删除当前结点的下一个结点作为删除结点上一个节点的next
			current.prev.next=current.next;
			//把删除结点的上一个节点作为删除结点下一个结点的prev
			current.next.prev=current.prev;
		}
		size--;//一定要足以减1
	}
	public void addFirst(Object ele) {
		Node node=new Node(ele);//需要保存的节点对象
		if (size==0) {
			this.first=node;
			this.last=node;
		}else{
			//把之前第一个作为新增节点的下一个结点
			node.next=this.first;
			//把新增节点作为之前第一个节点的上一个节点的上一个节点
			//node.next.prev=node;
			this.first.prev=node;
			//把新增节点作为第一个节点
			this.first=node;
		}
		size++;//注意加1
	}
	public void addLast(Object ele) {
		Node node=new Node(ele);//需要保存的节点对象
		if (size==0) {
			this.first=node;
			this.last=node;
		}else{
			//把新增的节点作为之前最后一个的下一个结点
			this.last.next=node;
			//把之前最后一个节点作为新增节点的上一个节点
			node.prev=this.last;
			//把新增节点作为最后一个节点
			this.last=node;
		}
		size++;
	}
	public String toString() {
		if(size==0){
			return "[]";
		}
		StringBuilder sb=new StringBuilder(size*2+1);
		Node current=this.first;//第一个节点
		sb.append("[");
		for(int i=0;i<size;i++){
			sb.append(current.ele);
			if(i!=size-1){
				sb.append(",");
			}else{
				sb.append("]");
			}
			current=current.next;//获取自己的下一个结点
		}
		return sb.toString();
	}
}
//测试类
package com.sayschj.linked;

import java.util.MissingFormatArgumentException;

public class MyLinkedListDemo {
	public static void main(String[] args) {
		MyLinkedList list=new MyLinkedList();
		list.addLast("B");
		list.addLast("C");
		list.addLast("D");
		list.addFirst("A");
		list.addLast("E");
		System.out.println(list);
		list.remove("D");
		System.out.println(list);
	}
}
```
输出：
```
[A,B,C,D,E]
[A,B,C,E]
```

```java
package com.sayschj.diedai;

import java.util.ArrayList;
import java.util.Iterator;
//四种迭代操作
public class IterationDemo {
	public static void main(String[] args) {
		ArrayList list=new ArrayList();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		//方式1：for循环
		for(int index = 0;index<list.size();index++){
			System.out.println(list.get(index));
		}
		System.out.println("-----------------------");
		//方式2：foreach循环
		for (Object ele : list) {
			System.out.println(ele);
		}
		System.out.println("-----------------------");
		//方式3：使用while循环操作迭代器Iterator
		Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("-----------------------");
		//方式4：使用for循环操作迭代器Iterator
		for (Iterator it2=list.iterator(); it2.hasNext();) {
			System.out.println(it2.next());
		}
	}
}
```

```java
package com.sayschj.generic;
//引入泛型
public class GenericDemo <E>{//E为未知类型，由类的调用者来确定类型
	private E x;
	private E Y;
	public E getX() {
		return x;
	}
	public void setX(E x) {
		this.x = x;
	}
	public E getY() {
		return Y;
	}
	public void setY(E y) {
		Y = y;
	}
	public static void main(String[] args) {
		GenericDemo<Integer> gd1=new GenericDemo<>();//此时E的类型为Integer
		gd1.setX(12);
		System.out.println(gd1.getX());
		GenericDemo<Double> gd2=new GenericDemo<>();
		gd2.setY(5.1);
		System.out.println(gd2.getY());
	}
}
```

```java
package com.sayschj.treeset;

import java.util.Set;
import java.util.Tre-eSet;
//TreeSet的自然排序
class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	//编写比较规则
	public int compareTo(Person o) {
		if(this.age>o.age){
			return 1;
		}else if(this.age<o.age){
			return -1;
		}
		return 0;
	}
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
public class TreeSetDemo {
	public static void main(String[] args) {
		//按照person对象的年龄做自然排序
		Set<Person> set=new TreeSet<>();
		set.add(new Person("李门吹雪",98));
		set.add(new Person("倩儿",32));
		set.add(new Person("孙三儿",18));
		set.add(new Person("赵",52));
		System.out.println(set);
	}
}
```
输出：
```
[Person [name=孙三儿, age=18], Person [name=倩儿, age=32], Person [name=赵, age=52], Person [name=李门吹雪, age=98]]
```

```java
package com.sayschj.treeset;
//TreeSet的定制排序
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Person{
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
//名字长度比较器
class NamelengthComparator implements Comparator<Person>{

	public int compare(Person o1, Person o2) {
		if(o1.name.length()>o2.name.length()){
			return 1;
		}else if(o1.name.length()<o2.name.length()){
			return -1;
		}
		return 0;
	}
	
}
public class TreeSetDemo {
	public static void main(String[] args) {
		//按照person对象的名字长短来做定制排序
		Set<Person> set=new TreeSet<>(new NamelengthComparator());
		set.add(new Person("李门吹雪",98));
		set.add(new Person("倩儿",32));
		set.add(new Person("孙三儿",18));
		set.add(new Person("赵",52));
		System.out.println(set);
	}
}
```
输出：
```
[Person [name=赵, age=52], Person [name=倩儿, age=32], Person [name=孙三儿, age=18], Person [name=李门吹雪, age=98]]
```

```java
package com.sayschj.study;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value3");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		map.put("key5", "value6");
		System.out.println(map);//{key1=value1, key2=value3, key5=value6, key3=value3, key4=value4}
		System.out.println(map.containsKey("key3"));//true
		System.out.println(map.containsValue("value3"));//true
		System.out.println(map.get("key3"));//value3
		map.remove("key4");
		System.out.println(map);//{key1=value1, key2=value3, key5=value6, key3=value3}
		//获取Map中所有key所组成的集合（key是不能重复的，set）
		Set<String> keys=map.keySet();
		for (String key : keys) {
			System.out.println(key+"->"+map.get(key));
		}
		System.out.println("---------------");
		//获取Map中所有value所组成的集合（value可以重复，类似于list）
		Collection<Object> value=map.values();
		for (Object val : value) {
			System.out.println(val);
		}
		//获取Map中所有的Entry(key-values)
		Set<Map.Entry<String, Object>> entrys=map.entrySet();
		for (Map.Entry<String, Object> entry : entrys) {
			System.out.println(entry);
		}
	}
}
```
输出：
```
{key1=value1, key2=value3, key5=value6, key3=value3, key4=value4}
true
true
value3
{key1=value1, key2=value3, key5=value6, key3=value3}
key1->value1
key2->value3
key5->value6
key3->value3
---------------
value1
value3
value6
value3
key1=value1
key2=value3
key5=value6
key3=value3
```

```java
package com.sayschj.study;

import java.util.Map;
import java.util.TreeMap;

//需求：计算一个字符串中，每一个字符出现的次数
public class MapDemo {
	public static void main(String[] args) {
		String str="asdfghjklbfasdgjflksaljsdhsajbfbsjfs";
		//把字符串转换为char数组（字符串本质就是char[]）
		char[] arr=str.toCharArray();
		//key:存储字符名，value：存储出现次数
		Map<Character,Integer> map=new TreeMap<>();//按自然顺序添加{a=4, b=3, d=3, f=5, g=2, h=2, j=5, k=2, l=3, s=7}
		//Map<Character,Integer> map=new LinkedHashMap<>();//记录了元素的添加顺序{a=4, s=7, d=3, f=5, g=2, h=2, j=5, k=2, l=3, b=3}
		//Map<Character,Integer> map=new HashMap<>();//{a=4, b=3, s=7, d=3, f=5, g=2, h=2, j=5, k=2, l=3}
		//循环得到每一个字符
		for (char ch : arr) {
			//判断当前字符是否已经在Map中的key中存在
			if(map.containsKey(ch)){
				//当前Map的key包含该字符，此时取出该value值递增1，在村放进去
				Integer old=map.get(ch);//得到该字符映射对应的value值
				map.put(ch, old+1);//把key和value存放到map中
			}else{
				//当前Map的key不包含该字符串，把该字符串存储到Map中，设置value为1
				map.put(ch, 1);
			}
		}
		System.out.println(map);
	}
}
```

```java
package com.sayschj.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDemo {
	public static void main(String[] args) {
		// 使用set集合存储一个班级学生的名称
		Set<String> names1 = new HashSet<>();
		names1.add("赵一");
		names1.add("倩儿");
		names1.add("孙三");

		Set<String> names2 = new HashSet<>();
		names2.add("西门吹雪");
		names2.add("叶孤城");
		names2.add("陆小凤");
		// 使用Map来存储多个班级的学生
		// 学院A
		Map<String, Set<String>> classMap1 = new HashMap<>();
		classMap1.put("学前班", names1);
		classMap1.put("大神班", names2);
		// 学院B
		Map<String, Set<String>> classMap2 = new HashMap<>();
		classMap2.put("学前班", names1);
		classMap2.put("大神班", names2);
		// 使用List来存储所有学院的学生姓名
		List<Map<String, Set<String>>> school = new ArrayList<>();
		school.add(classMap1);
		school.add(classMap2);
		System.out.println(school);
	}
}
```
输出：
```
[{学前班=[赵一, 孙三, 倩儿], 大神班=[西门吹雪, 陆小凤, 叶孤城]}, {学前班=[赵一, 孙三, 倩儿], 大神班=[西门吹雪, 陆小凤, 叶孤城]}]
```

```java
package com.sayschj.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo2 {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?characterEncoding=utf8&useSSL=true","root","123456");
			//最新版的MySQL需要在driver后加?characterEncoding=utf8&useSSL=true
			System.out.println(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```
>输出：com.mysql.jdbc.JDBC4Connection@133ddba


```java
package study;

import java.io.File;
//需求：列出指定目录下的所有所有文件，包括子文件夹中的所有文件
public class Filedemo {
	public static void main(String[] args) {
		File dir=new File("E:/GitHub/Java");
		listAllFiles(dir);
	}
//列出所有循环
	private static void listAllFiles(File f) {
		//System.out.println(f);
		/*
		 传统方法，需要很多成循环
		//第一级子文件
		File[] fs=f.listFiles();
		for(File file1:fs) {
			System.out.println(file1);
			//第二级
			if(file1.isDirectory()) {
				File[] f2=file1.listFiles();
				for (File file2 : f2) {
					System.out.println(file2);
					//第三级
					if(file2.isDirectory()) {
						File[] f3=file2.listFiles();
						for (File file3 : f3) {
							System.out.println(file3);
							...
						}
					}
				}
			}
		}
		 */		
		//采用递归调用的方法
		File[] f1=f.listFiles();
		for (File file : f1) {
			//打印文件和目录
			System.out.println(file);
			//如果是目录则继续递归
			if(file.isDirectory()) {
				listAllFiles(file);
			}
		}
	}
}
```
