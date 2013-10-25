package concurrency.test;

public class Runner {
    public static void main(String[] args) throws Exception{
        Bank bank = new Bank();
        System.out.println("Bank sum before: " + bank.sum());
        bank.simulate(100, 100000);
        System.out.println("Bank sum after: " + bank.sum());
    }
}
