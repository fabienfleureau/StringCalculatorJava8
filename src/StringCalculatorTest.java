import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static java.util.Arrays.parallelSetAll;
import static java.util.Arrays.setAll;

/**
 * Created by f.fleureau on 14/12/13.
 */
public class StringCalculatorTest {

    private static int random() {
        return (int) (1 + Math.random() * 100000);
    }

    private StringCalculator stringCalculator;

    @BeforeMethod
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testAddEmptyString() throws Exception {
        Assert.assertEquals(stringCalculator.add(""), 0);
    }

    @Test
    public void testAddOneString() throws Exception {
        Integer value = 1;
        Assert.assertEquals(stringCalculator.add(value.toString()), value.intValue());
    }

    @Test
    public void testAddTwoString() throws Exception {
        Integer value1 = 1;
        Integer value2 = 2;
        Assert.assertEquals(stringCalculator.add(value1.toString() + "," + value2.toString()), value1 + value2);
    }

    @Test
    public void testAnyNumberOfStrings() throws Exception {
        Integer intArray[] = new Integer[random()];
        parallelSetAll(intArray, x -> random());
        int computedSum = stringCalculator.add(Arrays.asList(intArray)
                .parallelStream()
                .map(val -> val.toString())
                .reduce((str1, str2) -> str1 + "," + str2).get());
        int realSum = Arrays.asList(intArray).parallelStream().reduce((x, y) -> x + y).get();
        Assert.assertEquals(computedSum, realSum);
    }

    @Test
    public void testAnyNumberOfStringsNoParallel() throws Exception {
        Integer[] intArray = new Integer[random()];
        setAll(intArray, x -> random());
        int computedSum = stringCalculator.add(Arrays.asList(intArray)
                .stream()
                .map(val -> val.toString())
                .reduce((str1, str2) -> str1 + "," + str2).get());
        int realSum = Arrays.asList(intArray).stream().reduce((x, y) -> x + y).get();
        Assert.assertEquals(computedSum, realSum);
    }

    @Test
    public void testNewLineSeparator() throws Exception {
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Assert.assertEquals(stringCalculator.add(value1.toString() + "," + value2.toString() + "\n" + value3.toString())
                , value1 + value2 + value3);

    }

    @Test
    public void testAnySeparator() throws Exception {
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Assert.assertEquals(stringCalculator.add("//[;]\n" + value1.toString() + ";" + value2.toString() + ";" + value3.toString())
                , value1 + value2 + value3);

    }

    @Test
    public void testAnyTwoSeparators() throws Exception {
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Assert.assertEquals(stringCalculator.add("//[;][!]\n" + value1.toString() + "!" + value2.toString() + ";" + value3.toString())
                , value1 + value2 + value3);

    }
}
