package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityDeconstructionTable extends ModTileEntityBase implements ITickable{

	private ItemStack stack = ItemStack.EMPTY;
	private int workTime = 0;
	
	private ItemStack lens = ItemStack.EMPTY;
	
	@Override
	public void update(){
		if(!getWorld().isRemote){
			boolean isDirty = false;
			if(stack!=ItemStack.EMPTY){
				RecipeDeconstructionTable recipe = RecipeDeconstructionTable.getRecipe(stack, lens);
				if(recipe != null){
					if(workTime<recipe.getDeconstrutionTime()){
						workTime++;
						isDirty=true;
					}
					else{
						setStack(recipe.getOutputs().copy());
						workTime=0;
						isDirty=true;
					}
				}
			}
			if(isDirty){
				markForClean();
			}
		}
	}
	
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void setStack(ItemStack stack) {
		this.stack = stack;
		markForClean();
	}
	
	public int getWorkTime(){
		return workTime;
	}

	
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("item")) {
            stack = new ItemStack(compound.getCompoundTag("item"));
        } else {
            stack = ItemStack.EMPTY;
        }

        workTime = compound.getInteger("workTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!stack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            stack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
        }	
        compound.setInteger("workTime", workTime);
        return compound;
    }
}
