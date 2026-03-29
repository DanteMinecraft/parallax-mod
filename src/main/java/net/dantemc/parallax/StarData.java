package net.dantemc.parallax;

import java.util.List;

public class StarData {
    public final String name;
    public final long seed;
    public final List<PlanetData> planets;

    public StarData(String name, long seed, List<PlanetData> planets) {
        this.name = name;
        this.seed = seed;
        this.planets = planets;
    }
}
