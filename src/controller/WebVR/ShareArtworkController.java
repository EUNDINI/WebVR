package controller.WebVR;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class ShareArtworkController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ��Ʈ�ѷ��� � jsp ���Ϸ� ���ϵǾ�� �ϴ����� �ۼ��ϸ� ��
		return "/WebVR/artworkShare.jsp";
	}

}
