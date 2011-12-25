var background = R301.constants.PUBLIC_PATH + '/images/bg.jpg', 
roles = [ {
	name : '',
	money : 1000,
	avatar : R301.constants.PUBLIC_PATH + '/images/shasha.png'
}, {
	name : '',
	money : 1000,
	avatar : R301.constants.PUBLIC_PATH + '/images/sunjie.png'
}, {
	name : '',
	money : 1000,
	avatar : R301.constants.PUBLIC_PATH + '/images/guolin.png'
}, {
	name : '',
	money : 1000,
	avatar : R301.constants.PUBLIC_PATH + '/images/xiaoxiao.png'
}, {
	name : '',
	money : 1000,
	avatar : R301.constants.PUBLIC_PATH + '/images/xiecn.png'
} ], 
	map = {
	"height" : 18,
	"width" : 23,
	"mapCells" : [
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ],
			[ {
				"id" : 126,
				"roadName" : "百子湾路",
				"cellName" : "后现代城",
				"size" : 1,
				"previousId" : 144,
				"nextId" : 127
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 127,
				"roadName" : "百子湾路",
				"cellName" : "红",
				"size" : 1,
				"previousId" : 126,
				"nextId" : 128
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 128,
				"roadName" : "百子湾路",
				"cellName" : "冰城串吧",
				"size" : 1,
				"previousId" : 127,
				"nextId" : 129
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 129,
				"roadName" : "百子湾路",
				"cellName" : "麻辣E世界",
				"size" : 1,
				"previousId" : 128,
				"nextId" : 130
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 130,
				"roadName" : "百子湾路",
				"cellName" : "爬爬车集散中心",
				"size" : 1,
				"previousId" : 129,
				"nextId" : 131
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 131,
				"roadName" : "百子湾路",
				"cellName" : "云南苍蝇菜馆",
				"size" : 1,
				"previousId" : 130,
				"nextId" : 132
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 132,
				"roadName" : "百子湾路",
				"cellName" : "711购物中心",
				"size" : 1,
				"previousId" : 131,
				"nextId" : 133
			}, {
				"id" : 133,
				"roadName" : "百子湾路",
				"cellName" : "595公交车站",
				"size" : 1,
				"previousId" : 132,
				"nextId" : 134
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 134,
				"roadName" : "百子湾路",
				"cellName" : "WON外国零食小店",
				"size" : 1,
				"previousId" : 133,
				"nextId" : 135
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 135,
				"roadName" : "百子湾路",
				"cellName" : "千年老酒店",
				"size" : 1,
				"previousId" : 134,
				"nextId" : 136
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 136,
				"roadName" : "百子湾路",
				"cellName" : "审美美发中心",
				"size" : 1,
				"previousId" : 135,
				"nextId" : 137
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 137,
				"roadName" : "百子湾路",
				"cellName" : "麻渔府",
				"size" : 1,
				"previousId" : 136,
				"nextId" : 138
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 138,
				"roadName" : "百子湾路",
				"cellName" : "大锅粥",
				"size" : 1,
				"previousId" : 137,
				"nextId" : 139
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 139,
				"roadName" : "百子湾路",
				"cellName" : "四惠桥",
				"size" : 1,
				"previousId" : 138,
				"nextId" : 140
			}, {
				"id" : 140,
				"roadName" : "百子湾路",
				"cellName" : "31路公交车站",
				"size" : 1,
				"previousId" : 139,
				"nextId" : 141
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 141,
				"roadName" : "百子湾路",
				"cellName" : "通惠河",
				"size" : 1,
				"previousId" : 140,
				"nextId" : 142
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 142,
				"roadName" : "百子湾路",
				"cellName" : "火车东站",
				"size" : 1,
				"previousId" : 141,
				"nextId" : 143
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 143,
				"roadName" : "百子湾路",
				"cellName" : "东郊市场",
				"size" : 1,
				"previousId" : 142,
				"nextId" : 144
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 144,
				"roadName" : "百子湾路",
				"cellName" : "京客隆",
				"size" : 1,
				"previousId" : 143,
				"nextId" : 145
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 145,
				"roadName" : "百子湾路",
				"cellName" : "软妹子水果吧",
				"size" : 1,
				"previousId" : 144,
				"nextId" : 146
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 146,
				"roadName" : "百子湾路",
				"cellName" : "水晶占卜坊",
				"size" : 1,
				"previousId" : 145,
				"nextId" : 147
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 147,
				"roadName" : "百子湾路",
				"cellName" : "657公交车站",
				"size" : 1,
				"previousId" : 146,
				"nextId" : 165
			}, null ],
			[ {
				"id" : 144,
				"roadName" : "湖南路",
				"cellName" : "长沙火车站",
				"size" : 1,
				"previousId" : 162,
				"nextId" : 126
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"id" : 165,
						"roadName" : "湖南路",
						"cellName" : "海南火车站",
						"size" : 1,
						"previousId" : 147,
						"nextId" : 183
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 162,
				"roadName" : "湖南路",
				"cellName" : "孙家",
				"size" : 1,
				"previousId" : 180,
				"nextId" : 144
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"level" : 0,
						"price" : 0,
						"id" : 183,
						"roadName" : "湖南路",
						"cellName" : "蜗家",
						"size" : 1,
						"previousId" : 165,
						"nextId" : 201
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 180,
				"roadName" : "湖南路",
				"cellName" : "郭孙家",
				"size" : 1,
				"previousId" : 198,
				"nextId" : 162
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"level" : 0,
						"price" : 0,
						"id" : 201,
						"roadName" : "湖南路",
						"cellName" : "莎家",
						"size" : 1,
						"previousId" : 183,
						"nextId" : 219
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 198,
				"roadName" : "湖南路",
				"cellName" : "妹记粉店",
				"size" : 1,
				"previousId" : 216,
				"nextId" : 180
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"level" : 0,
						"price" : 0,
						"id" : 219,
						"roadName" : "湖南路",
						"cellName" : "兔女郎夜总会",
						"size" : 1,
						"previousId" : 201,
						"nextId" : 237
					}, null ],
			[ {
				"id" : 216,
				"roadName" : "湖南路",
				"cellName" : "长沙港",
				"size" : 1,
				"previousId" : 234,
				"nextId" : 198
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"id" : 237,
						"roadName" : "湖南路",
						"cellName" : "长沙港",
						"size" : 1,
						"previousId" : 219,
						"nextId" : 255
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 234,
				"roadName" : "湖南路",
				"cellName" : "杨玉兴粉",
				"size" : 1,
				"previousId" : 252,
				"nextId" : 216
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"level" : 0,
						"price" : 0,
						"id" : 255,
						"roadName" : "湖南路",
						"cellName" : "蜗莎家",
						"size" : 1,
						"previousId" : 237,
						"nextId" : 273
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 252,
				"roadName" : "湖南路",
				"cellName" : "郭家",
				"size" : 1,
				"previousId" : 270,
				"nextId" : 234
			}, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					{
						"level" : 0,
						"price" : 0,
						"id" : 273,
						"roadName" : "湖南路",
						"cellName" : "莎老谢螃蟹",
						"size" : 1,
						"previousId" : 255,
						"nextId" : 291
					}, null ],
			[ {
				"level" : 0,
				"price" : 0,
				"id" : 270,
				"roadName" : "湖南路",
				"cellName" : "长沙乡村基",
				"size" : 1,
				"previousId" : 288,
				"nextId" : 252
			}, null, null, null, null, null, {
				"id" : 276,
				"roadName" : "四川路",
				"cellName" : "绵阳海关",
				"size" : 1,
				"previousId" : 277,
				"nextId" : 294
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 277,
				"roadName" : "四川路",
				"cellName" : "肖小妹之家",
				"size" : 1,
				"previousId" : 278,
				"nextId" : 276
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 278,
				"roadName" : "四川路",
				"cellName" : "川妹子之家",
				"size" : 1,
				"previousId" : 279,
				"nextId" : 277
			}, {
				"id" : 279,
				"roadName" : "四川路",
				"cellName" : "星期八烧烤",
				"size" : 1,
				"previousId" : 280,
				"nextId" : 278
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 280,
				"roadName" : "四川路",
				"cellName" : "人民公园",
				"size" : 1,
				"previousId" : 281,
				"nextId" : 279
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 281,
				"roadName" : "四川路",
				"cellName" : "富乐山公园",
				"size" : 1,
				"previousId" : 282,
				"nextId" : 280
			}, {
				"id" : 282,
				"roadName" : "四川路",
				"cellName" : "老房子",
				"size" : 1,
				"previousId" : 283,
				"nextId" : 281
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 283,
				"roadName" : "四川路",
				"cellName" : "东方红大桥",
				"size" : 1,
				"previousId" : 284,
				"nextId" : 282
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 284,
				"roadName" : "四川路",
				"cellName" : "绵阳二中",
				"size" : 1,
				"previousId" : 285,
				"nextId" : 283
			}, {
				"id" : 285,
				"roadName" : "四川路",
				"cellName" : "南山中学",
				"size" : 1,
				"previousId" : 286,
				"nextId" : 284
			}, {
				"id" : 286,
				"roadName" : "四川路",
				"cellName" : "科委立交桥",
				"size" : 1,
				"previousId" : 304,
				"nextId" : 285
			}, null, null, null, null, {
				"level" : 0,
				"price" : 0,
				"id" : 291,
				"roadName" : "湖南路",
				"cellName" : "海南鲨鱼港",
				"size" : 1,
				"previousId" : 273,
				"nextId" : 309
			}, null ],
			[ {
				"id" : 288,
				"roadName" : "湖南路",
				"cellName" : "湖南大学",
				"size" : 1,
				"previousId" : 289,
				"nextId" : 270
			}, {
				"id" : 289,
				"roadName" : "四川路",
				"cellName" : "肖家",
				"size" : 1,
				"previousId" : 290,
				"nextId" : 288
			}, {
				"id" : 290,
				"roadName" : "四川路",
				"cellName" : "川妹子之家",
				"size" : 1,
				"previousId" : 291,
				"nextId" : 289
			}, {
				"id" : 291,
				"roadName" : "四川路",
				"cellName" : "肖胖娃蹄花",
				"size" : 1,
				"previousId" : 292,
				"nextId" : 290
			}, {
				"id" : 292,
				"roadName" : "四川路",
				"cellName" : "清油小火锅",
				"size" : 1,
				"previousId" : 293,
				"nextId" : 291
			}, {
				"id" : 293,
				"roadName" : "四川路",
				"cellName" : "天天美食",
				"size" : 1,
				"previousId" : 294,
				"nextId" : 292
			}, {
				"id" : 294,
				"roadName" : "四川路",
				"cellName" : "绵阳飞机场",
				"size" : 1,
				"previousId" : 276,
				"nextId" : 293
			}, null, null, null, null, null, null, null, null, null, {
				"level" : 0,
				"price" : 0,
				"id" : 304,
				"roadName" : "四川路",
				"cellName" : "计划生育委员会",
				"size" : 1,
				"previousId" : 305,
				"nextId" : 286
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 305,
				"roadName" : "四川路",
				"cellName" : "翠花街",
				"size" : 1,
				"previousId" : 306,
				"nextId" : 304
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 306,
				"roadName" : "四川路",
				"cellName" : "四川小吃城",
				"size" : 1,
				"previousId" : 307,
				"nextId" : 305
			}, {
				"level" : 0,
				"price" : 0,
				"id" : 307,
				"roadName" : "四川路",
				"cellName" : "新华书店",
				"size" : 1,
				"previousId" : 308,
				"nextId" : 306
			}, {
				"id" : 308,
				"roadName" : "四川路",
				"cellName" : "绵阳电脑城",
				"size" : 1,
				"previousId" : 309,
				"nextId" : 307
			}, {
				"id" : 309,
				"roadName" : "湖南路",
				"cellName" : "海南大学",
				"size" : 1,
				"previousId" : 291,
				"nextId" : 308
			}, null ],
			[ null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null ] ]
};