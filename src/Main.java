import java.util.*;

public class Main {
    static final char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    static final int countOfBigram = 5;
    static final boolean log = false;
    static final boolean logFile = true;

    public static void main(String[] args) {
        final String text = "Сказка = ложь".toLowerCase();
        final String key = "Толстой".toLowerCase();
        final String Source = ".\\War&Peace.txt";
        final String PieceOfSource = ".\\War&PeacePiece.txt";
        final String YevgeniyOnegin = ".\\YevgeniyOnegin.txt";

        final String CodePieceOfSource = ".\\result\\CodePieceOfSource.txt";
        //final String DecodePieceOfSource = ".\\result\\DecodePieceOfSource.txt";
        final String FreqBreakCodePieceOfSource = ".\\result\\FreqBreakCodePieceOfSource.txt";
        final String CodeYevgeniyOnegin = ".\\result\\CodeYevgeniyOnegin.txt";
        final String FreqBreakCodeYevgeniyOnegin = ".\\result\\FreqBreakCodeYevgeniyOnegin.txt";
        final String BigramBreakCodeYevgeniyOnegin = ".\\result\\BigramBreakCodeYevgeniyOnegin.txt";

        Coder coder = new Coder();
        Decoder decoder = new Decoder();
        FrequencyTable frequencyTable = new FrequencyTable();
        FileManager fileManager = new FileManager();
        Breaker breaker = new Breaker();
        BigramsTable bigramsTable = new BigramsTable();

        ArrayList<String> source = fileManager.read(Source);
        ArrayList<String> pieceOfSource = fileManager.read(PieceOfSource);
        ArrayList<String> yevgeniyOnegin = fileManager.read(YevgeniyOnegin);

        fileManager.delete(Log.logFile);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String codeText = coder.code(key, text);
        System.out.println("text:   " + text);
        System.out.println("code:   " + codeText);
        System.out.println("decode: " + decoder.decode(key, codeText) + "\n");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Map<Character, Double> FreqOfSource = frequencyTable.create(source);
        System.out.println(printMap(FreqOfSource, 3) + "\n");
        ArrayList<String> codePieceOfSource = coder.code(key, pieceOfSource);
        fileManager.delete(CodePieceOfSource);
        fileManager.write(CodePieceOfSource, codePieceOfSource);
        Map<Character, Double> FreqOfCodePieceOfSource = frequencyTable.create(codePieceOfSource);
        System.out.println(printMap(FreqOfCodePieceOfSource, 3) + "\n");
        ArrayList<String> freqBreakCodePieceOfSource = breaker.toBreakFreq(codePieceOfSource,
                sortByValue(FreqOfSource, false), sortByValue(FreqOfCodePieceOfSource, false));
        fileManager.delete(FreqBreakCodePieceOfSource);
        fileManager.write(FreqBreakCodePieceOfSource, freqBreakCodePieceOfSource);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> codeYevgeniyOnegin = coder.code(key, yevgeniyOnegin);
        fileManager.delete(CodeYevgeniyOnegin);
        fileManager.write(CodeYevgeniyOnegin, codeYevgeniyOnegin);
        Map<String, Double> BigramOfSource = bigramsTable.create(source);
        Map<Character, Double> FreqOfCodeYevgeniyOnegin = frequencyTable.create(codeYevgeniyOnegin);
        Map<String, Double> BigramOfCodeYevgeniyOnegin = bigramsTable.create(codeYevgeniyOnegin);

        ArrayList<String> freqBreakCodeYevgeniyOnegin = breaker.toBreakFreq(codeYevgeniyOnegin,
                sortByValue(FreqOfSource, false), sortByValue(FreqOfCodeYevgeniyOnegin, false));
        fileManager.delete(FreqBreakCodeYevgeniyOnegin);
        fileManager.write(FreqBreakCodeYevgeniyOnegin, freqBreakCodeYevgeniyOnegin);
        ArrayList<String> bigramBreakCodeYevgeniyOnegin = breaker.toBreakBigram(codeYevgeniyOnegin,
                sortByValue(FreqOfSource, false), sortByValue(FreqOfCodeYevgeniyOnegin, false),
                sortByValue(BigramOfSource, false), sortByValue(BigramOfCodeYevgeniyOnegin, false));
        fileManager.delete(BigramBreakCodeYevgeniyOnegin);
        fileManager.write(BigramBreakCodeYevgeniyOnegin, bigramBreakCodeYevgeniyOnegin);

        Log.log("All tasks are complete!!!");
    }

    private static <K, V> String printMap(Map<K, V> map, int valueOnOneString) {
        int s = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (K c : map.keySet()) {
            if (valueOnOneString != 0 && s % valueOnOneString == 0 && s != 0) {
                sb.append('\n');
            }
            sb.append(c);
            sb.append("->");
            sb.append(map.get(c));
            if (s != map.size() - 1) {
                sb.append(", ");
            }
            s++;
        }
        sb.append("}");
        return sb.toString();
    }

    private static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(Map<K, V> Map, boolean ASC) {
        List<Map.Entry<K, V>> list = new LinkedList<>(Map.entrySet());
        LinkedHashMap<K, V> result = new LinkedHashMap<>();

        list.sort(new Comparator<java.util.Map.Entry<K, V>>() {
            @Override
            public int compare(java.util.Map.Entry<K, V> o1, java.util.Map.Entry<K, V> o2) {
                if (ASC) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}