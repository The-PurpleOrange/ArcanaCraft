package com.tyhone.arcanacraft.common.blocks.container;

import com.tyhone.arcanacraft.common.tileentity.TileEntityHeavyChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHeavyChest extends Container{

	private final IInventory CHEST_INV;
	private final int NUM_ROWS;
	private final TileEntityHeavyChest te;
	
	public ContainerHeavyChest(InventoryPlayer playerInv, TileEntityHeavyChest chestInv, EntityPlayer player) {
		this.te = chestInv;
		this.CHEST_INV = chestInv;
		this.NUM_ROWS = chestInv.getSizeInventory() / 9;
		chestInv.openInventory(player);
		final int ROW_SPACE = (this.NUM_ROWS - 4) * 18;
		
		//Chest
		for(int y = 0; y < this.NUM_ROWS; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(chestInv, x + (y * 9), 8 + (x * 18), 18 + (y * 18)));
			}
		}
		
		//Player Inventory
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(playerInv, x + (y * 9) + 9, 8 + (x * 18), 105 + (y * 18) + ROW_SPACE));
			}
		}
		
		//Player Hotbar
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(playerInv, x, 9 + (x * 18), 161 + ROW_SPACE));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.CHEST_INV.isUsableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			itemStack = stack1.copy();
			
			if(index < this.NUM_ROWS * 9) {
				if(!this.mergeItemStack(stack1, this.NUM_ROWS * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			else if(!this.mergeItemStack(stack1, 0, this.NUM_ROWS * 9, false)){
				return ItemStack.EMPTY;				
			}
			
			if(stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
		}
		
		return itemStack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.CHEST_INV.closeInventory(player);
	}
	
	public TileEntityHeavyChest getChestInventory() {
		return te;
	}

}
