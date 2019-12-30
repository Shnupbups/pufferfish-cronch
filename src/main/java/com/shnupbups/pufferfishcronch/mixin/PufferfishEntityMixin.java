package com.shnupbups.pufferfishcronch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.sound.SoundEvent;

import com.shnupbups.pufferfishcronch.PufferfishCronch;

@Mixin(PufferfishEntity.class)
public class PufferfishEntityMixin {
	@Inject(method = "getHurtSound(Lnet/minecraft/entity/damage/DamageSource;)Lnet/minecraft/sound/SoundEvent;", at = @At("RETURN"), cancellable = true)
	public void coughDamageSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
		if (source == PufferfishCronch.CHOKE) {
			cir.setReturnValue(PufferfishCronch.COUGH);
		}
	}
}
