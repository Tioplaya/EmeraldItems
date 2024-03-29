package ru.tioplaya.emeralditems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.*;

public final class EmeraldItems extends JavaPlugin implements Listener {

    private void log(String str) {
        Bukkit.getConsoleSender().sendMessage(str);
    }

    ColorizeText text = new ColorizeText();
    @Override
    public void onLoad() {
        log(GOLD + "EmeraldItems loading...");
        final File config = new File(this.getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            this.saveDefaultConfig();
        }
        Bukkit.resetRecipes();
        if ((boolean) this.getConfig().get("Helmet.enable")) {
            this.e_helmet();
        }
        if ((boolean) this.getConfig().get("Chestplate.enable")) {
            this.e_chestplate();
        }
        if ((boolean) this.getConfig().get("Leggings.enable")) {
            this.e_leggings();
        }
        if ((boolean) this.getConfig().get("Boots.enable")) {
            this.e_boots();
        }
        if ((boolean) this.getConfig().get("Sword.enable")) {
            this.e_sword();
        }
        if ((boolean) this.getConfig().get("Axe.enable")) {
            this.e_axe();
        }
        if ((boolean) this.getConfig().get("Pickaxe.enable")) {
            this.e_pickaxe();
        }
        if ((boolean) this.getConfig().get("Shovel.enable")) {
            this.e_shovel();
        }
        if ((boolean) this.getConfig().get("Hoe.enable")) {
            this.e_hoe();
        }
        if ((boolean) this.getConfig().get("Bow.enable")) {
            this.e_bow();
        }
        if ((boolean) this.getConfig().get("Rod.enable")) {
            this.e_rod();
        }
    }
    @Override
    public void onEnable() {
        log(GREEN + "EmeraldItems enabled!");
        log(DARK_GREEN + "EmeraldItems by Tioplaya");
    }

    private String Prefix = text.colorizeText((String) this.getConfig().get("Prefix"));
    private final String Not_permission_reload = text.colorizeText((String) this.getConfig().get("Not_permission"));
    public void item(Material item_id, String enchants_path, String line1, String line2, String line3, String name_path, String key_path) {
        final List<String> enchList = this.getConfig().getStringList(enchants_path);
        final HashMap<Enchantment, Integer> enchIntMap = new HashMap<>();
        for (final String str : enchList) {
            final String[] sArr = str.split(":");
            enchIntMap.put(Enchantment.getByName(sArr[0]), Integer.parseInt(sArr[1]));
        }
        if (!enchList.contains(enchIntMap)) {
            getLogger().log(Level.INFO, "item " + name_path + " enabled");
        }
        if (enchList.isEmpty()) {
            getLogger().log(Level.WARNING, "List enchants in item " + name_path + " is null. Please disable item or ignore it");
        }
        if (!item_id.isItem()) {
            getLogger().log(Level.SEVERE, "Material " + item_id + " not exist");
        }

        final ItemStack item = new ItemStack(item_id);
        final ItemMeta meta = item.getItemMeta();
        String name = text.colorizeText((String) this.getConfig().get(name_path));

        meta.setDisplayName(name);
        item.setItemMeta(meta);
        item.addEnchantments(enchIntMap);
        final NamespacedKey key = new NamespacedKey(this, key_path);
        final ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(line1, line2, line3);

        if (line1.contains("E")) {
            recipe.setIngredient('E', EMERALD); //сделать возможность добавлять сколько угодно и каких угодно
        }
        if (line2.contains("E")) {
            recipe.setIngredient('E', EMERALD);
        }
        if (line3.contains("E")) {
            recipe.setIngredient('E', EMERALD);
        }


        if (line1.contains("S")) {
            recipe.setIngredient('S', STICK);
        }
        if (line2.contains("S")) {
            recipe.setIngredient('S', STICK);
        }
        if (line3.contains("S")) {
            recipe.setIngredient('S', STICK);
        }


        if (line1.contains("W")) {
            recipe.setIngredient('W', STRING);
        }
        if (line2.contains("W")) {
            recipe.setIngredient('W', STRING);
        }
        if (line3.contains("W")) {
            recipe.setIngredient('W', STRING);
        }
        Bukkit.addRecipe(recipe);
    }
    //сделать создание такого добра свободным. сколько угодно и как угодно

public void e_helmet() { //перенести эту большую х в отдельный класс
    item(DIAMOND_HELMET,"Helmet.enchs", "EEE", "E E", "   ", "Helmet.name","emerald_helmet");
}
public void e_chestplate() {
    item(DIAMOND_CHESTPLATE, "Chestplate.enchs", "E E", "EEE", "EEE", "Chestplate.name", "emerald_chestplate");
}
public void e_leggings() {
    item(DIAMOND_LEGGINGS,"Leggings.enchs", "EEE", "E E", "E E","Leggings.name","emerald_leggings");
}
public void e_boots() {
    item(DIAMOND_BOOTS,"Boots.enchs", "   ", "E E", "E E","Boots.name","emerald_boots"); // надо шобы во всех положениях (будь сверху или снизу)
}
public void e_sword() {
    item(DIAMOND_SWORD, "Sword.enchs", " E ", " E ", " S ", "Sword.name", "emerald_sword");
}
public void e_axe() {
    item(DIAMOND_AXE, "Axe.enchs", "EE ", "ES ", " S ", "Axe.name", "emerald_axe"); //такая же тема как с ботинками
}
public void e_pickaxe() {
    item(DIAMOND_PICKAXE, "Pickaxe.enchs", "EEE", " S ", " S ", "Pickaxe.name", "emerald_pickaxe");
}
public void e_shovel() {
    item(DIAMOND_SHOVEL, "Shovel.enchs", " E ", " S ", " S ", "Shovel.name", "emerald_shovel");
}
public void e_hoe() {
    item(DIAMOND_HOE, "Hoe.enchs", "EE ", " S ", " S ", "Hoe.name", "emerald_hoe"); //такая же тема как с ботинками
}
public void e_bow() {
    item(BOW, "Bow.enchs", " EW", "E W", " EW", "Bow.name", "emerald_bow"); //такая же тема как с ботинками
}
public void e_rod() {
    item(FISHING_ROD, "Rod.enchs", "  E", " EW", "E W", "Rod.name", "emerald_rod"); //такая же тема как с ботинками
}

    public boolean onCommand(final CommandSender sender, final @NotNull Command cmd, final @NotNull String lable, final String[] args) {
        if (!sender.hasPermission("emeralditems.reload")) {
            sender.sendMessage(Prefix + " " + Not_permission_reload);
            return true;
        }
        if (args.length == 0 && sender.hasPermission("emeralditems.admin")) {
            String Help = text.colorizeText((String) this.getConfig().get("Help"));
            sender.sendMessage(Prefix + " " + Help);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("emeralditems.reload")) {
            this.reloadConfig();
            onLoad();
            String ConfigReloaded = text.colorizeText((String) this.getConfig().get("Config_reloaded"));
            sender.sendMessage(Prefix + " " + ConfigReloaded);
            return true;
        }
        if (!args[0].equalsIgnoreCase("reload") && args.length > 1) {
            String NotPermission = text.colorizeText((String) this.getConfig().get("Not_permission"));
            sender.sendMessage(Prefix + NotPermission);
            return false;
        }
        String UnknownCommand = text.colorizeText((String) this.getConfig().get("Unknown_command"));
        sender.sendMessage(Prefix + " " + UnknownCommand);
        return true;
    }

    @Override
    public void onDisable() {
        log(RED + "EmeraldItems disabled!");
    }
}
