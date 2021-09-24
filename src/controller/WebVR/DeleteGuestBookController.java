package controller.WebVR;

import controller.Controller;
import controller.WebVR.user.UserSessionUtils;
import model.dao.GuestBookDAO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteGuestBookController implements Controller {
	
	private GuestBookDAO guestBookDAO = new GuestBookDAO();
	
		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// �α��� ���� Ȯ��
	    	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/WebVR/login/form";		// login form ��û���� redirect
	        }
			HttpSession session = request.getSession();
		
			int gbID = Integer.parseInt(request.getParameter("gbID"));// �Ķ���ͷ� �Խ�Ʈ�� ���̵�
			
		
			guestBookDAO.remove(gbID);//���� 
		
		
		return "/WebVR/myPage.jsp";//�������������� �ؾ��ҵ�
	}

}