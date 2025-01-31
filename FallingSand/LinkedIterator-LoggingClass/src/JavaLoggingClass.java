import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaLoggingClass <T>{
    private String filePath;

    //creates the only instance of logging class which is declared in the scope
    //of the class
    private static JavaLoggingClass loggingInstance = new JavaLoggingClass();

    //removes the default constructor and prevents the creation of an instance
    //since the only constructor is not declarable beyond the scope of the class
    private JavaLoggingClass(){

    }
    //accessing the class instance
    public static JavaLoggingClass getLogger(){
        return loggingInstance;
    }

    public void writeLog(T newLogEntry) throws IOException {
    if(this.filePath == null){
        System.out.println("Create a file path first!");
    }else {
        FileWriter fileWriter = new FileWriter(filePath+".txt", true);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String currentTime = localDateTime.format(dateTimeFormatter);

        fileWriter.write("\n"+currentTime+" - "+newLogEntry+"\n");
        System.out.println("Process completed");
        fileWriter.close();
    }

    }
    public void createLog(String filePath){
        this.filePath = filePath;
    }
    public void deleteLog(String filepath){
        File file = new File(filepath+".txt");
        if(file.exists()){
            file.delete();
            System.out.println("File deleted successfully.");
        }
        else{
            System.out.println("File not located.");
        }
    }
}
