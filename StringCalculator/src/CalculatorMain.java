import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorMain {
    public static void main(String[] args) {
        String separator = "sep|";
        String regex = "\\W";
        Pattern p = Pattern.compile("\\W");
        Matcher matcher = p.matcher(separator);
        boolean ifHaveSymbol = matcher.find();
        System.out.println(ifHaveSymbol);
        if(ifHaveSymbol){
            System.out.println("Encontrado");
        }else System.out.println("Não encontrado");
    }
}
