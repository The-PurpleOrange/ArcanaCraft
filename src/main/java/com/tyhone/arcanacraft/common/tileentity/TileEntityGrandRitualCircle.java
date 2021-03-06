package com.tyhone.arcanacraft.common.tileentity;

import java.util.List;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileEntityGrandRitualCircle extends TileEntityRitualCircle{
	
	@Override
	public int getScanRadius() {
		return 6;
	}
	
	@Override
	public List<RecipeRitualCircle> getRecipeList(){
		return ArcanacraftRitualCraftingManager.getGrandRitualCircleRecipes();
	}
}

/*public class TileEntityGrandRitualCircle extends ModTileEntityBase implements ITickable{
	public void onActivated(EntityPlayer player, World worldObj){
		List<EntityItem> eItems = worldObj.getEntitiesWithinAABB(EntityItem.class,  this.getRenderBoundingBox().expand(1, 1, 1));
		List<ItemStack> itemStacks = new ArrayList<>();
		boolean noItems = false;
		
		if(eItems.size()>0){
			
			List<ItemStack> tempItemStacks = new ArrayList<ItemStack>();
			
			for(EntityItem entityItem : eItems){
				ItemStack itemStack = entityItem.getItem();
				Item item = itemStack.getItem();
				if(item instanceof ItemBlock){
					item = (ItemBlock) item;
				}
				tempItemStacks.add(itemStack.copy());
			}
			itemStacks = ItemStackUtil.compactItems(tempItemStacks);
			
			for(ItemStack i : itemStacks){
				Arcanacraft.logger.info(i.getDisplayName() + ", " + i.getMetadata() + ", " + i.getCount());
			}
		}else{
			noItems = true;
		}
		
		pos = this.getPos();
		final int xCoord = pos.getX();
		final int yCoord = pos.getY();
		final int zCoord = pos.getZ();
		
		List<ItemStack> blockStacks = new ArrayList<ItemStack>();
		
		int s = 6;
		for(int x = -s; x < s+1; x++){
			for(int z = -s; z < s+1; z++){
				BlockPos checkPos = new BlockPos(xCoord + x, yCoord, zCoord + z);
				Block block = worldObj.getBlockState(checkPos).getBlock();
				if(block == Blocks.AIR){
					blockStacks.add(ItemStack.EMPTY);
				}else{
					ItemStack blockItemStack = new ItemStack(block, 1, block.getMetaFromState(worldObj.getBlockState(checkPos)));
					blockStacks.add(blockItemStack);
				}
			}
		}

		RecipeRitualCircle recipe = ArcanacraftRitualCraftingManager.getRecipe(itemStacks, blockStacks, ArcanacraftRitualCraftingManager.getGrandRitualCircleRecipes());
		if(recipe != null){
			if(recipe.getRitual().meetsSpecialRequirements(worldObj, player, pos)){
				for(EntityItem ei : eItems){
					ei.setDead();
				}
				recipe.getRitual().preformRitual(worldObj, player, this.getPos());
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}*/
