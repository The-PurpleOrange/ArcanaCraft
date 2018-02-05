package com.tyhone.arcanacraft.client;

import java.util.Random;

import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleStar extends Particle{

	private int startColour;
	private int endColour;
	
	private float sr;
	private float sg;
	private float sb;
	
	private float er;
	private float eg;
	private float eb;
	
	private float particleScaleMax;
	private float alpha = 0.5f;
	
	private boolean shed;
	
	
	private final ResourceLocation starRL = ResourceLocationHelper.getModelResourceLocation("entity/star_fx");
	
	public ParticleStar(World world, double xc, double yc, double zc, double xs, double ys, double zs, float scale, int startColour, int endColour, boolean shed) {
		super(world, xc, yc, zc, xs, ys, zs);
		

		sr = ((startColour & 0xFF0000) >> 16)/255;
	    sg = ((startColour & 0xFF00) >> 8)/255;
	    sb = (startColour & 0xFF)/255;
	    
		er = ((endColour & 0xFF0000) >> 16)/255;
	    eg = ((endColour & 0xFF00) >> 8)/255;
	    eb = (endColour & 0xFF)/255;
		
	    this.startColour = startColour;
	    this.endColour = endColour;
	    
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
		
		this.shed = shed;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(starRL.toString());
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

		if(onGround){
			this.setExpired();
		}
		
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
		
		if(shed && new Random().nextFloat() < 0.1F){
			ParticleStar star = new ParticleStar(world, posX, posY, posZ, 0, -0.2f, 0, this.particleScale / 2, startColour, endColour, false);
			Minecraft.getMinecraft().effectRenderer.addEffect(star);
		}
	}
}
