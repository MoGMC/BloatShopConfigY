import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util {

		public ItemStack fromMarkup(String markup) {

			ItemStack item = new ItemStack(Material.DIRT, 1);

			StringBuilder b = new StringBuilder();

			for (char c : markup.toCharArray()) {

					switch (c) {

					case ';':

						item = new ItemStack(Material.getMaterial(b.toString()));
						b.setLength(0);

						break;

					case ',':

						item.setAmount(Integer.valueOf(b.toString()));
						b.setLength(0);

						break;

					case ')':

						String[] ench = b.toString().split("\\|");

						item.addEnchantment(Enchantment.getByName(ench[0]), Integer.valueOf(ench[1]));

						b.setLength(0);

						break;

					case ':':

						item.setDurability(Short.valueOf(b.toString()));
						b.setLength(0);

						break;

					case ']':

						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(colorString(b.toString()));

						item.setItemMeta(meta);

						b.setLength(0);

						break;

					default:

						b.append(c);

					}

			}

			return item;

		}

		public List<String> colorList(List<String> list) {

			ArrayList<String> colored = new ArrayList<String>();

			for (String s : list) {
					colored.add(colorString(s));

			}

			return colored;

		}

		// shorter and can change formatting to various other symbols faster if needed
		public String colorString(String string) {

			return ChatColor.translateAlternateColorCodes('&', string);

		}

}
