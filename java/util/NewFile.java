package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NewFile {

    public enum Command {
        Class,
        Main,
        Print
    }

    private static String myFile = "";
    private static String name = "";
    private static String javaFile = "";
    private static String javaName;
    private static String nameNoExtension;

    public static ArrayList<Var> varList = new ArrayList<>();

    public NewFile(String name) {
        NewFile.name = name;
        javaFile = "";
    }

    public void readFile() {
        nameNoExtension = name.split("\\.")[0];
        name = "C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\src\\" + name;
        javaName = "C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\java\\out\\" + nameNoExtension + ".java";

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
        String[] file = Arrays.stream(myFile.toLowerCase().split("")).filter((String s) -> !s.equals(" ")).collect(Collectors.joining()).split("\\)");
        for (String s : file) {
            Lexer lexer = new Lexer(s);
        }
    }

    public static void createVar(String[] params) {
        varList.add(new Var(params[0], params[1]));
    }
}