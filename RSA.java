import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.math.BigDecimal;
import java.math.BigInteger;


public class RSA {
    private BigInteger p = RSA.generateLargeNum(); // Generate 1024 bit length numbers
    private BigInteger q = RSA.generateLargeNum();

    private BigInteger n = RSA.generateLargeNum().multiply(RSA.generateLargeNum());

    private BigInteger e = BigInteger.valueOf(65537); // below verifies e is relatively prime to (p-1)(q-1)
    private BigInteger x = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); 
    // System.out.println(RSA.euclid(x, BigInteger.valueOf(65537)));

    private BigInteger d = ((BigInteger.ONE).divide(e)).multiply(x);

    // calculates(a^b)modn
    public static BigInteger modularExponentiation(BigInteger a, BigInteger b, BigInteger n) {
        if (b == BigInteger.ZERO) {
            return BigInteger.ONE;
        }
        else if ((b.mod(BigInteger.valueOf(2))).equals(BigInteger.ZERO)) {
            BigInteger d = modularExponentiation(a, b.divide(BigInteger.valueOf(2)), n);
            return (d.multiply(d)).mod(n);
        }
        else {
            BigInteger d = modularExponentiation(a, b.subtract(BigInteger.ONE), n);
            return (a.multiply(d)).mod(n);
        }
    }

    public static BigInteger generateLargeNum() {
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(1024, secureRandom);
    }

    public static BigInteger generateLargeNumRange(BigInteger min, BigInteger max) {
        BigInteger bigNum = generateLargeNum();
        boolean found = false;

        while (!found) {
            if ((bigNum.compareTo(min) == 1) && (bigNum.compareTo(max) == -1)){
                found = true;
            }
            else {
                bigNum = generateLargeNum();
            }
        }
        return bigNum;
    }

    public static String millerRabin(BigInteger n, int s) {
        BigInteger a;
        int j;

        for (j = 1; j <= s; j++) {
            a = generateLargeNumRange(BigInteger.valueOf(2), n.subtract(BigInteger.valueOf(2)));
            if (witness(a, n)) {
                return "Definitely prime";
            }
        }
        System.out.println(j);
        System.out.println(n.mod(BigInteger.valueOf(2)));
        return "Almost surely prime";
    }

    public static boolean witness(BigInteger a, BigInteger n) {
        BigInteger u = n.subtract(BigInteger.ONE);
        int t = 1;

        while (u.mod(BigInteger.valueOf(2)) == BigInteger.ZERO) {
            u = u.divide(BigInteger.valueOf(2));
            t += 1;
        }
       
        BigInteger x = modularExponentiation(a, u, n);

        BigInteger x0; // holds prev value of x
        
        for (int i = 1; i <= t; i++) {
            x0 = x;
            x = (x0.pow(2)).mod(n);
            if ((x == BigInteger.ONE) && (x0 != BigInteger.ONE) && (x0 != n.subtract(BigInteger.ONE))) {
                return true;
            }
        }
        if ((x != BigInteger.ONE)) {
            return true;
        }
        return false;
    }

    public static BigInteger euclid(BigInteger a, BigInteger b) {
        if (b == BigInteger.ZERO) {
            return a;
        }
        else return euclid(b, a.mod(b));
    }

    public static int stringToInt(String message) {
        byte[] output3 = message.getBytes(StandardCharsets.UTF_8);
        return ByteBuffer.wrap(output3).getInt();
    }

    public static String intToString(int num) {
        byte[] bytes = {
            (byte)(num >> 24),
            (byte)(num >> 16),
            (byte)(num >> 8),
            (byte)num };
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public BigInteger rsaEncode (String message) {
        System.out.println(RSA.millerRabin(p, 10)); // check if prime
        System.out.println(RSA.millerRabin(q, 10));

        BigInteger m = BigInteger.valueOf(RSA.stringToInt(message)); // Convert message to number used for encryption

        return RSA.modularExponentiation(m, e, n);
    }

    public String rsaDecode(BigInteger num) {
        BigInteger decodedMessage = modularExponentiation(num, d, n);
        
        return intToString(decodedMessage.intValue());
    }
}