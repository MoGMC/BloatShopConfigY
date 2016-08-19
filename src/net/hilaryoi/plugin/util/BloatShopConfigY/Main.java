package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Main {

		public static void main(String[] args) {

			String configPath = "/mary/dev/bukkitdev/mogmc-workspace/BloatShopConfigY/config.yml";

			String exportDir = "/mary/dev/bukkitdev/mogmc-workspace/BloatShopConfigY/BossShop";

			File file = new File(configPath);

			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

			Parser p = new Parser(config);

			p.parseData();

			try {
					p.exportBloatShopConfig(new File(exportDir));

			} catch (IOException e) {
					System.err.println("Error while exporting bloated config:");
					e.printStackTrace();

			}

			System.out.println("K");

		}

}
