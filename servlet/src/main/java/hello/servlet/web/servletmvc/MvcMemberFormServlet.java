package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        /WEB-INF 경로 안에 JSP 가 있으면 외부에서 직접 JSP 를 호출할 수 없고, 항상 컨트롤러를 통해서 JSP 를 호출해야 한다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
//        컨드롤러에서 뷰로 이동할 때 사용하는 것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        다른 서블릿이나 JSP로 이동할 수 있는 기능으로, JSP 를 호춣할 경우 서버 내부에서 제어권을 JSP 로 넘겨준다.
        dispatcher.forward(request, response);


    }
}

/*
* redirect VS forward
*
* 1) 리다이렉트
*   : 실제 클라이언트에 응답이 갔다가 클라이언트가 redirect 경로로 다시 요청하는 것으로, 클라이언트가 인지할 수 있고 URL 경로도 변경된다.
*
* 2) forward
*   : 서버 내부에서 일어나는 호출로써 다른 서블릿이나 JSP 로 이동하더라도 클라이언트가 전혀 인지하지 못한다.
*
* */