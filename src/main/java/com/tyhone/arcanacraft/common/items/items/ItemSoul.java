package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.api.item.IFocusLens;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

public class ItemSoul extends ModItemBase{

	private final static String[] VARIANTS = {"fragment", "complete"};
	
	public ItemSoul() {
		super("soul", VARIANTS);
	}
}
