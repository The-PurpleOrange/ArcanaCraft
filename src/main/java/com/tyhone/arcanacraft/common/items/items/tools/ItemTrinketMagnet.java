package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import it.unimi.dsi.fastutil.Arrays;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTrinketMagnet extends ModItemBase{
	
	private final String NBT_STATE = "active";
	private final String BASE_NAME;
	
	
	public ItemTrinketMagnet() {
		super("trinket_magnet");
		this.setMaxStackSize(1);
		this.BASE_NAME = "trinket_magnet";
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(isActive(stack)){
				AxisAlignedBB bounding = player.getEntityBoundingBox().grow(8D);
				List<Entity> entities = player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, bounding); //getEntitiesInAABBexcluding(player, bounding, null);
				for(Entity entityItem : entities){
					if(entityItem instanceof EntityItem && entityItem.ticksExisted > 40 && !((EntityItem) entityItem).cannotPickup()){
						double dx = entityItem.posX - player.posX;
						double dy = entityItem.posY - player.posY;
						double dz = entityItem.posZ - player.posZ;
						float s = 1F;
						entityItem.motionX = (dx/2)*-1;
						entityItem.motionY = ((dy/2)*-1)+0.1;
						entityItem.motionZ = (dz/2)*-1;
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
