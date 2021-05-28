package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリです.
 * 
 * @author kairi.hashimoto
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	/**
	 * Commentオブジェクトを生成するローマッパ.
	 */
	private static final RowMapper<Comment>COMMENT_ROW_MAPPER = (rs,i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	/**
	 * 渡した投稿IDを使って、コメント情報検索を行う.
	 * 
	 * @param articleId 投稿ID
	 * @return 検索されたコメント情報
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "select id,name,content,article_id from comments WHERE article_id=:articleId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}
	/**
	 * コメント情報を挿入します.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		
		String insertSql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId);";
		
		template.update(insertSql, param);
	}
	

}
