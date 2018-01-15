package com.tyhone.arcanacraft.api.tinkture;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class TinktureType extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<TinktureType>{

	private final String REGISTRY_NAME;
	private final int COLOUR_HEX;
	
	public TinktureType(String registryName, int colourHex){
		this.REGISTRY_NAME = registryName;
		this.COLOUR_HEX = colourHex;
		setRegistryName(registryName);
		
		ModTinktureTypes.registerTinktureToList(this);
	}

	public String getTinktureName(){
		return this.REGISTRY_NAME;
	}
	
	public int getColourHex(){
		return COLOUR_HEX;
	}
	
	public String getDisplayName(){
        return I18n.translateToLocal(this.getUnlocalizedName() + ".name").trim();
	}
	
	public String getUnlocalizedName(){
		return "tinkture." + Arcanacraft.MODID + ":" + REGISTRY_NAME;
	}

	public static TinktureType getType(String name) {
		for(TinktureType type : TinktureRegistry.getTinktureTypes()){
			if(name.equals(type.getRegistryName().toString())){
				return type;
			}
		}
		return null;
	}
}
