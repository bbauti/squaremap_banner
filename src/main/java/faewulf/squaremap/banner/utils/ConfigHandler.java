package faewulf.squaremap.banner.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import faewulf.squaremap.banner.Squaremapbanner;
import faewulf.squaremap.banner.dataType.ModConfigs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHandler {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File("config/squaremap_banner.json");

	public static ModConfigs loadConfig() {
		if (CONFIG_FILE.exists()) {
			try (FileReader reader = new FileReader(CONFIG_FILE)) {
				ModConfigs config = GSON.fromJson(reader, ModConfigs.class);
				if (config == null) {
					return new ModConfigs();
				}
				if (config.blacklist == null) {
					config.blacklist = new ModConfigs().blacklist;
				}
				return config;
			} catch (IOException | JsonParseException e) {
				Squaremapbanner.LOGGER.error(e.toString());
				return new ModConfigs();
			}
		} else {
			return new ModConfigs();
		}
	}

	public static void saveConfig(ModConfigs config) {
		try {
			java.nio.file.Files.createDirectories(CONFIG_FILE.toPath().getParent());
			try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
				GSON.toJson(config, writer);
			}
		} catch (IOException e) {
			Squaremapbanner.LOGGER.error(e.toString());
		}
	}
}
