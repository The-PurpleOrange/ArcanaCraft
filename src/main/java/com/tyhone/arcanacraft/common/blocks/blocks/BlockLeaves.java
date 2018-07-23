package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeaves extends ModBlockBase implements net.minecraftforge.common.IShearable{

	public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    int[] surroundings;
	
	public BlockLeaves() {
		super("leaves", Material.LEAVES);
        this.setTickRandomly(true);
        this.setHardness(0.2f);
        this.setResistance(0f);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	} 
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	public boolean isFancy() {
		return Minecraft.isFancyGraphicsEnabled();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return isFancy() ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if(isFancy()) {
			return true;
		}
		return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : true;
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		
		return Item.getItemFromBlock(ModBlocks.EVOLITE_SAPLING);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(40) == 0 ? 1 : 0;
	}

    @Override public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }
    @Override public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return true; }

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> items = new ArrayList<ItemStack>() {{add(new ItemStack(ModBlocks.LEAVES, 1));}}; 
		return items;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isTopSolid() && rand.nextInt(15) == 1) {
			double x = pos.getX() + rand.nextFloat();
            double y = pos.getY() - 0.05D;
            double z = pos.getZ() + rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 2) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 4) > 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if(!state.getValue(DECAYABLE).booleanValue())
		{
			i |= 2;
		}
		
		if(!state.getValue(CHECK_DECAY).booleanValue())
		{
			i |= 4;
		}
		
		return i;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(new IProperty[] {this.DECAYABLE}).ignore(new IProperty[] {this.CHECK_DECAY}).build());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
	}
	
    @Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	//LEAF DECAY
	
	private void destroy(World worldIn, BlockPos pos) {
		this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
		worldIn.setBlockToAir(pos);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if(worldIn.isAreaLoaded(new BlockPos(x - 2, y - 2, z - 2), new BlockPos(x + 2, y + 2, z + 2))) {
			for(int newX = -1; newX <= 1; newX++) {
				for(int newY = -1; newY <= 1; newY++) {
					for(int newZ = -1; newZ <=1; newZ++) {
						
						BlockPos blockpos = pos.add(newX, newY, newZ);
						IBlockState iblockstate = worldIn.getBlockState(blockpos);
						
						if(iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
							iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!worldIn.isRemote) {
			if(state.getValue(CHECK_DECAY).booleanValue() && state.getValue(DECAYABLE).booleanValue()) {
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				int n16 = 16;
				int n32 = 32;
				int n1024 = 1024;
				
				if(this.surroundings == null) {
					this.surroundings = new int[32768];
				}
				
				if(worldIn.isAreaLoaded(new BlockPos(x-5, y-5, z-5), new BlockPos(x+5, y+5, z+5))) {
					BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
					
					for(int newX = -4; newX <= 4; newX++) {
						for(int newY = -4; newY <= 4; newY++) {
							for(int newZ = -4; newZ <= 4; newZ++) {
								IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(x + newX, y + newY, z + newZ));
								Block block = iblockstate.getBlock();
								
								if(!block.canSustainLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(x + newX,  y + newY, z + newZ))) {
									if(block.isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(x + newX,  y + newY,  z + newZ))) {
										this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16] = -2;
									}
									else {
										this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16] = -1;
									}
								}
								else {
									this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16] = 0;
								}
							}
						}
					}
					
					for(int i3 = 1; i3 <= 4; i3++) {
						for(int newX = -4; newX <= 4; newX++) {
							for(int newY = -4; newY <= 4; newY++) {
								for(int newZ = -4; newZ <= 4; newZ++) {
									if (this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16] == i3 - 1)
                                    {
                                        if (this.surroundings[(newX + n16 - 1) * n1024 + (newY + n16) * n32 + newZ + n16] == -2)
                                        {
                                            this.surroundings[(newX + n16 - 1) * n1024 + (newY + n16) * n32 + newZ + n16] = i3;
                                        }

                                        if (this.surroundings[(newX + n16 + 1) * n1024 + (newY + n16) * n32 + newZ + n16] == -2)
                                        {
                                            this.surroundings[(newX + n16 + 1) * n1024 + (newY + n16) * n32 + newZ + n16] = i3;
                                        }

                                        if (this.surroundings[(newX + n16) * n1024 + (newY + n16 - 1) * n32 + newZ + n16] == -2)
                                        {
                                            this.surroundings[(newX + n16) * n1024 + (newY + n16 - 1) * n32 + newZ + n16] = i3;
                                        }

                                        if (this.surroundings[(newX + n16) * n1024 + (newY + n16 + 1) * n32 + newZ + n16] == -2)
                                        {
                                            this.surroundings[(newX + n16) * n1024 + (newY + n16 + 1) * n32 + newZ + n16] = i3;
                                        }

                                        if (this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + (newZ + n16 - 1)] == -2)
                                        {
                                            this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + (newZ + n16 - 1)] = i3;
                                        }

                                        if (this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16 + 1] == -2)
                                        {
                                            this.surroundings[(newX + n16) * n1024 + (newY + n16) * n32 + newZ + n16 + 1] = i3;
                                        }
                                    }
								}
							}
						}
					}
				}
				int l2 = this.surroundings[16912];

                if (l2 >= 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
                }
                else
                {
                    this.destroy(worldIn, pos);
                }
			}
		}
	}
	
}
