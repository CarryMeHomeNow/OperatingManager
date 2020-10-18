import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongfk on 2020/9/2.
 * @version 1.0
 */
@K
public class SizeTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("99");
        System.out.println(stringList.size());
        System.out.println(Integer.valueOf(stringList.size()) * 2);

    }

}
