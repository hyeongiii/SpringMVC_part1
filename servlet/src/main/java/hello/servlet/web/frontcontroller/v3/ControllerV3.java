package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;


// 이 컨트롤러는 서블릿 기술을 전혀 사용하지 않는다. -> 구현이 매우 단순해지고 테스트 하기 수월하다.
// HttpServletRequest 가 제공하는 파라미터는 프론트 컨트롤러가 paramMap 에 담아서 호출해주면 된다.
// 응답 결과로 ModelView 객체를 반환하는데, ModelView 는 뷰 이름과 뷰에 전달할 Model 데이터를 포함한다.
public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}