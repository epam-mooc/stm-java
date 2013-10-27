package concurrency.stm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mishadoff
 */
public final class GlobalContext extends Context {
    private static GlobalContext instance = new GlobalContext();

    private GlobalContext() { }

    public static GlobalContext get() {
        return instance;
    }

    @Override
    <T> T get(Ref<T> ref) {
        return ref.content.value;
    }
}
