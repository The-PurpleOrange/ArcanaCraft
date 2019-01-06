package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.ArrayList;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.tyhone.arcanacraft.common.items.ModToolMaterial;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.BlockUtils;
import com.tyhone.arcanacraft.common.util.PickaxeOreValues;
import com.tyhone.arcanacraft.common.util.PlayerUtils;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemToolPickaxeMagic extends ModItemBase{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);

    protected float efficiency;
	private final float attackDamage;
	private final float attackSpeed;
	protected Item.ToolMaterial toolMaterial;

	private final int MAX_MINE_BLOCKS = 17;

	private ArrayList<BlockPos> mineCheckList = new ArrayList<BlockPos>();
	private ArrayList<BlockPos> mineList = new ArrayList<BlockPos>();
	
	public ItemToolPickaxeMagic() {
		super("tool_pickaxe_magic");
		this.toolMaterial = ModToolMaterial.EMERADINE;
		this.efficiency = this.toolMaterial.getEfficiency();
		this.maxStackSize = 1;
		this.setMaxDamage(this.toolMaterial.getMaxUses());
        this.attackDamage = this.toolMaterial.getAttackDamage() + 1F;
		this.attackSpeed = this.toolMaterial.getAttackDamage() - 2.8F;
		toolClass = "pickaxe";
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		PlayerUtils.sendPlayerMessage(player, world, "Harvest Level: " + this.toolMaterial.getHarvestLevel());//  .getHarvestLevel(player.getHeldItem(hand), toolClass, player, world.get));
		PlayerUtils.sendPlayerMessage(player, world, "Tool Class: " + this.getToolClasses(player.getHeldItem(hand)));
		PlayerUtils.sendPlayerMessage(player, world, "can harvest: " + this.canHarvestBlock(world.getBlockState(pos)));
		
		int max = 12;
		int min = 1;
		int r = 3;
				
		int xLow = -r, yLow = -r, zLow = -r;
		int xHigh = r, yHigh = r, zHigh = r;

		
		switch(facing) {
		case DOWN:
			yLow = min;
			yHigh = max;
			break;
		case UP:
			yLow = -max;
			yHigh = -min;
			break;
		case NORTH:
			zLow = min;
			zHigh = max;
			break;
		case SOUTH:
			zLow = -max;
			zHigh = -min;
			break;
		case WEST:
			xLow = min;
			xHigh = max;
			break;
		case EAST:
			xLow = -max;
			xHigh = -min;
			break;
		default:
			break;
		}

		int power = 0;
		
		for(int checkX = xLow; checkX <= xHigh; checkX++) {
			for(int checkY = yLow; checkY <= yHigh; checkY++) {
				for(int checkZ = zLow; checkZ <= zHigh; checkZ++) {
					if(!world.isAirBlock(pos.add(checkX, checkY, checkZ))) {
						int newPower = PickaxeOreValues.compareAgainstOreMap(world.getBlockState(pos.add(checkX, checkY, checkZ)));
						if(newPower > power) {
							power = newPower;
						}
						
						newPower = PickaxeOreValues.compareAgainstBlockMap(world.getBlockState(pos.add(checkX, checkY, checkZ)));
						if(newPower > power) {
							power = newPower;
						}
					}
				}
			}
		}
		
		PlayerUtils.sendPlayerMessage(player, world, "Highest power found: " + PickaxeOreValues.getValueDescription(PickaxeOreValues.getHighestValue(power)));
		return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		
		if(!player.isSneaking()) {
			World world = player.getEntityWorld();
	
			IBlockState state = world.getBlockState(pos);
			
			if(!world.isRemote && canHarvestBlock(state)) {
				buildList(world, pos, player);
				destroyBlocks(world, player, itemstack);
			}
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	private void buildList(World world, BlockPos pos, EntityPlayer player) {
		IBlockState state = world.getBlockState(pos);
		this.mineCheckList.add(pos);
		while(this.mineList.size() < MAX_MINE_BLOCKS && !this.mineCheckList.isEmpty()) {
			addAreaToList(world, state);
			this.mineCheckList.remove(0);
		}
	}
	
	private boolean addAreaToList(World world, IBlockState state) {
		for(int mineX = -1; mineX <= 1; mineX++) {
			for(int mineZ = -1; mineZ <= 1; mineZ++) {
				for(int mineY = -1; mineY <= 1; mineY++) {
					BlockPos minePos = this.mineCheckList.get(0).add(mineX, mineY, mineZ);
					if(!(mineX == 0 && mineY == 0 && mineZ == 0)){
						if(this.mineList.size() < MAX_MINE_BLOCKS) {
							boolean haveFlag = false;
							
							for(BlockPos havePos : this.mineCheckList) {
								if(PosUtil.comparePos(minePos, havePos)) {
									haveFlag = true;
									break; //tagged out
								}
							}
	
							for(BlockPos havePos : this.mineList) {
								if(PosUtil.comparePos(minePos, havePos)) {
									haveFlag = true;
									break;
								}
							}
							
							if(!haveFlag) {
								if(BlockUtils.isRedstoneOre(state, world.getBlockState(minePos)) || BlockUtils.compareBlockStateWithMeta(state, world.getBlockState(minePos))) {//world.getBlockState(minePos).getBlock() == state.getBlock()) {
									this.mineCheckList.add(minePos);
									this.mineList.add(minePos);
								}
							}
						}
						else {
							return false;
						}
					}
				}
			}
		}

		return true;
	}
	
	private void destroyBlocks(World world, EntityPlayer player, ItemStack stack) {
		if(!this.mineList.isEmpty()) {
			for(BlockPos pos : this.mineList) {
				world.destroyBlock(pos, !player.isCreative());
		        stack.damageItem(1, player);
			}
		}
		this.mineCheckList.clear();
		this.mineList.clear();
	}
	
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		if(1==1) {
			return true;
		}
		
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            return this.toolMaterial.getHarvestLevel() >= 3;
        }
        else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
        {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
            {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
                {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
                    {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
                        {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
                            {
                                Material material = blockIn.getMaterial();

                                if (material == Material.ROCK)
                                {
                                    return true;
                                }
                                else if (material == Material.IRON)
                                {
                                    return true;
                                }
                                else
                                {
                                    return material == Material.ANVIL;
                                }
                            }
							return this.toolMaterial.getHarvestLevel() >= 2;
                        }
						return this.toolMaterial.getHarvestLevel() >= 1;
                    }
					return this.toolMaterial.getHarvestLevel() >= 1;
                }
				return this.toolMaterial.getHarvestLevel() >= 2;
            }
			return this.toolMaterial.getHarvestLevel() >= 2;
        }
        else
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
    }

    @Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? getDestroySpeedEffective(stack, state) : this.efficiency;
    }
    
    public float getDestroySpeedEffective(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return efficiency;
        }
        return this.EFFECTIVE_ON.contains(state.getBlock()) ? this.efficiency : 1.0F;
    }
    
    @Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }

        return true;
    }
	
    @Override
	public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
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
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.toolMaterial.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)this.attackSpeed, 0));
        }

        return multimap;
    }
	
	@javax.annotation.Nullable
    private String toolClass;
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
        int level = super.getHarvestLevel(stack, toolClass,  player, blockState);
        if (level == -1 && toolClass.equals(this.toolClass))
        {
            return this.toolMaterial.getHarvestLevel();
        }
		return level;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
    }
	
}
