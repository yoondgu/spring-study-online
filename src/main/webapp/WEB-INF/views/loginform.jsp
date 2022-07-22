<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>스프링 온라인</title>
</head>
<body>
<c:set var="menu" value="login"/>
<%@ include file="common/nav.jsp" %>
<div class="container my-3">
	<div class="row my-3">
		<div class="col">
			<h1 class="fs-5 border p-2">로그인</h1>
		</div>
	</div>
    <div class="row mb-3">
        <div class="col">
        	<c:if test="${param.fail eq 'invalid'}">
	        	<div class="alert alert-danger ${param.fail eq 'invalid' ? '' : 'd-none'}">
	        		 <strong>로그인 실패</strong> 아이디 혹은 비밀번호가 올바르지 않습니다.
	        	</div>
        	</c:if>
        	<c:if test="${param.fail eq 'deny'}">
	        	<div class="alert alert-danger ${param.fail eq 'deny' ? '' : 'd-none'}">
	        		 <strong>서비스 거부</strong> 로그인이 필요한 서비스입니다.
	        	</div>
        	</c:if>
            <p>이메일과 비밀번호를 입력하세요</p>
            <form id="form-login" class="border bg-light p-3" method="post" action="login">
                <div class="mb-3">
                	<label for="email-field" class="form-label">이메일</label>
                	<input type="text" class="form-control" name="email" id="email-field" placeholder="이메일을 입력하세요"/>
                </div>
                <div class="mb-3">
                	<label for="password-field" class="form-label">비밀번호</label>
                	<input type="password" class="form-control" name="password" id="password-field" placeholder="비밀번호를 입력하세요"/>
                </div>
                <div class="text-end">
                	<a href="/" class="btn btn-secondary">취소</a>
                	<button type="submit" class="btn btn-primary">로그인</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function() {
	// $("#form-login") : 아이디가 form-login인 엘리먼트를 선택 -> jQuery 객체(선택된 엘리먼트 + 메소드)가 얻어짐
	// .submit(함수) : 선택된 엘리먼트에서 submit 이벤트가 발생하면 실행될 함수를 등록시킨다.
	// .val() : 선택된 입력요소의 값을 읽어온다.
	// .text() : 선택된 엘리먼트가 포함하는 텍스트 컨텐츠를 읽어온다.
	// .htmlll() : 선택된 엘리먼트가 포함하는 html 컨텐츠를 읽어온다.
	// .attr(name) : 선택된 엘리먼트에서 지정된 속성명의 속성값을 읽어온다.
	$("#form-login").submit(function() {
		let emailValue = $("#email-field").val();
		if (emailValue === "") {
			alert("이메일은 필수 입력값입니다.");
			return false;
		}
		
		let passwordValue = $("#password-field").val();
		if (passwordValue === "") {
			alert("비밀번호는 필수 입력값입니다.");
			return false;
		}
		
		// true이면 해당 이벤트의 기본 동작이 수행된다.
		// false이면 해당 이벤트의 기본 동작이 수행되지 않는다.
		return true;
	});
});
</script>
</body>
</html>