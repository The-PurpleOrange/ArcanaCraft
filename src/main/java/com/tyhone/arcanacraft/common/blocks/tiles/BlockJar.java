package com.tyhone.arcanacraft.common.blocks.tiles;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.item.IEssenceVessel;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockJar extends ModBlockTileEntityBase{
    
	public BlockJar() {
		super("jar");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityJar();
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		return 0;
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
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        
		if(!world.isRemote){
			if(player.getHeldItem(hand).getItem() instanceof IEssenceVessel){
				TileEntityJar te = (TileEntityJar) world.getTileEntity(pos);
				ItemStack stack = player.getHeldItem(hand);
				IEssenceVessel item = (IEssenceVessel) stack.getItem();
				
				if(te.addTinktureAmount(item.getFluidType(stack), item.getFluidAmount(stack))){
					
					if(stack.getCount() > 1){
						stack.setCount(stack.getCount()-1);
					}
					else{
						stack = ItemStack.EMPTY;
					}
				}
			}
		}

		if(player.isSneaking() && hand == EnumHand.MAIN_HAND){
			if(!(player.getHeldItem(hand).getItem() instanceof IEssenceVessel)){
				TileEntityJar te = (TileEntityJar) world.getTileEntity(pos);
				String s = world.isRemote ? "Client - " : "Server - ";
    			player.sendMessage(new TextComponentTranslation(s + te.getTinktureStack().getTinktureName() + " " + te.getTinktureAmount() + "mt"));
			}
		}
        
        return false;
    }
	
}
