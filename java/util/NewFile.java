package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NewFile {
    private static String myFile = "";
    private static String name = "";

    public static ArrayList<Var> varList = new ArrayList<>();

    public NewFile(String name) {
        NewFile.name = name;
    }

    public void readFile() {
        name = "C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\src\\" + name;

        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myFile += data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeCommands() {
        String[] myFileArray = myFile.split("\"");
        ArrayList<String> files = new ArrayList<>();

        for (int i = 0; i < myFileArray.length; i++) {
            if (i % 2 == 0) {
                files.add(Arrays.stream(myFileArray[i].toLowerCase().split("")).filter((String s) -> !s.equals(" ")).collect(Collectors.joining()));
            } else {
                files.add(myFileArray[i]);
            }
        }
        String[] file = String.join("", files).split("\\)");

        varList.clear();
        for (String s : file) {
            Lexer lexer = new Lexer(s);
        }
    }

    public static void createVar(String id, String param) {
        varList.add(new Var(id, param));
    }
}