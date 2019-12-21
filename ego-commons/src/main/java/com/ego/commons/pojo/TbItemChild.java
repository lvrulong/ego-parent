package com.ego.commons.pojo;

import com.ego.pojo.TbItem;

public class TbItemChild extends TbItem{

	private String[] images;
	
	/**
	 * 判断库存
	 */
	private Boolean enough;

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public Boolean getEnough() {
		return enough;
	}

	public void setEnough(Boolean enough) {
		this.enough = enough;
	}

	
	
}
