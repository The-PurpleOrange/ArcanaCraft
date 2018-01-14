package com.tyhone.arcanacraft.api.ritual;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;
import com.tyhone.arcanacraft.common.init.ModRituals;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class RitualType extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<RitualType> implements IRitualType{

	private ResourceLocation registryName;
	private String ritualName;
	private List<Ritual> ritualTypeList;
	private List<RecipeRitualCircle> ritualRecipeList;
	private List<BlockPos> posOrderList;
	private int[] placeOrder;
	
	public RitualType(String ritualName, List<Ritual> ritualTypeList, List<RecipeRitualCircle> ritualRecipeList, List<BlockPos> posOrderList, int[] placeOrder) {
		this.ritualName = ritualName;
		this.registryName = new ResourceLocation(Arcanacraft.MODID, ritualName);
		this.ritualTypeList = ritualTypeList;
		this.ritualRecipeList = ritualRecipeList;
		this.posOrderList = posOrderList;
		this.placeOrder = placeOrder;
		ModRituals.register(this);
	}

	@Override
	public String getUnlocalizedName(){
		return "ritual." + Arcanacraft.MODID + ":" + ritualName;
    }

	public String getDisplayName(){
        return I18n.translateToLocal(this.getUnlocalizedName() + ".name").trim();
    }

	public List<Ritual> getRitualList() {
		return this.ritualTypeList;
	}

	public List<RecipeRitualCircle> getRitualRecipeList(){
		return this.ritualRecipeList;
	}

	public List<BlockPos> getRitualRecipePosList(){
		return this.posOrderList;
	}

	public int[] getRitualRecipePlaceOrder(){
		return this.placeOrder;
	}
}
