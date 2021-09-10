package controller.WebVR;

import controller.Controller;
import model.GuestBook;
import model.GuestBookUser;
import model.dao.ExhibitionDAO;
import model.dao.GuestBookDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateGuestBookController implements Controller {
	private GuestBookDAO guestBookDAO = new GuestBookDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int exhbId = Integer.parseInt(request.getParameter("ExhbId"));// �Ķ���ͷ� ���� ���̵� ��������
		// �α����� ����� ���̵� ��� ������..??
		int userID = Integer.parseInt(request.getParameter("UserId"));// �Ķ���ͷ� ���� ���̵� ��������
		Date date_now = new Date(System.currentTimeMillis());
		GuestBook gb = new GuestBook(0/* �ڵ����� �ο��Ǵ� ���̵� */, userID, exhbId, request.getParameter("content"),
				date_now);

		try {
			guestBookDAO.create(gb);
			List<GuestBookUser> GBUList = guestBookDAO.getGuestBookList(exhbId);
			request.setAttribute("GBUList", GBUList);	
			return "/webVR/Guestbook.jsp";
		} catch (Exception e) {
			return "/webVR/Guestbook.jsp";//���� �ʿ�
		}

	}

}