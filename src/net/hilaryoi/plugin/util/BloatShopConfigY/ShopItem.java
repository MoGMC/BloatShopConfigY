package net.hilaryoi.plugin.util.BloatShopConfigY;

public class ShopItem {

		// itemstack only stores type and durability so dont use it lol

		double price;

		int invSlot;

		Item item;

		public ShopItem(Item item, double price, int invSlot) {

			this.item = item;
			this.price = price;
			this.invSlot = invSlot;

		}

		public String toBloat(boolean isBuyShop) {

			StringBuilder s = new StringBuilder("  ");

			s.append(item.getMaterial().toString());

			// price and reward type

			s.append(":\n    RewardType: ");

			if (isBuyShop) {
					s.append("item");

			} else {
					s.append("money");

			}

			s.append("\n    PriceType: ");

			if (isBuyShop) {
					s.append("money");

			} else {
					s.append("item");

			}

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
			s.append(invSlot);
			s.append("\n    ExtraPermission: ''\n");

			return s.toString();

		}

		public String toBloatMenu(String tab) {

			StringBuilder s = item.startBloat(tab, 1);

			s.append("- amount: 1\n");
			s.append(tab);
			s.append("- 'lore:&rAmount: ");
			s.append(item.amount);
			s.append("#Price: ");
			s.append(price);
			s.append("'");

			return s.toString();

		}

}
