package controllers;


import models.GameMap;
import play.mvc.Controller;
import util.GsonUtils;

public class MapController extends Controller {

	public static String getGameMap() {
		return GameMap.randomMap();
	}
}
