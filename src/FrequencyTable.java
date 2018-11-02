import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FrequencyTable {
    /*HashMap<Character, Double> create(String text) {
        HashMap<Character, Double> freqTable = new HashMap<>();
        int size = 0;

        for (Character letter : Main.alphabet) {
            freqTable.put(letter, 0.);
        }

        for (int i = 0; i < text.length(); i++) {
            Character tmp = text.charAt(i);

            if (freqTable.containsKey(tmp)) {
                freqTable.put(tmp, freqTable.get(tmp) + 1);
                size++;
            }
        }

        if (!freqTable.isEmpty()) {
            for (Character letter : Main.alphabet) {
                freqTable.put(letter, freqTable.get(letter) / size);
            }
        } else {
            System.err.println('\n' + "Frequency table is empty");
            System.exit(-1);
        }
        return freqTable;
    }*/
    HashMap<Character, Double> create(ArrayList<String> textArray) {
        HashMap<Character, Double> freqTable = new HashMap<>();
        int size = 0;

        Log.log("Starting generate frequency table...");
        for (String text : textArray) {
            for (int i = 0; i < text.length(); i++) {
                String letterS = text.substring(i, i + 1);
                Character letter = letterS.charAt(0);
                Matcher matcher = Pattern.compile("^[а-я]$").matcher(letterS);

                if (matcher.find()) {
                    if (freqTable.containsKey(letter)) {
                        freqTable.put(letter, freqTable.get(letter) + 1);
                    } else {
                        freqTable.put(letter, 1.);
                    }
                    size++;
                }
            }
        }
        for (Map.Entry<Character, Double> letter : freqTable.entrySet()) {
            freqTable.put(letter.getKey(), letter.getValue() / size);
        }
        Log.log("Generating frequency table is complete");
        return freqTable;
    }
}
