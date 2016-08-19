package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.text.DecimalFormat;

public class ShopItem {

		// itemstack only stores type and durability so dont use it lol

		double price;

		Item item;

		static DecimalFormat decimalFormat = new DecimalFormat("0.00");

		public ShopItem(Item item, double price) {

			this.item = item;
			this.price = price;

		}

		public Item getRawItem() {
			return item;

		}

		public String toBloat(boolean isBuyShop) {

			StringBuilder s = new StringBuilder("  ");

			s.append(item.getMaterial().toString());

			// price and reward type

			s.append(":\n    RewardType: ");

			s.append(item.getRewardType());

			s.append("\n    PriceType: ");

			s.append(item.getPriceType());

			// price

			s.append("\n    Price: ");

			if (isBuyShop) {
					s.append(price);

			} else {
					s.append("\n    ");
					s.append(item.toBloat("      "));

			}

			// reward

			s.append("\n    Reward:");

			if (isBuyShop) {
					s.append("\n    - ");
					s.append(item.toBloat("      "));

			} else {
					s.append(price);

			}

			// menu item/display

			s.append("\n    MenuItem:\n    ");
			s.append(toBloatMenu("    "));
			s.append("\n    Message: ''\n    InventoryLocation: ");
			s.append(item.getInvSlot() + 1);
			s.append("\n    ExtraPermission: ''\n");

			return s.toString();

		}

		public String toBloatMenu(String tab) {

			StringBuilder s = item.startBloat(tab, 1);

			s.append("- amount: 1\n");
			s.append(tab);

			if (!isFree()) {

					s.append("- 'lore:&rAmount: ");
					s.append(item.getAmount());
					s.append("#&rPrice: $");
					s.append(decimalFormat.format(price));
					s.append("'");

			}

			return s.toString();

		}

		public double getPrice() {
			return price;

		}

		public boolean isFree() {
			return price == 0;

		}

		public void multiplyPrice(double multiplier) {
			price *= multiplier;

		}

		public void multiplyQuantity(double multiplier) {
			item.setAmount((int) (item.getAmount() * multiplier));

		}

}
