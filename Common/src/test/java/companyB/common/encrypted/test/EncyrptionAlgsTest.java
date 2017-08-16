package companyB.common.encrypted.test;

import companyB.common.encrypted.EncryptionAlgs;
import companyB.common.test.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
@Test(groups = {"unit","encrypted"})
public class EncyrptionAlgsTest extends TestBase
{
    @DataProvider(name = "algs")
    public static Object[][]algs()
    {
        final List<Object[]>objects = new LinkedList<>();
        Arrays.asList(EncryptionAlgs.values()).forEach((alg)->objects.add(new Object[]{alg}));
        return objects.toArray(new Object[objects.size()][1]);
    }

    @Test(dataProvider = "algs",groups = {"unit","encrypted"})
    public void testEcryptionAlg(EncryptionAlgs encryptionAlg)
    {
        final String plainText = "FOO";
        final String encrypted = encryptionAlg.encryptionFunction.apply(plainText);
        validateNotNull(encrypted);
        validateNotSame(plainText,encrypted);
    }
}
