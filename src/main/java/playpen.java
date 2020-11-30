import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class playpen {
    public static void main(String[] args) {
        testMatches();
        testMatches2();
        testMatches3();
        testMatches4();
        testMatches5();
        testMatches6();
        testMatches7();
        testMatches8();
        testMatches9();

        testStringMethods();

    }
    public static void testMatches(){
        System.out.println("abcdefg".matches("c.e"));
    }

    public static void testMatches2(){
        System.out.println("abcdefg".matches(".b.d.f."));
    }

    public static void testMatches3(){
        System.out.println(Arrays.asList("23,42,64,hike".split(",")));
    }

    public static void testMatches4(){
        System.out.println(Arrays.asList("Anne of the 1000 days".split(" ")));
    }

    public static void testMatches5(){
        System.out.println(Arrays.asList("Anne of the 1000 days".split("xxxx")));
    }

    public static void testMatches6(){
        System.out.println(Arrays.asList("Anne of the 1000 days".split("")));
    }

    public static void testMatches7(){
        System.out.println(Arrays.asList("Anne of the 1000 days".split(".")));
    }

    public static void testMatches8(){
        System.out.println(Arrays.asList("aaaaa".split("a")));
    }

    public static void testMatches9(){
        System.out.println(Arrays.asList("one + one = 2".replace("on.", "1")));
    }

    public static void testStringMethods(){
        String string = "x and tigers and bears, oh my!";
        String replaced = string.replaceAll("q", "Lions");
        System.out.println(string == replaced);
    }


    //Display all captured groups and their sequence number

    public static void display(Pattern pattern, String group){
        Matcher matcher = pattern.matcher(group);
        int count = matcher.groupCount();
        if(matcher.matches()){
            for (int i = -1; i <= count +1; i++){
                System.out.printf("%s.%s%n", i, matcher.group(i));
            }
        }
    }
}
