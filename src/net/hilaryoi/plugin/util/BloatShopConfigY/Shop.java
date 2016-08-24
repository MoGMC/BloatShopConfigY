package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

public class Shop {

		// TODO: combine shopItem and item

		String shopId;

		boolean isBuyShop;
		String displayName;

		List<Item> items;

		boolean[] inv;

		String message;

		public Shop(String shopId, ConfigurationSection config) {

			// 54 is max
			inv = new boolean[54];

			this.shopId = shopId;

			isBuyShop = config.getString("type").equalsIgnoreCase("buy");
			displayName = config.getString("name");

			message = config.getString("message");

			items = new ArrayList<Item>();

			ConfigurationSection itemsConfig = config.getConfigurationSection("items");

			for (String itemKey : itemsConfig.getKeys(false)) {

					Item item = Util.fromMarkup(itemKey, itemsConfig.getDouble(itemKey), isBuyShop, message);

					if (item.getInvSlot() == -1) {

						for (int i = 0; i < inv.length; i++) {

								if (!inv[i]) {

									System.out.println(i);

									item.setInvSlot(i);

									break;

								}
						}

					}

					if (item.getInvSlot() == -1) {
						System.err.println("Inventory conflict: Item could not be assigned a slot. Is the inventory full? Skipping item " + itemKey);
						continue;

					}

					inv[item.getInvSlot()] = true;

					items.add(item);

			}

		}

		public String toBloat() {

			StringBuilder s = new StringBuilder("ShopName: ");

			s.append(shopId);

			s.append("\nDisplayName: '");
			s.append(displayName);
			s.append("'\nshop:\n");

			for (Item item : items) {

					s.append(item.toBloat(isBuyShop));

			}

			return s.toString();

		}

		public Shop(String newId, Shop oldShop, ConfigurationSection config, Parser parser) {

			if (oldShop == null) {
					System.err.println("Error while creating shop " + newId + ": Cannot find old shop.");
			}

			shopId = oldShop.getShopId();
			isBuyShop = oldShop.isBuyShop();
			displayName = oldShop.getDisplayName();
			inv = oldShop.getInv();

			message = oldShop.getMessage();

			if (config.contains("message")) {
					message = config.getString("message");

			}

			shopId = newId;

			isBuyShop = config.getString("newtype").equalsIgnoreCase("buy");
			displayName = config.getString("name");

			double priceMultiplier = config.getDouble("pricemultiplier");
			double quantityMultiplier = config.getDouble("quantitymultiplier");

			List<String> excluding = config.getStringList("exclude");

			if (excluding == null) {
					excluding = new ArrayList<String>();

			}

			items = new ArrayList<Item>();

			for (Item i : oldShop.getItems()) {

					if (!excluding.contains(i.getMarkup())) {
						items.add(new Item(i, isBuyShop, priceMultiplier, quantityMultiplier));

					}

			}

			ConfigurationSection modifiedItemsConfig = config.getConfigurationSection("modify");

			Set<String> modifiedItems;

			if (modifiedItemsConfig == null) {
					modifiedItems = new HashSet<String>();

			} else {
					modifiedItems = modifiedItemsConfig.getKeys(false);

			}

			for (String shopId : config.getStringList("shops")) {

					items.addAll(parser.getShop(shopId).getItems());

			}

			// packs items

			int slot = 0;

			for (int i = 0; i < items.size(); i++) {

					Item item = items.get(i);

					if (modifiedItems.contains(item.getMarkup())) {

						items.remove(i);

						item = Util.fromMarkup(modifiedItemsConfig.getString(item.getMarkup()), item.getPrice(), isBuyShop, message);

						items.add(i, item);

					}

					if (item.isRegularItem()) {

						item.setMessage(message);

						item.setInvSlot(slot);

						slot++;

					}

			}

		}

		public String getMessage() {
			return message;

		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;

		}

		public boolean isBuyShop() {
			return isBuyShop;

		}

		public void setIsBuyShop(boolean isBuyShop) {
			this.isBuyShop = isBuyShop;

		}

		public String getDisplayName() {
			return displayName;

		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;

		}

		public List<Item> getItems() {
			return items;

		}

		public void setItems(List<Item> items) {
			this.items = items;

		}

		public boolean[] getInv() {
			return inv;

		}

}
