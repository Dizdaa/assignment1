import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.List;

public class ReportFileWriter {

    public static void writeReport(String fileName, List <String> lines) {
        try (PrintWriter out = new PrintWriter(new PrintWriter(fileName))) {
            ;

            for (String line : lines) {
                out.println(line);
            }
            System.out.println("Report saved to: " + fileName);
        } catch (Exception e){
            System.out.println("Error writing report file: " + fileName);
            e.printStackTrace();
        }
    }
}
