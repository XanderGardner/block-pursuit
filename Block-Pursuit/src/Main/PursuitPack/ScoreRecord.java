package Main.PursuitPack;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class ScoreRecord {
    //private InputStream record;
    //private File record2;
    private static String normalName;
    private static double normalScore;
    private static String insaneName;
    private static double insaneScore;

    /*
    public ScoreRecord() throws FileNotFoundException {
        record = new File("record.txt");
        URL url = getClass().getResource("record.txt");
        record2 = new File(url.getPath());

        record = this.getClass().getResourceAsStream("record.txt");
        Scanner readFile = new Scanner(record);
        String[] nLine = {"NORMAL", "NA", "0"};
        String[] iLine = {"INSANE", "NA", "0"};
        String[] t1;
        String[] t2;


        if (readFile.hasNextLine()) {
            t1 = readFile.nextLine().split(",");
            if (t1[0].equals("NORMAL")) {
                nLine = t1;
            } else {
                iLine = t1;
            }
            t2 = readFile.nextLine().split(",");
            if (t2[0].equals("NORMAL")) {
                nLine = t2;
            } else {
                iLine = t2;
            }
        }
        readFile.close();



        normalName = nLine[1];
        normalScore = Double.parseDouble(nLine[2]);
        insaneName = iLine[1];
        insaneScore = Double.parseDouble(iLine[2]);
    }
     */

    public static void trySetNormal(String name, Double score) throws FileNotFoundException {
        if (score > normalScore) {
            normalName = name;
            normalScore = score;
        }

        /*
        PrintWriter outFile = new PrintWriter(record2);
        outFile.println("NORMAL," + normalName + "," + normalScore);
        outFile.print("INSANE," + insaneName + "," + insaneScore);
        outFile.close();
         */
    }
    public static void setNormalName(String name) throws FileNotFoundException {
        normalName = name;

        /*
        PrintWriter outFile = new PrintWriter(record2);
        outFile.println("NORMAL," + normalName + "," + normalScore);
        outFile.print("INSANE," + insaneName + "," + insaneScore);
        outFile.close();
         */
    }
    public static void trySetInsane(String name, Double score) throws FileNotFoundException {
        if (score > insaneScore){
            insaneName = name;
            insaneScore = score;
        }
        /*
        PrintWriter outFile = new PrintWriter(record2);
        outFile.println("NORMAL," + normalName + "," + normalScore);
        outFile.print("INSANE," + insaneName + "," + insaneScore);
        outFile.close();
         */

    }
    public static void setInsaneName(String name) throws FileNotFoundException {
        insaneName = name;

        /*
        PrintWriter outFile = new PrintWriter(record2);
        outFile.println("NORMAL," + normalName + "," + normalScore);
        outFile.print("INSANE," + insaneName + "," + insaneScore);
        outFile.close();
         */
    }
    public static String getNormalName(){
        return normalName;
    }
    public static double getNormalScore(){
        return normalScore;
    }
    public static String getInsaneName(){
        return insaneName;
    }
    public static double getInsaneScore(){
        return insaneScore;
    }
}