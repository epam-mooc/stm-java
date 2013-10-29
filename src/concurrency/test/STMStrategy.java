package concurrency.test;

import concurrency.stm.STM;
import concurrency.stm.Transaction;
import concurrency.stm.TransactionBlock;

/**
 * @author mishadoff
 */
public class STMStrategy implements TransferStrategy {
    @Override
    public void transfer(final Account a, final Account b, final int amount) {
        STM.transaction(new TransactionBlock() {
            @Override
            public void run() {
                Transaction tx = this.getTx();
                long old1 = a.getRef().getValue(tx);
                a.getRef().setValue(old1 - amount, tx);
                long old2 = b.getRef().getValue(tx);
                b.getRef().setValue(old2 + amount, tx);
            }
        });
    }
}
