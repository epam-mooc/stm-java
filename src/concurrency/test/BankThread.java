package concurrency.test;

public class BankThread implements Runnable {
    private Bank bank;
    private int num;
    private TransferStrategy ts;

    public BankThread(Bank bank, int num, TransferStrategy ts) {
        this.bank = bank;
        this.num = num;
        this.ts = ts;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            ts.transfer(bank.getRandomAccount(),
                        bank.getRandomAccount(),
                        bank.getRandomValue());
        }
    }
}
