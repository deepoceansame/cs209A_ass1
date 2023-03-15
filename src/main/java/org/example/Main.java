package org.example;

import java.util.regex.Pattern;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       OnlineCoursesAnalyzer analyzer = new OnlineCoursesAnalyzer("./src/main/resources/local.csv");
        System.out.println(Pattern.quote("ScIence"));
        String a = "ScIence";
        String b = "ScienCe and Tech";
        System.out.println(
                Pattern.compile(Pattern.quote(b), Pattern.CASE_INSENSITIVE).matcher(a).find()
        );


    }

}