package concurrency.test;

/**
 * @author mishadoff
 */
public class SyncStrategy implements TransferStrategy{
    @Override
    public synchronized void transfer(Account a, Account b, int amount) {
        a.add(-amount);
        b.add(amount);
    }
}
