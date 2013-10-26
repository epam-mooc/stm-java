package concurrency.stm;

/**
 * @author mishadoff
 */
public final class Ref<T> {
    T value;
    long revision = 0;

    public Ref(T value) {
        this.value = value;
        GlobalContext.get().register(this, value);
    }

    public T getValue(Context ctx) {
        return ctx.get(this);
    }

    public void setValue(T value, Transaction tx) {
        tx.set(this, value);
    }
}
