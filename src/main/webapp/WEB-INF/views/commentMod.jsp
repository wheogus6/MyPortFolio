<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/in' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>

<head>

  <title>comment mod or del</title>

  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>

<body>

    <form method="post" action="<c:url value="/comments/modify"/>">

      <p>${comment.cno}</p>
      <p>
        <label>작성자</label>
        <input type="text" name="commneter" readonly="readonly" value="${comment.commenter}"/>
      </p>
      <textarea rows="3" cols="50" name="comment">${comment.comment}</textarea>
      <p>
        <input type="hidden" name="cno" value="${comment.cno}">
        <input type="hidden" name="num" value="${comment.num}">
         <button type="submit">수정</button>
         <button type="submit">삭제</button>
      </p>
    </form>
</body>

</html>