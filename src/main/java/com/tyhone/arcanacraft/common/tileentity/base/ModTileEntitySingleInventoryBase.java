 package com.tyhone.arcanacraft.common.tileentity.base;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTileEntitySingleInventoryBase extends ModTileEntityBase implements ITickable{

	private ItemStack itemStack = ItemStack.EMPTY;
	private int rotation = 0;
	
	@Override
	public void update(){
		if(world.isRemote){
			this.rotation = (this.rotation % 360);
			//this.rotation++;
			//Arcanacraft.log("Rotation BASE: " + GetRotation());
		}
	}
	
	public ItemStack getStack() {
		return this.itemStack;
	}
	
	public void setStack(ItemStack stack) {
		this.itemStack = stack;
		markForClean();
	}

    @SideOnly(Side.CLIENT)
	public int GetRotation(){
		return this.rotation;
	}

    @SideOnly(Side.CLIENT)
	public void SetRotation(int i){
		this.rotation = ( i % 360);
	}

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("item")) {
        	this.itemStack = new ItemStack(compound.getCompoundTag("item"));
        } else {
        	this.itemStack = ItemStack.EMPTY;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!this.itemStack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            this.itemStack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
        }
        return compound;
    }
}
