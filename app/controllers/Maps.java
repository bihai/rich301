package controllers;


import models.GameMap;
import play.mvc.Controller;
import util.GsonUtils;

public class Maps extends Controller {

	public static void random() {
		String mapJson =  GameMap.randomMap();
		renderJSON(mapJson);
	}
}
