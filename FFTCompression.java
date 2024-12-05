import java.util.*;

public class FFTCompression {

    public static String compress(String message, double compressionRate) {
        // Map each character to its ASCII value
        Map<Character, Double> charToFrequency = new HashMap<>();
        for (char c : message.toCharArray()) {
            charToFrequency.putIfAbsent(c, (double) c);
        }

        // Build a frequency sequence corresponding to the message
        List<Double> frequencySequence = new ArrayList<>();
        for (char c : message.toCharArray()) {
            frequencySequence.add(charToFrequency.get(c));
        }

        // Count the frequency occurrence
        Map<Double, Integer> frequencyCounts = new HashMap<>();
        for (Double freq : frequencySequence) {
            frequencyCounts.put(freq, frequencyCounts.getOrDefault(freq, 0) + 1);
        }

        // Sort frequencies by occurrence in descending order
        List<Double> sortedFrequencies = new ArrayList<>(frequencyCounts.keySet());
        sortedFrequencies.sort((a, b) -> frequencyCounts.get(b) - frequencyCounts.get(a));

        // Apply compression rate to determine how lossy the compression should be
        int numFrequenciesToRemove = (int) Math.ceil(sortedFrequencies.size() * compressionRate);

        // Get the frequencies to remove
        Set<Double> frequenciesToRemove = new HashSet<>(sortedFrequencies.subList(0, numFrequenciesToRemove));

        // Reconstruct message back into string
        StringBuilder compressedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            double freq = charToFrequency.get(c);
            if (!frequenciesToRemove.contains(freq)) {
                compressedMessage.append(c);
            }
        }

        return compressedMessage.toString();
    }
}
