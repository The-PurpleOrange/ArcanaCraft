package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.ritual.RitualBase;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Ritual extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<Item> {
	
	ResourceLocation registryName;
	String ritualName;
	RitualBase ritual;
	
	public Ritual(String ritualName, RitualBase ritualClass){
		this.ritualName = ritualName;
		this.ritual = ritualClass;
		this.registryName = new ResourceLocation(Arcanacraft.MODID, ritualName);
	}

	public String getUnlocalizedName(){
		return Arcanacraft.MODID + "." + ritualName;
    }

	public String getDisplayName(){
		return Arcanacraft.MODID + "." + ritualName + ".name";
    }
	
	public RitualBase GetRitual(){
		return ritual;
	}

	public boolean compare(String rit) {
		return getUnlocalizedName().equals(rit);
	}

}
