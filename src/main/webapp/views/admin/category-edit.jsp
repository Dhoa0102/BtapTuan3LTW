<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/category/update " method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" hidden="hidden" value="${cate.categoryid}"><br>
	<label for="fname">Category name</label><br>
	<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
	<label for="lname">Images</label><br>
	<c:if test="${cate.images.substring(0,5) == 'https' }">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) != 'https' }">
				<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
	<input type="file" id="images" name="images"><br>
	<p>Status</p>
	<input type="radio" id="ston" name="status" value="1" ${cate.status==1?'checked':'' }>
	<label for="html">Dang hoat dong</label><br>
	<input type="radio" id="stoff" name="status" value="0" ${cate.status!=1?'checked':'' }>
	<label for="css">Khoa</label><br>
	<input type="submit" value="Update">
</form>