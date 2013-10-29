package concurrency.test;

import concurrency.stm.*;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bank {
    Account[] accounts;
    private Random random = new Random();

    long total = 0;
    double charge = 0;

    public Bank() {
        randomFill();
    }

    private void randomFill() {
        int NUM = 100;
        accounts = new Account[NUM];
        for (int i = 0; i < NUM; i++) {
            accounts[i] = new Account(100000);
        }
    }

    public Account getRandomAccount() {
        return accounts[random.nextInt(accounts.length)];
    }

    public int getRandomValue() {
        return random.nextInt(100);
    }

    public long sum() {
        long sum = 0;
        for (Account a : accounts) sum += a.getMoney();
        return sum;
    }

    public void simulate(int threads, int num, TransferStrategy ts) throws Exception{
        ExecutorService service =
                Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            service.submit(new BankThread(this, num, ts));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }
}
