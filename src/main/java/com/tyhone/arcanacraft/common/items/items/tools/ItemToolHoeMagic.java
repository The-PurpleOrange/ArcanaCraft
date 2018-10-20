package com.tyhone.arcanacraft.common.items.items.tools;

import com.google.common.collect.Multimap;
import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.ModToolMaterial;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemToolHoeMagic extends ModItemBase{

	private final float speed;
	protected Item.ToolMaterial toolMaterial;
	
	public ItemToolHoeMagic() {
		super("tool_hoe_magic");
		this.toolMaterial = ModToolMaterial.EMERADINE;
		this.maxStackSize = 1;
		this.setMaxDamage(this.toolMaterial.getMaxUses()*6);
		this.speed = this.toolMaterial.getAttackDamage() + 1.0F;
	}
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
        if(block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.DIRT || block == Blocks.FARMLAND){
			if(player.isSneaking()) {
				till(itemstack, player, facing, worldIn, pos);
			}
			else {
				for(int hoeX = -2; hoeX < 3; hoeX++) {
					for(int hoeZ = -2; hoeZ < 3; hoeZ++) {
						till(itemstack, player, facing, worldIn, pos.add(hoeX, 0, hoeZ));
					}
				}
			}
        }

		return EnumActionResult.PASS;
    }
	
	@SuppressWarnings("incomplete-switch")
	private boolean till(ItemStack itemstack, EntityPlayer player, EnumFacing facing, World worldIn, BlockPos pos) {
		Arcanacraft.log("Tilling: " + pos.toString());
		
		if (!player.canPlayerEdit(pos, facing, itemstack))
        {
			Arcanacraft.log("Cant edit");
            return false;
        }
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
		Arcanacraft.log("Hook: " + hook);
		if (hook != 0) return hook > 0 ? true : false;
		
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())){
		    if (block == Blocks.GRASS || block == Blocks.GRASS_PATH){
		        this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
		        return true;
		    }

		    if (block == Blocks.DIRT){
		        switch (state.getValue(BlockDirt.VARIANT)){
		            case DIRT:
		                this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
		                return true;
		            case COARSE_DIRT:
		                this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
		                return true;
		        }
		    }
		}
		return false;
	}
	
	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        worldIn.setBlockState(pos, state, 11); //11
        stack.damageItem(1, player);
    }	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return true;
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}

	public String getMaterialName(){
		return this.toolMaterial.toString();
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)(this.speed - 4.0F), 0));
        }

        return multimap;
    }
	
}
