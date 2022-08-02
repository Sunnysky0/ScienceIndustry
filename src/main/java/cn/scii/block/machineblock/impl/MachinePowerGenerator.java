package cn.scii.block.machineblock.impl;

import cn.scii.ScienceIndustry;
import cn.scii.block.machineblock.BlockMachine;
import cn.scii.block.machineblock.tile.TileItemHeap;
import cn.scii.block.machineblock.tile.TilePowerGenerator;
import cn.scii.gui.SIGuiHandler;
import cn.scii.util.TileHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;


public class MachinePowerGenerator extends BlockMachine<TilePowerGenerator>{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    // Used for visuals only
    public static final IProperty<Boolean> IS_ACTIVE = PropertyBool.create("is_active");

    protected IBlockAccess worldIn;
    protected BlockPos pos;

    public MachinePowerGenerator() {
        super("power_battery");
        setHardness(32);
        setDefaultState(blockState.getBaseState().withProperty(IS_ACTIVE, false).withProperty(FACING,EnumFacing.NORTH));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(IS_ACTIVE, false);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public boolean hasTile() {
        return true;
    }


    @Override
    public void onPlayerDestroy(World worldIn, BlockPos pos, IBlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        this.worldIn = worldIn;
        this.pos = pos;
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, IS_ACTIVE,FACING);
    }


    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        playerIn.sendMessage(new TextComponentString("Stored Energy: "
                + getTileEntity(worldIn,pos).getEnergyStored(EnumFacing.NORTH) + "RF"));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){
            TilePowerGenerator tile = getTileEntity(worldIn, pos);

            player.openGui(ScienceIndustry.INSTANCE, SIGuiHandler.GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());

            tile.markDirty();
        }
        return true;
    }

    public void setState(boolean active,TilePowerGenerator te){

        World worldIn = te.getWorld();
        BlockPos pos = te.getPos();

        worldIn.setBlockState(pos,this.getDefaultState().withProperty(IS_ACTIVE,active));

        // te.invalidate();
    }

    @Override
    public Class<TilePowerGenerator> getTileEntityClass() {
        return TilePowerGenerator.class;
    }

    @Nullable
    @Override
    public TilePowerGenerator createTileEntity(World world, IBlockState state) {
        return new TilePowerGenerator();
    }


}
