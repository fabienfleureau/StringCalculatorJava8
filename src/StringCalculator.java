import java.util.Arrays;

/**
 * Created by f.fleureau on 14/12/13.
 */
public class StringCalculator {

    public int add(String numbers) {
        return "".equals(numbers) ? 0 : Arrays.asList(numbers.split(",")).stream()
                .mapToInt(Integer::parseInt)
                .reduce((left, right) -> left + right).getAsInt();
    }
}
