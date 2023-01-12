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
    <script src="https://kit.fontawesome.com/437ac62fbf.js" crossorigin="anonymous"></script>
    <script src="../p5.min.js"></script>
</head>

<body>
<input type="hidden" name="num" value="${boardDto.num}"> <!--게시물 번호 하나를 from태그에서 빼야함 ㄱdelete redirect시 필요!!! -->
    <form method="post" id="comment" action="">
      <p>${comment.cno}번 댓글</p>
      <p>
        <label>작성자 : </label>
        <input type="text" name="commneter" readonly="readonly" value="${comment.commenter}"/>
      </p>
      <textarea rows="3" cols="50" name="comment">${comment.comment}</textarea>
      <p>
          <input type="hidden" name="cno" value="${comment.cno}">
          <input type="hidden" name="num" value="${comment.num}">

         <button type="submit" id="mod_comment"><i class="fa fa-edit"></i> 수정</button>
         <button type="submit" id="del_comment"><i class="fa fa-trash"></i> 삭제</button>
      </p>
    </form>
<script>
    $("#del_comment").on("click", function (){
        if(!confirm("정말로 삭제하시겠습니까?")) return;
        let comment = $("#comment");
        comment.attr("action", "<c:url value="/comments/delete"/>");
        comment.attr("method", "post");
    })
    $("#mod_comment").on("click", function (){
        let comment = $("#comment");
        comment.attr("action", "<c:url value="/comments/modify"/>");
        comment.attr("method", "post");
    })
</script>
</body>

</html>