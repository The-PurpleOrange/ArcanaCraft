package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;

public class ItemIngot  extends ModItemBase{

	private final static String[] VARIANTS = {"magicite", "blood_iron", "alcharium"};
	
	public ItemIngot() {
		super("ingot", VARIANTS);
	}
}
