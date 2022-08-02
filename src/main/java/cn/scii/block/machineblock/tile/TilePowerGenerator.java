package cn.scii.block.machineblock.tile;

import cn.scii.ScienceIndustry;
import cn.scii.block.machineblock.impl.MachinePowerGenerator;
import cn.scii.util.ForgeDirection;
import cn.scii.util.TileHelper;
import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TilePowerGenerator extends TileEntity implements IEnergyProvider, ITickable {
    public static EnergyStorage storage = new EnergyStorage(12000);

    private boolean isActive = false;

    private ItemStackHandler inventory = new ItemStackHandler(1);
    private static int totalBurnTime = 0;
    private static int elapsedBurnTime = 0;

    protected static int maxEnergyGeneratedPerTick = 50;

    final IEnergyStorage energyCap;

    public TilePowerGenerator(){
        energyCap = new IEnergyStorage() {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                return 0;
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                return storage.extractEnergy(maxExtract,simulate);
            }

            @Override
            public int getEnergyStored() {
                return storage.getEnergyStored();
            }

            @Override
            public int getMaxEnergyStored() {
                return storage.getMaxEnergyStored();
            }

            @Override
            public boolean canExtract() {
                return true;
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        };
    }

    private boolean checkRunningState(){
        ItemStack itemStack = inventory.getStackInSlot(0);
        int fuelTime = TileHelper.getItemBurnTime(itemStack);
        //if(totalBurnTime <= elapsedBurnTime){
        //    if(!TileHelper.isItemFuel(itemStack))
        //        return false;
        //    elapsedBurnTime = 0;
        //    itemStack.setCount(itemStack.getCount() - 1);
        //    if(totalBurnTime != fuelTime)
        //       totalBurnTime = fuelTime;
        //}
        //elapsedBurnTime++;
        return fuelTime > 0;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }


    //@Override
    //public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        //return writeToNBT(new NBTTagCompound());
    //}

    //@Override
    //public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        //NBTTagCompound nbtTag = new NBTTagCompound();
        //this.writeToNBT(nbtTag);
        //return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    //}

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
        inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        nbt.setTag("inventory", inventory.serializeNBT());
        return nbt;
    }

    /* IEnergyConnection */
    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }


    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        ScienceIndustry.logger.debug("Method [extractEnergy] is being called now!");
        markDirty();
        return storage.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY ||
                capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY)
            return CapabilityEnergy.ENERGY.cast(energyCap);
        else
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void update() {

        if(checkRunningState()){
            storage.receiveEnergy(maxEnergyGeneratedPerTick,false);
            isActive = true;
        } else
            isActive = false;

        ((MachinePowerGenerator) this.getBlockType()).setState(isActive,this);

        int xCoord = getPos().getX();
        int yCoord = getPos().getY();
        int zCoord = getPos().getZ();
        if ((storage.getEnergyStored() > 0)) {
            for (int i = 0; i < 6; i++){
                TileEntity tile = getWorld().getTileEntity(
                        new BlockPos(xCoord + ForgeDirection.getOrientation(i).offsetX,
                                yCoord + ForgeDirection.getOrientation(i).offsetY,
                                zCoord + ForgeDirection.getOrientation(i).offsetZ)
                );
                if (tile instanceof IEnergyReceiver) {
                    storage.extractEnergy(((IEnergyReceiver) tile).receiveEnergy(ForgeDirection.cast(ForgeDirection.getOrientation(i).getOpposite()), storage.extractEnergy(storage.getMaxExtract(), true), false), false);
                }
            }
        }
    }
}
