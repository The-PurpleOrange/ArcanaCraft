package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.container.ContainerHeavyChest;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockHeavyChest;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityLockableLootBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;

public class TileEntityHeavyChest extends ModTileEntityLockableLootBase implements ITickable{

	private final String CONTAINER_NAME = Arcanacraft.MODID + ":container.heavy_chest";
	private final String GUI_NAME = Arcanacraft.MODID + ":heavy_chest";
	private final int INV_SIZE = 45;
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(INV_SIZE, ItemStack.EMPTY);
    public float lidAngle, prevLidAngle;
    private int numPlayerUsing, ticksSinceSync;
    private String customName;

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerHeavyChest(playerInventory, this, player);
	}
	
	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}
	
	@Override
	public String getContainerName() {
		return this.CONTAINER_NAME;
	}
	
	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public String getGuiID() {
		return GUI_NAME;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		if(!player.isSpectator()) {
			if(this.numPlayerUsing < 0) {
				this.numPlayerUsing = 0;
			}
			this.numPlayerUsing++;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayerUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if(!player.isSpectator() && this.getBlockType() instanceof BlockHeavyChest) {
			this.numPlayerUsing--;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayerUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}
	
	@Override
	public void update() {
		if(this.numPlayerUsing > 0 && this.lidAngle == 0.0F) {
			this.world.playSound((EntityPlayer)null, pos.add(0, 0.5D, 0), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
		if(this.numPlayerUsing == 0 && this.lidAngle > 0F || this.numPlayerUsing > 0 && this.lidAngle < 1F) {
			float oldLidAngle = this.lidAngle;
			
			if(this.numPlayerUsing > 0 && this.lidAngle < 1.0F) {
				this.lidAngle += 0.1F;
			}
			else {
				this.lidAngle -= 0.1F;
			}
			
			if(this.lidAngle>1.0F) {
				this.lidAngle = 1.0F;
			}
			
			if(this.lidAngle < 0.5F && oldLidAngle >= 0.5F) {
				this.world.playSound((EntityPlayer)null, pos.add(0, 0.5D, 0), SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
			}
		}
		if(this.lidAngle < 0F) {
			this.lidAngle = 0F;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		ItemStackHelper.loadAllItems(compound, getItems());
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		ItemStackHelper.saveAllItems(compound, getItems());
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.getName());
		}
		
		return compound;
	}
}
