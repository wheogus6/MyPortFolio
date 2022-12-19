<%--
  Created by IntelliJ IDEA.
  User: wheogus6
  Date: 2022/12/17
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href =
            "space_man.png" rel="shortcut icon" type="image/x-icon">
    <title>Hello</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/registerFormStyle.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

    <script src="https://kit.fontawesome.com/437ac62fbf.js" crossorigin="anonymous"></script>
    <script src="../p5.min.js"></script>
</head>

<body>
<nav class="nav">
    <div class = "title_logo">
        <i class="fa-solid fa-user-astronaut"></i>
        <a href = "<c:url value='/'/>">Hello</a>
    </div>

    <ul class="list">
        <li><a href="<c:url value='/'/>">홈</a></li>
        <li><a href="<c:url value='/board/list'/>">게시판</a></li>
        <li><a href="<c:url value=''/>">로그인</a></li>
        <li><a href="<c:url value='/register/addUser'/>">회원가입</a></li>
    </ul>

    <div class="icon">
        <li><a href = "https://github.com/"><i class="fa-brands fa-github"></i></a></li>
        <li><a href = "https://www.google.co.kr/"><i class="fa-brands fa-google"></i></a></li>
    </div>
</nav>
<div style="margin:auto;text-align:center;">

<form action="<c:url value="/register/plusUser"/>" method="post">
    <div class="title">회원가입</div>
<%--    <div id="msg" class="msg"><form:errors path="id"/></div>--%>
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" placeholder="8~12자리">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" placeholder="8~12자리">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@aaaa.com">
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" placeholder="2020-12-31">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <button id="saveUserBtn" class="save-userInfo">회원 가입</button>
<%--    <button id="saveUserBtn" class="save-userInfo" onclick="<c:url value="/register/plusUser"/>">회원 가입</button>--%>
</form>
</div>
</body>



</html>