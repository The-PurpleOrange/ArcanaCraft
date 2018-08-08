package com.tyhone.arcanacraft.common.rituals;

import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualGrowth extends Ritual{
	
	int coverage = 17;
	
	public RitualGrowth(String ritualName, RitualType ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		if(!world.isRemote){
			world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
		}
		return true;
	}
	
	@Override
	public boolean preformRepeatingRitual(World world, BlockPos pos) {
		if(!world.isRemote){
			int chance = world.rand.nextInt(4) == 0 ? 2 : 1;
			for(int i = 0; i < chance; i++) {
				boolean grownFlag = false;
				int looped = 0;
				do{
					looped++;
					int x, z;
					do {
						x = world.rand.nextInt(coverage) - (int) Math.floor(coverage/2);
						z = world.rand.nextInt(coverage) - (int) Math.floor(coverage/2);
					} while(x > -4 && x < 4 && z > -4 && z < 4);
					
					for(int y = -2; y < 3; y++) {
						BlockPos growPos = pos.add(x, y, z);
						IBlockState iblockstate = world.getBlockState(growPos);
						
						if(growCrop(world, iblockstate, growPos)) {
							grownFlag = true;
		                    //Arcanacraft.log("Grew at loop: " + looped);
						}
					}
					if(!grownFlag) {
	                    //Arcanacraft.log("Couldnt find spot, loop: " + looped);
					}
				} while(!grownFlag && looped < 4);
			}
		}
		return true;
	}
	
	public boolean growCrop(World world, IBlockState iblockstate, BlockPos pos) {
		if (iblockstate.getBlock() instanceof IGrowable){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if (igrowable.canGrow(world, pos, iblockstate, world.isRemote)){
                if (!world.isRemote){
                    if (igrowable.canUseBonemeal(world, world.rand, pos, iblockstate)){
                        igrowable.grow(world, world.rand, pos, iblockstate);
                        //Arcanacraft.log("Growing: " + pos.toString());
                        return true;
                    }
                }
            }
        }
		return false;
	}

	@Override
	public Boolean repeatable() {
		return true;
	}
	
	@Override
	public int getMaxCooldown() {
		return new Random().nextInt(30);
	}

}
