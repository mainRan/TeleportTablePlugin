package me.terramain.guiapi;

import org.bukkit.event.inventory.InventoryType;

public enum GUIType {
    /**
     * A chest inventory, with 0, 9, 18, 27, 36, 45, or 54 slots of type
     * CONTAINER.
     */
    CHEST0(0,InventoryType.CHEST,true),
    CHEST9(9,InventoryType.CHEST,true),
    CHEST18(18,InventoryType.CHEST,true),
    CHEST(27,InventoryType.CHEST,true),
    CHEST36(36,InventoryType.CHEST,true),
    CHEST45(45,InventoryType.CHEST,true),
    BIG_CHEST(54,InventoryType.CHEST,true),
    /**
     * A dispenser inventory, with 9 slots of type CONTAINER.
     */
    DISPENSER(9,InventoryType.DISPENSER),
    /**
     * A dropper inventory, with 9 slots of type CONTAINER.
     */
    DROPPER(9,InventoryType.DROPPER),
    /**
     * A furnace inventory, with a RESULT slot, a CRAFTING slot, and a FUEL
     * slot.
     */
    FURNACE(3,InventoryType.FURNACE),
    /**
     * A workbench inventory, with 9 CRAFTING slots and a RESULT slot.
     */
    WORKBENCH(10,InventoryType.WORKBENCH),
    /**
     * A player's crafting inventory, with 4 CRAFTING slots and a RESULT slot.
     * Also implies that the 4 ARMOR slots are accessible.
     */
    CRAFTING(5,InventoryType.CRAFTING),
    /**
     * An enchantment table inventory, with two CRAFTING slots and three
     * enchanting buttons.
     */
    ENCHANTING(2,InventoryType.ENCHANTING),
    /**
     * A brewing stand inventory, with one FUEL slot and four CRAFTING slots.
     */
    BREWING(5,InventoryType.BREWING),
    /**
     * A player's inventory, with 9 QUICKBAR slots, 27 CONTAINER slots, 4 ARMOR
     * slots and 1 offhand slot. The ARMOR and offhand slots may not be visible
     * to the player, though.
     */
    PLAYER(41,InventoryType.PLAYER),
    /**
     * The creative mode inventory, with only 9 QUICKBAR slots and nothing
     * else. (The actual creative interface with the items is client-side and
     * cannot be altered by the server.)
     */
    CREATIVE(9,InventoryType.CREATIVE),
    /**
     * The merchant inventory, with 2 CRAFTING slots, and 1 RESULT slot.
     */
    MERCHANT(3,InventoryType.MERCHANT),
    /**
     * The ender chest inventory, with 27 slots.
     */
    ENDER_CHEST(27,InventoryType.ENDER_CHEST),
    /**
     * An anvil inventory, with 2 CRAFTING slots and 1 RESULT slot
     */
    ANVIL(3,InventoryType.ANVIL),
    /**
     * A smithing inventory, with 2 CRAFTING slots and 1 RESULT slot
     */
    SMITHING(3,InventoryType.SMITHING),
    /**
     * A beacon inventory, with 1 CRAFTING slot
     */
    BEACON(1,InventoryType.BEACON),
    /**
     * A hopper inventory, with 5 slots of type CONTAINER.
     */
    HOPPER(5,InventoryType.HOPPER),
    /**
     * A shulker box inventory, with 27 slots of type CONTAINER.
     */
    SHULKER_BOX(27,InventoryType.SHULKER_BOX),
    /**
     * A barrel box inventory, with 27 slots of type CONTAINER.
     */
    BARREL(27,InventoryType.BARREL),
    /**
     * A blast furnace inventory, with a RESULT slot, a CRAFTING slot, and a
     * FUEL slot.
     */
    BLAST_FURNACE(3,InventoryType.BLAST_FURNACE),
    /**
     * A lectern inventory, with 1 BOOK slot.
     */
    LECTERN(1,InventoryType.LECTERN),
    /**
     * A smoker inventory, with a RESULT slot, a CRAFTING slot, and a FUEL slot.
     */
    SMOKER(3,InventoryType.SMOKER),
    /**
     * Loom inventory, with 3 CRAFTING slots, and 1 RESULT slot.
     */
    LOOM(4,InventoryType.LOOM),
    /**
     * Cartography inventory with 2 CRAFTING slots, and 1 RESULT slot.
     */
    CARTOGRAPHY(3,InventoryType.CARTOGRAPHY),
    /**
     * Grindstone inventory with 2 CRAFTING slots, and 1 RESULT slot.
     */
    GRINDSTONE(3,InventoryType.GRINDSTONE),
    /**
     * Stonecutter inventory with 1 CRAFTING slot, and 1 RESULT slot.
     */
    STONECUTTER(2,InventoryType.STONECUTTER),
    /**
     * Pseudo composter inventory with 0 or 1 slots of undefined type.
     */
    COMPOSTER(1,InventoryType.COMPOSTER),
    /**
     * Pseudo chiseled bookshelf inventory, with 6 slots of undefined type.
     */
    CHISELED_BOOKSHELF(6,InventoryType.CHISELED_BOOKSHELF);


    public final int slots;
    public final InventoryType inventoryType;
    public final boolean isChest;

    private GUIType(int slots, InventoryType inventoryType){
        this.slots = slots;
        this.inventoryType = inventoryType;
        this.isChest=false;
    }
    private GUIType(int slots, InventoryType inventoryType, boolean isChest){
        this.slots = slots;
        this.inventoryType = inventoryType;
        this.isChest=isChest;
    }
}
