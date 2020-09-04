package com.github.galatynf.achaybales;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class UntiedHay extends GlassBlock {

    public UntiedHay(Settings settings) {
        super(Settings.of(Material.LEAVES).nonOpaque());
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.slowMovement(state, new Vec3d(0.5D, 0.9D, 0.5D));
    }

    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.handleFallDamage(distance, 0.0F);
        if(entity instanceof PlayerEntity && distance >= 15) {
            BlockPos posPlayer = entity.getBlockPos();
            world.playSound(posPlayer.getX(), posPlayer.getY(), posPlayer.getZ(),Achaybales.aclandevent, SoundCategory.AMBIENT, 1F,1F,true);
        }
    }
}
