package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySpiritSkull extends ModTileEntityBase{


    private int skullRotation;

    @SideOnly(Side.CLIENT)
    public int getSkullRotation()
    {
        return this.skullRotation;
    }

    public void setSkullRotation(int rotation)
    {
        this.skullRotation = rotation;
    }
	
}
