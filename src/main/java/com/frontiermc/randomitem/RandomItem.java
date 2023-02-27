package com.frontiermc.randomitem;

import com.frontiermc.randomitem.commands.RandomItemCommand;
import com.frontiermc.randomitem.utils.Utils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomItem implements ModInitializer {
	public static final String MOD_ID = "randomitem";
	public static final String MOD_NAME = "RandomItem";
	public static final String VERSION = "0.0.1";

	public static RandomItem INSTANCE;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(RandomItemCommand::register);
		Utils.loadLists();
	}
}
