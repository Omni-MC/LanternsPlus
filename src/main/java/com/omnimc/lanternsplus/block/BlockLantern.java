package com.omnimc.lanternsplus.block;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
                    box(5, 0, 5, 11, 7, 11),
                    box(6, 7, 6, 10, 9, 10),
                    box(8, 9, 6.5, 8, 11, 9.5)),
            VoxelShapes.or( //DOWN
                    box(5, 1, 5, 11, 8, 11),
                    box(6, 8, 6, 10, 10, 10),
                    box(8, 10, 6.5, 8, 12, 9.5),
                    box(8, 14, 6.5, 8, 16, 9.5),
                    box(8, 11, 6.5, 8, 15, 9.5)),
            VoxelShapes.or( //NORTH
                    box(5, 1, 5, 11, 8, 11),
                    box(6, 8, 6, 10, 10, 10),
                    box(6.5, 10, 8, 9.5, 12, 8),
                    box(8, 11, 6.5, 8, 13, 9.5),
                    box(7, 13, 6, 9, 14, 16)),
            VoxelShapes.or( //EAST
                    box(5, 1, 5, 11, 8, 11),
                    box(6, 8, 6, 10, 10, 10),
                    box(8, 10, 6.5, 8, 12, 9.5),
                    box(6.5, 11, 8, 9.5, 13, 8),
                    box(0, 13, 7, 10, 14, 9)),
            VoxelShapes.or( //SOUTH
                    box(5, 1, 5, 11, 8, 11),
                    box(6, 8, 6, 10, 10, 10),
                    box(6.5, 10, 8, 9.5, 12, 8),
                    box(8, 11, 6.5, 8, 13, 9.5),
                    box(7, 13, 0, 9, 14, 10)),
            VoxelShapes.or( //WEST
                    box(5, 1, 5, 11, 8, 11),
                    box(6, 8, 6, 10, 10, 10),
                    box(8, 10, 6.5, 8, 12, 9.5),
                    box(6.5, 11, 8, 9.5, 13, 8),
                    box(6, 13, 7, 16, 14, 9)),
    };

    public BlockLantern(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public BlockRenderType getRenderShape(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(FACING)) {
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
        Direction direction = context.getClickedFace();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        return Block.canSupportCenter(world, blockpos, direction);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, world, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }
}