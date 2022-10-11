<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  // JSP 도 결국엔 서블릿으로 바뀌기 때문에, request, response 는 그냥 사용가능하다. (사실상 로직이 그대로 호출되는 것과 같음)
  // < % 표시 뒤에 자바 코드를 삽입할 수 있다.
  MemberRepository memberRepository = MemberRepository.getInstance();

  System.out.println("MemberSaveServlet.service");

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
