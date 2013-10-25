package concurrency.stm;

/**
 * @author mishadoff
 */
public abstract class TransactionBlock implements Runnable {
    private Transaction tx;

    void setTx(Transaction tx) {
        this.tx = tx;
    }

    public Transaction getTx() {
        return tx;
    }
}
