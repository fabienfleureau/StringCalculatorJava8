import java.util.Arrays;

/**
 * Created by f.fleureau on 14/12/13.
 */
public class StringCalculator {

    public int add(String numbers) {
        if ("".equals(numbers)) {
            return 0;
        }
        String separators = ",|\\n";
        if (numbers.startsWith("//")) {
            separators = Arrays.asList(numbers.substring(3, numbers.indexOf('\n') - 1)
                    .split("\\]\\[")).stream()
                    .reduce((left, right) -> left + "|" + right).get();
            numbers = numbers.substring(numbers.indexOf('\n') + 1);
        }
        return Arrays.asList(numbers.split(separators)).stream()
                .mapToInt(Integer::parseInt)
                .reduce((left, right) -> left + right).getAsInt();
    }
}
