package com.tyhone.arcanacraft.client;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleOrb extends Particle{

	
	private float sr;
	private float sg;
	private float sb;
	
	private float er;
	private float eg;
	private float eb;
	
	private float particleScaleMax;
	private float alpha = 0.5f;
	
	
	private final ResourceLocation orbRL = ResourceLocationHelper.getModelResourceLocation("entity/orb_fx");
	
	public ParticleOrb(World world, double xc, double yc, double zc, double xs, double ys, double zs, float scale, int startColour, int endColour) {
		super(world, xc, yc, zc, xs, ys, zs);
		

		sr = ((startColour & 0xFF0000) >> 16)/255;
	    sg = ((startColour & 0xFF00) >> 8)/255;
	    sb = (startColour & 0xFF)/255;
	    
		er = ((endColour & 0xFF0000) >> 16)/255;
	    eg = ((endColour & 0xFF00) >> 8)/255;
	    eb = (endColour & 0xFF)/255;
		
	    this.particleRed = sr;
        this.particleGreen = sg;
        this.particleBlue = sb;
	    
		particleGravity = Blocks.FIRE.blockParticleGravity;
		particleMaxAge = 40;

		particleScaleMax = scale;
		particleScale = scale;
		
		particleAlpha = alpha;
		
		motionX = xs;
		motionY = ys;
		motionZ = zs;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(orbRL.toString());
		setParticleTexture(sprite);
	}
	
	@Override
	public int getFXLayer(){
		return 1;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		float lf = (float) this.particleAge / (float) this.particleMaxAge;
		
		move(motionX*lf, motionY*lf, motionZ*lf);
		
		if(this.particleAge++ >= this.particleMaxAge){
			this.setExpired();
		}

		float r = (float)Math.abs((lf * er) + ((1 - lf) * sr));
		float g = (float)Math.abs((lf * eg) + ((1 - lf) * sg));
		float b = (float)Math.abs((lf * eb) + ((1 - lf) * sb));

		setRBGColorF(r, g, b);

		float a = ((alpha * lf)*-1) + alpha;
		setAlphaF(a);
		
		
		float ss = 0.2f;
		if(lf>ss){
			float nlf = ((lf-ss) * this.particleScaleMax) / (1f - ss);
			float ns = this.particleScaleMax - nlf;
			this.particleScale = ns;
		}
		
		//this.particleScale = this.particleScaleMax - (lf * this.particleScaleMax);
	}
}
