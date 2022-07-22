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
<c:set var="menu" value="register"/>
<%@ include file="common/nav.jsp" %>
<div class="container my-3">
	<div class="row my-3">
		<div class="col">
			<h1 class="fs-5 border p-2">회원가입</h1>
		</div>
	</div>
    <div class="row mb-3">
        <div class="col">
            <p>이메일, 비밀번호, 이름, 전화번호를 입력하세요.</p>
            <!-- 파일을 제출할 때에는 enctype을 아래와 같이 설정한다. -->
            <form:form class="border bg-light p-3" method="post"  action="register" modelAttribute="userRegisterForm" enctype="multipart/form-data">
                <div class="mb-3">
                	<label for="email-field" class="form-label">이메일</label>
                	<form:input class="form-control" path="email" id="email-field" placeholder="sample@abc.com"/>
                	<form:errors path="email" class="text-danger small fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                	<label for="password-field" class="form-label">비밀번호</label>
                	<form:password class="form-control" path="password" id="password-field" placeholder="비밀번호는 영어 대소문자와 숫자로 8글자 ~ 20글자입니다."/>
                	<form:errors path="password" class="text-danger small fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                	<label for="name-field" class="form-label">이름</label>
                	<form:input class="form-control" path="name" id="name-field" placeholder="이름은 한글로 2글자 이상입니다."/>
                	<form:errors path="name" class="text-danger small fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                	<label for="tel-field" class="form-label">전화번호</label>
                	<form:input class="form-control" path="phone" id="phone-field" placeholder="010-1234-5678"/>
                	<form:errors path="phone" class="text-danger small fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                	<label for="tel-field" class="form-label">프로필이미지</label>
                	<input type="file" class="form-control" name="profileFile" id="profile-file-field" />
                </div>
                <div class="text-end">
                	<a href="/" class="btn btn-secondary">취소</a>
                	<button type="submit" class="btn btn-primary">회원가입</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>