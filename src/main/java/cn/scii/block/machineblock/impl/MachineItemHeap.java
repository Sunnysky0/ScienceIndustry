package cn.scii.block.machineblock.impl;

import cn.scii.block.machineblock.BlockMachine;
import cn.scii.block.machineblock.tile.TileItemHeap;
import cn.scii.proxy.CommonProxy;
import cn.scii.util.TileHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class MachineItemHeap extends BlockMachine<TileItemHeap> {

    public MachineItemHeap() {
        super("item_heap");
        this.setHardness(32);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public Class<TileItemHeap> getTileEntityClass() {
        return TileItemHeap.class;
    }

    @Nullable
    @Override
    public TileItemHeap createTileEntity(World world, IBlockState state) {
        return new TileItemHeap();
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer player) {
        if(!worldIn.isRemote){
            TileItemHeap tile = getTileEntity(worldIn,pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.EAST);
            ItemStack stack = itemHandler.getStackInSlot(0);
            if (!stack.isEmpty()) {
                String localized = CommonProxy.localize(stack.getTranslationKey()+ ".name");
                player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
            } else {
                player.sendMessage(new TextComponentString("Empty"));
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileItemHeap tile = getTileEntity(world, pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            ItemStack heldItem = player.getHeldItem(hand);

            int amount;

            if (!player.isSneaking()) {
                amount = 1;
            } else {
                amount = 64;
            }

            if (heldItem .isEmpty()) {
                player.setHeldItem(hand, itemHandler.extractItem(0, amount, false));
            } else {
                if((!heldItem.isItemEqual(itemHandler.getStackInSlot(0))
                        && !itemHandler.getStackInSlot(0).isEmpty())
                        || !TileHelper.isItemFuel(heldItem)){
                    player.sendMessage(new TextComponentTranslation("msg.item_not_compatible.info"));
                }else
                if(itemHandler.getStackInSlot(0).getCount() == heldItem.getMaxStackSize())
                {
                    player.sendMessage(new TextComponentTranslation("msg.heap_overflow.info"));
                }else{
                    if(player.isSneaking()){
                        player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));
                    }else {
                        int temp = heldItem.getCount();
                        ItemStack temp2 = heldItem.copy();

                        heldItem.setCount(1);
                        player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));

                        temp2.setCount(temp - 1);
                        player.setHeldItem(hand, temp2);
                    }
                }
            }
            tile.markDirty();
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileItemHeap tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);

        if (!stack.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }
        super.breakBlock(world, pos, state);
    }

}
