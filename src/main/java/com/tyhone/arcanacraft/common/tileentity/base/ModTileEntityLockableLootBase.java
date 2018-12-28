package com.tyhone.arcanacraft.common.tileentity.base;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class ModTileEntityLockableLootBase extends TileEntityLockableLoot implements IInventory{

	@Override
	public abstract Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn);

	@Override
	public abstract String getGuiID();
	
	public abstract String getContainerName();

	@Override
	protected abstract NonNullList<ItemStack> getItems();

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : getContainerName();
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	@Override
	public void setCustomName(String string){
		this.customName = string;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isEmpty() {
		//for(ItemStack itemStack: this.chestContents) {
		for(ItemStack itemStack: getItems()) {
			if(!itemStack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void clear() {
		getItems().clear();
		//this.chestContents.clear();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		//return this.chestContents.get(index);
		return getItems().get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		//ItemStack itemStack = ItemStackHelper.getAndSplit(this.chestContents, index, count);
		ItemStack itemStack = ItemStackHelper.getAndSplit(getItems(), index, count);
		if(!itemStack.isEmpty()) {
			this.markForClean();
		}
		return itemStack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		//return ItemStackHelper.getAndRemove(this.chestContents, index);
		return ItemStackHelper.getAndRemove(getItems(), index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		//this.chestContents.set(index, stack);
		getItems().set(index, stack);
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		this.markForClean();
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if(this.world.getTileEntity(this.pos) != this) {
			return false;
		}
		
		return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]);
    }
	
	/*@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		ItemStackHelper.loadAllItems(compound, getItems());
		
		if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		ItemStackHelper.saveAllItems(compound, getItems());
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}
		
		return compound;
	}*/
	
	//NBT AND CLEANING
    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }
	
    
    public void markForClean(){
		markDirty();
		if (world != null){
			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 3);
		}
    }
}
