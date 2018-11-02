import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Coder {
    HashMap<Character, Character> codeSheet = null;

    Coder() {
        codeSheet = new HashMap<>();
    }

    private String code(String text, boolean needLog) {
        StringBuilder cryptogramm = new StringBuilder();

        if (needLog) Log.log("Starting coding...");
        for (int i = 0; i < text.length(); i++) {
            Character tmp = text.charAt(i);

            cryptogramm.append(codeSheet.getOrDefault(tmp, tmp));
        }
        if (needLog) Log.log("Coding is complete");
        return cryptogramm.toString();
    }

    String code(String key, String text) {
        genCodeSheet(key);
        return code(text, true);
    }

    ArrayList<String> code(String key, ArrayList<String> text) {
        ArrayList<String> result = new ArrayList<>();
        genCodeSheet(key);

        Log.log("Starting coding...");
        for (String s : text) {
            result.add(code(s, false));
        }
        Log.log("Coding is complete");
        return result;
    }

    void genCodeSheet(String key) {
        codeSheet.clear();
        HashSet<Character> usedLetters = new HashSet<>();
        int counter = 0;

        Log.log("Starting generate coding sheet...");
        for (int i = 0; i < key.length(); i++) {
            Character tmp = key.charAt(i);

            if (!usedLetters.contains(tmp)) {
                codeSheet.put(Main.alphabet[counter], tmp);
                usedLetters.add(tmp);
                counter++;
            }
        }
        for (int i = 0; i < Main.alphabet.length; i++) {
            Character tmp = Main.alphabet[i];

            if (!usedLetters.contains(tmp)) {
                codeSheet.put(Main.alphabet[counter], Main.alphabet[i]);
                counter++;
            }
        }
        Log.log("Generate coding sheet is complete");
    }
}
