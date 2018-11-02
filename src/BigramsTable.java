import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BigramsTable {
    HashMap<String, Double> create(ArrayList<String> textArray) {
        HashMap<String, Double> bigramsTable = new HashMap<>();
        int size = 0;

        Log.log("Starting generate bigrams frequency table...");
        for (String text : textArray) {
            for (int i = 0; i < text.length() - 1; i++) {
                String bigram = text.substring(i, i + 2);
                Matcher matcher = Pattern.compile("^[а-я]{2}$").matcher(bigram);

                if (matcher.find()) {
                    if (bigramsTable.containsKey(bigram)) {
                        bigramsTable.put(bigram, bigramsTable.get(bigram) + 1);
                    } else {
                        bigramsTable.put(bigram, 1.);
                    }
                    size++;
                }
            }
        }
        for (Map.Entry<String, Double> bigram : bigramsTable.entrySet()) {
            bigramsTable.put(bigram.getKey(), bigram.getValue() / size);
        }
        Log.log("Generating bigrams frequency table is complete");
        return bigramsTable;
    }
}
