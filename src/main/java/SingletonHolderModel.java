/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/27
 */
public class SingletonHolderModel {

    public static SingletonHolderModel instance() {
        return SingletonHolder.SINGLETON;
    }

    private SingletonHolderModel() {
    }

    private static class SingletonHolder {
        private static final SingletonHolderModel SINGLETON = new SingletonHolderModel();
    }
}
