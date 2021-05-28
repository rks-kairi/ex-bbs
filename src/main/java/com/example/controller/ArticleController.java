package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

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
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@Autowired
	private ArticleRepository articleRepository;
	/**
	 * 記事一覧画面へフォワード.
	 * 
	 * @param model モデル
	 * @return　記事一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList",  articleList);
		return "article/article";
	}
	/**
	 * 記事を投稿する.
	 * 
	 * @param form フォーム
	 * @return　記事一覧画面（リダイレクト)
	 */
	@RequestMapping("/postarticle")
	public String postArticle(ArticleForm form) {
		System.out.println(form);
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.insert(article);
		return "redirect:/bulletin/showList";
	}
}
