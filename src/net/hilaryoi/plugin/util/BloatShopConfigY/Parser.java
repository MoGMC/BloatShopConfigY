package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Parser {

		YamlConfiguration config;

		HashMap<String, Shop> shops;

		public Parser(YamlConfiguration config) {

			this.config = config;

			shops = new HashMap<String, Shop>();

		}

		public void parseData() {

			ConfigurationSection shopsList = config.getConfigurationSection("shops");

			for (String shop : shopsList.getKeys(false)) {

					shops.put(shop, new Shop(shop, shopsList.getConfigurationSection(shop)));

			}

			ConfigurationSection convertList = config.getConfigurationSection("convert");

			for (String convert : convertList.getKeys(false)) {

					ConfigurationSection convertConfig = convertList.getConfigurationSection(convert);

					shops.put(convert, shops.get(convertConfig.getString("oldshop")).getModifiedShop(convert, convertConfig));

			}

		}

		// will overwrite current files or create new ones
		public void exportBloatShopConfig(File directory) throws IOException {

			for (Shop shop : shops.values()) {

					File file = new File(directory, shop.shopId + ".yml");

					if (!file.exists()) {
						file.getParentFile().mkdirs();
						file.createNewFile();

					}

					FileWriter writer = new FileWriter(file, false);

					writer.write(shop.toBloat());

					writer.close();

					System.out.println("Wrote " + file.getAbsolutePath());

			}

		}

}
