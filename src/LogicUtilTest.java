import org.junit.Test;

public class LogicUtilTest {
    // Här kollar vi att man inte kan mata in annat än siffror i personnummer
    @Test(expected = IllegalArgumentException.class) //I want to see redddddd !!!
    public void testPersonnummerInput() {
        LogicUtil logicUtil = new LogicUtil();
        String customerListFilePath = "src\\CustomerInfo.txt";
        String visitorLogFilePath = "src\\PTfil.txt";

        logicUtil.checkMemberStatusPersonnummer(customerListFilePath, visitorLogFilePath, "ABC");
    }
}