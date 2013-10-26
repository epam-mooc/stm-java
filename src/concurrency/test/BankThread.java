package concurrency.test;

public class BankThread implements Runnable {
    private Bank bank;
    private int num;

    public BankThread(Bank bank, int num) {
        this.bank = bank;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            bank.transferSTM(bank.getRandomAccount(),
                          bank.getRandomAccount(),
                          bank.getRandomValue());
        }
    }
}
