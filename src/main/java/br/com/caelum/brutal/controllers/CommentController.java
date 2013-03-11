package br.com.caelum.brutal.controllers;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.status;
import br.com.caelum.brutal.auth.LoggedAccess;
import br.com.caelum.brutal.dao.CommentDAO;
import br.com.caelum.brutal.model.Comment;
import br.com.caelum.brutal.model.User;
import br.com.caelum.brutal.validators.CommentValidator;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class CommentController {

	private final Result result;
	private final User currentUser;
	private final CommentDAO comments;
	private final CommentValidator validator;

	public CommentController(Result result, User currentUser, CommentDAO comments, CommentValidator validator) {
		this.result = result;
		this.currentUser = currentUser;
		this.comments = comments;
		this.validator = validator;
	}

	@SuppressWarnings("rawtypes")
	@LoggedAccess
	@Post("/{onWhat}/{id}/comment")
	public void comment(Long id, String onWhat, String message) throws ClassNotFoundException {
		Comment comment = new Comment(currentUser, message);
		if(validator.validate(comment)){
			Class type = Class.forName("br.com.caelum.brutal.model." + onWhat);
			comments.load(type, id).add(comment);
			comments.save(comment);
			result.use(http()).body("<li class=\"comment\">" + message + "</li>");
		}
	}
	

	@Post("/Comment/edit/{id}")
	public void edit(String comment, Long id) {
		Comment original = comments.getById(id);
		if(original.getAuthor().getId() != currentUser.getId()){
			result.use(status()).badRequest("comment.edit.not_author");
			return;
		}
		original.setComment(comment);
		if(validator.validate(original)){
			comments.save(original);
			result.use(http()).body("<p>"+comment+"</p>");
		}
	}


}
