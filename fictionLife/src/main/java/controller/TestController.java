package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class TestController {

	@RequestMapping(value="/test/vo.html")
	public String test() {
		System.out.println("컨트롤러1");
		Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("name", "park");
		object.addProperty("age", 22);
		object.addProperty("success", true);
		String json = gson.toJson(object);
		System.out.println("컨트롤러2");
		return json;
	}
}
