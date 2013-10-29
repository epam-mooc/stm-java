package concurrency.stm;

/**
 * @author mishadoff
 */
public final class Ref<T> {
    RefTuple<T, Long> content;

    public Ref(T value) {
        content = RefTuple.get(value, 0L);
    }

    public T getValue(Context ctx) {
        return ctx.get(this);
    }

    public void setValue(T value, Transaction tx) {
        tx.set(this, value);
    }

    /*
       UNSAFE
       ONLY FOR INSTRUMENTATION
    */

    @Deprecated
    public T get() {
        return content.value;
    }

    @Deprecated
    public void set(T value) {
        this.content.value = value;
    }
}
