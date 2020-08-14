package com.omnimc.lanternsplus.block;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BlockLantern extends Block implements IWaterLoggable {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape[] SHAPES = new VoxelShape[] {
            VoxelShapes.or( //UP
                    makeCuboidShape(5, 0, 5, 11, 7, 11),
                    makeCuboidShape(6, 7, 6, 10, 9, 10),
                    makeCuboidShape(8, 9, 6.5, 8, 11, 9.5)),
            VoxelShapes.or( //DOWN
                    makeCuboidShape(5, 1, 5, 11, 8, 11),
                    makeCuboidShape(6, 8, 6, 10, 10, 10),
                    makeCuboidShape(8, 10, 6.5, 8, 12, 9.5),
                    makeCuboidShape(8, 14, 6.5, 8, 16, 9.5),
                    makeCuboidShape(8, 11, 6.5, 8, 15, 9.5)),
            VoxelShapes.or( //NORTH
                    makeCuboidShape(5, 1, 5, 11, 8, 11),
                    makeCuboidShape(6, 8, 6, 10, 10, 10),
                    makeCuboidShape(6.5, 10, 8, 9.5, 12, 8),
                    makeCuboidShape(8, 11, 6.5, 8, 13, 9.5),
                    makeCuboidShape(7, 13, 6, 9, 14, 16)),
            VoxelShapes.or( //EAST
                    makeCuboidShape(5, 1, 5, 11, 8, 11),
                    makeCuboidShape(6, 8, 6, 10, 10, 10),
                    makeCuboidShape(8, 10, 6.5, 8, 12, 9.5),
                    makeCuboidShape(6.5, 11, 8, 9.5, 13, 8),
                    makeCuboidShape(0, 13, 7, 10, 14, 9)),
            VoxelShapes.or( //SOUTH
                    makeCuboidShape(5, 1, 5, 11, 8, 11),
                    makeCuboidShape(6, 8, 6, 10, 10, 10),
                    makeCuboidShape(6.5, 10, 8, 9.5, 12, 8),
                    makeCuboidShape(8, 11, 6.5, 8, 13, 9.5),
                    makeCuboidShape(7, 13, 0, 9, 14, 10)),
            VoxelShapes.or( //WEST
                    makeCuboidShape(5, 1, 5, 11, 8, 11),
                    makeCuboidShape(6, 8, 6, 10, 10, 10),
                    makeCuboidShape(8, 10, 6.5, 8, 12, 9.5),
                    makeCuboidShape(6.5, 11, 8, 9.5, 13, 8),
                    makeCuboidShape(6, 13, 7, 16, 14, 9)),
    };

    public BlockLantern(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)) {
            case UP:
                return SHAPES[0];
            case DOWN:
            default:
                return SHAPES[1];
            case NORTH:
                return SHAPES[2];
            case EAST:
                return SHAPES[3];
            case SOUTH:
                return SHAPES[4];
            case WEST:
                return SHAPES[5];
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockpos = pos.offset(direction.getOpposite());
        return Block.hasEnoughSolidSide(world, blockpos, direction);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(state, world, currentPos) ? state : Blocks.AIR.getDefaultState();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.with(FACING, mirror.mirror(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
    }

    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }
}