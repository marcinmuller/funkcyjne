package main.java.pl.mmuller;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class EmailChecker {
    static final Pattern emailPattern =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String,Result<String>> emailChecker = x->{
        if(x==null) return Result.failure("email nie może być null");
        else if(x.length()==0) return Result.failure("emial nie może być pustym ciągiem");
        else if(emailPattern.matcher(x).matches()) return Result.success("email prawidłowy");
        else return Result.failure("email nie pasuje do wzorca");
    };

    public static void main(String[] args) {
        emailChecker.apply(null).bind(success,failure);
        emailChecker.apply("xxx@yyy.pl").bind(success,failure);
        emailChecker.apply("").bind(success,failure);
        emailChecker.apply("ccccdddd.pl").bind(success,failure);
    }

    static Consumer<String> success = x->System.out.println("dobrze "+x);

    static Consumer<String> failure = x-> System.out.println("błąd bo "+x);
}
