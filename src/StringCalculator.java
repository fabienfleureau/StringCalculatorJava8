import java.util.Arrays;
import java.util.List;

/**
 * Created by f.fleureau on 14/12/13.
 */
public class StringCalculator {

    public int add(String numbers) {
        if ("".equals(numbers)) {
            return 0;
        }
        List<String> stringList = Arrays.asList(numbers.split(","));
        return stringList.stream()
                .mapToInt(Integer::parseInt)
                .reduce((left, right) -> left + right).getAsInt();
    }
}
