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
<c:set var="menu" value="dashboard"/>
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<div class="row mb-3">
		<div class="col-2">
			<div class="list-group">
				<a href="#" class="list-group-item list-group-item-action py-3">대시보드</a>
				<a href="#" class="list-group-item list-group-item-action py-3">수강바구니</a>
				<a href="#" class="list-group-item list-group-item-action py-3">내 학습</a>
				<a href="#" class="list-group-item list-group-item-action py-3">프로필</a>
				<a href="#" class="list-group-item list-group-item-action py-3">지식 공유자 신청</a>
			</div>
		</div>
		<div class="col-10">
			<div class="row mb-3">
				<div class="col-6">
					<div class="card">
						<div class="card-header">내 수강 바구니</div>
						<div class="body p-3">
							<div class="list-group list-group-flush mb-3">
								<a href="" class="list-group-item fw-bold">spring cloud로 개발하는 마이크로 서비스 애플리케이션</a>
								<a href="" class="list-group-item fw-bold">spring cloud로 개발하는 마이크로 서비스 애플리케이션</a>
							</div>
							
							<div class="text-end">
								<a href="" class="btn btn-primary btn-sm">더보기</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="card">
						<div class="card-header">최근 학습 강의</div>
						<div class="body p-3">
							<h5 class="fs-6 fw-bold mb-3">spring cloud로 개발하는 마이크로 서비스 애플리케이션</h5>
							<div class="mb-3">
								<p class="card-text small text-muted mb-2">진도율 : 56강/156강 (35.9%)</p>
								<div class="progress">
									<div class="progress-bar" role="progressbar" style="width: 35%" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</div>
							<div class="text-end">
								<a href="" class="btn btn-primary btn-sm">내 모든 강의</a>
								<a href="" class="btn btn-warning btn-sm">이어서 학습하기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${user.teacher eq 'Y' }">
				<div class="row mb-3">
					<div class="col-12">
						<div class="card">
							<div class="card-header">최근 등록한 강의</div>
							<div class="body p-3">
							<c:choose>
								<c:when test="${empty courses }">
									<p class="text-center">등록한 강의가 없습니다.</p>
								</c:when>
								<c:otherwise>
									<table class="table">
										<colgroup>
											<col width="20%">
											<col width="60%">
											<col width="20%">
										</colgroup>
										<thead>
											<tr>
												<th>카테고리</th>
												<th>제목</th>
												<th>가격</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="course" items="${courses }">
											<tr>
												<td>${course.categories[0].category.name }</td>
												<td><a href="/courses/detail?no=${course.no }">${course.title }</a></td>
												<td><fmt:formatNumber value="${course.price }" /> 원</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
									<div class="text-end">
										<a href="courses" class="btn btn-primary btn-sm">내가 등록한 강의 전부 보기</a>
									</div>
								</c:otherwise>
							</c:choose>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<div class="row mb-3">
				<div class="col-6">
					<div class="card">
						<div class="card-header">${user.name }님 프로필</div>
						<div class="body p-3">
							<table class="table">
								<tbody>
									<tr>
										<th>이메일</th><td>${user.email }</td>
									</tr>
									<tr>
										<th>전화번호</th><td>${user.phone }</td>
									</tr>
									<tr>
										<th>지식공유자</th><td>${user.teacher }</td>
									</tr>
								</tbody>
							</table>
							<div class="text-end">
								<a href="modify" class="btn btn-outline-primary btn-sm">프로필 수정하기</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<c:choose>
						<c:when test="${user.teacher eq 'N' }">
							<div class="card">
								<div class="card-header">지식 공유자 신청</div>
								<div class="card-body">
									<p class="card-text">지식 공유자 신청을 해보세요.</p>
									<button type="button" class="btn btn-success btn-sm float-end" id="btn-open-modal-request-teacher">지식공유자 신청</button>
								</div>
							</div>
						</c:when>
						<c:when test="${user.teacher eq 'Y' }">
							<div class="card">
								<div class="card-header">강의 등록하기</div>
								<div class="card-body">
									<p class="card-text">새 강의를 등록하세요.</p>
									<a href="/courses/form1" class="btn btn-success btn-sm float-end">새 강의 등록</a>
								</div>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 지식 공유자 신청 모달 -->
<div id="modal-request-teacher" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">지식 공유자 신청</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>지식 공유자 신청을 하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
        <button type="button" id="btn-confirm-teacher" class="btn btn-primary">예</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
// html dom 객체가 완성되면(화면이 뿌려지기 전에) 아래 함수가 실행된다.
$(function() {
	// id="modal-request-teacher"로 제어할 수 있는 모달객체 생성
	let modalRequestTeacher = new bootstrap.Modal($("#modal-request-teacher"));
	
	// id="btn-open-modal-request-teacher" 버튼 엘리먼트를 클릭했을 때 실행할 이벤트핸들러 메소드를 등록
	$("#btn-open-modal-request-teacher").click(function() {
		modalRequestTeacher.show();
	});
	
	// id="btn-confirm-teacher" 버튼 엘리먼트를 클릭했을 때 실행할 이벤트핸들러 메소드를 등록
	$("#btn-confirm-teacher").click(function() {
		location.href="/user/confirmteacher";
	})
});
</script>
</body>
</html>