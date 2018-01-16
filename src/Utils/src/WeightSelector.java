import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by olko1016 on 01/17/2017.
 */
public class WeightSelector<T> {
    protected static final Random r = new Random();
    Integer maxWeight = 0;
    Map<Integer, T> itemsCollection = new LinkedHashMap();
    Map<T, Integer> statistics = new HashMap<>();

    public WeightSelector<T> addItem(T item, Integer weight) {
        maxWeight += weight;
        itemsCollection.put(maxWeight, item);
        statistics.put(item, 0);
        return this;
    }

    public T select() {
        int k = r.nextInt(maxWeight);
        for (Map.Entry<Integer, T> entry : itemsCollection.entrySet()) {
            if (k < entry.getKey()) {
                Integer integer = statistics.get(entry.getValue());
                statistics.put(entry.getValue(), ++integer);
                return entry.getValue();
            }
        }
        return null;
    }

    public void showStatistics() {
        for (Map.Entry<T, Integer> entry : statistics.entrySet()) {
            System.out.println(String.format("%s - %s", entry.getKey(), entry.getValue()));
        }
    }

    public void showInfo() {
        int previous = 0;
        for (Map.Entry<Integer, T> entry : itemsCollection.entrySet()) {
            Integer key = entry.getKey();
            System.out.println(String.format("[%s-%s] - %s", previous, key, entry.getValue()));
            previous = key + 1;
        }
    }
}
