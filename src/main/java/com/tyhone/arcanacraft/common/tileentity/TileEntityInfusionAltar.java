package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeInfusionAltar;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.tinkture.TinktureType;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntitySingleInventoryBase;
import com.tyhone.arcanacraft.common.util.BlockUtils;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityInfusionAltar extends ModTileEntityBase implements ITickable{
	
	private ItemStack stack = ItemStack.EMPTY;
	
	private final BlockPos PEDESTAL_ALL_SEARCH_POS[][] = {
			{
    			getPos().add(4, 0, -1),
    			getPos().add(4, 0, 1),
    			getPos().add(-4, 0, -1),
    			getPos().add(-4, 0, 1),
    			getPos().add(-1, 0, 4),
    			getPos().add(1, 0, 4),
    			getPos().add(-1, 0, -4),
    			getPos().add(1, 0, -4)
			}
		};
	
	private final BlockPos JAR_ALL_SEARCH_POS[][] = {
			{
				getPos().add(2, 1, 2),
				getPos().add(2, 1, -2),
				getPos().add(-2, 1, 2),
				getPos().add(-2, 1, -2)
			}
	};
	
	public boolean checkBuild(){
		BlockPos[] pedestals = getPedestals(world);
		BlockPos[] jars = getJars(world);
		if(pedestals != null && jars != null){
			return true;
		}
		return false;
	}
	
	@Override
	public void update(){
		
	}
	
	public void onActivated(){
		if(!getWorld().isRemote){
			
			Arcanacraft.log("Infusion Altar Activated");
			boolean isDirty = false;
			if(!stack.isEmpty()){
				
				BlockPos[] pedestals = getPedestals(world);
				BlockPos[] jars = getJars(world);
				if(pedestals != null && jars != null){
	            	
					ArrayList<ItemStack> pedestalsItemStacks = getPedestalItemStacks(pedestals);
					ArrayList<TinktureStack> jarTinktureStacks = getJarsTinktureStacks(jars);
					if(!pedestalsItemStacks.isEmpty()){
		            	
						RecipeInfusionAltar recipe = RecipeInfusionAltar.getRecipe(stack, pedestalsItemStacks, jarTinktureStacks);
						if(recipe != null){
							setStack(recipe.getOutput().copy());
							emptyPedestals(pedestals);
							for(TinktureStack tStack : recipe.getTInputs()){
								emptyJars(jars, tStack.getTinktureType(), tStack.getAmount());
							}
							isDirty=true;
						}
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
	
	public ArrayList<ItemStack> getPedestalItemStacks(BlockPos[] pedestalPosList){

		ArrayList<ItemStack> pedestalItemStacks = new ArrayList<ItemStack>();
		for(BlockPos pedestalPos : pedestalPosList){
			TileEntity te = world.getTileEntity(PosUtil.combinePos(pos, pedestalPos));
			if(te != null && (te instanceof TileEntityPedestal || te instanceof TileEntityPedestalSlab)){
				if(((ModTileEntitySingleInventoryBase) te).getStack().getItem() != Items.AIR){
					pedestalItemStacks.add(((ModTileEntitySingleInventoryBase) te).getStack());
				}
			}
		}
		
		return pedestalItemStacks;
	}
	
	public void emptyPedestals(BlockPos[] pedestalPosList){
		for(BlockPos pedestalPos : pedestalPosList){
			
			TileEntity te = world.getTileEntity(PosUtil.combinePos(pos, pedestalPos));
			if(te != null && (te instanceof TileEntityPedestal || te instanceof TileEntityPedestalSlab)){
				if(ItemStackUtil.isItemStackBucket(((ModTileEntitySingleInventoryBase) te).getStack())) {
					((ModTileEntitySingleInventoryBase) te).setStack(new ItemStack(Items.BUCKET, 1));
				}else {
					((ModTileEntitySingleInventoryBase) te).setStack(ItemStack.EMPTY);
				}
			}
			
            /*TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(PosUtil.combinePos(pos, pedestalPos));
            if(te != null){
	            te.setStack(ItemStack.EMPTY);
			}*/
		}
	}

	public BlockPos[] getPedestals(World world){    	
		for(BlockPos[] searchPos : PEDESTAL_ALL_SEARCH_POS){
			if(BlockUtils.checkIfPedestal(world, PosUtil.combinePos(pos, searchPos[0]))){
				if(checkforPedestals(searchPos)){
					return searchPos;
				}
				return null;
			}
		}
    	return null;
    }
	
	private boolean checkforPedestals(BlockPos[] searchPos){
		boolean matches = true;
		
		for(int i = 1; i < searchPos.length; i++){
			if(!BlockUtils.checkIfPedestal(world, PosUtil.combinePos(pos, searchPos[i]))){
				matches = false;
				break;
			}
		}
		if(matches){
			return true;
		}
		return false;
	}
	
	public ArrayList<TinktureStack> getJarsTinktureStacks(BlockPos[] jarPosList){

		ArrayList<TinktureStack> jarTinktureType = new ArrayList<TinktureStack>();
		for(BlockPos jarPos : jarPosList){
			TileEntity te = world.getTileEntity(PosUtil.combinePos(pos, jarPos));
			if(te != null && (te instanceof TileEntityJar)){
				jarTinktureType.add(new TinktureStack(((TileEntityJar) te).getTinktureType(), ((TileEntityJar) te).getTinktureAmount()));
			}
		}
		
		return jarTinktureType;
	}
	
	public void emptyJars(BlockPos[] jarPosList, TinktureType type, int amount){
		int remainder = amount;
		
		for(BlockPos jarPos : jarPosList){
			TileEntity tew = world.getTileEntity(PosUtil.combinePos(pos, jarPos));
			if(tew != null && (tew instanceof TileEntityJar)){
				TileEntityJar te = (TileEntityJar) world.getTileEntity(tew.getPos());
				if(te.getTinktureType() != null && te.getTinktureType() == type){
					remainder = te.removeTinktureAmountPartial(remainder);
					if(remainder == 0){
						break;
					}
				}
			}
		}
	}

	public BlockPos[] getJars(World world){    	
		for(BlockPos[] searchPos : JAR_ALL_SEARCH_POS){
			if(world.getBlockState(PosUtil.combinePos(pos, searchPos[0])).getBlock() == ModBlocks.JAR){
				if(checkforJars(searchPos)){
					return searchPos;
				}
				return null;
			}
		}
    	return null;
    }
	
	private boolean checkforJars(BlockPos[] searchPos){
		boolean matches = true;
		
		for(int i = 1; i < searchPos.length; i++){
			if(world.getBlockState(PosUtil.combinePos(pos, searchPos[i])).getBlock() != ModBlocks.JAR){
			//if(!BlockUtils.checkIfPedestal(world, PosUtil.combinePos(pos, searchPos[i]))){
				matches = false;
				break;
			}
		}
		if(matches){
			return true;
		}
		return false;
	}
	
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("item")) {
            stack = new ItemStack(compound.getCompoundTag("item"));
        } else {
            stack = ItemStack.EMPTY;
        }

        //workTime = compound.getInteger("workTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!stack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            stack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
        }	
        //compound.setInteger("workTime", workTime);
        return compound;
    }
}
