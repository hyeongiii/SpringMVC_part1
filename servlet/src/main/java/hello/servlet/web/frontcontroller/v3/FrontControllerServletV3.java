package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
//        생성자 내부에 매핑을 해줌으로써 초기화 작업을 진행한다.
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        요청 URI 를 받을 수 있다.
        String requestURI = request.getRequestURI();
//        요청 URI 에 맞는 컨트롤러 객체를 꺼낸다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

//        paramMap 넘겨주기
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();// 논리 이름 : new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

//    view 의 논리 이름을 가지고 실제 물리 위치를 찾는 메서드
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //    getParameterNames() 로 모든 파라미터 다 꺼낸 뒤, 각 변수들을 돌리면서 맵에 등록해준다.
//    목적성이 분명한 로직이기 때문에 메서드로 뽑아 코드를 작성한다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
