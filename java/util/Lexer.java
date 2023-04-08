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
                boolean num = true;
                boolean isOp = false;

                for (int j = 0; j < math.length; j++) {
                    switch (math[j]) {
                        case "*" -> {
                            math = String.join("", math).split("\\*");
                            op = "*";
                            isOp = true;
                        }
                        case "/" -> {
                            math = String.join("", math).split("/");
                            op = "/";
                            isOp = true;
                        }
                        case "+" -> {
                            math = String.join("", math).split("\\+");
                            op = "+";
                            isOp = true;
                        }
                        case "-" -> {
                            math = String.join("", math).split("-");
                            op = "-";
                            isOp = true;
                        }
                    }
                }

                for (Var var : NewFile.varList) {
                    for (int j = 0; j < math.length; j++) {
                        if (var.name.equals(math[j])) {
                            math[j] = var.val;
                        }
                    }
                }

                if (isOp) {
                    for (String s : math[0].split("")) {
                        if (Character.isLetter(s.charAt(0))) {
                            num = false;
                        }
                    }

                    double num1 = 0.0;
                    double num2 = 0.0;

                    if (num) {
                        num1 = Double.parseDouble(math[0]);
                        num2 = Double.parseDouble(math[1]);
                        switch (op) {
                            case "+" -> params[i] = String.valueOf(num1 + num2);
                            case "-" -> params[i] = String.valueOf(num1 - num2);
                            case "*" -> params[i] = String.valueOf(num1 * num2);
                            case "/" -> params[i] = String.valueOf(num1 / num2);
                            default -> {
                            }
                        }
                    } else {
                        params[i] = "";
                        for (String s : math) {
                            params[i] += s;
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
