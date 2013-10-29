package concurrency.test;

/**
 * @author mishadoff
 */
public interface TransferStrategy {
    void transfer(final Account a, final Account b, final int amount);
}
