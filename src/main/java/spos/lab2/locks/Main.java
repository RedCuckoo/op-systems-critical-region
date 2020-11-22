package spos.lab2.locks;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Bakery lock Demonstration:");
        new BakeryLockDemonstration().run();

        System.out.println("DekkerDemonstration:");
        new DekkerDemonstration().run();
    }
}
