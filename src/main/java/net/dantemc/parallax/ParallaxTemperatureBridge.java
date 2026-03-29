package net.dantemc.parallax;

import com.lightning.northstar.world.temperature.NorthstarTemperature;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.level.Level;

public class ParallaxTemperatureBridge {

    public static float getTemperatureCelsius(Level level, Vec3 pos) {
        return NorthstarTemperature.getTemperatureAt(level, pos);
    }

    public static float getTemperatureKelvin(Level level, Vec3 pos) {
        return getTemperatureCelsius(level, pos) + 273.15f;
    }
}
