package com.pallasli.study.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized是java中的一个关键字，也就是说是Java语言内置的特性。那么为什么会出现Lock呢？
 * 
 * 在上面一篇文章中，我们了解到如果一个代码块被synchronized修饰了，当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待，
 * 等待获取锁的线程释放锁，而这里获取锁的线程释放锁只会有两种情况：
 * 
 * 1）获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
 * 
 * 2）线程执行发生异常，此时JVM会让线程自动释放锁。
 * 
 * 那么如果这个获取锁的线程由于要等待IO或者其他原因（比如调用sleep方法）被阻塞了，但是又没有释放锁，其他线程便只能干巴巴地等待，试想一下，
 * 这多么影响程序执行效率。
 * 
 * 因此就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间或者能够响应中断），通过Lock就可以办到。
 * 
 * 再举个例子：当有多个线程读写文件时，读操作和写操作会发生冲突现象，写操作和写操作会发生冲突现象，但是读操作和读操作不会发生冲突现象。
 * 
 * 但是采用synchronized关键字来实现同步的话，就会导致一个问题：
 * 
 * 如果多个线程都只是进行读操作，所以当一个线程在进行读操作时，其他线程只能等待无法进行读操作。
 * 
 * 因此就需要一种机制来使得多个线程都只是进行读操作时，线程之间不会发生冲突，通过Lock就可以办到。
 * 
 * 另外，通过Lock可以知道线程有没有成功获取到锁。这个是synchronized无法办到的。
 * 
 * 总结一下，也就是说Lock提供了比synchronized更多的功能。但是要注意以下几点：
 * 
 * 1）Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问；
 * 
 * 2）Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，
 * 当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，
 * 如果没有主动释放锁，就有可能导致出现死锁现象。
 * 
 * 
 * @author lyt1987
 *
 */
public class Test {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock(); // 注意这个地方

	public static void main(String[] args) {
		final Test test = new Test();

		new Thread() {
			@Override
			public void run() {
				test.insert2(Thread.currentThread());
			};
		}.start();

		new Thread() {
			@Override
			public void run() {
				test.insert2(Thread.currentThread());
			};
		}.start();
	}

	public void insert(Thread thread) {
		// Lock lock = new ReentrantLock(); // 注意这个地方,局部变量将建立不同的锁
		lock.lock();
		try {
			System.out.println(thread.getName() + "得到了锁");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(thread.getName() + "释放了锁");
			lock.unlock();
		}
	}

	public void insert2(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + "得到了锁");
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				System.out.println(thread.getName() + "释放了锁");
				lock.unlock();
			}
		} else {
			System.out.println(thread.getName() + "获取锁失败");
		}
	}
}
