package concurrency.stm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mishadoff
 */
public final class Transaction extends Context{
    private HashMap<Ref, Object> inTxMap = new HashMap<>();
    private HashSet<Ref> toUpdate = new HashSet<>();
    private HashMap<Ref, Long> version = new HashMap<>();

    private long revision;
    private static AtomicLong transactionNum = new AtomicLong(0);

    Transaction() {
        revision = transactionNum.incrementAndGet();
        GlobalContext.get().fillSnapshot(inTxMap, version);
    }

    @Override
    <T> T get(Ref<T> ref) {
        return (T)inTxMap.get(ref);
    }

    <T> void set(Ref<T> ref, T value) {
        inTxMap.put(ref, value);
        toUpdate.add(ref);
        if (!version.containsKey(ref)) {
            version.put(ref, ref.revision);
        }
    }

    boolean commit() {
        synchronized (STM.commitLock) {
            // validation
            boolean isValid = true;
            for (Ref ref : inTxMap.keySet()) {
                if (ref.revision != version.get(ref)) {
                    isValid = false;
                    break;
                }
            }

            // writes
            if (isValid) {
                for (Ref ref : toUpdate) {
                    ref.value = inTxMap.get(ref);
                    ref.revision = revision;
                }
            }
            return isValid;
        }
    }
}
