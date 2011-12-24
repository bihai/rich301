package models;

import util.RichUtil;

public class MapGenerator {

	/*
	 * 
	 */
	public static GameMap generateTestMap() {
		GameMap gameMap = new GameMap(5, 5);
		
		EstateMapCell cell00 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 0, 0), "百子湾路", "后现代城", 2000);
		EstateMapCell cell01 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 0, 1), "百子湾路", "红", 2000);
		EstateMapCell cell02 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 0, 2), "百子湾路", "冰城串吧", 2000);
		EstateMapCell cell03 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 0, 3), "百子湾路", "麻辣E世界", 2000);
		EstateMapCell cell04 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 0, 4), "百子湾路", "爬爬车集散中心", 2000);
		
		EstateMapCell cell10 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 1, 0), "湖南路", "郭家", 2000);
		EstateMapCell cell20 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 2, 0), "湖南路", "孙家", 2000);
		EstateMapCell cell30 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 3, 0), "湖南路", "郭孙家", 2000);
		EstateMapCell cell40 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 4, 0), "湖南路", "妹记粉店", 2000);
		
		EstateMapCell cell14 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 1, 4), "海南路", "蜗家", 2000);
		EstateMapCell cell24 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 2, 4), "海南路", "莎家", 2000);
		EstateMapCell cell34 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 3, 4), "海南路", "蜗莎家", 2000);
		EstateMapCell cell44 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 4, 4), "海南路", "莎老谢螃蟹", 2000);
		
		EstateMapCell cell41 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 4, 1), "四川路", "肖家", 2000);
		EstateMapCell cell42 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 4, 2), "四川路", "川妹子之家", 2000);
		EstateMapCell cell43 = new EstateMapCell(RichUtil.retrieveCellId(gameMap, 4, 3), "四川路", "肖胖娃蹄花", 2000);
		
		gameMap.mapCells[0][0] = cell00;
		gameMap.mapCells[0][1] = cell01;
		gameMap.mapCells[0][2] = cell02;
		gameMap.mapCells[0][3] = cell03;
		gameMap.mapCells[0][4] = cell04;

		gameMap.mapCells[1][0] = cell10;
		gameMap.mapCells[2][0] = cell20;
		gameMap.mapCells[3][0] = cell30;
		gameMap.mapCells[4][0] = cell40;
		
		gameMap.mapCells[1][4] = cell14;
		gameMap.mapCells[2][4] = cell24;
		gameMap.mapCells[3][4] = cell34;
		gameMap.mapCells[4][4] = cell44;
		
		gameMap.mapCells[4][1] = cell41;
		gameMap.mapCells[4][2] = cell42;
		gameMap.mapCells[4][3] = cell43;
		
		cell00.pendNext(cell01);
		cell01.pendNext(cell02);
		cell02.pendNext(cell03);
		cell03.pendNext(cell04);
		cell04.pendNext(cell14);
		cell14.pendNext(cell24);
		cell24.pendNext(cell34);
		cell34.pendNext(cell44);
		cell44.pendNext(cell43);
		cell43.pendNext(cell42);
		cell42.pendNext(cell41);
		cell41.pendNext(cell40);
		cell40.pendNext(cell30);
		cell30.pendNext(cell20);
		cell20.pendNext(cell10);
		cell10.pendNext(cell00);

		
		return gameMap;
	}
}
