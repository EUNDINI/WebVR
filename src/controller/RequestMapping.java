package controller;

import java.util.HashMap;
import java.util.Map;

import controller.WebVR.CreateCommentController;
import controller.WebVR.CreateGuestBookController;
import controller.WebVR.DeleteCommentController;
import controller.WebVR.GridViewController;
import controller.WebVR.ListExhibitionController;
import controller.WebVR.ShareArtworkController;
import controller.WebVR.ViewArtworkController;
import controller.WebVR.ViewCommentController;
import controller.WebVR.ViewExhibitionController;
import controller.WebVR.ViewGuestBookController;
import controller.WebVR.user.DeleteUserController;
import controller.WebVR.user.LoginController;
import controller.WebVR.user.LogoutController;
import controller.WebVR.user.MyPageController;
import controller.WebVR.user.RegisterUserController;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����

		//home
        mappings.put("/", new ListExhibitionController());
		
		//login, register
        mappings.put("/WebVR/login/form", new ForwardController("/WebVR/loginForm.jsp"));
        mappings.put("/WebVR/login", new LoginController());
        mappings.put("/WebVR/logout", new LogoutController());
        mappings.put("/WebVR/register/form", new ForwardController("/WebVR/registerForm.jsp"));
        mappings.put("/WebVR/register", new RegisterUserController());
        
        //exhibition
        mappings.put("/WebVR/exhb", new ViewExhibitionController());
        
        //artwork
        mappings.put("/WebVR/exhb/artwork", new ViewArtworkController());
        mappings.put("/WebVR/artwork/share", new ShareArtworkController());
        
        //comment
        mappings.put("/WebVR/artwork/comment", new ViewCommentController());
        mappings.put("/WebVR/artwork/comment/create", new CreateCommentController());
        mappings.put("/WebVR/artwork/comment/delete", new DeleteCommentController());

        //myPage
        mappings.put("/WebVR/myPage", new MyPageController());
        mappings.put("/WebVR/myPage/delete", new DeleteUserController());
        
        //guestbook
        mappings.put("/WebVR/exhb/guestbook", new ViewGuestBookController());
        
        //gridview
        mappings.put("/WebVR/exhb/List", new GridViewController());
        mappings.put("/WebVR/exhb/List/artwork", new ViewArtworkController());
        
	}

	public Controller findController(String uri) {
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
