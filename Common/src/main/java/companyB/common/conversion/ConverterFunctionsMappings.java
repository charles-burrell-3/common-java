package companyB.common.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

class ConverterFunctionsMappings
{
    private static  ConverterFunctionsMappings converterFunctionsMappingsInstance;

    final  Map<Class,Function<String,Object>> converterFunctionsMappings;

    private ConverterFunctionsMappings()
    {
        final ConverterValues converterValues = ConverterValues.getInstance();
        converterFunctionsMappings = new HashMap<>();
        final List<String> trueValues = converterValues.trueValues;
        final List<String> falseValues = converterValues.falseValues;
        populateMappings(trueValues, falseValues);
    }

    private void populateMappings(List<String> trueValues, List<String> falseValues)
    {
        populateNumberFunctions();
        populateStringFunctions();
        populateBigAndByteFunctions();
        populateBooleanFunctions(trueValues, falseValues);
        populateCharacterFunctions();
    }

    static ConverterFunctionsMappings getInstance()
    {
        if (null == converterFunctionsMappingsInstance)
            converterFunctionsMappingsInstance = new ConverterFunctionsMappings();
        return converterFunctionsMappingsInstance;
    }

    private void populateBigAndByteFunctions()
    {
        populateMappingWithFunction(BigDecimal::new,BigDecimal.class);
        populateMappingWithFunction(BigInteger::new,BigInteger.class);
        populateMappingWithFunction(Byte::parseByte,Byte.class,byte.class);
    }

    private void populateNumberFunctions()
    {
        populateMappingWithFunction(Long::parseLong,Long.class,long.class);
        populateMappingWithFunction(Integer::parseInt,Integer.class,int.class);
        populateMappingWithFunction(Short::parseShort,Short.class,short.class);
        populateMappingWithFunction(Double::parseDouble,Double.class,double.class);
    }

    private void populateStringFunctions()
    {
        populateMappingWithFunction((value)->value, String.class);
    }

    private void populateCharacterFunctions()
    {
        populateMappingWithFunction(characterFunction,Character.class,char.class);
    }

    private void populateMappingWithFunction(Function<String,Object>function, Class...classes)
    {
        Arrays.asList(classes).forEach((_class)-> converterFunctionsMappings.put(_class,function));
    }

    @SuppressWarnings("ConstantConditions")
    private void populateBooleanFunctions(List<String>trueValues, List<String>falseValues)
    {
        Arrays.asList(boolean.class, Boolean.class).forEach(_class ->
                converterFunctionsMappings.put(_class, (value) ->
                        trueValues.contains(toLower(value)) ? true : falseValues.contains(toLower(value)) ? false : null));
    }

    private String toLower(String value)
    {
        return value.toLowerCase(Locale.getDefault());
    }

    private Function<String,Object>characterFunction = (value)->
    {
        if(value.length() > 1)
            throw new IllegalArgumentException(String.format("'%s' is not a valid character.",value));
        return value.charAt(0);
    };
}
