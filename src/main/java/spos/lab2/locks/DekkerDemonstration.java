package spos.lab2.locks;

public class DekkerDemonstration {
    private static final int ITERATIONS = 1_000_00;

    private volatile long counter;
    private static int counterwithoutlock=0;
    private Thread firstThread;
    private Thread secondThread;
    private Thread firstThreadwithoutlock;
    private Thread secondThreadwithoutlock;
    private DekkerLock dekkerLock = new DekkerLock();


    private void increment() throws InterruptedException {
        counter++;
    }

    private void manipulate() {

        for (int i = 0; i < ITERATIONS; i++) {
            try {
                    dekkerLock.lock();
                    System.out.println("Locked thread: " + Thread.currentThread().getName());
                    increment();
                System.out.println(Thread.currentThread().getName() + " incremented counter. Current value of counter: " + counter);

                dekkerLock.unlock();
                System.out.println("Unlocked thread: " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void manipulatewithoutlock() {

        for (int i = 0; i < ITERATIONS; i++) {
                counterwithoutlock++;
        }
    }
    public  void run() throws InterruptedException {
        firstThreadwithoutlock = new Thread(this::manipulate);
        secondThreadwithoutlock = new Thread(this::manipulate);

        firstThread = new Thread(this::manipulatewithoutlock);
        secondThread = new Thread(this::manipulatewithoutlock);

        firstThread.start();
        secondThread.start();
        firstThreadwithoutlock.start();
        secondThreadwithoutlock.start();

        firstThread.join();
        secondThread.join();
        firstThreadwithoutlock.join();
        secondThreadwithoutlock.join();

        report();
    }

    private void report() {

        System.out.println("With lock: " + (counter)  + "\n" +
                "Without lock: " + counterwithoutlock);
    }



}
