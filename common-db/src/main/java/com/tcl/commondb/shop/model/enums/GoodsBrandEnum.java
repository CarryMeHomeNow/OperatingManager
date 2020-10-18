package com.tcl.commondb.shop.model.enums;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GoodsBrandEnum {
	DIANSHI(1, "电视"),
	KONGTIAO(2, "空调"),
	BINXIANG(3, "冰箱"),
	XIYIJI(4, "洗衣机"),
	JKDIQI(5, "健康电器"),
	ZHINYJ(6, "智能硬件");

	GoodsBrandEnum(int brandid, String name) {
		this.brandid = brandid;
		this.name = name;
	}
	
	private int brandid;
	private String name;
	public int getBrandid() {
		return brandid;
	}
	public String getName() {
		return name;
	}

	public static GoodsBrandEnum parse(String s) {
		GoodsBrandEnum[] values = values();
		for (GoodsBrandEnum value : values) {
			if (String.valueOf(value.getBrandid()).equals(s) || value.getName().equals(s)) {
				return value;
			}
		}
		return null;
	}

	public static List toList() {
		List list = Lists.newArrayList();//Lists.newArrayList()其实和new ArrayList()几乎一模

		GoodsBrandEnum[] values = values();
		for (GoodsBrandEnum value : values) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", value.getBrandid());
			map.put("name", value.getName());
			list.add(map);
		}
		return list;
	}
}
