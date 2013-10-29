package concurrency.test;

import concurrency.stm.Ref;

public class Account {
    private Ref<Long> moneyRef;

    Account(long initialMoney) {
        moneyRef = new Ref<Long>(initialMoney);
    }

    public void add(long amount) {
        moneyRef.set(moneyRef.get() + amount);
    }

    long getMoney() {
        return moneyRef.get();
    }

    public Ref<Long> getRef() {
        return moneyRef;
    }

}
