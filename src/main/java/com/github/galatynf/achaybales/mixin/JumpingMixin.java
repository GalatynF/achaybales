package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.rmi.StubNotFoundException;

@Mixin(PlayerEntity.class)
public abstract class JumpingMixin extends LivingEntity {

    protected JumpingMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(at = @At("INVOKE"), method = "tick")
    private void playEagleSound(CallbackInfo info) {
        if(!onGround && getVelocity().getY() < -2) {
            System.out.println("playing sound");
            //super.playSound(Achaybales.acjumpevent, 1.0F, 1.0F);
            world.playSound(null, getBlockPos(),Achaybales.acjumpevent, SoundCategory.AMBIENT, 1.0F, 1.0F);
        }
    }
}
