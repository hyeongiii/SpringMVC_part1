package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        System.out.println(username);
        String age = request.getParameter("age");
        System.out.println(age);
        System.out.println("[단일 파라미터 조회 - end");
        System.out.println();

//        http://localhost:8080/request-param?username=hello&age=20&username=hello2
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

//        파라미터 이름은 같은데 값이 여러개일 경우, 쿼리 파라미터 상 앞쪽에 위치한 파라미터가 먼저 출력된다.

    }
}

/*
* application/x-www-form-urlencoded 형식은 쿼리 파라미터 형식과 동일하기 때문에, 쿼리 파라미터 조회 메서드를 그대로 사용하여 조회할 수 있다.
* 클라이언트(브라우저) 입장에서는 두 방식에 차이가 있지만, 서버 입장에서는 둘의 형식이 동일하기 때문에 request.getParameter() 로 조회가 가능하다.
* */