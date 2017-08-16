package companyB.common.test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class TestBase
{
    protected void validateEquality(Object expected, Object actual)
    {
        assertThat(actual,is(equalTo(expected)));
    }

    protected void validateInEquality(Object notExpected, Object actual)
    {
        assertThat(actual,is(not(notExpected)));
    }

    protected void validateNull(Object instance)
    {
        assertThat(instance,is(nullValue()));
    }

    protected void validateNotNull(Object instance)
    {
        assertThat(instance,is(not(nullValue())));
    }

    protected void validateTrue(Boolean condition)
    {
        assertThat(condition,is(true));
    }

    protected void validateFalse(Boolean condition)
    {
        assertThat(condition,is(false));
    }

    protected void validateNotSame(Object one, Object two)
    {
        assertThat(one.hashCode(),is( not(equalTo(two.hashCode()))));
    }

    protected <T> void validateAnyEquals(T[]expecteds, T actual)
    {
        final long count = Arrays.asList(expecteds).stream().filter((expected)->expected.equals(actual)).count();
        assertThat(true,is(count > 0));
    }
}
