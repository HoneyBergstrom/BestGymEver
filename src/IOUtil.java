import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 // Main idea är att read and write från och till fil
public class IOUtil {
 //reading the file and return array of customers
    public List<Customer> readFile(String filePath) {
        Path fileToReadPath = Paths.get(filePath);
        List<Customer> customersList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(fileToReadPath)) {
            String firstLine;
            while ((firstLine = br.readLine()) != null) {
                String[] customerDataPartsFirstLine = firstLine.split(",");

                // Read the second line
                String secondLine = br.readLine();
                if (secondLine != null) {
                    LocalDate dateOfLastPayment = LocalDate.parse(secondLine);

                    // Create a Customer object
                    Customer customer = new Customer(
                            customerDataPartsFirstLine[0].trim(),
                            customerDataPartsFirstLine[1].trim(),
                            dateOfLastPayment
                    );

                    customersList.add(customer); // add to arrayList
                }
            }
        } catch (IOException e) {
            System.out.println("Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        }

        return customersList;
    }
    // skriva kund information till PTfil.txt om det var en "aktiv kund" som kom till gym och skriva dagens datum
     // skriva på samma sätt som det står i Customerinfo.txt så att det är lätt att förstå
    public void writeToFile(String customerName, String customerPersonNumber, String dateOfVisit, String visitorLogFilePath){
        Path visitLogFilePath = Paths.get(visitorLogFilePath);

        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(visitLogFilePath, StandardOpenOption.APPEND))) {
            printWriter.print(customerPersonNumber + ", " + customerName + "\n" + dateOfVisit + "\n"); }
        catch (FileNotFoundException e){
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e){
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}

