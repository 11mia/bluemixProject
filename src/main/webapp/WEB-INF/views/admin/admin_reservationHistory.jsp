<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <title>������ : ���೻�� ������</title>
</head>
<body>
 <jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
 <button>���� ��Ȳ ����</button> <button>����</button>
 <br>
 <p>���۳�¥ ����¥</p>
 <br><br>
 
 <table class="rsvHistory">
 	<thead>
 	 <tr>
 	  <th>ȸ�ǳ�¥</th>
 	  <th>ȸ�ǽ��۽ð�</th>
 	  <th>ȸ������ð�</th>
 	  <th>������</th>
 	  <th>��ȭ��ȣ</th>
 	  <th>ȸ�ǽ�</th>
 	  <th>ȸ������</th>
 	  <th>����</th> <!-- ������ ���±��� ��Ÿ���� -->
 	  <th>���ຯ�泯¥</th> <!-- ���� �ȵ����� �׳� ���೯¥�� ��� -->
 	 </tr>
 	</thead>
 	<tbody>
 	 <c:choose>
 	  <c:when test="${fn:length(list)} > 0">
 	   <c:forEach items="${list}" var="row">
 	    <tr>
 	     <td>${row.HST_DATE}</td>
 	     <td>${row.HST_START_TIME}</td>
 	     <td>${row.HST_END_TIME}</td>
 	     <td>${row.HST_RSV_MEM_NM}</td>
 	     <td>${row.HST_RSV_MEN_PN}</td>
 	     <td>${row.HST_CONF_NM}</td>
 	     <td>${row.HST_RSV_TITLE}</td>
 	     <td>${row.HST_REG_DATE}</td>
 	    </tr>
 	   </c:forEach>
 	  </c:when>
 	  <c:otherwise>
 	   <tr>
 	    <td>��ȸ�� ����� �����ϴ�.</td>
 	   </tr>
 	  </c:otherwise>
 	 </c:choose>
 	</tbody>
 </table>
 
 <jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>

</body>
</html>