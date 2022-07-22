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
			<form id="form-course" class="col-8 p-3" method="post" action="insert">
				<div class="mb-3">
					<label class="form-label">이런 것을 배울 수 있어요. (최대 5개)</label>
					<input class="form-control mb-2" type="text" name="learnings">
					<button id="btn-add-learning" type="button" class="btn btn-success btn-sm mb-3 w-100">추가</button>
				</div>
				<div class="mb-3">
					<label class="form-label">이런 분들에게 추천해요. (최대 5개)</label>
					<input class="form-control  mb-2" type="text" name="targets">
					<button id="btn-add-target" type="button" class="btn btn-success btn-sm mb-3 w-100">추가</button>
				</div>
				<div class="mb-3">
					<label class="form-label">태그를 추가하세요. (최대 5개)</label>
					<div class="d-flex justify-content-start">
						<input id="input-tag" class="form-control w-25 me-3" type="text">
						<button id="btn-add-tag" type="button" class="btn btn-success btn-sm ">추가</button>
					</div>
					<div class="mb-3 mt-2">
					<!-- 
						<span class="badge text-bg-secondary rounded-1 p-2">자바스크립트</span>
						<span class="badge text-bg-secondary rounded-1 p-2">프론트엔드</span>
					 -->
						<div id="tag-box">
						<!-- 
							<input type="hidden" name="tags" value="자바스크립트">
							<input type="hidden" name="tags" value="프론트엔드">
						 -->
						</div>
					</div>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary px-5">등록</button>
				</div>				
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		// learnings 5개 이상 추가 불가
		$("#btn-add-learning").click(function() {
			let fieldLength = $(":text[name=learnings]").length;
			if (fieldLength >= 5) {
				alert("최대 5개의 항목까지 입력할 수 있습니다.");
				return;
			}
			
			let content = '<input class="form-control mb-2" type="text" name="learnings">';
			$(this).before(content);
		});
			
		// targets 5개 이상 추가 불가
		$("#btn-add-target").click(function() {
			let fieldLength = $(":text[name=targets]").length;
			if (fieldLength >= 5) {
				alert("최대 5개의 항목까지 입력할 수 있습니다.");
				return;
			}
			
			let content = '<input class="form-control mb-2" type="text" name="targets">';
			$(this).before(content);
		});
		
		// tags 5개 이상 추가 불가
		$("#btn-add-tag").click(function() {
			let $div = $("#tag-box");
			let $input = $("#input-tag");
			
			let tagLength = $(":hidden[name=tags]").length;
			if (tagLength >= 5) {
				alert("최대 5개의 항목까지 입력할 수 있습니다.");
				$input.val("");
				return;
			}
			
			//태그명 빈 값 불가
			let value = $input.val().trim();
			if (value === "") {
				alert("태그명을 입력하세요.");
				return;
			}
			
			let spanContent = '<span class="badge text-bg-secondary rounded-1 p-2 me-2">'+ value +'</span>';
			let hiddenContent = '<input type="hidden" name="tags" value="' + value +'">';
			$div.before(spanContent);
			$div.append(hiddenContent);
			
			$input.val("");
		});
		
		// 빈 input박스가 있을 경우 제출 불가
		$("#form-course").submit(function() {
			// learnings 빈칸 제출 불가
			let existEmptyLearning = false;
			$(":text[name=learnings]").each(function(){
				if ($(this).val().trim() === "") {
					alert("배울 수 있는 것 항목에 빈 칸을 채워주세요.");
					existEmptyLearning = true;
					return;
				}
			});
			if (existEmptyLearning) {
				return false;
			}
			
			// targets 빈칸 제출 불가
			let existEmptyTarget = false;
			$(":text[name=targets]").each(function(){
				if ($(this).val().trim() === "") {
					alert("추천 대상 항목에 빈 칸을 채워주세요.");
					existEmptyTarget = true;
					return;
				}
			});
			if (existEmptyTarget) {
				return false;
			}
			
			// tags 0개 제출 불가
			if ($(":hidden[name=tags]").length === 0) {
				alert("태그는 하나 이상 등록해야 합니다.");
				return false;
			}
			
			return true;
		});})
</script>
</body>
</html>