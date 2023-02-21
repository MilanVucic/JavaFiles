import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String input = "ovo je test za ovo za ovo za ovo za ovo sto je testirano za nas za nas ipak";
        String[] words = input.split(" ");
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i].toLowerCase();
            String nextWord = words[i + 1].toLowerCase();
            if (map.containsKey(word)) {
                Map<String, Integer> temp = map.get(word);
                if (temp.containsKey(nextWord)) {
                    temp.put(nextWord, temp.get(nextWord) + 1);
                } else {
                    temp.put(nextWord, 1);
                }
            } else {
                HashMap<String, Integer> tempMap = new HashMap<>();
                tempMap.put(nextWord, 1);
                map.put(word, tempMap);
            }
        }
        System.out.println(map);
    }
}