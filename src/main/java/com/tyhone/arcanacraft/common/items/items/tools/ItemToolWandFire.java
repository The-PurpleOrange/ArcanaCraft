package com.tyhone.arcanacraft.common.items.items.tools;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemToolWandFire extends ModItemBase{

	public ItemToolWandFire(){
		super("tool_wand_fire");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count){
		if(!player.getEntityWorld().isRemote && player instanceof EntityPlayer){
			RayTraceResult tracePos = Minecraft.getMinecraft().objectMouseOver;
			Arcanacraft.log(tracePos.toString());
			if(tracePos.typeOfHit == RayTraceResult.Type.ENTITY && tracePos.entityHit instanceof EntityLivingBase){
				Entity entity = tracePos.entityHit;
				entity.attackEntityFrom(DamageSource.IN_FIRE, 1);
				if(!entity.isImmuneToFire()){
					entity.setFire(4);
				}
				//player.getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), 0, 0.2, 0);
			}
		}
		/*else if (player.getEntityWorld().isRemote){
			player.getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, x, y, z, xs, ys, zs);
			//ParticleDamagingFire fire = new ParticleDamagingFire(player.getEntityWorld(), 0.5, 0.2 + ((new Random().nextFloat()) / 2), 0.5, 0, 0, 0, 1F, 0xff0000, 0xff0000, entityItem, 0.5D, 0.4D, 20);
			//Minecraft.getMinecraft().effectRenderer.addEffect(fire);
		}*/
		
		/*AxisAlignedBB bounding = player.getEntityBoundingBox().grow(3D, 1D, 3D);
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
		}*/
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
