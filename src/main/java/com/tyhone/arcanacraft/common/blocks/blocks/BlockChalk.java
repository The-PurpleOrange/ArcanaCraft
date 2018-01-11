package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.IEnumMeta;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockAlchemicArray;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChalk extends ModBlockEnum {

	public enum EnumChalkType implements IEnumMeta, Comparable<EnumChalkType>{
		CHARCOAL,
		BONE,
		BLOOD,
		LAPIS,
		GOLD,
		MAGICITE;
		
		private int meta;
		protected static final EnumChalkType[] VARIANTS = values();
		
		private EnumChalkType(){
			meta = ordinal();
		}

		@Override
		public String toString(){
			return getName();
		}

		@Override
		public int getMeta() {
			return meta;
		}
		
		public static EnumChalkType getById(int meta) {
		    for(EnumChalkType e : values()) {
		        if(e.meta == meta) return e;
		    }
		    return null;
		}
		
		public static EnumChalkType byMeta(int meta){
			return VARIANTS[Math.abs(meta) % VARIANTS.length];
		}
	}
	
	protected static final AxisAlignedBB CHALK_AABB = new AxisAlignedBB(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.0625F, 0.8125F);
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumChalkType.class);
	
	public BlockChalk() {
		super("chalk_block", Material.CIRCUITS, EnumChalkType.VARIANTS);
		this.setHardness(0f);
        setDefaultState(getDefaultState().withProperty(VARIANT, EnumChalkType.CHARCOAL));
        this.setCreativeTab(null);
	}

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {


			BlockPos[] alchemicArray = {new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1)};
			BlockPos[] ritualCircle = {
					new BlockPos(2, 0, 0), new BlockPos(2, 0, -1), new BlockPos(2, 0, 1),
					new BlockPos(-2, 0, 0), new BlockPos(-2, 0, -1), new BlockPos(-2, 0, 1),
					new BlockPos(-1, 0, 2), new BlockPos(0, 0, 2), new BlockPos(1, 0, 2),
					new BlockPos(-1, 0, -2), new BlockPos(0, 0, -2), new BlockPos(1, 0, -2),};
									
			
			boolean aa = true;
			for(BlockPos posaa : alchemicArray){
				BlockPos posaa2 = PosUtil.combinePos(posaa, pos);
				if(world.getBlockState(posaa2)!=null){
					if(world.getBlockState(posaa2).getBlock() == ModBlocks.CHALK_BLOCK){
						if(getMetaFromState(world.getBlockState(posaa2)) != ItemMetaUtil.chalk("bone")){
							aa = false;
						}
					}
					else{
						aa = false;
					}
				}
				else{
					aa = false;
				}
			}
			
			if(aa){
				
				boolean rc = true;

				for(BlockPos posrc : ritualCircle){
					BlockPos posrc2 = PosUtil.combinePos(posrc, pos);
					if(world.getBlockState(posrc2)!=null){
						if(world.getBlockState(posrc2).getBlock() == ModBlocks.CHALK_BLOCK){
							if(getMetaFromState(world.getBlockState(posrc2)) != ItemMetaUtil.chalk("magicite")){
								rc = false;
							}
						}
						else{
							rc = false;
						}
					}
					else{
						rc = false;
					}
				}
				
				if(rc){
					for(BlockPos posi : ritualCircle){
						BlockPos posk = PosUtil.combinePos(posi, pos);
						world.setBlockState(posk, Blocks.AIR.getDefaultState());
					}
					for(BlockPos posi : alchemicArray){
						BlockPos posk = PosUtil.combinePos(posi, pos);
						world.setBlockState(posk, Blocks.AIR.getDefaultState());
					}
					world.setBlockState(pos, ModBlocks.RITUAL_CIRCLE.getDefaultState());
				}
				else{
					for(BlockPos posi : alchemicArray){
						BlockPos posk = PosUtil.combinePos(posi, pos);
						world.setBlockState(posk, Blocks.AIR.getDefaultState());
					}
					world.setBlockState(pos, ModBlocks.ALCHEMIC_ARRAY.getDefaultState());
				}
			}
			
		}
        return true;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CHALK_AABB;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return null;
    }

    @Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    } 
	
	@Override
	public IBlockState getStateFromMeta(int meta){
    	return this.getDefaultState().withProperty(VARIANT, EnumChalkType.byMeta(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state){

        return ((IEnumMeta) state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int damageDropped(IBlockState blockState) {
        return ((IEnumMeta) blockState.getValue(VARIANT)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> itemStacks)
    {
        for (EnumChalkType chalkVariant : EnumChalkType.VARIANTS) {
            itemStacks.add(new ItemStack(this, 1, chalkVariant.getMeta()));
        }
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP));
    }
    
    @Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        if (!worldIn.isRemote){
            if(!worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP)){
                worldIn.setBlockToAir(pos);
            }
        }
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        int meta = state.getBlock().getMetaFromState(state);

        if (meta == ItemMetaUtil.chalk("magicite") && world.getTotalWorldTime() % 5 == 0)
        {
            double d0 = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d1 = (double)((float)pos.getY() + 0.0625F);
            double d2 = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double f1 = 0.1;
            double f2 = 0.2;
            double f3 = 0.3;
            world.spawnParticle(EnumParticleTypes.SPELL_WITCH, d0, d1, d2, f1, d2, f3);
        }
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

    @Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.CHALK, 1, this.getMetaFromState(state));
    }

    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
}
