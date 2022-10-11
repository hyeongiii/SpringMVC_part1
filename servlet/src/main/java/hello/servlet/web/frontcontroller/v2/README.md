V2 - View 분리
---
***
### V1의 문제점
- 모든 컨트롤러에서 뷰로 이동하는 부분을 위한 코드의 중복이 있기 때문에 깔끔하지 않다.  
    ~~~java
    String viewPath = "/WEB-INF/views/new-form.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
    ~~~
- 해당 부분을 깔끔하게 처리하기 위해서는, 뷰를 처리하는 별도의 객체를 만드는 것이다.

<br>

### V2 구조
- 뷰를 처리하는 MyView 라는 객체를 생성하였고, render 메서드 내부에서 forward()를 처리한다.
- ControllerV2의 process 메서드는 MyView 객체를 반환한다.
- ControllerV2를 구현하는 각 컨트롤러는 단순히 MyView 객체를 생성하고 그 안에 뷰 이름을 넣어 반환하기만 하면 된다.  
> 프론트 컨트롤러는 각 컨트롤러가 반환한 MyView 객체를 바탕으로 render()를 호출하는 부분을 일관되게 처리할 수 있다.  
> 그에 따라, 각 컨트롤러에서 뷰로 이동하는 코드의 중복이 사라지고, 코드를 깔끔하게 유지할 수 있게 된다.

