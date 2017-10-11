<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�˻����</title>
</head>
<body>
 <jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
  <div class="container">

 <h3>�˻����</h3>
 <form name="search" method="post" action="">
  <select name="searchOpt">
  	<option value="all">��ü</option>
  	<option value="title">ȸ������</option>
  	<option value="mem_nm">������</option>
  	<option value="mem_pn">��ȭ��ȣ</option>
  </select>
  <input type="text" size="70"/>
  <button type="button">�˻�</button>
 </form>
 <br><br>
 
 <div id="allReservList">
 <table class="table table-striped table-hover ">
  <thead>
 	<tr>
 	  <th>   ������Ʈ     </th>
 	  <th>   ȸ�ǳ�¥      </th>
 	  <th>    ȸ�ǽð�    </th>
 	  <th>    ȸ�ǽ�        </th>
 	  <th>    ȸ������     </th>
 	  <th>    ������        </th>
 	  <th>    ��ȭ��ȣ     </th>
 	</tr>
   </thead>
   <tbody>
 	 <c:forEach items="${allReservList}" var="allReservList">
	    <tr>
	     <td>${allReservList.rsv_comp}</td>
	     <td>${allReservList.rsv_date}</td>
	     <td>${allReservList.rsv_start_time} ~ ${allReservList.rsv_end_time}</td>
	     <td>${allReservList.rsv_conf_nm}</td>
	     <td>${allReservList.rsv_title}</td>
	     <td>${allReservList.rsv_mem_nm}</td>
	     <td>${allReservList.rsv_mem_pn}</td>
	    </tr>
	   </c:forEach>
	 </tbody>
 </table>
</div>

   
  </div>

  
 <jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>