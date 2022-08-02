package cn.scii.gui.container;

import cn.scii.block.machineblock.tile.TilePowerGenerator;
import cn.scii.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGenerator extends ContainerBase {

    public ContainerGenerator(InventoryPlayer playerInv, final TilePowerGenerator generator) {
        super(playerInv,generator);
        IItemHandler inventory = generator.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 35) {
            @Override
            public void onSlotChanged() {
                generator.markDirty();
            }

            @Override
            public boolean canTakeStack(EntityPlayer playerIn) {
                return false;
            }

        });
    }
}
