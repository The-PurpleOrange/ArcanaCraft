package com.tyhone.arcanacraft.common.items.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.Ritual;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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

public class ItemMetamorphicChalk extends ModItemBase{

	private final String NBT_RITUAL = "ritual";
	
	public ItemMetamorphicChalk() {
		super("chalk_metamorphic");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking()){
			String msg = null;
			List<Ritual> rituals = RitualRegistry.getRitualList();
			ItemStack stack = player.getHeldItem(hand);
	
			NBTTagCompound tag = new NBTTagCompound();
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL)){
				tag = stack.getTagCompound();
				for(int i = 0; i < rituals.size(); i++){
					if(tag.getString(NBT_RITUAL) == rituals.get(i).getDisplayName()){
						if(i == rituals.size()-1){
							tag.setString(NBT_RITUAL, rituals.get(0).getDisplayName());
		            		msg = (rituals.get(0).getDisplayName());
						}else{
							tag.setString(NBT_RITUAL, rituals.get(i+1).getDisplayName());
		            		msg = (rituals.get(i+1).getDisplayName());
						}
						break;
					}
				}
			}else{
				tag.setString(NBT_RITUAL, rituals.get(0).getDisplayName());
        		msg = (rituals.get(0).getDisplayName());
			}
			
			if(world.isRemote && msg != null){
				player.sendMessage(new TextComponentTranslation(msg));
			}
	
			stack.setTagCompound(tag);
		}else{
			if(!world.isRemote){
				Arcanacraft.log("ItemStackDisplayName: " + this.getItemStackDisplayName(player.getHeldItem(hand)));
				Arcanacraft.log("UnlocalizedName:      " + this.getUnlocalizedName(player.getHeldItem(hand)));
				Arcanacraft.log("RegistryName:         " + this.getRegistryName().toString());
			}
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        /*if(!worldIn.isRemote){
        	boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
		    BlockPos blockpos = flag ? pos : pos.offset(facing);
		
		    if (player.canPlayerEdit(blockpos, facing, player.getHeldItem(hand))){

		        Block block = worldIn.getBlockState(blockpos).getBlock();
		        
		        Block chalkType = ModBlocks.CHALK_BLOCK;
		
		        if (chalkType.canPlaceBlockAt(worldIn, blockpos)) //else if (chalkType.canPlaceBlockAt(worldIn, blockpos))
		        {
		        	worldIn.setBlockState(blockpos, chalkType.getStateFromMeta(player.getHeldItem(hand).getMetadata()));
		        	
		            return EnumActionResult.SUCCESS;
		        }
		    }
        }*/
        return EnumActionResult.FAIL;
    }
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
    	if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL)){
    		tooltip.add(stack.getTagCompound().getString(NBT_RITUAL));
    	}
    }
}
