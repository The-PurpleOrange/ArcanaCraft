package com.tyhone.arcanacraft.common.entity;

import com.tyhone.arcanacraft.common.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderThaumonuclearBall extends Render<EntityThaumonuclearBall>{

	protected final Item item;
	private final RenderItem itemRenderer;
	
	private ResourceLocation texture = new ResourceLocation("arcanacraft:textures/item/thaumonuclear_ball.png");
    public static final Factory FACTORY = new Factory();
	
    public RenderThaumonuclearBall(RenderManager renderManager, Item item, RenderItem itemRenderer) {
    	super(renderManager);
    	this.item = item;
    	this.itemRenderer = itemRenderer;
    }
    
    @Override
    public void doRender(EntityThaumonuclearBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
    	
    	GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        //this.bindTexture(texture);

        this.itemRenderer.renderItem(this.getStackToRender(), ItemCameraTransforms.TransformType.GROUND);
        
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public ItemStack getStackToRender(){
        return new ItemStack(this.item);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityThaumonuclearBall entity) {
		return texture;
	}

	public static class Factory implements IRenderFactory<EntityThaumonuclearBall>{

		@Override
		public Render<? super EntityThaumonuclearBall> createRenderFor(RenderManager manager) {
			//return new RenderThaumonuclearBall(manager, ModItems.THAUMONUCLEAR_BALL, Minecraft.getMinecraft().getRenderItem());
			return new RenderThaumonuclearBall(manager, ModItems.THAUMONUCLEAR_BALL, Minecraft.getMinecraft().getRenderItem());
		}
		
	}
}
