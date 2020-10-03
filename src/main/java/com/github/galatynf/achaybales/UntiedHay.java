package com.github.galatynf.achaybales;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class UntiedHay extends Block {

    public UntiedHay(Settings settings) {
        super(settings.nonOpaque());
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
        entity.slowMovement(state, new Vec3d(1, 1, 1));
        if(entity instanceof PlayerEntity &&
            !entity.isOnGround() &&
            entity.getVelocity().getY() <= -1.5F) {
            BlockPos posPlayer = entity.getBlockPos();
            world.playSound(posPlayer.getX(), posPlayer.getY(), posPlayer.getZ(),Achaybales.aclandevent, SoundCategory.AMBIENT, 1F,1F,true);
        }

        BlockPos posUp = new BlockPos(new Vec3d(pos.getX(), pos.getY() + 1, pos.getZ()));
        if(entity instanceof LivingEntity &&
            world.getBlockState(posUp).isOf(Achaybales.untiedhay) &&
            entity.getY() == pos.getY()) {
            StatusEffect statusEffectInv = StatusEffects.INVISIBILITY;
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(statusEffectInv, 2, 0, false, false));
            StatusEffect statusEffectStr = StatusEffects.STRENGTH;
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(statusEffectStr, 2, 1, false, false));
        }
    }
}
