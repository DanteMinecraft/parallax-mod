package net.dantemc.parallax;

import com.lightning.northstar.world.temperature.NorthstarTemperature;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.level.Level;

public class ParallaxTemperatureBridge {

    public static float getTemperatureCelsius(Level level, Vec3 pos) {

        Float planetTemp = ParallaxPlanetManager.getTemperature(level);

        if (planetTemp != null) {
            return planetTemp; //Parallax temperature
        } else {
            return NorthstarTemperature.getTemperatureAt(level, pos); //Northstar fallback temperature
        }
    }

    // Display in UI:s
    public static float toKelvin(float celsius) {
        return celsius + 273.15f;
    }
}
