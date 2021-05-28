package com.example.domain;

/**
 * 記事情報を表すドメイン.
 * 
 * @author kairi.hashimoto
 *
 */
public class Article {
	/** ID. */
	private Integer id;
	/** 投稿者名. */
	private String name;
	/** 投稿内容. */
	private String content;
//	private List<Comment> commentList;

	public Article() {

	}

	public Article(Integer id, String name, String content) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + "]";
	}

}
