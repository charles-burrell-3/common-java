package companyB.spring.enabled.test;

import companyB.spring.log.Log;
import companyB.spring.log.LogBeanProcessor;
import org.slf4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

@Test(groups = {"unit","spring.enabled","log.bean.processor"})
public class LogEnabledTest
{
    private LogBeanProcessor logBeanProcessor;
    private TestLoggerClass testClass;
    private Logger fooLogger;
    private Logger defaultLogger;
    private Object nullLogger;

    @BeforeMethod
    public void before()
    {
        logBeanProcessor = new LogBeanProcessor();
        testClass = new TestLoggerClass();
    }

    public void testBefore()
    {
        doSetup(false);
        assertNotNull(fooLogger);
        assertNotNull(defaultLogger);
        assertEquals(fooLogger.hashCode(),defaultLogger.hashCode());
        assertNull(nullLogger);
    }

    public void testAfter()
    {
        doSetup(true);
        assertNull(fooLogger);
        assertNull(defaultLogger);
        assertNull(nullLogger);
    }

    private void doSetup(boolean initAfter)
    {
        testClass = initAfter?
                (TestLoggerClass)logBeanProcessor.postProcessAfterInitialization(testClass,"testClass"):
                (TestLoggerClass)logBeanProcessor.postProcessBeforeInitialization(testClass,"testClass");
        assertNotNull(testClass);
        fooLogger = testClass.fooLogger;
        defaultLogger = testClass.defaultLogger;
        nullLogger = testClass.nullLogger;
    }
}
class TestLoggerClass
{
    @Log(name = "foo")
    public Logger fooLogger;

    @Log()
    public Logger defaultLogger;

    @Log
    public Object nullLogger;
}
