package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
		
		// �ۼ� ����
		// mappings.put("/artist/login/form", new ForwardController("/artist/login_register.jsp"));
		// mappings.put("/artist/login", new LoginController());
	}

	public Controller findController(String uri) {
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}
