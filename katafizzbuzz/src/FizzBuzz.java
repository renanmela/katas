public class FizzBuzz {
    private int num;

    public FizzBuzz(int num) {
        this.num = num;
    }

    private boolean isFizz(){
        if (hasNumber(3)){
            return true;
        }
        return this.num % 3 == 0;
    }

    private boolean isBuzz(){
        if (hasNumber(5)){
            return true;
        }
        return this.num % 5 == 0;
    }

    private boolean hasNumber(int number){
        char number_char = Character.forDigit(number , 10);
        for(int i=0 ; i < numDigits().length; i++){
            if(numDigits()[i] == number_char){
                return true;
            }
        }
        return false;
    }

    private char[] numDigits(){
        String num = String.valueOf(this.num);
        char[] digits = num.toCharArray();
        return digits;
    }

    public String toFizzBuzz(){
        String fizzBuzz = "";
        if(isFizz()){
            fizzBuzz += "Fizz";
        }
        if(isBuzz()){
            fizzBuzz += "Buzz";
        }
        if (isFizz() == false && isBuzz() == false){
            fizzBuzz += num;
        }
        return fizzBuzz;
    }

}
