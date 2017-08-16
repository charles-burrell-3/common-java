package companyB.common.encrypted;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class receives an instance of a Class and encrypts the value of any String field annotated with the @Encrypted Annotation
 * using the Encryption Algorithm specified.
 * @see companyB.common.encrypted.Encrypted
 * @see companyB.common.encrypted.EncryptionAlgs
 * @author Charles Burrell (deltafront@gmail.com)
 */
public class EncryptedDecorator
{
    /**
     *
     * @param instance
     */
    public void decorate(Object instance)
    {
        final List<Field>declaredFields = getDeclaredFields(instance);
        final List<Field>annotatedFields = getAnnotatedFields(declaredFields);
        annotatedFields.forEach((field)-> decorateField(field,instance));
    }

    private void decorateField(Field field,Object instance)
    {
        try
        {
            final String encrypted = getEncryptedValue(field, instance);
            setValue(encrypted,field,instance);
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalStateException(String.format("%s.%s was not decorated with encrypted value.",
                    instance.getClass().getCanonicalName(),field.getName()));
        }

    }

    private String getEncryptedValue(Field field, Object instance) throws IllegalAccessException
    {
        final String plainText = getValue(field,instance);
        final EncryptionAlgs encryptionAlg = getEncryptedAlgorithm(field);
        return encrypt(plainText,encryptionAlg);
    }

    private EncryptionAlgs getEncryptedAlgorithm(Field field)
    {
        return field.getAnnotation(Encrypted.class).algorithm();
    }

    private String encrypt(String plainText, EncryptionAlgs encryptionAlg)
    {
        return encryptionAlg.encryptionFunction.apply(plainText);
    }

    private void setValue(String encrypted, Field field, Object instance) throws IllegalAccessException
    {
        field.setAccessible(true);
        field.set(instance,encrypted);
    }

    private String getValue(Field field, Object instance) throws IllegalAccessException
    {
        field.setAccessible(true);
        return String.valueOf(field.get(instance));
    }

    private List<Field>getDeclaredFields(Object instance)
    {
        return Arrays.asList(instance.getClass().getDeclaredFields());
    }

    private List<Field> getAnnotatedFields(List<Field>annotatedFields)
    {
        return annotatedFields.stream().filter((field)->fieldPredicate.test(field)).collect(Collectors.toList());
    }

    private Predicate<Field>fieldPredicate = (field) -> null != field.getAnnotation(Encrypted.class);
}
