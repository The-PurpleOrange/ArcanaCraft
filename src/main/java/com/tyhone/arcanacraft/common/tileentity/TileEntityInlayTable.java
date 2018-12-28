 package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeInlayTable;
import com.tyhone.arcanacraft.api.recipe.RecipeTransmutationAltar;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityInlayTable extends ModTileEntityBase{


	private final String NBT_STACK_ARRAY = "stack_array";
	private final String NBT_PROGRESS = "progress";
	
	private final ItemStack[] EMPTY_STACK_ARRAY = new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};
	private ItemStack[] stackArray = EMPTY_STACK_ARRAY.clone();
	private float progress = 0;

	public ItemStack getSlot(int i) {
		return this.stackArray[i];
	}
	
	public ItemStack[] getStack() {
		return this.stackArray;
	}
	
	public void cleanSlots() {
		this.stackArray = EMPTY_STACK_ARRAY.clone();
		markForClean();
	}
	
	public void setSlot(int i, ItemStack stack) {
		this.stackArray[i] = stack;
		markForClean();
	}
	
	public boolean addItem(ItemStack stack) {
    	for(int i = 0; i < this.stackArray.length; i++) {
			if(this.stackArray[i].isEmpty()) {
				setSlot(i, stack);
				return true;
			}
		}
		return false;
	}
	
	public ItemStack removeItem() {
    	for(int i = this.stackArray.length-1; i >= 0; i--) {
    		if(!this.stackArray[i].isEmpty()) {
    			ItemStack itemStack = stackArray[i].copy();
    			setSlot(i, ItemStack.EMPTY);
    			return itemStack;
    		}
    	}
    	
		return ItemStack.EMPTY;
	}
	
	public void hammered() {
		if(this.stackArray != this.EMPTY_STACK_ARRAY) {
			RecipeInlayTable recipe = RecipeInlayTable.getRecipe(stackArray);
			if(recipe != null){
				if(this.progress <1F) {
					addProgress();
				}
				else {
					resetProgress();
					cleanSlots();
					setSlot(0, recipe.getOutput().copy());
				}
				markForClean();
			}
		}
	}
	
	public void addProgress() {
		this.progress+=0.1F;
		markForClean();
	}
	
	public void setProgress(float i) {
		if(i > 1){
			this.progress = 1;
		}
		if(i < 0) {
			this.progress = 0;
		}
		this.progress = i;
		markForClean();
	}

	public void resetProgress() {
		this.progress = 0;
		markForClean();
	}

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    	for(int i = 0; i < this.stackArray.length; i++) {
            if (compound.hasKey(NBT_STACK_ARRAY + Integer.toString(i))) {
	        	this.stackArray[i] = new ItemStack(compound.getCompoundTag(this.NBT_STACK_ARRAY + Integer.toString(i)));
            }
            else {
	        	this.stackArray[i] = ItemStack.EMPTY;
            }
        }
    	if(compound.hasKey(NBT_PROGRESS)) {
        	this.progress = compound.getFloat(NBT_PROGRESS);
    	}
    	else {
    		this.progress = 0;
    	}
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        compound.setFloat(NBT_PROGRESS, this.progress);

    	for(int i = 0; i < this.stackArray.length; i++) {
    		if(!this.stackArray[i].isEmpty()) {
	            NBTTagCompound tagCompound = new NBTTagCompound();
	            this.stackArray[i].writeToNBT(tagCompound);
	            compound.setTag(this.NBT_STACK_ARRAY + Integer.toString(i), tagCompound);
    		}
        }
        return compound;
    }
}
