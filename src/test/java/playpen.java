import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import static junit.framework.TestCase.fail;

public class playpen {

    @Test
    public void testPatterSplit(){
        Pattern pattern = Pattern.compile(",");
        String[] split = pattern.split("12,34,56,78");
        System.out.println(Arrays.asList(split));
    }

    @Test
    public void validateMasterCardNumber() {
        Pattern pattern = Pattern.compile
                ("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
//        boolean matches;
//        matches = pattern.matcher("5112345678890123").matches();
//        System.out.println(matches);
//        matches = pattern.matcher("22211012345678901").matches();
//        System.out.println(matches);
//        matches = pattern.matcher("2721012345678901").matches();
//        System.out.println(matches);

        Collection <String> numbers = Arrays.asList(
                "5112345678890123",
                "22211012345678901",
                "2721012345678901");

        List<String> valids = numbers.stream().filter(pattern.asPredicate())
                .collect(Collectors.toList());
        System.out.println(valids);
    }

    @Test
    public void invalidRegex(){
        try{
            "some-string".matches("\\w\\d[]");
        }catch (PatternSyntaxException e){
            System.out.println("Index: " + e.getIndex());
            System.out.println("Pattern: "+ e.getPattern());
            System.out.println("Message: "+ e.getMessage());
        }
    }

    @Test
    public void testEscapeDate(){
        boolean matches = " (212) 345-6789 ".matches("^\\s*\\(\\d{3}\\)\\s*\\d{3}-\\d{4}\\s*$");
        System.out.println(matches);
    }

//    @Test
//    public void testCaptureGroup(){
//        Pattern pattern = Pattern.compile("(\\w+)(-(\\w+))+-(\\w+)");
//        display(pattern, "securities-development-equities-valuation-americas");
//        display(pattern, "fixed_income-development-equities-asia");
//        display(pattern, "fx-development-emea");
//    }

    @Test
    public void testReplaceAllWithReferences(){
        List<String> list = Arrays.stream(new String[]{
                "securities-development-equities-valuation-americas",
                "securities-development-equities-valuation-americas",
                "fixed_income-development-equities-asia",
                "fx-development-emea"}).map(
                        label -> label.replaceAll("(?<business>\\w+)(-(\\w+))+-(?<region>\\w+)",
                                "Region:${region}, Unit:$1"))
                .collect(Collectors.toList());
        for(String line : list){
            System.out.println(line);
        }
    }

    @Test
    public void test(){
        String text = "The \"rain\" in spain \"falls\" mainly on \"the plain\"";
        Pattern pattern = Pattern.compile("(\"\\w+\")|(\\w+)");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            System.out.println("Group 1: "+ matcher.group(1) + ";");
            System.out.println("Group 2: "+ matcher.group(2) + ";");
        }
    }

    @Test
    public void testDotAll(){
        String regex = "\u00E0";
        String text = "\u00C0";

        System.out.printf("regex: %s, text: %s%n", regex, text);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);
//        String regex = "abc.+def";
//        String text = "abc\r\ndef";
//        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
//        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            System.out.println("Match!");
            return;
        }
        fail("No match");
    }

    @Test
    public void testMatches(){
        boolean matches = Pattern.matches("[\\w\\s]+", "She sells sea shells");
        System.out.println(matches);
    }

    @Test
    public void testSplitAsStream(){
        String text = "One, Two, , Buckle,   , My_Shoe";
        Pattern pattern = Pattern.compile("(,\\s+)");
        List<String> list = pattern
                .splitAsStream(text)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
