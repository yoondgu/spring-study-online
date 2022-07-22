<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp" %>
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
<c:set var="menu" value="dashboard" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<div class="row my-3">
		<div class="col">
			<h1 class="fs-5 border p-2">내가 등록한 강의 목록</h1>
		</div>
	</div>
    <div class="row mb-3">
        <div class="col">
        	<p>강의 목록을 확인하세요</p>
        	<table class="table">
        		<colgroup>
        			<col width="20%">
        			<col width="15%">
        			<col width="*">
        			<col width="10%">
        			<col width="10%">
        			<col width="10%">
        		</colgroup>
        		<thead>
        			<tr>
        				<th></th>
        				<th>카테고리</th>
        				<th>제목</th>
        				<th>리뷰갯수</th>
        				<th>리뷰평점</th>
        				<th>수강신청수</th>
        			</tr>
        		</thead>
        		<tbody>
        		<c:choose>
        			<c:when test="${empty courses }">
	        			<tr>
	        				<td colspan="6" class="text-center">등록된 강의가 없습니다.</td>
	        			</tr>
        			</c:when>
        			<c:otherwise>
		        		<c:forEach var="course" items="${courses }">
		        			<tr class="align-middle">
		        				<td><img src="/resources/images/course/${course.imagename }" class="img-thumbnail rounded-0"></td>
		        				<td>
		        				<c:forEach var="cc" items="${course.categories }">
			        				<div class="mb-1">${cc.category.name }</div>
		        				</c:forEach>
			        			</td>
		        				<td><a href="/courses/detail?no=${course.no }">${course.title }</a></td>
		        				<td>${course.reviewCount }</td>
		        				<td>${course.reviewScore }</td>
		        				<td>${course.studentCount }</td>
		        			</tr>
		        		</c:forEach>
        			</c:otherwise>
        		</c:choose>
        		</tbody>
        	</table>
        </div>
    </div>
</div>
</body>
</html>