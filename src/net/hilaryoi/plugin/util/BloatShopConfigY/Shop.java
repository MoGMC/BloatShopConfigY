package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class Shop {

		String shopId;

		boolean isBuyShop;
		String displayName;

		List<ShopItem> items;

		boolean[] inv;

		public Shop(String shopId, ConfigurationSection config) {

			inv = new boolean[27];

			this.shopId = shopId;

			isBuyShop = config.getString("type").equalsIgnoreCase("buy");
			displayName = config.getString("name");

			items = new ArrayList<ShopItem>();

			ConfigurationSection itemsConfig = config.getConfigurationSection("items");

			for (String itemKey : itemsConfig.getKeys(false)) {

					int invSlot = -1;

					if (itemsConfig.contains(itemKey + ".slot")) {
						invSlot = itemsConfig.getInt(itemKey + ".slot");

						if (inv[invSlot]) {
								System.err.println("Inventory conflict: Two items cannot exist at inventory location " + invSlot + ". Skipping item " + itemKey);
								continue;

						}

					} else {

						for (int i = 0; i < inv.length; i++) {

								if (!inv[i]) {
									invSlot = i;

								}

						}

					}

					if (invSlot == -1) {
						System.err.println("Inventory conflict: Item could not be assigned a slot. Is the inventory full?");

					}

					inv[invSlot] = true;

					items.add(new ShopItem(Util.fromMarkup(itemKey), itemsConfig.getDouble(itemKey), invSlot));

			}

		}

		public String toBloat() {

			StringBuilder s = new StringBuilder("ShopName: ");

			s.append(shopId);

			s.append("\nDisplayName: '");
			s.append(displayName);
			s.append("'\nshop:\n");

			for (ShopItem item : items) {

					s.append(item.toBloat(isBuyShop));

			}

			return s.toString();

		}

}
