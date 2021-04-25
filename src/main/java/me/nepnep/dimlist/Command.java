package me.nepnep.dimlist;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class Command {
    public static void registerCommand() {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("dimlist").executes(context -> {
            List<Identifier> identifiers = context.getSource().getRegistryManager().getDimensionTypes().getIds().stream().collect(Collectors.toList());
            List<String> ids = new ArrayList<>();

            for (Identifier id : identifiers) {
                ids.add(id.toString());
            }

            try (FileWriter writer = new FileWriter("Dimensions.txt")) {
                String formatted = ids.toString().replaceAll(",", String.format(",%n"));
                writer.write(formatted);
                context.getSource().sendFeedback(new LiteralText("Dimension list saved at " + new File("Dimensions.txt").getAbsolutePath()));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return 1;
        }));
    }
}
