package com.javaex.vo;

public class PostVo {
	
	private int postNo;
	private int cateNo;
	private String postTitle;
	private String postContent;
	private String regDate;
	private String userName;
	
	public PostVo() {
		super();
	}

	public PostVo(int cateNo, String postTitle, String postContent) {
		super();
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
	}

	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		super();
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}
	

	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate, String userName) {
		super();
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
		this.userName = userName;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", regDate=" + regDate + ", userName=" + userName + "]";
	}

	
	
	

}
