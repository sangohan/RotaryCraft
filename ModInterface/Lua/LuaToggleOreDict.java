/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.ModInterface.Lua;

import net.minecraft.tileentity.TileEntity;
import Reika.DragonAPI.ModInteract.Lua.LuaMethod;
import Reika.RotaryCraft.TileEntities.TileEntityBlower;
import dan200.computercraft.api.lua.LuaException;

public class LuaToggleOreDict extends LuaMethod {

	public LuaToggleOreDict() {
		super("toggleOreDict", TileEntityBlower.class);
	}

	@Override
	public Object[] invoke(TileEntity te, Object[] args) throws LuaException, InterruptedException {
		((TileEntityBlower)te).useOreDict = !((TileEntityBlower)te).useOreDict;
		return null;
	}

	@Override
	public String getDocumentation() {
		return "Toggles item pump ore dictionary matching.";
	}

	@Override
	public String getArgsAsString() {
		return "";
	}

	@Override
	public ReturnType getReturnType() {
		return ReturnType.VOID;
	}

}
