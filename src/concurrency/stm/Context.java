package concurrency.stm;

/**
 * @author mishadoff
 */
public abstract class Context {
    abstract <T> T get(Ref<T> ref);
}
