package controller.WebVR.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dao.CommentDAO;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			// �𵨿� �α��� ó���� ����
			UserManager manager = UserManager.getInstance();
			manager.login(email, password);
	
			CommentDAO commentDAO = new CommentDAO();
			User user = commentDAO.findUserByEmail(email);
			// ���ǿ� ����� ���̵� ����
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, String.valueOf(user.getUserID()));
            
            return "redirect:/WebVR/home";			
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/WebVR/loginform.jsp";			
		}	
    }
}
