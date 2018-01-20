package com.tyhone.arcanacraft.common.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHomunculus extends RenderLiving<EntityHomunculus>{

    private ResourceLocation mobTexture = new ResourceLocation("arcanacraft:textures/entity/homunculus.png");
    public static final Factory FACTORY = new Factory();
    
	public RenderHomunculus(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHomunculus entity) {
		return mobTexture;
	}
	
	public static class Factory implements IRenderFactory<EntityHomunculus>{

		@Override
		public Render<? super EntityHomunculus> createRenderFor(RenderManager manager) {
			return new RenderHomunculus(manager);
		}
		
	}

}
