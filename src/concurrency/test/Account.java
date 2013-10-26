package concurrency.test;

import concurrency.stm.Ref;

public class Account {
    private long money;
    private Ref<Long> moneyRef;

    Account(long initialMoney) {
        money = initialMoney;
        moneyRef = new Ref<Long>(money);
    }

    public void add(long amount) {
        money += amount;
    }

    long getMoney() {
        return money;
    }

    public Ref<Long> getRef() {
        return moneyRef;
    }

}
