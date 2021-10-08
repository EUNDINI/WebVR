package controller.WebVR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.Artwork;
import model.Likes;
import model.User;
import model.dao.ArtworkDAO;
import model.dao.LikesDAO;
import model.dao.UserDAO;

public class ViewArtworkController implements Controller {

	private ArtworkDAO artworkDAO = new ArtworkDAO();
	private LikesDAO likesDAO = new LikesDAO();
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int artworkId = Integer.parseInt(request.getParameter("artworkId"));
		Artwork artwork = artworkDAO.findArtwork(artworkId);
		request.setAttribute("artwork", artwork);
		
		// �α����� �Ǿ��ִ��� Ȯ��
		HttpSession session = request.getSession();
		if (!UserSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			request.setAttribute("login", "false");
			request.setAttribute("like", "false");
        } else { // �α��� �Ǿ��ִ� ���
        	request.setAttribute("login", "true");
        	
        	int userId = Integer.parseInt(UserSessionUtils.getLoginUserId(session));
			User user = userDAO.findUser(userId);
			Likes like = likesDAO.findLikesByUserId(userId, artworkId); // DB���� user�� artwork�� ���ƿ並 ���� ��� Ȯ��
			
			if (like == null) { // ���ƿ並 ���� ����� ���ٸ�
				request.setAttribute("like", "false");
			}
			else { // ���ƿ並 ���� ����� �ִٸ�
				request.setAttribute("like", "true");
			}
        }
		
		artworkDAO.updateView(artwork); // ��ȸ�� ����
		
		return "/WebVR/artwork.jsp";
	}

}
