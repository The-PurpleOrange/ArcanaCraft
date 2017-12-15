package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityJar extends ModTileEntityBase {
	
	private final double maxFluid = 32D;
	private double fluid = 0D;
	private String fluidType;
	
	public double getFluidLevel(){
		return fluid;
	}
	
	public String getFluidType(){
		return fluidType;
	}
	
	public double getMaxFluid(){
		return maxFluid;
	}
	
	public void setFluidType(String fluid){
		fluidType = fluid;
		markForClean();
	}
	
	public boolean addFluid(double amount){
		
		if((fluid + amount) > maxFluid){
			return false;
		}
		else{
			fluid += amount;
			markForClean();
			return true;
		}
	}
	
	public double addFluidForce(double amount){
		if(fluid >= maxFluid){
			return amount;
		}
		else{
			markForClean();
			if((fluid + amount) >= maxFluid){
				double amountLeft = (fluid + amount) - maxFluid;
				fluid = maxFluid;
				return amountLeft;
			}
			else{
				fluid += amount;
				return 0;
			}
		}
	}

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("fluidType")){
        	fluidType = compound.getString("fluidType");
        } else {
            fluidType = null;
        }
        if(compound.hasKey("fluid")){
        	fluid = compound.getDouble("fluid");
        }
        else{
        	fluid = 0D;
        	fluidType = null;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        if(fluid>0){
        	compound.setString("fluidType", fluidType);
        	compound.setDouble("fluid", fluid);
        }
        return compound;
    }
}
