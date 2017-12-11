package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

public class ItemItem  extends ModItemBase{

	private final static String[] VARIANTS = {"bone_ash", "clean_flesh"};
	
	public ItemItem() {
		super("item", Names.MetaItems.ITEM);
	}
}
