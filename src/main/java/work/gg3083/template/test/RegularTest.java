package work.gg3083.template.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gimi
 * @date 2019/8/15 15:05
 */
public class RegularTest {

    public static void main(String[] args) {
        String str = "/sh/sss";
        Pattern pattern = Pattern.compile("^(/sh)*$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.err.println(matcher.group());
        }

    }

}

