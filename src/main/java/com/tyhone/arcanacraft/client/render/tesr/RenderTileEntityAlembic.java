package com.tyhone.arcanacraft.client.render.tesr;

import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderTileEntityAlembic extends TileEntitySpecialRenderer{
	
	public RenderTileEntityAlembic(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityAlembic alembic = (TileEntityAlembic) te;
		/*ItemStack itemStack = alembic.getStack();
		
		if(itemStack != null && itemStack.getCount()>0){ //Check if there is an item in slot 0

			RenderUtil.renderItem(te, itemStack, x, y+1.35F, z, partialTicks, true);

		}*/
	}

}
