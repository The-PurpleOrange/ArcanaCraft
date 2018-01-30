package com.tyhone.arcanacraft.common.items.items.tools;

import com.tyhone.arcanacraft.common.blocks.tiles.BlockDisapearingBlock;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDisapearingBlock;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTrinketHoverCharm extends ModItemBase{
	
	private final String NBT_STATE = "active";
	private final String BASE_NAME;
	
	
	public ItemTrinketHoverCharm() {
		super("trinket_hover_charm");
		this.setMaxStackSize(1);
		this.BASE_NAME = "trinket_hover_charm";
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(isActive(stack) && !player.isSneaking()){
				BlockPos pos = entity.getPosition();
				for(int j = -1; j < 2; j++){
					for(int k = -1; k < 2; k++){
						BlockPos newPos = new BlockPos(pos.getX()+j, pos.getY()-1, pos.getZ()+k);
						if(world.isAirBlock(newPos)){
							world.setBlockState(newPos, ModBlocks.DISAPEARING_BLOCK.getDefaultState());
						}
						else if(world.getTileEntity(newPos) instanceof TileEntityDisapearingBlock){
							((TileEntityDisapearingBlock)world.getTileEntity(newPos)).setTimer(BlockDisapearingBlock.getTimer());
						}
					}
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		if(player.isSneaking()){
			setActive(player.getHeldItem(hand), !isActive(player.getHeldItem(hand)));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else{
	        return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
		}
	}

	private boolean isActive(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_STATE)){
			return stack.getTagCompound().getBoolean(NBT_STATE);
		}
		return true;
	}
	
	private void setActive(ItemStack stack, boolean active){
		NBTTagCompound tag;
		if(stack.hasTagCompound()){
			tag = stack.getTagCompound();
		}else{
			tag = new NBTTagCompound();
		}
		tag.setBoolean(NBT_STATE, active);
		stack.setTagCompound(tag);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initModelsAndVariants(){
		ModelResourceLocation active = ResourceLocationHelper.getModelResourceLocation(this.BASE_NAME + "_active");
		ModelResourceLocation inactive = ResourceLocationHelper.getModelResourceLocation(this.BASE_NAME + "_inactive");
		ModelBakery.registerItemVariants(this, active, inactive);
		

		ModelLoader.setCustomMeshDefinition(this, stack ->{
			if(isActive(stack)){
				return active;
			}
			else{
				return inactive;
			}
		});
		
	}
}
