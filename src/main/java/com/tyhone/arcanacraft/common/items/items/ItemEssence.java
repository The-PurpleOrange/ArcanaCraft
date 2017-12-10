package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;

public class ItemEssence  extends ModItemBase{

	private final static String[] VARIANTS = {"iron", "gold", "magicite"};
	
	public ItemEssence() {
		super("essence", VARIANTS);
	}
}
