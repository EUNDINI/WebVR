/*
 *  myPage �ҷ����� (User ����/�ش� User�� Likes, Visit List)
 * 
 */

package controller.WebVR.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.User;
import model.Visit;
import model.Likes;
import model.dao.UserDAO;
import model.dao.VisitDAO;
import model.dao.LikesDAO;

public class ViewMyPageController implements Controller {

	private UserDAO userDAO = new UserDAO();
	private LikesDAO likesDAO = new LikesDAO();
	private VisitDAO visitDAO = new VisitDAO();

	@SuppressWarnings("null")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/WebVR/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");

		if (UserSessionUtils.getLoginUserId(session).equals(userId)) {
			request.setAttribute("isSameUser", true);
		} else {
			request.setAttribute("isSameUser", false);
		}

    	User user = userDAO.findUser(userId);	// ����� ���� �˻�	
  
    	// �ش� ������� ���ƿ� ����Ʈ�� �湮�� ���� ����Ʈ ��������
    	List<Likes> likesList = likesDAO.findLikesListByUserId(Integer.parseInt(userId));
    	List<Visit> visitList = visitDAO.findVisitListByUserId(Integer.parseInt(userId));
    	
    	request.setAttribute("user", user);		// ����� ���� ����	
    	request.setAttribute("likesList", likesList);
    	request.setAttribute("visitList", visitList);
		return "/WebVR/myPage.jsp";
	}

}
