package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.reference.Reference;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChest;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVacuumChest extends ModBlockTileEntityBase{

	public BlockVacuumChest() {
		super("vacuum_chest");
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
	public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state){
        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVacuumChest();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(world.isRemote) {
			return true;
		}
		
		ILockableContainer ilc = this.getLockableContainer(world, pos);
		if(ilc != null) {
			player.openGui(Arcanacraft.MODID, Reference.GuiID.GUI_VACUUM_CHEST, world, pos.getX(), pos.getY(), pos.getZ());
			//player.addStat(StatList.CHEST_OPENED);
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityVacuumChest te = (TileEntityVacuumChest) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		if(stack.hasDisplayName()) {
			TileEntityVacuumChest te = (TileEntityVacuumChest) world.getTileEntity(pos);
			te.setCustomName(stack.getDisplayName());
		}
	}

	private ILockableContainer getLockableContainer(World world, BlockPos pos) {
		return this.getContainer(world, pos, false);
	}


	private ILockableContainer getContainer(World world, BlockPos pos, boolean b) {

		TileEntityVacuumChest te = (TileEntityVacuumChest) world.getTileEntity(pos);
		if(te == null) {
			return null;
		}
		
		ILockableContainer ilc = te;
		if(!this.isBlocked(world, pos)) {
			return ilc;
		}
		
		return null;
	}
	
	private boolean isBlocked(World world, BlockPos pos) {
		return world.getBlockState(pos.up()).isSideSolid(world, pos.up(), EnumFacing.DOWN);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face){
        return BlockFaceShape.UNDEFINED;
    }
}
