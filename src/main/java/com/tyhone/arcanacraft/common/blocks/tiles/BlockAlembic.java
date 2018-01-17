package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.IEssenceVessel;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.items.ItemEmptyTinkture;
import com.tyhone.arcanacraft.common.items.items.ItemTinkture;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlembic extends ModBlockTileEntityBase{

	//protected static final AxisAlignedBB ALEMBIC_AABB = new AxisAlignedBB(0.1250F, 0.0F, 0.1250F, 0.875F, 0.1250F, 0.875F);
	
	public BlockAlembic() {
		super("alembic");
	}
	
	/*@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return ALEMBIC_AABB;
    }*/

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAlembic();
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
        
		int jar = 0;
		
		if(hitY < 0.33F){ //BASE JAR
			jar = 0;
		}else if(hitY < 0.66F){ //TOP LEFT
			jar = 1;
		}else{ //TOP RIGHT
			jar = 2;
		}
		
		if(!world.isRemote && hand == EnumHand.MAIN_HAND && !player.isSneaking()){
			TileEntityAlembic te = (TileEntityAlembic) world.getTileEntity(pos);
			
			if(te.getStack(jar) != null && te.getStack(jar) instanceof ItemStack){
				ItemStack itemStack = (ItemStack) te.getStack(jar);
				if(!itemStack.isEmpty()){
					if(player.addItemStackToInventory(itemStack)){
						te.cleanStack(jar);
					}
				}
			}
			else if(te.getStack(jar) == null && !player.getHeldItem(hand).isEmpty()){

				if(player.getHeldItem(hand).getItem() instanceof IEssenceVessel){
					IEssenceVessel tinkture = (IEssenceVessel) player.getHeldItem(hand).getItem();
					te.setStack(jar, new TinktureStack(tinkture.getFluidType(player.getHeldItem(hand)), tinkture.getFluidAmount()));
					player.getHeldItem(hand).shrink(1);
				}
				else{
					te.setStack(jar, player.getHeldItem(hand).copy());
					player.getHeldItem(hand).shrink(1);
				}
				/*else if(player.getHeldItem(hand).getItem() instanceof ItemBucket && ((ItemBucket) player.getHeldItem(hand).getItem()).get){
					
				}*/
			}
			else if(te.getStack(jar) instanceof TinktureStack){
				if(player.getHeldItem(hand).getItem() instanceof IEssenceVessel){
					ItemStack essenceVessel = player.getHeldItem(hand);
					IEssenceVessel item = (IEssenceVessel) essenceVessel.getItem();
					if(item.canAcceptsFluid(essenceVessel)){
						int amountLeft = item.addFluidRemainder(essenceVessel, ((TinktureStack) te.getStack(jar)).getAmount());
						((TinktureStack)te.getStack(jar)).setAmount(amountLeft);
					}
				}
				else if(player.getHeldItem(hand).getItem() instanceof ItemEmptyTinkture && ((TinktureStack)te.getStack(jar)).getAmount() >= 8){
					TinktureStack tStack = ((TinktureStack)te.getStack(jar));
					ItemStack newTinkture = new ItemStack(ModItems.TINKTURE, 1);
					NBTTagCompound tag = new NBTTagCompound();
					tag.setString(ItemTinkture.NBT_TINKTURE, tStack.getTinktureType().getRegistryName().toString());
					tag.setString(ItemTinkture.NBT_TINKTURE_DISPLAY_NAME, tStack.getDisplayNameFromStack());
					Arcanacraft.log(tag.toString());
					newTinkture.setTagCompound(tag);
					player.addItemStackToInventory(newTinkture);
					te.cleanStack(jar);
				}
			}
		}

		if(player.isSneaking() && hand == EnumHand.MAIN_HAND){
			if(!(player.getHeldItem(hand).getItem() instanceof IEssenceVessel)){
				TileEntityAlembic te = (TileEntityAlembic) world.getTileEntity(pos);
				String s = world.isRemote ? "Client - " : "Server - ";
				String msgAmount = "";
				String msgItem = "";
				
				if(te.getStack(jar) != null){
					if(te.getStack(jar) instanceof ItemStack){
						msgAmount = String.valueOf(((ItemStack) te.getStack(jar)).getCount()) + "x";
						msgItem = ((ItemStack) te.getStack(jar)).getDisplayName();
					}
					if(te.getStack(jar) instanceof TinktureStack){
						msgAmount = String.valueOf(((TinktureStack) te.getStack(jar)).getAmount()) + "mt";
						msgItem = ((TinktureStack) te.getStack(jar)).getDisplayNameFromStack();
					}
					if(te.getStack(jar) instanceof FluidStack){
						msgAmount = String.valueOf(((FluidStack) te.getStack(jar)).amount) + "mb";
						msgItem = ((FluidStack) te.getStack(jar)).getLocalizedName();
					}
				}
				
    			player.sendMessage(new TextComponentTranslation(jar + ": " + s + msgAmount + " " + msgItem));
			}
		}
        
        return false;
    }
	
}
