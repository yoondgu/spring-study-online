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
<%@ include file="common/nav.jsp" %>
<div class="container my-3">
    <div class="row mb-3">
        <div class="col">
            <div class="border p-3 bg-light">
                <h1 class="mb-4">회원가입 완료.</h1>
                <p class=""><strong>홍길동</strong>님 회원가입이 완료되었습니다.</p>
                <p class=""><a href="/login" class="btn btn-primary btn-lg">로그인</a> 후 다양한 서비스를 사용해보세요</p>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>