package com.frontiermc.randomitem.utils;

import com.cobblemon.mod.common.CobblemonItems;
import com.cobblemon.mod.common.api.apricorn.Apricorn;
import com.cobblemon.mod.common.api.pokeball.PokeBalls;
import com.cobblemon.mod.common.item.ApricornItem;
import com.cobblemon.mod.common.item.CobblemonItem;
import com.cobblemon.mod.common.item.CobblemonItemGroups;
import com.cobblemon.mod.common.item.PokeBallItem;
import com.cobblemon.mod.common.pokeball.PokeBall;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String regex(String line) {
        String regex = "&(?=[0123456789abcdefklmnor])";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            line = line.replaceAll(regex, "§");
        }
        return line;
    }

    public static List<Item> blacklistedItemList() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < Config.blacklistedItemIDs.length; i++) {
            Item newItem = CobblemonItem.byRawId(Integer.parseInt(Config.blacklistedItemIDs[i]));
            if (newItem != null) {
                items.add(newItem);
            }
        }
        return items;
    }

    public static ArrayList<PokeBall> pokeballs = new ArrayList<>();
    public static ArrayList<Item> evoitems = new ArrayList<>();
    public static ArrayList<Item> evostones = new ArrayList<>();

    public static void loadLists() {
        pokeballs.addAll(PokeBalls.INSTANCE.all());
    }
    public static Item randomBall() {
        while (true) {
            Item pokeball = pokeballs.get(new Random().nextInt(pokeballs.size())).item();
            //if (!blacklistedItemList().contains(pokeball.getName().toString())) {
                return pokeball;
            //}
        }
    }

    public static Item randomApricorn() {
        while (true) {
            Apricorn apricorn = Apricorn.values()[ThreadLocalRandom.current().nextInt(Apricorn.values().length)];
            //if (!blacklistedItemList().contains(apricorn.item().getName().toString())) {
                return apricorn.item();
            //}
        }
    }

    private static HashMap<Class<?>, List<Object>> classes = new HashMap<>();

    public static <T> T getRandom(Class<T> clazz) throws IllegalAccessException {
        List<Object> objects = classes.computeIfAbsent(clazz, k -> new ArrayList<>());
        if (objects.isEmpty()) {
            for (Field declaredField : CobblemonItems.class.getDeclaredFields()) {
                if (declaredField.getDeclaringClass().isAssignableFrom(clazz)) {
                    objects.add(declaredField.get(null));
                }
            }
        }
        return (T) objects.get(ThreadLocalRandom.current().nextInt(objects.size()));
    }

}
