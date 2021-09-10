package controller.WebVR;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.Comment;
import model.dao.CommentDAO;

public class CreateCommentController implements Controller {
	
	private CommentDAO commentDAO = new CommentDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String artworkId = request.getParameter("artworkId");
		
		HttpSession session = request.getSession();
		int userId = UserSessionUtils.getLoginUserId(session); //UserSessionUtils ���� �ǵ� ��� �ִ��� ����� �����ؾ�~
		
		Comment comment = new Comment(
				0, 
				request.getParameter("content"), 
				new Date(), 
				userId, 
				Integer.parseInt(artworkId));
		
		commentDAO.create(comment);
		
		return "redirect:/WebVR/exhb/artwork?artworkId=" + artworkId;
	}

}

