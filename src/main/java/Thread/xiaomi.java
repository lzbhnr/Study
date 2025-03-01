package Thread;


public class xiaomi {

        // 共享变量，表示当前应该打印哪个字母
        private static int state = 0;

        // 共享对象，作为锁和通信的媒介
        private static final Object lock = new Object();

        public static void main(String[] args) throws InterruptedException {
            // 创建三个线程
            Thread threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 循环100次
                        for (int i = 0; i < 2; i++) {
                            // 获取锁
                            synchronized (lock) {
                                System.out.println("333333-1");
                                // 判断是否轮到自己执行
                                while (state % 3 != 0) {
                                    // 不是则等待
                                    System.out.println("333333-2");
                                    lock.wait();
                                    System.out.println("333333-3");
                                }
                                // 打印字母
                                System.out.println("A");
                                // 修改状态
                                state++;
                                // 唤醒下一个线程
                                lock.notifyAll();
                                System.out.println("333333-4");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadB = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            synchronized (lock) {
                                System.out.println("2222222-1");
                                while (state % 3 != 1) {
                                    System.out.println("2222222-2");
                                    lock.wait();
                                    System.out.println("2222222-3");
                                }
                                System.out.println("2222222-4");
                                System.out.println("B");
                                state++;
                                lock.notifyAll();
                                System.out.println("2222222-5");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadC = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 2; i++) {
                            synchronized (lock) {
                                System.out.println("111111-1");
                                while (state % 3 != 2) {
                                    System.out.println("111111-2");
                                    // 释放锁
                                    lock.wait();
                                    System.out.println("111111-3");
                                }
                                System.out.println("C");
                                System.out.println("111111-4");
                                state++;
                                System.out.println("111111-5");
                                lock.notifyAll();
                                System.out.println("111111-6");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            // 启动三个线程

            threadB.start();
            Thread.sleep(1000l);
            threadC.start();
            Thread.sleep(1000l);
            threadA.start();


        }


}
