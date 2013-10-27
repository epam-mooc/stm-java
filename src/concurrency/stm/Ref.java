package concurrency.stm;

/**
 * @author mishadoff
 */
public final class Ref<T> {
    RefTuple<T, Long> content;

    public Ref(T value) {
        content = RefTuple.get(value, 0);
    }

    public T getValue(Context ctx) {
        return ctx.get(this);
    }

    public void setValue(T value, Transaction tx) {
        tx.set(this, value);
    }
}
