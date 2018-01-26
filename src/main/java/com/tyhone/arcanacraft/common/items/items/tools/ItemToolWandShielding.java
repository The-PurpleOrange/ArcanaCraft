package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.client.ParticleWind;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemToolWandShielding extends ModItemBase{

	public ItemToolWandShielding(){
		super("tool_wand_shielding");
		this.setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @Override
			@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count){
		AxisAlignedBB bounding = player.getEntityBoundingBox().grow(2D, 1D, 2D);
		List<Entity> entities = player.getEntityWorld().getEntitiesInAABBexcluding(player, bounding, null);
		for(Entity entity : entities){
			entity.setVelocity(entity.motionX/4, entity.motionY, entity.motionZ/4);
			if(entity instanceof EntityLivingBase){
				double d0 = entity.posX - player.posX;
				double d1 = entity.posZ - player.posZ;
				float s = 1F;
				entity.motionX = d0/10; //(d0 / 0.01F); //(32F / entity.getDistance(player)));
				entity.motionZ = d1/10; //(d1 / 0.01F); //(32F / entity.getDistance(player)));
			}
			else{
				entity.setVelocity(entity.motionX/4, entity.motionY, entity.motionZ/4);
			}
		}
		
		
		if(player.getEntityWorld().isRemote){
			for(int i = 0; i < 3; i++){
				ParticleWind wind = new ParticleWind(player.getEntityWorld(), 0.5, ((new Random().nextFloat()) * 2), 0.5, 0, 0, 0, 1F, 0xffffff, 0xffffff, player);
				Minecraft.getMinecraft().effectRenderer.addEffect(wind);
			}
		}
	}

    @Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    @Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
