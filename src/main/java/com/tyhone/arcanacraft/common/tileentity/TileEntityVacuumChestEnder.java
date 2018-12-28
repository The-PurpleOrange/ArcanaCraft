package com.tyhone.arcanacraft.common.tileentity;

import java.util.List;
import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tileentity.ITEDimensionsBindable;
import com.tyhone.arcanacraft.client.ParticleWind;
import com.tyhone.arcanacraft.common.blocks.container.ContainerVacuumChestEnder;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockVacuumChestEnder;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityLockableLootBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleCrit;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityVacuumChestEnder extends ModTileEntityLockableLootBase implements ITickable, ITEDimensionsBindable{

	private final String CONTAINER_NAME = Arcanacraft.MODID + ":container.vacuum_chest_ender";
	private final String GUI_NAME = Arcanacraft.MODID + ":vacuum_chest_ender";
	private final int INV_SIZE = 15;
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(INV_SIZE, ItemStack.EMPTY);
	private int numPlayerUsing;
    private String customName;
    private BlockPos[] dim = {pos, pos};

	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerVacuumChestEnder(playerInventory, this, player);
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
	public void setDimensions(BlockPos pos1, BlockPos pos2) {
		int minX = Math.min(pos1.getX(), pos2.getX());
		int minY = Math.min(pos1.getY(), pos2.getY());
		int minZ = Math.min(pos1.getZ(), dim[1].getZ());
		
		int maxX = Math.max(pos1.getX(), pos2.getX());
		int maxY = Math.max(pos1.getY(), pos2.getY());
		int maxZ = Math.max(pos1.getZ(), pos2.getZ());

		this.dim[0] = new BlockPos(minX, minY, minZ);
		this.dim[1] = new BlockPos(maxX, maxY, maxZ);
		
		//this.dim[0] = pos1;
		//this.dim[1] = pos2;
		this.markForClean();
	}
	
	public BlockPos getDimension(int index) {
		if(index < 0 || index > 1) {
			Arcanacraft.logger.error("Incorrect dimension index asked for on", this.getName(), "at blockpos", this.pos);
			return pos;
		}
		return dim[index];
	}
	
	public BlockPos[] getDimensionArray() {
		return dim;
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
		if(!player.isSpectator() && this.getBlockType() instanceof BlockVacuumChestEnder) {
			this.numPlayerUsing--;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayerUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}

	@Override
	public void update() {
		if (dim[0] != pos && dim[1] != pos) {
			
			/*if(world.isRemote && world.getTotalWorldTime() % 5 == 0) {
				for(int x = dim[0].getX(); x < dim[1].getX()+1; x++) {
					for(int y = dim[0].getY(); y < dim[1].getY()+1; y++) {
						for(int z = dim[0].getZ(); z < dim[1].getZ()+1; z++) {
					        world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, x+0.5, y+0.2F, z+0.5, 0, 0.3, 0);
						}
					}
				}
			}*/
			AxisAlignedBB bounding = new AxisAlignedBB(dim[0], dim[1].add(1, 1, 1));
			List<Entity> entities = world.getEntitiesWithinAABB(EntityItem.class, bounding); //getEntitiesInAABBexcluding(player, bounding, null);
			for(Entity entityItem : entities){
				
				if(entityItem instanceof EntityItem && entityItem.ticksExisted > 40 && !((EntityItem) entityItem).cannotPickup()){
					this.world.playSound((EntityPlayer)null, entityItem.getPosition().add(0, 0.5D, 0), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
					EntityItem eItem = (EntityItem) entityItem;
					ItemStack stack = eItem.getItem();
					this.addItemStack(-1, stack);
				}
				
				if(world.isRemote && entityItem instanceof EntityItem && entityItem.ticksExisted > 20){
					ParticleWind wind = new ParticleWind(world, 0.5, 0.2 + ((new Random().nextFloat()) /2), 0.5, 0, 0, 0, 1F, 0x0000ff, 0x0000ff, entityItem, 0.5D, 0.4D, 20);
					Minecraft.getMinecraft().effectRenderer.addEffect(wind);
					ParticleWind wind2 = new ParticleWind(world, 0.5, 0.2 + ((new Random().nextFloat()) / 2), 0.5, 0, 0, 0, 1F, 0xff0000, 0xff0000, entityItem, 0.5D, 0.4D, 20);
					Minecraft.getMinecraft().effectRenderer.addEffect(wind2);
				}
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
		int[] a = {pos.getX(), pos.getY(), pos.getZ()}, b = {pos.getX(), pos.getY(),pos.getZ()};
		if(compound.hasKey("pos1")) a = compound.getIntArray("pos1");
		if(compound.hasKey("pos2")) b = compound.getIntArray("pos2");

		dim[0] = new BlockPos(a[0], a[1], a[2]);
		dim[1] = new BlockPos(b[0], b[1], b[2]);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		ItemStackHelper.saveAllItems(compound, getItems());
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.getName());
		}

		int[] a = {dim[0].getX(),dim[0].getY(),dim[0].getZ()}, b = {dim[1].getX(),dim[1].getY(),dim[1].getZ()};
		compound.setIntArray("pos1", a);
		compound.setIntArray("pos2", b);
		
		return compound;
	}
}
