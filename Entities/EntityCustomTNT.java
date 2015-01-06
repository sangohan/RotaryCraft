/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.Entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCustomTNT extends EntityTNTPrimed implements IEntityAdditionalSpawnData {

	private int extraTime;

	public EntityCustomTNT(World world) {
		super(world);
	}

	public EntityCustomTNT(World world, double x, double y, double z, EntityLivingBase e, int fuse)
	{
		super(world, x, y, z, e);
		this.fuse = fuse;
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeInt(fuse);
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		fuse = data.readInt();
	}

}
