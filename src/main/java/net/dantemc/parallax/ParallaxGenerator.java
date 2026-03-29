package net.dantemc.parallax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallaxGenerator {

    public static StarData generateStar(long worldSeed, int starNumber) {

        // These finals should be added to the config later on to make them customizable for players.
        final int MAX_PLANET_TEMPERATURE = 1000; // The maximum value a planet's temperature can be (in Kelvin). Default is 1000.
        final double GAS_GIANT_CHANCE = 0.3; // The chance of a planet being a gas giant. Range: 0-1. Default is 0.3
        final int MAX_PLANETS = 6; // Max planets in solar system
        final int ICE_TEMP = 200; // If under this temperature, the planet will be considered an ice planet
        final int MOLTEN_TEMP = 800; // If above this temperature, the planet will be considered a molten planet
        final String STAR_PREFIX = "PX"; // Prefix for all the stars and exoplanet names. Default is "PX" (short for Parallax!).

        String starName = STAR_PREFIX + "-" + starNumber;
        long starSeed = worldSeed * 31L + starNumber;

        Random rand = new Random(starSeed);

        int planetCount = rand.nextInt(MAX_PLANETS); // Amount of planets between 0-5 in each system - make customizable in config later

        List<PlanetData> planets = new ArrayList<>();

        for (int i = 0; i < planetCount; i++) {

            // Planet Name
            char planetSubname = (char) ('b' + i);
            String planetName = starName + planetSubname;

            // Planet temperature
            int planetTemp = rand.nextInt(MAX_PLANET_TEMPERATURE);

            // Planet type
            String planetType;
            boolean isPlanetLandable = true;

            if (rand.nextDouble() < GAS_GIANT_CHANCE) {
                planetType = "Gas Giant";
                isPlanetLandable = false;
            } else if (planetTemp < ICE_TEMP) {
                planetType = "Ice";
            } else if (planetTemp > MOLTEN_TEMP) {
                planetType = "Molten";
            } else {
                planetType = "Rocky";
            }

            long planetSeed = starSeed * 31L + i;

            planets.add(new PlanetData(planetName, planetType, planetTemp, isPlanetLandable, planetSeed));
        }
        return new StarData(starName, starSeed, planets);
    }
}
