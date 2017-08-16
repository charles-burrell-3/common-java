package companyB.common.encrypted;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Decorator used to mark String fields in a class as being eligible for Encryption
 * @author Charles Burrell (deltafront@gmail.com)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypted
{
    EncryptionAlgs algorithm();
}
