package com.tyhone.arcanacraft.common.tileentity;

import java.util.List;
import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.ParticleWind;
import com.tyhone.arcanacraft.common.blocks.container.ContainerVacuumChest;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockVacuumChest;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityLockableLootBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityVacuumChest extends ModTileEntityLockableLootBase implements ITickable{

	private final String CONTAINER_NAME = Arcanacraft.MODID + ":container.vacuum_chest";
	private final String GUI_NAME = Arcanacraft.MODID + ":vacuum_chest";
	private final int INV_SIZE = 15;
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(INV_SIZE, ItemStack.EMPTY);
	private int numPlayerUsing;
    private String customName;

	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerVacuumChest(playerInventory, this, player);
	}

	@Override
	public String getGuiID() {
		return GUI_NAME;
	}

	@Override
	public String getContainerName() {
		return this.CONTAINER_NAME;
	}
	
	@Override
	protected NonNullList<ItemStack> getItems() {
		return chestContents;
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
		if(!player.isSpectator() && this.getBlockType() instanceof BlockVacuumChest) {
			this.numPlayerUsing--;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayerUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}

	@Override
	public void update() {
		AxisAlignedBB bounding =  new AxisAlignedBB(pos).grow(8D); //player.getEntityBoundingBox().grow(8D);
		List<Entity> entities = world.getEntitiesWithinAABB(EntityItem.class, bounding); //getEntitiesInAABBexcluding(player, bounding, null);
		for(Entity entityItem : entities){
			
			if(entityItem instanceof EntityItem && entityItem.getDistanceSqToCenter(pos) < 0.75D) {
				EntityItem eItem = (EntityItem) entityItem;
				ItemStack stack = eItem.getItem();
				this.addItemStack(-1, stack);
			}
			
			if(entityItem instanceof EntityItem && entityItem.ticksExisted > 40 && !((EntityItem) entityItem).cannotPickup()){
				double dx = entityItem.posX - pos.getX()-0.5D;
				double dy = entityItem.posY - pos.getY();
				double dz = entityItem.posZ - pos.getZ()-0.5D;
				entityItem.motionX = (dx/5)*-1;
				entityItem.motionY = ((dy/5)*-1)+0.1;
				entityItem.motionZ = (dz/5)*-1;
			}
			
			if(world.isRemote && entityItem instanceof EntityItem && entityItem.ticksExisted > 20){
				ParticleWind wind = new ParticleWind(world, 0.5, 0.2 + ((new Random().nextFloat()) /2), 0.5, 0, 0, 0, 1F, 0x0000ff, 0x0000ff, entityItem, 0.5D, 0.4D, 20);
				Minecraft.getMinecraft().effectRenderer.addEffect(wind);
				ParticleWind wind2 = new ParticleWind(world, 0.5, 0.2 + ((new Random().nextFloat()) / 2), 0.5, 0, 0, 0, 1F, 0xff0000, 0xff0000, entityItem, 0.5D, 0.4D, 20);
				Minecraft.getMinecraft().effectRenderer.addEffect(wind2);
			}
		}
	}
	
	private boolean addItemStack(int slot, final ItemStack stack) {
		if(stack.isEmpty()) {
			return false;
		}
		
		try {
			if(stack.isItemDamaged()) {
				if(slot == -1) {
					slot = this.getFirstEmptyStack();
				}
				if(slot >= 0) {
					this.chestContents.set(slot, stack.copy());
					this.chestContents.get(slot).setAnimationsToGo(5);
					stack.setCount(0);
					return true;
				}
				return false;
			}
			int i;
			
			while (true) {
				i = stack.getCount();
				
				if(slot == -1) {
					stack.setCount(this.storePartialItemStack(stack));
				}
				else {
					stack.setCount(this.addResource(slot, stack));
				}
				
				if(stack.isEmpty() || stack.getCount() >= i) {
					break;
				}
			}
		}
		catch(Throwable throwable) {
			
			//Vanilla catch code - Hopper
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
		    CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
		    crashreportcategory.addCrashSection("Item ID", Integer.valueOf(Item.getIdFromItem(stack.getItem())));
		    crashreportcategory.addCrashSection("Item data", Integer.valueOf(stack.getMetadata()));
		    crashreportcategory.addDetail("Item name", new ICrashReportDetail<String>()
		    {
		        @Override
				public String call() throws Exception
		        {
		            return stack.getDisplayName();
		        }
		    });
		    throw new ReportedException(crashreport);
		}
		
		return true;
	}
	
	private int getFirstEmptyStack() {
		for(int i = 0; i < this.INV_SIZE; i++) {
			if(this.chestContents.get(i).isEmpty()) {
				return i;
			}
		}
		
		return -1;
	}
	
	private int storePartialItemStack(ItemStack stack) {
		int i = this.storeItemStack(stack);
		
		if(i == -1) {
			i = this.getFirstEmptyStack();
		}
		
		return i == -1 ? stack.getCount() : this.addResource(i, stack);
	}
	
	public int storeItemStack(ItemStack stack) {
		for(int i = 0; i < this.INV_SIZE; i++) {
			if(this.canMergeStacks(this.chestContents.get(i), stack)) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean canMergeStacks(ItemStack stack1, ItemStack stack2) {
		return !stack1.isEmpty() && ItemStackUtil.simpleAreItemStacksEqual(stack1, stack2, false) && stack1.getCount() < stack1.getMaxStackSize() && stack1.getCount() < this.getInventoryStackLimit();
	}
	
	private int addResource(int slot, ItemStack stack) {
		int count = stack.getCount();
		ItemStack stackInSlot = this.getStackInSlot(slot);
		
		if(stackInSlot.isEmpty()) {
			stackInSlot = stack.copy();
			stackInSlot.setCount(0);
			
			if(stack.hasTagCompound()) {
				stackInSlot.setTagCompound(stack.getTagCompound().copy());
			}
			
			this.setInventorySlotContents(slot, stackInSlot);
		}
		
		int j = count;
		
		if(count > stackInSlot.getMaxStackSize() - stackInSlot.getCount()) {
			j = stackInSlot.getMaxStackSize() - stackInSlot.getCount();
		}
		
		if(j > this.getInventoryStackLimit() - stackInSlot.getCount()) {
			j = this.getInventoryStackLimit() - stackInSlot.getCount();
		}
		
		if(j == 0) {
			return count;
		}
		
		count = count - j;
		stackInSlot.grow(j);
		stackInSlot.setAnimationsToGo(5);
		return count;
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
			compound.setString("CustomName", getName());
		}
		
		return compound;
	}
}
