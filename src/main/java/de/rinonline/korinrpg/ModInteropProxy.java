package de.rinonline.korinrpg;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import astrotibs.villagenames.village.StructureVillageVN;

public interface ModInteropProxy {

    void load();

    NBTTagCompound getOrMakeVNInfo(World world, int posX, int posY, int posZ);

}

class DummyModInteropProxy implements ModInteropProxy {

    @Override
    public void load() {

    }

    @Override
    public NBTTagCompound getOrMakeVNInfo(World world, int posX, int posY, int posZ) {
        return null;
    }

}

class ActiveModInteropProxy implements ModInteropProxy {

    @Override
    public void load() {}

    @Override
    public NBTTagCompound getOrMakeVNInfo(World world, int posX, int posY, int posZ) {
        return StructureVillageVN.getOrMakeVNInfo(world, posX, posY, posZ);
    }

}
