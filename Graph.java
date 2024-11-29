import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList = new HashMap<>();

    public void addPerson(String person) {
        adjacencyList.putIfAbsent(person, new ArrayList<>());
    }

    public void addConnection(String person1, String person2) {
        adjacencyList.get(person1).add(person2);
        adjacencyList.get(person2).add(person1);
    }

    public boolean canSendMessage(String sender, String receiver) {
        return adjacencyList.containsKey(sender) && adjacencyList.containsKey(receiver);
    }
}
