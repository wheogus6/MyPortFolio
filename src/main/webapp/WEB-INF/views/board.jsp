<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/in' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href =
            "space_man.png" rel="shortcut icon" type="image/x-icon">
    <title>Hello</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">

    <script src="https://kit.fontawesome.com/437ac62fbf.js" crossorigin="anonymous"></script>
    <script src="../p5.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
        <li><a href="<c:url value='/game'/>">게임</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
<%--<c:if test="${loginId eq null}">--%>
        <li><a href="<c:url value='/register/addUser'/>">회원가입</a></li>
<%--</c:if>--%>
    </ul>

    <div class="icon">
        <li><a href = "https://github.com/"><i class="fa-brands fa-github"></i></a></li>
        <li><a href = "https://www.google.co.kr/"><i class="fa-brands fa-google"></i></a></li>
    </div>

</nav>
<script>
    let msg = "${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>
<div class="container">
    <link rel="stylesheet" href="<c:url value='/css/boardStyle.css'/>">
    <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form id="form" class="frm" action="" method="post">
        <input type="hidden" name="num" value="${boardDto.num}">

        <input name="title" type="text" value="<c:out value= '${boardDto.title}'/>" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><br>
        <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><c:out value='${boardDto.content}'/></textarea><br>

        <c:if test="${mode eq 'new'}">
            <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
        </c:if>

        <c:if test="${boardDto.writer eq loginId}">
            <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
            <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
    </form>
</div>
<script>
    $(document).ready(function(){
        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.title.value=="") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }
            if(form.content.value=="") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }

        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let isReadonly = $("input[name=title]").attr('readonly');
            // 1. 읽기 상태이면, 수정 상태로 변경
            if(isReadonly=='readonly') {
                $(".writing-header").html("게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                return;
            }
            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list${searchCondition.queryString}'/>";
        });

    });
</script>
</body>
<br>
<!-- 댓글 작성란 -->
<div>
    <c:if test="${mode ne 'new'}">
        <link rel="stylesheet" href="<c:url value='/css/commentStyle.css'/>">
    <form method="post" class="commentWrite" action="<c:url value="/comments/write"/>">

        <p>
            <textarea class="commentArea" rows="5" cols="50" name="comment" placeholder="댓글 쓰기..."></textarea>
        </p>
        <p>
            <input type="hidden" name="num" value="${boardDto.num}">
            <button type="submit" class="comment write-btn"><i class="fa fa-pencil"></i> 댓글 작성</button>
            <br>
            <br>
        <h3 class="commentCnt">댓글 ${boardDto.comment_cnt}개</h3>
        <br><br>
        </p>
    </form>
</c:if>
</div>


<!-- 댓글 리스트 -->

<c:forEach items="${comment}" var="comment">
    <li>
        <form id="commentList" class="comment_list" action="" method="">

            <input type="hidden" name="cno" value="${comment.cno}">
            <input type="hidden" name="num" value="${comment.num}">
<%--            <input  name="commenter" value="${comment.commenter}">--%>

<%--            <p>${comment.cno}</p>--%>
            <p class="commenter">작성자 : ${comment.commenter}</p>
<%--            <p>${comment.num}</p>--%>
            <textarea rows="3" name="comment" ${commentMode=="modify" ? "" : "readonly='readonly'"}>${comment.comment}</textarea>
            <p>
                <c:if test="${comment.commenter eq loginId}">
                    <a class="commentMod" href="<c:url value="/comments/modify?num=${boardDto.num}&cno=${comment.cno}"/>" ><i class="fa fa-edit"></i> 댓글 수정</a>
<%--                    <a class="commentMod" href= "javascript:popup()" ><i class="fa fa-edit"></i> 댓글 수정</a>--%>
                </c:if>
            </p>
            <br>
        </form>
    </li>
</c:forEach>

<%--<script>--%>
<%--    function popup(){--%>
<%--        let url = "/MyPortFolio/comments/modify?num=${boardDto.num}&cno=${comment.cno}";--%>

<%--        let name = "popup test";--%>
<%--        let option = "witdh = 500, height = 500, top = 100, left = 200";--%>

<%--        window.open(url, name, option);--%>
<%--    }--%>
<%--</script>--%>


<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--    $("#comment_modify").on("click", function() {--%>
<%--        let form = $("#commentList");--%>
<%--        let isReadonly = $("textarea[name=comment]").attr('readonly');--%>
<%--        // 1. 읽기 상태이면, 수정 상태로 변경--%>
<%--        if (isReadonly == 'readonly') {--%>
<%--            $("textarea[name=comment]").attr('readonly', false);--%>
<%--            $("#comment_modify").html("댓글작성");--%>
<%--            return;--%>
<%--        }--%>
<%--        form.attr("action", <c:url value="/comments/modify${comment}"/>);--%>
<%--        form.attr("method", "post");--%>
<%--        if (formCheck())--%>
<%--            form.submit();--%>
<%--    });--%>

<%--        &lt;%&ndash;$("#comment_delete").on("click", function(){&ndash;%&gt;--%>
<%--        &lt;%&ndash;    if(!confirm("정말로 삭제하시겠습니까?")) return;&ndash;%&gt;--%>
<%--        &lt;%&ndash;    let form = $("#commentList");&ndash;%&gt;--%>
<%--        &lt;%&ndash;    form.attr("action", "<c:url value='/comment/delete'/>");&ndash;%&gt;--%>
<%--        &lt;%&ndash;    form.attr("method", "post");&ndash;%&gt;--%>
<%--        &lt;%&ndash;    form.submit();&ndash;%&gt;--%>
<%--        &lt;%&ndash;});&ndash;%&gt;--%>

<%--        $("#comment_delete").on("click", function(){--%>
<%--            let cno = $(this).parent().attr("data-cno");--%>
<%--            let num = $(this).parent().attr("data-num");--%>

<%--            $.ajax({--%>
<%--                type: 'delete',--%>
<%--                url: '/myPortFolio/comments/delete/'+cno+'?num='+num,--%>
<%--                success : function (result){--%>
<%--                    alert(result)--%>
<%--                },--%>
<%--                error: function (){--%>
<%--                    alert("error")--%>
<%--                }--%>
<%--            });--%>



<%--        });--%>


<%--    });--%>
<%--</script>--%>

</body>

</html>