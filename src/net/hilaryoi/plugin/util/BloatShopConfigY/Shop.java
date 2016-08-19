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

					ShopItem item = new ShopItem(Util.fromMarkup(itemKey), itemsConfig.getDouble(itemKey));

					if (item.getRawItem().invSlot == -1) {

						for (int i = 0; i < inv.length; i++) {

								if (!inv[i]) {
									
									System.out.println(i);

									item.getRawItem().setInvSlot(i);

									break;

								}
						}

					}

					if (item.getRawItem().invSlot == -1) {
						System.err.println("Inventory conflict: Item could not be assigned a slot. Is the inventory full? Skipping item " + itemKey);
						continue;

					}

					inv[item.getRawItem().invSlot] = true;

					items.add(item);

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
