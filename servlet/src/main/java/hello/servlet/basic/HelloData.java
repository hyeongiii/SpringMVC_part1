package hello.servlet.basic;

/*
JSON 형식의 데이터는 그대로 사용되지 않고 객체에 담겨 사용되기 때문에,
JSON 형식의 데이터를 파싱해줄 객체를 하나 생성한다.
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {

    private String username;

    private int age;


}
