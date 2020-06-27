public class Main {
    static Object m = new Object();
    static volatile int mNum = 1;
    static final int num = 6;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 1; i < num; i++) {
                    synchronized (m) {
                        while (mNum != 1) {
                            m.wait();
                        }
                        System.out.print("A ");
                        mNum = 2;
                        m.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 1; i < num; i++) {
                    synchronized (m) {
                        while (mNum != 2) {
                            m.wait();
                        }
                        System.out.print("B ");
                        mNum = 3;
                        m.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 1; i < num; i++) {
                    synchronized (m) {
                        while (mNum != 3) {
                            m.wait();
                        }
                        System.out.print("C ");
                        mNum = 4;
                        m.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 1; i < num; i++) {
                    synchronized (m) {
                        while (mNum != 4) {
                            m.wait();
                        }
                        System.out.print("-Поток " + i + " -\n");
                        mNum = 1;
                        m.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

