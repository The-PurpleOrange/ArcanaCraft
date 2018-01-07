package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

public class TinktureStack{

    public static final TinktureStack EMPTY = new TinktureStack(ModTinktureTypes.EMPTY, 0);
    
    //private NBTTagCompound stackTagCompound;
	private TinktureType tinktureType;
	private int amount = 0;
	boolean isEmpty;

	public TinktureStack(TinktureType tinktureType, int amount){
		this.tinktureType = tinktureType;
		this.amount = amount;
		this.updateEmptyState();
	}
	
	/*public TinktureStack(NBTTagCompound compound){
		this.tinktureType = TinktureStackUtil.getTinktureTypeFromString(compound.getString("fluidType"));
    	this.amount = compound.getInteger("fluid");
		this.isEmpty = (amount<=0);
	}*/
	
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
	
	/*public void checkEmpty(){
		if(this == EMPTY){
			this.isEmpty = true;
		}
		if(this.amount<=0 || this.tinktureType == null){ //|| this.tinktureType == ModTinktureTypes.EMPTY){
			this.amount = 0;
			this.isEmpty = true;
			this.tinktureType = ModTinktureTypes.EMPTY;
		}
		else{
			this.isEmpty = false;
		}
	}*/
	
	public boolean isEmpty(){
		if(this == EMPTY){
			return true;
		}
		if(this.getAmount() > 0 && this.tinktureType != ModTinktureTypes.EMPTY){
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
}
