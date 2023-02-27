package com.frontiermc.randomitem.commands;

import com.frontiermc.randomitem.utils.Config;
import com.frontiermc.randomitem.utils.Utils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import static com.frontiermc.randomitem.utils.Utils.regex;

public class RandomItemCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("randomitem").requires(Permissions.require("randomitems.command.randomitem")).then(CommandManager.argument("player",
                        EntityArgumentType.player())
                .then(CommandManager.argument("type", StringArgumentType.string())
                        .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                .executes(RandomItemCommand::sendRandomAmount))
                        .executes(RandomItemCommand::sendRandom))));
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
                    ItemStack evoitem = new ItemStack(Utils.randomEvoitem());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + evoitem.getCount() + " §d" + evoitem.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(evoitem);
                    //pokeballs.add(evostone.getItem());
                }
                break;
            /*case "helditem":
                for (int x = 0; x < inputNum; x++) {
                    ItemStack helditem = new ItemStack(Utils.randomHelditem());
                    cmdPlayer.sendMessage(Text.literal("§6You received " + helditem.getCount() + " §d" + helditem.getName().getString() + "§6."));
                    cmdPlayer.giveItemStack(helditem);
                    //pokeballs.add(evostone.getItem());
                }
                break;*/
            default:
                cmdPlayer.sendMessage(Text.literal(regex("§cInvalid argument.")));
                //cmdPlayer.sendMessage(new StringTextComponent(regex(getUsage(sender))));
                break;
        }
    }



}
