package companyB.common.conversion;

import java.util.Arrays;
import java.util.List;

class ConverterValues
{
    final List<String> trueValues = Arrays.asList("y","yes","t","true","1");;
    final List<String> falseValues = Arrays.asList("n","no","f","false","0");;
    final List<Class> numberClasses = Arrays.asList(short.class, long.class, int.class,double.class);

    private static ConverterValues converterValues;

    private ConverterValues(){}

    static ConverterValues getInstance()
    {
        if(null == converterValues)
            converterValues = new ConverterValues();
        return converterValues;
    }
}
