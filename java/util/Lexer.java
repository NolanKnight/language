package util;

import java.util.ArrayList;

public class Lexer {
    String id;
    Object val;
    public Lexer(String command) {
        String[] splitCommand = command.split("\\(");
        id = splitCommand[0];
        String[] params = splitCommand[1].split(",");

        for (int i = 0; i < params.length; i++) {
            for (Var var : NewFile.varList) {
                if (params[i].equals(var.name)) {
                    params[i] = var.val;
                }
            }
        }

        switch (id) {
            case "add" -> val = Integer.parseInt(params[0]) + Integer.parseInt(params[1]);
            case "subtract" -> val = Integer.parseInt(params[0]) - Integer.parseInt(params[1]);
            case "var" -> {
                NewFile.createVar(params);
            }
            case "print" -> {
                val = params[0];
                System.out.println(val);
            }
        }
    }
}
