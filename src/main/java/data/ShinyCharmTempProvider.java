package data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ShinyCharmTempProvider implements ICapabilitySerializable<NBTBase>{

	 @CapabilityInject(IShinyCharmTemp.class)
	 public static final Capability<IShinyCharmTemp> SHINYCHARMTEMP_CAP = null;

	 private IShinyCharmTemp instance = SHINYCHARMTEMP_CAP.getDefaultInstance();

	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == SHINYCHARMTEMP_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == SHINYCHARMTEMP_CAP ? SHINYCHARMTEMP_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return SHINYCHARMTEMP_CAP.getStorage().writeNBT(SHINYCHARMTEMP_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		SHINYCHARMTEMP_CAP.getStorage().readNBT(SHINYCHARMTEMP_CAP, this.instance, null, nbt);
	}
}