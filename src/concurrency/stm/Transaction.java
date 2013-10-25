package concurrency.stm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author mishadoff
 */
public final class Transaction  extends Context{
    private HashMap<Ref, Object> inTxMap = new HashMap<>();
    private HashSet<Ref> toUpdate = new HashSet<>();

    @Override
    <T> T get(Ref<T> ref) {
        if (!inTxMap.containsKey(ref)) {
            inTxMap.put(ref, ref.value);
        }
        return (T)inTxMap.get(ref);
    }

    <T> void set(Ref<T> ref, T value) {
        inTxMap.put(ref, value);
        toUpdate.add(ref);
    }

    boolean commit() {
        synchronized (STM.commitLock) {
            // TODO validate
            boolean isValid = true;

            for (Ref ref : toUpdate) {
                ref.value = inTxMap.get(ref);
            }
            return isValid;
        }
    }
}
