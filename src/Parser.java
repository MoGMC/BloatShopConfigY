import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Parser {

		YamlConfiguration config;

		public Parser(YamlConfiguration config) {

			this.config = config;

		}

		public void parseData() {

			for (String s : config.getConfigurationSection("shops").getKeys(false)) {
					System.out.println(s);

			}

		}

		// will overwrite current files or create new ones
		public void exportBloatShopConfig(File directory) {

		}

}
