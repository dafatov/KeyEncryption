import java.io.*;
import java.util.ArrayList;

class FileManager {
    ArrayList<String> read(String filePath) {
        ArrayList<String> stringArray = new ArrayList<>();

        Log.log("Starting read file " + filePath + "...");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String tmp;
            while ((tmp = br.readLine()) != null) {
                stringArray.add(tmp);
            }
        } catch (IOException e) {
            System.err.println("File is not found");
            System.exit(100);
        }
        Log.log("Reading file is complete");
        return stringArray;
    }

    void write(String filePath, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("File " + filePath + " is not found");
            System.exit(101);
        }
    }

    void write(String filePath, ArrayList<String> text) {
        Log.log("Starting writing to file " + filePath + "...");
        for (String s : text) {
            write(filePath, s);
        }
        Log.log("Writing to file is complete");
    }

    boolean delete(String filePath) {
        Log.log("Starting delete file " + filePath + "...");
        File file = new File(filePath);
        Log.log("Deleting file is complete");
        return file.delete();
    }
}