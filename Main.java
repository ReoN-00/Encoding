public class Main {
    public static void main(String[] args) {
        // Create graph
        Graph network = new Graph();
        network.addPerson("John");
        network.addPerson("Bob");
        network.addPerson("Alice");
        network.addConnection("John", "Bob");
        network.addConnection("Alice", "Bob");

<<<<<<< HEAD
        // Send message
        String originalMessage = "AAABBBCCDAA";
        String compressedMessage = RunLengthEncoding.encode(originalMessage);
        
=======
        // Task 1: Run-Length Encoding Test
        System.out.println("--- Task 1: Run-Length Encoding Test ---");
        String originalMessageRLE = "AAABBBCCDAA";
        System.out.println("Original Message (RLE): " + originalMessageRLE);

        String compressedMessageRLE = RunLengthEncoding.encode(originalMessageRLE);
        System.out.println("Compressed Message (RLE): " + compressedMessageRLE);

>>>>>>> 875928bc76b23086ee7349204142a05458533bfd
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
<<<<<<< HEAD
        } 
=======
        }
>>>>>>> 875928bc76b23086ee7349204142a05458533bfd

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
    }
}
