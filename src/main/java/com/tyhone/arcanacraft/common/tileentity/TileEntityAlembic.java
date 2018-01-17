package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import scala.actors.threadpool.Arrays;

public class TileEntityAlembic extends ModTileEntityBase implements ITickable{

	private final String ITEMSTACK = "itemstack";
	private final String TINKTURESTACK = "tinkturestack";
	private final String FLUIDSTACK = "fluidstack";
	
	private final String ITEM = "item_";
	private final String TYPE = "type_";

	private final int MAX_TINKTURE = 8;
	private final int MAX_FLUID = 1000;
	
	private Object[] objectStack = {null, null, null};
	
	@Override
	public void update(){
		if(world.isRemote){
			//Stuff
		}
	}
	
	public Object getStack(int i) {
		if(i<this.objectStack.length && i>=0){
			return this.objectStack[i];
		}
		return null;
	}
	
	public void setStack(int i, Object stack) {
		if(i<this.objectStack.length && i>=0){
			if(stack instanceof ItemStack || stack instanceof TinktureStack || stack instanceof FluidStack){
				this.objectStack[i] = stack;
				markForClean();
			}
		}
	}
	
	public void cleanStack(int i) {
		if(i<this.objectStack.length && i>=0){
			this.objectStack[i] = null;
			markForClean();
		}
	}
	
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        for(int i = 0; i < 3; i++){
        	if (compound.hasKey(TYPE + i)) {
        		if(compound.getString(TYPE).equals(ITEMSTACK)){
        			this.objectStack[i] = new ItemStack(compound.getCompoundTag(ITEM + i));
	            }
		        else if(compound.getString(TYPE).equals(TINKTURESTACK)){
		        	this.objectStack[i] = new TinktureStack(compound.getCompoundTag(ITEM + i));
		        }
		        else if(compound.getString(TYPE).equals(FLUIDSTACK)){
		        	this.objectStack[i] = FluidStack.loadFluidStackFromNBT(compound.getCompoundTag(ITEM + i));
		        }
		        else{
		        	this.objectStack[i] = null;
		        }
        		
            } else {
            	this.objectStack[i] = null;
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        for(int i = 0; i < 3; i++){
	        if (objectStack[i] != null) {
	            NBTTagCompound tagCompound = new NBTTagCompound();
	            String stackType = "";
	            if(objectStack[i] instanceof ItemStack){
	            	ItemStack itemStack = (ItemStack) objectStack[i];
	            	itemStack.writeToNBT(tagCompound);
	            	stackType = ITEMSTACK;
	            }
		        else if(objectStack[i] instanceof TinktureStack){
		        	((TinktureStack) objectStack[i]).writeToNBT(tagCompound);
		        	stackType = TINKTURESTACK;
		        }else if(objectStack[i] instanceof FluidStack){
	            	((FluidStack) objectStack[i]).writeToNBT(tagCompound);
	            	stackType = FLUIDSTACK;
		        }
	            compound.setTag(ITEM + i, tagCompound);
	            compound.setString(TYPE + i, stackType);
	        }
        }
        return compound;
    }
}
