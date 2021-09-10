/*
 *  �������������� ȸ������ ���� �� form �޾ƿͼ� ������Ʈ
 * 
 */

package controller.WebVR.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.User;
import model.dao.UserDAO;

public class UpdateMyPageController implements Controller {

	private UserDAO userDAO = new UserDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/WebVR/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		User user = userDAO.findUser(userId);
		
		//GET request: form ��û
		if (request.getMethod().equals("GET")) {
			request.setAttribute("user", user);
			
			if (UserSessionUtils.isLoginUser(userId, session) ||
				UserSessionUtils.isLoginUser("admin", session)) {
				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
								
				return "/WebVR/myPage.jsp";   // �˻��� ����� ������ update form���� ����     
			}    
			
//			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//				new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
//			return "/myPage/myPage.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
		}
			
		User updateUser = new User(
				Integer.parseInt(userId),
				user.getEmail(),
				user.getPassword(),
				user.getNickname());
		try {
			userDAO.update(updateUser);
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 

		request.setAttribute("user", updateUser);
		return "redirect:/WebVR/myPage.jsp";
	}

}