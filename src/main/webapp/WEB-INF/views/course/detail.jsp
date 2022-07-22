<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.0/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>스프링 온라인</title>
</head>
<body>
<c:set var="menu" value="course" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<div class="row mb-3">
		<div class="col-6">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/courses" class="text-decoration-none">전체</a></li>
					<li class="breadcrumb-item active" aria-current="page">프로그래밍</li>
				</ol>
			</nav>
		</div>
		<div class="col-6 text-end">
			<form>
				<div class="input-group">
					<input type="text" class="form-control rounded-0"  name="keyword">
					<button class="btn btn-success btn-sm rounded-0" type="button" >검색</button>
				</div>
			</form>				
		</div>
	</div>
	<div class="row mb-3 bg-dark p-3">
		<div class="col-4">
			<img src="/resources/images/course/${course.imagename }" class="img-thumbnail" />
		</div>
		<div class="col-8 bg-dark p-3 ps-5 text-white">
			<c:forEach var="cat" items="${course.categories }">
				<span>${cat.category.name }</span>
			</c:forEach>
			<h3 class="w-bold mb-5">${course.title }</h3>
			<p class="m-0">${course.user.name }</p>
			<p class="m-0">
				<i class="bi ${course.reviewScore gt 0 ? 'bi-star-fill' : 'bi-star' }"></i>
				<i class="bi ${course.reviewScore gt 1 ? 'bi-star-fill' : 'bi-star' }"></i>
				<i class="bi ${course.reviewScore gt 2 ? 'bi-star-fill' : 'bi-star' }"></i>
				<i class="bi ${course.reviewScore gt 3 ? 'bi-star-fill' : 'bi-star' }"></i>
				<i class="bi ${course.reviewScore gt 4 ? 'bi-star-fill' : 'bi-star' }"></i>
				<!-- 
				<c:forEach begin="1" end="${course.reviewScore }">
					<i class="bi bi-star-fill"></i>
				</c:forEach>
				<c:forEach begin="1" end="${5 - course.reviewScore }">
					<i class="bi bi-star"></i>
				</c:forEach>
				 -->
				(${course.reviewCount })
			</p>
			<p class="m-0">
				<i class="bi bi-tags-fill me-3"></i>
				<c:forEach var="item" items="${course.tags }">
					<span class="rounded-1 badge text-bg-light me-1">${item.tag }</span>
				</c:forEach>
			</p>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-9">
			<nav>
  				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">강의소개</button>
					<button class="nav-link" id="nav-review-tab" data-bs-toggle="tab" data-bs-target="#nav-review" type="button" role="tab" aria-controls="nav-review" aria-selected="false">수강평</button>
				</div>
			</nav>
			<div class="tab-content" id="nav-tabContent">
				<div class="tab-pane fade show active pt-3" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">
					<p class="fw-bold">${course.description }</p>
					
					<div class="card mb-3">
						<div class="card-header text-bg-secondary">이런 걸 배웁니다.</div>
						<ul class="list-group list-group-flush">
							<c:forEach var="item" items="${course.learnings }">
	    						<li class="list-group-item">${item.learning }</li>
							</c:forEach>
  						</ul>
					</div>
					
					<div class="card mb-3">
						<div class="card-header text-bg-secondary">이런 분들께 추천드립니다.</div>
						<ul class="list-group list-group-flush">
							<c:forEach var="item" items="${course.recommendations }">
	    						<li class="list-group-item">${item.target }</li>
							</c:forEach>
  						</ul>
					</div>
					
				</div>
				<div class="tab-pane fade" id="nav-review" role="tabpanel" aria-labelledby="nav-review-tab" tabindex="0">
				
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="card">
				<div class="card-body border-bottom ">
					<p class="card-text"><strong class="fs-3"><fmt:formatNumber value="${course.price }" /> 원</strong></p>
					<div>
						<a href="request?no=${course.no }" class="btn btn-success w-100 btn-sm mb-2">수강 신청하기</a>
						<a href="/cart/add?no=${course.no }" class="btn btn-outline-success w-100 btn-sm">수강 바구니에 담기</a>
					</div>
				</div>
				<div class="card-body bg-light">
					<ul>
						<li>지식 공유자 : ${course.user.name }</li>
						<li>강의 수준 : ${course.grade }</li>
						<li>수강 기간 : ${course.period }</li>
						<li>수료증 : ${course.certificateCompletion }</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>