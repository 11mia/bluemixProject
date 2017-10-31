<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/headerLocation.js"></script>  
 
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" onclick="main()" style="cursor:pointer"><strong>IBM 회의실 예약 시스템</strong></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav navbar-right">
        <li><a onClick="repeatReservation()" style="cursor:pointer"><strong>반복예약신청</strong></a></li>
        <c:choose>
        	<c:when test="${sessionScope.id ne null}">
        		<li><a onClick="adminSearchPage()" style="cursor:pointer"><strong>검색(관리자용)</strong></a></li>
        	</c:when>
        	
        	<c:otherwise>
        		<li><a onClick="searchPage()" style="cursor:pointer"><strong>검색</strong></a></li>
        	</c:otherwise>
        </c:choose>
		<li><a target="_blank" onclick="admin()" style="cursor:pointer"><strong>관리자</strong></a></li>
		
		<c:if test="${sessionScope.id ne null}">
		   <li><a target="_blank" onclick="logout()" style="color:red; cursor:pointer"><strong>로그아웃</strong></a></li>
		</c:if>
      </ul>
    </div>
  </div>
</nav>

<script>


</script>