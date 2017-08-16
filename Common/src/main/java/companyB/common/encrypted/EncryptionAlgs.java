package companyB.common.encrypted;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.function.Function;

/**
 * Enumeration of all available Encryption Algorithms.
 * @author Charles Burrell (deltafront@gmail.com)
 */
public enum  EncryptionAlgs
{
    /**
     * MD2 Algo.
     * @see org.apache.commons.codec.digest.DigestUtils#md2Hex(String)
     **/
    MD2(DigestUtils::md2Hex),
    /**
     * MD5 Algo.
     * @see org.apache.commons.codec.digest.DigestUtils#md5Hex(String) (String)
     **/
    MD5(DigestUtils::md5Hex),
    /**
     * SHA1 Algo.
     * @see org.apache.commons.codec.digest.DigestUtils#sha1Hex(String)
     **/
    SHA1(DigestUtils::sha1Hex),
    /**
     * SHA256 Algo.
     * @see org.apache.commons.codec.digest.DigestUtils#sha256Hex(String)
     **/
    SHA256(DigestUtils::sha256Hex),
    /**
     * SHA512 Algo.
     * @see org.apache.commons.codec.digest.DigestUtils#sha512Hex(String)
     **/
    SHA512(DigestUtils::sha512Hex);
    public final Function<String,String>encryptionFunction;

    EncryptionAlgs(Function<String,String>encryptionFunction)
    {
        this.encryptionFunction = encryptionFunction;
    }
}
