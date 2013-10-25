package concurrency.test;

public class Account {
    private long money;

    Account(long initialMoney) {
        money = initialMoney;
    }

    public void add(long amount) {
        money += amount;
    }

    long getMoney() {
        return money;
    }
}
