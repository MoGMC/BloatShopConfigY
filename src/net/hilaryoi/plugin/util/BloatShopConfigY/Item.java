package net.hilaryoi.plugin.util.BloatShopConfigY;

import java.text.DecimalFormat;

import org.bukkit.Material;

public class Item {

		double price;

		String markup;

		private Material mat;
		private short durability;
		private int amount;
		private int invSlot;
		private String displayName;
		String lore;

		String message;

		private String priceType, rewardType;

		boolean isBuyShop;

		String rewardOverride;

		static DecimalFormat decimalFormat = new DecimalFormat("0.00");

		public String toBloat(boolean isBuyShop) {

			StringBuilder s = new StringBuilder("  ");
			s.append(mat.toString());

			// have to do this because buyshop lmao
			s.append(invSlot);

			// price and reward type

			s.append(":\n    RewardType: ");
			s.append(rewardType);

			s.append("\n    PriceType: ");
			s.append(priceType);

			// price

			s.append("\n    Price: ");
			appendPR(s, isBuyShop);

			// reward

			s.append("\n    Reward: ");

			if (rewardOverride != null) {
					s.append(rewardOverride.toString());

			} else {
					appendPR(s, !isBuyShop);

			}

			// menu item/display

			s.append("\n    MenuItem:\n    ");
			s.append(getBloatedMenuItem("    "));
			s.append("\n    Message: '");
			s.append(message);
			s.append("'\n    InventoryLocation: ");
			s.append(invSlot + 1);
			s.append("\n    ExtraPermission: ''\n");

			return s.toString();

		}

		public String getBloatedMenuItem(String tab) {

			StringBuilder s = getInitialItemBloat(tab, 1);

			s.append("- amount: 1");

			if (!isFree() || !lore.isEmpty()) {

					s.append("\n");
					s.append(tab);

					s.append("- 'lore:");

					if (!lore.isEmpty()) {

						s.append(lore);

					}

					if (!isFree()) {

						s.append("&rAmount: ");
						s.append(amount);
						s.append("#&rPrice: $");
						s.append(decimalFormat.format(price));

					}

					s.append("'");

			}

			return s.toString();

		}

		public String getBloatedItem(String tab) {

			StringBuilder s = new StringBuilder("- ");

			s.append(getInitialItemBloat(tab, amount));

			s.append("- amount:");
			s.append(amount);

			return s.toString();

		}

		public StringBuilder getInitialItemBloat(String tab, int amount) {

			StringBuilder s = new StringBuilder("- type:");
			s.append(mat.toString());

			s.append("\n");
			s.append(tab);

			if (displayName != null) {

					s.append("- 'name:");
					s.append(displayName);
					s.append("'\n");
					s.append(tab);

			}

			if (durability != 0) {

					s.append("- durability:");
					s.append(getDurability());
					s.append("\n");
					s.append(tab);

			}

			return s;

		}

		public Item(double price, String markup, boolean isBuyShop) {

			this.price = price;

			this.markup = markup;

			this.message = "";

			mat = Material.DIRT;
			durability = -1;
			amount = 1;
			invSlot = -1;
			displayName = null;
			lore = "";

			this.isBuyShop = isBuyShop;

			rewardOverride = null;

			if (isBuyShop) {

					rewardType = "item";
					priceType = "money";

			} else {

					rewardType = "money";
					priceType = "item";

			}

		}

		public Item(Item oldItem, boolean isBuyShop, double priceMultiplier, double quantityMultiplier) {

			this.markup = oldItem.getMarkup();

			this.mat = oldItem.getMaterial();
			this.durability = oldItem.getDurability();
			this.amount = oldItem.getAmount();
			this.invSlot = oldItem.getInvSlot();
			this.displayName = oldItem.getDisplayName();
			this.lore = oldItem.getLore();
			this.message = oldItem.getMessage();

			priceType = oldItem.getPriceType();
			rewardType = oldItem.getRewardType();

			rewardOverride = oldItem.getRewardOverride();

			if (isBuyShop != oldItem.isBuyShop()) {

					if (oldItem.getPriceType().equals("money") && oldItem.getRewardType().equals("item")) {

						priceType = "item";
						rewardType = "money";

					} else if (oldItem.getPriceType().equals("item") && oldItem.getRewardType().equals("money")) {

						priceType = "money";
						rewardType = "item";

					}

					this.isBuyShop = isBuyShop;

			}

			price = oldItem.getPrice() * priceMultiplier;

			amount = (int) (oldItem.getAmount() * quantityMultiplier);

		}

		public boolean isRegularItem() {

			if ((priceType.equals("money") && rewardType.equals("item")) || (priceType.equals("item") && rewardType.equals("money"))) {
					return true;

			} else {
					return false;

			}

		}

		// pr = price/reward
		public void appendPR(StringBuilder s, boolean reward) {

			if (reward) {
					s.append(price);

			} else {
					s.append("\n    ");
					s.append(getBloatedItem("      "));

			}

		}

		public String getMessage() {
			return message;

		}

		public void setMessage(String message) {
			this.message = message;

		}

		public boolean isFree() {
			return price == 0;

		}

		public double getPrice() {
			return price;

		}

		public String getMarkup() {
			return markup;

		}

		public Material getMaterial() {
			return mat;

		}

		public void setMaterial(Material mat) {
			this.mat = mat;

		}

		public int getAmount() {
			return amount;

		}

		public void setAmount(int amount) {
			this.amount = amount;

		}

		public short getDurability() {

			if (durability == -1) {
					return 0;

			} else {
					return durability;

			}

		}

		public void setDurability(short durability) {
			this.durability = durability;

		}

		public String getDisplayName() {
			return displayName;

		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;

		}

		public int getInvSlot() {
			return invSlot;

		}

		public void setInvSlot(int invSlot) {
			this.invSlot = invSlot;

		}

		public boolean isBuyShop() {
			return isBuyShop;

		}

		public String getLore() {
			return lore;

		}

		public void overridePriceType(String priceType) {
			this.priceType = priceType;

		}

		public void overrideRewardType(String rewardType) {
			this.rewardType = rewardType;

		}

		public String getPriceType() {
			return priceType;

		}

		public String getRewardType() {
			return rewardType;

		}

		public String getRewardOverride() {
			return rewardOverride;

		}

		public void overrideReward(String rewardOverride) {
			this.rewardOverride = rewardOverride;

		}

		public void setLore(String lore) {
			this.lore = lore;

		}

}
