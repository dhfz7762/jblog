<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
					<c:forEach items="${cateList}" var="CateVo">
						<tr>
							<td>${CateVo.cateNo }</td>
							<td>${CateVo.cateName}</td>
							<td>${CateVo.postCount}</td>
							<td>${CateVo.description}</td>
							<td class='text-center'><img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						</tr>
						
					</c:forEach>	
					</tbody>
				</table>
<!-- 리스트 영역 -->
				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>

					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="cateName" name="cateName" value=""></td>
						<td><input type="hidden" id="cateId" name="id" value="${blogUser.id}"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="description" name="description"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
$("#btnAddCate").on("click",function(){
	var id = $("#cateId").val();
	var cateName = $("#cateName").val();
	var description = $("#description").val();
	var categoryVo = {
			id : id,
			cateName : cateName,
			description : description
	}
	
$.ajax({
		
		url : "${pageContext.request.contextPath}/admin/category/insert",		
		type : "post",
		data : categoryVo,
		success : function(categoryVo){
			if(categoryVo!=null){
				add(categoryVo);
				
				
				$("#cateId").val("");
				$("#cateName").val("");
				$("#description").val("");
				//성공시 처리해야될 코드
				}
			else{
				alert("오류");
			}
			
			
		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
	function add(categoryVo){
		var str="";
		str+= ' <tr> ';
		str+= ' <td>'+categoryVo.cateNo+'</td> ';
		str+= ' <td>'+categoryVo.cateName+'</td> ';
		str+= ' <td>'+categoryVo.postCount+'</td> ';
		str+= ' <td>'+categoryVo.description+'</td> ';
		str+= ' <td class="text-center"><img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td> ';
		str+= ' </tr> ';
		$("#cateList").prepend(str);
	}
})


</script>




</html>