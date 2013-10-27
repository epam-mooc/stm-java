package concurrency.stm;

/**
 * @author mishadoff
 */
public class RefTuple<V, R> {
    V value;
    R revision;

    public RefTuple(V v, R r) {
        this.value = v;
        this.revision = r;
    }

    static <V, R>  RefTuple get(V v, R r) {
        return new RefTuple<V, R>(v, r);
    }
}
