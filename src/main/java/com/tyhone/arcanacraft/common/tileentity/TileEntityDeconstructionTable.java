package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityDeconstructionTable extends ModTileEntityBase implements ITickable{


	private ItemStack stack = ItemStack.EMPTY;
	private int workTime = 0;
	private int maxWorkTime = 0;
	private RecipeDeconstructionTable recipe;
	int rotation = 0;
	
	@Override
	public void update(){
		if(!getWorld().isRemote){
			boolean isDirty = false;
			if(!this.stack.isEmpty()){
				getRecipe();
				if(this.recipe != null){
					this.maxWorkTime = this.recipe.getDeconstrutionTime();
					if(this.workTime<this.maxWorkTime){
						this.workTime++;
						isDirty=true;
					}
					else{
						setStack(this.recipe.getOutput().copy());
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
		else{
			this.rotation = (this.rotation % 360);
			if(this.workTime > 0){
				
				//Get Fastest
				double fastest = 0;
				if(this.maxWorkTime < 60){
					fastest = Math.floor(((double) this.maxWorkTime / 4D) * 3D);
				}
				else{
					fastest = 45D;
				}
				
				//Get rotation
				if(this.workTime < fastest){
					this.rotation += Math.floor((double)this.workTime / 2D);
				}
				else{
					this.rotation += Math.floor(fastest / 2D);
				}
				
				
			}
			else{
				this.rotation++;
			}
		}
	}
	
	private void getRecipe() {
		if(this.recipe != RecipeDeconstructionTable.getRecipe(this.stack, getLens())){
			this.recipe = RecipeDeconstructionTable.getRecipe(this.stack, getLens());
			markForClean();
		}
	}

	public ItemStack getLens(){
		if(world.getTileEntity(pos.add(0, 1, 0)) instanceof TileEntityLensReceptacle){
			TileEntityLensReceptacle receptacle = (TileEntityLensReceptacle) world.getTileEntity(pos.add(0, 1, 0));
			return receptacle.getStack();
		}
		return ItemStack.EMPTY;
	}
	
	public ItemStack getStack() {
		return this.stack;
	}
	
	public void setStack(ItemStack stack) {
		this.stack = stack;
		this.workTime = 0;
		this.maxWorkTime = 0;
		this.recipe = null;
		markForClean();
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
	
	public int clientGetRotation(){
		return this.rotation;
	}
	
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("item")) {
        	this.stack = new ItemStack(compound.getCompoundTag("item"));
        } else {
        	this.stack = ItemStack.EMPTY;
        }

        this.workTime = compound.getInteger("workTime");
        this.maxWorkTime = compound.getInteger("maxWorkTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!this.stack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            this.stack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
        }
        compound.setInteger("workTime", workTime);
        compound.setInteger("maxWorkTime", maxWorkTime);
        return compound;
    }
}
