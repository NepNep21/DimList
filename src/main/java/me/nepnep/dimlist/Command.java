package me.nepnep.dimlist;

import com.google.common.collect.Lists;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class Command implements ICommand {

    @Override
    public int compareTo(ICommand arg) {
        return 0;
    }

    @Override
    public String getName() {
        return "dimlist";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/dimlist";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        int[] ids = DimensionManager.getRegisteredDimensions().values().stream().flatMap(Collection::stream).mapToInt(Integer::intValue).toArray();
        List<String> names = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            names.add(DimensionType.getById(ids[i]).getName());
        }

        Properties dimensionProperties = new Properties();

        for (int i = 0; i < ids.length; i++) {
            dimensionProperties.put(String.valueOf(ids[i]), names.get(i));
        }
        File file = new File("Dimensions.txt");

        try {
            file.createNewFile();
            dimensionProperties.store(new FileOutputStream("Dimensions.txt"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ITextComponent message = new TextComponentString("Dimension list created at " + file.getAbsolutePath());
        sender.sendMessage(message);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = Lists.newArrayList();
        aliases.add("dimlist");

        return aliases;
    }
}
