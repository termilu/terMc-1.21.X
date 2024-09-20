package net.termilu.termc.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;

public class ModSounds {

    public static final SoundEvent CHAINSAW_CUT = registerSoundEvent("chainsaw_cut");
    public static final SoundEvent CHAINSAW_PULL = registerSoundEvent("chainsaw_pull");

    //Entities
    public static final SoundEvent CAPYBARA_AMBIENT_1 = registerSoundEvent("capybara_ambient_1");
    public static final SoundEvent CAPYBARA_AMBIENT_2 = registerSoundEvent("capybara_ambient_2");
    public static final SoundEvent CAPYBARA_HURT_1 = registerSoundEvent("capybara_hurt_1");
    public static final SoundEvent CAPYBARA_DEATH_1 = registerSoundEvent("capybara_death_1");


    private static SoundEvent registerSoundEvent(String name){
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(TerMc.MOD_ID, name),
                SoundEvent.of(Identifier.of(TerMc.MOD_ID, name)));
    }

    public static void registerSounds(){
        TerMc.LOGGER.info("Registering Mod Sounds for: " + TerMc.MOD_ID);
    }

}
