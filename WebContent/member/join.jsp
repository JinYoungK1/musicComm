<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
<script type="text/javascript" src="./script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// class="submit" 클릭 id="join" submit()
		$('#join').submit(function() {
// 			alert('전송');
// 			class="id" val() 비어있으면 "아이디 입력" 포커스 리턴 false
			
			if($('.id').val().length < 4 || $('.id').val().length > 10) {
				alert('아이디를 4~10자리로 입력하세요');
				$('.id').focus();
				return false;
			}
// 			id="pass"
			if($('#pass').val().length < 4 || $('#pass').val().length > 8) {
				alert('비밀번호 입력하세요');
				$('#pass').focus();
				return false;
			} else if($('#pass2').val()=="") {
				alert('비밀번호 확인하세요');
				$('#pass2').focus();
				return false;
			}
// 			id="pass2"
			if($('#pass2').val()!=$('#pass').val()) {
				alert('비밀번호가 틀립니다');
				$('#pass2').focus();
				return false;
			}

// 			id="email"
			if($('#email').val()=="") {
				alert('이메일 입력하세요');
				$('#email').focus();
				return false;
			}
			
// 			id="phone"
			if($('#phone').val()=="") {
				alert('폰번호 입력하세요');
				$('#phone').focus();
				return false;
			}

		});
		$('.dup').click(function() {
			// 아이디 비어있으면 "아이디 입력하세요" 포커스 리턴
			if($('.id').val()=="") {
				alert('아이디 입력하세요');
				return false;
			}
			$.ajax({
				url:'./MyMemberContent.me',
				data:{'id':$('.id').val()},
				success:function(rdata) {
					$('#divdup').html(rdata);
				}
			});
		});
	});

</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function kakaopost() {
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#zipcode").value = data.zonecode;
           document.querySelector("#address").value = data.address;
        }
    }).open();
}
</script>
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/topheader.jsp"></jsp:include>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<!-- <div id="sub_img_member"></div> -->
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>회원 가입</h1>
<form action="./MyMemberJoinAction.me" method="post" id="join">
<fieldset>
<legend>가입 정보</legend>
<label>아이디</label>
<input type="text" name="id" class="id" placeholder="ID 4~10자리입력">
<input type="button" value="중복 확인" class="dup"><br>
<div id="divdup"></div>
<label>비밀번호</label>
<input type="password" name="pass" id="pass" placeholder="패스워드를 4~8자리로 입력"><br>
<label>비밀번호 확인</label>
<input type="password" name="pass2" id="pass2" placeholder="패스워드 확인"><br>
<label>이메일</label>
<input type="email" name="email" id="email" placeholder="이메일 입력"><br>
<label>우편번호</label>
<input type="text" name="zipcode" id="zipcode" readonly>
<input type="button" value="우편번호찾기" onclick="kakaopost()"><br>
<label>주소</label>
<input type="text" name="address" id="address"><br>
<label>휴대폰</label>
<input type="text" name="phone" id="phone" placeholder="휴대폰 번호 입력"><br>

</fieldset>

<div class="clear"></div>
<div id="buttons">
<input type="submit" value="회원가입" class="submit">
<input type="reset" value="취소" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottomfooter.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>