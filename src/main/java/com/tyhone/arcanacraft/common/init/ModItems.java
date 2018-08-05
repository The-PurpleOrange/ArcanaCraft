package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.items.items.ItemAlchemicalCoal;
import com.tyhone.arcanacraft.common.items.items.ItemAyre;
import com.tyhone.arcanacraft.common.items.items.ItemChalk;
import com.tyhone.arcanacraft.common.items.items.ItemCrystal;
import com.tyhone.arcanacraft.common.items.items.ItemDust;
import com.tyhone.arcanacraft.common.items.items.ItemEmptyTinkture;
import com.tyhone.arcanacraft.common.items.items.ItemEssence;
import com.tyhone.arcanacraft.common.items.items.ItemEvolite;
import com.tyhone.arcanacraft.common.items.items.ItemGorgonEye;
import com.tyhone.arcanacraft.common.items.items.ItemIcons;
import com.tyhone.arcanacraft.common.items.items.ItemIngot;
import com.tyhone.arcanacraft.common.items.items.ItemItem;
import com.tyhone.arcanacraft.common.items.items.ItemLens;
import com.tyhone.arcanacraft.common.items.items.ItemMetamorphicChalk;
import com.tyhone.arcanacraft.common.items.items.ItemMulch;
import com.tyhone.arcanacraft.common.items.items.ItemNeedle;
import com.tyhone.arcanacraft.common.items.items.ItemRedCoal;
import com.tyhone.arcanacraft.common.items.items.ItemShard;
import com.tyhone.arcanacraft.common.items.items.ItemSoul;
import com.tyhone.arcanacraft.common.items.items.ItemSpiritSkull;
import com.tyhone.arcanacraft.common.items.items.ItemStar;
import com.tyhone.arcanacraft.common.items.items.ItemThaumonuclearBall;
import com.tyhone.arcanacraft.common.items.items.ItemTinkture;
import com.tyhone.arcanacraft.common.items.items.ItemWarpHeart;
import com.tyhone.arcanacraft.common.items.items.tools.ItemHammer;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandDebug;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandExodus;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandFire;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandGrapple;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandIllumination;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWandShielding;
import com.tyhone.arcanacraft.common.items.items.tools.ItemTrinketHoverCharm;
import com.tyhone.arcanacraft.common.items.items.tools.ItemTrinketHungerCharm;
import com.tyhone.arcanacraft.common.items.items.tools.ItemTrinketMagnet;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModItems {
	
	private static final List<ModItemBase> ITEMS = new ArrayList<>();

	public static final ModItemBase RED_COAL = new ItemRedCoal();
	public static final ModItemBase ALCHEMICAL_COAL = new ItemAlchemicalCoal();
	//public static final ModItemBase ALCHEMIC_DUST = new ItemAlchemicDust();
	public static final ModItemBase DUST = new ItemDust();
	public static final ModItemBase LENS = new ItemLens();
	public static final ModItemBase SOUL = new ItemSoul();
	public static final ModItemBase ESSENCE = new ItemEssence();
	public static final ModItemBase SHARD = new ItemShard();
	public static final ModItemBase INGOT = new ItemIngot();
	public static final ModItemBase ITEM = new ItemItem();
	public static final ModItemBase CRYSTAL = new ItemCrystal();
	public static final ModItemBase MULCH = new ItemMulch();
	public static final ModItemBase STAR = new ItemStar();
	public static final ModItemBase AYRE = new ItemAyre();
	public static final ModItemBase EVOLITE = new ItemEvolite();
	public static final ModItemBase GORGON_EYE = new ItemGorgonEye();
	public static final ModItemBase NEEDLE = new ItemNeedle();
	public static final ModItemBase CHALK = new ItemChalk();
	public static final ModItemBase CHALK_METAMORPHIC = new ItemMetamorphicChalk();
	public static final ModItemBase TINKTURE = new ItemTinkture();
	public static final ModItemBase EMPTY_TINKTURE = new ItemEmptyTinkture();

	public static final ModItemBase HAMMER = new ItemHammer();
	public static final ModItemBase WARP_HEART = new ItemWarpHeart();
	public static final ModItemBase SPIRIT_SKULL = new ItemSpiritSkull();
	
	public static final ModItemBase ICONS = new ItemIcons();

	public static final ModItemBase THAUMONUCLEAR_BALL = new ItemThaumonuclearBall();

	public static final ModItemBase TOOL_WAND_DEBUG = new ItemToolWandDebug();

	public static final ModItemBase TOOL_WAND_ILLUMINATION = new ItemToolWandIllumination();
	public static final ModItemBase TOOL_WAND_GRAPPLE = new ItemToolWandGrapple();
	
	public static final ModItemBase TOOL_WAND_EXODUS = new ItemToolWandExodus();
	public static final ModItemBase TOOL_WAND_SHIELDING = new ItemToolWandShielding();
	public static final ModItemBase TOOL_WAND_FIRE = new ItemToolWandFire();

	public static final ModItemBase TRINKET_HUNGER_CHARM = new ItemTrinketHungerCharm();
	public static final ModItemBase TRINKET_MAGNET = new ItemTrinketMagnet();
	public static final ModItemBase TRINKET_HOVER_CHARM = new ItemTrinketHoverCharm();

	
	private ModItems() {}
	
	public static Collection<ModItemBase> getItems(){
		return ITEMS;
	}
	
	public static void register(ModItemBase item){
		ITEMS.add(item);
	}
}
