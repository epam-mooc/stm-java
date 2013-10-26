package concurrency.stm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mishadoff
 */
public final class GlobalContext extends Context {
    private static GlobalContext instance = new GlobalContext();
    private HashMap<Ref, Object> world = new HashMap<>();

    private GlobalContext() { }

    public static GlobalContext get() {
        return instance;
    }

    @Override
    <T> T get(Ref<T> ref) {
        return (T)world.get(ref);
    }

    public void fillSnapshot(final HashMap<Ref, Object> inTxMap,
                             final HashMap<Ref, Long> version) {
        synchronized (STM.snapshotLock) {
            for (Map.Entry<Ref, Object> refEntry : world.entrySet()) {
                inTxMap.put(refEntry.getKey(), refEntry.getValue());
                version.put(refEntry.getKey(), refEntry.getKey().revision);
            }
        }
    }

    public <T> void register(Ref<T> ref, T value) {
        world.put(ref, value);
    }
}
