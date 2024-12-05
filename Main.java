import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // Create graph
        Graph network = new Graph();
        network.addPerson("John");
        network.addPerson("Bob");
        network.addPerson("Alice");
        network.addConnection("John", "Bob");
        network.addConnection("Alice", "Bob");

        // Task 1: Run-Length Encoding Test
        System.out.println("--- Task 1: Run-Length Encoding Test ---");
        String originalMessageRLE = "AAABBBCCDAA";
        System.out.println("Original Message (RLE): " + originalMessageRLE);

        String compressedMessageRLE = RunLengthEncoding.encode(originalMessageRLE);
        System.out.println("Compressed Message (RLE): " + compressedMessageRLE);

        if (network.canSendMessage("John", "Bob")) {
            Message rleMessage = new Message(
                "John",
                "Bob",
                "Encoding: RLE, Original Length: " + originalMessageRLE.length(),
                compressedMessageRLE
            );
            System.out.println("\nMessage Sent:");
            System.out.println(rleMessage);

            // Decode message
            String decodedMessageRLE = RunLengthEncoding.decode(compressedMessageRLE);
            System.out.println("Decoded Message (RLE): " + decodedMessageRLE);
        } else {
            System.out.println("Cannot send message. Receiver not found in network.");
        }

        // Task 2: FFT Compression Test
        System.out.println("\n--- Task 2: FFT Compression Test ---");
        String originalMessageFFT = "This is a test message for FFT compression.";
        double compressionRate = 0.1; // parameter for compression rate
        System.out.println("Original Message (FFT): " + originalMessageFFT);

        if (network.canSendMessage("Alice", "Bob")) {
            String compressedMessageFFT = FFTCompression.compress(originalMessageFFT, compressionRate);
            System.out.println("Compressed Message (FFT): " + compressedMessageFFT);

            Message fftMessage = new Message(
                "Alice",
                "Bob",
                "Encoding: FFT, Original Length: " + originalMessageFFT.length() + ", Compression Rate: " + compressionRate,
                compressedMessageFFT
            );
            System.out.println("\nMessage Sent:");
            System.out.println(fftMessage);
        } else {
            System.out.println("Cannot send message. Receiver not found in network.");
        }

        // Task 3: Encrypt Message with RSA Encrption
        System.out.println("--- Task 3: RSA Encryption Test ---");
        String originalMessageRSA = "test";
        System.out.println("Original Message (RSA): " + originalMessageRSA);
 
        BigInteger m = BigInteger.valueOf(RSA.stringToInt(originalMessageRSA)); // Convert message to number used for encryption

        BigInteger p = RSA.generateLargeNum(); // Generate 1024 bit length numbers
        BigInteger q = RSA.generateLargeNum();
        System.out.println(RSA.millerRabin(p, 10)); // check if prime
        System.out.println(RSA.millerRabin(q, 10));

        BigInteger n = p.multiply(q);

        BigInteger e = BigInteger.valueOf(65537); // below verifies e is relatively prime to (p-1)(q-1)
        BigInteger x = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); 
        // System.out.println(RSA.euclid(x, BigInteger.valueOf(65537)));

        BigInteger d = ((BigInteger.ONE).divide(e)).multiply(x);

        BigInteger c = RSA.modularExponentiation(m, e, n);
    
        BigInteger s = RSA.modularExponentiation(c, d, n);
        
        if (network.canSendMessage("John", "Bob")) {
            Message rsaMessage = new Message(
                "John",
                "Bob",
                "Encoding: RSA, Original Length: " + originalMessageRLE.length(),
                c.toString()
            );
            System.out.println("\nMessage Sent:");
            System.out.println(rsaMessage);

            // Decode message
            System.out.println("Decoded Message (RLE): " + s);
        } else {
            System.out.println("Cannot send message. Receiver not found in network.");
        }
    }
}
