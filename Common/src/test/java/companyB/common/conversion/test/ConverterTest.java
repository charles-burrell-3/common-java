package companyB.common.conversion.test;

import companyB.common.conversion.Converter;
import companyB.common.test.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static junit.framework.TestCase.fail;

@Test(groups = {"unit","converter","utils","common"})
public class ConverterTest extends TestBase
{
    private Converter converter;

    @BeforeMethod
    public void before()
    {
        converter = new Converter();
    }
    @DataProvider(name = "boolean")
    public static Object[][]booleanData()
    {
        final List<Class>classes = Arrays.asList(Boolean.class,boolean.class);
        final List<String>trueValues = Arrays.asList("t","true","y","yes","1");
        final List<String>falseValues = Arrays.asList("f","false","n","no","0");
        final Map<Boolean, List<String>>mapping = new HashMap<>();
        final List<Object[]>listing = new LinkedList<>();
        mapping.put(true,trueValues);
        mapping.put(false,falseValues);
        classes.forEach( (_class)->{
            mapping.keySet().forEach((key)->{
                final List<String>values = mapping.get(key);
                values.forEach((value)->{
                    listing.add(new Object[]{value,_class,key});
                });
            });
        });
        return listing.toArray(new Object[listing.size()][3]);
    }

    @DataProvider(name = "supported")
    public static Object[][]supported()
    {
        return new Object[][]
                {
                        {Long.class,true},
                        {long.class,true},
                        {Integer.class,true},
                        {int.class,true},
                        {Short.class,true},
                        {short.class,true},
                        {Double.class,true},
                        {double.class,true},
                        {String.class,true},
                        {BigDecimal.class,true},
                        {BigInteger.class,true},
                        {Character.class,true},
                        {char.class,true},
                        {Byte.class,true},
                        {byte.class,true},
                        {ConverterTest.class,false}
                };
    }

    @DataProvider(name = "conversions")
    public static Object[][]conversions()
    {
        final String value = "42";
        final Long longValue = Long.parseLong(value);
        final Short shortValue = Short.parseShort(value);
        final Integer intValue = Integer.parseInt(value);
        final Double doubleValue = Double.parseDouble(value);
        final Byte byteValue = Byte.valueOf(value);
        return new Object[][]
                {
                        {long.class,longValue},
                        {Long.class,longValue},
                        {Short.class,shortValue},
                        {short.class,shortValue},
                        {Double.class,doubleValue},
                        {double.class,doubleValue},
                        {Integer.class,intValue},
                        {int.class,intValue},
                        {String.class,value},
                        {Boolean.class,Boolean.valueOf("true")},
                        {boolean.class,Boolean.valueOf("false")},
                        {BigDecimal.class,BigDecimal.valueOf(doubleValue)},
                        {BigInteger.class,BigInteger.valueOf(longValue)},
                        {Character.class,'a'},
                        {char.class,'a'},
                        {byte.class,byteValue},
                        {Byte.class,byteValue}
                };
    }

    @DataProvider(name = "invalid")
    public static Object[][]invalid()
    {
        final List<Class>classes = Arrays.asList(Long.class,Short.class,Double.class,Integer.class,
                BigDecimal.class,BigInteger.class,Byte.class,Boolean.class,Character.class);
        final List<Object[]>outer = new LinkedList<>();
        classes.forEach((_class) -> outer.add(new Object[]{_class}));
        return outer.toArray(new Object[outer.size()][1]);
    }

    @Test(dataProvider = "supported")
    public void testSupportedNumberTypes(Class _class, Boolean expected)
    {
        final Boolean actual = converter.isSupported(_class);
        validateEquality(expected,actual);
    }

    @Test(dataProvider = "conversions")
    public <T> void conversions(Class<T> _class, T expected)
    {
        final T actual = converter.convert(_class, String.valueOf(expected));
        validateNotNull(actual);
        validateEquality(expected,actual);
    }

    @Test(dataProvider = "boolean")
    public <T> void testBoolean(String value, Class<T> _class, T expected)
    {
        final T actual = converter.convert(_class, value);
        validateNotNull(actual);
        validateEquality(expected,actual);
    }

    @Test(dataProvider = "invalid",expectedExceptions = {IllegalArgumentException.class})
    public <T> void testInvalid(Class<T>_class)
    {
        final T actual = converter.convert(_class,"BAR");
        validateNull(actual);
        fail("IllegalArgumentException expected.");
    }
}
