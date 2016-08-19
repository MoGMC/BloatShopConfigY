package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class Shop {

		// TODO: combine shopItem and item

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

					ShopItem item = new ShopItem(Util.fromMarkup(itemKey, isBuyShop), itemsConfig.getDouble(itemKey));

					if (item.getRawItem().getInvSlot() == -1) {

						for (int i = 0; i < inv.length; i++) {

								if (!inv[i]) {

									System.out.println(i);

									item.getRawItem().setInvSlot(i);

									break;

								}
						}

					}

					if (item.getRawItem().getInvSlot() == -1) {
						System.err.println("Inventory conflict: Item could not be assigned a slot. Is the inventory full? Skipping item " + itemKey);
						continue;

					}

					inv[item.getRawItem().getInvSlot()] = true;

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

		public Shop(Shop shop) {

			this.shopId = shop.shopId;
			this.isBuyShop = shop.isBuyShop;
			this.displayName = shop.displayName;
			this.items = shop.items;
			this.inv = shop.inv;

		}

		public void setShopId(String shopId) {
			this.shopId = shopId;

		}

		public void setIsBuyShop(boolean isBuyShop) {
			this.isBuyShop = isBuyShop;

		}

		public List<ShopItem> getItems() {
			return items;

		}

		public Shop getModifiedShop(String newId, ConfigurationSection config) {

			Shop shop = new Shop(this);

			shop.setShopId(newId);

			isBuyShop = config.getString("newtype").equalsIgnoreCase("buy");
			displayName = config.getString("name");

			for (ShopItem i : shop.getItems()) {

					i.multiplyPrice(config.getDouble("pricemultiplier"));
					i.multiplyQuantity(config.getDouble("quantitymultiplier"));

			}

			return shop;

		}

}
