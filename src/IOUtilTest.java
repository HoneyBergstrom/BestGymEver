import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IOUtilTest {
    // Testar att vi får rätt antal kunder
    @Test
    public void testNumberOfCustomers() {
        IOUtil io = new IOUtil();
        List<Customer> customers = io.readFile("src\\CustomerInfo.txt");
        assertEquals(14, customers.size());
    }

    // Testar att vi läser värden korrekt
    @Test
    public void testOneCustomer() {
        IOUtil io = new IOUtil();
        List<Customer> customers = io.readFile("src\\CustomerInfo.txt");
        Customer customer = customers.getFirst();
        assertEquals("Alhambra Aromes", customer.getName());
        assertEquals("7703021234", customer.getPersonNumber());
        assertEquals(LocalDate.of(2024, 7, 1), customer.getDateOfLastPayment());
    }

    // Testar att vi skriver korrekt till PTFil
    @Test
    public void testCorrectFileOutput() {
        IOUtil io = new IOUtil();
        io.writeToFile("Kalle Anka",
                "6201011111",
                "2024-10-21",
                "src\\PTfil.txt");
        List<Customer> visits = io.readFile("src\\PTfil.txt");
        Customer visit = visits.getLast();
        assertEquals("Kalle Anka", visit.getName());
        assertEquals("6201011111", visit.getPersonNumber());
        assertEquals(LocalDate.of(2024, 10, 21), visit.getDateOfLastPayment());
    }
}