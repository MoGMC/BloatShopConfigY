package net.hilaryoi.plugin.util.BloatShopConfigY;

import org.bukkit.Material;

public class Item {

		Material mat;
		int amount;
		short durability;
		String displayName;

		public Item() {

			mat = Material.DIRT;
			amount = 1;
			durability = 0;
			displayName = null;

		}

		public void setMaterial(Material mat) {
			this.mat = mat;

		}

		public Material getMaterial() {
			return mat;

		}

		public void setAmount(Integer amount) {
			this.amount = amount;

		}

		public void setDurability(short durability) {
			this.durability = durability;

		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;

		}

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

}
