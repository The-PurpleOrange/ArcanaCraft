package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.api.item.IFocusLens;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

public class ItemLens extends ModItemBase implements IFocusLens{

	private final static String[] VARIANTS = {"essence", "soul"};
	
	public ItemLens() {
		super("lens", VARIANTS);
	}

}
