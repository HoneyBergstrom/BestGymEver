import java.time.LocalDate;
// I den här klassen finns bara konstruktor and getters

public class Customer {
    protected String name;
    protected String personNumber;
    protected LocalDate dateOfLastPayment;

    public Customer(String personNumber, String name, LocalDate dateOfLastPayment) {
        this.name = name;
        this.personNumber = personNumber;
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public String getName() {
        return name;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public LocalDate getDateOfLastPayment() {
        return dateOfLastPayment;
    }



}
