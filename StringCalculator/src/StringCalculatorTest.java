import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    public void testAdd(){
        StringCalculator calculator = new StringCalculator();
        assertEquals("25", calculator.add("1,1,2,3\n4,4\n5,5"));
    }
    /*@Test
    public void testInvalidNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals("25", calculator.add("175.2,\n35"));
    }*/

    @Test
    public void testAddWithCustom(){
        StringCalculator calculator = new StringCalculator();
        assertEquals("5", calculator.add("//sep|\n2sep|3"));
    }

    @Test
    public void testGetSeparator(){
        StringCalculator calculator = new StringCalculator();
        String number = "//sep|\n2sep|3";
        assertEquals("sep|", calculator.getSeparator(number));
    }

    @Test
    public void testTreatSymbol(){
        StringCalculator calculator = new StringCalculator();
        assertEquals("sep[|]", calculator.treatSymbols("sep|"));
    }

    @Test
    public void testVerifyIfHaveSymbol(){
        StringCalculator calculator = new StringCalculator();
        assertEquals("sep[|]", calculator.verifyIfHaveSymbol("sep|"));
    }

}