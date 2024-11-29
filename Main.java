public class Main {
    public static void main(String[] args) {
        // Create graph
        Graph network = new Graph();
        network.addPerson("John");
        network.addPerson("Bob");
        network.addConnection("John", "Bob");

        // Send message
        String originalMessage = "AAABBBCCDAA";
        String compressedMessage = RunLengthEncoding.encode(originalMessage);

        if (network.canSendMessage("John", "Bob")) {
            Message message = new Message(
                "John", 
                "Bob", 
                "Encoding: RLE, Original Length: " + originalMessage.length(), 
                compressedMessage
            );
            System.out.println(message);

            // Decode message
            String decodedMessage = RunLengthEncoding.decode(compressedMessage);
            System.out.println("\nDecoded Message: " + decodedMessage);
        } else {
            System.out.println("Cannot send message. Receiver not found in network.");
        }
    }
}
