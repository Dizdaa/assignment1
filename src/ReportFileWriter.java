import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ReportFileWriter {

    public static void writeReport(String fileName, List<String> lines) {
        try {
            // 1) Kreiraj folder /reports ako ne postoji
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdir();
                System.out.println("Created folder: /reports");
            }

            // 2) Putanja fajla u reports folder
            File file = new File(reportsDir, fileName);

            // 3) Pišemo u fajl
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                for (String line : lines) {
                    out.println(line);
                }
            }

            System.out.println("Report saved to: " + file.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Error writing report to file: " + fileName);
            e.printStackTrace();
        }
    }
}
