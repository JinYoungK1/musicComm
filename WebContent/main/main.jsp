<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/front.css" rel="stylesheet" type="text/css">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<!-- <link href="./css/slider.css" rel="stylesheet" type="text/css"> -->
<!-- <link href="./css/jquery.bxslider.min.css" rel="stylesheet" type="text/css"> -->
<link href="./css/hov.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

<!--[if IE 6]>
 <script src="script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]--> 
 <script type="text/javascript" src="./script/jquery-3.6.0.js"></script>
 <script src="./script/jquery.bxslider.min.js"></script>
<script type="text/javascript">
// class="brown" 클릭했을때 json 데이터 출력
$(document).ready(function() {
	
// 		alert("클릭");
		$.ajax({
			url:'./MyMusicListJson.mu',
			dataType:'json',
			success:function(rdata) {
				// rdata => json => 여러개 데이터 => 반복
				$.each(rdata, function(index, item) {
if(index == 0 || index == 5 || index == 10) {
	$('#bxslider1').append('<li><ul class="list-album"><li><a><img src="./albums/'+item.album_name+'.jpg" style="width: 100%;" /><span class="box"><span class="titleM">'+item.music_name+'</span><span class="artistM">'+item.artist_name+'</span></span></a>'+index+'</li>');
} 
if((index > 0 && index < 4) || (index > 5 && index < 9) || (index > 10 && index < 14)) {
	$('.list-album').append('<li><a><img src="./albums/'+item.album_name+'.jpg" style="width: 100%;"><span class="box"><span class="titleM">'+item.music_name+'</span><span class="artistM">'+item.artist_name+'</span></span></a>'+index+'</li>');					
}
if(index == 4 || index == 9 || index == 14) {
	$('.list-album').append('<li><a><img src="./albums/'+item.album_name+'.jpg" style="width: 100%;"><span class="box"><span class="titleM">'+item.music_name+'</span><span class="artistM">'+item.artist_name+'</span></span></a>'+index+'</li></ul></li>');
}
					});
				$('#bxslider1').bxSlider({
					auto: false,
					speed: 500,
					pause: 500,
					mode: 'fade',
					autoControls: true,
					pager: true,
					pagerType: 'short'
				});
			} // success
		});
		$.ajax({
			url:"./MyGalListJson.ga",
			dataType:'json',
			success:function(gdata) {
				$.each(gdata, function(idx, items) {
					$('#bxslider2').append('<li><img src="./gals/'+items.file_name+'"  /></li>');
				});
				$('#bxslider2').bxSlider({
					auto: true,
					speed: 500,
					pause: 4000,
					mode: 'fade',
					controls: false,
					pager: false,
					pagerType: 'short'
				});
			}
		});
		$.ajax({
			url:"./MyMusicChartJson.mu",
			dataType:'json',
			success:function(mdata) {
				$.each(mdata, function(i, items) {
					$('.chart').append('<tr style="font-weight: bold;"><td style="width: 30px;">'+(i+1)+'</td><td style="width: 300px;">'+items.music_name+'</td><td style="width:80px;">'+items.artist_name+'</td></tr>');
				});
			}
		});
		$('#bxslider3').bxSlider({
			auto: true,
			speed: 500,
			pause: 4000,
			mode: 'fade',
			controls: false,
			pager: false,
			pagerType: 'short'
		});
		
		
});

