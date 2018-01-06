package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class TinktureStack {

    private NBTTagCompound stackTagCompound;
	private TinktureType tinktureType;
	private int amount = 0;
	boolean isEmpty;
	
	public TinktureStack(TinktureType tinktureType, int amount){
		this.tinktureType = tinktureType;
		this.amount = amount;
		this.isEmpty = (amount<=0);
	}
	
	public TinktureType getTinktureType(){
		return tinktureType;
	}
	
	public TinktureType getTinktureTypeFromString(String tinktureType){
		for(TinktureType type : TinktureManager.getTinktureTypes()){
    		if(tinktureType == type.getTinktureName()){
    			return type;
    		}
    	}
		return ModTinktureTypes.EMPTY;
	}
	
	public TinktureStack copy(){
		return new TinktureStack(tinktureType, amount);
	}
	
	public int getAmount(){
		if(tinktureType != ModTinktureTypes.EMPTY){
			return amount;
		}
		return 0;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
		checkEmpty();
	}
	
	public void setTinktureType(TinktureType tinktureType){
		this.tinktureType = tinktureType;
	}
	
	public void checkEmpty(){
		if(this.tinktureType == ModTinktureTypes.EMPTY){
			this.amount = 0;
			this.isEmpty = true;
		}
		if(this.amount<=0){
			this.amount = 0;
			this.isEmpty = true;
			this.tinktureType = ModTinktureTypes.EMPTY;
		}
		else{
			this.isEmpty = false;
		}
	}
	
	public boolean isEmpty(){
		return this.isEmpty;
	}
	
	public boolean modifyAmount(int amount){
		if((this.amount + amount) >= 0){
			this.amount+=amount;
			checkEmpty();
			return true;
		}
		return false; 
	}
	
	public boolean canModifyAmount(int amount){
		if((this.amount + amount) >= 0){
			return true;
		}
		return false; 
	}

	public int getRemainderModify(int amount){
		if((this.amount + amount) >= 0){
			return (amount+this.amount);
		}
		return -1; 
	}

	/*public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        String fluidType = this.getTinktureType().getFluidType();
        nbt.setString("id", fluidType == null ? "null" : fluidType);
        nbt.setInteger("amount", this.amount);

        if (this.stackTagCompound != null)
        {
            nbt.setTag("tag", this.stackTagCompound);
        }

        return nbt;
    }*/
	
}
