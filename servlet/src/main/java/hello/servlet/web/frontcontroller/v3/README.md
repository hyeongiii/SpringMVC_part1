V3 - Model 추가
---
***

### V2에서 개선해야할 점
1. <b>서블릿 종속성 제거</b>  
   각 컨트롤러 입장에선, <code>HttpServletRequest</code>와 <code>HttpServletResponse</code> 가 필요하지 않다.  
   단지 요청 파라미터 정보만 필요하기 때문에 해당 정보를 자바의 <code>Map</code>으로 넘기도록 한다면, 
   컨트롤러는 서블릿 기술을 모르더라도 동작할 수 있다.  
   또한, 컨트롤러의 서블릿 종속성을 제거함에 따라 컨트롤러에서 HttpServletRequest를 사용할 수 없으므로 
   Model이 별도로 필요하다.


2. <b>뷰 이름의 중복 제거</b>  
    컨트롤러에서 저장하는 뷰 이름에 중복이 발생한다.
   ~~~
   /WEB-INF/views/new-form.jsp
   /WEB-INF/views 와 .jsp 가 중복되는 것을 확인할 수 있다.
   ~~~
   이를 개선하기 위해, 컨트롤러는 뷰의 논리 이름을 반환하고 
   실제 물리 위치의 이름은 프론트 컨트롤러에서 처리하도록 단순화한다.  
    또한 뷰의 실제 위치에 변경사항이 있을 경우 각 컨트롤러에서 수정하는 것이 아닌 프론트 컨트롤러에서만 변경함으로써,
    변경의 지점을 하나로 획일화시킨다. (좋은 설계)
   
<br>

### V3 구조
> <code>클라이언트 (HTTP 요청)</code>   
> -> <code>Front Controller</code> -> <code>매핑 정보</code> (1. 컨트롤러 조회)  
> -> <code>Front Controller</code> -> <code>Controller</code> (2. 컨트롤러 호출한 뒤, 각 컨트롤러에서 Model View 반환)  
> -> <code>Front Controller</code> -> <code>viewResolver</code> (3. viewResolver 호출 및 MyView 반환)  
> -> <code>Front Controller</code> -> <code>MyView</code> (4. render(model) 호출)  
> -> <code>HTML 응답</code>

<br>

### 뷰 리졸버
- 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경한 뒤, 실제 물리 경로가 있는 MyView 객체를 반환한다.
- 뷰 객체를 통해서 HTML 화면을 렌더링 한다.
- 뷰 객체의 'render()' 는 모델 정보도 함꼐 받는다.
- JSP는 <code>request.getAttribute()</code> 로 데이터를 조회하기 때문에, 모델의 데이터를 꺼내서
  <code>request.setAttribute()</code> 로 담아둔다.
- JSP로 포워드 한 뒤, JSP를 렌더링한다.