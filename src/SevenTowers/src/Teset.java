import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Олег on 14.02.2018.
 */
public class Teset {
    public static void main(String[] args) {
        f8();
    }

    private static void f2() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
    }

    private static void f1() {
        List<Integer> integers = Arrays.asList(1, 5, 6, -1, 8);

    }

    private static void f8() {
        List<List<String>> source = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("x", "y"));
        Stream<String> stream = source.get(0).stream().flatMap(a ->
                source.get(1).stream().map(b -> a + b));
        stream.forEach(System.out::print);
    }


}
