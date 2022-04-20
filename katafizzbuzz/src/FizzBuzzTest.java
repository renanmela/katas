import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    @Test
    void testFizz(){
        FizzBuzz fizzBuzz = new FizzBuzz(6);
        assertEquals("Fizz", fizzBuzz.toFizzBuzz());
    }

    @Test
    void testFizzHavingThree(){
        FizzBuzz fizzBuzz = new FizzBuzz(37);
        assertEquals("Fizz", fizzBuzz.toFizzBuzz());
    }

    @Test
    void testBuzz(){
        FizzBuzz fizzBuzz = new FizzBuzz(10);
        assertEquals("Buzz", fizzBuzz.toFizzBuzz());
    }

    @Test
    void testBuzzHavingFive(){
        FizzBuzz fizzBuzz = new FizzBuzz(58);
        assertEquals("Buzz", fizzBuzz.toFizzBuzz());
    }

    @Test
    void testNotFizzOrBuzz(){
        FizzBuzz fizzBuzz = new FizzBuzz(7);
        assertEquals("7", fizzBuzz.toFizzBuzz());
    }

    @Test
    void testFizzBuzz(){
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        assertEquals("FizzBuzz", fizzBuzz.toFizzBuzz());
    }
}