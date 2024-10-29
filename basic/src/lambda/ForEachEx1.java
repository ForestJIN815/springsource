package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachEx1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("사과", "딸기", "수박", "바나나", "배", "메론");

        // List<String> list1 = new ArrayList<>();
        // list1.add("사과");
        // list1.add("딸기");
        // list1.add("수박");
        // list1.add("바나나");

        for (String s : list) {
            System.out.println(s);
        }

        // forEach
        // <? super String> : String 타입이나 상위 타입
        // Consumer<String> c = (x) -> System.out.println(x);
        // c.accept("")

        // 하나씩 꺼내서 출력하는 용도로 주로 사용
        list.forEach((x) -> System.out.println(x));
    }
}
