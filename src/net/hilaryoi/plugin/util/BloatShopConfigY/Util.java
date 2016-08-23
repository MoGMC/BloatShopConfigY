package net.hilaryoi.plugin.util.BloatShopConfigY;

import org.bukkit.Material;

public class Util {

		public static Item fromMarkup(String markup, double price, boolean isBuyShop) {

			Item item = new Item(price, markup, isBuyShop);

			StringBuilder b = new StringBuilder();

			for (char c : markup.toCharArray()) {

					switch (c) {

					case ';':

						item.setMaterial(Material.getMaterial(b.toString()));
						b.setLength(0);

						break;

					case ',':

						item.setAmount(Integer.valueOf(b.toString()));
						b.setLength(0);

						break;

					// case ')':

					// String[] ench = b.toString().split("\\|");

					// item.addEnchantment(Enchantment.getByName(ench[0]), Integer.valueOf(ench[1]));

					// b.setLength(0);

					// break;

					case ':':

						item.setDurability(Short.valueOf(b.toString()));
						b.setLength(0);

						break;

					case ']':

						item.setDisplayName(b.toString());
						b.setLength(0);

						break;

					case '^':

						item.setInvSlot(Integer.valueOf(b.toString()));
						b.setLength(0);

						break;

					case '$':

						item.overridePriceType(b.toString());
						b.setLength(0);

						break;

					case '*':

						item.overrideRewardType(b.toString());
						b.setLength(0);

						break;

					case '@':

						item.overrideReward(b.toString());
						b.setLength(0);

					default:

						b.append(c);

					}

			}

			return item;

		}

}
