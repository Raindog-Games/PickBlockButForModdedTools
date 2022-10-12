package ca.raindoggames.quickpickmoddedtool.inventory;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryHelper {
	
	public PlayerInventoryHelper() {}
	
	private static int WOOD = 0;
	private static int STONE = 1;
	private static int GOLD = 2;
	private static int IRON = 3;
	private static int DIAMOND = 4;
	private static int NETHERITE = 5;
	private static int LAPIS = 6;
	private static int BRONZE = 7;
	private static int GLOWSTONE = 8;
	private static int STEEL = 9;
	private static int OSMIUM = 10;
	private static int OBSIDIAN = 11;
	
	
	// Weight which tool should be returned
	// requires silk_touch if save block
	// prioritizes fortune on break block
	// 1. highest level material
	// 2. named
	// 3. most enchantments
	// tie first best found
	public boolean selectTool(Inventory inventory, String tool, MultiPlayerGameMode gameMode, boolean silkTouch) {
		int material = 0;
		boolean named = false;
		boolean fortune = false;
		int numEnchants = 0;
		int bestIndex = -1;
		for (int i = 0; i < inventory.items.size(); ++i) {
			ItemStack curStack = inventory.items.get(i);
			String stackString = curStack.toString();
			boolean replace = false;
			// Find the best item
			if (stackString.contains(tool)) {
				// skip tool if not silk_touch
				ListTag enchantments = curStack.getEnchantmentTags();
				if (silkTouch && enchantments.toString().indexOf("silk_touch") == -1) {
					continue;
				} else if (!silkTouch && enchantments.toString().indexOf("silk_touch") > -1) {
					continue;
				}
				
				// decide best tool
				if (bestIndex == -1) {
					replace = true;
				} else {
					int curMaterial = this.matchMaterial(stackString);
					if (curMaterial > material) {
						replace = true;
					} else if (curMaterial == material && curStack.hasCustomHoverName() && !named) {
						replace = true;
					} else if (curMaterial == material && curStack.hasCustomHoverName() && (!silkTouch && enchantments.toString().indexOf("fortune") > -1 && !fortune)) {
						replace = true;
					} else if (curMaterial == material && curStack.hasCustomHoverName() && (!silkTouch && enchantments.toString().indexOf("fortune") > -1) && enchantments.size() > numEnchants) {
						replace = true;
					} else if (curMaterial == material && curStack.hasCustomHoverName() && !fortune && enchantments.size() > numEnchants) {
						replace = true;
					// Set of cases where tools are not named just enchanted
					} else if (curMaterial == material && !named && (!silkTouch && enchantments.toString().indexOf("fortune") > -1 && !fortune)) {
						replace = true;
					} else if (curMaterial == material && !named && (!silkTouch && enchantments.toString().indexOf("fortune") > -1) && enchantments.size() > numEnchants) {
						replace = true;
					} else if (curMaterial == material && !named && !fortune && enchantments.size() > numEnchants) {
						replace = true;
					}
				}
				
				if (replace) {
					material = this.matchMaterial(stackString);
					named = curStack.hasCustomHoverName();
					fortune = enchantments.toString().indexOf("fortune") > -1;
					numEnchants = enchantments.size();
					bestIndex = i;
				}
			}
		}
			
		// Place the best item in your toolbar or select it if there
		if (bestIndex > -1) {
			if (Inventory.isHotbarSlot(bestIndex)) {
				inventory.selected = bestIndex;
			} else {
				gameMode.handlePickItem(bestIndex);
			}
			return true;
		}
		return false;
	}
	
	// Selects pickarang from inventory
	public void selectPickarang(Inventory inventory, MultiPlayerGameMode gameMode) {
		boolean named = false;
		int numEnchants = 0;
		int bestIndex = -1;
		boolean flamerang = false;
		boolean silkTouch = false;
		for (int i = 0; i < inventory.items.size(); ++i) {
			ItemStack curStack = inventory.items.get(i);
			String stackString = curStack.toString();
			boolean replace = false;
			// Find the best item
			if (stackString.contains("pickarang") || stackString.contains("flamerang")) {
				ListTag enchantments = curStack.getEnchantmentTags();
				
				// decide best tool
				if (bestIndex == -1) {
					replace = true;
				} else {
					boolean isFlamerang = stackString.contains("flamerang");
					if (isFlamerang && !flamerang) {
						replace = true;
					} else if (comparePickarang(isFlamerang, flamerang) && curStack.hasCustomHoverName() && !named) {
						replace = true;
					} else if (comparePickarang(isFlamerang, flamerang) && curStack.hasCustomHoverName() && !silkTouch && enchantments.toString().indexOf("silk_touch") > -1) {
						replace = true;
					} else if (comparePickarang(isFlamerang, flamerang) && curStack.hasCustomHoverName() && enchantments.toString().indexOf("silk_touch") > -1 && enchantments.size() > numEnchants) {
						replace = true;
					// Set of cases where tools are not named just enchanted
					} else if (comparePickarang(isFlamerang, flamerang) && !named && !silkTouch && enchantments.toString().indexOf("silk_touch") > -1) {
						replace = true;
					} else if (comparePickarang(isFlamerang, flamerang) && !named && enchantments.size() > numEnchants) {
						replace = true;
					}
				}
				
				if (replace) {
					flamerang = stackString.contains("flamerang");
					named = curStack.hasCustomHoverName();
					numEnchants = enchantments.size();
					bestIndex = i;
				}
			}
		}
			
		// Place the best item in your toolbar or select it if there
		if (bestIndex > -1) {
			if (Inventory.isHotbarSlot(bestIndex)) {
				inventory.selected = bestIndex;
			} else {
				gameMode.handlePickItem(bestIndex);
			}
		}
	}
	
	// Selects atomic dissassembler from inventory
	public void selectAtomicDisassembler(Inventory inventory, MultiPlayerGameMode gameMode) {
		int bestIndex = -1;
		for (int i = 0; i < inventory.items.size(); ++i) {
			ItemStack curStack = inventory.items.get(i);
			String stackString = curStack.toString();
			// Find the best item
			if (stackString.contains("atomic_disassembler")) {
				bestIndex = i;
				break;
			}
		}
		// Place the best item in your toolbar or select it if there
		if (bestIndex > -1) {
			if (Inventory.isHotbarSlot(bestIndex)) {
				inventory.selected = bestIndex;
			} else {
				gameMode.handlePickItem(bestIndex);
			}
		}
	}
	
	private boolean comparePickarang(boolean isFlamerang, boolean flamerang) {
		if ((isFlamerang && flamerang) || (!isFlamerang && !flamerang)) {
			return true;
		}
		return false;
	}
	
	private int matchMaterial(String stackString) {
		if (stackString.contains("wood")) {
			return WOOD;
		} else if (stackString.contains("stone")) {
			return STONE;
		} else if (stackString.contains("gold")) {
			return GOLD;
		} else if (stackString.contains("iron")) {
			return IRON;
		} else if (stackString.contains("diamond")) {
			return DIAMOND;
		} else if (stackString.contains("netherite")) {
			return NETHERITE;
		} else if (stackString.contains("lapis")) {
			return LAPIS;
		} else if (stackString.contains("bronze")) {
			return BRONZE;
		} else if (stackString.contains("glowstone")) {
			return GLOWSTONE;
		} else if (stackString.contains("steel")) {
			return STEEL;
		} else if (stackString.contains("osmium")) {
			return OSMIUM;
		} else if (stackString.contains("obsidian")) {
			return OBSIDIAN;
		} else {
			return -1;
		}
	}
}
