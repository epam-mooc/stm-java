package concurrency.stm;

/**
 * @author mishadoff
 */
public class GlobalContext extends Context {

    @Override
    <T> T get(Ref<T> ref) {
        return ref.value;
    }
}
