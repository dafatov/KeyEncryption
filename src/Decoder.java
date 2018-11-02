import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Decoder {
    HashMap<Character, Character> decodeSheet = null;
    
    Decoder() {
        decodeSheet = new HashMap<>();
    }

    private String decode(String key, String text, boolean needLog) {
        StringBuilder cryptogramm = new StringBuilder();

        if (needLog) Log.log("Starting decoding...");
        for (int i = 0; i < text.length(); i++) {
            Character tmp = text.charAt(i);

            cryptogramm.append(decodeSheet.getOrDefault(tmp, tmp));
        }
        if (needLog) Log.log("Decoding is complete");
        return cryptogramm.toString();
    }

    String decode(String key, String text) {
        genDecodeSheet(key);
        return decode(key, text, true);
    }

    ArrayList<String> decode(String key, ArrayList<String> text) {
        ArrayList<String> result = new ArrayList<>();
        genDecodeSheet(key);

        Log.log("Starting decoding...");
        for (String s : text) {
            result.add(decode(key, s, false));
        }
        Log.log("Decoding is complete");
        return result;
    }

    void genDecodeSheet(String key) {
        decodeSheet.clear();
        HashSet<Character> usedLetters = new HashSet<>();
        int counter = 0;

        Log.log("Starting generate decoding sheet...");
        for (int i = 0; i < key.length(); i++) {
            Character tmp = key.charAt(i);

            if (!usedLetters.contains(tmp)) {
                decodeSheet.put(tmp, Main.alphabet[counter]);
                usedLetters.add(tmp);
                counter++;
            }
        }
        for (int i = 0; i < Main.alphabet.length; i++) {
            Character tmp = Main.alphabet[i];

            if (!usedLetters.contains(tmp)) {
                decodeSheet.put(Main.alphabet[i], Main.alphabet[counter]);
                counter++;
            }
        }
        Log.log("Generate decoding sheet is complete");
    }
}
