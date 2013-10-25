package concurrency.test;

import concurrency.stm.*;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bank {
    private Account[] accounts;
    private Random random = new Random();

    public Bank() {
        randomFill();
    }

    void transfer(Account a1, Account a2, int amount) {
        a1.add(-amount);
        a2.add(amount);
    }

    void transfer(final Ref<Integer> a1, final Ref<Integer> a2, final int value) {
        STM.transaction(new TransactionBlock() {
            @Override
            public void run() {
                Transaction tx = this.getTx();
                int old1 = a1.getValue(tx);
                a1.setValue(old1 - value, tx);
                int old2 = a2.getValue(tx);
                a2.setValue(old2 + value, tx);
            }
        });
    }

    private void randomFill() {
        int NUM = 10;
        accounts = new Account[NUM];
        for (int i = 0; i < NUM; i++) {
            accounts[i] = new Account(1000000);
        }
    }

    public Account getRandomAccount() {
        return accounts[random.nextInt(accounts.length)];
    }

    public int getRandomValue() {
        return random.nextInt(10);
    }

    public long sum() {
        long sum = 0;
        for (Account a : accounts) sum += a.getMoney();
        return sum;
    }

    public void simulate(int threads, int num) throws Exception{
        ExecutorService service =
                Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            service.submit(new BankThread(this, num));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }
}
