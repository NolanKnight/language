package util;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lexer {
    String id;
    Object val;

    public Lexer(String command) {
        String[] splitCommand = command.split("\\(");
        id = splitCommand[0];
        String[] params = {};

        if (splitCommand.length > 1) {
            params = splitCommand[1].split(",");

            for (int i = 0; i < params.length; i++) {
                for (Var var : NewFile.varList) {
                    if (params[i].equals(var.name)) {
                        params[i] = var.val;
                    }
                }

                String[] math = params[i].split("");
                String op = "";
                boolean decimal = false;
                boolean string = false;

                for (int j = 0; j < math.length; j++) {
                    switch (math[j]) {
                        case "\\*" -> {
                            math = String.join("", math).split("\\*");
                            op = "*";
                        }
                        case "/" -> {
                            math = String.join("", math).split("/");
                            op = "/";
                        }
                        case "+" -> {
                            math = String.join("", math).split("\\+");
                            op = "+";
                        }
                        case "-" -> {
                            math = String.join("", math).split("-");
                            op = "-";
                        }
                        case "\\." -> decimal = true;
                        case "\"" -> string = true;
                    }
                }

                if (decimal) {
                    switch (op) {
                        case "+" -> params[i] = String.valueOf((Double.parseDouble(math[0]) + Double.parseDouble(math[1])));
                        case "-" -> params[i] = String.valueOf((Double.parseDouble(math[0]) - Double.parseDouble(math[1])));
                        case "*" -> params[i] = String.valueOf((Double.parseDouble(math[0]) * Double.parseDouble(math[1])));
                        case "/" -> params[i] = String.valueOf((Double.parseDouble(math[0]) / Double.parseDouble(math[1])));
                        default -> {
                        }
                    }
                } else if (string) {
                    params[i] = math[0] + math[1];
                } else {
                    switch (op) {
                        case "+" -> params[i] = String.valueOf((Integer.parseInt(math[0]) + Integer.parseInt(math[1])));
                        case "-" -> params[i] = String.valueOf((Integer.parseInt(math[0]) - Integer.parseInt(math[1])));
                        case "*" -> params[i] = String.valueOf((Integer.parseInt(math[0]) * Integer.parseInt(math[1])));
                        case "/" -> params[i] = String.valueOf((Integer.parseInt(math[0]) / Integer.parseInt(math[1])));
                        default -> {
                        }
                    }
                }
            }
        }

        switch (id) {
            case "println" -> {
                val = params[0];
                System.out.println(val);
            }

            case "print" -> {
                val = params[0];
                System.out.print(val);
            }

            case "input" -> {
                Scanner scanner = new Scanner(System.in);
                switch (params[0]) {
                    case "int" -> {
                        int num = scanner.nextInt();
                        val = String.valueOf(num);
                    }
                    case "float" -> {
                        float num = scanner.nextFloat();
                        val = String.valueOf(num);
                    }
                    default -> val = scanner.next();
                }

                for (int i = 0; i <NewFile.varList.size(); i++) {
                    if (NewFile.varList.get(i).name.equals(params[1])) {
                        NewFile.varList.remove(i);
                    }
                }

                NewFile.createVar(params[1], (String) val);
            }
            default -> {
                if (splitCommand.length > 1) {
                    NewFile.createVar(id, params[0]);
                } else {
                    NewFile.createVar(id, "");
                }
            }
        }
    }
}
