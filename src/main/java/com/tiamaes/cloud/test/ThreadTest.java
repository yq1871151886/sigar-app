package com.tiamaes.cloud.test;



/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/4/20 16:10
 */
public class ThreadTest {


    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println("1sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
                System.out.println("2sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
                System.out.println("3sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                    System.out.println("0sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            System.out.println("线程0执行完毕");
            thread1.join();
            System.out.println("线程1执行完毕");
            thread2.join();
            System.out.println("线程2执行完毕");
            thread3.join();
            System.out.println("线程3执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
