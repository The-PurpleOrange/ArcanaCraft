package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.nbt.NBTTagCompound;

public class TinktureType {

	private final String fluidType;
	private final int colourHex;
	
	public TinktureType(String fluidType, int colourHex){
		this.fluidType = fluidType;
		this.colourHex = colourHex;
		
		ModTinktureTypes.registerTinktureToList(this);
	}

	public String getTinktureName(){
		return this.fluidType;
	}
	
	public int getColourHex(){
		return colourHex;
	}
	
}
