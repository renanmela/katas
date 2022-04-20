import org.junit.jupiter.api.Test;


import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest { ;
    @Test
    public void testAdd(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("1.1,2.2");
        assertEquals("3.3", calculator.add());
    }

    @Test
    public void testAddWithNewLine(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("1\n2,3");
        assertEquals("6.0", calculator.add());
    }

    @Test
    public void testAddWithInvalidNewLine(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("175.2,\n35");
        try{
            calculator.add();
        }catch (NumberFormatException e){
            assertEquals("Number expected but ',\n' found at position 5", e.getMessage());
        }
    }

    @Test
    public void testAddWithInvalidLastPosition() {
        StringCalculator calculator = new StringCalculator("1,3,");
        try{
            calculator.add();
        }catch (NumberFormatException e){
            assertEquals("Number expected but EOF found", e.getMessage());
        }
    }

    @Test
    public void testAddWithCustomSeparator(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("//;\n1;2");
        assertEquals("3.0", calculator.add());
        calculator = new StringCalculator("//|\n1|2|3");
        assertEquals("6.0", calculator.add());
        calculator = new StringCalculator("//sep\n2sep3");
        assertEquals("5.0", calculator.add());
    }

    @Test
    public void testInvalidSeparator(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("//|\n1|2,3");
        try{
            calculator.add();
        }catch (NumberFormatException e){
            assertEquals("'|' expected but ',' found at position 3", e.getMessage());
        }
    }

    @Test
    public void testInvalidNegativeNumber(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("-1,2");
        try{
            calculator.add();
        }catch (NumberFormatException e){
            assertEquals("Negative not allowed : -1, \n" +
                    "',' expected but '-' found at position 0", e.getMessage());
        }
    }

    @Test
    public void testManyInvalidNegativeNumber(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("2,-4,-5");
        try{
            calculator.add();
        }catch (NumberFormatException e){
            assertEquals("Negative not allowed : -4, -5, \n" +
                    "',' expected but '-' found at position 2", e.getMessage());
        }
    }

    @Test
    public void testAddMultiply(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("1,2,3");
        assertEquals(6.0, calculator.multiply());
    }

    @Test
    public void testAddReturningNumber(){
        Locale.setDefault(Locale.US);
        StringCalculator calculator = new StringCalculator("1.1,2.2");
        assertEquals(3.3, calculator.addReturningNumber());
    }
}