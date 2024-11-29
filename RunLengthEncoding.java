public class RunLengthEncoding {

    public static String encode(String message) {
        StringBuilder encoded = new StringBuilder();
        int count = 1;
        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i) == message.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(count).append(message.charAt(i - 1));
                count = 1;
            }
        }
        encoded.append(count).append(message.charAt(message.length() - 1));
        return encoded.toString();
    }

    public static String decode(String encodedMessage) {
        StringBuilder decoded = new StringBuilder();
        StringBuilder count = new StringBuilder();

        for (char c : encodedMessage.toCharArray()) {
            if (Character.isDigit(c)) {
                count.append(c);
            } else {
                int repeat = Integer.parseInt(count.toString());
                decoded.append(String.valueOf(c).repeat(repeat));
                count.setLength(0);
            }
        }
        return decoded.toString();
    }
}
