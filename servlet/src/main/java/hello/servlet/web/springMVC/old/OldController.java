package hello.servlet.web.springMVC.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 빈 등록(스프링 빈 이름 설정)
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        // 논리적 이름만 넣고, 실제 물리적 위치를 찾는 것은 뷰리졸버에게 위임한다.
        return new ModelAndView("new-form");
    }
}
