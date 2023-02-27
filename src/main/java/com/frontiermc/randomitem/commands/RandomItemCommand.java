package com.frontiermc.randomitem.commands;

import com.cobblemon.mod.common.CobblemonBlockEntities;
import com.cobblemon.mod.common.CobblemonEntities;
import com.cobblemon.mod.common.CobblemonItems;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.fabric.CobblemonFabric;
import com.frontiermc.randomitem.utils.Config;
import com.frontiermc.randomitem.utils.Utils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Material;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static com.frontiermc.randomitem.utils.Utils.evoitems;
import static com.frontiermc.randomitem.utils.Utils.regex;

public class RandomItemCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("randomitem").then(CommandManager.argument("player",
                        EntityArgumentType.player())
                .then(CommandManager.argument("type", StringArgumentType.string())
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                .executes(RandomItemCommand::sendRandomAmount))
                        .executes(RandomItemCommand::sendRandom))));
    }

    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        return 1;
    }

    private static int sendRandomAmount(CommandContext<ServerCommandSource> commandSourceCommandContext)
            throws CommandSyntaxException {
        int amount = IntegerArgumentType.getInteger(commandSourceCommandContext, "amount");
        ServerPlayerEntity player = EntityArgumentType.getPlayer(commandSourceCommandContext, "player");
        String type = StringArgumentType.getString(commandSourceCommandContext, "type");
        try {
            execute(player, type, amount);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int sendRandom(CommandContext<ServerCommandSource> commandSourceCommandContext)
            throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(commandSourceCommandContext, "player");
        String type = StringArgumentType.getString(commandSourceCommandContext, "type");
        try {
            execute(player, type, 0);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Command.SINGLE_SUCCESS;
    }
    //public static ArrayList<Item> pokeballs = new ArrayList<>();
    //public static ArrayList<Item> apricorns = new ArrayList<>();
    private static void execute(ServerPlayerEntity cmdPlayer, String type, int inputNum)
            throws IllegalAccessException {
        String msg = Config.receiverMsg;
        switch (type) {
            case "ball":
                for (int x = 0; x < inputNum; x++) {
                    //creates new ball itemstack
                    ItemStack ball = new ItemStack(Utils.randomBall());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + ball.getCount() + " §d" + ball.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(ball);
                    //pokeballs.add(ball.getItem());
                }
                break;
            case "apricorn":
                for (int x = 0; x < inputNum; x++) {
                    ItemStack apricorn = new ItemStack(Utils.randomApricorn());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + apricorn.getCount() + " §d" + apricorn.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(apricorn);
                    //apricorns.add(apricorn.getItem());
                }
                break;
            case "evostone":
                for (int x = 0; x < inputNum; x++) {
                    ItemStack evostone = new ItemStack(Utils.randomEvostone());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + evostone.getCount() + " §d" + evostone.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(evostone);
                    //pokeballs.add(evostone.getItem());
                }
                break;
            case "evoitem":
                for (int x = 0; x < inputNum; x++) {
                    ItemStack evoitem = new ItemStack(Utils.randomEvoitems());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + evoitem.getCount() + " §d" + evoitem.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(evoitem);
                    //pokeballs.add(evostone.getItem());
                }
                break;
            default:
                cmdPlayer.sendMessage(Text.literal(regex("§cInvalid argument.")));
                //cmdPlayer.sendMessage(new StringTextComponent(regex(getUsage(sender))));
                break;
        }
    }



}
