package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 投稿情報画面を表示する処理をするコントローラ.
 * 
 * @author kairi.hashimoto
 *
 */
@Controller
@Transactional
@RequestMapping("/bulletin")
public class ArticleController {

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentform() {
		return new CommentForm();
	}

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 記事一覧画面へフォワード.
	 * 
	 * @param model モデル
	 * @return 記事一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Article> articleList = articleRepository.findAll();
		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}

		model.addAttribute("articleList", articleList);
		return "article/article";
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param form フォーム
	 * @return 記事一覧画面（リダイレクト)
	 */
	@RequestMapping("/postarticle")
	public String postArticle(ArticleForm form) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.insert(article);
		return "redirect:/bulletin/showList";
	}

	/**
	 * コメントを投稿する
	 * 
	 * @param form フォーム
	 * @return 記事一覧画面（リダイレクト）
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm form) {
		System.out.println(form);
		Comment comment = new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		Integer getIntArticleId = Integer.parseInt(form.getArticleId());
		comment.setArticleId(getIntArticleId);
		commentRepository.insert(comment);
		return "redirect:/bulletin/showList";
	}

	@RequestMapping("/deleteArticle")
	public String deleteArticle(int id) {
		System.out.println(id);
		commentRepository.deleteByArticleId(id);
		articleRepository.deleteBy(id);
		return "redirect:/bulletin/showList";
	}
}
