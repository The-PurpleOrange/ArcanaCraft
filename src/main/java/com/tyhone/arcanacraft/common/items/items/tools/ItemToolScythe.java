package com.tyhone.arcanacraft.common.items.items.tools;

import com.google.common.collect.Multimap;
import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemToolScythe extends ModItemBase{

	private final float speed;
	protected Item.ToolMaterial toolMaterial;
	private final int radius;
	
	public ItemToolScythe(String material, ToolMaterial toolMaterial, int radius) {
		super("tool_scythe_" + material);
		this.toolMaterial = toolMaterial;
		this.radius = radius;
		this.maxStackSize = 1;
		this.setMaxDamage(this.toolMaterial.getMaxUses()*6);
		this.speed = this.toolMaterial.getAttackDamage() + 1.0F;
	}
	
	public ItemToolScythe(String material, ToolMaterial toolMaterial) {
		this(material, toolMaterial, 2);
		/*super("tool_scythe_" + material);
		this.toolMaterial = toolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(this.toolMaterial.getMaxUses()*6);
		this.speed = this.toolMaterial.getAttackDamage() + 1.0F;*/
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		World world = player.getEntityWorld();
		//ItemStack itemstack = player.getHeldItem(hand);

		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
        if(block instanceof BlockCrops || block instanceof BlockBush){
        	if(player.isSneaking()) {
				state = world.getBlockState(pos);
				block = state.getBlock();
				if(block instanceof BlockCrops || block instanceof BlockBush){
					//Arcanacraft.log("Harvesting"); //TODO remove
					harvest(itemstack, player, EnumFacing.DOWN, world, pos, true);
		        }
        	}else {
				for(int hoeX = -radius; hoeX <= radius; hoeX++) {
					for(int hoeZ = -radius; hoeZ <= radius; hoeZ++) {
						for(int hoeY = -radius; hoeY <= radius; hoeY++) {
							state = world.getBlockState(pos.add(hoeX, hoeY, hoeZ));
							block = state.getBlock();
							if(block instanceof BlockCrops || block instanceof BlockBush){
								//Arcanacraft.log("Harvesting"); //TODO remove
								harvest(itemstack, player, EnumFacing.DOWN, world, pos.add(hoeX, hoeY, hoeZ), true);
					        }
						}
					}
				}
			}
        }
        
        else if(block.isLeaves(state, world, pos)) {
        	if(player.isSneaking()) {
				state = world.getBlockState(pos);
				block = state.getBlock();
				if(world.getBlockState(pos).getMaterial() == Material.LEAVES && player.canPlayerEdit(pos, EnumFacing.DOWN, itemstack)) {
					world.destroyBlock(pos, true);
					itemstack.damageItem(1, player);
				}
        	}else {
        		for(int hoeX = -radius; hoeX <= radius; hoeX++) {
					for(int hoeZ = -radius; hoeZ <= radius; hoeZ++) {
						for(int hoeY = -radius; hoeY <= radius; hoeY++) {
							BlockPos leafPos = pos.add(hoeX, hoeY, hoeZ);
							if(world.getBlockState(leafPos).getMaterial() == Material.LEAVES && player.canPlayerEdit(leafPos, EnumFacing.DOWN, itemstack)) {
								world.destroyBlock(leafPos, true);
								itemstack.damageItem(1, player);
							}
						}
					}
				}
			}
        }
        
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	/*@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return false;
	}*/
	
	/*@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		Arcanacraft.log(world.getBlockState(pos).toString());
		
		if(world.isRemote) {
			world.markBlocksDirtyVertical(pos.getX(), pos.getZ(), pos.getY(), pos.getY());
			world.scheduleUpdate(pos, world.getBlockState(pos).getBlock(), 5);
		}
		return false;
	}*/
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
        if(block instanceof IGrowable){
        	if(player.isSneaking()) {
				harvest(itemstack, player, facing, worldIn, pos, false);
        	}else {
        		for(int hoeX = -radius; hoeX <= radius; hoeX++) {
					for(int hoeZ = -radius; hoeZ <= radius; hoeZ++) {
						for(int hoeY = -radius; hoeY <= radius; hoeY++) {
							if(block instanceof IGrowable) {
								//Arcanacraft.log("Harvesting"); //TODO remove
								harvest(itemstack, player, facing, worldIn, pos.add(hoeX, hoeY, hoeZ), false);
							}
						}
					}
				}
        	}
        }

		return EnumActionResult.PASS;
    }
	
	private boolean harvest(ItemStack stack, EntityPlayer player, EnumFacing facing, World world, BlockPos pos, boolean destroyBlocks) {
		
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if(!player.canPlayerEdit(pos, facing, stack)) {
			//Arcanacraft.log("Its not a IGrowable"); //TODO remove
			return false;
		}
		
		if(destroyBlocks) {
			world.destroyBlock(pos, !player.isCreative());
	        stack.damageItem(1, player);
			return true;
		}

		if(block instanceof IGrowable) {
			IGrowable igrowable = (IGrowable)block;
			
			if(!igrowable.canGrow(world, pos, state, world.isRemote)) {
				//Arcanacraft.log("Can grow"); //TODO remove
				NonNullList<ItemStack> drops = NonNullList.create();;
				block.getDrops(drops, world, pos, state, 0);
				Item blockItem = Item.getItemFromBlock(block);
				boolean removedSeedFlag = false;
				for(ItemStack drop : drops) {
					if(!removedSeedFlag && ItemStackUtil.simpleAreItemStacksEqual(drop, new ItemStack(blockItem, 1, block.getMetaFromState(state)), false)) {
						drop.shrink(1);
						removedSeedFlag = true;
					}
					if(!drop.isEmpty()) {
						if(!world.isRemote) {
							EntityItem eDrop = new EntityItem(world, pos.getX()+0.5F, pos.getY(), pos.getZ()+0.5F, drop);
							world.spawnEntity(eDrop);
						}
					}
				}
				if(world.isRemote) {
			        world.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
				world.setBlockState(pos, block.getDefaultState(), 3);
		        stack.damageItem(1, player);
			}
			
			else {
				//Arcanacraft.log("Cant grow"); //TODO remove
			}
		}
		
		return true;
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
