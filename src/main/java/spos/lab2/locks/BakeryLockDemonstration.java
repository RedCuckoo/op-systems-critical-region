package spos.lab2.locks;

import java.util.ArrayList;
import java.util.List;

public class BakeryLockDemonstration {
    public void run() {
        AbstractFixnumLock bakeryLock = new BakeryLock(5);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            threads.add(new Thread(new BakeryLockRunnable(bakeryLock)));
        }

        for (Thread i : threads) {
            i.start();
        }

        try {
            for (Thread i : threads) {
                i.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
