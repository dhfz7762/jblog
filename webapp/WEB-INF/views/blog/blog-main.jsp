<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">

					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogUser.logoFile}">


					<div id="nick">${blogUser.userName}(${blogUser.id})님</div>
					<input type="hidden" id="hiddenId" value="${blogUser.id}">
					<input type="hidden" id="hiddenLastId" value="${lastCateNo}">
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">

					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">
				<div id="clickPost">
					<div id="lastPost">
						<c:choose>
							<c:when test="${lastPost == null}">
								<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left">
										<strong>등록된 글이 없습니다.</strong>
									</div>
									<div id="postDate" class="text-left">
										<strong></strong>
									</div>
									<div id="postNick"></div>
								</div>
								<div id="post"></div>

							</c:when>
							<c:otherwise>
								<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left">
										<strong>${lastPost.postTitle}</strong>
									</div>
									<div id="postDate" class="text-left">
										<strong>${lastPost.regDate}</strong>
									</div>
									<div id="postNick">${lastPost.userName}님</div>
								</div>
								<!-- //postBox -->

								<div id="post">${lastPost.postContent}</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- //post -->




				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table id="listTable">
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<tr id="viewlist">
							<td><input type="hidden" id="postNo" value="${PostVo.postNo}" /></td>
							<td class="text-left" id="postClick" data-del="${PostVo.postNo}">${PostVo.postTitle}</td>
							<td class="text-right">${PostVo.regDate}</td>
						</tr>

					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	//좌측 카테고리와 아래 포스트 로딩
	$(document).ready(function() {
		var id = $("#hiddenId").val()
		
		var UserVo = {
			id : id
		}
		
		$.ajax({

			url : "${pageContext.request.contextPath }/category/list",
			type : "post",
			data : UserVo,
			dataType : "json",
			success : function(cateList) {
				for (var i = 0; i < 5; i++) {
					render(cateList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		

	
	
	
	var cateNo =  $("#hiddenLastId").val()
	var PostVo = {
		cateNo : cateNo
	}
	
	
	$.ajax({

		url : "${pageContext.request.contextPath }/post/list",
		type : "post",
		data : PostVo,
		dataType : "json",
		success : function(PostList) {
			$("#listTable").empty();
			colRender();
			for (var i = 0; i < PostList.length ; i++) {
				postRender(cateList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	//좌측 카테고리 작성
	function render(CateVo) {

		var str = "";
		str += ' <li id="categoryClick" data-click="' +CateVo.cateNo + '"> ' + CateVo.cateName + ' </li> ' ;

		$("#cateList").append(str);

	}
});
	
	
	//좌측 카테고리 클릭
	$("#cate").on("click","#categoryClick",function() {
						var cateNo = $(this).data("click");
						var CateVo = {
							cateNo : cateNo
						}

						$.ajax({

									url : "${pageContext.request.contextPath}/category/click",
									type : "post",
									data : CateVo,
									dataType : "json",
									success : function(PostList) {
										$("#listTable").empty();
										colRender();
										for (var i = 0; i < PostList.length; i++) {
											postRender(PostList[i]);
										}

									},
									error : function(XHR, status, error) {
										console.error(status + " : " + error);
									}
								});
						
						

					})

	
	function colRender() {
			var colStr = "";
			
			colStr += ' <colgroup> ';
			colStr += ' <col style=""> ';
			colStr += ' <col style="width: 20%;"> ';
			colStr += ' </colgroup> ';
	
			$("#listTable").prepend(colStr);
	}
						
						
						
	function postRender(PostVo) {
			var str = "";
			
			str += ' <tr> '
			str += ' <td><input type="hidden" id="postNo" value=" ' + PostVo.postNo + ' "  /></td> ';
			str += ' <td class="text-left" id="postClick" data-del=" ' + PostVo.postNo + ' "> '
					+ PostVo.postTitle + ' </td> ';
			str += ' <td class="text-right"> ' + PostVo.regDate
					+ ' </td> '
			str += ' </tr> ';
	
			$("#listTable").prepend(str);
	}
	
	
	//카테고리의 글 클릭
	$("#post_area").on("click", "#postClick", function() {
		var postNo = $(this).data("del");
		var PostVo = {
			postNo : postNo,
		}

		$.ajax({

			url : "${pageContext.request.contextPath}/post/click",
			type : "post",
			data : PostVo,
			success : function(PostVo) {
				if (PostVo != null) {
					$("#clickPost").empty();
					add(PostVo);
				} else {
					alert("오류");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		function add(PostVo) {
			var str = "";
			str += ' <div id="postBox" class="clearfix"> ';
			str += ' <div id="postTitle" class="text-left"> ';
			str += ' 	<strong> ' + PostVo.postTitle + ' </strong> ';
			str += ' </div> ';
			str += ' <div id="postDate" class="text-left"> ';
			str += ' 	<strong> ' + PostVo.regDate + ' </strong> ';
			str += ' </div> ';
			str += ' <div id="postNick"> ' + PostVo.userName + ' 님</div> ';
			str += ' </div> ';
			str += ' <div id="post"> ' + PostVo.postContent + ' </div> '
			$("#clickPost").prepend(str);
		}

	});
</script>


</html>