package concurrency.test;

public class Runner {
    public static void main(String[] args) throws Exception{
        Bank bank = new Bank();
        System.out.println("Bank sum before: " + bank.sumSTM());
        long before = System.currentTimeMillis();
        bank.simulate(10, 100000);
        long after = System.currentTimeMillis();
        System.out.println("Bank sum after: " + bank.sumSTM());
        System.out.println("Elapsed time: " + (after - before));
    }
}
