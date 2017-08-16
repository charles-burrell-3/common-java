package companyB.common.encrypted.test;

import companyB.common.encrypted.Encrypted;
import companyB.common.encrypted.EncryptedDecorator;
import companyB.common.encrypted.EncryptionAlgs;
import companyB.common.test.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BiConsumer;

import static junit.framework.TestCase.fail;

public class EncryptedDecoratorTest extends TestBase
{
    @DataProvider(name = "data")
    public static Object[][]data()
    {
        return new Object[][]
                {
                        {new validClass(),true,false},
                        {new noDecoratorClass(),false,false},
                        {new invalidDecoratorClass(),false,true},
                        {new illegalStateClass(),false,false}
                };
    }

    @Test(dataProvider = "data",groups = {"unit","encrypted"})
    public void test(testClass testClass, Boolean encryptedExpected, Boolean exceptionThrown)
    {
        final EncryptedDecorator decorator = new EncryptedDecorator();
        try
        {
            final String oldFoo = testClass.foo();
            final String oldBar = testClass.bar();
            decorator.decorate(testClass);
            if(exceptionThrown)
                fail("Exception expected");
            final String newFoo = testClass.foo();
            final String newBar = testClass.bar();
            final BiConsumer<String,String>validation = (encryptedExpected) ? this::validateInEquality : this::validateEquality;
            validation.accept(oldBar,newBar);
            validation.accept(oldFoo,newFoo);

        }
        catch (IllegalArgumentException e)
        {
            if(!exceptionThrown)
                fail("Exception not expected.");
        }
    }

}

interface testClass
{
    String foo();
    String bar();
}
class validClass implements testClass
{
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    String foo = "FOO";
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    String bar = "BAR";


    @Override
    public String foo()
    {
        return foo;
    }

    @Override
    public String bar()
    {
        return bar;
    }
}

class noDecoratorClass implements testClass
{
    String foo = "Foo";
    String bar = "Bar";

    @Override
    public String foo()
    {
        return foo;
    }

    @Override
    public String bar()
    {
        return bar;
    }
}

class invalidDecoratorClass implements testClass
{
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    Integer foo;
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    Integer bar;
    @Override
    public String foo()
    {
        return String.valueOf(foo);
    }

    @Override
    public String bar()
    {
        return String.valueOf(bar);
    }
}

class illegalStateClass implements testClass
{
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    final String foo = "FOO";
    @Encrypted(algorithm = EncryptionAlgs.MD2)
    final String bar = "BAR";

    @Override
    public String foo()
    {
        return foo;
    }

    @Override
    public String bar()
    {
        return bar;
    }
}