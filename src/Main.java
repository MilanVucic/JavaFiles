import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // this -> [is->1]
        String input = "this is this is a test is testing this test please";
        String[] words = input.split(" ");
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i].toLowerCase();
            String nextWord = words[i + 1].toLowerCase();
            if (map.containsKey(currentWord)) {
                Map<String, Integer> tempMap = map.get(currentWord);
                if (tempMap.containsKey(nextWord)) {
                    int count = tempMap.get(nextWord);
                    tempMap.put(nextWord, count + 1);
                } else {
                    tempMap.put(nextWord, 1);
                }
            } else {
                Map<String, Integer> tempMap = new HashMap<>();
                tempMap.put(nextWord, 1);
                map.put(currentWord, tempMap);
            }
        }
        System.out.println(map);
    }
}