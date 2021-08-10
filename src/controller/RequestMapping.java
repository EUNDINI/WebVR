package controller;

import java.util.HashMap;
import java.util.Map;

import controller.WebVR.ListExhbController;
import controller.WebVR.ViewArtworkController;
import controller.WebVR.ViewExhbController;
import controller.WebVR.user.LoginController;
import controller.WebVR.user.LogoutController;
import controller.WebVR.user.RegisterUserController;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����

		//home
        mappings.put("/", new ListExhbController());
		
		//login, register
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        //exhibition
        mappings.put("/WebVR/exhb", new ViewExhbController());
        
        //artwork
        mappings.put("/WebVR/exhb/artwork", new ViewArtworkController());
        
	}

	public Controller findController(String uri) {
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
