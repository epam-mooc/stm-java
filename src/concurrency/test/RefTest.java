package concurrency.test;

import concurrency.stm.GlobalContext;

/**
 * @author mishadoff
 */
public class RefTest {
    public static void main(String[] args) {
        Account a = new Account(100);
        Account b = new Account(100);

        Bank bank = new Bank();

        new STMStrategy().transfer(a, b, 10);
        new STMStrategy().transfer(a, b, 10);
        new STMStrategy().transfer(a, b, 10);

        System.out.println("A: " + a.getRef().getValue(GlobalContext.get()));
        System.out.println("B: " + b.getRef().getValue(GlobalContext.get()));
    }
}
