package com.tyhone.arcanacraft.common.tileentity;

import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntitySingleInventoryBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityDeconstructionTable extends ModTileEntitySingleInventoryBase implements ITickable{


	//private ItemStack stack = ItemStack.EMPTY;
	private int workTime = 0;
	private int maxWorkTime = 0;
	private RecipeDeconstructionTable recipe;
	
	@Override
	public void update(){
		if(!getWorld().isRemote){
			boolean isDirty = false;
			if(!this.getStack().isEmpty()){
				getRecipe();
				if(this.recipe != null){
					this.maxWorkTime = this.recipe.getDeconstrutionTime();
					if(this.workTime<this.maxWorkTime){
						this.workTime++;
						isDirty=true;
					}
					else{
						this.setStack(this.recipe.getOutput().copy());
						this.workTime = 0;
						this.maxWorkTime = 0;
						this.recipe = null;
						isDirty=true;
					}
				}
				else{
					this.workTime = 0;
					this.maxWorkTime = 0;
					
				}
			}
			if(isDirty){
				markForClean();
			}
		}
		else if (world.isRemote){
			if(this.workTime > 0){
				double speed = 0;
				if(this.workTime < 60){
					speed = (double) workTime; //Math.floor(((double) this.workTime / 4D) * 3D);
				}
				else{
					speed = 60D;
				}
				SetRotation( GetRotation() + ((int) Math.floor(speed / 2D)));
				
				if(this.workTime>=this.maxWorkTime-2){
					for(int q = 0; q < 8; q++){
						BlockDeconstructionTable.spawnEffectParticle(world, pos, new Random());
					}
				}
			}
		}
	}
	
	private void getRecipe() {
		if(recipe==null){
			this.recipe = RecipeDeconstructionTable.getRecipe(this.getStack(), getLens());
			markForClean();
		}
		else{
			if(!(ItemStackUtil.simpleAreStackSizeEqual(recipe.getInput(), this.getStack()) && ItemStackUtil.simpleAreStackSizeEqual(recipe.getLens(), getLens()))){ 
				this.recipe = RecipeDeconstructionTable.getRecipe(this.getStack(), getLens());
				markForClean();
			}
		}
	}

	public ItemStack getLens(){
		if(world.getTileEntity(pos.add(0, 1, 0)) instanceof TileEntityLensReceptacle){
			TileEntityLensReceptacle receptacle = (TileEntityLensReceptacle) world.getTileEntity(pos.add(0, 1, 0));
			return receptacle.getStack();
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public void setStack(ItemStack stack) {
		this.workTime = 0;
		this.maxWorkTime = 0;
		this.recipe = null;
		super.setStack(stack);
	}
	
	public int getWorkTime(){
		return this.workTime;
	}
	
	public int getRecipeMaxTime(){
		return this.maxWorkTime;
	}

	public boolean isWorking(){
		return this.recipe != null ? true : false;
	}
	
	public float getPercentage(){
		return ((float) this.workTime / (float) this.maxWorkTime);
	}
	
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    	this.setStack(new ItemStack(compound.getCompoundTag("item")));

        this.workTime = compound.getInteger("workTime");
        this.maxWorkTime = compound.getInteger("maxWorkTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagCompound tagCompound = new NBTTagCompound();
        this.getStack().writeToNBT(tagCompound);
        compound.setTag("item", tagCompound);
        compound.setInteger("workTime", workTime);
        compound.setInteger("maxWorkTime", maxWorkTime);
        return compound;
    }
}
