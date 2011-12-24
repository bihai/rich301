package controllers;


import models.Map;
import play.mvc.Controller;
import util.GsonUtils;

public class Maps extends Controller {

	public static void random() {
		String mapJson =  Map.randomMap();
		renderJSON(mapJson);
	}
}
