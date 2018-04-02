#### //运用for循环打印菱形
```java
public class Var_02 {	
	public static void main(String args[]){			
		for(int i=1;i<=7;i+=2){
			for(int kong=7;kong>i-1;kong--){
				System.out.print(" ");
			}
			for(int xing=1;xing<=i;xing++){
				System.out.print("* ");
			}
		       System.out.println();
		}
		for(int j=1;j<=5;j+=2){
				for(int kong1=1;kong1<j+3;kong1++){
					System.out.print(" ");
				}
				for(int xing1=5;xing1>=j;xing1--){
					System.out.print("* ");
				}
			System.out.println();
		}
    }
}
```
运行截图：
```
       * 
     * * * 
   * * * * * 
 * * * * * * * 
   * * * * * 
     * * * 
       * 
```

```java
package com.sayschj.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//演示同步锁，不常用
//保证线程的同步
class Apple3 implements Runnable{
	private final Lock lock=new ReentrantLock();
	private int num=50;//总共50个苹果 
	public void run(){
		for(int i=0;i<50;i++){
			eat();
		}
	}

	private void eat() {
		lock.lock();//获取锁
			try {
			if(num>0){
				System.out.println(Thread.currentThread().getName()+"吃了第"+num+"个苹果");
				Thread.sleep(10);
				num--;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();//释放锁
			}
		}
	}
public class LockDemo {
	public static void main(String[] args) {
		Apple3 a=new Apple3();
		new Thread(a,"小A").start();
		new Thread(a,"小B").start();
		new Thread(a,"小C").start();
	}
}
```