</script>
<style type="text/css">
.recent-music{
position: relative;
width: 942px;
margin: 0 auto;
}
.recent-music li{
display:block;
overflow:hidden;
float: left;
position: relative;
width: 151px;
height: 151px;
margin: 0 3px 6px;
}
.recent-gal{
position: relative;
width: 100%;
height: 300px;
margin: 0 auto;
}
.recent-gal li img{
display:block;
overflow:hidden;
float: left;
position: relative;
width: 300px;
height: 300px;
margin: 0 3px 6px;
}
.bx-wrapper .bx-pager {
	text-align: center;
	font-size: .85em;
	font-family: Arial;
	font-weight: 700;
	color: #666;
	padding-top: 20px
}
.bx-wrapper .bx-prev {
	left: 10px;
	background: url(main/images/controls.png) 0 -32px no-repeat
}
.bx-wrapper .bx-prev:focus, .bx-wrapper .bx-prev:hover {
	background-position: 0 0
}
.bx-wrapper .bx-next:focus, .bx-wrapper .bx-next:hover {
	background-position: -43px 0
}
.bx-wrapper .bx-next {
	right: 10px;
	background: url(main/images/controls.png) -43px -32px no-repeat
}
.bx-wrapper .bx-controls-direction a {
	position: absolute;
	top: 50%;
	margin-top: -16px;
	outline: 0;
	width: 32px;
	height: 32px;
	text-indent: -9999px;
	z-index: 9999
}

.bx-wrapper .bx-controls-direction a.disabled {
	display: none
}

.bx-wrapper .bx-controls-auto {
	text-align: center
}
</style>
<style type="text/css">
table{
	border-top: 1px solid;
	border-bottom: 1px solid;
	margin: 10px;
	width: 430px;
	table-layout: fixed;
}
table tr td{
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	
}
td{
	border-bottom: 1px solid;
	border-bottom-color: gray;
	padding: 10px;
	font-size: 14px;
	font-style: italic;
	color: blue;
	text-shadow: 4px 4px 5px aqua;
}
table tr:hover td{
	font-size: 25px;
	height: 50px;
	font-style: italic;
	color: red;
	text-shadow: 4px 4px 5px purple;
}


#bxslider3>li{
	height: 200px;
	margin-bottom: 20px;
}
#bxslider3>li>a>img{
	margin-left: 100px;
	height: 100%;
	float: left;
}
.pagego{
			width: 100%; height: 70px;
			float:right;
			position: absolute;
			color: black;
			margin-top: 40px;
			font-size: 16px;
			padding-top: 45px;
			
			}
</style>
</head>
<body>

<div id="wrap">
<div id="wrap-head">

<jsp:include page="../inc/topheader.jsp"></jsp:include>
</div>
<!-- 헤더파일들어가는 곳 -->
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>

<!-- <div id="main_img"> -->
<!-- <img src="./images/main_img.jpg" width="971" height="282"> -->
<!-- <div class="bx-warpper"> -->
<span style="padding-left: 20px;font-weight: bold;font-style: italic;font-size: 20px;color: blue;text-shadow: 4px 4px 5px aqua;">최근 업데이트 곡</span>
<div class="recent-music">
<ul id="bxslider1">

</ul>
</div>
<!-- </div> -->
<!-- </div> -->
<!-- 메인이미지 들어가는곳 -->
<!-- 메인 콘텐츠 들어가는 곳 -->



<article id="front">
<div id="solution">

<ul id="bxslider3">
<li><a href="https://mcountdown.genie.co.kr/"><img src="./main/images/Mcount.jpg"><span class="pagego">공식 홈페이지 바로가기</span></a></li>
<li><a href="https://program.genie.co.kr/queendom2/main"><img src="./main/images/qd.png"><span class="pagego">공식 홈페이지 바로가기</span></a></li>
<li><a href="https://program.genie.co.kr/tmishow/main"><img src="./main/images/tmishow.jpg"><span class="pagego">공식 홈페이지 바로가기</span></a></li>
<li><a href="https://program.genie.co.kr/yourvoice/main"><img src="./main/images/youvoice.jpg"><span class="pagego">공식 홈페이지 바로가기</span></a></li>
</ul>


</div>
<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">실시간</span> 차트</h3>

<table class="chart">
</table>

</div>
<div id="news_notice">
<h3 class="brown">최신 갤러리</h3>

<div class="recent-gal">
<ul id="bxslider2">

</ul>
</div>

</div>
</article>

<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottomfooter.jsp"></jsp:include>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>