package net.dantemc.parallax;

public class PlanetData {
    public final String name;
    public final String type;
    public final int temperature;
    public final boolean isLandable;
    public final long seed;

    public PlanetData(String name, String type, int temperature, boolean isLandable, long seed) {
        this.name = name;
        this.type = type;
        this.temperature = temperature;
        this.isLandable = isLandable;
        this.seed = seed;
    }
}
