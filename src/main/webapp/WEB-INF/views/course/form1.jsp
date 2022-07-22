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
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<div class="row my-3">
		<div class="col">
			<h1 class="fs-5 border p-2">강의 정보</h1>
		</div>
	</div>
	<div class="row mb-3 px-3">
		<div class="col border bg-light">
			<form id="form-course" class="col-8 p-3" method="post" action="form2" enctype="multipart/form-data">
				<div class="mb-3">
					<label class="form-label">카테고리</label>
					<div>
						<c:forEach var="cat" items="${categories }">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="checkbox" name="categoryIds" value="${cat.id }">
								<label class="form-check-label" >${cat.name }</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="mb-3">
					<label class="form-label">강의 제목</label>
					<input class="form-control " type="text" name="title">
				</div>
				<div class="mb-3">
					<label class="form-label">강의수준</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="grade" value="입문" checked="checked">
							<label class="form-check-label" >입문</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="grade" value="초급">
							<label class="form-check-label" >초급</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="grade" value="중급">
							<label class="form-check-label" >중급</label>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label class="form-label">수강기간</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="period" value="3개월" checked="checked">
							<label class="form-check-label" >3개월</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="period" value="6개월">
							<label class="form-check-label" >6개월</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="period" value="1년">
							<label class="form-check-label" >1년</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="period" value="무제한">
							<label class="form-check-label" >무제한</label>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label class="form-label">수료증 발급여부</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="certificate" value="발급" checked="checked">
							<label class="form-check-label" >발급</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="certificate" value="미발급">
							<label class="form-check-label" >미발급</label>
						</div>
					</div>
				</div>				
				<div class="mb-3">
					<label class="form-label">가격</label>
					<input class="form-control w-50" type="number" name="price" value="50000">
				</div>
				<div class="mb-3">
					<label class="form-label">강의 대표 이미지</label>
					<input class="form-control w-50" type="file" name="imageFile">
				</div>
				<div class="mb-3">
					<label class="form-label">강의 설명</label>
					<textarea class="form-control" rows="5" name="description"></textarea>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary px-5">다음</button>
				</div>				
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	$("#form-course").submit(function() {
	// 카테고리 선택 체크
	let checkedCategoryLength = $(":checkbox[name=categoryIds]:checked").length;
	if (checkedCategoryLength == 0) {
		alert("카테고리를 하나 이상 선택하세요.");
		return false;
	}
	
	// 강의 제목 체크
	let titleValue = $(":text[name=title]").val().trim();
	if (titleValue === "") {
		alert("제목을 입력하세요.");
		return false;
	}
	
	// 가격 유효값 체크
	let priceValue = $("input[name=price]").val();
	if (priceValue <= 0) {
		alert("입력한 가격이 유효하지 않습니다.");
		return false;
	}
	
	// 대표 이미지 체크
	let fileValue = $(":file[name=imageFile]").val();
	if (fileValue === "") {
		alert("강의 대표 이미지를 첨부하세요.");
		return false;
	}
	
	// 강의 설명 체크
	let descriptionValue = $("textarea[name=description]").val().trim();
	if (descriptionValue === "") {
		alert("강의 설명을 입력하세요.");
		return false;
	}
	
	return true;
	})
})
</script>
</body>
</html>