import java.time.LocalDate;
import java.util.List;

public class LogicUtil {

// This is all the magic happen.

    public static String checkMemberStatusPersonnummer(String customerListFilePath, String visitorLogFilePath, String personnummer) {
        try {
            Long.parseLong(personnummer);
        } catch (NumberFormatException e) { //make sure that is a nummer
            throw new IllegalArgumentException();
        }
        return check(customerListFilePath, visitorLogFilePath, personnummer);
    }
        //I rutan för namn kan man skriva vad som helst ingen exception
    public static String checkMemberStatusName(String customerListFilePath, String visitorLogFilePath, String name) {
        return check(customerListFilePath, visitorLogFilePath, name);
    }
 //Om dem två metoderna uppe går bra då kommer de köra här i Check
    private static String check(String customerListFilePath, String visitorLogFilePath, String input) {
        IOUtil ioUtil = new IOUtil();
        List<Customer> customersList = ioUtil.readFile(customerListFilePath); //read the file and out them in arraylist
        String customerStatus = "";
//Loopa igenom arrayList
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
            if (input.equals(customer.getPersonNumber()) || input.equals(customer.getName())) {
                    //om vi hittar en person som matcha hämta vi date of last payment från den kunden och ta fram dagen datum för att kunna jämföra
                LocalDate dateOfLastPayment = customer.getDateOfLastPayment();
                LocalDate currentDate = LocalDate.now();

                //här jämför om det är mer än ett år sedan och berättar att det är "före detta kund" och när de senast betalade
                if (dateOfLastPayment.isBefore(currentDate.minusYears(1))) {
                    return "Det här är en före detta kund som senast betalade årsavgiften " + dateOfLastPayment.toString();
                }
                //här jämför om det är mindre en ett år sedan och berättar att det är "aktiv kund" och när de senast betalade
                if (dateOfLastPayment.isAfter(currentDate.minusYears(1))) {
                    customerStatus = customer.getName() + " är en aktiv kund som senast betalade årsavgiften " + dateOfLastPayment.toString();

                    //Get information från kund som jag ska writeToFile (PTfil.txt)
                    String customerName = customer.getName();
                    String customerPersonNumber = customer.getPersonNumber();
                    String dateOfVisit = currentDate.toString();
                    ioUtil.writeToFile(customerName, customerPersonNumber, dateOfVisit, visitorLogFilePath);
                    return customerStatus;
                }
            }
        }
        return "Den här personen är inte behörig att komma in på Best Gym Ever";
    }
}
