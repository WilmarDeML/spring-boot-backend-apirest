package com.wilmardeml.springboot.backend.apirest.auth;

public class JwtConfig {
    public  static final String LLAVE_SECRETA = "clave.secreta.123456789";

    public static final String RSA_PRIVADA = """
            -----BEGIN RSA PRIVATE KEY-----
            MIIEpAIBAAKCAQEAoxLTuxvk8JB/iicjNEkErc0OapYvfu+vOx5BgSBEkd/59ifd
            TVFH1S5Oz0gC/IdJ1QGzPs0jHF/93Yy5RAIzybRiHCO/UVoodTjtoydLRnfp0ZqL
            LlTFbqNC/dqJNeK6+D1E3NAfqJzWhoi4ONqa8LiBrRgufws6jLNtSSVWVN8j9nZg
            pvFBLIC/mZcNMnPG5W8KzzxFMaIs0QUYXwMlq7goa2+2+EsL2lVlXxZECIroG29H
            ED42+T+g4a+tSkw8aakJoJzVnkYEx8QXsl3T2ZJ8Qe8WD/E1fGm2liIL1BHAdnTY
            l+LR4SEvp/kqfoAJlM7iunqTDwvHQXgd0T0AewIDAQABAoIBAEFdQYslUi3u7YBV
            0bGadwqnP9kPW/hYYHek2ALHCVUjXtEBB55/LvjPN/2z0qagH8MGjG9m0GUGQP61
            8mjFLTbzW8QFvCECr2bVK8OS84RKemlutc0bY4vGWwrfttHnkKQCAwEzVw1B2GdS
            k+0OCr7kZe4Y3EJGIPDW1r5T/ffEWZt+YCyNlh6a2i6aKEgtJKoPpLT5isIIX+Pa
            /5yy2PPQDSrEL+BsZnRjNatQ/xSFgAUv3wTybAizsSsSWtHXM991BajRDxGIe7wC
            dmcQjxQOdVZLm7Y1gzWoRZmAwWuZ/6WCvex4hQsVVou72rRvb+/cuBJY9T5SkdnV
            2HE/4pkCgYEAzgTuU8XbnDaUQeAjiDRM6IoeR0vNVWT/IDNkD3BC4+12QvN2+bei
            tGbk1/3ffD0cB9O2AKRF6ynkoSnRGIPCkS9abWWJVTBWl2Wq4Ec8FiLr5vJerJMe
            +VUKMR6mmgpNnByko3KHnYaozvWOY8DbxV1pVyca/HcBZpssCxBM8z8CgYEAyqKz
            n/nUjHz+iqHuthiirzxt9oYwWFd1MsG3kj9KqdEdVQPzN5bmqgZxAn50BqWgFbAH
            bjuybpv9klvFYXvl6UKU9e2m/RN+RYybDyfIP5Le0ddnnjipBJWUDlAUERntWkHM
            Csfp63jSvXFJASbF2uBQ1khfgSlEaMXz9AuS78UCgYEAlM2q9ocqoCIr44jpIS/l
            fPCF/ikaHlvcin2UADKtncOeb606ehPRSUwobnnIOgxJer3H7563gau4n09836AL
            oKH7RpjYINfRM3kTevr4w2Rlz2GwUCXTcXOi8OeanhhjBicKI6RNn9Hri9CBEDv0
            gRF/TJvKOiAauunJXhRgADECgYEAqLeVheK3BY5hRb6Uikuy0OMoz7Vk9XlEXNGM
            bhmCqigyGwIh9SwQvIqGd8QgCRTIWKZrWW4dIBmDpOQCUSue/3vvmWCZxGgBoBj1
            VkYSZlR8cA3A+a1FlUqRruUgACktRhQ4ttVDQEsmdJOpbGsDHD3fMLurwVhB3FbN
            y2oDmt0CgYBuq5WlEn/GcZuP7MTvvqfmwQLLiBlCMVDUW93DqNGBaYu28lDcR4HX
            PFyIXxF/Jo2AhlOmBcwiuRq9t9W6G9opWLfBLxuQUERw7faHxntxxsozHGIGydcS
            bXidj8eDnJf2OttnLC1eXidHyDbXEmU7Km4iNXb+GOKI7msXk9HJGQ==
            -----END RSA PRIVATE KEY-----""";

    public static final String RSA_PUBLIC = """
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoxLTuxvk8JB/iicjNEkE
            rc0OapYvfu+vOx5BgSBEkd/59ifdTVFH1S5Oz0gC/IdJ1QGzPs0jHF/93Yy5RAIz
            ybRiHCO/UVoodTjtoydLRnfp0ZqLLlTFbqNC/dqJNeK6+D1E3NAfqJzWhoi4ONqa
            8LiBrRgufws6jLNtSSVWVN8j9nZgpvFBLIC/mZcNMnPG5W8KzzxFMaIs0QUYXwMl
            q7goa2+2+EsL2lVlXxZECIroG29HED42+T+g4a+tSkw8aakJoJzVnkYEx8QXsl3T
            2ZJ8Qe8WD/E1fGm2liIL1BHAdnTYl+LR4SEvp/kqfoAJlM7iunqTDwvHQXgd0T0A
            ewIDAQAB
            -----END PUBLIC KEY-----""";
}
