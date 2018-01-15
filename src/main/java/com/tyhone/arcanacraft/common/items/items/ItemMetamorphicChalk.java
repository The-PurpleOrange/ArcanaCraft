package com.tyhone.arcanacraft.common.items.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;
import com.tyhone.arcanacraft.api.ritual.IRitualBuilder;
import com.tyhone.arcanacraft.api.ritual.IRitualCircle;
import com.tyhone.arcanacraft.api.ritual.IRitualDisplayIgnoreBlock;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualType;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockChalk;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.PlayerUtils;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMetamorphicChalk extends ModItemBase implements IRitualBuilder{

	private final String NBT_RITUAL_TYPE = "ritual_type";
	private final String NBT_RITUAL_TYPE_DISPLAY_NAME = "ritual_type_display_name";
	
	
	//TODO add support for shift-right clicking for different ritual types
	
	public ItemMetamorphicChalk() {
		super("chalk_metamorphic");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		
		
		if(player.isSneaking()){
			String msg = null;


			List<RitualType> ritualTypes = RitualRegistry.getRitualTypeList();
			
			ItemStack stack = player.getHeldItem(hand);
			NBTTagCompound tag = new NBTTagCompound();
			
			if(stack.hasTagCompound()){
				tag = stack.getTagCompound();
			}
			
			if(ritualTypes.size() > 1 && tag.hasKey(NBT_RITUAL_TYPE)){
				for(int i = 0; i < ritualTypes.size(); i++){
					if((tag.getString(NBT_RITUAL_TYPE)).equals(ritualTypes.get(i).getUnlocalizedName())){
						if(i == ritualTypes.size()-1){
							setRitualTypeNBT(tag, ritualTypes.get(0));
		            		msg = (ritualTypes.get(0).getDisplayName());
						}else{
							setRitualTypeNBT(tag, ritualTypes.get(i+1));
		            		msg = (ritualTypes.get(i+1).getDisplayName());
						}
						break;
					}
				}
			}else{
				setRitualTypeNBT(tag, ritualTypes.get(0));
        		msg = (ritualTypes.get(0).getDisplayName());
			}
			PlayerUtils.sendPlayerMessage(player, world, msg);
	
			stack.setTagCompound(tag);
		}
		else{
			String msg = null;
			ItemStack stack = player.getHeldItem(hand);
			NBTTagCompound tag = new NBTTagCompound();
			
			if(stack.hasTagCompound()){
				tag = stack.getTagCompound();
			}
			
			if(tag.hasKey(NBT_RITUAL_TYPE)){
				RitualType ritualType = getRitualTypeNBT(stack);
				if(ritualType != null && tag.hasKey(ritualType.getUnlocalizedName())){
					List<Ritual> rituals = RitualRegistry.getRitualListFromString(ritualType.getUnlocalizedName());
					
					if(rituals.size() > 1){
						for(int i = 0; i < rituals.size(); i++){
							if(tag.getString(ritualType.getUnlocalizedName()).equals(rituals.get(i).getUnlocalizedName())){
								if(i == rituals.size()-1){
									setAllNBT(tag, rituals.get(0), ritualType);
				            		msg = (rituals.get(0).getDisplayName());
								}else{
									setAllNBT(tag, rituals.get(i+1), ritualType);
				            		msg = (rituals.get(i+1).getDisplayName());
								}
								break;
							}
						}
					}
					else{
						Ritual ritual = RitualRegistry.getRitualListFromHashMap(ritualType).get(0);
						setAllNBT(tag, ritual, ritualType);
		        		msg = (ritual.getDisplayName());
					}
					
				}
				else{
					Ritual ritual = RitualRegistry.getRitualListFromHashMap(ritualType).get(0);
					setAllNBT(tag, ritual, ritualType);
	        		msg = (ritual.getDisplayName());
				}
				
				PlayerUtils.sendPlayerMessage(player, world, msg);
		
				stack.setTagCompound(tag);
			}
			else{
				RitualType ritualType = ModRituals.RITUAL_TYPE_STANDARD;
				Ritual ritual = RitualRegistry.getRitualListFromHashMap(ritualType).get(0);
				setAllNBT(tag, ritual, ritualType);
        		msg = (ritualType.getDisplayName());
				stack.setTagCompound(tag);
    			PlayerUtils.sendPlayerMessage(player, world, msg);
			}
		}
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
	
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(player.isSneaking() && player.isCreative()){
			ItemStack stack = player.getHeldItem(hand);
			RecipeRitualCircle recipe = getRitualRecipeNBT(stack);
			if(recipe!=null){
				if(checkArea(recipe, player, worldIn, pos, hand)){
					if(drawCircle(recipe, player, worldIn, pos, hand, false)){
						return EnumActionResult.SUCCESS;
					}
				}
			}
			else{
				PlayerUtils.sendPlayerMessage(player, worldIn, "Null Ritual Recipe");
			}
		}
		else{
			ItemStack stack = player.getHeldItem(hand);
			RecipeRitualCircle recipe = getRitualRecipeNBT(stack);
			if(recipe!=null){
				if(checkArea(recipe, player, worldIn, pos, hand)){
					if(drawCircle(recipe, player, worldIn, pos, hand, true)){
						return EnumActionResult.SUCCESS;
					}
				}
			}
			else{
				PlayerUtils.sendPlayerMessage(player, worldIn, "Null Ritual Recipe");
			}
		}

		return EnumActionResult.SUCCESS;
    }
	
	
	
	public boolean drawCircle(RecipeRitualCircle recipe, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, boolean individual)
    {
		String msg = null;
		boolean buildComplete;

		RitualType ritualType = recipe.getRitual().getRitualType();
		
		for(int place : ritualType.getRitualRecipePlaceOrder()){
			BlockPos oldPos = PosUtil.combinePos(pos, ritualType.getRitualRecipePosList().get(place));
			final BlockPos placePos = (worldIn.getBlockState(pos).getBlock() instanceof IRitualCircle) ? oldPos : PosUtil.combinePos(oldPos, new BlockPos(0, 1, 0));
			
			ItemStack itemStack = recipe.getBlockRequirements().get(place);
			if(!itemStack.isEmpty() && worldIn.getBlockState(placePos).getBlock().isReplaceable(worldIn, placePos)){
				
		    	Block block = Block.getBlockFromItem(itemStack.getItem());
			    if(block.canPlaceBlockAt(worldIn, placePos)){
					
			    	if(block == ModBlocks.CHALK_BLOCK || player.isCreative()){
			    		worldIn.setBlockState(placePos, block.getStateFromMeta(itemStack.getMetadata()));
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
			PlayerUtils.sendPlayerMessage(player, worldIn, "Completed " + recipe.getRitual().getDisplayName());
			
			if(recipe.getItemRequirements().size()>0){
				List<String> itemReqsMsg = new ArrayList<>();
				itemReqsMsg.add("Item requirements:");
				
			    List<ItemStack> itemStacks = recipe.getItemStackRequirements();
				for(ItemStack req : itemStacks){
					itemReqsMsg.add(" - x" + ((ItemStack)req).getCount() + " " + ((ItemStack)req).getDisplayName());
				}
				
				for(String line : itemReqsMsg){
					PlayerUtils.sendPlayerMessage(player, worldIn, line);
				}
			}
		}else{
			PlayerUtils.sendPlayerMessage(player, worldIn, msg);
		}
		return true;
    }
	
	private boolean checkArea(RecipeRitualCircle recipe, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand)
	{
		if(recipe==null){
			return false;
		}
		
		RitualType ritualType = recipe.getRitual().getRitualType();
		
		for(int place : ritualType.getRitualRecipePlaceOrder()){
			BlockPos oldPos = PosUtil.combinePos(pos, ritualType.getRitualRecipePosList().get(place));
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
	
	private RecipeRitualCircle getRitualRecipeNBT(ItemStack stack){
		
		RitualType ritualType = getRitualTypeNBT(stack);
		Ritual ritual = getRitualNBT(stack, ritualType);
		
		if(ritual != null && ritualType != null){
			for(RecipeRitualCircle ritualRecipe : ritualType.getRitualRecipeList()){
				if(ritual.getUnlocalizedName().equals(ritualRecipe.getRitual().getUnlocalizedName())){
					return ritualRecipe;
				}
			}
		}
		
		return null;
	}
	
	private void setAllNBT(NBTTagCompound tag, Ritual ritual, RitualType ritualType){
		setRitualTypeNBT(tag, ritualType);
		setRitualNBT(tag, ritual, ritualType);
	}
	
	private void setRitualNBT(NBTTagCompound tag, Ritual ritual, RitualType ritualType){
		tag.setString(ritualType.getUnlocalizedName(), ritual.getUnlocalizedName());
		tag.setString(ritualType.getUnlocalizedName() + "_display_name", ritual.getDisplayName());
	}
	
	private void setRitualTypeNBT(NBTTagCompound tag, RitualType ritualType){
		tag.setString(NBT_RITUAL_TYPE, ritualType.getUnlocalizedName());
		tag.setString(NBT_RITUAL_TYPE_DISPLAY_NAME, ritualType.getDisplayName());
	}
	
	private Ritual getRitualNBT(ItemStack stack, RitualType ritualType){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(ritualType.getUnlocalizedName())){
			String ritualName = stack.getTagCompound().getString(ritualType.getUnlocalizedName());
			if(ritualName != null){
				return RitualRegistry.getRitualFromString(ritualName, ritualType);
			}
		}
		return null;
	}
	
	private RitualType getRitualTypeNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL_TYPE)){
			String ritualTypeName = stack.getTagCompound().getString(NBT_RITUAL_TYPE);
			if(ritualTypeName != null){
				return RitualRegistry.getRitualTypeFromString(ritualTypeName);
			}
		}
		return null;
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){

    	RecipeRitualCircle recipe = getRitualRecipeNBT(stack);
    	if(recipe != null){
    		tooltip.add(recipe.getRitual().getDisplayName() + "   (Shift for Items)");
    		tooltip.add(" - " + recipe.getRitual().getRitualType().getDisplayName());
    		
    		if(!GuiScreen.isShiftKeyDown()){
    			tooltip.add("Required Blocks");

        		List<ItemStack> blockStack = ItemStackUtil.compactItems(recipe.getBlockRequirements());
        		for(ItemStack block : blockStack){
        			if(!(Block.getBlockFromItem(block.getItem()) instanceof IRitualDisplayIgnoreBlock || block.isEmpty())){
        	    		tooltip.add(" - " + block.getCount() + "x " + block.getDisplayName());
        			}
        		}
    		}else{
    			tooltip.add("Required Items");
    			
	    		List<ItemStack> itemStacks = recipe.getItemStackRequirements();
	    		for(ItemStack itemStack : itemStacks){
	    	    	tooltip.add(" - " + itemStack.getCount() + "x " + itemStack.getDisplayName());
	    		}
    		}
    	}
    }
}
