import java.util.*;

public class Main {
    public static void main(String[] args) {
        // this -> [is->1]
        String input = "this is this could this is this is this is a" +
                " test is testing this test please this test this something this something this something";
        String[] words = input.split(" ");
        Map<String, Map<String, Integer>> map = createWordUsageMap(words);
        System.out.println(map);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a word?");
            String word = scanner.next();
            if (word.equalsIgnoreCase("exit")) {
                break;
            }
            Map<String, Integer> wordsMap = map.get(word);
            if (wordsMap == null || wordsMap.isEmpty()) {
                System.out.println("No suggestions.");
            } else {
                List<String> list = getTopSuggestions(wordsMap, 2);
                System.out.println(list);
            }
        }
    }
    // "word" -> 5
    // "something" -> 5
    // 5 -> {word, something}

    private static List<String> getTopSuggestions(Map<String, Integer> wordsMap, int wordCount) {
        List<String> result = new ArrayList<>();
        Map<Integer, List<String>> reversedMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (String word : wordsMap.keySet()) {
            int numOfOccurrences = wordsMap.get(word);
            if (reversedMap.containsKey(numOfOccurrences)) {
                List<String> list = reversedMap.get(numOfOccurrences);
                list.add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                reversedMap.put(numOfOccurrences, list);
            }
        }
        // 5 -> is, something, ask, go
        // 4 -> do, you, be
        // 3 -> this
        boolean enoughWords = false;
        for (Integer key : reversedMap.keySet()) {
            List<String> words = reversedMap.get(key);
            for (String word : words) {
                result.add(word);
                if (result.size() == wordCount) {
                    enoughWords = true;
                    break;
                }
            }
            if (enoughWords) {
                break;
            }
        }

        return result;
    }

    private static Map<String, Map<String, Integer>> createWordUsageMap(String[] words) {
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
        return map;
    }
}