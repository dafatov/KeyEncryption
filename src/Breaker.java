
import java.util.*;

class Breaker {
    HashMap<Character, Character> breakSheetFreq;
    HashMap<Character, Character> breakSheetBigram;

    Breaker() {
        breakSheetFreq = new HashMap<>();
        breakSheetBigram = new HashMap<>();
    }

    private String toBreak(String text, boolean needLog, boolean freq) {
        StringBuilder cryptogram = new StringBuilder();

        if (needLog) Log.log("Starting breaking...");
        for (int i=0; i<text.length(); i++) {
            Character tmp = text.charAt(i);

            if (freq) {
                cryptogram.append(breakSheetFreq.getOrDefault(tmp, tmp));
            }else {
                cryptogram.append(breakSheetBigram.getOrDefault(tmp, tmp));
            }
        }
        if (needLog) Log.log("Breaking is complete");
        return cryptogram.toString();
    }

    String toBreakFreq(String text, LinkedHashMap<Character, Double> fTableSorted,
                       LinkedHashMap<Character, Double> fTableCryptSorted) {
        getBreakSheetFreq(fTableSorted, fTableCryptSorted);
        return toBreak(text, true, true);
    }

    ArrayList<String> toBreakFreq(ArrayList<String> text, LinkedHashMap<Character, Double> fTableSorted,
                                  LinkedHashMap<Character, Double> fTableCryptSorted) {
        ArrayList<String> result = new ArrayList<>();
        getBreakSheetFreq(fTableSorted, fTableCryptSorted);

        Log.log("Starting breaking...");
        for (String s : text) {
            result.add(toBreak(s, false, true));
        }
        Log.log("Breaking is complete");
        return result;
    }

    String toBreakBigram(String text, LinkedHashMap<Character, Double> fTableSorted,
                         LinkedHashMap<Character, Double> fTableCryptSorted, LinkedHashMap<String, Double> bTableSorted,
                         LinkedHashMap<String, Double> bTableCryptSorted) {
        getBreakSheetBigram(fTableSorted, fTableCryptSorted, bTableSorted, bTableCryptSorted);
        return toBreak(text, true, false);
    }

    ArrayList<String> toBreakBigram(ArrayList<String> text, LinkedHashMap<Character, Double> fTableSorted,
                                    LinkedHashMap<Character, Double> fTableCryptSorted, LinkedHashMap<String, Double> bTableSorted,
                                    LinkedHashMap<String, Double> bTableCryptSorted) {
        ArrayList<String> result = new ArrayList<>();
        getBreakSheetBigram(fTableSorted, fTableCryptSorted, bTableSorted, bTableCryptSorted);

        Log.log("Starting breaking...");
        for (String s : text) {
            result.add(toBreak(s, false, false));
        }
        Log.log("Breaking is complete");
        return result;
    }

    void getBreakSheetFreq(LinkedHashMap<Character, Double> fTableSorted,
                                   LinkedHashMap<Character, Double> fTableCryptSorted) {
        breakSheetFreq.clear();
        //if (fTableSorted.size() != fTableCryptSorted.size()) { System.err.println("Какая то лажа в Breaker.getBreakSheetFreq"); System.exit(1000);}
        Character[] fts = fTableSorted.keySet().toArray(new Character[0]);
        Character[] ftcs = fTableCryptSorted.keySet().toArray(new Character[0]);

        Log.log("Starting generate breaking sheet from frequency table...");
        for (int i=0; i<fts.length; i++) {
            breakSheetFreq.put(ftcs[i], fts[i]);
        }
        Log.log("Generate breaking sheet from frequency table is complete");
    }

    void getBreakSheetBigram(LinkedHashMap<Character, Double> fTableSorted,
                                     LinkedHashMap<Character, Double> fTableCryptSorted, LinkedHashMap<String, Double> bTableSorted,
                                     LinkedHashMap<String, Double> bTableCryptSorted) {
        breakSheetBigram.clear();
        //if (bTableSorted.size() != bTableCryptSorted.size()) { System.err.println("Какая то лажа в Breaker.getBreakSheeBigram: " + bTableSorted.size() + " != " + bTableCryptSorted.size()); System.exit(1001);}
        String[] bts = bTableSorted.keySet().toArray(new String[0]);
        String[] btcs = bTableCryptSorted.keySet().toArray(new String[0]);

        Log.log("Starting generate breaking sheet from bigrams table...");
        getBreakSheetFreq(fTableSorted, fTableCryptSorted);
        breakSheetBigram = breakSheetFreq;
        for (int i=0; i<Main.countOfBigram; i++) {
            breakSheetBigram.put(btcs[i].charAt(0), bts[i].charAt(0));
            breakSheetBigram.put(btcs[i].charAt(1), bts[i].charAt(1));
        }
        Log.log("Generate breaking sheet from bigrams table is complete");
    }
}


    /*String toBreakF(String text, LinkedHashMap<Character, Double> freqTableSorted,
                   LinkedHashMap<Character, Double> freqTableCryptSorted) {
        getBreakSheetF(freqTableSorted, freqTableCryptSorted);
        return toBreak(text);
    }

    String toBreakD(String text, LinkedHashMap<String, Double> digramsTableSorted,
                   LinkedHashMap<String, Double> digramsTableCryptSorted) {
        getBreakSheetD(digramsTableSorted, digramsTableCryptSorted);
        return toBreak(text);
    }


    private void getBreakSheetD(LinkedHashMap<String, Double> bTableSorted,
                                                         LinkedHashMap<String, Double> bTableCryptSorted) {
        if (bTableSorted.size() != bTableCryptSorted.size()) { System.err.println("Какая то лажа в Breaker.getBreakSheetD: " + bTableSorted.size() + " != " + bTableCryptSorted.size()); System.exit(1001);}
        String[] dts = bTableSorted.keySet().toArray(new String[0]);
        String[] dtcs = bTableCryptSorted.keySet().toArray(new String[0]);

        Log.log("Starting generate breaking sheet from digrams table...");
        for (int i=0; i<(dts.length<dtcs.length?dts.length:dtcs.length); i++) {
            Character dtcs0 = dtcs[i].charAt(0);
            Character dtcs1 = dtcs[i].charAt(1);
            Character dts0 = dts[i].charAt(0);
            Character dts1 = dts[i].charAt(1);


        }
        Log.log("Generate breaking sheet is complete");
    }
}
*/
