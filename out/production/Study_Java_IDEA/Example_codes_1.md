#### //使用while循环语句计算1+1/2!+1/3!....+1/20!之和：
```java
public class Example_02 {	
    public static void main(String args[])
    {			
      double sum = 0,a = 1;  //从计算内容可知sum和a都为double型
      int i = 1;
      while(i <= 20)
      {
    	  sum = sum+a;
    	  i = i+1;
    	  a = a*(1.0/i);//注意使用1.0，如使用1则输出的结果为1。
    }
      System.out.println(sum);//输出为：1.7182818284590455
    }KV
}
```
#### //查看现在的时间：

```java
import java.util.*;

public class Eval { // 新建类
	public static void main(String[] args) { // 主方法
		Date date = new Date(); // 创建Date对象date
		String year = String.format("%tY", date); // 将date进行格式化
		String month = String.format("%tB", date);
		String day = String.format("%td", date);
		System.out.println("今年是：" + year + "年"); // 输出信息
		System.out.println("现在是：" + month);
		System.out.println("今天是：" + day + "号");
	}
}
```
#### //在字符a后面追加1~10这10个数字：

```java
public class Eval { // 新建类
	public static void main(String[] args) { // 主方法
		StringBuilder builder = new StringBuilder("a");
		for (int i = 1; i <= 10; i++) {
			builder.append(i);//builder=builder.append(i);
		}
		System.out.println(builder.toString());
	}
}
```
 #### //九九乘法表
```
1*1=1	
1*2=2	2*2=4	
1*3=3	2*3=6	3*3=9	
1*4=4	2*4=8	3*4=12	4*4=16	
1*5=5	2*5=10	3*5=15	4*5=20	5*5=25	
1*6=6	2*6=12	3*6=18	4*6=24	5*6=30	6*6=36	
1*7=7	2*7=14	3*7=21	4*7=28	5*7=35	6*7=42	7*7=49	
1*8=8	2*8=16	3*8=24	4*8=32	5*8=40	6*8=48	7*8=56	8*8=64	
1*9=9	2*9=18	3*9=27	4*9=36	5*9=45	6*9=54	7*9=63	8*9=72	9*9=81
```
代码如下：
```java
public class SumDemo{
	public static void main(String[] args){
		/*第一种方式：最原始的方法
		System.out.println("1*1=1");
		System.out.println("1*2=2 2*2=4");
		System.out.println("1*3=3 2*3=6 3*3=9");
		.....
		*/
		//规律：第二个乘数等于行数，第一个乘数《=第二个乘数
		//简化,第二种方法
		/*
		 int Line=1;
		 
		for(int i=1;i<=Line;i++){//i表示第一个乘数
			System.out.print(i+"*"+Line+"="+(i*Line)+"\t");
		}
		System.out.println();//换行
		Line++;
		for(int i=1;i<=Line;i++){//i表示第一个乘数
			System.out.print(i+"*"+Line+"="+(i*Line)+"\t");
		}
		System.out.println();//换行
		Line++;
		for(int i=1;i<=Line;i++){//i表示第一个乘数
			System.out.print(i+"*"+Line+"="+(i*Line)+"\t");
		}
		System.out.println();//换行
		Line++;
		......
		*/
		//在第二种方法的里面找规律，化简，最优算法
		for(int Line=1;Line<=9;Line++){
			for(int i=1;i<=Line;i++){
				System.out.print(i+"*"+Line+"="+(i*Line)+"\t");
			}
			System.out.println();
		}
	}
}
```
#### //打印三角形：
```
*
**
***
****
*****
```
代码：

