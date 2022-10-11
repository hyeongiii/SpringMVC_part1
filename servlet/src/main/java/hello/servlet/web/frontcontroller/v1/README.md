V1 - 프론트 컨트롤러 도입
---
***
### ControllerV1  
> 서블릿과 비슷한 모양의 컨트롤러 인터페이스(ControllerV1)를 도입하고,  
> form, save, list 에 해당하는 각 컨트롤러는 ControllerV1 인터페이스를 구현한다.   

<br>

### 프론트 컨트롤러
- 프론트 컨트롤러 서블릿 하나만으로 클라이언트의 요청을 받는다.
- 프론터 큰트롤러가 요청에 맞는 컨트롤러를 찾아서 호출해준다. (각 컨트롤러의 매핑 작업이 필요하다.)
- 공통 기능을 처리 가능하다.
- 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다.

<br>

### 프론터 컨트롤러 분석
- 프론트 컨트롤러는 HttpServlet을 상속받으며, urlPatterns를 "/front-controller/v1/*" 로 지정함으로써
  /front-controller/v1 를 포함한 하위 모든 요청을 해당 서블릿에서 받는다.
- 생성자 내부에 각 컨트롤러로 매핑해주기 위한 코드를 작성한다.