package com.myproject.music.db;


public class MusicDTO {
	
	private int num;
	private String music_name;
	private String artist_name;
	private String genre;
	private String album_name;
	private int readcount;
	
	// 답글관련 정보
	private int re_ref;
	private int re_lev;
	private int re_seq;
	
	private String date;
	private String ip;
	private String file;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "MusicDTO [num=" + num + ", music_name=" + music_name + ", artist_name=" + artist_name + ", genre="
				+ genre + ", album_name=" + album_name + ", readcount=" + readcount + ", re_ref=" + re_ref + ", re_lev="
				+ re_lev + ", re_seq=" + re_seq + ", date=" + date + ", ip=" + ip + ", file=" + file + "]";
	}
	
	
}
