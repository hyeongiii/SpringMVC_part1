package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

//    핸들러가 파라미터로 넘어왔을 때, 이 어댑터가 해당 핸들러를 지원할 수 있는지 판별하는 메서드
    boolean supports(Object handler);

//    어댑터가 실제 컨트롤러를 호출하며, 그 결과로 ModelView 를 반환한다.
//    이전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만, 이젠 어댑터가 실제 컨트롤러를 호출한다.
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
