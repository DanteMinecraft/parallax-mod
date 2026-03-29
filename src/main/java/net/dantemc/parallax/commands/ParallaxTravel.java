package net.dantemc.parallax.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.dantemc.parallax.ParallaxGenerator;
import net.dantemc.parallax.PlanetData;
import net.dantemc.parallax.StarData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ParallaxTravel {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("parallax")
                .then(Commands.literal("travel")
                        .then(Commands.argument("planet", StringArgumentType.word())
                        .executes(context -> {

                            String input = StringArgumentType.getString(context, "planet");

                            ServerPlayer player = context.getSource().getPlayer();

                            long worldSeed = context.getSource().getLevel().getSeed();

                            // Parse "PX-1c"
                            int starNumber = Integer.parseInt(input.substring(3, input.length() - 1));
                            char letter = input.charAt(input.length() - 1);
                            int planetIndex = letter - 'b';

                            // Regenerate system
                            StarData star = ParallaxGenerator.generateStar(worldSeed, starNumber);

                            if (planetIndex < 0 || planetIndex >= star.planets.size()) {
                                context.getSource().sendFailure(Component.literal("Planet not found."));
                                return 0;
                            }

                            PlanetData planet = star.planets.get(planetIndex);

                            // Fake "dimension" via coordinates
                            int x = (int) (planet.seed % 10000);
                            int y = 120;
                            int z = (int) ((planet.seed / 10000) % 10000);

                            if (planet.isLandable == true) {
                                player.teleportTo(x, y, z);
                                context.getSource().sendSuccess(() -> Component.literal("Travelling to " + planet.name + "..."), false);
                            } else {
                                context.getSource().sendSuccess(() -> Component.literal("Target planet " + planet.name + " is a gas giant. No solid surface detected."), false);
                            }

                            return 1;
                        }))));
    }
}