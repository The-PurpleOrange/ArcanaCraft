package com.tyhone.arcanacraft.common.jei.alembic;

import java.lang.reflect.Array;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiIngredient;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;
import scala.actors.threadpool.Arrays;

public class AlembicRecipeCategory implements IRecipeCategory<AlembicRecipeWrapper>{


    public static final String NAME = "arcanacraft:alembic";

    private final IDrawable background;
    
    public AlembicRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_alembic"), 0, 0, 135, 45);
	}
	
	@Override
	public String getUid() {
		return NAME;
	}

	@Override
	public String getTitle() {
		return I18n.translateToLocal("container.nei."+NAME+".name");
	}

	@Override
	public String getModName() {
		return Arcanacraft.MODNAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlembicRecipeWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiIngredientGroup<TinktureStack> guiTinktureStacks = recipeLayout.getIngredientsGroup(TinktureStack.class);
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		List<List<TinktureStack>> tInputs = ingredients.getInputs(TinktureStack.class);
		List<List<FluidStack>> fInputs = ingredients.getInputs(FluidStack.class);

		int[] xPos = {25, 12, 38};
		int[] yPos = {21, 0, 0};
		
		for(int i = 0; i < 3; i++){
			if(tInputs.get(i).get(0) != null){
				guiTinktureStacks.init(i, true, xPos[i]+6, yPos[i]+6);
				guiTinktureStacks.set(i, tInputs.get(i));
			}
			else if(fInputs.get(i).get(0) != null){
				guiFluidStacks.init(i, true, xPos[i]+1, yPos[i]+1);
				guiFluidStacks.set(i, fInputs.get(i));
			}
			else if(inputs.get(i).get(0) != null){
				guiItemStacks.init(i, true, xPos[i], yPos[i]);
				guiItemStacks.set(i, inputs.get(i));
			}
		}
		
		int ox = 98;
		int oy = 11;
		
		Object output = wrapper.recipe.getOutput();
		if(output instanceof ItemStack){
			guiItemStacks.init(4, true, ox, oy);
			guiItemStacks.set(4, (ItemStack) output);
		}
		else if(output instanceof TinktureStack){
			guiTinktureStacks.init(4, true, ox+6, oy+6);
			guiTinktureStacks.set(4, (TinktureStack) output);
		}
		else if(output instanceof FluidStack){
			guiFluidStacks.init(4, true, ox+1, oy+1);
			guiFluidStacks.set(4, (FluidStack) output);
		}
		
	}

}
