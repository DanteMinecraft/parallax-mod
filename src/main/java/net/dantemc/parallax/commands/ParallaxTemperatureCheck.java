package net.dantemc.parallax.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.dantemc.parallax.ParallaxTemperatureBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ParallaxTemperatureCheck {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("parallax")
                        .then(Commands.literal("temperature")
                                .executes(context -> {

                                    CommandSourceStack source = context.getSource();

                                    if (!(source.getEntity() instanceof Player player)) {
                                        source.sendFailure(Component.literal("Must be a player!"));
                                        return 0;
                                    }

                                    Level level = player.level();
                                    Vec3 pos = player.position();

                                    float celsius = ParallaxTemperatureBridge.getTemperatureCelsius(level, pos);
                                    float kelvin = ParallaxTemperatureBridge.toKelvin(celsius);

                                    source.sendSuccess(() ->
                                                    Component.literal("Northstar: " + celsius + " °C\n" + "Parallax: " + kelvin + " K"),
                                            false
                                    );

                                    return 1;
                                })
                        )
        );
    }
}