```java
public class SumDemo{
	public static void main(String[] args){
		/*第一种类型
		System.out.println("*");
		System.out.println("**");
		System.out.println("***");
		System.out.println("****");
		System.out.println("*****");
		 */
		/*第二种类型
		for(int i=1;i<=1;i++){
			System.out.print("*");
		}
		System.out.println();
		for(int i=1;i<=2;i++){
			System.out.print("*");
		}
		System.out.println();
		for(int i=1;i<=3;i++){
			System.out.print("*");
		}
		System.out.println();
		for(int i=1;i<=4;i++){
			System.out.print("*");
		}
		System.out.println();
		for(int i=1;i<=5;i++){
			System.out.print("*");
		}
		System.out.println();
		*/
		/*第三种方式
		int Line=1;//第几行
		for(int i=1;i<=Line;i++){
			System.out.print("*");
		}
		System.out.println();
		Line++;
		for(int i=1;i<=Line;i++){
			System.out.print("*");
		}
		System.out.println();
		Line++;
		for(int i=1;i<=Line;i++){
			System.out.print("*");
		}
		System.out.println();
		Line++;
		....
		*/
		//第三种方式
		for(int Line=1;Line<=5;Line++){
			for(int i=1;i<=Line;i++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
```
#### //break：终止当前循环
```java
public class SumDemo{
	public static void main(String[] args){
	//从1输出到10
		for(int i=1;i<=10;i++){
			//如果输出7，则终止循环
			if(i==7){
				break;
			}
			System.out.println(i);
		}
		System.out.println("ending.....");
		System.out.println("-----------");
		//实例：输出100以内所有的数字
		//3 6 9 12 .....
		//1：循环输出100以内的所有数字
		//2:判断哪些数字是3的倍数
		//3：若是3的倍数，则使用计数器加1
		//4：当计数器为5时，跳出循环
		int result=0;//计数器
		for(int i=1;i<=100;i++){
			//int result=1;//若计数器定义在这里，，每一次循环后result又被重新定义为1。
			if(i%3==0){
				//int result=1;
				System.out.println(i);
				result++;
			}
			if(result==5){
				break;//终止当前循环
				//System.out.println();//报错，break后循环终止，后面的内容不会再执行
			}
		}
	}
}
```
#### //continue，终止当前循环，进入下一次循环

```java
public class Demo{
	public static void main(String[] args){
	//从1输出到10
		for(int i=1;i<=10;i++){
			//不输出不吉利的数字4
			if(i==4){
				continue;//终止当前循环，进入下一次循环
			}
			System.out.println(i);
		}
		System.out.println("ending.....");
		System.out.println("-----------");
		//实例：输出100至200之间不能被3整除的数
		//说人话：能被3整除的数，跳过输出
		for(int i=100;i<=200;i++){
		
			if(i%3==0){
				continue;//终止当前循环，进入下一次循环
				//System.out.println("....");//报错，
			}
			System.out.println(i);
		}
	}
}
```
#### //return：表示结束循环所在的方法，方法结束了，循环结构自然也就结束了。

```java
public class Demo{
	public static void main(String[] args){
	//从1输出到10
		for(int i=1;i<=10;i++){
			//到7停止
			if(i==7){
				break;//终止循环
			}
			System.out.println(i);
		}
		System.out.println("ending.....");
		System.out.println("-----------");
		for(int i=1;i<=10;i++){
			if(i==7){
				return;//终止所在的方法，后面的所有内容不会再输出
			}
			System.out.println(i);
		}
		System.out.println("ending.....");
	}
}
```
#### //控制外层循环：此时就得使用标签了，标签就是给某个循环起的别名，不过该别名的符合标识符规范。若要控制外层循环，就在break和continue后面跟上循环的别名

