package com.tyhone.arcanacraft.common.items.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;
import com.tyhone.arcanacraft.api.ritual.IRitualBuilder;
import com.tyhone.arcanacraft.api.ritual.IRitualCircle;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.tinkture.RitualRegistry;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PlayerUtils;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetamorphicChalk extends ModItemBase implements IRitualBuilder{

	private final String NBT_RITUAL = "ritual";
	private final String NBT_RITUAL_DISPLAY_NAME = "ritual_display_name";
	
	public ItemMetamorphicChalk() {
		super("chalk_metamorphic");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking()){
			String msg = null;
			List<RitualBase> rituals = RitualRegistry.getRitualList();
			ItemStack stack = player.getHeldItem(hand);
	
			NBTTagCompound tag = new NBTTagCompound();
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL)){
				tag = stack.getTagCompound();
				for(int i = 0; i < rituals.size(); i++){
					if((tag.getString(NBT_RITUAL)).equals(rituals.get(i).getUnlocalizedName())){
						if(i == rituals.size()-1){
							setNBT(tag, rituals.get(0));
		            		msg = (rituals.get(0).getUnlocalizedName());
						}else{
							setNBT(tag, rituals.get(i+1));
		            		msg = (rituals.get(i+1).getUnlocalizedName());
						}
						break;
					}
				}
			}else{
				setNBT(tag, rituals.get(0));
        		msg = (rituals.get(0).getUnlocalizedName());
			}
			PlayerUtils.sendPlayerMessage(player, world, msg);
	
			stack.setTagCompound(tag);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(player.isSneaking()){
			return EnumActionResult.SUCCESS;
		}
		else{
			ItemStack stack = player.getHeldItem(hand);
			RecipeRitualCircle recipe = getNBT(stack);
			//Arcanacraft.log("Checking Area");
			if(checkArea(recipe,player, worldIn, pos, hand)){
				//Arcanacraft.log("Building circle");
				if(drawCircle(recipe,player, worldIn, pos, hand, true)){
					return EnumActionResult.SUCCESS;
				}
			}
		}
		
        return EnumActionResult.PASS;
    }
	
	
	
	public boolean drawCircle(RecipeRitualCircle recipe, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, boolean individual)
    {
		String msg = null;
		boolean buildComplete;
		
		for(int place : ArcanacraftRitualCraftingManager.getPlaceOrder()){
			BlockPos oldPos = PosUtil.combinePos(pos, ArcanacraftRitualCraftingManager.getBlockPlaceFromList(place));
			final BlockPos placePos = (worldIn.getBlockState(pos).getBlock() instanceof IRitualCircle) ? oldPos : PosUtil.combinePos(oldPos, new BlockPos(0, 1, 0));
			
			ItemStack itemStack = recipe.getBlockRequirements().get(place);
			if(!itemStack.isEmpty() && worldIn.getBlockState(placePos).getBlock().isReplaceable(worldIn, placePos)){
				
		    	Block block = Block.getBlockFromItem(itemStack.getItem());
			    if(block.canPlaceBlockAt(worldIn, placePos)){
					
			    	if(block == ModBlocks.CHALK_BLOCK || player.isCreative()){
			    		worldIn.setBlockState(placePos, block.getStateFromMeta(itemStack.getMetadata()));
			    		//worldIn.setBlockState(placePos, Blocks.DIRT.getDefaultState());
			    		if(individual){
			    			return true;
			    		}
			    	}
			    	else if(PlayerUtils.consumePlayerItem(player, itemStack)){
			    		worldIn.setBlockState(placePos, block.getStateFromMeta(itemStack.getMetadata()));
			    		if(individual){
			    			return true;
			    		}
			    	}
			    	else if(!(block.getBlockFromItem(itemStack.getItem()) instanceof IRitualCircle)){
			    		msg = "Missing " + itemStack.getDisplayName();
			    	}
			    	else{
			    		msg = "Missing " + itemStack.getDisplayName();
			    	}
			    }
			}
		}
		if(msg == null){
			PlayerUtils.sendPlayerMessage(player, worldIn, "Build Complete!");
		}
		PlayerUtils.sendPlayerMessage(player, worldIn, msg);
		return true;
    }
	
	private boolean checkArea(RecipeRitualCircle recipe, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand)
	{
		if(recipe==null){
			return false;
		}
		
		for(int place : ArcanacraftRitualCraftingManager.getPlaceOrder()){
			BlockPos oldPos = PosUtil.combinePos(pos, ArcanacraftRitualCraftingManager.getBlockPlaceFromList(place));
			final BlockPos placePos = (worldIn.getBlockState(pos).getBlock() instanceof IRitualCircle) ? oldPos : PosUtil.combinePos(oldPos, new BlockPos(0, 1, 0));

        	if(placePos == null){
        		Arcanacraft.logger.error("Null BlockPos for ritual recipe " + recipe.getRitual().getDisplayName());
        		Arcanacraft.logger.error("Please report this to Tyhone");
        		return false;
        	}
        	
    		if(!worldIn.isSideSolid(placePos.add(0, -1, 0), EnumFacing.UP)){
    			PlayerUtils.sendPlayerMessage(player, worldIn, "I need to fill in the holes.");
    			return false;
    		}
    		
    		ItemStack itemStack = recipe.getBlockRequirements().get(place);
			if(!itemStack.isEmpty()){
	        	if(worldIn.getBlockState(placePos).getBlock() != Blocks.AIR){
	        		if(!worldIn.getBlockState(placePos).getBlock().isReplaceable(worldIn, placePos)){
	        			
				    	ItemStack itemStack1 = recipe.getBlockRequirements().get(place);
				    	Block block = worldIn.getBlockState(placePos).getBlock();
				    	ItemStack itemStack2 = new ItemStack(block, 1, block.getMetaFromState(worldIn.getBlockState(placePos)));
						if(!(ItemStackUtil.simpleAreStacksEqual(itemStack1, itemStack2))){
							PlayerUtils.sendPlayerMessage(player, worldIn, "I need to clean the area.");
							return false;
						}
					}
	        	}
			}
		}
    	return true;
	}
	
	private RecipeRitualCircle getNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL)){
			String ritualName = stack.getTagCompound().getString(NBT_RITUAL);
			return RecipeRitualCircle.getRecipe(ritualName);
		}
		return null;
	}
	
	private void setNBT(NBTTagCompound tag, RitualBase ritual){
		tag.setString(NBT_RITUAL, ritual.getUnlocalizedName());
		tag.setString(NBT_RITUAL_DISPLAY_NAME, ritual.getDisplayName());
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
    	if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL_DISPLAY_NAME)){
    		tooltip.add(stack.getTagCompound().getString(NBT_RITUAL_DISPLAY_NAME));
    		tooltip.add(stack.getTagCompound().toString());
    	}
    }
}
