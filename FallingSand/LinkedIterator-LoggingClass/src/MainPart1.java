import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainPart1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Create a log file name: ");
        String fileName = scanner.next();
        JavaLoggingClass.getLogger().createLog(fileName);
        scanner.nextLine();
        System.out.println("Enter a log: ");
        String log = scanner.nextLine();
        JavaLoggingClass.getLogger().writeLog(log);
        System.out.println("Delete a file name: ");
        String input = scanner.next();
        JavaLoggingClass.getLogger().deleteLog(input);


    }
}
