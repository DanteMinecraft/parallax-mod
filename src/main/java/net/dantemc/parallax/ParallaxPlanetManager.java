package net.dantemc.parallax;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class ParallaxPlanetManager {

    private static final Map<ResourceKey<Level>, Float> PLANET_TEMPS = new HashMap<>();

    // temperature set
    public static void setTemperature(ResourceKey<Level> dimension, float temp) {
        PLANET_TEMPS.put(dimension, temp);
    }

    // get temp
    public static Float getTemperature(Level level) {
        return PLANET_TEMPS.get(level.dimension());
    }

    // check if parallax planet
    public static boolean isParallaxPlanet(Level level) {
        return PLANET_TEMPS.containsKey(level.dimension());
    }
}
