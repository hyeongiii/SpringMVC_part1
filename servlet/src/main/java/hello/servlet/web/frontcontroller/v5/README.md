V5 - 유연한 컨트롤러
---
***

### 어댑터 패턴
> 이전까지의 컨트롤러는 한가지 방식의 컨트롤러 인터페이스만 사용할 수 있었다.  
> 다시 말해, ControllerV3과 ControllerV4는 완전히 다른 인터페이스이기 때문에 호환이 불가능했다.  
> 이 때, 어댑터 패턴을 사용하면 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있게 된다.

<br>

### V5 구조
> <code>클라이언트 (HTTP 요청)</code>   
> -> <code>Front Controller</code> -> <code>핸들러 매핑 정보</code> (1. 핸들러 조회)  
> -> <code>Front Controller</code> -> <code>핸들러 어댑터 목록</code> (2. 핸들러를 처리할 수 있는 핸들러 어댑터 조회)
> -> <code>Front Controller</code> -> <code>핸들러 어댑터</code> -> <code>핸들러(컨트롤러)</code> (3. 클라이언트 요청에 따른 핸들러 호출)  
> -> <code>핸들러 어댑터</code> -> <code>Front Controller</code> (4. ModelView 반환)  
> -> <code>Front Controller</code> -> <code>viewResolver</code> (5. viewResolver 호출한뒤, MyView 반환)
> -> <code>Front Controller</code> -> <code>MyView</code> (4. render(model) 호출)  
> -> <code>HTML 응답</code>

- 다양한 컨트롤러를 사용할 수 있도록 연결해주는 어댑터를 추가함으로써 컨트롤러의 호출이 유연해질 수 있다.
- 핸들러 : 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경한 것이다.  
  (어댑터가 있기 때문에 컨트롤러의 개념 뿐만 아니라 어떤 것이든 해당하는 종류의 어댑터만 있다면 모두 처리 가능하다.)