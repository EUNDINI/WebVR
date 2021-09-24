/*
 *  �ش� ȸ�� ���� DB���� ����
 * 
 */

package controller.WebVR.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.User;
import model.dao.UserDAO;

public class DeleteUserController implements Controller {

	private UserDAO userDAO = new UserDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/WebVR/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();	
		int adminId = 1;
		int userId = Integer.parseInt(UserSessionUtils.getLoginUserId(session));
	
		if ((UserSessionUtils.isLoginUser(adminId, session) && 	// �α����� ����ڰ� �������̰� 	
			 userId != adminId)							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!UserSessionUtils.isLoginUser(adminId, session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			UserSessionUtils.isLoginUser(userId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)

			userDAO.remove(String.valueOf(userId));				// ����� ���� ����
			
			if (UserSessionUtils.isLoginUser(adminId, session))	// �α����� ����ڰ� ������ 	
				return "redirect:/WebVR/home";		// ����� ����Ʈ�� �̵�
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/WebVR/logout";		// logout ó��
		}
		
		
		User user = userDAO.findUser(userId);
		if(user.matchPassword(request.getParameter("confirm_pwd"))) {
			userDAO.remove(String.valueOf(userId));
			return "redirect:/WebVR/home";
		}
		
		/* ������ �Ұ����� ��� 
		User user = userDAO.findUser(userId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser(adminId, session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));  */
		
		request.setAttribute("deleteFailed", "true");		
		return "redirect:/WebVR/myPage?userId=" + userId;
	}

}