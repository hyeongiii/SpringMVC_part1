package hello.springmvc.basic.request;

import hello.springmvc.basic.requestmapping.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.HandshakeResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    // 뷰 조회를 진행하지 않고 HTTP 응답 메세지에 반환값을 넣어 보낸다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String userNaem,
                                 @RequestParam("age") int age) {

        log.info("username={}, age={}", userNaem, age);
        return "ok";
    }

    // RequestParma 변수명과 파라미터명이 같을 경우, 변수명을 생략할 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 파라미터 명이 요청 파라미터 명과 일치할 경우, @RequestParam 을 기입하지 않아도 된다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 파라미터 필수 여부
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) { // 기본형에는 null 값이 들어갈 수 없으므로, required 속성을 false 로 한다면 변수 타입을 기본형으로 하면 안된다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 파라미터 기본값 적용
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) { // 기본형에는 null 값이 들어갈 수 없으므로, required 속성을 false 로 한다면 변수 타입을 기본형으로 하면 안된다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 모든 파라미터를 맵으로 받는 방법 (파라미터를 Map으로 조회하기)
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) { // 기본형에는 null 값이 들어갈 수 없으므로, required 속성을 false 로 한다면 변수 타입을 기본형으로 하면 안된다.

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }
}
