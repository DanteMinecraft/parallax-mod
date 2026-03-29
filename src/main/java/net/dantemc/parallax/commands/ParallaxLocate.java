package net.dantemc.parallax.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.dantemc.parallax.ParallaxGenerator;
import net.dantemc.parallax.PlanetData;
import net.dantemc.parallax.StarData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.Random;

public class ParallaxLocate {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("parallax").then(Commands.literal("scan").executes(context -> {

            long worldSeed = context.getSource().getLevel().getSeed();

            Random baseRand = new Random();
            int starNumber = baseRand.nextInt(3); // Amount of starts between 0-2 in each system - make customizable in config later

            StarData star = ParallaxGenerator.generateStar(worldSeed, starNumber);

            StringBuilder output = new StringBuilder();

            if (!star.planets.isEmpty()) {
                output.append("=== Stellar Scan ===\n")
                        .append("Star: ").append(star.name)
                        .append("\nDetected Planets:");
            } else {
                output.append("=== Stellar Scan ===\n")
                        .append("Star: ").append(star.name)
                        .append("\nNo planets detected in the solar system");
            }

            for (PlanetData planet : star.planets) {
                output.append("\n\n- ").append(planet.name)
                        .append("\n  Type: ").append(planet.type)
                        .append("\n  Temperature: ").append(planet.temperature).append("K")
                        .append("\n  Landing Capability: ").append(planet.isLandable ? "True" : "False")
                        .append("\n  Planet Seed: ").append(planet.seed);
            }

            // Command response
            context.getSource().sendSuccess(() -> Component.literal(output.toString()), false);

            return 1;
        })));
    }
}