```java
public class Demo{
	public static void main(String[] args){
		C1:for(int Line=1;Line<=9;Line++){
			for(int i=1;i<=Line;i++){
				//输出前四行
				if(Line==5){
				break C1;
				//return;//结束整个方法，可完成需求
				//break;//输出的结果如下：
				/*
		1*1=1	
		1*2=2	2*2=4	
		1*3=3	2*3=6	3*3=9	
		1*4=4	2*4=8	3*4=12	4*4=16	
					//只是缺了第五行，break停止了所在循环（内循环），外循环还在继续。			1*6=6	2*6=12	3*6=18	4*6=24	5*6=30	6*6=36	
		1*7=7	2*7=14	3*7=21	4*7=28	5*7=35	6*7=42	7*7=49	
		1*8=8	2*8=16	3*8=24	4*8=32	5*8=40	6*8=48	7*8=56	8*8=64	
		1*9=9	2*9=18	3*9=27	4*9=36	5*9=45	6*9=54	7*9=63	8*9=72	9*9=81	
				*/
				}
				System.out.print(i+"*"+Line+"="+(i+Line)+"\t");
			}
			System.out.println();
		}
	}
}
//输出的结果：
1*1=1	
1*2=2	2*2=4	
1*3=3	2*3=6	3*3=9	
1*4=4	2*4=8	3*4=12	4*4=16
```
#### //方法的定义和调用
```java
public class Demo { 
	//无参无返回。
	//需求：打印一个方法，专门打印-------------
	static void p(){
		System.out.println("----------");
	}
	//有参无返回
	//需求：打印任意值
	static void pValue(String val){
		System.out.println(val);
	}
	//无参又返回
	//对于有返回的方法，调用者应该定义一个变量去接受
	//需求：返回我的年龄
	static int getAge(){
		return 17;
	}
	//有参有返回
	//求两个数之和getSum
	static int getSum(int a,int b){
		//System.out.println(a+","+b);//显示此时的a=3,b=4.
		return a+b;
	}
	public static void main(String[] args) {
		Demo.p();//调用
		
		Demo.pValue("hello");//传递hello字符串
		Demo.pValue("你好");
		
		//定义变量，接受getAge方法返回的结果
		int age=Demo.getAge();
		System.out.println(age);
		
		//定义变量sum接受getSum之后的结果,并赋值（实参）
		int sum=Demo.getSum(3,4);
		System.out.println(sum);
	}
}
```
//方法的重载
```java
public class Demo {
	/*
	 * 不用方法的重载，则需要给不同类型而操作相同的方法起不同的名称
	//需求1：在同一个类中，分别求两个整数、小数之和
	//求两个整数之和
	static int getSum1(int x,int y){
		return x+y;
	}
	//求两个小数之和
	static double getSum2(double x,double y){
		return x+y;
	}
	//需求2：在同一个类中分别定义打印string,int,boolean类型的方法
	//打印String
	static void pString(String data){
		System.out.println(data);
	}
	//打印int
	static void pInt(int data){
		System.out.println(data);
	}
	//打印boolean
	static void pBoolean(boolean data){
		System.out.println(data);
	}
	*/
	//相似的操作方法名可以相同根据形参的不同进行选择、判断
	//打印操作
	//求两个整数之和
		static int getSum(int x,int y){
			return x+y;
		}
		//求两个小数之和
		static double getSum(double x,double y){
			return x+y;
		}
	    //需求2
		//打印String
		static void p(String data){
			System.out.println(data);
		}
		//打印int
		static void p(int data){
			System.out.println(data);
		}
		//打印boolean
		static void p(boolean data){
			System.out.println(data);
		}
	public static void main(String[] args) {
		int sum=Demo.getSum(1, 2);
		System.out.println(sum);
		double sum1=Demo.getSum(1.0, 2.0);
		System.out.println(sum1);
		.......
	}
}
```
#### //递归操作

