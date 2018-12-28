package com.tyhone.arcanacraft.common.handler;

import com.tyhone.arcanacraft.client.render.gui.GuiHeavyChest;
import com.tyhone.arcanacraft.client.render.gui.GuiVacuumChest;
import com.tyhone.arcanacraft.client.render.gui.GuiVacuumChestEnder;
import com.tyhone.arcanacraft.common.blocks.container.ContainerHeavyChest;
import com.tyhone.arcanacraft.common.blocks.container.ContainerVacuumChest;
import com.tyhone.arcanacraft.common.blocks.container.ContainerVacuumChestEnder;
import com.tyhone.arcanacraft.common.reference.Reference;
import com.tyhone.arcanacraft.common.tileentity.TileEntityHeavyChest;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChest;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChestEnder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GuiID.GUI_HEAVY_CHEST) return new ContainerHeavyChest(player.inventory, (TileEntityHeavyChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		if(ID == Reference.GuiID.GUI_VACUUM_CHEST) return new ContainerVacuumChest(player.inventory, (TileEntityVacuumChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		if(ID == Reference.GuiID.GUI_VACUUM_CHEST_ENDER) return new ContainerVacuumChestEnder(player.inventory, (TileEntityVacuumChestEnder)world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GuiID.GUI_HEAVY_CHEST) return new GuiHeavyChest(player.inventory, (TileEntityHeavyChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		if(ID == Reference.GuiID.GUI_VACUUM_CHEST) return new GuiVacuumChest(player.inventory, (TileEntityVacuumChest)world.getTileEntity(new BlockPos(x, y, z)), player);
		if(ID == Reference.GuiID.GUI_VACUUM_CHEST_ENDER) return new GuiVacuumChestEnder(player.inventory, (TileEntityVacuumChestEnder)world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

	public static void registerGUIs() {
		
	}
}
