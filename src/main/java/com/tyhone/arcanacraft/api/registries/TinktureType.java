package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class TinktureType {

	private final String REGISTRY_NAME;
	private final int COLOUR_HEX;
	
	public TinktureType(String registryName, int colourHex){
		this.REGISTRY_NAME = registryName;
		this.COLOUR_HEX = colourHex;
		
		ModTinktureTypes.registerTinktureToList(this);
	}

	public String getTinktureName(){
		return this.REGISTRY_NAME;
	}
	
	public int getColourHex(){
		return COLOUR_HEX;
	}
	
	public String getDisplayName(){
		return this.getUnlocalizedName() + ".name";
	}

	public String getLocalizedName(){
		return REGISTRY_NAME;
	}
	
	public String getUnlocalizedName(){
		return Arcanacraft.MODID + "." + REGISTRY_NAME;
	}

	public static TinktureType getType(String name) {
		for(TinktureType type : TinktureManager.getTinktureTypes()){
			if(name.equals(type.getUnlocalizedName())){
				return type;
			}
		}
		return null;
	}
	
	/*public ResourceLocation getRegistryName(){
		
	}*/
	
}
