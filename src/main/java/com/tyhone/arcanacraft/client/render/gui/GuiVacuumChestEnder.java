package com.tyhone.arcanacraft.client.render.gui;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.container.ContainerVacuumChestEnder;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChestEnder;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiVacuumChestEnder extends GuiContainer{

	private static final ResourceLocation GUI_VACUUM_CHEST_ENDER = new ResourceLocation(Arcanacraft.MODID + ":textures/gui/vacuum_chest.png");
	private final InventoryPlayer playerInv;
	private final TileEntityVacuumChestEnder te;
	
	public GuiVacuumChestEnder(InventoryPlayer playerInv, TileEntityVacuumChestEnder te, EntityPlayer player) {
		super(new ContainerVacuumChestEnder(playerInv, te, player));
		this.playerInv = playerInv;
		this.te = te;
		
		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize-92, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(GUI_VACUUM_CHEST_ENDER);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
