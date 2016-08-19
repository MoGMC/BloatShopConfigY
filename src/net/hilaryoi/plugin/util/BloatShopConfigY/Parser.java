package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Parser {

		YamlConfiguration config;

		List<Shop> shops;

		public Parser(YamlConfiguration config) {

			this.config = config;

			shops = new ArrayList<Shop>();

		}

		public void parseData() {

			ConfigurationSection shopsConfig = config.getConfigurationSection("shops");

			for (String shop : shopsConfig.getKeys(false)) {

					shops.add(new Shop(shop, shopsConfig.getConfigurationSection(shop)));

			}

		}

		// will overwrite current files or create new ones
		public void exportBloatShopConfig(File directory) throws IOException {

			for (Shop shop : shops) {

					File file = new File(directory, shop.shopId + ".yml");

					if (!file.exists()) {
						file.getParentFile().mkdirs();
						file.createNewFile();

					}

					FileWriter writer = new FileWriter(file, false);

					writer.write(shop.toBloat());

					writer.close();

			}

		}

}
