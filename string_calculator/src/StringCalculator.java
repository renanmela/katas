
import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String number) {
        verifyInvality(number);
        List<String> firstSeparation = verifyIfHaveCustomSeparator(number);
        List<Integer> separatedNumbers = separateByNewlines(firstSeparation);
        int sum = 0;
        for(int i = 0; i < separatedNumbers.size(); i++){
            sum += separatedNumbers.get(i);
        }
        return Integer.toString(sum);
    }

    public List<String> verifyIfHaveCustomSeparator(String number){
        if(number.contains("//")){
            String separator = getSeparator(number);
            String delimeter = "//" + separator+ "\n";
            number = number.replace(delimeter, "");
            List<String> separatedByCustom = separateByCustom(separator, number);
            return separatedByCustom;
        }else {
            List<String> separatedByComma = separateByComma(number);
            return separatedByComma;
        }
    }

    public String getSeparator(String number) {
        String regex = "\\D{2}(.+)\\n.*";
        String separator = number.replaceAll(regex, "$1");
        return separator;
    }

    public List<String> separateByCustom(String separator, String number){
        String separeRegex = verifyIfHaveSymbol(separator);
        String[] separatedByCustom = number.split(separeRegex);
        ArrayList<String> separatedNumbers = new ArrayList<>();
        for(int i = 0; i < separatedByCustom.length; i++){
            separatedNumbers.add(separatedByCustom[i]);
        }
        return separatedNumbers;
    }

    public String verifyIfHaveSymbol(String separator){
        Pattern p = Pattern.compile("\\W");
        Matcher matcher = p.matcher(separator);
        boolean ifHaveSymbol = matcher.find();
        if(ifHaveSymbol){
            return treatSymbols(separator);
        }
        else return separator;
    }

    public String treatSymbols (String separator){
        String buildedSeparator = "";
        for(int i = 0; i < separator.length(); i++){
            buildedSeparator += buildAndAdjustSymbol(separator.charAt(i));
        }
        return buildedSeparator;
    }

    public String buildAndAdjustSymbol(char symbol){
        String symbolToString = String.valueOf(symbol);
        if(symbolToString.matches("\\W")){
            return "[" + symbol + "]";
        }
        else return symbolToString;
    }

    public List<Integer> separateByNewlines(List<String> numbers){
        ArrayList<Integer> separatedByNewlines = new ArrayList<>();
        for(int i = 0; i < numbers.size(); i++){
            if(numbers.get(i).contains("\n")){
                String[] separate = numbers.get(i).split("\n");
                    for(int j = 0; j < separate.length; j++){
                        separatedByNewlines.add(Integer.parseInt(separate[j]));
                    }
            }else separatedByNewlines.add(Integer.parseInt(numbers.get(i)));
        }
        return separatedByNewlines;
    }

    public List<String> separateByComma(String number){
        ArrayList<String> separatedByComma = new ArrayList<>();
        String[] numbers= number.split(",");
        for(int i = 0; i < numbers.length; i++){
            separatedByComma.add(numbers[i]);
        }
        return separatedByComma;
    }

    public void verifyInvality(String number) {
        try {
            InvalidTypes(number);
        } catch (NumberFormatException e) {
           e.printStackTrace();
        }
    }
    public void InvalidTypes(String number) {
        if(number.contains(",\n")){
            throw new NumberFormatException("Number expected but '\\n' found at position "+ (number.indexOf(",\n") + 1));
        }else if(number.contains("\n,")){
            throw new NumberFormatException("Number expected but '\\n' found at position "+ (number.indexOf("\n,") + 1));
        }
        if((number.charAt(number.length()-1)) == ','){
            throw new NumberFormatException("Number expected but EOF found");
        }else if((number.charAt(0)) == ','){
            throw new NumberFormatException("Number expected but EOF found");
        }
    }
}
