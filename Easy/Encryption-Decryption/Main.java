package encryptdecrypt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static String readFile (String filePath){
        String result = "";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            System.out.println("There is no such file, " + e.getMessage());
        }
        return result;
    }


    public static void writeFile (String filePath, String data){
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)){
            writer.write(data);
        } catch (IOException e){
            System.out.println("Error while writing file");
        }
    }

    public static CipherSelectionAlgorithm create(String algType) {
        switch (algType) {
            case "shift": {
                return new ShiftAlgorithm();
            }
            case "unicode": {
                return new UnicodeAlgorithm();
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }

    public static void main(String[] args) {
        String mode ="enc";
        int key = 0;
        String data = "";
        int  _dataMode = 1;
        int out = 0;
        String inFilePath = "./";
        String outFilePath = "./";
        String alg = "shift";

        if (args.length == 0) {
            System.out.println("Error no input data");
        }
        else {

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-key")) {
                    key = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equals("-mode")) {
                    mode = args[i + 1];
                }
                if (args[i].equals("-data")) {
                    data += args[i + 1];
                    _dataMode = 0;
                }
                if (args[i].equals("-in")) {
                    inFilePath += args[i + 1];
                }
                if (args[i].equals("-out")) {
                    outFilePath += args[i + 1];
                    out = 1;
                }
                if (args[i].equals("-alg")) {
                    alg = args[i + 1];
                }

            }
        }

        if (_dataMode == 1) {
            data = readFile(inFilePath);


        }

        final CipherSelectionAlgorithm selAlg = create(alg);
        final SelectContext ctx = new SelectContext();
        ctx.setAlgorithm(selAlg);
        String result = null;
        switch (mode){
            case "enc" : result = ctx.selectEncryption(data, key); break;
            case "dec" : result = ctx.selectDecryption(data, key); break;
        }


        switch (out){
            case 0 : System.out.println(result); break;
            case 1 : writeFile(outFilePath, result); break;
        }
    }
}
