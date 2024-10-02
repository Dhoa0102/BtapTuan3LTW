<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
	<label for="fname">Category name</label><br>
	<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
	<label for="lname">Images</label><br>
		
			<img height="150" width="200" src="${imgUrl}" />
	<input type="file" id="images" name="images"><br>
	<p>Status</p>
	<input type="radio" id="ston" name="status" value="1" checked }>
	<label for="html">Dang hoat dong</label><br>
	<input type="radio" id="stoff" name="status" value="0" }>
	<label for="css">Khoa</label><br>
	<input type="submit" value="insert">
</form>