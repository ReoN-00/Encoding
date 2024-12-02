import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.math.BigInteger;


public class RSA {

    public static BigInteger modularExponentiation(BigInteger a, BigInteger b, BigInteger n) {
        if (b == BigInteger.ZERO) {
            return BigInteger.ONE;
        }
        else if ((b.mod(BigInteger.TWO)) == BigInteger.ZERO) {
            BigInteger d = modularExponentiation(a, b.divide(BigInteger.TWO), n);
            return (d.multiply(d)).mod(n);
        }
        else {
            BigInteger d = modularExponentiation(a, b.subtract(BigInteger.ONE), n);
            return (a.multiply(d)).mod(n);
        }
    }

    public static BigInteger generateLargeNum() {
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(2048, secureRandom);
    }

    public static BigInteger random(BigInteger min, BigInteger max) {
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

        for (int j = 1; j <= s; j++) {
            a = random(BigInteger.valueOf(2), n);
            if (witness(a, n)) {
                return "Definitely prime";
            }
        }
        return "Almost surely prime";
    }

    public static boolean witness(BigInteger a, BigInteger n) {
        BigInteger d = n.subtract(BigInteger.ONE);
        int t = 0;

        while (d.mod(BigInteger.TWO) == BigInteger.ZERO) {
            d = d.divide(BigInteger.TWO);
            t += 1;
        }

        //System.out.println(d);
        //System.out.println(t);

        BigInteger x = modularExponentiation(a, d, n);
        //System.out.println(x);

        BigInteger x0; 

        for (int i = 1; i < t + 1; i++) {
            x0 = x;
            x = (x.pow(2)).mod(n);
            if ((x == BigInteger.ONE) && (x0 != BigInteger.ONE) && (x0 != n.subtract(BigInteger.ONE))) {
                System.out.println("BINGO");
                return true;
            }
        }
        if (x != BigInteger.ONE) {
            return true;
        }
        return false;
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

    public static void main(String[] args) {
        String message = "Test";
        int i = stringToInt(message);
        // System.out.println(i);
        // System.out.println(intToString(i));

        BigInteger x = generateLargeNum();
        // System.out.println(x);

        //System.out.println(witness(BigInteger.valueOf(2), BigInteger.valueOf(561)));

        System.out.println(millerRabin(x, 5));
    }
}

