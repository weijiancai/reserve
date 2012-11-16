<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Pager-taglib��ҳ������ʾ</title>
</head>
<body>
<pg:pager items="1001" maxPageItems="33" maxIndexPages="20" export="currentPageNumber=pageNumber">
	<pg:first>
		<a href="${pageUrl}">��ҳ</a>
	</pg:first>
	<pg:prev>
		<a href="${pageUrl}">ǰҳ</a>
	</pg:prev>
	<pg:pages>
		<c:choose>
			<c:when test="${currentPageNumber eq pageNumber}">
				<font color="red">${pageNumber }</font>
			</c:when>
			<c:otherwise>
				<a href="${pageUrl}">${pageNumber }</a>
			</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next>
		<a href="${pageUrl}">��ҳ</a>
	</pg:next>
	<pg:last>
		<a href="${pageUrl}">βҳ</a>
	</pg:last>
</pg:pager>
</body>
</html>