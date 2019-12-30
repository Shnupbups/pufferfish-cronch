package com.shnupbups.pufferfishcronch;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PufferfishCronch implements ModInitializer {
	public static final String MOD_ID = "pufferfishcronch";
	
	public static SoundEvent CRONCH;
	public static SoundEvent COUGH;
	public static SoundEvent SQUELCH;
	public static SoundEvent BRAIN_DAMAGE;
	
	public static DamageSource CHOKE;
	
	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
	
	@Override
	public void onInitialize() {
		CRONCH = Registry.register(Registry.SOUND_EVENT, id("cronch"), new SoundEvent(id("cronch")));
		COUGH = Registry.register(Registry.SOUND_EVENT, id("cough"), new SoundEvent(id("cough")));
		SQUELCH = Registry.register(Registry.SOUND_EVENT, id("squelch"), new SoundEvent(id("squelch")));
		BRAIN_DAMAGE = Registry.register(Registry.SOUND_EVENT, id("brain_damage"), new SoundEvent(id("brain_damage")));
		
		CHOKE = new ChokeDamageSource();
	}
}
