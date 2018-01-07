package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;

import com.tyhone.arcanacraft.api.recipe.RecipeSoulAltar;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntitySingleInventoryBase;
import com.tyhone.arcanacraft.common.util.BlockUtils;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntitySoulAltar extends ModTileEntityBase implements ITickable{
	
	private ItemStack stack = ItemStack.EMPTY;
	
	private final BlockPos PEDESTAL_ALL_SEARCH_POS[][] = {
			{
    			getPos().add(3, 0, 0),
    			getPos().add(1, 0, 3),
    			getPos().add(1, 0, -3),
    			getPos().add(-3, 0, 2),
    			getPos().add(-3, 0, -2)
			},
			{
				getPos().add(-3, 0, 0),
				getPos().add(-1, 0, 3),
				getPos().add(-1, 0, -3),
				getPos().add(3, 0, 2),
				getPos().add(3, 0, -2)
			},
			{
				getPos().add(0, 0, 3),
				getPos().add(3, 0, 1),
				getPos().add(-3, 0, 1),
				getPos().add(2, 0, -3),
				getPos().add(-2, 0, -3)
			},
			{
				getPos().add(0, 0, -3),
				getPos().add(3, 0, -1),
				getPos().add(-3, 0, -1),
				getPos().add(2, 0, 3),
				getPos().add(-2, 0, 3)
			}
		};
	
	@Override
	public void update(){
		if(!getWorld().isRemote){
			boolean isDirty = false;
			if(!stack.isEmpty()){
				
				BlockPos[] pedestals = getPedestals(world);
				if(pedestals != null){
	            	
					ArrayList<ItemStack> pedestalsItemStacks = getPedestalItemStacks(pedestals);
					if(!pedestalsItemStacks.isEmpty()){
		            	
						RecipeSoulAltar recipe = RecipeSoulAltar.getRecipe(stack, pedestalsItemStacks);
						if(recipe != null){
							setStack(recipe.getOutput().copy());
							emptyPedestals(pedestals);
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
				((ModTileEntitySingleInventoryBase) te).setStack(ItemStack.EMPTY);
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
