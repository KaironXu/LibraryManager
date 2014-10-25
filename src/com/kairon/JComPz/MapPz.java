package com.kairon.JComPz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kairon.dao.Dao;
import com.kairon.model.BookType;

/**
 * 获得所有图书类别typeID
 * @author kairon
 *
 */
public class MapPz {
	static Map<String,Item> map = new HashMap<String,Item>();

	public static Map<String,Item> getMap() {
		List<BookType> list = Dao.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);

			Item item = new Item();
			item.setId(booktype.getId());
			item.setName(booktype.getTypeName());
			map.put(item.getId(), item);

		}
		return map;
	}
}
