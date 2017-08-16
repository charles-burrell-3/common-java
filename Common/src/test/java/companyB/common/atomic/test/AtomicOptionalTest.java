package companyB.common.atomic.test;


import companyB.common.atomic.AtomicOptional;
import companyB.common.test.TestBase;
import org.testng.annotations.Test;

import java.util.Optional;

@SuppressWarnings({"ConstantConditions", "OptionalUsedAsFieldOrParameterType"})
@Test(groups = {"unit","common","utils","atomic"})
public class AtomicOptionalTest extends TestBase
{

    private String expected = "Foo";

    public void getOriginalValue()
    {
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(expected);
        final Optional<String>actual = atomicOptional.get();
        validateIsPresentAndMatches(expected, actual);
    }

    public void getEmptyValue()
    {
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(null);
        final Optional<String>actual = atomicOptional.get();
        validateIsEmpty(actual);
    }


    public void getSetValue()
    {
        final String newValue = "Bar";
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(expected);
        atomicOptional.set(newValue);
        final Optional<String>actual = atomicOptional.get();
        validateIsPresentAndMatches(newValue, actual);
    }

    public void setToNull()
    {
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(expected);
        atomicOptional.set(null);
        final Optional<String>actual = atomicOptional.get();
        validateIsEmpty(actual);
    }

    public void getUpdatedValue()
    {
        final String newValue = "Bar";
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(expected);
        final Optional<String>actual = atomicOptional.getAndSet(newValue);
        validateIsPresentAndMatches(expected, actual);
        final Optional<String>newActual = atomicOptional.get();
        validateIsPresentAndMatches(newValue,newActual);
    }

    public void setUpdatedToNull()
    {
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(expected);
        final Optional<String>actual = atomicOptional.getAndSet(null);
        validateIsPresentAndMatches(expected, actual);
        final Optional<String>newActual = atomicOptional.get();
        validateEquality(Optional.empty(),newActual);
    }

    public void getAndSetUpdatedToNull()
    {
        final AtomicOptional<String>atomicOptional = new AtomicOptional<>(null);
        final Optional<String>actual = atomicOptional.getAndSet("42");
        validateFalse(actual.isPresent());
    }

    private void validateIsEmpty(Optional<String> actual)
    {
        validateFalse(actual.isPresent());
        validateEquality(Optional.empty(),actual);
    }

    private void validateIsPresentAndMatches(String newValue, Optional<String> actual)
    {
        validateTrue(actual.isPresent());
        validateEquality(newValue,actual.get());
    }
}
