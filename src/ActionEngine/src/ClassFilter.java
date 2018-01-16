import java.util.HashMap;
import java.util.Map;

public class ClassFilter<T> extends AbstractFilter<T> {
    private final static Map<Class, ClassFilter> classInstanceMap = new HashMap<>();
    private final Class<T> aClass;

    private ClassFilter(Class wantedClass) {
        aClass = wantedClass;
    }

    public static ClassFilter getInstance(Class wantedClass) {
        ClassFilter filter = classInstanceMap.get(wantedClass);
        if (filter != null) {
            return filter;
        }
        ClassFilter newInstance = new ClassFilter(wantedClass);
        classInstanceMap.put(wantedClass, newInstance);
        return newInstance;
    }


    @Override
    public boolean isOk(T obj) {
        return aClass.isInstance(obj);
    }
}
