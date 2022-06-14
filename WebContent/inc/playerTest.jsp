<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="./dist/skin/pink.flag/css/jplayer.pink.flag.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./dist/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="./dist/add-on/jplayer.playlist.min.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	var myPlaylist = new jPlayerPlaylist({
		jPlayer: "#jquery_jplayer_1",
		cssSelectorAncestor: "#jp_container_1"
	},
	[]
	,
	{
		playlistOptions: {
			enableRemoveControls: true
		},
		swfPath: "../dist/jplayer",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true
	});
	
	$(".playlist-add-bubble1").click(function(eve) {
		eve.preventDefault();
		myPlaylist.add({
			title:$(this).attr("title"),
			artist:$(this).attr("id"),
			free:true,
			mp3:$(this).attr("href")+".mp3",
		});
	});
});
</script>