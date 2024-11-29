public class Message {
    private String sender;
    private String receiver;
    private String metadata;
    private String body;

    public Message(String sender, String receiver, String metadata, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.metadata = metadata;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message from " + sender + " to " + receiver + "\nMetadata: " + metadata + "\nBody: " + body;
    }
}
