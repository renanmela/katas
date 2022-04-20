
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private String number;

    NumberFormat formatter = new DecimalFormat("#0.0");

    public StringCalculator(String number) {
        this.number = number;
    }

    public String add() {
        List<String> firstSeparation = separe();
        List<Double> separatedNumbers = separateByNewlines(firstSeparation);
        Double sum = 0.0;
        for(int i = 0; i < separatedNumbers.size(); i++){
            sum += separatedNumbers.get(i);
        }
        return formatter.format(sum);
    }

    public Double addReturningNumber() {
        List<String> firstSeparation = separe();
        List<Double> separatedNumbers = separateByNewlines(firstSeparation);
        Double sum = 0.0;
        for(int i = 0; i < separatedNumbers.size(); i++){
            sum += separatedNumbers.get(i);
        }
        return Double.valueOf(formatter.format(sum));
    }

    public Double multiply() {
        List<String> firstSeparation = separe();
        List<Double> separatedNumbers = separateByNewlines(firstSeparation);
        Double sum = 1.0;
        for(int i = 0; i < separatedNumbers.size(); i++){
            sum *= separatedNumbers.get(i);
        }
        return Double.valueOf(formatter.format(sum));
    }

    private List<String> separe(){
        if(this.number.contains("//")){
            return separateByCustom();
        }
        else return separateByComma();
    }

    private List<String> separateByComma(){
        verifyInvalidTypes(",");
        String[] numbers = this.number.split(",");
        ArrayList<String> separatedByComma = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++){
            separatedByComma.add(numbers[i]);
        }
        return separatedByComma;
    }

    private List<Double> separateByNewlines(List<String> numbersList){
        ArrayList<Double> separatedByNewlines = new ArrayList<>();
        for(int i = 0; i < numbersList.size(); i++){
            if(numbersList.get(i).contains("\n")){
                String[] separate = numbersList.get(i).split("\n");
                for(int j = 0; j < separate.length; j++){
                    separatedByNewlines.add(Double.valueOf(separate[j]));
                }
            }else separatedByNewlines.add(Double.valueOf(numbersList.get(i)));
        }
        return separatedByNewlines;
    }

    private List<String> separateByCustom(){
        String separator = getSeparator();
        removeDelimeter(separator);
        verifyInvalidTypes(separator);
        String treatedSeparator = treatSeparatorSymbols(separator);
        String[] separatedByCustom = this.number.split(treatedSeparator);

        ArrayList<String> separatedNumbers = new ArrayList<>();
        for(int i = 0; i < separatedByCustom.length; i++){
            separatedNumbers.add(separatedByCustom[i]);
        }
        return separatedNumbers;
    }

    private String getSeparator() {
        String regex = "\\D{2}(.+)\\n.*";
        String separator = this.number.replaceAll(regex, "$1");
        return separator;
    }

    private void removeDelimeter(String separator){
        String delimeter = "//" + separator + "\n";
        this.number = this.number.replace(delimeter, "");
    }

    private String treatSeparatorSymbols (String separator){
        String treatedSeparator = "";
        for(int i = 0; i < separator.length(); i++){
            treatedSeparator += AdjustSymbol(separator.charAt(i));
        }
        return treatedSeparator;
    }

    private String AdjustSymbol(char symbol){
        String symbolToString = String.valueOf(symbol);
        if(symbolToString.matches("\\W")){
            return "[" + symbol + "]";
        }
        else return symbolToString;
    }

    private void verifyInvalidTypes(String separator) {
        String exceptionMessage = "";

        exceptionMessage = constructExceptionMessage(exceptionMessage, InvalidNegativeNumber());
        exceptionMessage += constructExceptionMessage(exceptionMessage, InvalidFormat());
        exceptionMessage += constructExceptionMessage(exceptionMessage, InvalidStartOrLastPosition());
        exceptionMessage += constructExceptionMessage(exceptionMessage, InvalidSeparator(separator));

        throwExceptionMessageIfExist(exceptionMessage);
    }

    private String constructExceptionMessage(String exceptionMessage, String errorMessage) {
        if(exceptionMessage.isEmpty())
            return errorMessage;
        else{
            return verifyIfHaveNewErrorMessage(errorMessage);
        }
    }

    private String verifyIfHaveNewErrorMessage(String messageException) {
        if(messageException.isEmpty()){
            return "";
        }
        return "\n" + messageException;
    }

    private void throwExceptionMessageIfExist(String exceptionMessage) {
        if(!exceptionMessage.isEmpty()){
            throw new NumberFormatException(exceptionMessage);
        }
    }

    private String InvalidFormat() {
        Pattern p = Pattern.compile("\\W\\W");
        Matcher matcher = p.matcher(this.number);
        if(matcher.find()){
            if(matcher.group().contains("-")) {
                return "";
            }
            return "Number expected but '"+ matcher.group() +"' found at position " + matcher.start();
        }
        return "";
    }

    private String InvalidSeparator(String separator) {
        String number = this.number;
        number = removeSeparatorAndDots(number, separator);
        String invalidSeparator = tryToGetInvalidSeparator(number, separator);
        if(invalidSeparator.isEmpty()){
            return "";
        }
        else if(invalidSeparator.contains("\n")){
            return "";
        }
        else {
            Pattern p = Pattern.compile(invalidSeparator);
            Matcher matcher = p.matcher(this.number);
            matcher.find();
            return "'" + separator + "' expected but '" + matcher.group() + "' found at position " + matcher.start();
        }
    }

    private String tryToGetInvalidSeparator(String number, String separator){
        Pattern p = Pattern.compile("\\D+");
        Matcher matcher = p.matcher(number);
        if(matcher.find()){
            String invalidSeparator = matcher.group();
            return invalidSeparator;
        }
        return "";
    }

    private String removeSeparatorAndDots(String number, String separator){
        String treatedSeparator = treatSeparatorSymbols(separator);
        number = number.replaceAll(treatedSeparator, "");
        number = number.replaceAll("[.]", "");
        return number;
    }

    private String InvalidStartOrLastPosition() {
        Pattern p = Pattern.compile("\\W$");
        Matcher matcher = p.matcher(this.number);
        if(matcher.find()){
            return "Number expected but EOF found";
        }
        return "";
    }

    private String InvalidNegativeNumber() {
        Pattern regex = Pattern.compile("-\\d");
        Matcher matcher = regex.matcher(this.number);
        if(matcher.find()){
            return "Negative not allowed : " + matchesNegativeNumbers(regex);
        }
        return "";
    }

    private String matchesNegativeNumbers(Pattern regex) {
        Matcher matcher = regex.matcher(this.number);
        String negativeNumber;
        String allNegativeNumbers = "";

        for(int i = 0; i < countMatches(regex); i++){
            matcher.find();
            negativeNumber = matcher.group();
            allNegativeNumbers += negativeNumber + ", ";
            this.number.replaceFirst("-\\d","");
        }

        return allNegativeNumbers;
    }

    private int countMatches(Pattern regex) {
        Matcher matcher = regex.matcher(this.number);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

}
