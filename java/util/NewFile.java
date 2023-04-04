package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NewFile {
//    interface Command {
//        String run(String[] params);
//    }
//
//    public enum CodeCommands implements Command {
//        Class {
//            @Override
//            public String run(String[] params) {
//                return "public class " + params[0] + " {" + params[1] + "}";
//            }
//        },
//        Main {
//            @Override
//            public String run(String[] params) {
//                String cmds = "";
//                for (String p : params) {
//                    cmds += p;
//                }
//                return "public static void main(String[] args) {" + cmds + "}";
//            }
//        },
//        Print {
//            @Override
//            public String run(String[] params) {
//                return "System.out.println(\"" + params[0] + "\");";
//            }
//        };
//    }

    private static String myFile = "";
    private static String name = "";
    private static String javaFile = "";
    private static String javaName;
    private static String nameNoExtension;

    public NewFile(String name) {
        NewFile.name = name;
        javaFile = "";
    }

    public void readFile() {
        nameNoExtension =  name.split("\\.")[0];
        name = "C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\src\\" + name;
        javaName = "C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\java\\" + nameNoExtension + ".java";

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

    public void writeToJavaFile() {
        System.out.println(javaFile);
        try {
            FileWriter myWriter = new FileWriter(javaName);
            myWriter.write(javaFile);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeCommands() {
        javaFile += "public class " + nameNoExtension + " {public static void main(String[] args) {System.out.println(\"Hello World!\");}}";
        // CodeCommands cmd = CodeCommands.Class;
        // String[] hello = { "Hello" };
        // String[] pCmds = { CodeCommands.Print.run(hello) };
        // String[] cmds = { "File", CodeCommands.Main.run(pCmds) };
        // javaFile += cmd.run(cmds);
        // String[] file = myFile.split("\\)");

        // for (int i = 0; i < file.length; i++) {
        // String[] commandParam = file[i].split("\\(");
        // String commandString = commandParam[0];
        // String[] sParams = commandParam[1].split(",");

        // CodeCommands cmd = stringToCommand(commandString);

        // // switch (cmd) {
        // // case Class:
        // // String[] cmdParams = { sParams[0],
        // // stringToCommand(sParams[1]).run(file[i + 1].split("\\(")[1].split(",")) };
        // // javaFile += cmd.run(cmdParams);
        // // break;
        // // case Main:
        // // javaFile += cmd.run(sParams);
        // // case Print:
        // // default:
        // // }
        // // ArrayList<Object> params = new ArrayList<>();

        // // for (int j = 0; j < sParams.length; j++) {
        // // if (isCommand(sParams[j])) {
        // // String yes = sParams[j];
        // // String[] commandParam1 = file[j].split("\\(");
        // // String commandString1 = commandParam1[0];
        // // String[] sParams1 = commandParam1[1].split(",");
        // // for (int k = 0; k < sParams1.length; k++) {
        // // yes = sParams1[k];
        // // if (!isCommand(yes)) {
        // // CodeCommands cmd = stringToCommand(yes);
        // // }
        // // }
        // // params.add(file);
        // // } else {
        // // params.add(sParams[j]);
        // // }
        // // }

        // // CodeCommands bigCmd = stringToCommand(commandString);
        // // javaFile += bigCmd.run(sParams);
        // }
    }

//    public static boolean isCommand(String command) {
//        return switch (command) {
//            case "Class", "Main", "Print" -> true;
//            default -> false;
//        };
//    }
//
//    public static CodeCommands stringToCommand(String command) {
//        return switch (command) {
//            case "Class" -> CodeCommands.Class;
//            case "Main" -> CodeCommands.Main;
//            default -> CodeCommands.Print;
//        };
//    }

    public void createJavaFile() {
            File myObj = new File(javaName);
    }
}
