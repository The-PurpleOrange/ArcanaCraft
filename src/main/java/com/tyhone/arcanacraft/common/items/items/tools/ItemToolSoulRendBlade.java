package com.tyhone.arcanacraft.common.items.items.tools;

import com.google.common.collect.Multimap;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.ModToolMaterial;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

public class ItemToolSoulRendBlade extends ModItemBase{

	private final float attackDamage;
	private final Item.ToolMaterial material;
	
	public ItemToolSoulRendBlade() {
		super("tool_soul_rend_blade");
		this.material = ToolMaterial.IRON;
		this.maxStackSize = 1;
		this.setMaxDamage((int) Math.floor(material.getMaxUses()/2));
		this.attackDamage = 1.0F + material.getAttackDamage();
	}
	
	public float getAttackDamage() {
		return this.material.getAttackDamage();
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Block block = state.getBlock();
		
		if(block == Blocks.WEB) {
			return 15.0F;
		}
		
		Material material = state.getMaterial();
		return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		Random rand = new Random();
		if(rand.nextInt(4) == 0) {
			World world = attacker.getEntityWorld();
			EntityItem item = new EntityItem(world, target.posX, target.posY, target.posZ, new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("fragment")));
			world.spawnEntity(item);
		}
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if(state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(2, entityLiving);
		}
		return true;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return blockIn.getBlock() == Blocks.WEB;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public int getItemEnchantability() {
		return this.material.getEnchantability();
	}
	
	public String getToolMaterialName(){
		return this.material.toString();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.material.getRepairItemStack();
		if(!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
		return super.getIsRepairable(toRepair, repair);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {

		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
		
		if(equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}
		
		return multimap;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
