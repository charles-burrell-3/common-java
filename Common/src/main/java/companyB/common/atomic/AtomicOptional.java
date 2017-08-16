package companyB.common.atomic;

import java.util.Optional;

/**
 * A class for holding Optional containers whose contents may change but whose basic contract will not. In other words,
 * immutability for the Optional itself is guaranteed, but not that of its value.
 * @param <T> Type of the value being held by the Optional.
 * @author Charles A. Burrell (deltafront@gmail.com)
 */

public class AtomicOptional<T>
{

    private volatile T optionalValue;

    /**
     * Default constructor for class.
     * @param optionalValue Initial value of the optional.
     */
    public AtomicOptional(T optionalValue)
    {
        this.optionalValue = optionalValue;
    }

    /**
     * Returns the value held within the Optional. If the underlying value is null, then this method will return Optional.empty().
     * @return Value held within this Optional.
     */
    public Optional<T>get()
    {
        return Optional.ofNullable(this.optionalValue);
    }

    /**
     * Resets the value that is held within the optional.
     * @param optionalValue New value that is to be contained within the optional.
     */
    public void set(T optionalValue)
    {
        this.optionalValue = optionalValue;
    }

    /**
     * Resets the value that is held within the optional, returning the original.
     * If the underlying value is null, then this method will return Optional.empty().
     * @param optionalValue New value that is to be contained within the optional.
     * @return Value held within this Optional.
     */
    public Optional<T>getAndSet(T optionalValue)
    {
        final Optional<T>result = Optional.ofNullable(this.optionalValue);
        this.optionalValue = optionalValue;
        return result;
    }
}
