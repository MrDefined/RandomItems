package com.frontiermc.randomitem.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*public class RandomItemTypesArguments implements SuggestionProvider<ServerCommandSource> {
    public static ArrayList<String> Types = new ArrayList<>();
    public static String INVALID_TYPE = new String("Invalid Type.");

    @Override
    public String parse(com.mojang.brigadier.StringReader reader) throws CommandSyntaxException {
        return CommandSource.suggestMatching(Types);
    }

    @Override
    public static CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        return null;
    }
}*/