```java
public class Demo {
	//斐波那契数列，其通项公式为：fn(0)=0,fn(1)=1,fn(n)=fn(n-1)+f(n-2）（n>=3,n为正整数）
	//要知道某个数必须要知道他前面的两个数，即调用自己前两位数
	static int fn(int n){
		if(n==0){
			return 0;
		}
		else if(n==1){
			return 1;
		}
		else{//n不等于0和1
			return fn(n-1)+fn(n-2);
		}
	}
	public static void main(String[] args) {
		int ret=Demo.fn(5);
		System.out.println(ret);
	}
}
```
#### //输出字符串中的元素
```java
public class Demo {
	static void printArray(String[] arr){
		if(arr==null){
			System.out.println("null");
			return;//结束方法，若不加return，则输出"null"后，还会继续向下执行，输出下面的内容。
		}
		String ret="[";
		for(int index=0;index<arr.length;index++){
			ret=ret+arr[index];
			if(index!=arr.length-1){
				ret=ret+",";
			}
		}
		ret=ret+"]";
		System.out.println(ret);
	}
	public static void main(String[] args) {
		String[] arr={"a","b","c","d","e"};
		Demo.printArray(arr);
		Demo.printArray(null);//不带任何内容
	}
}
```
输出：
```
[a,b,c,d,e]
null
```
#### //字符串中的元素逆序输出
```java
public class Demo {
	static String[] Array(String[] oldArr){
		String[] newArr=new String[5];//创建一个新的数组，用来接受倒序数组		
                for(int index=oldArr.length-1;index>=0;index--){
			//index=4     0
			//index=3     1   //两者相加为oldArr.length-1
			newArr[oldArr.length-1-index]=oldArr[index];
		}
		return newArr;
	}
	static void printArray(String[] arr){
		if(arr==null){
			System.out.println("null");
			return;//结束方法，若不加return，则输出"null"后，还会继续向下执行，输出下面的内容。
		}
		String ret="[";
		for(int index=0;index<arr.length;index++){
			ret=ret+arr[index];
			if(index!=arr.length-1){
				ret=ret+",";
			}
		}
		ret=ret+"]";
		System.out.println(ret);
	}
	
	public static void main(String[] args) {
		String[] oldArr={"a","b","c","d","e"};
		Demo.printArray(oldArr);
		String[] arr=Demo.Array(oldArr);
		Demo.printArray(arr);//Array方法返回的值再调用printArray方法输出
    }
}
输出：
[a,b,c,d,e]
[e,d,c,b,a]
```
#### //元素在字符串中出现时的索引（第一次/最后一次）
```java
public class Demo {
	/*
	 查询key在arr数组中第一次出现的位置
	 参数：
	 arr：从哪一个数组中去查询
	 key：当前需要查询的元素
	 返回：如果key存在于arr数组中。则返回第一次出现的索引
	 key：不存在于arr中返回-1.
	 */
		static int indexOf(int[] arr,int key){
			for(int index=0;index<arr.length;index++){
				if(arr[index]==key){
					return index;
				}
			}
		return -1;
	}
	//判断key在arr数组最后出现的索引
	static int lastIndexOf(int[] arr,int key){
		for(int index=arr.length-1;index>=0;index--){
			if(arr[index]==key){
				return index;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] arr={10,20,30,10,12,20,-20,10};
		int begainIndex=Demo.indexOf(arr,10);
		System.out.println(begainIndex);
		int endIndex=Demo.lastIndexOf(arr, 10);
		System.out.println(endIndex);//10在数组中最后出现时的索引
	}
}
输出：
0
7
```
#### //需求1：计算所有商品的总价，需求2：逢年过节打8折
```java
/*
 *java5中的两个新特性；1）：foreach循环 2)方法的可变参数
 */
/*使用数组方法
  public class Demo {
	public static void main(String[] args) {
		double[] arr=new double[]{10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0};
		double sum=Demo.getSum(arr);
		System.out.println(sum);
	}
	static double getSum(double[] arr){
		double sum=0.0;//double型变量
		for(double price:arr){
			sum+=price;
		}
		return sum;
	}
}
*/
public class Demo {
	public static void main(String[] args) {
		//double[] arr=new double[]{10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0};
		//设置8折折扣，此时应将折扣值放在最前面，否者无法判断数据到底是数组的还是折扣的，避免参数的歧义
		double sum=Demo.getSum(0.8,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0);//采用可变参数，在底层其实质是数组
		System.out.println(sum);
	}
	static double getSum(double cutoff,double ... arr){//cutoff为折扣
		double sum=0.0;//double型变量
		for(double price:arr){
			sum+=price;
		}
		sum*=cutoff;//折扣后的价格
		return sum;
	}
}
```
#### //数组元素拷贝,从src数组中拷贝3,4,5,6这四个元素到dest数组中（从索引为5的位置开始拷贝）
```java
public class Demo {
	public static void main(String[] args) {
		int[] src=new int[]{1,2,3,4,5,6,7,8,9,10};//原数组
		int[] dest=new int[10];//目标数组
		//需求：从src数组中拷贝3,4,5,6这四个元素到dest数组中
		Demo.printArray(dest);//打印dest中原有的元素[0,0,0,0,0,0,0,0,0,0,]
		Demo.copyArr(src,2,dest,5,4);//拷贝操作
		Demo.printArray(dest);//[0,0,0,0,0,3,4,5,6,0]
		}
	/*参数：
	 * src：表示原数组
	 * srcPos:从原数组的哪一个索引位置开始拷贝
	 * destPos:在目标数组中哪一个索引位置开始粘贴
	 * dest:目标数组
	 * length:拷贝的长度
	 * */
	static void copyArr(int[] src,int srcPos,int[] dest,int destPos,int length ){
		/*
		//拷贝3
		dest[destPos]=src[srcPos];
		srcPos++;
		destPos++;
		//拷贝4
		dest[destPos]=src[srcPos];
		srcPos++;
		destPos++;
		//拷贝5
		dest[destPos]=src[srcPos];
		srcPos++;
		destPos++;
		//拷贝6
		dest[destPos]=src[srcPos];
		srcPos++;
		destPos++;
		*/
		//index表示每次想要拷贝元素的索引
		if(srcPos<0||destPos<0||length<0||srcPos+length>src.length){
			System.out.println("输入有误");
		}
		for(int index=srcPos;index<srcPos+length;index++){
			dest[destPos]=src[index];
			destPos++;//destPos若不自增，则dest数组中只在第5个位置，更改数据，最后该位置为6
		}
	}
```
#### //经常用到的打印操作
```java
static void printArray(int[] arr){
	if(arr==null){
		System.out.println("null");
		return;//结束方法，若不加return，则输出"null"后，还会继续向下执行，输出下面的内容。
	}
	String ret="[";
	for(int index=0;index<arr.length;index++){
		ret=ret+arr[index];
		if(index!=arr.length-1){
			ret=ret+",";
		}
	}
	ret=ret+"]";
	System.out.println(ret);
  }
}
输出：
[0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,3,4,5,6,0]
```
#### //冒泡排序
```java
public class Demo {
	public static void main(String[] args) {
		int[] arr={1,3,5,4,9,2,8,10,7,};
		Demo.printArray(arr);//输出原数组元素
		Demo.bubbleSort(arr);
		Demo.printArray(arr);
	}
	static void bubbleSort(int[] arr){
		/*
		//第一轮
		for(int i=1;i<=arr.length-1;i++){
			if(arr[i-1]>arr[i]){//最后一个元素不用比较，节约时间
				Demo.swap(arr, i-1, i);
			}
		}
		//第二轮
		for(int i=1;i<=arr.length-2;i++){//最后的两个元素可以不用比较
			if(arr[i-1]>arr[i]){
				Demo.swap(arr, i-1, i);
			}
		}
		//第三轮
		for(int i=1;i<=arr.length-3;i++){//最后三个元素不用比较
			if(arr[i-1]>arr[i]){
				Demo.swap(arr, i-1, i);
			}
		}
		......
		*/
		for(int time=1;time<=arr.length-1;time++){//引入time变量，用于记录第几轮排序
			for(int i=1;i<=arr.length-time;i++){
				if(arr[i-1]>arr[i]){
					//1:int temp=arr[i];//也可使用1：这种方法输出数组元素
					//1:arr[i]=arr[i-1];
					//1:arr[i-1]=temp;
					Demo.swap(arr, i-1, i);
				}
			}	
		}
		//1:Demo.printArray(arr);
	}
```
#### //数组元素的位置调换方法
```java
	static void swap(int[] arr,int index1,int index2){//错误的写法：(int arr[i-1];int arr[i])
		int temp=arr[index1];
		arr[index1]=arr[index2];
		arr[index2]=temp;
	}
	static void printArray(int[] arr){
		if(arr==null){
			System.out.println("null");
			return;//结束方法，若不加return，则输出"null"后，还会继续向下执行，输出下面的内容。
		}
		String ret="[";
		for(int index=0;index<arr.length;index++){
			ret=ret+arr[index];
			if(index!=arr.length-1){
				ret=ret+",";
			}
		}
		ret=ret+"]";
		System.out.println(ret);
	}
}
输出：
[1,3,5,4,9,2,8,10,7]
//1：[1,3,5,4,9,2,8,10,7]
[1,3,5,4,9,2,8,10,7]
```
#### //二分求解法，猜随产生的数为多少
>从数组arr中搜索元素key的元素，找到返回其索引，否则返回-1
```java
public class Demo {
	public static void main(String[] args) {
		int[] arr={1,2,3,4,5,6,7,8,9,10};//此例中使用有序数组，无需数组则需先进行排序操作
		int index=Demo.binarySearch(arr, 8);//插叙数字8的索引
		System.out.println(index);
	}
	static int binarySearch(int[] arr,int key){
		int low=0;//最小的索引
		int high=arr.length-1;//最大的索引
		while(low<=high){
			System.out.println(low+"-------"+high);//便于观看查找的过程
			int mid=(low+high)>>1;//等于(low+high)/2,求索引的中间值,这样写的效率更高，省去了转换时间
			if(key>arr[mid]){//猜小了
				low=mid+1;
			}
			else if(key<arr[mid]){//猜大了
				high=mid-1;
			}
			else{
				return mid;//arr[mid]=key
			}
		}
		return -1;
	}
}
输出：
0-------9
5-------9
7
```
#### //描述学生对象，输出学生的信息，是否缴费，没缴费的调用缴费函数
```java
class Student{
	String name;//学生的姓名,null
	boolean isFee;//是否缴费,false
	void fees(){//执行该方法则使缴费状态变为已经交学费
		isFee=true;//不能这样定义boolean isFee=true;此时isFee为一个局部变量，不会改变外面的成员变量的值
	}
}
public class Demo {
	public static void main(String[] args){//主方法
	//创建四个对象
	Student s1=new Student();
	s1.name="张三";
	s1.isFee=true;
	//创建第二个对象
	Student s2=new Student();
	s2.name="李四";
	//s2.isFee=false;//可以省略，默认值就为false
	//创建第三个对象
	Student s3=new Student();
	s3.name="王麻子";
	s3.isFee=false;
	//创建第四个对象
	Student s4=new Student();
	s4.name="企鹅";
	s4.isFee=true;
	//创建一个数组，用于存储所有学生对象
	//数组的定义格式：数组元素类型[] arr=new  数组元素类型[]{s1,s2,s3,s4};
	Student[] arr=new Student[]{s1,s2,s3,s4};
	//使用循环迭代数组中的一个元素
	for(Student ele:arr){//注意foreach循环里的数据类型
		System.out.print(ele.name+":"+ele.isFee+"\t");
		//判断当前学生对象是否缴费，如果没有，则调用缴费方法
		if(!ele.isFee){
			ele.fees();
		}
	}
	System.out.println();//换行
	for(Student sle:arr){
		System.out.print(sle.name+":"+sle.isFee+"\t");
	}
  }
}
输出：
张三:true	李四:false	王麻子:false	企鹅:true	
张三:true	李四:true	王麻子:true	企鹅:true	
```
#### //演示自定义构造类
```java
//表示人类
class Person{
	String name=null;//人的名称
	//自定义构造器
	Person(){//无参数构造方法，和下面一个构造方法形成方法的重载
		System.out.println("无参数");
	}
	Person(String n){//有参构造方法
		name=n;//把n的参数值赋给name字段
		//初始化操作
		//可以给字段设置初始值，也可以调用初始化方法
	}
}
public class Demo {
	public static void main(String[] args){//主方法
	//创建对象：其实是在调用构造器
		new Person();//匿名对象，仅可使用一次
		Person p=new Person();//表示调用Person类中，无参数的构造器
		p.name="will";
		System.out.println(p.name);
		Person p1=new Person("hhhh");//直接赋值
		System.out.println(p1.name);
		
	}
}
输出：
无参数
无参数
will
hhhh
```
#### //static的用法比较
```java
//人类
class Person{
	String name;
	int age;
	static int totalNum=5;//假设现在的总人数为5，全人类共有的
	Person(String n,int a){
		name=n;
		age=a;
		totalNum++;
	}
	void die(){
		totalNum--;
		System.out.println("去世。。。。。。");
	}
	//只有人类才有毁灭
	static void destory(){
		totalNum=0;//毁灭后人数为0
		System.out.println("人类毁灭。。。。。");
	}
}
public class Demo {
	public static void main(String[] args){//主方法
		System.out.println(Person.totalNum);
		Person p1=new Person("will",17);
		Person p2=new Person("lucy",18);
		System.out.println(Person.totalNum);
//		System.out.println(new Person().name);
//		System.out.println(p1.totalNum);//提示使用Person.totalNum访问
//		System	.out.println(p2.totalNum);
//		System.out.println(new Person("x",18).totalNum);
		p2.die();//p2去世
		System.out.println(Person.totalNum);
		//static修饰的方法可以被类中的成员调用，但这样不直观反映调用的过程，而且这种方法的实质是底层上运用Person在调用。
		//p2.destory();
		Person.destory();
		System.out.println(Person.totalNum);
	}
}
输出：
5
7
去世。。。。。。
6
人类毁灭。。。。。
0
```
#### //演示封装
>通过public方法访问私字段
```java
class Person{
	String name;
	private int age;//将age定义为私有的防止外部随便访问,可以用一个方法来改变age的数据，这样就可以对输入的数值进行判断是否为错误的数据
	//int age;//若使用这个语句这外部对象也可访问age，并且改变其值，但又不可以对赋值内容进行判断，如有些赋值是错误的如年龄被赋值为负数
	void setAge(int a){
		System.out.println(a);
		if(a<0){
			System.out.println("你傻啊，年龄不能为负数");
			return;//结束方法
		}
		age=a;//把参数a的值赋给字段age
	}
	//以上方法只是一个setter方法，通过该方法把a的值付给了age，又age为私有成员变量，此时外部对象还不可以获取age的值这就需要在该类中设置一个getter方法来让对象获取age的值。
}
public class Demo {
	public static void main(String[] args){//主方法
	//创建一个对象，设置年龄
	Person p1=new Person();
	p1.name="will";
	p1.setAge(-17);
//	System.out.println(p1.setAge);
	}
}
输出：
-17
你傻啊，年龄不能为负数
```
#### //演示this关键字的使用
```java
class User{
	private String name;//名称
	private int age;//年龄
	public String getName(){//不需要参数，调用就行
		return name;//返回name字段
	}
	public void setName(String name){
		this.name=name;
	}
//	public void setName(String name){
//		name=name;//输出：null，遵循就近原则，此时两个name都是指形参name，初始值为null
//	}
//	public void setName(String n){//不需要返回值，需要参数
//		name=n;
//	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
	
}
public class Demo {
	public static void main(String[] args){
	User s1=new User();
	s1.setName("will");
	s1.setAge(17);
	int a=s1.getAge();
	System.out.println(s1.getName());//参数，第一种打印
	System.out.println(a);//参数，第二种打印
	}
}
输出：
will
17
```
#### //练习：判断一个点和圆的关系（圆外、圆内、圆上），规定圆的圆心点为（0，0）
```java
//创建点对象
class Point{
	private int x;
	private int y;
	Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX(){
		return this.x=x;
	}
	public int getY(){
		return this.y=y;
	}
	
}
//创建于圆对象
class Circle{
	private int r;//半径
	Circle(int r){
		this.r=r;
	}
	/*判断点和圆的关系
	 * 返回：
	 * 		1：表明在圆外
	 * 		0：表明在圆周上
	 * 		-1：表明在圆内
	 * */
	int judge(Point p){
		//暂不使用Math.sqrt()方法
		int xxyy=p.getX()*p.getX()+p.getY()*p.getY();
		int rr=this.r*this.r;//此时this无歧义，可以省略.r^2=x^2+y^2
		if(xxyy>rr){
			return 1;
		}
		else if(xxyy<rr){
			return -1;
		}
		else{
			return 0;
		}
	}
}
public class Demo {
	public static void main(String[] args){
		//创建一个点对象（3,4）
		Point p=new Point(3,4);
		//创建一个半径为5的对象
		Circle c=new Circle(5);
		//判断
		int ret=c.judge(p);//
		System.out.println(ret);
	}

}
输出：0（在圆内）
```
#### //演示方法的覆盖
```java
//鸟类
class Bird{//class Bird extends Object{
	//省略鸟类的其他状态，只定义鸟的飞翔
	public void fly(){
	System.out.println("仰望天空，自由飞翔！");
	}
}
//企鹅类
class Penguin extends Bird{
	//重新定义fly
	public void fly(){
	System.out.println("我是企鹅，不能飞翔");
	}
}
public class ExtendsDemo{
	public static void main(String[] args){
	//创建一个企鹅对象，并调用其飞翔的方法
		Penguin p=new Penguin();
		//先去子类中去寻找fly方法，如果找到就执行
		//如果找不大,继续去父类中去找（若直接父类中找不到，则向父类的父类中找，若没找到，则找到最原始的父类为Object类中后停止）
		p.fly();
	}
}
输出：我是企鹅，不能飞翔
```
#### //子类初始化过程:创建子类对象的过程
```java
//动物
class Animal{
	private String name;
	//protected String name;//子类中可以访问
	private int age;
	Animal(String name,int age){//如自己不设置构造器，系统将自动构建一个默认的构造器
		System.out.println("Animal的构造器");
		this.name=name;
		this.age=age;
	}
	public String getName(){
		return name;
	}
}
//鱼
class Fish extends Animal{
	private String color;//颜色
	Fish(String name,int age,String color){//Fish本身拥有name,age和color，只不过有两个状态需要从父类中继承，所以在床底参数时就当Fish类中有这三个元素，传递三个相同类型的实参进去
		//super();//隐式，调用父类无参数的构造器，这个方法会自动生成，也可以自己写出来
		//System.out.println("Fish的构造器");
		super(name,age);//调用父类中参数列表一样的构造器
		this.color=color;
	}
	public void say(){
		System.out.println(getName()+","+this.color);
	}
}
public class ExtendsDemo{
	public static void main(String[] args){
	//创建鱼对象
		Fish f=new Fish("尼莫",5,"橘黄色");
		f.say();
	}
}
输出：
Animal的构造器
尼莫,橘黄色
```