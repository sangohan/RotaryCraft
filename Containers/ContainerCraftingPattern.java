/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.Containers;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.Registry.ReikaItemHelper;
import Reika.RotaryCraft.Items.Tools.ItemCraftPattern;
import Reika.RotaryCraft.Registry.ItemRegistry;

public class ContainerCraftingPattern extends Container {

	private static final int width = 3;
	private static final int height = 3;

	private InventoryCrafting craftMatrix = new InventoryCrafting(this, width, height);
	private InventoryCraftResult craftResult = new InventoryCraftResult();
	private World worldObj;

	public ContainerCraftingPattern(EntityPlayer player, World par2World)
	{
		worldObj = par2World;
		int var6;
		int var7;

		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				this.addSlotToContainer(new Slot(craftMatrix, i*width+k, 30+k*18, 17+i*18));
			}
		}

		this.addSlotToContainer(new SlotFurnace(player, craftResult, width*height, 124, 35));

		for (var6 = 0; var6 < 3; ++var6)
			for (var7 = 0; var7 < 9; ++var7)
				this.addSlotToContainer(new Slot(player.inventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
		for (var6 = 0; var6 < 9; ++var6)
			this.addSlotToContainer(new Slot(player.inventory, var6, 8 + var6 * 18, 142));

		ItemStack tool = player.getCurrentEquippedItem();
		ArrayList<ItemStack>[] items = ItemCraftPattern.getItems(tool);
		for (int i = 0; i < 9; i++) {
			craftMatrix.setInventorySlotContents(i, items[i] != null && !items[i].isEmpty() ? items[i].get(0) : null);
		}

		this.onCraftMatrixChanged(craftMatrix);
	}

	@Override
	public void onCraftMatrixChanged(IInventory ii)
	{
		super.onCraftMatrixChanged(ii);

		ItemStack out = CraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj);
		craftResult.setInventorySlotContents(0, out);
	}

	@Override
	public ItemStack slotClick(int slot, int par2, int par3, EntityPlayer ep) {
		boolean inGUI = slot < width*height && slot >= 0;
		if (inGUI) {
			ItemStack held = ep.inventory.getItemStack();
			ItemStack is = held != null ? ReikaItemHelper.getSizedItemStack(held, 1) : null;
			craftMatrix.setInventorySlotContents(slot, is);
			return held;
		}
		else if (slot == 9) {
			return null;
		}
		else {
			return super.slotClick(slot, par2, par3, ep);
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer ep) {
		super.onContainerClosed(ep);

		ItemStack is = ep.getCurrentEquippedItem();
		if (ItemRegistry.CRAFTPATTERN.matchItem(is))
			ItemCraftPattern.setRecipe(is, craftMatrix);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return this.getSlot(0).getStack();
	}

}
