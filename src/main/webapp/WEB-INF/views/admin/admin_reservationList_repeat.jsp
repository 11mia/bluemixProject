<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>

<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>   

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>


<html>
<head>
<title>관리자 메인</title>

</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container" >
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">예약내역</div>
		</div>
		
		<div id="deleteDiv" >
		<div class="col-lg-3">
		  	<button type="button" onClick="showInputBtn()" class="pull-left btn btn-primary">이전예약현황 삭제</button> <br><br>
		  </div>
		   	<form name="deleteResevAndHistForm" id="deleteResevAndHistForm" method="post" action="">
		   		<div class="col-lg-2">
					<input type="text" id="histDeleteDate" name="histDeleteDate" class="form-control" readonly/> 
				</div>
				<div class="col-lg-3">이전의 내용을 삭제합니다.</div>
				<div class="col-lg-2">
					<button type="button" onClick="storeResevAndHistBtn()" class="pull-right btn btn-primary">저장</button>
				</div>
				<div class="col-lg-2">
					<button type="button" onClick="deleteResevAndHistBtn()" class="pull-right btn btn-primary">삭제</button>
				</div>
		   	</form>
		</div>
		
		<br><br>
		
		<div style="margin-top:3%;">
			<form name="searchHistByDateForm" id="searchHistByDateForm" method="post" action="">
				<div class="col-lg-1">
					시작일 
				</div>
				<div class="col-lg-3">
					<input type="text" id="selectStartDate" name="selectStartDate" class="form-control" />  
				</div>
				<div class="col-lg-1">
					종료일 
				</div>
				<div class="col-lg-3">
					 <input type="text" id="selectEndDate" name = "selectEndDate" class="form-control" />
				</div>
				
				<div class="col-lg-2">
					<button type="submit" onClick="repeatSearchFormSubmit()" class="pull-right btn btn-primary">검색</button>
				</div>
				
				<!-- 탭 -->
				<br><br><br>
				<ul class="nav nav-tabs">
			  		<li id="tab1" class="" onclick="searchHistClickBtn()"><a href="#" data-toggle="tab" aria-expanded="false" >일반예약</a></li>
			  		<li id="tab2" class="active" onclick="repeatSearchFormSubmit()" ><a href="#" data-toggle="tab" aria-expanded="false">반복예약</a></li>
			  		<li id="tab3" class="" onclick="noshowSearchFormSubmit()" ><a href="#" data-toggle="tab" aria-expanded="false">No-Show</a></li>
				</ul> <br>
				
				<!-- 정렬 -->
				<div class="col-xs-3">
					<select class="form-control"  name="sort" id="sort" onchange="repeatSearchFormSubmit()">
								<option value="new">최신순</option>
								<option value="old">오래된 순</option>
								<option value="name">이름 가나다순</option>
					</select>
				</div>

				
			</form>
			
			</div>
			
			<br><br>
			
	  	<div id="searchRepeatHistoryResultList" class="col-lg-12 table-responsive" style="margin-top:3%;">
				<table class="table table-hover text-center" style="text-align:center; font-size:100%">
					<thead>
						<tr>
							<th width="10%" style="text-align:center;">반복시작일</th>
							<th width="10%" style="text-align:center;">반복종료일</th>
							<th width="10%" style="text-align:center;">회의시간</th>
							<th width="10%" style="text-align:center;">회의실</th>
							<th width="10%" style="text-align:center;">회의제목</th>
							<th width="10%" style="text-align:center;">예약자</th>
							<th width="10%" style="text-align:center;">전화번호</th>
							<th width="10%" style="text-align:center;">예약/변경일</th>
							<th width="10%" style="text-align:center;"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${searchRepeatHistoryResultList}" var="searchRepeatHistoryResultList">
							<tr>
								<td width="10%">${searchRepeatHistoryResultList.X}</td>
								<td width="10%">${searchRepeatHistoryResultList.Y}</td>
								<td width="10%"><c:out value="${fn:substring(searchRepeatHistoryResultList.HST_START_TIME,0,5)}"/> ~
							<c:out value="${fn:substring(searchRepeatHistoryResultList.HST_END_TIME,0,5)}"/></td>
								<td width="10%">${searchRepeatHistoryResultList.CONF_NM}</td>
								<td width="10%">${searchRepeatHistoryResultList.HST_RSV_TITLE}</td>
								<td width="10%">${searchRepeatHistoryResultList.HST_RSV_MEM_NM}</td>
								<td width="10%">${searchRepeatHistoryResultList.HST_RSV_MEM_PN}</td>
								<td width="10%">${searchRepeatHistoryResultList.HST_REG_DATE}</td>
								<td width="10%"><button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#reservDetail" onClick="showRepeatDetail(${searchRepeatHistoryResultList.HST_REPEAT_NO} )">상세보기</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		<!--  모달로 반복내역 상세보기 띄우기 -->
		<div class="modal fade" id="reservDetail">
			<div class="modal-dialog" style="width:80%">
				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">상세내역</h4>
					</div>

					<div class="modal-body">
						<div class="table-responsive">
							<table class="table table-hover text-center"
								style="text-align: center;" id="modalTable">
								<thead>
									<tr>
										<!-- 회의 종료일, 반복 주기 넣어 놓기-->
										<th width="10%" style="text-align: center">회의날짜</th>
										<th width="30%" style="text-align: center">회의시간</th>
										<th width="10%" style="text-align: center">회의실</th>
										<th width="20%" style="text-align: center">회의제목</th>
										<th width="10%" style="text-align: center">예약자</th>
										<th width="20%" style="text-align: center">전화번호</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>			
			
			
			
	<%-- ################### 페이징 ################ --%>
	<%-- page, sort 이름 확인하기 --%>

	<%-- 첫 페이지로 이동 --%>
	<p align="center">
		<a href="/AdminReservCheckAndDelete/SearchRepeatHistory?page=1&sort=${requestScope.sort }&selectStartDate=${requestScope.selectStartDate }&selectEndDate=${requestScope.selectEndDate }">첫 페이지</a>

		<%-- 이전 페이지 그룹 처리 --%>
			<c:choose>
			<c:when test="${requestScope.pageBean.previousPageGroup }">
				<%-- 이전 페이지 그룹이 있다면 isPreviousPageGroup() 호출 --%>
				<a href="/AdminReservCheckAndDelete/SearchRepeatHistory?page=${requestScope.pageBean.beginPage - 1 }&sort=${requestScope.sort }&selectStartDate=${requestScope.selectStartDate }&selectEndDate=${requestScope.selectEndDate }">◀</a>
			</c:when>
			<c:otherwise>
			◀
		</c:otherwise>
		</c:choose>

		<%-- 현재 page가 속한 page 그룹 내의 페이지들 링크 --%>
		<c:forEach begin="${requestScope.pageBean.beginPage }"
			end="${requestScope.pageBean.endPage }" var="page">
			<c:choose>
				<c:when test="${requestScope.pageBean.page != page }">
					<!-- 현재 페이지가 아니라면 -->
					<a href="/AdminReservCheckAndDelete/SearchRepeatHistory?page=${page}&sort=${requestScope.sort }&selectStartDate=${requestScope.selectStartDate }&selectEndDate=${requestScope.selectEndDate }">${page }&nbsp;&nbsp;</a>
				</c:when>
				<c:otherwise>
				[${page }]  <%-- &nbsp;는 공백을 나타냄 --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음페이지 그룹으로 이동. 만약에 다음페이지 그룹이 있으면 링크 처리 없으면 화살표만 나오도록 처리 -->
		<c:choose>
			<c:when test="${requestScope.pageBean.nextPageGroup }">
				<%-- isNextPageGroup() 호출 --%>
				<a
					href="/AdminReservCheckAndDelete/SearchRepeatHistory?page=${requestScope.pageBean.endPage + 1 }&sort=${requestScope.sort }&selectStartDate=${requestScope.selectStartDate }&selectEndDate=${requestScope.selectEndDate }">▶</a>
				<%-- getEndPage()에서 리턴된 값 넣기 --%>
			</c:when>
			<c:otherwise>
			▶
		</c:otherwise>
		</c:choose>

		<!-- 마지막 페이지로 이동 -->
		<a href="/AdminReservCheckAndDelete/SearchRepeatHistory?page=${requestScope.pageBean.totalPage}&sort=${requestScope.sort }&selectStartDate=${requestScope.selectStartDate }&selectEndDate=${requestScope.selectEndDate }">마지막
			페이지</a>
	</p>
			
		
		 
	</div>

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script>
	
	$("#deleteResevAndHistForm").hide();
	
	function showInputBtn() {
		$("#deleteResevAndHistForm").show();
	}
	
	$ ( function() {
		
		$("#histDeleteDate").datepicker({
			dateFormat : "yy-mm-dd"
		});
		
		
		$("#selectStartDate").datepicker({
			dateFormat : "yy-mm-dd",
			onClose : function(selectedDate) {
				$("#selectEndDate").datepicker("option", "minDate", selectedDate);
			}
		});
		
		$("#selectEndDate").datepicker({
			dateFormat : "yy-mm-dd",
			onClose : function(selectedDate) {
				$("#selectStartDate").datepicker("option", "maxDate", selectedDate);
			}
		});
		
	});
	
	//시작일과 종료일 계속 보이도록
	$(function() {
		$("#selectStartDate").val("${startDateBack}");
		$("#selectEndDate").val("${endDateBack}");
		$("#sort").val("${sortTypeBack}");
	});

	
	//입력한 날짜 이전의 내용을 삭제
	function deleteResevAndHistBtn() {
		var histDeleteDate = $("#histDeleteDate").val();
		
		//alert("histDeleteDate : "+histDeleteDate);
		
		alert("정말 삭제하시겠습니까?");
		
		//$("#deleteResevAndHistForm").attr("action", "/AdminReservCheckAndDelete/DeleteReservAndHistory");
		//$("#deleteResevAndHistForm").submit();
		
		
		$.ajax({
			url : "/AdminReservCheckAndDelete/DeleteReservAndHistory",
			dataType : "text",
			async : false,
			type : "POST",
			data : { "deleteDate" : histDeleteDate },
			success : function(data) {
				//alert("sucess check");
				
				$('<div/>').html(data).find('deleteDiv');	
				
				alert("삭제되었습니다");
			},
			error : function(request, status, error) {
				alert("code : "+request.status + "\n" + "error : " + error);
			}
		});
		
		
	}
	
	//입력한 날 이전의 내역을 저장
	function storeResevAndHistBtn() {
		
		
		
	}
	
	
	//일반예약 내역 탭으로 이동
	function searchHistClickBtn(){
		
		var selectStartDate = $("#selectStartDate").val();
 		var selectEndDate = $("#selectEndDate").val();
		
		//alert("버튼 클릭 확인");
		
 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
 			alert("시작일 또는 종료일을 입력해주세요.");
 		}else {
 			$("#searchHistByDateForm").attr("action","/AdminReservCheckAndDelete/SearchGeneralHistory");
			$("#searchHistByDateForm").submit();
 		}

	}
	
	//노쇼탭으로 이동
	function noshowSearchFormSubmit() {
		
		var selectStartDate = $("#selectStartDate").val();
 		var selectEndDate = $("#selectEndDate").val();
		
 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
 			alert("시작일 또는 종료일을 입력해주세요.");
 		}else {
 			$("#searchHistByDateForm").attr("action","/AdminReservCheckAndDelete/SearchNoshowHistory");
			$("#searchHistByDateForm").submit();
 		}

	}
	
	
	//반복예약 탭으로 이동
	function repeatSearchFormSubmit() {
		var selectStartDate = $("#selectStartDate").val();
 		var selectEndDate = $("#selectEndDate").val();
		
 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
 			alert("시작일 또는 종료일을 입력해주세요.");
 		}else {
 			$("#searchHistByDateForm").attr("action","/AdminReservCheckAndDelete/SearchRepeatHistory");
			$("#searchHistByDateForm").submit();
 		}
	}
	
	
	
	
	
	
	//반복예약 상세내역 띄우기
	function showRepeatDetail(repeatNo) {
		
		var table = document.getElementById('modalTable');

		
		var detailViewArray = new Array();
		
		$.ajax({
			url : "/AdminReservCheckAndDelete/ShowRepeatDetail",
			dataType : "json",
			type : "POST",
			data : { "repeatNo" : repeatNo },
			success : function(data) {
				detailViewArray = data;

				for(var i = table.rows.length - 1; i > 0; i--)
		         {
		             table.deleteRow(i);
		         }
				
				//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호
				for(var i =0; i<data.length; i++){
					//var row = table.insertRow(rowlen-1);
					var row = table.insertRow();
					
					var startTime= detailViewArray[i].HST_START_TIME +"";
					var endTime= detailViewArray[i].HST_END_TIME +"";
					
					//alert(typeof startTime);
					
					row.insertCell(0).innerHTML =detailViewArray[i].HST_DATE;
					row.insertCell(1).innerHTML = startTime.substring(0, 5) + "~" + endTime.substring(0, 5);
					row.insertCell(2).innerHTML = detailViewArray[i].CONF_NM;
					row.insertCell(3).innerHTML = detailViewArray[i].HST_RSV_TITLE;
					row.insertCell(4).innerHTML = detailViewArray[i].HST_RSV_MEM_NM;
					row.insertCell(5).innerHTML = detailViewArray[i].HST_RSV_MEM_PN;
					
				}
			},
			error : function(request, status, error) {
				alert("code : "+request.status + "\n" + "error : " + error);
			}
			
		});
		
		
	}

</script>