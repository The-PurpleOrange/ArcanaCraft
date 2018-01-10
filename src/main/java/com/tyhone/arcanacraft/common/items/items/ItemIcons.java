package com.tyhone.arcanacraft.common.items.items;

import java.util.ArrayList;
import java.util.Arrays;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;

public class ItemIcons extends ModItemBase{
	
	private static final ArrayList<String> ITEMS = new ArrayList<String>(Arrays.asList("alchemic_array", "ritual_circle"));
	
	public ItemIcons(){
		super("icons", ITEMS);
	}
}
