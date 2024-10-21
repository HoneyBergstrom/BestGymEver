import javax.swing.*;

// This a main program
public class ReceptionistProgram {
    public static void main(String[] args) {
        //Definiera sök väg till in/out fil
        String customerListFilePath = "src\\CustomerInfo.txt";
        String visitorLogFilePath = "src\\PTfil.txt";

        //skapa text fält att skiva personnummer eller namn i
        JTextField personNumberField = new JTextField(10);
        JTextField nameField = new JTextField(30);

        // skapa Jpanel att ta txt label och rutor i
        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Personnummer:"));
        myPanel.add(personNumberField);
        myPanel.add(Box.createHorizontalStrut(15)); // a empty spacer between personnummer and name part
        myPanel.add(new JLabel("Namn:"));
        myPanel.add(nameField);

        //använd JOptionpane för att visa JPanel som jag har skapas
        JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter personnummer or name", JOptionPane.OK_OPTION);

        //Om kan inte skriva eller skriva fel så man det här message
        String memberStatusMessage = "Enter either personnummer or name";

        //skapa object av LogicUtil för att kunna leta efter kunden
        LogicUtil logicUtil = new LogicUtil();
        try {
            if (!personNumberField.getText().isEmpty()) {
                memberStatusMessage = logicUtil.checkMemberStatusPersonnummer(customerListFilePath, visitorLogFilePath, personNumberField.getText());
            }
            if (!nameField.getText().isEmpty()) {
                memberStatusMessage = logicUtil.checkMemberStatusName(customerListFilePath, visitorLogFilePath, nameField.getText());
            }
        } catch (IllegalArgumentException e) {
            memberStatusMessage = "Input error"; // om man skriver String in personnummer
        }
        JOptionPane.showMessageDialog(null, memberStatusMessage);

    }
}