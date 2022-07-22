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
	<div class="row mb-3">
		<div class="col-2">
			<div class="list-group">
				<a href="courses" data-category="" class="list-group-item list-group-item-action py-3 ${empty param.cat ? 'active' : '' }">전체 강의</a>
				<c:forEach var="category" items="${categories }">
					<a href="courses?cat=${category.id }" data-category="${category.id }" class="list-group-item list-group-item-action py-3 ${param.cat eq category.id ? 'active' : '' }">${category.name }</a>
				</c:forEach>
			</div>
		</div>
		<div class="col-10">
			<div class="row justify-content-md-end border-bottom mb-2">
				<div class="col col-4">
					<form>
						<div class="input-group mb-3">
							<input type="text" class="form-control rounded-0"  name="keyword">
							<button class="btn btn-success btn-sm rounded-0" type="button" >검색</button>
						</div>
					</form>				
				</div>
			</div>
			<form id="form-search-courses">
				<c:if test="${not empty param.cat }">
					<input type="hidden" name="categoryId" value="${param.cat }">
				</c:if>
				<div class="row mb-3">
					<div class="col-12">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="/courses" class="text-decoration-none">전체</a></li>
								<li class="breadcrumb-item active" aria-current="page">프로그래밍</li>
							</ol>
						</nav>
						<div id="box-btn-tags">
							<c:forEach var="tag" items="${tags }" varStatus="loop">
								<button type="button" class="btn-tags btn btn-secondary mb-2 ${loop.count gt 15 ? 'extend-tag d-none' : '' }" data-tag="${tag }">${tag }<span class="mx-1 d-none">x</span></button>
							</c:forEach>
							<button type="button" class="btn btn-primary mb-2" id="btn-toggle-tags">펼치기</button>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-10 d-flex justified-content-start">
						<div class="border p-1 me-2">
							<div class="form-check form-check-inline ">
	  							<input class="form-check-input" type="checkbox" data-pay="유료" name="pays" value="유료">
	 							<label class="form-check-label" >유료</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="checkbox" data-pay="무료" name="pays" value="무료">
								<label class="form-check-label" >무료</label>
							</div>
						</div>
						<div class="border p-1">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="checkbox" data-grade="입문" name="grades" value="입문">
								<label class="form-check-label" >입문</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="checkbox" data-grade="초급" name="grades" value="초급">
								<label class="form-check-label" >초급</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="checkbox" data-grade="중급" name="grades" value="중급">
								<label class="form-check-label" >중급</label>
							</div>
						</div>
					</div>
					<div class="col-2">
						<select class="form-control" name="sort">
							<option value="day" selected> 최신순</option>
							<option value="score"> 평점순</option>
							<option value="price"> 가격순</option>
						</select>
					</div>
				</div>
			</form>
			<div class="row mb-3" id="box-courses">
			<c:choose>
				<c:when test="${empty courses }">
					<p class="text-center">검색결과가 존재하지 않습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="course" items="${courses }">
						<div class="col-3 mb-3">
							<div class="card">
								<img src="/resources/images/course/${course.imagename }">
								<div class="card-body">
									<h5 class="card-title fs-6"><strong>${course.title }</strong></h5>
									<p class="card-text m-0">${course.user.name }</p>
									<p class="card-text m-0">
										<i class="bi ${course.reviewScore gt 0 ? 'bi-star-fill' : 'bi-star' }"></i>
										<i class="bi ${course.reviewScore gt 1 ? 'bi-star-fill' : 'bi-star' }"></i>
										<i class="bi ${course.reviewScore gt 2 ? 'bi-star-fill' : 'bi-star' }"></i>
										<i class="bi ${course.reviewScore gt 3 ? 'bi-star-fill' : 'bi-star' }"></i>
										<i class="bi ${course.reviewScore gt 4 ? 'bi-star-fill' : 'bi-star' }"></i>
										(${course.reviewCount })
									</p>
									<p class="card-text m-0">
										<span class="text-muted text-decoration-line-through"><fmt:formatNumber value="${course.price }"/>원</span>
										<strong class="text-bold text-danger float-end"><fmt:formatNumber value="${course.discountPrice }"/>원</strong>
									</p>
									<div class="mt-2">
										<a class="btn btn-primary btn-sm" >담기</a>
										<button class="btn border-0 float-end"><i class="bi bi-heart"></i></button>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	let $extendTags = $("#box-btn-tags .extend-tag");
	$("#btn-toggle-tags").click(function(){
		$extendTags.toggleClass("d-none");
		let text = $(this).text();
		$(this).text(text === "펼치기" ? "접기" : "펼치기");
	});
	
	////////////////// 강의 검색하기
	
	// 태그를 선택, 선택 해제 할 때
	// -secondary는 click()으로 이벤트 등록하면 안되는 이유?
	// -버튼 토글 자체는 toggleClass를 써서 클릭/해제 모두 같은 이벤트핸들러함수를 등록할 수도 있지만, hiddenField 엘리먼트를 추가/삭제하려면 나눠서 등록하는 게 깔끔하다.
	$("#box-btn-tags").on('click', '.btn-secondary', function() {
		$(this).removeClass("btn-secondary").addClass("btn-success");
		let tag = $(this).data("tag");
		let hiddenField = '<input type="hidden" name="tags" value="' + tag + '">';
		$("#box-btn-tags").append(hiddenField);
		searchCourses();
	});
	$("#box-btn-tags").on('click', '.btn-success', function() {
		$(this).removeClass("btn-success").addClass("btn-secondary");
		let tag = $(this).data("tag");
		$(':hidden[name=tags][value="' + tag + '"]').remove();
		searchCourses();
	});
	
	// 가격 유형을 체크/해제할 때
	$(":checkbox[name=pays]").change(function() {
		searchCourses();
	});
	
	// 등급 유형을 체크/해제할 때
	$(":checkbox[name=grades]").change(function() {
		searchCourses();
	});
	
	// 정렬 기준을 변경할 때
	$("select[name=sort]").change(function() {
		searchCourses();
	});
	
	// 조건에 따른 강의 검색하기
	function searchCourses() {
		let queryString = $("#form-search-courses").serialize();
		let $box = $("#box-courses").empty();
		$.getJSON("/courses/search", queryString).done(function(courses) {
			if (courses.length === 0) {
				let content = `
					<div class="col-12">
						<p class="text-center">검색결과가 존재하지 않습니다.</p>
					</div>
				`;
				$box.append(content);
			} else {
					// 벡틱을 사용하지 못할 경우 content += 'html태그 문자열'을 반복하는 식으로 html컨텐츠를 만든다.
					// .find()를 쓸 필요 없이 + 사이 사이에 값들을 넣으면 된다.
					$.each(courses, function(index, course) {
					let content = '';
					content += '<div class="col-3 mb-3">';
					content += '	<div class="card">';
					content += '		<img src="/resources/images/course/'+ course.imagename +'">';
					content += '		<div class="card-body">';
					content += '			<h5 class="card-title fs-6"><strong id="course-title">'+ course.title +'</strong></h5>';
					content += '			<p class="card-text m-0" data-name="user">'+ course.user.name +'</p>';
					content += '			<p class="card-text m-0">';
					content += '				<i class="bi '+ (course.reviewScore > 0 ? 'bi-star-fill' : 'bi-star') +'" id="course-star-1"></i>';
					content += '				<i class="bi '+ (course.reviewScore > 1 ? 'bi-star-fill' : 'bi-star') +'" id="course-star-2"></i>';
					content += '				<i class="bi '+ (course.reviewScore > 2 ? 'bi-star-fill' : 'bi-star') +'" id="course-star-3"></i>';
					content += '				<i class="bi '+ (course.reviewScore > 3 ? 'bi-star-fill' : 'bi-star') +'" id="course-star-4"></i>';
					content += '				<i class="bi '+ (course.reviewScore > 4 ? 'bi-star-fill' : 'bi-star') +'" id="course-star-5"></i>';
					content += '				(<span id="course-review-count">'+ course.reviewCount +'</span>)';
					content += '			</p>';
					content += '			<p class="card-text m-0">';
					content += '				<span class="text-muted text-decoration-line-through" id="course-price">'+ course.price +' 원</span>';
					content += '				<strong class="text-bold text-danger float-end" id="course-discount-price">'+ course.discountPrice +' 원</strong>';
					content += '			</p>';
					content += '			<div class="mt-2">';
					content += '				<a href="" class="btn btn-primary btn-sm">담기</a>';
					content += '				<button class="btn border-0 float-end"><i class="bi bi-heart"></i></button>';
					content += '			</div>';
					content += '		</div>';
					content += '	</div>';
					content += '</div>';
					// 생성된 jQuery 객체를 $box jQuery 객체에 포함된 엘리먼트의 자식 엘리먼트로 추가하기
					$box.append(content);
				});
			}
		});
	}
	/*
	jQuery ajax 메소드 없이 검색하기
	function searchCourses() {
		let queryString = $("#form-search-courses").serialize();
		
		// 조건에 맞는 것만 추가하기 위해 강의 정보 엘리먼트를 모두 지우기
		// (d-none으로 조절하는 것도 해보기)
		let $box = $("#box-courses").empty();
		// ajax 처리 : form의 입력값 쿼리스트링으로 붙인 검색조건체크 URL을 get방식으로 요청한다.
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				// 검색조건체크 요청에 대한 응답으로, 조건에 맞는 강의정보를 json으로 전달받는다.
				let jsonText = xhr.responseText;
				let courses = JSON.parse(jsonText);
				
				if (courses.length === 0) {
					let content = `
						<div class="col-12">
							<p class="text-center">검색결과가 존재하지 않습니다.</p>
						</div>
					`;
					$box.append(content);
				} else {
					// 여러 줄을 사용하기 위해 벡틱을 사용한 것임.
					let content = `
						<div class="col-3 mb-3">
							<div class="card">
								<img src="/resources/images/course/1.png">
								<div class="card-body">
									<h5 class="card-title fs-6"><strong id="course-title"></strong></h5>
									<p class="card-text m-0" data-name="user"></p>
									<p class="card-text m-0">
										<i class="bi" id="course-star-1"></i>
										<i class="bi" id="course-star-2"></i>
										<i class="bi" id="course-star-3"></i>
										<i class="bi" id="course-star-4"></i>
										<i class="bi" id="course-star-5"></i>
										(<span id="course-review-count"></span>)
									</p>
									<p class="card-text m-0">
										<span class="text-muted text-decoration-line-through" id="course-price"> 원</span>
										<strong class="text-bold text-danger float-end" id="course-discount-price"> 원</strong>
									</p>
									<div class="mt-2">
										<a href="" class="btn btn-primary btn-sm" >담기</a>
										<button class="btn border-0 float-end"><i class="bi bi-heart"></i></button>
									</div>
								</div>
							</div>
						</div>
					`;
					$.each(courses, function(index, course) {
						// html 컨텐츠로 생성된 엘리먼트를 포함하는 jQuery 객체 생성
						$el = $(content); 
						// 생성된 jQuery 객체에 포함된 엘리먼트를 찾아서 값을 설정하기
						$el.find("img").attr("src", "/resources/images/course/" + course.imagename);
						$el.find("#course-title").text(course.title);
						$el.find("#course-user").text(course.user.name);
						for(let i=1; i<=5; i++) {
							$el.find("#course-star-" + i).addClass(course.reviewScore > (i-1) ? "bi-star-fill" : "bi-star");
						}
						$el.find("#course-review-count").text(course.reviewCount);
						$el.find("#course-price").text(course.price.toLocaleString() + " 원");
						$el.find("#course-discount-price").text(course.discountPrice.toLocaleString() + " 원");
						// 생성된 jQuery 객체를 $box jQuery 객체에 포함된 엘리먼트의 자식 엘리먼트로 추가하기
						$box.append($el);
					});
					
				}
			}
		}
		xhr.open("GET", "/courses/search?" + queryString);
		xhr.send();
	}
	*/

});
</script>
</body>
</html>