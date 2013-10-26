package concurrency.stm;

/**
 * @author mishadoff
 */
public final class STM {
    private STM() {}

    public static Object snapshotLock = new Object();
    public static Object commitLock = new Object();

    public static void transaction(TransactionBlock block) {
        boolean committed = false;
        while (!committed) {
            Transaction tx = new Transaction();
            block.setTx(tx);
            block.run();
            committed = tx.commit();
        }
    }

}
