package com.shnupbups.pufferfishcronch.mixin;

import net.fabricmc.fabric.api.tools.FabricToolTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

import com.shnupbups.pufferfishcronch.PufferfishCronch;

@Mixin(FishEntity.class)
public abstract class FishEntityMixin {
	@Inject(method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Z", at = @At(value = "RETURN", ordinal = 1))
	public void feedCarrot(PlayerEntity player, Hand hand, CallbackInfoReturnable<Boolean> cir) {
		if ((Object) this instanceof PufferfishEntity) {
			PufferfishEntity puff = (PufferfishEntity) (Object) this;
			if (puff.isAlive()) {
				ItemStack stack = player.getStackInHand(hand);
				if (stack.getItem() == Items.CARROT) {
					if (!player.isCreative()) stack.decrement(1);
					if (player.getRandom().nextInt(4) == 0) {
						puff.damage(PufferfishCronch.CHOKE, 0.1f);
					} else puff.playSound(PufferfishCronch.CRONCH, 0.3f, 0.5f + player.getRandom().nextFloat());
				} else if (stack.getItem() == Items.CARROT_ON_A_STICK) {
					if (!player.isCreative()) {
						stack.damage(7, player, ((p) -> {
							p.sendToolBreakStatus(hand);
						}));
					}
					if (player.getRandom().nextInt(4) == 0) {
						puff.damage(PufferfishCronch.CHOKE, 0.1f);
					} else puff.playSound(PufferfishCronch.CRONCH, 0.3f, 0.5f + player.getRandom().nextFloat());
				} else if (stack.getItem() == Items.GOLDEN_CARROT) {
					if (!player.isCreative()) stack.decrement(1);
					if (player.getRandom().nextInt(3) == 0) {
						puff.damage(PufferfishCronch.CHOKE, 0.25f);
					} else puff.playSound(PufferfishCronch.SQUELCH, 0.3f, 0.5f + player.getRandom().nextFloat());
				} else if (stack.getItem().isIn(FabricToolTags.SWORDS)) {
					if (!player.isCreative()) {
						stack.damage(1, player, ((p) -> {
							p.sendToolBreakStatus(hand);
						}));
					}
					puff.playSound(PufferfishCronch.BRAIN_DAMAGE, 0.3f, 0.5f + player.getRandom().nextFloat());
					puff.setAiDisabled(true);
					puff.setHealth(0.1f);
				}
			}
		}
	}
}
