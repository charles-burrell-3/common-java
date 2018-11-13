package companyB.common.conversion;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Converts Strings representations into various supported datatypes.
 * The types supported are:
 * <ul>
 * <li>java.lang.Boolean</li>
 * <li>java.lang.String</li>
 * <li>java.lang.Integer</li>
 * <li>java.lang.Short</li>
 * <li>java.lang.Long</li>
 * <li>java.lang.Double</li>
 * <li>java.lang.Byte</li>
 * <li>java.lang.Character</li>
 * <li>java.math.BigDecimal</li>
 * <li>java.math.BigInteger</li>
 * <li>boolean</li>
 * <li>char</li>
 * <li>int</li>
 * <li>long</li>
 * <li>short</li>
 * <li><double/li>
 * <li>byte</li>
 * </ul>
 * The following values are supported for Booleans (case insensitive):
 * <ul>
 *     <li>
 *         True:
 *         <ul>
 *             <li>true</li>
 *             <li>t</li>
 *             <li>yes</li>
 *             <li>y</li>
 *             <li>1</li>
 *         </ul>
 *     </li>
 *     <li>
 *         False:
 *         <ul>
 *             <li>false</li>
 *             <li>f</li>
 *             <li>no</li>
 *             <li>n</li>
 *             <li>0</li>
 *         </ul>
 *     </li>
 * </ul>
 * @author Charles Burrell (deltafront@gmail.com)
 */
@SuppressWarnings("ALL")
public class Converter
{
    private final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
    private final List<Class> numberClasses;
    private final Map<Class,Function<String,Object>>converterFunctionsMappings;

    /**
     * Default constrictor for class.
     */
    public Converter()
    {
        final ConverterValues converterValues = ConverterValues.getInstance();
        final ConverterFunctionsMappings converterFunctionsMappingsInstance =
                ConverterFunctionsMappings.getInstance();
        numberClasses = converterValues.numberClasses;
        converterFunctionsMappings = converterFunctionsMappingsInstance.converterFunctionsMappings;
    }

    /**
     * Returns whether or not the indicated class is supported.
     * @param c Class to be evaluated.
     * @return Whether or not the indicated class is supported.
     */
    public boolean isSupported(Class c)
    {
        Validate.notNull(c,"Class is required.");
        return converterFunctionsMappings.containsKey(c);
    }

    /**
     *
     * @param classType Target class type of conversion.
     * @param value String value to be converted.
     * @param <T> Generic Target type.
     * @return String value of type T.
     */
    public  <T>T convert(Class<T>classType, String value)
    {
        validateClassIsPresent(value, classType);
        T out = null;
        try
        {
            out = (T) converterFunctionsMappings.get(classType).apply(value);
        }
        catch (Exception e)
        {
            final String message = String.format("'%s' is an invalid value for type '%s': {}",value,classType.getCanonicalName());
            LOGGER.error("{}: {}",message,e.getMessage(),e);
            throw new IllegalArgumentException(message,e);
        }
        return out;
    }

    private <T> void validateClassIsPresent(String value, Class<T> classType)
    {
        Validate.notNull(value,"Class is required.");
        Validate.notNull(classType,"Class type is required.");
    }
}
