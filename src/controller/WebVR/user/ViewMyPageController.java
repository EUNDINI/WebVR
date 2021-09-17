/*
 *  myPage �ҷ����� (User ����/�ش� User�� Likes, Visit List)
 * 
 */

package controller.WebVR.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.User;
import model.Visit;
import model.Likes;
import model.Artwork;
import model.Comment;
import model.Exhibition;
import model.dao.UserDAO;
import model.dao.VisitDAO;
import model.dao.LikesDAO;
import model.dao.CommentDAO;
import model.dao.ExhibitionDAO;

public class ViewMyPageController implements Controller {

	private UserDAO userDAO = new UserDAO();
	private LikesDAO likesDAO = new LikesDAO();
	private VisitDAO visitDAO = new VisitDAO();
	private CommentDAO commentDAO = new CommentDAO();
	private ExhibitionDAO exhibitionDAO = new ExhibitionDAO();

	@SuppressWarnings("null")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/WebVR/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");

		if (UserSessionUtils.getLoginUserId(session).equals(userId)) {
			request.setAttribute("isSameUser", true);
		} else {
			request.setAttribute("isSameUser", false);
		}*/

    	User user = userDAO.findUser("1");	// ����� ���� �˻�	
  
    	// �ش� ������� ���ƿ� ����Ʈ�� �湮�� ���� ����Ʈ ��������
    	List<Likes> likesList = likesDAO.findLikesListByUserId(1);
    	List<Visit> visitList = visitDAO.findVisitListByUserId(1);
    	List<Comment> commentList = commentDAO.findCommentListByUserId(1);
    	List<Exhibition> exhibitionList = exhibitionDAO.findExhibitionListByUserId(1);
    	HashMap<Integer, List<Artwork>> artworkMap = new HashMap<>();
    	
    	// user�� ������ exhibition���� Artwork�� ������
    	for(int i = 0; i < exhibitionList.size(); i++){
    		List<Artwork> artworkList = exhibitionDAO.findArtworkListById(exhibitionList.get(i).getId());
    		artworkMap.put(i, artworkList);
    	}
    	
    	
    	request.setAttribute("user", user);		// ����� ���� ����	
    	request.setAttribute("likesList", likesList);
    	request.setAttribute("visitList", visitList);
    	request.setAttribute("commentList", commentList);
    	request.setAttribute("exhibitionList", exhibitionList);
    	request.setAttribute("artworkMap", artworkMap);
    	
		return "/WebVR/myPage.jsp";
	}

}
