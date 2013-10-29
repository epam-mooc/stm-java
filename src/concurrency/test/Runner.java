package concurrency.test;

public class Runner {
    public static void main(String[] args) throws Exception{
        TransferStrategy[] tss = new TransferStrategy[] {
            new NonSyncStrategy(),
            new SyncStrategy(),
            new STMStrategy()
        };

        for (TransferStrategy ts : tss) {
            Bank bank = new Bank();
            System.out.println(ts.getClass().getSimpleName());
            System.out.println("Bank sum before: " + bank.sum());
            long before = System.currentTimeMillis();
            bank.simulate(100, 10000, ts);
            long after = System.currentTimeMillis();
            System.out.println("Bank sum after: " + bank.sum());
            System.out.println("Elapsed time: " + (after - before));
        }
    }
}
