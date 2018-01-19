package com.tyhone.arcanacraft.api.tinkture;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.jei.TinktureIngredientRenderer;

import net.minecraft.nbt.NBTTagCompound;

public class TinktureStack{

    public static final TinktureStack EMPTY = new TinktureStack(ModTinktureTypes.EMPTY, 0);
    
    
    private NBTTagCompound tag;
	private TinktureType tinktureType;
	private int amount = 0;
	boolean isEmpty;

	public TinktureStack(TinktureType tinktureType, int amount){
		this.tinktureType = tinktureType;
		this.amount = amount;
		this.updateEmptyState();
	}

	public TinktureStack(TinktureType tinktureType){
		this(tinktureType, 8);
	}
	
	public TinktureStack(NBTTagCompound nbt) {
		
		TinktureType type = ModTinktureTypes.EMPTY;
		int mt = 0;
		
		if(nbt.hasKey("FluidName")){
			type = TinktureRegistry.getTinktureTypeFromName(nbt.getString("FluidName"));
		}
		if(nbt.hasKey("Amount")){
			mt = nbt.getInteger("Amount");
		}
		this.tinktureType = type;
		this.amount = mt;
		this.updateEmptyState();
	}

	public TinktureType getTinktureType(){
		return tinktureType;
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
		this.updateEmptyState();
	}
	
	public void setTinktureType(TinktureType tinktureType){
		this.tinktureType = tinktureType;
	}
	
	public String getTinktureName(){
		if(this.isEmpty()){
			return "null";
		}
		return this.tinktureType.getTinktureName();
	}
	
	public String getUnlocalizedName(){
		if(this.isEmpty()){
			return "null";
		}
		return this.tinktureType.getUnlocalizedName();
	}
	
	public String getDisplayNameFromStack(){
		if(this.isEmpty()){
			return "null";
		}
		return this.tinktureType.getDisplayName();
	}
	
	public boolean isEmpty(){
		if(this == EMPTY){
			return true;
		}
		if(this.getAmount() > 0){
			return false;
		}
		return true;
	}

    private void updateEmptyState()
    {
        this.isEmpty = this.isEmpty();
    }
	
	public boolean modifyAmount(int amount){
		if((this.amount + amount) >= 0){
			this.amount+=amount;
			this.updateEmptyState();
			//Arcanacraft.logger.info("IT BE >= 0");
			return true;
		}
		//Arcanacraft.logger.info("NAH YA CANT");
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

	public TinktureStack setEmpty() {
		return TinktureStack.EMPTY;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("FluidName", tinktureType.getRegistryName().toString());
        nbt.setInteger("Amount", amount);

        if (tag != null)
        {
            nbt.setTag("Tag", tag);
        }
        return nbt;
    }
}
