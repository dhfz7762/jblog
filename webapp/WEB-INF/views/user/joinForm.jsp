<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="center-content">

		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>


		<div>
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>

					<tr>
						<td><label for="txtId">아이디</label></td>
						<td><input id="txtId" type="text" name="id" required="required"></td>
						<td><button id="btnIdCheck" type="button">아이디체크</button></td>
						<td><input id="btnClick" type="hidden" value="false"></td>
					</tr>
					<tr>
						<td></td>
						<td id="tdMsg" colspan="2"></td>
					</tr>
					<tr>
						<td><label for="txtPassword">패스워드</label></td>
						<td><input id="txtPassword" type="password" name="password" value="" required="required"></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="txtUserName">이름</label></td>
						<td><input id="txtUserName" type="text" name="userName" value="" required="required"></td>
						<td></td>
					</tr>
					<tr>
						<td><span>약관동의</span></td>
						<td colspan="3"><input id="chkAgree" type="checkbox" name="agree" value="y" required="required"> <label for="chkAgree">서비스
								약관에 동의합니다.</label></td>
					</tr>
					<tr>
						<td id="btnClickCheck" colspan="2"></td>
					</tr>
				</table>
				<div id="btnArea">
					<button id="btnJoin" class="btn" type="reset">회원가입</button>
				</div>

			</form>

		</div>


		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	</div>

</body>

<script type="text/javascript">
	$("#btnIdCheck").on("click", function() {
		var id = $("[name=id]").val();

		$.ajax({
			url : "${pageContext.request.contextPath }/user/idcheck",
			type : "post",
			//contentType : "application/json",
			data : {
				id : id
			},
			success : function(check) {
				/*성공시 처리해야될 코드 작성*/
				if (check != 1) { //처리성공()
					$("#tdMsg").html(id + "는 사용가능 합니다.");
					$("#btnClick").val("true");

				} else {
					$("#tdMsg").html(id + "는 사용중입니다.");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
				//alert("서버요청실패");
			}
		});

	})
	$("#btnJoin").on("click", function() {
		if ($("#btnClick").val() != "true") {
			$("#btnClickCheck").html("아이디체크를 해주세요 !!")
		} else {
			$("#btnJoin").attr("type", "submit");
		}

	})
</script>

</html>