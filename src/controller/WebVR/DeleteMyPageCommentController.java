package controller.WebVR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.dao.CommentDAO;

public class DeleteMyPageCommentController implements Controller {
	
	private CommentDAO commentDAO = new CommentDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		if (!UserSessionUtils.hasLogined(request.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/WebVR/login/form";	
		}
		
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		commentDAO.delete(commentId);
		
		return "redirect:/WebVR/myPage?userId=" + userId;
	}

}
