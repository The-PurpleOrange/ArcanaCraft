package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;
import com.tyhone.arcanacraft.api.ritual.ArcanacraftRitualManager;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityRitualCircle extends ModTileEntityBase implements ITickable{
	
	private final String NBT_CASTING = "casting";
	private final String NBT_RITUAL = "ritual";
	private final String NBT_RITUAL_TYPE = "ritualtype";
	private final String NBT_COOLDOWN = "cooldown";
	
	private boolean casting = false;
	private RecipeRitualCircle ritualRecipe = null;
	private RitualType ritualType = null;
	private Ritual ritual = null;
	private int cooldown = 0;


	public void onActivated(EntityPlayer player, World worldObj){
		if(casting && player.isSneaking()) {
			reset();
		}
		else {
			List<EntityItem> eItems = worldObj.getEntitiesWithinAABB(EntityItem.class,  this.getRenderBoundingBox().expand(1, 1, 1).expand(-1, -1, -1));
			List<ItemStack> itemStacks = convertEntityItemsToStacks(eItems);
			
			List<ItemStack> blockStacks = scanBlocks(worldObj, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
			RecipeRitualCircle recipe = ArcanacraftRitualCraftingManager.getRecipe(itemStacks, blockStacks, getRecipeList());
			
			preformRecipe(player, worldObj, eItems, recipe);
		}
	}
	
	public int getScanRadius() {
		return 2;
	}
	
	public List<RecipeRitualCircle> getRecipeList(){
		return ArcanacraftRitualCraftingManager.getRitualCircleRecipes();
	}
	
	private List<ItemStack> convertEntityItemsToStacks(List<EntityItem> eItems) {
		List<ItemStack> itemStacks = new ArrayList<>();
		
		if(eItems.size()>0){
			
			List<ItemStack> tempItemStacks = new ArrayList<ItemStack>();
			
			for(EntityItem entityItem : eItems){
				ItemStack itemStack = entityItem.getItem();
				tempItemStacks.add(itemStack.copy());
			}
			itemStacks = ItemStackUtil.compactItems(tempItemStacks);
			
		}
		return itemStacks;
	}
	
	private void preformRecipe(EntityPlayer player, World worldObj, List<EntityItem> eItems, RecipeRitualCircle recipe) {
		if(recipe != null){
			if(recipe.getRitual().meetsSpecialRequirements(worldObj, player, pos)){
				for(EntityItem ei : eItems){
					ei.setDead();
				}
				if(recipe.getRitual().repeatable()) {
					this.casting = true;
					this.ritual = recipe.getRitual();
					this.ritualType = this.ritual.getRitualType();
					this.ritualRecipe = recipe;
					this.cooldown = ritual.getMaxCooldown();
					markForClean();
				}
				else {
					recipe.getRitual().preformRitual(worldObj, player, this.getPos());
				}
			}
		}
	}

	private List<ItemStack> scanBlocks(World worldObj, final int xCoord, final int yCoord, final int zCoord) {
		List<ItemStack> blockStacks = new ArrayList<ItemStack>();
		int sr = getScanRadius();
		for(int x = -sr; x < sr+1; x++){
			for(int z = -sr; z < sr+1; z++){
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
		return blockStacks;
	}

	private void reset() {
		casting = false;
		ritual = null;
		ritualType = null;
		cooldown = 0;
		markForClean();
	}

	@Override
	public void update() {
		if(!this.getWorld().isRemote) {
			if(casting == true && ritual != null) {

				List<ItemStack> blockStacks = scanBlocks(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
				if(this.ritualRecipe.matchesBlocks(blockStacks)) {
					
					if(ritual.getMaxCooldown()>0) {
						if(cooldown>0) {
							cooldown--;
						}
						else {
							cooldown = ritual.getMaxCooldown();
							ritual.preformRepeatingRitual(this.getWorld(), this.getPos());
						}
						markForClean();
					}
				}
				else {
					reset();
				}
			}
		}
	}
	
	public boolean isCasting() {
		return casting;
	}
	
	public Ritual getRitual() {
		return ritual;
	}
	
	public RitualType getRitualType() {
		return ritualType;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        if (compound.hasKey(NBT_CASTING)) {
        	this.casting = compound.getBoolean(NBT_CASTING);
        } else {
        	this.casting = false;;
        }
        
        if (compound.hasKey(NBT_COOLDOWN)) {
        	this.cooldown = compound.getInteger(NBT_COOLDOWN);
        } else {
        	this.cooldown = 0;
        }
        
        if(compound.hasKey(NBT_RITUAL) && compound.hasKey(NBT_RITUAL_TYPE)) {
        	
        	this.ritualType = ArcanacraftRitualManager.getRitualTypeFromString(compound.getString(NBT_RITUAL_TYPE));
        	this.ritual = ArcanacraftRitualManager.getRitualFromString(compound.getString(NBT_RITUAL), ritualType);
        	
        	this.ritualRecipe = ArcanacraftRitualCraftingManager.getRecipe(ritual, getRecipeList());
        }
        else {
        	this.ritualRecipe = null;
        	this.ritual = null;
        	this.ritualType = null;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean(NBT_CASTING, casting);
        compound.setInteger(NBT_COOLDOWN, cooldown);
        if(ritualType != null) {compound.setString(NBT_RITUAL_TYPE, ritualType.getUnlocalizedName());}
        if(ritual != null) {compound.setString(NBT_RITUAL, ritual.getUnlocalizedName());}
        return compound;
    }
}
