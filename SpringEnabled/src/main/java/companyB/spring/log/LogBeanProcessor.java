package companyB.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * Spring based processor for Auto-injecting SLF4J Log fields.
 * @author Charles Burrell (deltafront@gmail.com)
 * @see org.springframework.beans.factory.config.BeanPostProcessor
 */
public class LogBeanProcessor implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException
    {

        final Field[]fields = o.getClass().getDeclaredFields();
        try
        {
            for(final Field field : fields)
            {
                if(Logger.class.equals(field.getType()))
                {
                    final Log log = field.getAnnotation(Log.class);
                    if(null != log)
                    {
                        final String name = getName(log,o);
                        final Logger logger = LoggerFactory.getLogger(name);
                        field.setAccessible(true);
                        field.set(o,logger);
                    }
                }
            }
        }
        catch (IllegalAccessException e)
        {
            throw new _BeansException(e.getMessage(),e);
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException
    {
        return o;
    }
    private String getName(Log log, Object instance)
    {
       return "".equals(log.name()) ?
               instance.getClass().getCanonicalName() : log.name();
    }
}
class _BeansException extends BeansException
{
    public _BeansException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
