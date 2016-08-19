package net.hilaryoi.plugin.util.BloatShopConfigY;

import org.bukkit.Material;

public class Item {

		private Material mat;
		private short durability;
		private int amount;
		private int invSlot;
		private String displayName;

		private String priceType, rewardType;

		public String toBloat(String tab) {

			StringBuilder s = startBloat(tab, amount);

			s.append("- amount:");
			s.append(amount);

			return s.toString();

		}

		public StringBuilder startBloat(String tab, int amount) {

			StringBuilder s = new StringBuilder("- type:");
			s.append(mat.toString());

			s.append("\n");
			s.append(tab);

			if (durability != 0) {
					s.append(tab);
					s.append("\n- durability:");
					s.append(durability);

			}

			return s;

		}

		public Item(boolean isBuyShop) {

			mat = Material.DIRT;
			durability = 0;
			amount = 1;
			invSlot = -1;
			displayName = null;

			if (isBuyShop) {

					rewardType = "item";
					priceType = "money";

			} else {

					rewardType = "money";
					priceType = "item";

			}

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
			return durability;

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

		public void setPriceType(String priceType) {
			this.priceType = priceType;

		}

		public void setRewardType(String rewardType) {
			this.rewardType = rewardType;

		}

		public String getPriceType() {
			return priceType;

		}

		public String getRewardType() {
			return rewardType;

		}

}
