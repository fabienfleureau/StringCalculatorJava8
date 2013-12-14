import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by f.fleureau on 14/12/13.
 */
public class StringCalculatorTest {


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
}
