package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.tinkture.TinktureType;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityJar extends ModTileEntityBase {
	
	private final String TAG_TYPE = "type";
	private final String TAG_AMOUNT = "amount";
	
	private final int MAX_FLUID = 32;
	private TinktureStack tinktureStack = TinktureStack.EMPTY;
	
	public int getTinktureAmount(){
		return tinktureStack.getAmount();
	}

	public TinktureStack getTinktureStack(){
		return tinktureStack;
	}
	
	public TinktureType getTinktureType(){
		return tinktureStack.getTinktureType();
	}
	
	public int getMaxFluid(){
		return MAX_FLUID;
	}
	
	public void setTinktureType(TinktureType type){
		tinktureStack.setTinktureType(type);
		markForClean();
	}
	
	public boolean removeTinktureAmount(int amount){
		if((tinktureStack.getAmount() - amount) < 0){
			return false;
		}
		else{
			tinktureStack.modifyAmount(-amount);
			markForClean();
			return true;
		}
	}
	
	public int removeTinktureAmountPartial(int amount){
		
		int remainder = 0;
		if((tinktureStack.getAmount() - amount) < 0){
			remainder = ((tinktureStack.getAmount() - amount) * -1);
			tinktureStack.setAmount(0);
		}
		else if(tinktureStack.getAmount() == amount){
			tinktureStack = TinktureStack.EMPTY;
			
		}
		else{
			tinktureStack.modifyAmount(-amount);
		}
		markForClean();
		return remainder;
	}
	
	public boolean addTinktureAmount(TinktureType type, int amount){
		if(tinktureStack.isEmpty() || tinktureStack.getTinktureType() == type){
			if((tinktureStack.getAmount() + amount) > MAX_FLUID){
				return false;
			}
			else{
				if(tinktureStack.isEmpty()){
					tinktureStack = new TinktureStack(type, amount);
				}
				else{
					tinktureStack.modifyAmount(amount);
				}
				markForClean();
				return true;
			}
		}
		return false;
	}
	
	public int addTinktureAmountPartial(TinktureType type, int amount){
		if(tinktureStack.isEmpty() || tinktureStack.getTinktureType() == type){
			if(tinktureStack.getAmount() >= MAX_FLUID){
				return amount;
			}
			else{
				markForClean();
				if((tinktureStack.getAmount() + amount) >= MAX_FLUID){
					int amountLeft = (tinktureStack.getAmount() + amount) - MAX_FLUID;
					tinktureStack.setAmount(MAX_FLUID);
					return amountLeft;
				}
				else{
					tinktureStack.modifyAmount(amount);
					return 0;
				}
			}
		}
		return amount;
	}

	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    	
        if(compound.hasKey(TAG_TYPE)){
        	String stype = compound.getString(TAG_TYPE);
        	TinktureType type = TinktureStackUtil.getTinktureTypeFromString(stype);
        	int amount = compound.getInteger(TAG_AMOUNT);
            tinktureStack = new TinktureStack(type, amount);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
    	compound.setString(TAG_TYPE, tinktureStack.getTinktureName());
    	compound.setInteger(TAG_AMOUNT, tinktureStack.getAmount());
        
        return compound;
    }
}
