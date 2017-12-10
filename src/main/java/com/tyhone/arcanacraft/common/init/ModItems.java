package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.items.items.ItemLens;
import com.tyhone.arcanacraft.common.items.items.ItemRedCoal;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModItems {
	
	private static final List<ModItemBase> ITEMS = new ArrayList<>();

	public static final ModItemBase RED_COAL = new ItemRedCoal();
	public static final ModItemBase LENS = new ItemLens();
	
	private ModItems() {}
	
	public static Collection<ModItemBase> getItems(){
		return ITEMS;
	}
	
	public static void register(ModItemBase item){
		ITEMS.add(item);
	}
}
