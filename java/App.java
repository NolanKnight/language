import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import util.NewFile;

public class App {
    public static Set<String> listFiles(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) throws Exception {
        for (String file : listFiles("C:\\Users\\nolan\\Desktop\\Coding\\Java\\language\\src")) {
            System.out.println(file);
            NewFile newFile = new NewFile(file);

            newFile.readFile();

            newFile.writeCommands();

            newFile.writeToJavaFile();
        }
    }
}
