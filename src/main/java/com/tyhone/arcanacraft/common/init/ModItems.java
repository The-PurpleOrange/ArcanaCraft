package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.items.items.ItemAlchemicDust;
import com.tyhone.arcanacraft.common.items.items.ItemAlchemicalCoal;
import com.tyhone.arcanacraft.common.items.items.ItemChalk;
import com.tyhone.arcanacraft.common.items.items.ItemDust;
import com.tyhone.arcanacraft.common.items.items.ItemEssence;
import com.tyhone.arcanacraft.common.items.items.ItemHammer;
import com.tyhone.arcanacraft.common.items.items.ItemIngot;
import com.tyhone.arcanacraft.common.items.items.ItemItem;
import com.tyhone.arcanacraft.common.items.items.ItemLens;
import com.tyhone.arcanacraft.common.items.items.ItemRedCoal;
import com.tyhone.arcanacraft.common.items.items.ItemSoul;
import com.tyhone.arcanacraft.common.items.items.ItemTinkture;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModItems {
	
	private static final List<ModItemBase> ITEMS = new ArrayList<>();

	public static final ModItemBase RED_COAL = new ItemRedCoal();
	public static final ModItemBase ALCHEMICAL_COAL = new ItemAlchemicalCoal();
	public static final ModItemBase ALCHEMIC_DUST = new ItemAlchemicDust();
	public static final ModItemBase DUST = new ItemDust();
	public static final ModItemBase LENS = new ItemLens();
	public static final ModItemBase SOUL = new ItemSoul();
	public static final ModItemBase ESSENCE = new ItemEssence();
	public static final ModItemBase INGOT = new ItemIngot();
	public static final ModItemBase ITEM = new ItemItem();
	public static final ModItemBase CHALK = new ItemChalk();
	public static final ModItemBase TINKTURE = new ItemTinkture();
	
	public static final ModItemBase HAMMER = new ItemHammer();
	
	private ModItems() {}
	
	public static Collection<ModItemBase> getItems(){
		return ITEMS;
	}
	
	public static void register(ModItemBase item){
		ITEMS.add(item);
	}
}
