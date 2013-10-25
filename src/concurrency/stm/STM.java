package concurrency.stm;

/**
 * @author mishadoff
 */
public final class STM {
    private STM() {}

    public static Object commitLock = new Object();

    public static void transaction(TransactionBlock block) {
        Transaction tx = new Transaction();
        block.setTx(tx);
        block.run();
        tx.commit();
    }

}
