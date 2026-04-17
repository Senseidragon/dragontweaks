

---
# File: MinecoloniesWiki\src\content\wiki\buildings\alchemist.mdoc

---
type: building
id: alchemist
name: Alchemist Laboratory
plural: Alchemist Laboratories
description: The Alchemist Laboratory is where your workers can brew potions, harvest netherwart and harvest mistletoe.
workers:
  - alchemist
rotation: 90
---

The {% building /%} is where the {% worker /%} works. The {% worker /%} brews [potions](https://minecraft.wiki/w/Potion), harvests Nether Wart and {% item name="minecolonies/mistletoe" /%}.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_brewing_recipes order=2 /%}
{% building_gui_content_block_tasks order=3 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\archery.mdoc

---
type: building
id: archery
name: Archery
plural: Archeries
description: The Archery is where your Archers in training will train to become fully fledged Archers.
workers:
  - archer
rotation: 270
---

The {% building /%} is where your {% worker plural=true /%} in Training will train to become {% worker /%}.
This also allows them to level up without a risk of dying to mobs. A new {% worker /%} in Training will need a bed in a house in order to spawn.
However, once they are hired at the {% building /%}, that becomes their new residence and the bed in the house will open up for another new citizen (child or recruit).

The number of students that can be trained at a time depends on the level of the {% building /%}. 

| Building Level | Max # of Students |
| -------------- | ----------------- |
| 1              | 1                 |
| 2              | 2                 |
| 3              | 3                 |
| 4              | 4                 |
| 5              | 5                 |

The {% worker plural=true /%} in Training require a bow to practice. They will shoot at the practice targets. Practice targets are the {% item name="minecraft/target" /%} blocks.
While shooting, they will try to stand at a place designated either by glowstone or by any block specially tagged as a standing point (tagging any block means that glowstone is ignored).

The {% worker plural=true /%} in Training are not actual Guards even though they will be dressed as {% worker plural=true /%}. They will not help defend the colony.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\baker.mdoc

---
type: building
id: baker
name: Bakery
plural: Bakeries
description: The Bakery will make several products like bread, cookies, pastries, pies and so forth.
workers:
  - baker
rotation: 270
---

The {% building /%} will craft bread dough, cookie dough, cake dough, and raw pumpkin pie, then bake these in a furnace to create bread, cookies, cakes, and pumpkin pies. They will only do this upon request, whether from the {% worker name="cook" /%}, the {% item name="minecolonies/blockpostbox" /%}, or as a minimum stock in the {% building name="warehouse" /%}.

The {% worker /%} can also craft some non-vanilla breads:

- Sweet bread, made from wheat and a honey bottle. Available at {% building /%} level 3. Has slightly higher saturation than normal bread, also gives you a speed boost and removes poison.
- Milk-infused bread, made from wheat and a milk bucket. Available at {% building /%} level 4. Removes all potion effects (like milk buckets do).
- Golden bread, made from wheat and a gold ingot. Available at {% building /%} level 5. Instantly heals 2 hearts.
- Chorus bread, made from wheat and a chorus fruit. Available after completing the Know the End research in the {% building name="university" /%}. Has higher saturation than normal bread and teleports you to the surface after eating it.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_smelting_recipes order=3 /%}
{% building_gui_content_block_stock order=4 /%}
{% building_gui_content_block_fuel order=5 /%}
{% building_gui_content_block_tasks order=6 /%}
{% building_gui_content_block_settings order=7 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\barracks.mdoc

---
type: building
id: barracks
name: Barracks
plural: Barracks
description: The Barracks is the ultimate protection for your colony. The Barracks consists of multiple towers that house up to 5 Guards, allowing you to get your military up and running fast.
workers:
  - archer
  - druid
  - knight
rotation: 270
---

The {% building /%} is the ultimate protection for your colony. Each {% building /%} can hold multiple {% building name="barrackstower" /%} within its structure. The {% building name="barrackstower" /%} (unlike the normal {% building name="guardtower" /%}) will employ *and* house one Guard for every level built, for a total of 5 Guards per {% building name="barrackstower" /%}! Each new Guard will need a bed in a {% building name="residence" /%} in order to spawn. However, once they are hired at the {% building name="barrackstower" /%}, that becomes their new residence and the bed in the house will open up for another new citizen. Currently, all official-style {% building /%} contain 4 {% building name="barrackstower" /%} for a total of 20 Guards per {% building /%} for your colony. However, custom styles can have more or fewer than 4 {% building name="barrackstower" /%}. Colonists like feeling safe, so building {% building /%} close to colonists' work and homes can improve their [happiness](/wiki/systems/happinessandsaturation).

| Building Level | Max # of {% building name="barrackstower" /%} | Max Level of {% building name="barrackstower" /%} |
| -------------- | --------------------------------------------- | ------------------------------------------------- |
| 1              | 1                                             | 1                                                 |
| 2              | 2                                             | 2                                                 |
| 3              | 3                                             | 3                                                 |
| 4              | 4                                             | 4                                                 |
| 5              | 4                                             | 5                                                 |

> **Note:** The {% building /%} has slightly higher border expansion than other buildings. See the [border system](/wiki/systems/border) page for more information.

## Interface

{% building_gui_content_block_custom order=1 header="main interface" imageKey="main" imageAlt="Main" %}
- Header:
    - **Building Name**: Shows the name of the building, including the level of the building.
    - **Pencil**: Allows you to rename the building. The level of the building will always be listed after the name.
    - **Current Barbarian Position and Last Barbarian Spawn**: A tracker system for Barbarians. **Note:** you can only see the current barbarian position if you have **hired spies** (see below) during the current raid.
- Controls:
    - **Build Options**: Lets you create a build, upgrade, or repair build order for this hut. To learn more about the building system, please visit the {% worker name="builder" /%} page.
    - **Hire Spies**: This option is only available after the hut is level 3. Here you can hire spies during raids.
- Footer:
    - **Info Button**: Some huts have an in-game guide. Press the ? button to access it.
    - **Inventory**: Here you can access the hut block’s storage, where the worker at this hut takes and deposits materials. They will also use any racks that were placed in the hut when it was built or upgraded, so be sure to check those as well!
    - **Chest Icon**: Click this button to see all the items in the hut’s storage (including the hut block’s inventory and any racks that came with the hut). Clicking the ? button next to an item’s count will highlight the storage container it’s in.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_custom order=2 header="hiring spies" imageKey="hiringspies" imageAlt="Hiring spies" %}
Before each raid, you are able to hire spies for 5 gold per raid, these spies will go out into the world and if barbarians are spotted, the spies will tell you exactly where the barbarians are.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\barrackstower.mdoc

---
type: building
id: barrackstower
name: Barracks Tower
plural: Barracks Towers
description: The Barracks Towers are part of the Barracks and can employ up to 5 Guards.
workers:
  - archer
  - druid
  - knight
rotation: 270
---

The {% building /%} will employ and house one Guard for every level built (unlike the normal {% building name="guardtower" /%}, which can only have 1 Guard at a time). Each new Guard will need a bed in a house in order to spawn. However, once they are hired at the {% building /%}, that becomes their new residence and the bed in the house will open up for another new citizen (child or recruit).

| Building Level | Max # of Guards |
| -------------- | --------------- |
| 1              | 1               |
| 2              | 2               |
| 3              | 3               |
| 4              | 4               |
| 5              | 5               |

The {% building /%} locations are predetermined by the {% building name="barracks" /%} that you choose. They are placed in specific locations to fit within the {% building name="barracks" /%}. 

The maximum level of the {% building /%} is the same as the {% building name="barracks" /%}.

Guard(s) will patrol a set distance around their tower, which is based on their tower's level.

| Tower Level | Max Patrol Distance |
| ----------- | ------------------- |
| 1           | 80 blocks           |
| 2           | 110 blocks          |
| 3           | 140 blocks          |
| 4           | 170 blocks          |
| 5           | 200 blocks          |

> **Note:** If you place {% building name="barracks" /%}/{% building /%} near your colony border and level them up, your border will [expand](/wiki/systems/border).

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_custom order=2 header="selection tools" imageKey="guardtool" imageAlt="Guard tool" %}
Click the **Obtain Tools** button to get the Guard Scepter. Right-clicking on a block with the Guard Scepter will set it as a guard spot or a patrol point.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_hostiles order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Task:** This is where you can choose if you want the guard to patrol, follow, or guard.
  - **Patrol:** The guard will patrol an area you designate in **Patrol Settings**.
  - **Guard:** You can set one area for the guard to stay in. When you click "Set Target", you will be taken to the **Selection Tools** tab, where you can get the the Guard Scepter and designate the guard location.
  - **Follow:** The guard will follow you around as your personal bodyguard, protecting you or fighting alongside you. They will even go outside the colony when following! You can designate how they follow you in **Follow Settings**.
- **Retreat on low health:** Here you can choose if the Guard will attempt to retreat when they have low health.
- **Hire Trainee:** If there is a vacancy at this tower, a new {% worker name="knight" /%} or {% worker name="archer" /%} can be hired from the respective training facility ({% building name="combatacademy" /%} for {% worker name="knight" plural=true /%} and {% building name="archery" /%} for {% worker name="archer" /%}) instead of an unemployed colonist. This setting only matters if Assign Colonists to Jobs is turned to Automatic in the {% building name="townhall" /%} GUI.
- **Patrol Mode:** This is where you can choose how you want the guard to patrol. Only available when the **Task** is set to **Patrol**.
  - **Automatic:** The guard will patrol from hut to hut and back to their tower. They will only patrol huts within the patrol range of their tower.
  - **Manual:** You can set the patrol route when you click on **Set Positions**. You will be taken to the **Selection Tools** tab, where you can get the the Guard Scepter and designate patrol positions for the guard to patrol between. To delete patrol positions, simply get a new Guard Scepter and click a new patrol position. The old ones should disappear.
- **Follow Mode:** This is where you can choose how you want the guard to follow you. Only available when the **Task** is set to **Follow**.
  - **Loose Grouping:** The guard will stay a decent range away from you.
  - **Tight Grouping:** The guard will stay relatively close to you.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_stock order=5 /%}
{% building_gui_content_block_statistics order=6 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\beekeeper.mdoc

---
type: building
id: beekeeper
name: Apiary
plural: Apiaries
description: The Apiary is where you keep your bees, the Beekeeper is responsible for breeding your bees whilst also harvesting their honey and honeycombs.
workers:
  - beekeeper
rotation: 270
---

The {% building /%} is where the {% worker /%} works. The {% worker /%} breeds bees and harvests honeycombs or honey bottles, you can
choose what you want to harvest in the settings of the building.

The level of the {% building /%} determines the max number of hives the {% worker /%} can take care of:

| Building Level | Max Number of Hives |
| -------------- | ------------------- |
| 1              | 1                   |
| 2              | 2                   |
| 3              | 4                   |
| 4              | 8                   |
| 5              | 16                  |

> **Note:** If the {% worker /%} is asking for hives but there are some nearby, make sure you've set the hives for them to
take care of with the hive tool. This tool is accessed from the second page of the {% building /%} GUI (see below).

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed bees.
- **Honeycombs or Honey:** Here you choose if the {% worker /%} will collect honeycombs, honey bottles, or both. They will collect whatever shows on the GUI.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_item_list order=4 header="flowers list" imageKey="flowers" imageAlt="Flower list" %}
Here you designate what flowers you want the {% building /%} to request and use during breeding.
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_custom order=5 header="hive tool" imageKey="hivetool" imageAlt="Hive tool" %}
Click this button to get the hive tool, which is how you select which hives the {% worker /%} will take care of. To select a hive, right-click on it with the hive tool. Right-click on a hive again to remove it.
{% /building_gui_content_block_custom %}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\blacksmith.mdoc

---
type: building
id: blacksmith
name: Blacksmith's Hut
plural: Blacksmith Huts
description: The Blacksmith is responsible for creating tools and armor of any material for your colonists.
workers:
  - blacksmith
rotation: 270
---

The {% building /%} is a 3x3 crafter and can make any vanilla tools, armor, swords, and shields (no bows or redstone items). The {% building /%} will work when they receive a request for any of those items from another worker. 

> **Note:** You will need to teach the {% building /%} the recipes of the items you want them to create. The number of items the {% building /%} can learn are listed below:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

Additionally, upon reaching level 5, the {% building /%} learns the nine netherite recipes (shovel, hoe, pickaxe, axe, sword, helmet, chestplate, leggings, and boots), which count toward the recipe total above.

When a colonist is requesting a tool from the {% building /%} with multiple accepted levels, the {% worker /%} will craft whichever tool type is highest in their list of recipes that they have the materials for (when you teach them a new recipe, it'll go on the bottom).

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\builder.mdoc

---
type: building
id: builder
name: Builder's Hut
plural: Builder Huts
description: The Builder is the most vital building in your colony, the Builder will construct any other building and is the first building you need in your colony.
workers:
  - builder
rotation: 90
---

### Before you build *any* other building, you must build the {% building /%}. If the {% building /%} is not built, the {% worker /%} cannot build other buildings.

Before you choose a place to build the {% building /%}, take into account the distances among the other possible building sites and obstacles like water, trees, caves, mountains, lava sources, etc. After you have selected a place for the hut, you have to craft the {% building /%} block and place it with your {% item name="structurize/sceptergold" /%}. Once the hut is placed, the {% worker /%} will be automatically assigned (or you can manually assign one with the best [traits](/wiki/systems/worker) for a {% worker /%} if you changed this in the settings tab in the {% building name="townhall" /%}).

Now you will have to issue the build assignment so the {% worker /%} can build their own hut first. The {% worker /%} will ask for the materials they need. Make sure to check the {% item name="minecolonies/resourcescroll" /%} or the Required Resources tab of the {% building /%} GUI to see what materials the {% worker /%} is requesting for any build/upgrade. Any material in the list that is still missing will be in red letters.

Once the {% building /%} is built you can now build anything you want, like worker huts, buildings, decorations, or your own schematics.

- **Note:** The {% worker /%} can only build or upgrade any other hut up to the level of their own hut. So, in order for the {% worker /%} to upgrade any building, the {% building /%} must be upgraded first. Then the {% worker /%} will be able to upgrade any other building(s).

### Hints and Tips

For the placement of the {% building /%}, you should consider having the hut in the middle of where you plan to have the rest of your buildings so that the {% worker /%} has less of a distance to walk between their hut and the build sites.

The {% worker /%} will not start another build assignment until they have finished the current one.

You can go into the {% building name="townhall" /%} it's GUI and click on the work orders tab to cancel builds as well as arrange the priorities of the other build orders you have there. If you cancel a work order and it was being built already, if you assign the build order again, the {% worker /%} will continue where they left off.

If the {% worker /%} removes a block while building and/or upgrading, they will keep it in their inventory and dump any items in their inventory at the end of a build into the {% building /%} inventory.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_required_resources order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Task Assignment Mode:** Here you can set your {% worker /%} to Manual or Automatic mode (Automatic by default).
  - **Automatic:** The {% worker /%} chooses which build order they'll complete next themselves (based on the order of the build requests in the {% building name="townhall" /%} GUI's work orders tab).
  - **Manual:** You choose their next build order yourself by clicking Select next to the build order's name.
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the {% building name="warehouse" /%} first to see what resource you have more of before deciding what recipe it will use.
- **Construction Strategy:** This is unlocked by researching {% research_link name="minecolonies/technology/buildermodes" /%} in the {% building name="university" /%}. This allows you to change how the {% worker /%} builds, reducing pathfinding and speeding up builds (especially on large builds). Any one of these can be set in the [Structurize](/wiki/dependencies/structurize) config, but once the {% research_link name="minecolonies/technology/buildermodes" /%} research is done in the {% building name="university" /%}, the one set here takes precedence.
  - **Default:** The default row-by-row pattern.
  - **Hilbert:** Hilbert does [this pattern](https://en.wikipedia.org/wiki/Hilbert_curve), with a little difference to work for rectangular areas.
  - **Inward Circle:** Blocks are placed like a square spiral from the outside in.
  - **Inward Circle Height 1-4**: Same as `inwardcircle`, but configurable to go X amount of blocks up as well.
  - **Random:** Blocks are placed in an entirely random order. Note that this slows down builds.
- **Use Shears:** Whether the {% worker /%} will use shears to harvest certain blocks like leaves or grass.
- **Fill Block:** Here you can select what block the {% worker /%} uses to fill in holes/gaps in the schematic. The default is the dirt block.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_work_orders order=5 /%}
{% building_gui_content_block_stock order=6 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\chickenherder.mdoc

---
type: building
id: chickenherder
name: Chicken Farmer's Hut
plural: Chicken Farmer Huts
description: The Chicken Farmer is responsible for herding your chickens.
workers:
  - chickenherder
rotation: 90
---

The {% building /%} Hut is where the {% worker /%} will raise chickens, collect eggs, and butcher chickens for food. You will have to capture and bring in two chickens to the {% building /%}, as the {% worker /%} will not catch and bring in any chickens.

> **Note:** The {% worker /%} will only keep two chickens alive per hut level, so at level 5 they will have 10 chickens in their holding pens to breed and butcher. This means they will be faster at producing and collecting meat, drops, and other byproducts, like eggs. So:

| Building Level | Chickens Housed |
| -------------- | --------------- |
| 1              | 2               |
| 2              | 4               |
| 3              | 6               |
| 4              | 8               |
| 5              | 10              |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed (and consequently kill) chickens.
- **Feeding:** On by default. Here you can choose if the {% worker /%} will feed baby animals, to let them grow up faster.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\combatacademy.mdoc

---
type: building
id: combatacademy
name: Combat Academy
plural: Combat Academies
description: The Combat Academy is where your Knights in training will train to become fully fledged Knights.
workers:
  - knight
rotation: 270
---

The {% building /%} is where your {% worker plural=true /%} in Training will train to become {% worker /%}. This also allows them to level up without a risk of dying to mobs. A new Knight in Training will need a bed in a house in order to spawn. However, once they are hired at the {% building /%}, that becomes their new residence and the bed in the house will open up for another new citizen (child or recruit).

The number of students that can be trained at a time depends on the level of the building. 

| Building Level | Max # of Students |
| -------------- | ----------------- |
| 1              | 1                 |
| 2              | 2                 |
| 3              | 3                 |
| 4              | 4                 |
| 5              | 5                 |

The {% worker plural=true /%} in Training require a sword and shield to practice. They will attack the practice dummies, a pumpkin on top of a bale of hay. 

The {% worker plural=true /%} in Training are not actual guards even though they will be dressed as {% worker /%} Guards. They will not help defend the colony.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\composter.mdoc

---
type: building
id: composter
name: Composter's Hut
plural: Composter Huts
description: The Composter is responsible for recycling organic materials into compost, which can be used by Farms, Plantations and Florists to more efficiently work.
workers:
  - composter
rotation: 270
---

The {% building /%} is where the {% worker /%} will turn organic materials into {% item name="minecolonies/barrel_block" /%} to make {% item name="minecolonies/compost" /%} or dirt.

> **Note:** The higher the level of the {% building /%}, the more compost barrels the {% worker /%} will be able to use. So:

| Building Level | {% item name="minecolonies/barrel_block" /%} |
| -------------- | -------------------------------------------- |
| 1              | 1                                            |
| 2              | 2                                            |
| 3              | 3                                            |
| 4              | 4                                            |
| 5              | 5                                            |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_item_list order=2 header="items to compost" imageKey="compost" imageAlt="Items to compost" %}
Here you will see a list of all the items that were recognized for the {% building /%} to use, including modded items.
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_settings order=3 %}
- **Produce Dirt:** Turning this on will make {% item name="minecraft/dirt" /%} instead of {% item name="minecolonies/compost" /%}. It also occassionally produces {% item name="minecraft/podzol" /%}.
- **Min Warehouse Stock:** This is the minimum number of an item to be left in the {% building name="warehouse" /%} before the hut will request and compost other items. The default is 16.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\concretemixer.mdoc

---
type: building
id: concretemixer
name: Concrete Mixer's Hut
plural: Concrete Mixer Huts
description: The Concrete Mixer is responsible for creating concrete powder and concrete in your colony.
workers:
  - concretemixer
rotation: 270
---

The {% building /%} will craft all types of concrete powder and place them in flowing water (built in to their hut), then mine the resulting concrete. The {% building /%} will only make concrete and concrete powder when they receive a request for a block and have the needed materials. (All their recipes are pretaught.)

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\cook.mdoc

---
type: building
id: cook
name: Restaurant
plural: Restaurants
description: The Restaurant will ensure that your citizens are well fed, make sure you keep them supplied with food so that they can keep your colonist fed.
workers:
  - cook
rotation: 270
overrides:
  - version: "1.20"
    name: Dining Hall
    plural: Dining Halls
    description: The Dining Hall will ensure that your citizens are well fed, make sure you keep them supplied with food so that they can keep your colonist fed.
---

The {% building /%} is where the {% worker /%} will cook food, provided they have the necessary ingredients and fuel. When citizens are hungry, they will come to the {% building /%} and the {% worker /%} will give them food.
The {% worker /%} is only able to smelt food into it's cooked form. The {% worker name="chef" /%} is responsible for crafting ingredients and dishes in the {% building name="kitchen" /%}.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_fuel order=2 /%}
{% building_gui_content_block_item_list order=3 header="food" imageKey="food" imageAlt="Food list" defaultOn=true %}
Listed here are food items that will be handed out by the {% worker /%}. Disable any you don't want to be handed out or produced.
However, if a colonist finds a disabled item, they'll still eat it - the {% worker /%} just won't hand it out.

The background color indicates the quality of the food. Gold is the best food served for the colonists, silver is the next best followed by bronze. Food with a white background is non minecolonies food and has a negative impact on happiness and gives worse nutrition.

The cook in the {% building /%} is only able to cook the most basic food. The more advanced recipes can be baked by the {% worker name="baker" /%}. For the best food you will need to build a {% building name="kitchen" /%}
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_stock order=4 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\cowboy.mdoc

---
type: building
id: cowboy
name: Cowhand's Hut
plural: Cowhand Huts
description: The Cowhand is responsible for herding your cows.
workers:
  - cowboy
rotation: 180
---

The {% building /%} is where the {% worker /%} will breed, butcher, and milk (if you have the option enabled) cows for food and leather. You will have to capture and bring in two cows to the {% building /%}, as the {% worker /%} will not catch and bring in any cows.

> **Note:** The {% worker /%} will only keep two cows alive per hut level, so at level 5 they will have 10 cows in their holding pens to breed and butcher. This means they will be faster at producing and collecting meat and leather. So:


| Building Level | Cows Housed |
| -------------- | ----------- |
| 1              | 2           |
| 2              | 4           |
| 3              | 6           |
| 4              | 8           |
| 5              | 10          |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed (and consequently kill) cows.
- **Feeding:** On by default. Here you can choose if the {% worker /%} will feed baby animals, to let them grow up faster.
- **Milk attempts:** How often, per the given amount of days, the {% worker /%} will be allowed to milk cows.
- **Stew attempts:** How often, per the given amount of days, the {% worker /%} will be allowed to milk mooshrooms for mushroom stew.
- **Per days:** Per how many days the above two values should reset.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\crusher.mdoc

---
type: building
id: crusher
name: Crusher's Hut
plural: Crusher Huts
description: The Crusher will crush certain blocks into other blocks, like cobblestone into gravel, allowing your colony access to new materials.
workers:
  - crusher
rotation: 180
---

The {% building /%} is where the {% worker /%} will take items and crush them into other blocks. The defaults are: 

| Starting Item | Created Item                 |
| ------------- | ---------------------------- |
| Bone          | Bonemeal                     |
| Bone Block    | Bonemeal                     |
| Cobblestone   | Gravel (chance to get flint) |
| Clay          | Clay Ball                    |
| Gravel        | Sand                         |
| Sand          | Clay                         |

> **Note:** By default the {% worker /%} their ratio is 2:1, but by unlocking the {% research_link name="minecolonies/technology/gildedhammer" /%} research in the {% building name="university" /%} you can make them work on a 1:1 ratio.

The higher the level of the {% building /%}, the more daily output the {% worker /%} can handle. So:

| Building Level | Daily Max |
| -------------- | --------- |
| 1              | 16        |
| 2              | 64        |
| 3              | 144       |
| 4              | 256       |
| 5              | 999       |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
- **Item:** Here you can set what item you want the {% worker /%} to make. (See the above lists.)
- **Daily Limit:** This setting allows you to further limit the number of blocks that can be crushed in a day. The maximum value is set by the hut level (see above).
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\deliveryman.mdoc

---
type: building
id: deliveryman
name: Courier's Hut
plural: Courier Huts
description: The Courier is a vital building to the automation of your colony, Couriers are employed by the Warehouse to ensure items are brought around your colony.
workers:
  - courier
rotation: 270
---

The {% worker /%} runs back and forth from the {% building name="warehouse" /%} to all the worker huts in your colony, delivering materials to workers and putting finished products in the {% building name="warehouse" /%}. Each {% worker /%} needs their own hut, and you can have up to 10 {% worker plural=true /%} per {% building name="warehouse" /%}, depending on the {% building name="warehouse" /%} its level (2 per {% building name="warehouse" /%} level).

The level of the {% building /%} will dictate how many items the {% worker /%} can deliver, so if you want them to carry more materials/tools, upgrade their hut. Upgrading the hut will also increase the amount of requests they can keep track of at a time.

| Building Level | Max Stacks Carried |
| -------------- | ------------------ |
| 1              | 2                  |
| 2              | 3                  |
| 3              | 4                  |
| 4              | 5                  |
| 5              | Unlimited          |


The greater a {% worker /%} its Agility skill, the faster they'll run. The greater their Adaptability skill, the more huts they can visit before going back to the {% building name="warehouse" /%}.

> **Note:** You **must** build the {% building name="warehouse" /%} to at least level 1 so the {% worker /%} can do their work.

> **Note:** If you have multiple {% building name="warehouse" plural=true /%}, {% worker plural=true /%} will only see the items in the {% building name="warehouse" /%} they are assigned to.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_tasks order=2 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\dyer.mdoc

---
type: building
id: dyer
name: Dyer's Hut
plural: Dyer Huts
description: The Dyer is able to turn blocks like wool into their dyed variants.
workers:
  - dyer
rotation: 180
---

The {% building /%} is where the {% worker /%} will craft dyes and dye other items, including red nether bricks and dark prismarine. They won't dye concrete or concrete powder, though. The {% worker /%} will only do this if they receive a request from another worker and have the necessary materials. The {% building /%} automatically knows how to make green dye and red sand, but you must teach it the recipes for the other dyes and the dyed items.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

> **Note:** The higher the level of the {% building /%}, the more furnaces the {% worker /%} will have available. The number of furnaces they *use* depends on their Creativity level. So:

| Building Level | Furnaces |
| -------------- | -------- |
| 1              | 1        |
| 2              | 2        |
| 3              | 3        |
| 4              | 4        |
| 5              | 5        |

> **Note:** The higher the {% worker /%} their Dexterity level, the faster things will smelt. At high levels, they can go *much* faster than the player!

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_smelting_recipes order=3 /%}
{% building_gui_content_block_fuel order=4 /%}
{% building_gui_content_block_tasks order=5 /%}
{% building_gui_content_block_settings order=6 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\enchanter.mdoc

---
type: building
id: enchanter
name: Enchanter's Tower
plural: Enchanter Towers
description: The Enchanter will create enchanted books using ancient tomes and experience. The Enchanter automatically goes around your colony collecting experience from other citizens.
workers:
  - enchanter
rotation: 270
---

The {% building /%} is where the {% worker /%} will create enchanted books, as long as they have {% item name="minecolonies/ancienttome" /%}. The {% worker /%} will observe other workers throughout their daily routines in order to collect experience to create enchanted books. The higher their Mana level, the more XP they will collect per trip. They will *not* apply the enchanted books to tools and armor, you must do that yourself. {% worker plural=true /%} do not take XP from workers, they simply gain it by observing.

The higher the level of the {% building /%}, the higher the level of the enchanted books the {% worker /%} will produce (the {% worker /%} their Knowledge level also contributes to this). So:

| Building Level | Enchantment Level | Odds |
| -------------- | ----------------- | ---- |
| 1              | 1                 | 50   |
| 2              | 2                 | 25   |
| 3              | 3                 | 15   |
| 4              | 4                 | 5    |
| 5              | 5                 | 1    |

The {% worker /%} can also craft some magical {% item_page name="scrolls" /%} upon request:

- The Ultrasafe Colony Teleport Scroll, crafted with 3 paper, a compass, and the {% item name="structurize/sceptergold" /%}. (Outputs 3 scrolls.)
- The Ultrasafe Colony Group Teleport Scroll, crafted with 3 Ultrasafe Colony Teleport Scrolls. The {% building /%} must be at least level 2 for the {% worker /%} to craft this scroll.
- The Spatial Guard Reinforcements Scroll, crafted with 1 Ultrasafe Colony Teleport Scroll, 5 lapis lazuli, 1 ender pearl, and 1 paper. (Outputs 2 scrolls.) The More Scrolls [research](/wiki/systems/research) in the {% building name="university" /%} must be completed for the {% worker /%} to craft this scroll.
- The Worker-Where-Are-You Scroll, crafted with 1 Ultrasafe Colony Teleport Scroll, 6 glowstone dust, and 2 paper. (Outputs 5 scrolls.) The More Scrolls research in the {% building name="university" /%} must be completed for the {% worker /%} to craft this scroll.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_stock order=3 /%}
{% building_gui_content_block_custom order=4 header="gather targets" imageKey="targets" imageAlt="Gather targets" %}
Here you can set which workers the {% worker /%} will collect XP from, and how far that worker is from the tower.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\farmer.mdoc

---
type: building
id: farmer
name: Farmer's Hut
plural: Farmer's Huts
description: The Farm is one of the main sources of food production for your colony, farms are able to grow any root crop that has to be placed on farmland.
workers:
  - farmer
rotation: 0
---

The {% building /%} is where the {% worker /%} will grow crops for your colony. The crops the {% worker /%} currently cultivates are wheat, carrots, potatoes, beets, melons, pumpkins, and most crops from other mods (as long as they have normal growth behavior). Before the {% worker /%} can start, you will have to give the {% worker /%} a hoe, an axe (for harvesting crops), and the crop you want to cultivate. 

The {% worker /%} will also craft seeds, carved pumpkins, hay bales and coarse dirt. They will only make items when they have been taught the recipes, receive a request for an item, and have the needed materials.

> **Note:** The {% worker /%} can only learn a set number of recipes based on their hut level. So:

| Building Level | Recipes |
| -------------- | ------- |
| 1              | 10      |
| 2              | 20      |
| 3              | 40      |
| 4              | 80      |
| 5              | 160     |

For the {% worker /%} to start, you will also need to place fields. Place the Field block (it looks like a scarecrow) in the plot of farmland you want the {% worker /%} to work on and right-click on it to access its GUI. Here you will place the seed of the crop you want this specific field to cultivate. (For potatoes, carrots, and other plants without seeds, just put the raw potato/carrot/etc in the Field.) If you decide later to change the type of crop you want cultivated in that farmland, just go into the Field's GUI and switch the seed there.

![Field](../../../assets/images/wiki/misc/field.png)
![Field GUI](../../../assets/images/wiki/misc/fieldgui.png)

You can click on the arrows to increase the size of the area the {% worker /%} will farm. (Right-clicking will decrease the area.) The max size is 5 blocks in each direction from the Field block, or 11x11 total.

**IMPORTANT:** The {% worker /%} will farm up to five fields, depending on the level of the building. The level of the building is the number of Fields the {% worker /%} can cultivate:

| Building Level | Fields |
| -------------- | ------ |
| 1              | 1      |
| 2              | 2      |
| 3              | 3      |
| 4              | 4      |
| 5              | 5      |

## Minecolonies Crops

Minecolonies adds crops that can only be grown by a {% worker /%} and not by players. Those crops are needed to make high-quality food for your citizens. Some of these need a specific climate to grow.

| Crop             | Biomes        |
| ---------------- | ------------- |
| Onion            | Any           |
| Garlic           | Any           |
| Durum            | Any           |
| Eggplant         | Any           |
| Mint             | Any           |
| Butternut Squash | Cold          |
| Cabbage          | Cold          |
| Tomato           | Temperate     |
| Bell Pepper      | Temperate     |
| Corn             | Temperate     |
| Chickpea         | Hot and Dry   |
| Nether Pepper    | Hot and Dry   |
| Rice             | Hot and Humid |
| Soybean          | Hot and Humid |
| Peas             | Hot and Humid |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Request Fertilizer:** On by default. This lets you choose whether the {% worker /%} will request fertilizer.
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_fields order=5 /%}
{% building_gui_content_block_stock order=6 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\fisherman.mdoc

---
type: building
id: fisherman
name: Fisher's Hut
plural: Fisher Huts
description: The Fisher will catch fish and treasure from the vast oceans in your world.
workers:
  - fisher
rotation: 90
---

The {% building /%} is where the {% worker /%} will catch fish. The {% worker /%} requires a fishing rod and a body of water of a minimum of {% meta key="fisher_pond_size" /%} to be able to work, and the water must be near the hut.

![Pond](../../../assets/images/wiki/misc/pond.png)

Upgrading the {% building /%} will expand the range at which the {% worker /%} can fish, and the higher the level of the {% building /%}, the more loot the {% worker /%} will be fishing out (instead of fish). This includes prismarine and sponges!

> **Note:** The {% worker /%} will only catch fish and junk in most biomes. If the {% worker /%} is in an ocean biome, items from treasure category will also be caught.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\fletcher.mdoc

---
type: building
id: fletcher
name: Fletcher's Hut
plural: Fletcher Huts
description: The Fletcher is your master bowmaker, they will craft bows and arrows. But also anything else to do with string, like fishing rods.
workers:
  - fletcher
rotation: 270
---

The {% building /%} is where the {% worker /%} will craft arrows and items that use string or wool, such as bows, fishing rods, and paintings. For the {% worker /%} to work, they must receive a request from another worker and have the necessary materials. The {% building /%} must also have been taught the crafting recipes for all the items you want the {% worker /%} to be able to craft.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_do_recipes order=3 /%}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\florist.mdoc

---
type: building
id: florist
name: Flowershop
plural: Flowershops
description: The Florist will grow flowers for you, they need compost from the Composter to be able to do this.
workers:
  - florist
rotation: 270
---

The {% building /%} is where your {% worker /%} will grow flowers for your colony, if given {% item name="minecolonies/compost" /%} and an axe.

> **Note:** The higher the level of the building, the more daily output the {% worker /%} can handle. So:

| Building Level | Number of Plants |
| -------------- | ---------------- |
| 1              | 4                |
| 2              | 8                |
| 3              | 12               |
| 4              | 16               |
| 5              | 20               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_item_list order=2 header="plantables" imageKey="plantables" imageAlt="Plantables list" defaultOn=true %}
This shows a list of items the {% worker /%} can plant. As the {% building /%} is leveled up, your {% building /%} will be able to grow more types of flowers. At level 3 or higher, you will be able to select which flowers you want to use, before that it can only grow poppies and dandelions.
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_stock order=3 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\glassblower.mdoc

---
type: building
id: glassblower
name: Glassblower's Hut
plural: Glassblower Huts
description: The Glassblower creates glass and other created using glass.
workers:
  - glassblower
rotation: 270
---

The {% building /%} is where the {% worker /%} will smelt sand into glass and make glass panes from glass blocks. For the {% worker /%} to work, they must receive a request from another worker and have the necessary materials, including fuel if they're smelting sand into glass. The {% building /%} must also have been taught the crafting recipe for glass panes.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

> **Note:** The higher the level of the {% building /%}, the more furnaces the {% worker /%} will have available. The number of furnaces they *use* depends on their Creativity level. So:

| Building Level | Furnaces |
| -------------- | -------- |
| 1              | 1        |
| 2              | 2        |
| 3              | 3        |
| 4              | 4        |
| 5              | 5        |

> **Note:** The higher the {% worker /%} their Focus level, the faster things will smelt. At high levels, they can go *much* faster than the player!

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_smelting_recipes order=3 /%}
{% building_gui_content_block_do_recipes order=4 /%}
{% building_gui_content_block_fuel order=5 /%}
{% building_gui_content_block_tasks order=6 /%}
{% building_gui_content_block_settings order=7 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\graveyard.mdoc

---
type: building
id: graveyard
name: Graveyard
plural: Graveyards
description: The Graveyard is where your deceased citizens will be laid to rest. Every citizen will get a gravestone with their name on it, as long as there is room.
workers:
  - undertaker
rotation: 270
---

The {% building /%} is where the {% worker /%} will bury your deceased citizens.

The grave (decayed or not) will hold all the items the citizen had in their inventory at the time of death. The player can right-click the grave to open its inventory and retrieve the items. The {% worker /%} will walk (run if you've completed the relevant research) toward the grave and retrieve its inventory, then go back to the {% building /%}.

The recommended maximum grave count per {% building /%} level is below. This is **not mandatory**, and the actual amount will vary between styles.

| Building Level | Number of Graves |
| -------------- | ---------------- |
| 1              | 14               |
| 2              | 18               |
| 3              | 27               |
| 4              | 36               |
| 5              | 50               |

Once the {% worker /%} gets to the {% building /%}, they will attempt to revive the deceased citizen. The chance for them to succeed can be increased by:
- Researches (+1% and +2%)
- The {% worker /%} its Mana skill (+0.125% per Mana Skill Point)
- The level of the {% building /%} (+0.5% per Level)
- The use of totems unlocked by research (Totem gets used up with a chance of 1%)

By default, the chance of reviving is capped at 2.5%. This cap can be boosted by upgrading the {% building name="mysticalsite" /%} (0.5% per Level) and the use of totems (5% for 1 Totem, 7.5% for multiple totems). In total, the maximum chance is 12.5%.

If the citizen cannot be revived, the {% worker /%} will bury them in the {% building /%}. Another grave will be placed with the citizen's name on it (this grave does not store items).

The {% worker /%} is exempt from mourning so they can complete their job.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_custom order=2 header="graves" imageKey="graves" imageAlt="Graves" %}
The top half is a list of the graves the {% worker /%} needs to recover. The second half is a list of currently-buried citizens.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\guardtower.mdoc

---
type: building
id: guardtower
name: Guard Tower
plural: Guard Towers
description: The Guard Tower is your primary defense, every tower will employ a single Guard and can greatly expand your colony claim radius.
workers:
  - archer
  - druid
  - knight
rotation: 270
---

The {% building /%} will employ and house one Guard to protect your colony. The new guard will need a bed in a house in order to spawn. However, once they are hired at the {% building /%}, that becomes their new home and the bed in their original home will open up for another new citizen (child or recruit). Citizens like feeling safe, so building {% building plural=true /%} close to colonists' work and homes can improve their [happiness](/wiki/systems/happinessandsaturation). Additionally, if you place {% building plural=true /%} near your colony border and level them up, your border will [expand](/wiki/systems/border).

The guard will patrol a set distance around their building, which is based on their building its level.

| Building Level | Max Patrol Distance |
| -------------- | ------------------- |
| 1              | 80 blocks           |
| 2              | 110 blocks          |
| 3              | 140 blocks          |
| 4              | 170 blocks          |
| 5              | 200 blocks          |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_custom order=2 header="selection tools" imageKey="guardtool" imageAlt="Guard tool" %}
Click the **Obtain Tools** button to get the Guard Scepter. Right-clicking on a block with the Guard Scepter will set it as a guard spot or a patrol point.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_hostiles order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Task:** This is where you can choose if you want the guard to patrol, follow, or guard.
  - **Patrol:** The guard will patrol an area you designate in **Patrol Settings**.
  - **Guard:** You can set one area for the guard to stay in. When you click "Set Target", you will be taken to the **Selection Tools** tab, where you can get the the Guard Scepter and designate the guard location.
  - **Follow:** The guard will follow you around as your personal bodyguard, protecting you or fighting alongside you. They will even go outside the colony when following! You can designate how they follow you in **Follow Settings**.
- **Retreat on low health:** Here you can choose if the Guard will attempt to retreat when they have low health.
- **Hire Trainee:** If there is a vacancy at this tower, a new {% worker name="knight" /%} or {% worker name="archer" /%} can be hired from the respective training facility ({% building name="combatacademy" /%} for {% worker name="knight" plural=true /%} and {% building name="archery" /%} for {% worker name="archer" /%}) instead of an unemployed colonist. This setting only matters if Assign Colonists to Jobs is turned to Automatic in the {% building name="townhall" /%} GUI.
- **Patrol Mode:** This is where you can choose how you want the guard to patrol. Only available when the **Task** is set to **Patrol**.
  - **Automatic:** The guard will patrol from hut to hut and back to their tower. They will only patrol huts within the patrol range of their tower.
  - **Manual:** You can set the patrol route when you click on **Set Positions**. You will be taken to the **Selection Tools** tab, where you can get the the Guard Scepter and designate patrol positions for the guard to patrol between. To delete patrol positions, simply get a new Guard Scepter and click a new patrol position. The old ones should disappear.
- **Follow Mode:** This is where you can choose how you want the guard to follow you. Only available when the **Task** is set to **Follow**.
  - **Loose Grouping:** The guard will stay a decent range away from you.
  - **Tight Grouping:** The guard will stay relatively close to you.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_stock order=5 /%}
{% building_gui_content_block_statistics order=6 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\hospital.mdoc

---
type: building
id: hospital
name: Hospital
plural: Hospitals
description: The Hospital will treat your sick citizens. Several types of diseases will be able to spread through your colony, make sure they don't get out of control!
workers:
  - healer
rotation: 270
---

The {% building /%} is where injured or sick citizens go. The Healer will heal them with various items. {% version range="--1.19.4" inline=true %}The possible diseases can be changed in the [config file](/wiki/misc/configfile).{% /version %}{% version range="1.20--" inline=true %}The possible diseases can be changed through [datapacks](http://localhost:4321/wiki/tutorials/datapacks).{% /version %}

These are the defaults diseases:

| Disease             | Items Needed to Cure          |
| ------------------- | ----------------------------- |
| Influenza (the flu) | Carrot and Potato             |
| Measles             | Dandelion, Kelp, and Poppy    |
| Smallpox            | Honey Bottle and Golden Apple |

The higher the level of the {% building /%}, the more people can be healed at a time. So:

| Building Level | Number of Beds |
| -------------- | -------------- |
| 1              | 1              |
| 2              | 2              |
| 3              | 3              |
| 4              | 4              |
| 5              | 5              |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\kitchen.mdoc

---
type: building
id: kitchen
name: Cookery
plural: Cookeries
description: The Cookery is where food for your colonists will be cooked.
workers:
  - chef
rotation: 90
overrides:
  - version: "1.20"
    name: Chef's Kitchen
    plural: Chef's Kitchens
    description: The Chef's Kitchen is where food for your colonists will be cooked.
---

The {% building /%} is where the {% worker /%} will create food for your citizens.
Food will be cooked when buildings request is, use this conjunction with your {% building name="cook" plural=true /%} to automatically keep your {% building name="cook" plural=true /%} stocked with food.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_smelting_recipes order=2 /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_fuel order=4 /%}
{% building_gui_content_block_stock order=5 /%}
{% building_gui_content_block_crafting_recipes order=6 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\library.mdoc

---
type: building
id: library
name: Library
plural: Libraries
description: The Library is where your citizens can come to study, increasing their intelligence. This will make them more efficient learning other skills.
workers:
  - librarystudent
rotation: 90
---

The {% building /%} is a way for you to raise your adult citizens their Intelligence skill, which influences all other skill increases (at other jobs). A citizen will randomly level up their Intelligence as long as they're assigned to the {% building /%}. Being a {% worker /%} is their full-time job, so you can't have one citizen work at the {% building /%} and another worker hut at the same time.

Two citizens can study per building level. So: 

| Building Level | Citizens Educated at a Time |
| -------------- | --------------------------- |
| 1              | 2                           |
| 2              | 4                           |
| 3              | 6                           |
| 4              | 8                           |
| 5              | 10                          |

> **Note:** Paper and books help {% worker plural=true /%} increase their skills faster.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\lumberjack.mdoc

---
type: building
id: lumberjack
name: Forester's Hut
plural: Forester's Huts
description: The Forester will cut down trees in your colony, you can tell them what trees to cut down and even in what area they're allowed to cut trees down.
workers:
  - forester
rotation: 180
---

The {% building /%} is where the {% worker /%} will go in between chopping down trees. The {% worker /%} will cut down any tree in an approximate 150 block area (from themselves) that is not in a hut schematic and doesn't have cobblestone placed beneath it. Or you can optionally configure a specific chopping zone that they will use instead.

> **Note:** In addition to axes for chopping down trees, {% worker /%} require hoes for breaking leaves.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_item_list order=3 header="saplings" imageKey="saplings" imageAlt="Saplings list" defaultOn=true %}
It shows a list of recognized saplings the {% worker /%} can work with, even modded ones (if coded correctly).
Here you can turn on or off which type of trees the {% worker /%} will chop down.
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Replanting:** Where you can select if you want the {% worker /%} to replant trees that are chopped down. They will only do this if they have enough saplings.
- **Zoning Mode:** This lets you turn on or off if you want the {% worker /%} to be restricted to a certain area when chopping trees. To choose the area, use the Obtain Tool button on the next tab.
- **Break Leaves:** This lets you decide whether you want the {% worker /%} to also remove all of the leaves of the tree, else he will only cut those which are in their way.
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
- **Use Shears:** Whether the {% worker /%} will use shears or a hoe to cut down the leaves.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_custom order=6 header="zoning tool" imageKey="zonetool" imageAlt="Zoning tool" %}
This tool will let you define an area for the {% worker /%} to work. Right click one corner of the area you want, then left click the opposite corner, and this will set a box inside which the {% worker /%} will search for trees. Vertical coordinates are important as well -- for best results, ensure that your box contains the base of the trees as well as at least some of the leaf blocks. It's not necessary to contain the entire tree, but it won't hurt. Defining a zone is optional, but recommended if you want a dedicated tree farm area.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_stock order=7 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\mechanic.mdoc

---
type: building
id: mechanic
name: Mechanic's Hut
plural: Mechanic Huts
description: The Mechanic is the master of redstone, because the Mechanic is so smart, he will also be able to make things that no one else in the colony could.
workers:
  - mechanic
rotation: 270
---

The {% building /%} is where the {% worker /%} will craft redstone items, rails, minecarts, clocks, compasses, sea lanterns, torches, lanterns, jack-o-lanterns, storage blocks (like blocks of iron, coal, quartz, etc), dried kelp blocks, blue ice, packed ice, enchantment tables, ender chests, tripwire hooks, sticky pistons, {% item name="multipiston/multipistonblock" /%}, glowstone blocks, anything made with blaze rods, and many other items that no other crafter can make. For the {% worker /%} to work, they must receive a request from another worker and have the necessary materials. The {% building /%} must also have been taught the crafting recipes for all the items you want the {% worker /%} to be able to craft.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_do_recipes order=3 /%}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\miner.mdoc

---
type: building
id: miner
name: Mine
plural: Mines
description: The Miner will gather stone and ores from the depths below, they will make a vast tunnel network underground to gather all of your valuables.
workers:
  - miner
rotation: 270
---

The {% building /%} is where you can hire a {% worker /%} to work the mine, or a {% worker name="quarrier" /%} to work the {% building name="quarry" /%}. If you hire a Quarrier, there will be no Miner at this {% building /%}. 

{% version range="1.20.1--" %}
At the {% building /%}, the {% worker /%} will mine for ores and materials. Once they are hired, they will first create a shaft downward.

The {% worker /%} digs their shaft down until to the first mining level below it, increasing one further for each building level past 1.

These mining levels are based on the ores you can find there most:
| Ore     | Y-level |
| ------- | ------- |
| Copper  | 48      |
| Iron    | 16      |
| Gold    | -16     |
| Diamond | Bedrock |

To give some examples:
If the {% worker /%} builds their first platform at Y 48 or above:
- Level 1 can dig down to Y 48
- Level 2 can dig down to Y 16
- Level 3 can dig down to Y -16
- Etc

If the {% worker /%} builds their first platform between Y 48 and Y 16:
- Level 1 can dig down to Y 16
- Level 2 can dig down to Y -16
- Level 3 can dig down to bedrock

Once the main shaft is completed, the {% worker /%} will then branch out.
{% /version %}
{% version range="--1.20" %}
At the {% building /%}, the {% worker /%} will mine for ores and materials. Once they are hired, they will first create a shaft downward to a specific depth depending on the level of the {% building /%}. Once the main shaft is completed, the {% worker /%} will then branch out.
{% /version %}
The {% worker /%} will never dig further down than the Y-level specified in the "maximum depth" setting of the building. It is by default set to -100, which effectively means bedrock level.


While mining, sometimes the {% worker /%} will get lucky and get an ore block instead of a basic stone block. The chance of getting "Lucky Ores" is set in the [config](/wiki/misc/configfile).

> **Note:** When the {% worker /%} encounters air whilst building the shaft downwards, they don't make platforms there, as they think they encountered a cave. In particular, that means you should not help them with mining. Even though they skip platforms, they still check the Y-level against the depth threshold and stop digging down if they aren't allowed to dig down further.
{% version range="--1.20" %}
> **Note:** Placing the {% building /%} hut below the maximum Y level it can mine will cause the {% worker /%} not work and complain the hut needs to be upgraded. To avoid this error, place the hut at least 4 blocks above the maximum depth for the hut level. If you want your {% building /%} to be lower, you will need to upgrade it before the {% worker /%} will work.
{% /version %}

| Building Level | Shaft Y Level |
| -------------- | ------------- |
| 1              | 40            |
| 2              | 20            |
| 3              | 0             |
| 4              | Bedrock       |
| 5              | "             |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_custom order=3 header="levels" imageKey="levels" imageAlt="Levels" %}
The level refers to the platforms the {% worker /%} will place every 3 blocks down. Here you can assign what level of the {% building /%} the {% worker /%} will create their mineshafts (nodes).
If a level has a red number next to it, that means the {% worker /%} is currently mining that level.
The {% worker /%} will ignore orders to mine at a specific level until the entire mineshaft is completed to the maximum depth their hut's level allows.
You can also click Repair, to tell the {% worker /%} to restore that level to its original state. This can be useful if a fire breaks out in the mineshaft.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_settings order=4 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
- **Fill Block:** Here you can select what block the {% worker /%} uses to fill in holes/gaps in the schematic. The default is the dirt block.
- **Max Depth:** Here you can overide the default maximum depth of the {% building /%}, if you don't want the {% worker /%} to dig as deep as the {% building /%} level allows. The default is -100.
- **Use Shears:** Whether the {% worker /%} will use shears to break certain blocks.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_custom order=5 header="guards" imageKey="guards" imageAlt="Guards" %}
Here is where you can assign guards to patrol this {% building /%}. If assigned, they will patrol the level the {% worker /%} is currently mining at, to help protect them from hostile mobs.
Only guards set to the `Patrol Mine` task will show up here; tasks can be set in the {% building name="guardtower" /%} it's GUI ({% building name="barrackstower" plural=true /%} do not have the Patrol Mine task).

The amount of guards you can assign to the {% building /%} changes based on the building it's level.

| Building Level | Amount of guards |
| -------------- | ---------------- |
| 1              | 1                |
| 2              | 1                |
| 3              | 2                |
| 4              | 2                |
| 5              | 3                |
{% /building_gui_content_block_custom %}
{% building_gui_content_block_required_resources order=6 /%}
{% building_gui_content_block_stock order=7 /%}
{% building_gui_content_block_statistics order=8 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\mysticalsite.mdoc

---
type: building
id: mysticalsite
name: Mystical Site
plural: Mystical Sites
description: The Mystical Site is a place where your citizens can come and pray, which will make them a little bit happier.
rotation: 270
---

The {% building /%} is a simple building that increase a colony's overall [happiness](/wiki/systems/happinessandsaturation) level. It has no worker and increases colonists' happiness just by existing.

The happiness bonus does not stack - only the highest level of {% building /%} in the colony will provide a bonus. While there is no benefit to building multiple Mysical Sites, one is not prevented from doing so should they wish.

The {% building /%} also gives a bonus to the maximum resurrection chance of the {% worker name="undertaker" /%}

## Interface

{% building_gui_content_block_custom order=1 header="main interface" imageKey="main" imageAlt="Main" %}
- Header:
    - **Building Name**: Shows the name of the building, including the level of the building.
    - **Pencil**: Allows you to rename the building. The level of the building will always be listed after the name.
- Controls:
    - **Build Options**: Lets you create a build, upgrade, or repair build order for this hut. To learn more about the building system, please visit the {% worker name="builder" /%} page.
- Footer:
    - **Info Button**: Some huts have an in-game guide. Press the ? button to access it.
    - **Inventory**: Here you can access the hut block’s storage, where the worker at this hut takes and deposits materials. They will also use any racks that were placed in the hut when it was built or upgraded, so be sure to check those as well!
    - **Chest Icon**: Click this button to see all the items in the hut’s storage (including the hut block’s inventory and any racks that came with the hut). Clicking the ? button next to an item’s count will highlight the storage container it’s in.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\netherworker.mdoc

---
type: building
id: netherworker
name: Nether Mine
plural: Nether Mines
description: The Nether Miner is a dangerous job, this worker will venture to the Nether to gather special items that can only be found in the Nether.
workers:
  - netherminer
rotation: 270
---

The {% building /%} is where the {% worker /%} works. The {% worker /%} travels into [The Nether](https://minecraft.wiki/w/The_Nether) and mines resources found there. The level of the hut determines what items {% worker /%} brings back.

- **Level 1:**  blaze rod, ender pearl, ghast tear, gold ingot, gold nugget, gravel, gunpowder, leather, magma cream, nether quartz ore, netherrack, porkchop, rotten flesh, soul sand, soul_soil.
- **Level 2:**  All items from previous levels, plus brown mushroom, crimson fungus, crimson nylium, crimson stem, glowstone, nether wart, red mushroom.
- **Level 3:**  All items from previous levels, plus basalt, warped fungus, warped nylium, warped_stem.
- **Level 4:**  All items from previous levels, plus blackstone, nether gold ore.
- **Level 5:**  All items from previous levels, plus ancient debris.

The {% worker /%} can also craft {% item name="minecraft/lava_bucket" /%}.

> **Note:** The portal in the {% building /%} will transport players to the Nether. However, the {% worker /%} does not actually travel into the Nether, nor do they actually mine any blocks in the Nether.

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_stock order=3 /%}
{% building_gui_content_block_settings order=4 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
- **Close portal on return:** Tells the {% worker / %} whether to close the portal or not when they return. Default is On.
{% /building_gui_content_block_settings %}
{% building_gui_content_block_item_list order=5 header="food" imageKey="food" imageAlt="Food list" defaultOn=true %}
Listed here are items that can be used by the {% worker /%} as food while they are traveling in the nether.
Simply turn on any that you want your {% worker /%} to eat, and a {% worker name="courier" /%} will deliver those items when they need food.
{% /building_gui_content_block_item_list %}
{% building_gui_content_block_tasks order=6 /%}
{% building_gui_content_block_custom order=7 header="expedition log" imageKey="expeditions" imageAlt="Expeditions log" %}
Here it will show the current expedition the {% worker /%} is currently on.
From top to bottom the following is shown:
- The name of the {% worker /%} going on the expedition.
- The status of the adventure.
- The {% worker /%} their current health.
- The {% worker /%} their current saturation.
- The {% worker /%} their equipment.
- All the enemies the {% worker /%} defeated on their expedition.
- All the resources the {% worker /%} gathered on their expedition.
{% /building_gui_content_block_custom %}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\plantation.mdoc

---
type: building
id: plantation
name: Plantation
plural: Plantations
description: The Plantation is the place to go for any plants or non-farmland food that your colony may need.
workers:
  - planter
rotation: 180
---

{% version range="--1.19.1" %}
The {% building /%} is where the {% worker /%} will grow either sugar cane, bamboo, or cactus.

The {% building /%} its level determines how many crops the {% worker /%} can plant at a time.

| Building Level | Crops Grown |
| -------------- | ----------- |
| 1              | 4           |
| 2              | 8           |
| 3              | 12          |
| 4              | 16          |
| 5              | 20          |
{% /version %}
{% version range="1.19.2--" %}
The {% building /%} is where the {% worker /%} will grow a variety of plants:

- [Sugar Cane](https://minecraft.wiki/w/Sugar_Cane)
- [Cactus](https://minecraft.wiki/w/Cactus)
- [Bamboo](https://minecraft.wiki/w/Bamboo)
- [Cocoa Beans](https://minecraft.wiki/w/Cocoa_Beans)
- [Vine](https://minecraft.wiki/w/Vine)
- [Kelp](https://minecraft.wiki/w/Kelp)
- [Seagrass](https://minecraft.wiki/w/Seagrass)
- [Sea Pickles](https://minecraft.wiki/w/Sea_Pickle)
- [Glow Berries](https://minecraft.wiki/w/Glow_Berries)
- [Weeping Vines](https://minecraft.wiki/w/Weeping_Vines)
- [Twisting Vines](https://minecraft.wiki/w/Twisting_Vines)
- Crimson Plants ([Crimson Roots](https://minecraft.wiki/w/Roots) and [Crimson Fungus](https://minecraft.wiki/w/Fungus))
- Warped Plants ([Warped Roots](https://minecraft.wiki/w/Roots) and [Warped Fungus](https://minecraft.wiki/w/Fungus))

Each plant is grown on fields, which can be schematics (part of your style pack) that the {% worker name="builder" /%} can construct.
However, these fields have different requirements as outlined on the [Schematics](/wiki/tutorials/schematics#plantation-fields) page.

> These do **not** work like {% building name="farmer" /%} fields where you only have to place a scarecrow down!

The {% building /%} has a limit of fields, based on it's building level, as well as one accompanying research.

| Building Level | Number of Fields | Number of Fields with "Crop Rotation" Research |
| -------------- | ---------------- | ---------------------------------------------- |
| 1              | 1                | 2                                              |
| 2              | 1                | 2                                              |
| 3              | 2                | 3                                              |
| 4              | 2                | 3                                              |
| 5              | 3                | 4                                              |

The {% building /%} is also limited by the amount of concurrent plants it can work on, so if you were to have a field of Sugar Cane and Cactus, those are two different plants.
Unlike the field limit, this one does not increase by the research, meaning that - with the research unlocked - you will not be able to have four different kinds of fields.

| Building Level | Number of Concurrent Plants |
| -------------- | --------------------------- |
| 1              | 1                           |
| 2              | 1                           |
| 3              | 2                           |
| 4              | 2                           |
| 5              | 3                           |

{% /version %}

The {% worker /%} can also craft paper, books, sugar, and anything made with bamboo. The {% worker /%} will only make these items when they have been taught the recipes, receive a request for an item, and have the needed materials.

> **Note:** The {% worker /%} can only learn a certain amount of recipes per their hut level. 

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_tasks order=3 /%}
{% building_gui_content_block_fields order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\quarry.mdoc

---
type: building
id: quarry
name: Quarry
plural: Quarries
description: The Quarry is the big brother of the Mine, need a whole load of stone? Build a Quarry, they will be digging enormous holes to gather vast amounts of materials. Quarries come in different sizes.
workers:
  - quarrier
blockhutname:
  - minecolonies/simplequarry
  - minecolonies/mediumquarry
rotation: 90
---

The {% building /%} is where you can hire a {% worker /%} to dig the {% building /%}. The {% building /%} will be a pit of varied size (simple is 1 x 1 chunks, medium is 2 x 2) to collect larger amounts of stone type blocks. The {% building /%} will only produce the blocks it digs out.  It will not produce additional ore blocks like the {% building name="miner" /%}.

> **Note:** You must first hire a {% worker /%} at a {% building name="miner" /%} before assigning them to a {% building /%}.

## Interface

{% building_gui_content_block_custom order=1 header="main interface" imageKey="main" imageAlt="Main" %}
- Header:
    - **Building Name**: Shows the name of the building, including the level of the building.
    - **Pencil**: Allows you to rename the building. The level of the building will always be listed after the name.
- Controls:
    - **Build Options**: Lets you create a build, upgrade, or repair build order for this hut. To learn more about the building system, please visit the {% worker name="builder" /%} page.
- Footer:
    - **Info Button**: Some huts have an in-game guide. Press the ? button to access it.
    - **Inventory**: Here you can access the hut block's storage, where the worker at this hut takes and deposits materials. They will also use any {% item name="minecolonies/blockminecoloniesrack" /%} that were placed in the hut when it was built or upgraded, so be sure to check those as well!
    - **Chest Icon**: Click this button to see all the items in the hut's storage (including the hut block's inventory and any racks that came with the hut). Clicking the ? button next to an item's count will highlight the storage container it's in.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_custom order=2 header="manage workers" imageKey="workers" imageAlt="Manage workers" %}
- Header:
    - **Assigned Workers**: Tells you the worker assigned to this building.
- Controls:
    - **Manage Workers**: Lets you change which worker is assigned to work at this hut. There can only be one worker at each hut. **Note:** Unlike other buildings, you must first hire a {% worker /%} at the {% building name="miner" /%} and then assign that {% worker /%} to work in this building.
    - **Recall Worker**: Recalls the worker at this building to their hut block. You might use it if they are stuck somewhere, you want to see what they have, or want to give them something directly.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\rabbithutch.mdoc

---
type: building
id: rabbithutch
name: Rabbit Hutch
plural: Rabbit Hutches
description: The Rabbit Hutch is responsible for herding your rabbits.
workers:
  - rabbitherder
rotation: 180
---

The {% building /%} is where the {% worker /%} will raise and butcher rabbits. You will have to capture and bring in two rabbits to the {% building /%}, as the {% worker /%} will not catch and bring in any rabbits.

> **Note:** The {% worker /%} will only keep two rabbits alive per hut level, so at level 5 they will have 10 rabbits in their holding pens to breed and butcher. This means they will be faster at producing and collecting meat and other drops.

| Building Level | Rabbits Housed |
| -------------- | -------------- |
| 1              | 2              |
| 2              | 4              |
| 3              | 6              |
| 4              | 8              |
| 5              | 10             |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed (and consequently kill) rabbits.
- **Feeding:** On by default. Here you can choose if the {% worker /%} will feed baby animals, to let them grow up faster.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\residence.mdoc

---
type: building
id: residence
name: House
plural: Houses
description: The House, the place to call home for your citizens, your citizens will live and sleep here. Every citizen wants a roof above their head so they can be as efficient as possible.
blockhutname: minecolonies/blockhutcitizen
rotation: 270
overrides:
  - version: "1.19"
    name: Residence
    plural: Residences
    description: The Residence, the place to call home for your citizens, your citizens will live and sleep here. Every citizen wants a roof above their head so they can be as efficient as possible.
---

Citizens will go to the {% building /%} they are assigned to at night to sleep. The {% building /%} is also a way to get more citizens for your colony.

Each level of the {% building /%} will house one citizen. So: 

| Building Level | Citizens Housed |
| -------------- | --------------- |
| 1              | 1               |
| 2              | 2               |
| 3              | 3               |
| 4              | 4               |
| 5              | 5               |

For additional citizens to spawn, you first have to have enough space in your {% building plural=true /%} to house your first four citizens. After you have created enough space for your initial four citizens, you have two options for your colony to grow. The first way is to have a free bed in your colony. You can also make space for a new citizen, go to the {% building name="tavern" /%}, wait for a traveler to show up, and then give them the items they request to recruit them.

> **Note:** To have more than 25 citizens, you have to research {% research_link name="minecolonies/civilian/outpost" /%}, {% research_link name="minecolonies/civilian/hamlet" /%}, {% research_link name="minecolonies/civilian/village" /%} and {% research_link name="minecolonies/civilian/city" /%} in the {% building name="university" /%}.

## Interface

{% building_gui_content_block_main_residential order=1 /%}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\sawmill.mdoc

---
type: building
id: sawmill
name: Sawmill
plural: Sawmills
description: The Sawmill is where all your wood is transformed into more useful wood! Carpenters can craft nearly all wooden materials there are.
workers:
  - carpenter
rotation: 270
---

The {% building /%} is where the {% worker /%} will craft any items made of at least 75% wood that do not include ingots, stone, redstone (or produce a redstone signal), or string. The {% worker /%} will also craft a few other items, including {% item name="minecolonies/blockminecoloniesrack" /%}, as well as [Domum Ornamentum](/wiki/dependencies/domumornamentum) blocks that include wood. For them to do this, you must teach the {% building /%} the recipes and the {% worker /%} must receive a request for an item from another worker.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_do_recipes order=3 /%}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\school.mdoc

---
type: building
id: school
name: School
plural: Schools
description: The School is where your children come to learn from their teachers. They can level up their intelligence before growing up, and heading off to work.
workers:
  - teacher
  - pupil
rotation: 270
---

The {% building /%} is where the {% worker name="teacher" /%} will level up the {% worker name="pupil" plural=true /%} (children) their Intelligence skill. Paper will increase the leveling speed of the {% worker name="pupil" plural=true /%}. 

The level of the {% building /%} determines how many {% worker name="pupil" plural=true /%} can be taught at a time.

| Building Level | {% worker name="pupil" plural=true /%} Taught |
| -------------- | --------------------------------------------- |
| 1              | 2                                             |
| 2              | 4                                             |
| 3              | 6                                             |
| 4              | 8                                             |
| 5              | 10                                            |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\shepherd.mdoc

---
type: building
id: shepherd
name: Shepherd's Hut
plural: Shepherd's Huts
description: The Shepherd's Hut is responsible for herding your sheep.
workers:
  - shepherd
rotation: 270
---

The {% building /%} is where the {% worker /%} will breed and butcher sheep for food and wool. You will have to capture and bring in two sheep to the {% building /%}, as the {% worker /%} will not catch and bring in any sheep.

> **Note:** The {% worker /%} will only keep two sheep alive per hut level, so at level 5 they will have 10 sheep in their holding pens to breed and butcher. This means they will be faster at producing and collecting meat and wool. So:

| Building Level | Sheep Housed |
| -------------- | ------------ |
| 1              | 2            |
| 2              | 4            |
| 3              | 6            |
| 4              | 8            |
| 5              | 10           |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed (and consequently kill) sheep.
- **Feeding:** On by default. Here you can choose if the {% worker /%} will feed baby animals, to let them grow up faster.
- **Dyeing**: On by default. Here you choose if the {% worker /%} will dye sheep random colors to get differently colored wool. (They do not require dye to do this.)
- **Shearing**: On by default. Here you can choose if the {% worker /%} will shear the sheep or not.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\sifter.mdoc

---
type: building
id: sifter
name: Sifter's Hut
plural: Sifter Huts
description: The Sifter can turn "useless" materials into valuable materials, by seeing what comes out from sieving them through a mesh.
workers:
  - sifter
rotation: 270
---

The {% building /%} is where the {% worker /%} will sift through dirt, gravel, sand, or soul sand to find loot. Doing this will make the block the {% worker /%} is sifting disappear. 

| Block     | Chance for        |
| --------- | ----------------- |
| Dirt      | Beetroot seeds    |
| Dirt      | Carrots           |
| Dirt      | Melon seeds       |
| Dirt      | Potatoes          |
| Dirt      | Pumpkin seeds     |
| Dirt      | Oak saplings      |
| Dirt      | Spruce saplings   |
| Dirt      | Birch saplings    |
| Dirt      | Jungle saplings   |
| Dirt      | Acacia saplings   |
| Dirt      | Dark oak saplings |
| Dirt      | Wheat seeds       |
| Gravel    | Coal              |
| Gravel    | Diamonds          |
| Gravel    | Lapis lazuli      |
| Gravel    | Emeralds          |
| Gravel    | Flint             |
| Gravel    | Gold ingots       |
| Gravel    | Iron ingots       |
| Gravel    | Iron nuggets      |
| Gravel    | Redstone          |
| Sand      | Cacti             |
| Sand      | Cocoa beans       |
| Sand      | Gold nuggets      |
| Sand      | Sugarcane         |
| Soul Sand | Blaze powder      |
| Soul Sand | Glowstone dust    |
| Soul Sand | Magma cream       |
| Soul Sand | Nether wart       |
| Soul Sand | Quartz            |
| Soul Sand | Human skulls      |

You can choose between four {% item_page name="meshes" /%}. The higher the level of the mesh, the higher the likelihood that the {% worker /%} will find loot.

The {% building /%} can sift a certain amount of blocks per day:

| Building Level | Maximum Blocks |
| -------------- | -------------- |
| 1              | 64 blocks      |
| 2              | 256 blocks     |
| 3              | 576 blocks     |
| 4              | 1024 blocks    |
| 5              | Unlimited      |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_stock order=3 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\smeltery.mdoc

---
type: building
id: smeltery
name: Smeltery
plural: Smelteries
description: The Smeltery can turn all of your ores into ingots, higher level Smelteries can utilize more furnaces.
workers:
  - smelter
rotation: 270
---

The {% building /%} is where the {% worker /%} will smelt ores into ingots.

{% version range="1.19--" %}
The primary skill level of the {% worker /%} will determine how many furnaces they will be able to use, up to a maximum of 10.
The {% worker /%} applies fortune to the ores they smelt. The fortune level is building level -1 (for example, a level 3 {% worker /%}, will apply fortune II).
{% /version %}
{% version range="--1.18.2" %}
The higher the level of the {% building /%}, the more furnaces the {% worker /%} will be able to use.
A higher level will also have a higher chance to double and even triple the ingot output per block of ore. 
{% /version %}

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_smelting_recipes order=2 noTeach=true noRemove=true /%}
{% building_gui_content_block_fuel order=3 /%}
{% building_gui_content_block_stock order=4 /%}
{% building_gui_content_block_item_list order=5 header="ores" imageKey="ores" imageAlt="Ores" defaultOn=true %}
Listed here are all ore blocks the {% building /%} can smelt. Turn off any ores you do not want smelted.
All coded ores (even if they come from other mods) are automatically added to this list.
If you have ores that are not on the list, they can be added to the [config file](/wiki/misc/configfile).
{% /building_gui_content_block_item_list %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\stonemason.mdoc

---
type: building
id: stonemason
name: Stonemason's Hut
plural: Stonemason Huts
description: The Stonemason is where all your stone produts are sculped into other stone products! Stonemasons can craft nearly all stone materials there are.
workers:
  - stonemason
rotation: 270
---

The {% building /%} is where the {% worker /%} will craft 3x3 recipes made entirely out of cobblestone, stone, andesite, diorite, granite, quartz, purpur, nether bricks, prismarine, sandstones, blackstone, basalt, and/or ores (no ingots or redstone items). They can also craft end stone from cobblestone and an ender pearl, but only if you have completed the Know the End [research](/wiki/systems/research) in the {% building name="university" /%}. The {% worker /%} will only work when they receive a request from another worker and have the needed materials. You will also need to teach the {% building /%} the recipes you want the {% worker /%} to craft.

> **Note:** Upgrading the {% building /%} lets you teach it more recipes. So:

| Building Level | Number of Recipes |
| -------------- | ----------------- |
| 1              | 10                |
| 2              | 20                |
| 3              | 40                |
| 4              | 80                |
| 5              | 160               |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_crafting_recipes order=2 /%}
{% building_gui_content_block_do_recipes order=3 /%}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\stonesmeltery.mdoc

---
type: building
id: stonesmeltery
name: Stone Smeltery
plural: Stone Smelteries
description: The Stone Smeltery is your general smelter for your colony, they can smelt things like cobblestone into stone, sand into sandstone and so forth.
workers:
  - stonesmelter
rotation: 270
overrides:
  - version: "1.20"
    name: Brick Yard
    plural: Brick Yards
    description: The Brick Yard is your general smelter for your colony, they can smelt things like cobblestone into stone, sand into sandstone and so forth.
---

The {% building /%} is where the {% worker /%} will smelt cobblestone into stone, stone bricks into cracked stone bricks, clay balls into bricks, clay blocks into terracotta, terracotta into glazed terracotta, all types of stone into all types of smooth stone, and logs into charcoal. They can also pop chorus fruits! The Stone Smelter will only work when they have been taught the recipe, receive a request from another worker, and have the needed materials. 

**Special Note: Charcoal cannot be used as a fuel for making charcoal in the stone smeltery.**

> **Note:** The higher the level of the Stone Smeltery, the more furnaces the Stone Smelter will have available. The number of furnaces they *use* depends on their Athletics level. So:

| Building Level | Furnaces |
| -------------- | -------- |
| 1              | 1        |
| 2              | 2        |
| 3              | 3        |
| 4              | 4        |
| 5              | 5        |

> **Note:** The higher the Stone Smelter's Dexterity level, the faster things will smelt. At high levels, they can go *much* faster than the player!

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_smelting_recipes order=2 /%}
{% building_gui_content_block_fuel order=3 /%}
{% building_gui_content_block_tasks order=4 /%}
{% building_gui_content_block_settings order=5 %}
- **Recipe Mode:** This is unlocked by researching {% research_link name="minecolonies/technology/warehousemaster" /%} in the {% building name="university" /%}. This changes how multiple recipes for the same item are prioritized.
  - **Priority:** This is the default setting. The hut will try to use recipes that are higher up in their recipe list first.
  - **Warehouse Stock:** The hut will look in the warehouse first to see what resource you have more of before deciding what recipe it will use.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\swineherder.mdoc

---
type: building
id: swineherder
name: Swineherd's Hut
plural: Swineherd Huts
description: The Swineherd's Hut is responsible for herding your pigs.
workers:
  - swineherder
rotation: 270
---

The {% building /%} is where the {% worker /%} will breed and butcher pigs for food. You will have to capture and bring in two pigs to the {% building /%}, as the {% worker /%} will not catch and bring in any pigs.

> **Note:** The {% worker /%} will only keep two pigs alive per hut level, so at level 5 they will have 10 pigs in their holding pens to breed and butcher. This means they will be faster at producing and collecting meat. So:

| Building Level | Pigs Housed |
| -------------- | ----------- |
| 1              | 2           |
| 2              | 4           |
| 3              | 6           |
| 4              | 8           |
| 5              | 10          |

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_stock order=2 /%}
{% building_gui_content_block_settings order=3 %}
- **Breeding:** On by default. Here you can choose if the {% worker /%} will breed (and consequently kill) pigs.
- **Feeding:** On by default. Here you can choose if the {% worker /%} will feed baby animals, to let them grow up faster.
{% /building_gui_content_block_settings %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\tavern.mdoc

---
type: building
id: tavern
name: Tavern
plural: Taverns
description: The Tavern is where you can get visitors to your colony who are eager to join your colony. The Tavern also functions as basic housing for your starting citizens.
singular: true
rotation: 270
---

The {% building /%} is like a {% building name="residence" /%} in that it houses citizens, however, the {% building /%} houses four instead of one and can't be upgraded to house more.

Every so often, visitors will come to the {% building /%}. You can recruit these visitors (with items) to live and work in your colony. Don't attack them, they'll fight back!
Upgrading the {% building /%} will garner more and better visitors.

> **Note:** The {% building /%} can only be upgraded to level 3, not level 5.

## Interface

{% building_gui_content_block_main_residential order=1 /%}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\townhall.mdoc

---
type: building
id: townhall
name: Town Hall
plural: Town Halls
description: The Townhall is the starting point of your colony, from here you can manage everything regarding your colony.
singular: true
rotation: 90
---

The {% building /%} is the central part of your colony.

> **Note:** The {% building /%} block **cannot** be crafted until **after** you have already placed the {% building /%} you get from the {% item_page name="supply_camp_and_ship" /%}. If needed, it can also be obtained in creative mode like any other block or by commands.

## Starting a New Colony

### Scouting the Area

Make sure you scout your area carefully before you decide where you want to place your {% building /%}. Your colony will start with a 4 chunk radius (4 chunks in each direction) from the {% building /%} block. Make sure this is where you want your colony to be.

### Placing your {% building /%}

After you have carefully decided where you want to place your {% building /%} (remember, the position where you placed the {% building /%} block will be the center of your colony's protected area. Once placed, the area will be set and cannot be changed), use your {% item name="structurize/sceptergold" /%} to place the {% building /%} block.

Right-click the ground in the area you want to place the {% building /%}. The building GUI will display showing the 3D preview of the building. You will be able to use the arrow buttons to move the building to the location you desire. 

> **Note:** Make sure you use the + and - options in the GUI to make sure you have the ground level of the hut where you want the ground level to be. Not all hut blocks sit on the ground.

Once you commit to the placement of the {% building /%} (green checkmark), the {% building /%} block will be placed.

### Creating your Colony

Once you have placed the {% building /%} block you will need to right-click on it, change the name of your colony if you want to, and found the colony.

![Creating your first colony](../../../assets/images/wiki/gui/buildings/townhall/th_found_colony.png)

A new colony will be founded, the area of your colony will be established, and the entire area will be protected.

### Protection Area

When you start a colony, the area that is initially set also has protections added to it. The protection system includes blocking any player from placing/breaking or interacting with blocks of any kind, placing lava or water, and placing/lighting TNT. Once established it will also show in the informational screen.

The protected area of your colony (once the {% building /%} has been placed) will depend on the [configuration](/wiki/misc/configfile), but will be 4 chunks radius by default, measured from where you placed your {% building /%} block the first time. Therefore, plan carefully where you want to place your {% building /%}. Your protected colony area includes mountains, hills, lakes, oceans, caves, world generated structures, etc. from bedrock to the sky limit.

Due to the protected area of each colony, you have to carefully scout your surroundings to make sure you are clear of any other colonies nearby preventing you from placing your {% building /%} or limiting your colony area in that direction.

> **Note:** Once you place your {% building /%} block, this will be the CENTER of your town it's protected radius. If you decide that you want your actual {% building /%} building to be built in a different location (within your currently set protected radius), you can break the block and place it again with your build tool. Removing and replacing the {% building /%} block will NOT remove the protected area of your town. The only way to remove the protected area of your colony so that you can place a {% building /%} somewhere else is by a person with OP or admin permission deleting your colony through [commands](/wiki/systems/command).

If there is another colony too close to your current position, you won't be able to place a {% building /%}.

If you try to place another {% building /%} outside of your protected area, you will get a message:

![Creating a second colony](../../../assets/images/wiki/gui/buildings/townhall/th_found_colony_existing.png)

### Deleting a Colony

To delete your colony, place a {% building /%} outside your current colony border (either by mining or deconstructing your old {% building /%} or by crafting a new one). Interacting with the newly placed {% building /%} outside of the current borders will prompt you to delete your colony.
Alternatively, if you have the proper permissions, you can use [commands](/wiki/systems/command).


---

## Interface

### **Actions:** This section contains the most important actions for the colony.

{% building_gui_content_block_custom imageKey="th_actions" imageAlt="Townhall actions" cols=6 %}
**Page 1:** Here you will see the name of your colony as well as the building level of the {% building /%}. And the buttons:
- **Pencil:** To change the name of your colony (from `<user>'s Colony`, which is the default) to anything you want.
- **Build Options:** Lets you create a build, upgrade, reposition, or repair build order for the {% building /%}. To learn more about the building system, please visit the {% building name="builder" /%} page.
- **Town Map:** From here you can view the map of your town. You have to add maps to the inventory to start showing the map.
- **Hire Mercenaries:** Here you can hire mercenaries to help defend your colony. Be warned, they're mean and will steal from citizens!

**Page 2:** Contains all the cosmetic options for your colony, like your colony flag, color, citizen style and citizen names.
- **Edit Colony Flag:** Clicking this button will open up a banner designer where you can design your colony banner. See more in the section below.
- **Colony Color:** Whatever color you pick from here, your guards will have a glow of this color around them when you put them in follow mode at their {% building name="guardtower" /%} or {% building name="barrackstower" /%}. This is for the PVP system, so you know which guards are yours when you are fighting. Your citizens' names will also be in this color.
- **Colony Pack:** This option controls the default selected style pack when opening the build tool.
- **Patreon:** Shown if you're not a member of our {% social_link id="patreon" /%}, brings you to our {% social_link id="patreon" /%} page.
- **Citizen Style:** The option controls how the citizens look. This feature is exclusive for patrons.
- **Name Pack:** The option controls how the citizens are named. This feature is exclusive for patrons. Note that changing this will not rename existing citizens.
{% /building_gui_content_block_custom %}

{% building_gui_content_block_custom imageKey="th_town_map" imageAlt="Townhall town map" cols=6 %}
The town map is where you can gets a birds eye view of your entire colony. Once you open the map you will initially not see a map, prompting you to first make a {% item name="minecraft/map" /%} and fill the map by walking around the colony.
Once your maps are good, go back to the {% building /%}, and deposit the maps in the inventory of the {% building /%}, you can access this on the bottom left of map view.

When your map is showing up, you will be able to see all of your buildings and citizens walking around on the map.

> **Note:** The maps do not automatically update, if you want to see the changes in your world reflected on your map, you have to reload them every so often, just like with
> vanilla map walls.
{% /building_gui_content_block_custom %}

{% building_gui_content_block_custom imageKey="th_colonybannerdesigner" imageAlt="Townhall colony banner designer" cols=6 %}
The colony banner designer is where you can create your own colony banner design. You can use up to 6 layers to create your own unique flag.

Your {% worker name="knight" /%} will wear the finished design on their shields, and it is also used in some schematics where colony banners are placed down.
{% /building_gui_content_block_custom %}

### **Information:** This is the overall information section of the colony.

{% building_gui_content_block_custom imageKey="th_information" imageAlt="Townhall information" cols=6 %}
**Page 1:** Here you can view the events happening in a colony, events are things like new citizens getting spawned in, buildings built or repaired, etc. Newest events are at the bottom.

**Page 2:** Here are all the work orders for this colony (including decorations and your own schematics as well as buildings). Your {% worker name="builder" plural=true /%} will complete the builds from top to bottom, and you can change the priority of the builds by moving them up or down in the list. You can also delete work orders. When you delete a work order that is currently being built, the {% worker name="builder" /%} will stop building and will have to restart from the beginning.
{% /building_gui_content_block_custom %}

### **Permissions:** Here you can invite other players to your colony to collaborate.

{% building_gui_content_block_custom imageKey="th_permissions1" imageAlt="Townhall permissions" cols=6 %}
**Page 1:** Here you can add players to your colony by clicking on "Add Player" and typing in someone their name.
There's also a list of all players and their given rank within your colony.

**Page 2:** Here you can find the list of permission events, similar to the events list on the information page, however this is related to permission events. For example if a player tried breaking a block, or something made an explosion.
{% /building_gui_content_block_custom %}

{% building_gui_content_block_custom imageKey="th_permissions2" imageAlt="Townhall permissions" cols=6 %}
**Page 3:** Here you can select the rank that you would like to manage.

**Page 4:** Here are the individual permissions (for the rank you have selected on the previous page) that you can toggle **On** or **Off**, giving each rank the permission you want.
> **Note:** the Fight Guards permission causes guards to treat the player as an enemy and attack them. It is only recommended for hostile players.
{% /building_gui_content_block_custom %}

{% building_gui_content_block_custom imageKey="th_permissions3" imageAlt="Townhall permissions" cols=6 %}
**Page 5:** Where you enter the position (X, Y, Z) of the block, or the ID of a block, for example `minecraft:dirt`, that you want to make free for interaction.

**Page 6:** Here you will see the list of block positions/blocks that you have added as free for interaction and can remove them.
{% /building_gui_content_block_custom %}

### **Citizens:** This section displays the names and skill levels of the citizens in your colony.

{% building_gui_content_block_custom imageKey="th_citizens" imageAlt="Townhall citizens" cols=6 %}
**Page 1:** Here you can see the overall happiness for your citizens, happiness is split into lots of different kinds of factors.
Each factor can have a variety of colours:
- Green (positive)
- Blue (neutral)
- Yellow (slightly negative)
- Red (negative)

**Page 2:** Here you will see a list of the citizens in your colony. Select a citizen to see their basic info like health, happiness and saturation.
You can also recall citizens to the {% building /%} from here if they are stuck.
{% /building_gui_content_block_custom %}

### **Statistics:** This is the section for the global happiness of your colony so you can see what area needs more attention to raise the happiness level.

{% building_gui_content_block_custom imageKey="th_statistics" imageAlt="Townhall happiness" cols=6 %}
**Page 1:** Here you can see an overview of the total amount of citizens you have and the limit of the amount of citizens you can have at most.
This limit is determined based on the amount of available sleeping places in the colony, but also affected by research.

Below is a list of all jobs in the colony, including children and unemployed people.
Every building that registers a job will list all of the jobs in this list, with two numbers behind each job. The amount of jobs occupied and the amount of jobs available.
This can help you determine if you have any free jobs for your citizens to take.

**Page 2:** Here are statistics listed, the {% building /%} lists every statistic of every building combined. Statistics are simple values like "X fish caught", "Y logs cut", "Z blocks mined", etc.
You can show these values for:
- Yesterday
- Last week
- Last 100 days
- All time
{% /building_gui_content_block_custom %}

### **Alliances:** Here you can see alliances and feuds with other colonies

{% building_gui_content_block_custom imageKey="th_alliances" imageAlt="Townhall alliances" cols=6 %}
**Page 1:** Allies and feuds are relations with other colonies. Allies can be formed by making the owner of the other colony listed as a friend in the permissions of the {% building /%}.
Once an alliance is formed, you can teleport to the other colony, assuming the {% building /%} is level 3 or above.

Feuds can be formed the same way, but rather than marking the other player as a friend, you mark them as hostile. Feuds by default do not have any usage, unless you turn pvp features on
in the [config](/wiki/misc/configfile).

**Page 2:** Blank page.
{% /building_gui_content_block_custom %}

### **Settings:** This section is where you can control how your citizens will be hired and assigned housing in your colony, among other things.

{% building_gui_content_block_custom imageKey="th_settings" imageAlt="Townhall settings" cols=6 %}
**Page 1:** There are four buttons here:
- **New citizens spawning:** Determine whether new citizens are allowed to spawn in. If off, neither initial citizens, nor children will spawn in.
- **Auto Worker Hiring:** When on, citizens will automatically be hired at any open working place, assuming the individual buildings their hiring mode is also on automatic.
- **Auto Citizen Housing:** Same as above, but this controls whether citizens will automatically be assigned a place to sleep or not.
- **Enter/Leave messages:** Whether you want to show messages for other players entering and leaving your colony.
- **Construction tape:** Whether you want buildings to spawn with construction tape upon creating a work order.

**Page 2:** Blank page.
{% /building_gui_content_block_custom %}


---
# File: MinecoloniesWiki\src\content\wiki\buildings\university.mdoc

---
type: building
id: university
name: University
plural: Universities
description: The University is where you research new technologies, unlocking new buildings, bonuses for your citizens or other new features. This is an essential building to have.
workers:
  - researcher
rotation: 270
---

The {% building /%} is where a {% worker /%} will research various upgrades to your colony.

As you level up the {% building /%}, you can hire more {% worker plural=true /%}:

| Building Level | Max {% worker plural=true /%} |
| -------------- | ----------------------------- |
| 1              | 1                             |
| 2              | 2                             |
| 3              | 3                             |
| 4              | 4                             |
| 5              | 5                             |

Each {% worker /%} can start their own research, but more {% worker plural=true /%} does not lower the amount of time each individual research takes. For example, having two {% worker plural=true /%} but only one research in progress does not decrease the time that single research takes.

> **Note:** Research time is real-world time spent in-game. However, {% worker plural=true /%} will sometimes use offline time to work on researches. {% worker plural=true /%} their Knowledge skill affects the amount of research time they get from the offline time (it's not a 1:1 ratio), and their Mana skill affects the max amount of research time they can get. Offline research time is unlocked at {% building /%} level 3. 

## Interface

{% building_gui_content_block_main order=1 /%}
{% building_gui_content_block_custom order=2 header="research" imageKey="research" imageAlt="Research" %}
Here you can select what research you want to unlock. **Research in Progress** displays how many research projects are currently being worked on, along with the maximum research that can be done.
Once you click on a research tree, you will see the options for each research branch. Each option will tell you the requirements and how long it takes to research that option.
To see a description of each of the researches, please visit the [Research System](/wiki/systems/research) page.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\buildings\warehouse.mdoc

---
type: building
id: warehouse
name: Warehouse
plural: Warehouses
description: The Warehouse is the main building you need for colony automation. The Warehouse serves as the storage for your entire colony and employs Couriers to haul goods around your colony.
workers:
  - courier
rotation: 270
---

The {% building /%} is the central storage from where a {% worker /%} will store and retrieve everything your workers harvest, craft, or need. Items will be stored in {% item name="minecolonies/blockminecoloniesrack" /%}.

The level of the {% building /%} will determine how many {% worker plural=true /%} will be able to use it at the same time. Level up the {% building /%} to increase the amount of {% worker plural=true /%} that can work in it. Leveling up the {% building /%} will also increase its storage capacity.

| Building Level | Max {% worker plural=true /%} |
| -------------- | ----------------------------- |
| 1              | 2                             |
| 2              | 4                             |
| 3              | 6                             |
| 4              | 8                             |
| 5              | 10                            |

## Interface

{% building_gui_content_block_custom order=1 header="main interface" imageKey="main" imageAlt="Main" %}
- Header:
    - **Building Name**: Shows the name of the building, including the level of the building.
    - **Pencil**: Allows you to rename the building. The level of the building will always be listed after the name.
- Controls:
    - **Build Options**: Lets you create a build, upgrade, or repair build order for this hut. To learn more about the building system, please visit the {% worker name="builder" /%} page.
- Footer:
    - **Info Button**: Some huts have an in-game guide. Press the ? button to access it.
    - **Inventory**: Here you can access the hut block’s storage, where the worker at this hut takes and deposits materials. They will also use any racks that were placed in the hut when it was built or upgraded, so be sure to check those as well!
    - **Chest Icon**: Click this button to see all the items in the hut’s storage (including the hut block’s inventory and any racks that came with the hut). Clicking the ? button next to an item’s count will highlight the storage container it’s in.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_custom order=2 header="workers" imageKey="workers" imageAlt="Workers" %}
- **Assigned Workers:** A list of the {% worker plural=true /%} assigned to this {% building /%}.
- **Manage Workers:** You can choose which {% worker plural=true /%} to hire at the {% building /%}. **Note:** this only works if you have turned the worker hiring mode in the {% building /%} block to manual, otherwise your {% worker plural=true /%} will be hired automatically.
- **Recall Workers:** Recalls the {% worker plural=true /%} at this {% building /%} to the hut block. You might use it if they are stuck somewhere, you want to see what they have, or want to give them something directly.
{% /building_gui_content_block_custom %}
{% building_gui_content_block_stock order=3 /%}
{% building_gui_content_block_custom order=4 header="storage" imageKey="storage" imageAlt="Upgrade storage" %}
- **Block of Emerald:** You can increase the max amount of stacks in each rack by pressing this button. This can only be done when the {% building /%} is at level 5 and you have at least one block of emerald in your inventory. The storage can be increased 3 times.
- **Sort:** The sort option is available when the {% building /%} reaches level 3. It sorts and stacks all the items in the racks.
{% /building_gui_content_block_custom %}



---
# File: MinecoloniesWiki\src\content\wiki\dependencies\blockui.mdoc

---
type: page
title: BlockUI
---
Mod link: [https://www.curseforge.com/minecraft/mc-mods/blockui](https://www.curseforge.com/minecraft/mc-mods/blockui)

BlockUI is a dependency of MineColonies, it is an XML-based UI management system for Minecraft.
It defines the structure for the UI inside an XML while providing a backing "Window" class that is used to handle callbacks and data supply.

It has been used in numerous past projects like Structurize and MineColonies and is also used in projects like StorageRacks and MultiPiston.

It is continuously updated and supports a broad selection of UI tools:

- Images
- Buttons and Button Handlers
- Text Input
- Scroll and Drag Screens
- More!


---
# File: MinecoloniesWiki\src\content\wiki\dependencies\domumornamentum.mdoc

---
type: item-combined
title: Domum Ornamentum
items:
 - domum_ornamentum/architectscutter
infobox:
 show: false
---
Mod link: [https://www.curseforge.com/minecraft/mc-mods/domum-ornamentum](https://www.curseforge.com/minecraft/mc-mods/domum-ornamentum)

Domum Ornamentum, or DO for short, is a dependency of MineColonies that adds combinatorial crafting of ornamental blocks. With it, you can combine almost any full blocks to create framed blocks, shingles, pillars, doors, and many more!

One block gives a few options, but you can combine up to three blocks together.
Given that Minecraft has quite a few blocks, and you do "quite a few" x "quite a few" x "quite a few" x "however many block options for one block" x "however many block options for two blocks" x "however many block options for three blocks".
In case of the most detailed blocks, this results in over 16 million blocks!

## Getting started

{% item_infobox item="domum_ornamentum/architectscutter" %}
To start, you need to craft an Architects cutter.

The easiest way to find the materials you need is to hover your mouse over the the image of the requested item. This will bring up an information box, telling you the name of the item, that it is crafted in the Architects cutter, the main material, and the support material(s).

![Request for a Domum Ornamentum item](../../../assets/images/wiki/misc/do_shingles_mouseover.png)

When you have that information, you can put the materials in the Architects cutter. Once you have the materials in place, click on the image of the item in the middle section of the Cutter's GUI, and it will show that item in the output box. Check carefully to make sure the output matches EXACTLY the required materials from the request. Take special care matching the support materials as any differences in materials means your colonists won't use the item you craft, and some of the supporting materials aren't visible from the output preview.

![The Architects cutter's interface, showing shingles](../../../assets/images/wiki/misc/do_shingles_cutter.png)

When you have crafters, e.g. a {% building name="sawmill" /%}, up and running, you can then teach them the recipe by choosing the "custom recipe" tab and putting it in there.
Input 1 is the top left slot of the cutter, input 2 is the top right, and input 3 the bottom left slot in the cutter. When you have put the items in the slots, you will see various items below the input slots. The crafter can create ALL of those items from the recipe you have input.

However, crafters can only learn these recipes if they correspond to at least one of the materials. For example, the {% building name="sawmill" /%} can only learn recipes that involve wood, and the {% building name="stonemason" /%} can only learn recipes that involve various types of stone. For recipes involving multiple materials, any of the corresponding crafters work; for example, both the Carpenter and Stonemason can learn Brick Extra-framed Oak Planks (pictured below).

![Teaching the Sawmill an Architects cutter recipe](../../../assets/images/wiki/misc/do_shingles_sawmill.png)
{% /item_infobox %}


---
# File: MinecoloniesWiki\src\content\wiki\dependencies\multipiston.mdoc

---
type: page
title: Multi-Piston
---
Mod link: [https://www.curseforge.com/minecraft/mc-mods/multi-piston](https://www.curseforge.com/minecraft/mc-mods/multi-piston)

Multi-piston is a dependency of MineColonies that adds a multipiston block to the game which allows you to suck in any block from a defined direction and spit it out into any direction with definable speed and range as well as a configurable max range in the config file.

#### FAQ:

How does the GUI work?

> 1. Right-click the block to open the GUI.
> 2. Right-click the input direction and left-click the output direction.
> 3. Edit the range and speed, exit the GUI via the escape button.

How can I increase the range?
> In the multi-piston.cfg file in your config folder.

Can I increase the speed?
> Only up to 3, might support more in the future.

Can I change the sound?
> Might be added in the future as well.

Does it work with redstone?
> Yes, it works with buttons, levers and any redstone input as normal pistons.


---
# File: MinecoloniesWiki\src\content\wiki\dependencies\structurize.mdoc

---
type: page
title: Structurize
---
Mod link: [https://www.curseforge.com/minecraft/mc-mods/structurize](https://www.curseforge.com/minecraft/mc-mods/structurize)

Structurize is a dependency of MineColonies which handles the construction of schematics (blueprints).

The primary tool to do this is the {% item name="structurize/sceptergold" /%}, which we go further in depth in the page about the build tool.

There are 3 other tools that are used in schematic creation:
- {% item name="structurize/sceptersteel" /%}
- {% item name="structurize/sceptertag" /%}
- {% item name="structurize/shapetool" /%}

For more information about creating schematics, please visit [this page](/wiki/tutorials/schematics)

## Commands

| Command                                 | Permissions | Command Description                                                                                                                                                       |
| --------------------------------------- | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `/structurize linksession create`       | ALL         | Creates a linksession to which you can invite other players to see build previews.                                                                                        |
| `/structurize linksession addplayer`    | ALL         | Invites a player to your link session so they can see your building previews. Make sure you create one first! After being invited, players have to accept the invitation. |
| `/structurize linksession acceptinvite` | ALL         | Accepts an invitation to a link session.                                                                                                                                  |



---
# File: MinecoloniesWiki\src\content\wiki\installation\curseforge.mdoc

---
type: page
title: CurseForge
---

## Installing MineColonies Mod through CurseForge

- This process will cover both how to install just MineColonies, but also one of our modpacks.
- No other installation requirements are required, besides installing CurseForge itself.

> **Note:** We cannot offer support on problems with CurseForge directly.

---

## Step 1: Downloading CurseForge

You can download CurseForge [here](https://www.curseforge.com/download/app), choose whether you want the overwolf or the standalone version.
We recommend picking the standalone version, as generally this is the only Overwolf application people would use.

If you already have Overwolf installed, open Overwolf on your computer and install the CurseForge app through there.

## Step 2: Opening CurseForge & Installing Minecraft

When you initially open CurseForge, you can see a list of games on your left, initially there should be nothing here.

Select the "Home" page on the bar on the left, find "Minecraft". The exact list of games you see in the image below will probably differentiate from what you see.

![CurseForge landing page](../../../assets/images/wiki/installation/curseforge/setup_1.png)

Next, you will be prompted to install Minecraft, pick the standard installation option, this is a quick installation in the default installation folder for Curse.
You may choose advanced if you wish to change specific things for your installation of Minecraft.

![CurseForge Minecraft installation page](../../../assets/images/wiki/installation/curseforge/setup_2.png)

The installation shouldn't take very long, once done you will be sent to the home page of your Minecraft installation, where you can manage your modpacks.

![CurseForge Minecraft modpack landing page](../../../assets/images/wiki/installation/curseforge/setup_3.png)

## Step 3: Choose whether you want to play an existing modpack, or make your own

You can choose whether you want to use a pre-made modpack, or create your own modpack with just the mods you want.

If you want to use a pre-made modpack, click on "Browse" on the tabs at the top of the screen, and search for "MineColonies Official", created by Raycoms.
From here you can install the pack, CurseForge will download all the mods, once it's done you can click "Play" and start having fun, it's that simple.

If you want to make your own pack, and manage the mods yourself, please continue to step 4.

## Step 4: Creating your own modpack

Creating your own modpack is not that difficult, click on "Create" at the top, name the profile whatever you like.
The important parts lie in the options below, note that the most of these options may not be changed anymore after creating the pack. So make sure you choose correctly.

First have to select the Minecraft version you want to play in, choose whatever you want.

> **Note:** Please set the version dropdown in the top right on this page to the same version, so you get the most correct instructions for that specific version.

{% version range="1.21--" %}
For the game type, select **NeoForge**, any other modloader won't work.

The modloader version, you may ignore, just pick what it tells you to.
(Usually the latest version of NeoForge), this is the only option you can change after creating the profile.
{% /version %}
{% version range="--1.20.6" %}
For the game type, select **Forge**, any other modloader won't work.

The modloader version, you may ignore, just pick what it tells you to.
(Usually the latest version of Forge), this is the only option you can change after creating the profile.

{% version range="1.20.1" %}
> **Note:** For 1.20.1, we recommend using Forge version **47.1.3**, any version above this is unstable.
{% /version %}
{% /version %}

![CurseForge modpack creation popup](../../../assets/images/wiki/installation/curseforge/create.png)

Once you are happy with your settings, click on "Create" to finish the modpack creation.

CurseForge will now create the modpack and start downloading the files from Minecraft to start the creation of your instance, this will take a little bit of time to finish.
You may already continue to step 5 whilst this process is busy.

## Step 5: Adding MineColonies and dependencies

In order to add mods to your modpack, click on the modpack (if you come from step 4 you should already be here) and click on "Add More Content".
This will bring you to the screen to search for mods.

Look for "MineColonies", and click on "Install". CurseForge will automatically add MineColonies and it's dependencies to the mods list.
Feel free to add anything else you like to the pack, note that we cannot give support for custom made packs including anything else besides our own mods!

> **Note:** CurseForge makes a best attempt at installing the correct dependency versions, but may not always go correctly.
> If during startup your game tells you that you have the wrong versions of certain mods, find the faulty mods in CurseForge, click on the name of the mod.
> This will bring you to the detail page of the mod in question. On the tabs you can click on "Versions" and from here you can install a specific version of a certain mod.
>
> ![CurseForge specific mod version installation page](../../../assets/images/wiki/installation/curseforge/install_version.png)

## Step 6: Start Minecraft

In the top right, click the "Play" button, this will start the regular Minecraft launcher. Login to your Microsoft account if you aren't logged in, and start the game.

## Step 7: Troubleshooting

If your game does not start up correctly, you are getting some error on startup and unsure of what to do, join our {% social_link id="discord" /%} server and ask in our help channels what is wrong!


---
# File: MinecoloniesWiki\src\content\wiki\installation\gdlauncher.mdoc

---
type: page
title: GDLauncher
---

Installing MineColonies via GDLauncher, a custom Minecraft launcher, is the recommended method.

- [Getting GDLauncher](#getting-gdlauncher)
- [Setting Up GDLauncher](#setting-up-gdlauncher)
  - [Account](#account)
  - [Create an Instance](#create-an-instance)
- [Installing MineColonies Mod Only](#installing-minecolonies-mod-only)
- [Installing MineColonies Official Modpack](#installing-minecolonies-official-modpack)
    
## Getting GDLauncher

GDLauncher can be downloaded [here](https://gdevs.io/#downloadContainer). Simply choose the version for your operating system and run the file.

![GDLauncher Download](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_1.png)

## Setting Up GDLauncher

Once you've installed GDLauncher, setting it up is simple.

### Account

When you first open GDLauncher, it'll ask you to log in to your Minecraft account. Enter your account information and click Sign In.

![Log In](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_2.png)

### Create an Instance

Once you're logged in, you'll be able to create an "instance." Instances are GDLauncher's way of separating each version of Minecraft and each different set of mods you wish to install.

To create an instance, click the + button in the bottom left.

![Instance Button](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_3.png)

The steps past this point differ, depending on whether you're installing the mod or the modpack.

## Installing MineColonies Mod Only

Change the dropdowns to select the latest Forge version for the latest version of Minecraft.

![Changing Dropdowns](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_4.png)

Next, click the white arrow in the bottom right. It'll prompt you to enter a name for your instance; enter whatever you'd like.

![Instance Name](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_5.png)

Once you're done choosing a name, click the bottom-right arrow again. Wait for GDLauncher to finish installing Forge. Once it's done, you should see the instance in the main menu (along with any other instances). Right-click it and click Manage.

![Manage Button](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_6.png)

Once you're in the Manage Instance menu, go to the Mods tab and click the Add Mod button at the top.

![Mods Tab](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_7.png)

Search for "MineColonies" at the top, then click Install. (You can click elsewhere on the mod's icon to read the description.) GDLauncher will automatically install Structurize, a dependency of MineColonies.

![MineColonies Search](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_8.png)

You're ready to play! To launch Minecraft, close out of the instance manager and left-click on the instance icon (in the main menu).

## Installing MineColonies Official Modpack

When creating an instance, change from Vanilla to Twitch at the top. Search for "MineColonies Official".

![Searching in Twitch Tab](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_9.png)

Hover over the top result and click Download Latest (or Explore / Versions to read the description). It'll prompt you to choose a name for your instance.

![Instance Name](../../../assets/images/wiki/installation/gdlauncher/gdlauncher_10.png)

When you're done, click the white arrow in the bottom left. GDLauncher will start installing the modpack.

Once GDLauncher is finished, **you're done!** Just click on the instance icon to launch Minecraft. Have fun!



---
# File: MinecoloniesWiki\src\content\wiki\installation\installation.mdoc

---
type: page
title: Installation
sections:
 - installation/curseforge
 - installation/gdlauncher
 - installation/manual
---

There are a variety of ways to install MineColonies, in this installation guide we will describe three possible methods to install MineColonies.

- [CurseForge](/wiki/installation/curseforge)
- [GDLauncher](/wiki/installation/gdlauncher)
- [Manual Installation](/wiki/installation/manual)

> **Note:** You can use any launcher you want, however the setup will be slightly different, please use the installation guides for your respective launcher.


---
# File: MinecoloniesWiki\src\content\wiki\installation\manual.mdoc

---
type: page
group: installation/installation
title: Manual Installation
---

## Installing MineColonies Mod on your original Minecraft Launcher

- This process is for installing only the MineColonies mod in your original Minecraft launcher. This is not a modpack installation guide.
- Installation of the MineColonies mod is very simple. It does require Forge to be able to integrate mods into the Minecraft game, though.

{% version range="--1.20.6" %}
> **Note:** We cannot offer support on the installation of Forge, however, there are many excellent (and more in-depth) tutorials online. Just Google 'how to install Minecraft Forge' to find some.
{% /version %}
{% version range="1.21--" %}
> **Note:** We cannot offer support on the installation of NeoForge, however, there are many excellent (and more in-depth) tutorials online. Just Google 'how to install Minecraft NeoForge' to find some.
{% /version %}

---

{% version range="--1.20.6" %}
## Step 1: Download Forge

Go to the [Forge Download page](http://files.minecraftforge.net) and download the **installer** for your Minecraft version.

## Step 2: Run Forge Installer

Run the downloaded version and make sure it's on Install Client (or Server if you plan to run a Minecraft server). The installation path is preset according to Minecraft's default installation path (`C:/Users/<user>/AppData/Roaming/.minecraft`). If you have it installed in a different directory, change the installation directory.
When it's done installing, you will see a notification the installation has successfully completed (image 2).

![Forge installer main menu](../../../assets/images/wiki/installation/manual/forge_1.png)

![Forge installed notification](../../../assets/images/wiki/installation/manual/forge_2.png) 
{% /version %}
{% version range="1.21--" %}
## Step 1: Download NeoForge

Go to the [NeoForge Download page](https://projects.neoforged.net/neoforged/neoforge) and download the **installer** for your Minecraft version.

## Step 2: Run NeoForge Installer

Run the downloaded version and make sure it's on Install Client (or Server if you plan to run a Minecraft server). The installation path is preset according to Minecraft's default installation path (`C:/Users/<user>/AppData/Roaming/.minecraft`). If you have it installed in a different directory, change the installation directory.
When it's done installing, you will see a notification the installation has successfully completed (image 2).

![NeoForge installer main menu](../../../assets/images/wiki/installation/manual/neo_1.png)

![NeoForge installed notification](../../../assets/images/wiki/installation/manual/neo_2.png) 
{% /version %}

## Step 3: Download MineColonies

Go to the [MineColonies CurseForge Download page](https://www.curseforge.com/minecraft/mc-mods/minecolonies/files/all?showAlphaFiles=show) site and download the latest version of MineColonies for the correct Minecraft version.

## Step 4: Download Dependencies

Do the same as step 3 above, but for all of the dependencies for MineColonies for your current Minecraft version. These are:

- [Structurize](/wiki/dependencies/structurize)
- [Domum Ornamentum](/wiki/dependencies/domumornamentum)
- [BlockUI](/wiki/dependencies/blockui)
- [Multipiston](/wiki/dependencies/multipiston)

## Step 5: Copy the jar files to the mods folder

Open the folder `C:/Users/<user>/AppData/Roaming/.minecraft/mods`, you can either go to this folder directly, or type `%appdata%` in the Windows start menu.
This will bring you directly to the `C:/Users/<user>/AppData/Roaming` folder on your computer. Then open the `.minecraft/mods` folder.

Once you are in the mods folder, copy all of the jars you downloaded in step 3 and 4 into this folder.

> **Note:** **DO NOT extract the files** if the option appears. Just put the .jar file in here without extracting its contents.

## Step 6: Start Minecraft

Run your original Minecraft launcher. Make sure you have the {% version range="1.21--" inline=true %}**NeoForge**{% /version %}{% version range="--1.20.6" inline=true %}**Forge**{% /version %} profile loaded and hit play. (You might need to go to Installations and create a new installation first.)

![Minecraft Launcher](../../../assets/images/wiki/installation/manual/launcher.png) 

That's it. If you followed these steps, you are ready to play MineColonies!

## Step 7: Troubleshooting

If your game does not start up correctly, you are getting some error on startup and unsure of what to do, join our {% social_link id="discord" /%} server and ask in our help channels what is wrong!


---
# File: MinecoloniesWiki\src\content\wiki\items\ancienttome.mdoc

---
type: item
item: minecolonies/ancienttome
---
The Ancient Tome is a glowing book that's dropped by raiders (barbarians, pirates, vikings, egyptian raiders, etc) during raids. The Ancient Tome cannot be crafted and is only available as a drop. 

## Using the Ancient Tome

There are two uses for the Ancient Tome, one use for the player and one for the {% worker name="enchanter" /%}. The player's use is that if you have the Ancient Tome in your inventory, it will glow when there is going to be a raid that night. The {% worker name="enchanter" plural=true /%} use is to use the experience they collect from other workers to turn the Ancient Tome into an enchantment book. When they do this, it will use up the Ancient Tome.



---
# File: MinecoloniesWiki\src\content\wiki\items\barrels.mdoc

---
type: item-combined
title: Barrels
items:
  - domum_ornamentum/blockbarreldeco_onside
  - domum_ornamentum/blockbarreldeco_standing
infobox:
  cols: 1
---
Barrels are decorative blocks that can be used in schematics to further decorate buildings, barrels are crafted in the {% building name="sawmill" /%}.

There are 2 variations to the barrels, horizontal and vertical.


---
# File: MinecoloniesWiki\src\content\wiki\items\blocktagsubstitution.mdoc

---
type: item
item: structurize/blocktagsubstitution
---
The Tag Anchor Block is a creative-only item, whose sole purpose is to act as the tag-containing (via the {% item name="structurize/sceptertag" /%}) anchor for non-upgradeable decorations. There should typically be at most one of these in any given schematic. (It is possible to include more than one, but only one can be the actual anchor, and that's the only one that matters.)

Note that you should *only* use the Tag Anchor for non-upgradeable decorations. For regular huts, upgradable decorations, and other schematics that have "main" blocks (like the fields), you should set that block as the anchor instead and not use any Tag Anchors.

When built by a {% worker name="builder" /%} or pasted normally, the Tag Anchor Block will disappear. As such it is only useful for tags that affect the build itself (but no other tags are interesting for decorations anyway). When pasting the schematic for editing, ensure that the left paste option (or "Schematic Paste", for 1.19) is used to retain the block (similar to other {% item_page name="placeholderblocks" /%}).

In 1.19+ only (with Structurize 456+), the Tag Anchor can "absorb" another block by using Pick Block (middle-click by default) on a block with the anchor in hand. After placing this special anchor in the world, scanning, and being built, it will turn into the absorbed block instead of into air. This is primarily intended for cases where you want the anchor position somewhere specific but need a non-air block in that position.



---
# File: MinecoloniesWiki\src\content\wiki\items\caliper.mdoc

---
type: item
item: structurize/caliper
---
The calipers are for measuring the size of lines, surfaces, and areas. They cannot be crafted and are only available in creative mode.

## Using the Calipers

To use the calipers to measure a line, right-click on the last block on each end of the line.

![Measuring a line](../../../assets/images/wiki/misc/calipersstraight.png)

To use the calipers to measure a surface, right-click on the front corner on one side and the back corner on the opposite side. You will get the length and width of the box, as well as the diagonal length.

![Measuring a surface](../../../assets/images/wiki/misc/calipersdiagonal.png)

To use the calipers to measure a box, right-click on the front bottom corner on one side and the back top corner on the opposite side. You will get the length, width, and height of the box, as well as the diagonal length.

![Measuring a box](../../../assets/images/wiki/misc/caliperscube.png)



---
# File: MinecoloniesWiki\src\content\wiki\items\clipboard.mdoc

---
type: item
item: minecolonies/clipboard
---
The clipboard is an easy way to see all the requests your workers have (that are not being fulfilled by a {% worker name="courier" /%}).

## Using the Clipboard

When you first make the clipboard and right-click anywhere, you will get a message to sneak + right-click on the {% building name="townhall" /%} to register the clipboard.
An example message will say:

> No colony set (sneak + use the clipboard on the Town Hall)

After you register the clipboard, this message will appear:
An example message will say:

> Noting requests on the clipboard from [colony name].

Now when you right-click while holding the clipboard, you will see all your citizens' requests on the clipboard (that are not being fulfilled by a {% worker name="courier" /%}) as long as you're close to your colony!

![Clipboard GUI](../../../assets/images/wiki/gui/items/clipboardgui1.png)

If you click on the exclamation mark in the top right, you will only see requests for things that workers need *right now.*

![Clipboard GUI Important Requests](../../../assets/images/wiki/gui/items/clipboardgui2.png)



---
# File: MinecoloniesWiki\src\content\wiki\items\colony_banner.mdoc

---
type: item
item: minecolonies/colony_banner
---
The Colony Banner is a banner that uses the design set in the {% building name="townhall" /%}. Your {% worker name="knight" /%} will wear the design on their shields, and it is also used in some schematics.



---
# File: MinecoloniesWiki\src\content\wiki\items\compost.mdoc

---
type: item
item: minecolonies/compost
---
There are two types of compost. There is composted dirt which is used in the {% building name="florist" /%} schematics, and there is the compost item which is used by the {% worker name="florist" /%} and the {% worker name="farmer" /%} like bone meal.

## Using Compost

The {% worker name="composter" /%} uses a {% item name="minecolonies/barrel_block" /%} to create compost (the item). It works like bone meal to increase the speed of plant growth. The Florist must have compost to grow flowers. The Farmer does not need compost or bone meal to work, but prefers it (this is toggleable in the {% building name="farmer" /%} GUI).

Composted dirt can be crafted by the Farmer. It is used in the Flower Shop schematics.


---
# File: MinecoloniesWiki\src\content\wiki\items\compost_barrel.mdoc

---
type: item
item: minecolonies/barrel_block
---
The compost barrel is used by the {% worker name="composter" /%} to make {% item name="minecolonies/compost" /%}. It can be crafted at the {% building name="sawmill" /%}.

## Using the Compost Barrel

The compost barrel is primarily used by the Composter to make compost, but it can also be used by the player to do the same thing (right-click while holding a compostable item to raise the level of compost in the compost barrel). It can also be used as decoration.



---
# File: MinecoloniesWiki\src\content\wiki\items\construction_tape.mdoc

---
type: item
item: minecolonies/blockconstructiontape
---
Construction tape is a unique item that automatically generates when a building is placed. It can be crafted at the {% building name="sawmill" /%} or by the player for use as decoration.

## Using Construction Tape

Construction tape shows you the borders of buildings to be upgraded, built, repaired, or repositioned. It is placed one block outside of the building's footprint.

Construction tape can also be used as decoration, however, remember that construction tape is not solid and can be walked through. It is also directional, meaning it changes orientation depending on how you place it.

#### For more information on the build system, please see the {% worker name="builder" /%} page.


---
# File: MinecoloniesWiki\src\content\wiki\items\decorationcontroller.mdoc

---
type: item
item: minecolonies/decorationcontroller
---

The decoration controller is used to create custom [Schematics](/wiki/tutorials/schematics). See the Schematics page for how they're used.

The decoration controller is only available through creatively giving it to yourself. It cannot be crafted.

## Using the Decoration Controller

To use the decoration controller, right-click on it to bring up the GUI.

![Decoration Controller GUI](../../../assets/images/wiki/gui/decocontrollergui1.png)

You can repair or upgrade decorations from the GUI, assuming that the decoration can be upgraded.



---
# File: MinecoloniesWiki\src\content\wiki\items\extra_blocks.mdoc

---
type: item-combined
title: '"Extra" Blocks'
items:
 - domum_ornamentum/cactus_extra
 - domum_ornamentum/brick_extra
 - domum_ornamentum/mossy_cobblestone_extra
 - domum_ornamentum/cobblestone_extra
 - domum_ornamentum/wheat_extra
 - domum_ornamentum/paper_extra
---
"Extra" blocks are used for decoration. They can be crafted manually or at various crafters. The dyed version of each block is the same recipe except the middle item (i.e., row 2, item 2) is replaced by the appropriate dye.

| Extra Block       | Crafter                                                             |
| ----------------- | ------------------------------------------------------------------- |
| Brick             | {% building name="stonemason" /%}                                   |
| Cactus            | {% building name="sawmill" /%} or {% building name="stonemason" /%} |
| Cobblestone       | {% building name="stonemason" /%}                                   |
| Mossy Cobblestone | {% building name="stonemason" /%}                                   |
| Paper             | {% building name="plantation" /%}                                   |
| Wheat             | {% building name="baker" /%} or {% building name="stonemason" /%}   |


---
# File: MinecoloniesWiki\src\content\wiki\items\floating_carpet.mdoc

---
type: item-combined
title: Floating Carpet
items:
  - domum_ornamentum/white_floating_carpet
---
Floating carpet is a decoration. It allows you to place carpet in the air without using string to hold it up. Other than that, it's the exact same as vanilla Minecraft carpet.

Floating carpet can be crafted at the {% building name="fletcher" /%}.

## Using Floating Carpet

Use floating carpet for awnings or other builds where you would use carpet with string.



---
# File: MinecoloniesWiki\src\content\wiki\items\gates.mdoc

---
type: item-combined
title: Gates
items:
  - minecolonies/gate_iron
  - minecolonies/gate_wood
---
The Gates are like large doors, but harder for raiders to break. Iron Gates take even longer than Wooden Gates for raiders to break, and there are a couple of [researches](/wiki/systems/research) at the {% building name="university" /%} to make them take *even longer.*

However, players and citizens can still use them as normal doors by right-clicking. They will also be triggered by redstone signals.

## Using the Gates

To place a Gate:
- Have several stacked in your inventory
- Right-click the stack where you want it to be placed
- The entire stack will be placed, filling as much space as it can

> The maximum size of a Gate is 5x4 blocks.

## Here are a few screenshots:

### Before Placing

{% image_row %}![Wooden Gate Before Placing](../../../assets/images/wiki/misc/gate_before_placing_wooden.png) ![Iron Gate Before Placing](../../../assets/images/wiki/misc/gate_before_placing_iron.png){% /image_row %}

### After Placing (Closed)

{% image_row %}![Closed Wooden Gate After Placing](../../../assets/images/wiki/misc/gate_after_placing_closed_wooden.png) ![Closed Iron Gate After Placing](../../../assets/images/wiki/misc/gate_after_placing_closed_iron.png){% /image_row %}

### After Placing (Open)

{% image_row %}![Open Wooden Gate After Placing](../../../assets/images/wiki/misc/gate_after_placing_open_wooden.png) ![Open Iron Gate After Placing](../../../assets/images/wiki/misc/gate_after_placing_open_iron.png){% /image_row %}


---
# File: MinecoloniesWiki\src\content\wiki\items\magic_potion.mdoc

---
type: item
item: minecolonies/magicpotion
---
Magic Potions are an item that {% worker name="alchemist" /%} can craft using {% item name="minecolonies/mistletoe" /%} after completing the {% research_link name="minecolonies/combat/druidpotion" /%} research. Druids will request Magic Potions to increase the strength and duration of their potions.



---
# File: MinecoloniesWiki\src\content\wiki\items\meshes.mdoc

---
type: item-combined
title: Meshes
items:
  - minecolonies/sifter_mesh_flint
  - minecolonies/sifter_mesh_string
  - minecolonies/sifter_mesh_diamond
  - minecolonies/sifter_mesh_iron
---
MineColonies adds four meshes, used by the {% worker name="sifter" /%} to sift through items. The Sifter will automatically unlock these recipes for your colony once the requirements are met. String meshes are the most fragile, followed by flint meshes, while iron is more durable, and diamond the most resilient. Better meshes can also sift out materials earlier meshes would have missed.

| Mesh    | Requires                                       | Crafter                         | Cost         |
| ------- | ---------------------------------------------- | ------------------------------- | ------------ |
| String  | {% building name="sifter" /%} at least level 1 | {% worker name="fletcher" /%}   | 1 String     |
| Flint   | {% building name="sifter" /%} at least level 3 | {% worker name="stonemason" /%} | 1 Flint      |
| Iron    | {% building name="sifter" /%} at least level 4 | {% worker name="blacksmith" /%} | 1 Iron Ingot |
| Diamond | {% building name="sifter" /%} at least level 5 | {% worker name="mechanic" /%}   | 1 Diamond    |


---
# File: MinecoloniesWiki\src\content\wiki\items\mistletoe.mdoc

---
type: item
item: minecolonies/mistletoe
---
Mistletoe is an item that {% worker name="alchemist" /%} can gather randomly with shears.

## Using the Mistletoe

Mistletoe is used by {% worker name="alchemist" plural=true /%} to craft magic potions for {% worker name="druid" /%}. These potions increase the strength and duration of Druid potions.



---
# File: MinecoloniesWiki\src\content\wiki\items\multipiston.mdoc

---
type: item
item: multipiston/multipistonblock
---
The multi-piston looks like a smooth andesite block with a hole in each face. It lets you move multiple blocks at a time.

The multi-piston can be crafted at the {% building name="mechanic" /%}.

## The Multi-Piston's GUI

Place the multi-piston beside, above, or below the blocks you want to move, then right-click it to open the GUI. 

![Multi-Piston GUI 1](../../../assets/images/wiki/gui/multiblockgui.png)

You will see 4 blue arrows, a + and a - with Range: and Speed: in the center. The arrows refer to the cardinal directions, up for North, down for South, right for East, and left for West. The + is to raise and the - is to lower.  The range is 10 blocks and the speed: is 1-3, 3 being the fastest. 

You will need to left-click on one of the arrows, +, or - and it will turn green. This shows the directions the blocks will move when the multi-piston is activated by redstone. Then right-click on another arrow, +, or - for the direction the blocks will move when the redstone signal is off. This arrow will turn red.

> **Note:** To set the red arrow, the blocks that you want to move must already be there.

> **Note:** All blocks being moved must be the same in order to work.

> **Note:** There must be enough room for all the blocks to move or it will stop.

![Multi-Piston GUI 2](../../../assets/images/wiki/misc/multiblockroom.png)

![Multi-Piston GUI 3](../../../assets/images/wiki/misc/multiblockblock.png)




---
# File: MinecoloniesWiki\src\content\wiki\items\placeholderblocks.mdoc

---
type: item-combined
title: Placeholder Blocks
items:
  - structurize/blocksubstitution
  - structurize/blocksolidsubstitution
  - structurize/blockfluidsubstitution
infobox:
  show: false
---
The placeholder blocks (also called substitution blocks) are tan, brown, and blue. The tan block is the placeholder block, the brown is the solid placeholder block, and the blue is the fluid placeholder block. Each serves its own function. 

All kinds of placeholder blocks can be crafted at the {% building name="sawmill" /%}.

You can make and use these blocks in survival, but they are mainly used to scan schematics.

{% item_infobox item="structurize/blocksubstitution" %}
## Placeholder

The regular placeholder is the most common placeholder block, it is essentially a "keep what is already here" placeholder.

- If the original block is air, keep it as air.
- If the original block is a non solid block, keep the same block.
- If the original block is a solid block, keep the same block.
- If the original block is a fluid, keep the same fluid.

> ![Placeholder Example](../../../assets/images/wiki/misc/example_placeholder.png)
{% /item_infobox %}

{% item_infobox item="structurize/blocksolidsubstitution" %}
## Solid Placeholder

The solid placeholder is mostly used for foundations of buildings, these will place a solid block in case there is a non solid block there.

- If the original block is air, place a solid block.
- If the original block is a non solid block, replace with a solid block.
- If the original block is a solid block, keep the same block.
- If the original block is a fluid, replace with a solid block.

> When the {% worker name="builder" /%} is responsible for placing a solid block, it will use the Fill Block defined in the {% building name="builder" /%} it's GUI.

> ![Solid Placeholder Example](../../../assets/images/wiki/misc/example_solid_placeholder.png)
{% /item_infobox %}

{% item_infobox item="structurize/blockfluidsubstitution" %}
## Fluid Placeholder

The fluid placeholder is intended for setting up fluid in schematics. The fluid placeholder will place the preferred fluid for the given dimension (i.e. water in overworld, lava in the nether, etc.).
If there are already blocks in place of the fluid placeholder, they will stay there.

- If the original block is air, place a fluid.
- If the original block is a non solid block, replace with a fluid.
- If the original block is a solid block, keep the same block.
- If the original block is a fluid, keep the same fluid.

> Be careful when using fluid placeholders next to waterlogged blocks when schematics are intended to be placed in the nether.
> This can cause the waterlogged blocks to come into contact with lava and create cobblestone/stone/obsidian.

> ![Fluid Placeholder Example](../../../assets/images/wiki/misc/example_fluid_placeholder.png)
{% /item_infobox %}


---
# File: MinecoloniesWiki\src\content\wiki\items\plate_armor.mdoc

---
type: item-combined
title: Plate Armor
items:
  - minecolonies/plate_armor_chest
  - minecolonies/plate_armor_helmet
  - minecolonies/plate_armor_boots
  - minecolonies/plate_armor_legs
---
Plate armor is a type of armor added by MineColonies. It is moderately durable and offers excellent protection compared to conventional iron armor, but has poor enchantability. Plate armor is crafted by the {% worker name="blacksmith" /%} after their {% building name="blacksmith" /%} has reached level 4 and the Plate Armor [research](/wiki/systems/research) has been completed at the {% building name="university" /%} .

## Material Costs

| Item       | Initial Crafting Requirement     |
| ---------- | -------------------------------- |
| Helmet     | 4 iron ingots, 1 leather, 1 coal |
| Chestplate | 7 iron ingots, 1 leather, 3 coal |
| Pants      | 6 iron ingots, 1 leather, 4 coal |
| Boots      | 3 iron ingots, 1 leather, 1 coal |

Like most other recipes, the Blacksmith may improve the recipe efficiency over time, reducing these costs. They will always cost at least one iron, one leather, and one coal.


---
# File: MinecoloniesWiki\src\content\wiki\items\postbox.mdoc

---
type: item
item: minecolonies/blockpostbox
---
The postbox is for the player to request items from the {% building name="warehouse" /%}. A {% worker name="courier" /%} will deliver them to the postbox.

The postbox can be crafted at the {% building name="sawmill" /%} or by the player.

## Using the Postbox

To use the postbox, right-click on it to bring up the GUI.

![Postbox GUI](../../../assets/images/wiki/gui/postboxgui.png)

You can search for the item you want in the top box. When you find the item, enter in the field beside it how many you want, then press request. The requested items will show on the right side of the screen. Once a Courier fulfills the request, the items will be in the postbox's inventory.

There is also an option to 'Deliver what's available.' If, for example, you have this on and you request 64 stone, but your {% building name="warehouse" /%} only has 33 (and you don't have a {% worker name="stonesmelter" /%} to smelt more), a {% worker name="courier" /%} will bring you the 33 stone instead of none. (This option will not stay on if you exit the postbox GUI, but any requests made with it turned on will stay the same.)



---
# File: MinecoloniesWiki\src\content\wiki\items\rack.mdoc

---
type: item
item: minecolonies/blockminecoloniesrack
---
The rack is the MineColonies version of a chest. It can be combined with another rack to make a double rack, like a double chest. Racks are used almost exclusively in the mod its schematics.

Racks can be crafted at the {% building name="sawmill" /%}.

## Using the Rack

Use the rack like you would use a chest.



---
# File: MinecoloniesWiki\src\content\wiki\items\rallying_banner.mdoc

---
type: item
item: minecolonies/banner_rally_guards
---
The rallying banner will summon your Guards to you when used. It's especially useful in raids, as Guards don't automatically know where to find the raiders.

> Your Guards will not follow you outside the colony [border](/wiki/systems/border). To bring Guards outside the border, set them to Follow you on the second page of their tower GUI.

## Using the Rallying Banner

First, shift + right-click while holding the banner on a {% building name="guardtower" /%} or {% building name="barrackstower" /%}. This assigns the Guards in that tower to the rallying banner. (Shift + right-click on the block again to remove the tower.)

Then, shift + right-click on any other block to summon the Guards! (Shift + right-click again to dismiss them.)

At any time, you can right-click while holding the banner to open a GUI, which will show the towers currently added and the number of hired Guards at each one. You can remove the towers from this GUI, and rally/dismiss the Guards as well.

If you have the Standard research unlocked, you can "Place" your rallying banner on a grounded Colony Banner by right-clicking a grounded Colony Banner.

![Rallying Banner GUI](../../../assets/images/wiki/gui/rallyingbannergui.png)


---
# File: MinecoloniesWiki\src\content\wiki\items\resource_scroll.mdoc

---
type: item
item: minecolonies/resourcescroll
---
The resource scroll is an easy way to tell what one of your {% worker name="builder" /%} needs. It's like a portable second page of the {% building name="builder" /%} !

## Using the Resource Scroll

When you first make the resource scroll, if you right-click anywhere you will get a message to sneak + right-click on a {% building name="builder" /%} to register the resource scroll. 

![Resource Scroll Initial Message](../../../assets/images/wiki/misc/resourcebuilderinitmessage.png)

Now when you right-click while holding the resource scroll, you will see all the needed materials for the {% building name="builder" /%} you clicked on. Each needed item is displayed, along with how many of that item is in the hut's inventory, and how many are needed. These amounts will change as the {% worker name="builder" /%} places blocks and will show only what blocks the {% worker name="builder" /%} still needs to place. The blocks in black are in their inventory. The blocks in red are the ones neither you nor the {% worker name="builder" /%} has in their inventory. The blocks in green are ones you have in inventory but the {% worker name="builder" /%} needs. Yellow blocks means you have some of the necessary resource, but not enough.

![Resource Scroll GUI](../../../assets/images/wiki/gui/resourcescrollgui.png)

If you go to a {% building name="warehouse" /%} and right-click the resource scroll on the Warehouse hut, the resource scroll will show you how many of each item is currently stored in that Warehouse. This will be in green to the far right of each block.



---
# File: MinecoloniesWiki\src\content\wiki\items\sceptergold.mdoc

---
type: item
item: structurize/sceptergold
---
The build tool is *THE* most important tool in the entire mod! With it, you can place all the buildings, worker huts, and even any structure scanned by you (see [Schematics](/wiki/tutorials/schematics)) perfectly. The possibilities are endless! Watch a short video of how it works here:

#### Build Tool Video

[![Watch the video](https://img.youtube.com/vi/3Oz627IutL0/hqdefault.jpg)](https://youtu.be/3Oz627IutL0)

### Step One: Crafting

The first step is to craft the build tool. You can also get a build tool in the {% item_page name="supply_camp_and_ship" /%} in the same rack as the {% building name="townhall" /%}.

### Step Two: Designating a Building Location

To place a worker hut or building from the mod, you'll first need to craft the specific block for that building (see [Recipes](/wiki/misc/recipes)). Then, right-click with the build tool on a solid block where you want to place that building. The GUI will pop up:

![Build Tool GUI](../../../assets/images/wiki/misc/buildtool1.png)

**Left Dropdown:** Here you will see 3 general categories: My Schematics, Decorations and Huts.

- Hut Blocks/Building Blocks- When you have a Hut/Building block in your inventory it will appear in this list for you to place and view.
- Decorations- Here are several structures considered decorations that you can use and any of your own scans in this folder.
- My Schematics- In this category, you will see all the scans you have made with the {% item name="structurize/sceptersteel" /%}. If you haven't made any, this category will not show up.

**Middle Dropdown:** This is where you will see the different styles of the buildings available. The current styles (as of March 20, 2021) are Acacia, Asian, Birch, Caledonia, Cave, Dark Oak, Fortress, Jungle, Medieval Birch, Medieval Dark Oak, Medieval Oak, Medieval Spruce, Mesa, Nordic, Sandstone, Spacewars, Stone, True Dwarven, Warped, and Wooden.

**Right Dropdown:** Here you will be able to see the individual schematics, depending on what you chose:

- From My Schematics - the name of the schematic (either auto-generated or if you have named it).
- From Decorations - all decorations that are of the specified style previously chosen in the middle dropdown.
- From Huts - The level of the hut from level 1 to level 5. Even if you place a hut with level 5 selected, it'll still place as level 1. This is because you need to *upgrade* it to levels 2+.

**In the middle of the screen** are the controls to adjust the structure you are about to place.

- The blue arrows are to you move the 3D structure you are viewing (front - back - right - left).
- The orange minus and plus signs are to raise or lower the structure on the Y axis.
- The orange curved arrows are to rotate the structure left or right.
- The orange triangles in the middle are to mirror the schematic.
- The green check mark is for when you are done and want to commit to placing it.
- The red X is to cancel the whole process and turn off the ghost image of the building.

> **Note:** Once you have the GUI open, you can press the ESC button on your keyboard so that the 3D image will remain. Then, you can move around and take a better look at it from all sides to make sure it's the way you want it! Right-click with the build tool again towards the 3D image (on a block or in the air) to go back to the GUI so you can "nudge" your structure to the place and orientation that you want. You can repeat this process as many times as you want before clicking the green check mark to commit to the placement of the structure.

> **Note:** When in Creative Mode, you will also see options in the lower right. The one on the left will paste it into the world completely, including all the {% item_page name="placeholderblocks" /%}. This is useful for designing schematics. The button on the right will paste the structure into the world exactly as if a {% worker name="builder" /%} had built it (without placeholder blocks).

> **Note:** To help with planning hut locations, buildings can preview as their level 5 versions and with blue outlines. These only render if their anchor blocks — usually the hut block — are close enough to each other. This preview can be turned off in the clientside [config](/wiki/misc/configfile) settings.

### Step Three

Now that you have the structure ready to place, click on the green check mark and voilá: construction tape will appear and the building is planned! Decorations and custom schematics will automatically create a build request, but you will need to create the build request manually for hut blocks. To create the build request, go into the hut block's GUI and click the Build Options button, then Build Building. Your {% worker name="builder" plural=true /%} will then pick up a build request and start building the building.

You can check in the {% building name="townhall" /%} GUI under the Work Orders (! symbol) tab to see the queue of all build requests you have for the {% worker name="builder" plural=true /%} in your colony.


---
# File: MinecoloniesWiki\src\content\wiki\items\sceptersteel.mdoc

---
type: item
item: structurize/sceptersteel
---
The scan tool is a very useful tool. With the scan tool, you can scan any structure you like and have your {% worker name="builder" /%} build it for you! So if you build a house, wall, bridge, tower, shop or any other structure that you want in your colony, you can scan it. You can scan a structure in Singleplayer or Multiplayer (even on a server). The scanned structure will be in a {% item name="structurize/sceptergold" /%} over what you want the Builder to clear-they'll clear that area away, leaving only air! Watch a short video of how it works here:

## Scan Tool Video

[![Watch the video](https://img.youtube.com/vi/UO4ePh0N4Mc/hqdefault.jpg)](https://youtu.be/UO4ePh0N4Mc)

**Hint:** There are three placeholder blocks that help in building schematics, read more about them {% item_page name="placeholderblocks" /%}.

### Step Zero

Optionally, shift-left-click the "anchor block" (see below for more info). Usually you can skip this, but sometimes it needs to be selected manually.

### Step One

Then left-click a lower front corner (left or right makes no difference) at the ground level of the structure.

![Point 1](../../../assets/images/wiki/tutorial/scan1.png)

### Step Two

Right-click on the top opposite corner of the structure (you can use a {% item_page name="placeholderblocks" /%} for this), leaving the entire structure inside this imaginary box.

![Point 2](../../../assets/images/wiki/tutorial/scan2.png)

### Step Three

Next, right-click in the air to get the scan tool GUI.

![Scan Tool GUI](../../../assets/images/wiki/gui/scan_tool_gui.png)

Here you will be able to see the coordinates for the scan you just did and can change them. You will also be able to give your scan a name. Use the green arrow if you are ready to save your scan or the red X to cancel the scan and start over.  

# Resources

You can click on the Show Resources button to see all the blocks that are in your scan. When scrolling through the blocks that are in your scan, you have three options:

* Remove, to remove any of the blocks you see in your scan that you don't want in there.
* Replace, to replace that block with any other block you prefer for that scan.
* Remove Filtered, to remove all of the blocks you see (you're expected to use a filter to select a subset first).

WARNING: these buttons will delete actual blocks or entities in your world. It may be a good idea to scan first so you have a backup in case you remove too much!

# Anchor Block

Every blueprint needs an "anchor". This is shown as a red box when scanning and building, and is the block that the blueprint will rotate around. Most importantly, though, it's the block that defines what kind of blueprint this is.

For regular buildings, the hut block for the building must be the anchor. For upgradable decorations, the {% item name="minecolonies/decorationcontroller" /%} must be the anchor. For other decorations, you can either use a {% item name="structurize/blocktagsubstitution" /%} (if you want to use the {% item name="structurize/sceptertag" /%}) or any other block (otherwise; including air blocks).

You can select the anchor for your scan explicitly, by shift-left-clicking the desired block. If you don't do this, the scan tool will try to guess -- most of the time this works, but if you have more than one possible anchor inside your scan box then it won't be able to figure it out and you'll get an error message in chat. In that case you will have to set it manually. (Having more than one possible anchor is normal if you're scanning a building that has child buildings, or if you've included certain blocks like the {% item name="minecolonies/blockstash" /%} -- other times it might be because you've added a {% item name="structurize/blocktagsubstitution" /%} when you shouldn't have.)

{% version range="1.19--" %}
# Multiple Scan Slots

The scan tool has 10 separate slots that can hold scan coordinates and names. You can use these however you like, but one example is to scan each level of a single building to a separate slot, with some extra slots used for temporary boxes (such as using the replace function).

With the scan tool selected on your hotbar, you can switch slots by using sneak+scrollwheel, or using (sneak+)Pick-Block in air. With the GUI open, you can also press the number keys to switch slots.

To clear a slot, simply overwrite it with another scan, or delete the name in the GUI. Or to reset everything, throw away your scan tool and make another.
{% /version %}

# Command Blocks

When doing a lot of scanning, such as when making an entire custom style, it can be useful to use command blocks to automate the scanning process using the [scan command](/wiki/systems/command#structurize-commands).

In 1.19+ only, you can click the scan tool on a command block using Pick-Block:

* With pick-block alone, the scan command is copied to the current slot of the scan tool.
* With shift+pick-block, the current slot of the scan tool is pasted to the command block.

After interacting with a command block in this way at least once, you can press your Scan Tool Teleport keybind (unset by default) to quickly teleport between your command block and the corresponding build.



---
# File: MinecoloniesWiki\src\content\wiki\items\sceptertag.mdoc

---
type: item
item: structurize/sceptertag
---

The Tag Tool is a creative-only item used to help build custom [schematics](/wiki/tutorials/schematics). It allows "tags" (specific words or phrases) to be attached to specific blocks in the schematic. Any tag can be attached to any block, but only specific ones will do something. Some building types require specific tags in order to function correctly, while others are optional.

## Using the Tag Tool

1. Shift-right-click the anchor block of the schematic that you want to view or edit. This will highlight it with a red border and make the associated tags visible.

    * For a building with a hut block (also including the fields), the hut block is the anchor block.
    * For an upgradeable decoration, the {% item name="minecolonies/decorationcontroller" /%} is the anchor block.
    * For any other decoration, you must place a {% item name="structurize/blocktagsubstitution" /%} as the anchor block.

2. While you are holding the tag tool, you will see all existing tags overlaying their blocks in-world, and they will be highlighted with a white border.

3. Right-click the tag tool in the air to open its GUI. This allows you to type in the tag that you want to apply or remove. Close the GUI afterwards. The GUI also shows a list of all currently tagged blocks.


4. Left-click on a block to apply the current tag to that block. Left-click again to remove the tag.

    * It is possible (although unusual) for a single block to have multiple tags. To remove a tag it must be the current tag entered in the GUI.

5. When finished, use the {% item name="structurize/sceptersteel" /%} to save the schematic, including the tags. Tags will only take effect if the anchor block for the scan is the same anchor used to store the tags. (When there is only one anchor block in the scan area, it will be selected automatically; if you are including multiple hut blocks in a single schematic then you may need to select the right one manually.)

## Current Tags

The following tags are currently implemented (more may be added from time to time):

* `groundlevel`

    This tag is supported by all huts and decorations. It is optional. When included, it should only appear on exactly one block in the schematic that is at the nominal "ground level" of the building -- i.e. any blocks below this should be an underground "basement" area, blocks at the same level should be dirt/grass/other ground blocks (often {% item_page name="placeholderblocks" /%}) are used for this, while underground can be either solid or light placeholders outside of the intended basement area), and blocks above should be the actual building. (If it is specified on more than one block, then only one will take effect. This is harmless if they're all on the same y level but can be confusing if not; hence the recommendation to stick with one at most.)

    When not included, the block immediately below the anchor block is assumed to be the ground level. It is recommended that the `groundlevel` tag be explicitly specified whenever the anchor block is placed anywhere other than directly on the intended ground level (or to just always specify it regardless, for safety).

    This affects the default alignment of the {% item name="structurize/sceptergold" /%} -- assuming that the player clicks the build tool on the ground (as is usually the case) it will align the groundlevel of the schematic to this position as well, so that they are less likely to bury the building underground or put the basement above.

    As such, this should be used even for "underground" styles -- in this case the ground level means the nominal "floor" of the colony area (where they're clicking the build tool), not necessarily the surface ground level.

* `leisure`

    Supported only by Decoration Controllers, not huts or the tag anchor. It is optional. When included, it must appear directly on the decoration controller itself.
    
    Including this tag indicates that this decoration is intended as a "leisure site" for colonists to visit when they're otherwise idle. Leisure sites may optionally include `sit` tags, and colonists will also just wander around.
    
    In addition to tagged decorations, colonists will also always treat L3+ Town Hall and L1+ Mystical Site, Library, and Tavern as leisure sites.

* `invisible`

    1.19+ only; supported by all huts and decorations. It is optional. When included, it must appear directly on the anchor block (hut, decoration controller, or tag anchor).
    
    When present, this marks the corresponding schematic blueprint as "invisible" to normal players -- it will not be listed in the build tool in survival mode at all. In creative mode, the schematic will be visible but specially marked.
    
    This is intended to be placed on buildings or decorations that are not intended to be built on their own, but rather only as children of some other parent building/decoration.
    
    The {% building name="barrackstower" /%} is implicitly invisible even if not tagged as such, for backwards compatibility.

* Building-specific tags

    The following tags are only used by specific building types:

    | Tag            | Used By                             | Amount      | Description                                                                                                                               |
    | -------------- | ----------------------------------- | ----------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
    | `sit`          | {% building name="cook" /%}         | 0-many      | A "chair" where colonists will sit to eat their food                                                                                      |
    | `sit`          | {% building name="tavern" /%}       | 0-many (~4) | A "chair" where visitors will occasionally sit                                                                                            |
    | `sit`          | (leisure)                           | 0-many      | A "chair" where colonists will occasionally sit                                                                                           |
    | `cobble`       | {% building name="miner" /%}        | Always 1    | The block behind the top-most ladder at the start of the mineshaft (traditionally cobblestone, but may be something else)                 |
    | `ladder`       | {% building name="miner" /%}        | Always 1    | The top-most ladder at the start of the mineshaft                                                                                         |
    | `bamboo`       | {% building name="plantation" /%}   | level-based | A block that bamboo can be planted on ([legacy](/wiki/tutorials/schematics#plantation-fields))                                            |
    | `cactus`       | {% building name="plantation" /%}   | level-based | A block that cactus can be planted on ([legacy](/wiki/tutorials/schematics#plantation-fields))                                            |
    | `sugar`        | {% building name="plantation" /%}   | level-based | A block that sugarcane can be planted on ([legacy](/wiki/tutorials/schematics#plantation-fields))                                         |
    | `portal`       | {% building name="netherworker" /%} | Always 1    | An obsidian block in the bottom of the nether portal (with air above)                                                                     |
    | `vault`        | {% building name="netherworker" /%} | 0-1         | A block in an enclosed room that the nether miner can stand on while "away"                                                               |
    | `work`         | {% building name="archery" /%}      | level-based | The block the {% worker name="archer" /%} in Training stands on to shoot at targets (Note: If no tags are used, they look for glowstone.) |
    | `job=miner`    | {% building name="miner" /%}        | Always 1    | Sets the mine's worker to be only {% worker name="miner" /%}                                                                              |
    | `job=quarrier` | {% building name="miner" /%}        | Always 1    | Sets the mine's worker to be only {% worker name="quarrier" /%}                                                                           |
    | `job=knight`   | {% building name="guardtower" /%}   | Always 1    | Sets the Guard Tower's worker to be only {% worker name="knight" /%}                                                                      |
    | `job=ranger`   | {% building name="guardtower" /%}   | Always 1    | Sets the Guard Tower's worker to be only {% worker name="archer" /%}                                                                      |
    | `job=druid`    | {% building name="guardtower" /%}   | Always 1    | Sets the Guard Tower's worker to be only {% worker name="druid" /%}                                                                       |



---
# File: MinecoloniesWiki\src\content\wiki\items\scrolls.mdoc

---
type: item-combined
title: Scrolls
items:
  - minecolonies/scroll_area_tp
  - minecolonies/scroll_tp
  - minecolonies/scroll_buff
  - minecolonies/scroll_guard_help
  - minecolonies/scroll_highlight
---
MineColonies adds 5 magical scrolls:

- Ultrasafe Colony Teleport Scroll
- Ultrasafe Colony Group Teleport Scroll
- Sacred Scroll of Regeneration
- Spatial Guard Reinforcements Scroll
- Worker-Where-Are-You Scroll

To use a scroll, hold down right-click with the scroll in your main hand. Scrolls have a small chance to fail. Whether it fails or not, the scroll will disappear after using it.

## Ultrasafe Colony Teleport Scroll

This scroll will teleport you to your {% building name="townhall" /%} when used. You must register it to your {% building name="townhall" /%} first by shift + right-clicking on the {% building name="townhall" /%} hut block.

It is crafted by the {% worker name="enchanter" /%} with 3 paper, 1 compass, and 1 {% item name="structurize/sceptergold" /%}.

## Ultrasafe Colony Group Teleport Scroll

This scroll will teleport you and any players near you to your {% building name="townhall" /%} when used. You must register it to your {% building name="townhall" /%} first by shift + right-clicking on the {% building name="townhall" /%} hut block.

It is crafted by the {% worker name="enchanter" /%} with 3 Ultrasafe Colony Teleport Scrolls. For the {% worker name="enchanter" /%} to unlock this recipe, their {% building name="enchanter" /%} must be at least level 2.

## Spatial Guard Reinforcements Scroll

Calls Guards to you for a limited time. Like the {% item name="minecolonies/banner_rally_guards" /%}, but can only summon one tower of Guards at a time and has a finite time period. Must be registered to your {% building name="townhall" /%} and to a {% building name="guardtower" /%}/{% building name="barrackstower" /%}  (register it by shift + right-clicking on the hut block).

This scroll is crafted by the {% worker name="enchanter" /%} with 1 Ultrasafe Colony Teleport Scroll, 5 lapis lazuli, 1 ender pearl, and 1 paper. For the {% worker name="enchanter" /%} to unlock this recipe, you must have the {% research_link name="minecolonies/technology/morescrolls" /%} research completed.

## Worker-Where-Are-You Scroll

Highlights the worker of a specific building and gives them a slight speed boost. Shift + right-click on a hut block to use.

Crafted by the {% worker name="enchanter" /%} with 3 Ultrasafe Colony Teleport Scrolls, 6 glowstone dust, and 2 paper. For the {% worker name="enchanter" /%} to unlock this recipe, you must have the {% research_link name="minecolonies/technology/morescrolls" /%} research completed.

## Sacred Scroll of Regeneration

This scroll applies the regeneration effect to you and any citizens near you. It can be found in loot chests.



---
# File: MinecoloniesWiki\src\content\wiki\items\shapetool.mdoc

---
type: item
item: structurize/shapetool
---
The shape tool is creative-only when using Structurize alone, but can be used in survival with MineColonies as well.

Have you ever wanted to have your {% worker name="builder" /%} just dig out a hole, fill in a hole, flatten terrain, or even construct a basic room or other simple shape for you? Then the shape tool may be for you.

### Step One

The first step is to craft a shape tool.

### Step Two

Then right-click any block in the world as a starting point (similar to the {% item name="structurize/sceptergold" /%}).

### Step Three

Use the GUI to configure, resize, and reposition your desired shape.

![Shape Tool GUI](../../../assets/images/wiki/gui/shape_tool_gui.png)

- **CUBE**: click this to open a menu (or use the arrows to cycle between) the following different shape options:
    - **CUBE**: a full cube
    - **SPHERE**: a spherical shape of blocks (this may still resemble a cube at small sizes)
    - **HALF_SPHERE**: a dome shape -- flat on the bottom, spherical on top
    - **BOWL**: an inverse dome -- flat on top, spherical on bottom
    - **WAVE**: a 2D sine wave -- this can look very different depending on settings (it can be used to make stairs, channels, and many other things)
    - **WAVE_3D**: a 3D sine wave
    - **DIAMOND**: a diamond shape, or double-ended pyramid
    - **PYRAMID**: a pyramid shape, or top-half diamond
    - **UPSIDE_DOWN_PYRAMID**: an upside-down pyramid shape, or bottom-half diamond
    - **CYLINDER**: a cylindrical shape (circular cross-section from above)
    - **RANDOM**: a shape generated from an equation
- **Pick Main Block**: click this button (not the block itself underneath it!) to select the block that goes on the outside edges of the shape. You can select AIR if you want to dig out the shape, or select a particular block to fill in with that block.
- **Pick Fill Block**: same as above, but this defines the block that goes inside the shape (if it's solid). Not all shapes use a fill block. You can select AIR if you want to dig out the interior of the shape, or select a particular block to fill it.
- **Solid**: click this to toggle between Solid and Hollow, which controls what happens inside the shape. Note that Hollow is not the same as Solid with Fill=AIR -- it will leave any existing interior blocks alone.
- **Height/Width/Length/Frequency**: either enter a value or click the +/- buttons here to adjust the size of the shape. Different shapes may have different settings or use them slightly differently.
- **Arrows**: similar to the Build Tool, use these buttons to reposition or rotate the shape.
- **Red X**: click this to cancel the build entirely.

You can also press ESC to exit the GUI but keep the shape preview visible, so you can move around and see if it fits. Right-click the shape tool in the air to re-open the GUI.

### Step Four

Once you have the shape configured and positioned to your satisfaction, you can click the **green tick** to ask your Builder to begin construction. (They will, of course, need to be supplied with all of the necessary materials.) This is a decoration build without a hut block to control it; so if you want to cancel the build in progress, you will need to do so at the {% building name="townhall" /%}.

If you're in creative mode, you can alternatively click the **blue paste button** to instantly place the shape for free. This is also what happens when clicking the green tick when MineColonies is not installed.

(The undo button is also creative-only and works the same way as in the {% item name="structurize/sceptersteel" /%} -- it reverts the most recent paste.)



---
# File: MinecoloniesWiki\src\content\wiki\items\stash.mdoc

---
type: item
item: minecolonies/blockstash
---
The Stash lets a player deposit items that they want to be picked up by a {% worker name="courier" /%} to take to the {% building name="warehouse" /%}.

The Stash can be crafted at the {% building name="sawmill" /%} or by the player.

## Using the Stash

To use the Stash, right-click on it to bring up its inventory, which works like a chest. 
Put items in the stash and a Courier from the nearest Warehouse will pick them up and deposit them in the Warehouse for workers or your own requests.



---
# File: MinecoloniesWiki\src\content\wiki\items\supply_camp_and_ship.mdoc

---
type: item-combined
title: Supply camp and ship
items: 
  - minecolonies/supplycampdeployer
  - minecolonies/supplychestdeployer
infobox:
  cols: 1
---
The {% item name="minecolonies/supplycampdeployer" /%} or the {% item name="minecolonies/supplychestdeployer" /%} is how you begin a colony. 

The {% item name="minecolonies/supplycampdeployer" /%} or {% item name="minecolonies/supplychestdeployer" /%} is one of the two ways to obtain the {% building name="townhall" /%} block so you can start building your colony. Each option gives you a great supply of materials for you to get started as well as a very illuminated and safe place to live while you get your colony started. There are a variety of items, and they may change depending on the style you choose to use. Many also have some treasure hidden in them!

## Placement of the {% item name="minecolonies/supplycampdeployer" /%} or {% item name="minecolonies/supplychestdeployer" /%}

Before you place the {% item name="minecolonies/supplycampdeployer" /%}, you need to have a large enough flat, clear piece of land that is as large as the camp area. For the {% item name="minecolonies/supplychestdeployer" /%}, you need an area of water at least one block larger than the build area. Not all camps or ships are the same size.

To place either camp or ship, simply right-click on a block and the 3D preview of the camp and ship will be displayed, with the GUI to move it around. You will see a white outline of the entire build, which is the area that needs to be cleared. If you need to look around the area before accepting the build, press ESC to exit the GUI. This will leave the preview up so you can still see it. To re-enter the GUI, simply click the camp or ship on the ground again to bring it up.

{% image_row %}![Supply Camp GUI](../../../assets/images/wiki/misc/campgui.png) ![Supply Ship GUI](../../../assets/images/wiki/misc/shipgui.png){% /image_row %}

There are also a variety of camps to choose from, based on the style you want. You can choose the style from the 3rd drop down. You can use the arrow buttons in the center to change the location and the curved arrows to rotate the camp.
Once you have it where you want it, press the green check, and the camp or ship will be placed.

> **Note:** Once the {% item name="minecolonies/supplycampdeployer" /%} or {% item name="minecolonies/supplychestdeployer" /%} is placed, you can't place another camp or ship in this world anymore, unless you find one in a treasure chest.

> **Note:** If you’re not able to place the {% item name="minecolonies/supplycampdeployer" /%} or {% item name="minecolonies/supplychestdeployer" /%}, try it block by block: one to the right, or the left, closer or further from the area you cleared. The area cannot contain any holes or have any flowers, grass, ferns, seaweed, etc. It must be completely flat and clear. If you can not place the camp, enlarge the area outside the outline of the camp by one or two blocks around the perimeter. 

> **Note:** You can place camps and ships in the Nether, but placing a ship there requires lava instead of water.

**Here are some screenshots:**

{% image_row %}![{% item name="minecolonies/supplycampdeployer" /%} Example 1](../../../assets/images/wiki/misc/camp4.png) ![{% item name="minecolonies/supplycampdeployer" /%} Example 2](../../../assets/images/wiki/misc/camp5.png){% /image_row %}

{% image_row %}![{% item name="minecolonies/supplychestdeployer" /%} Example 1](../../../assets/images/wiki/misc/ship1.png) ![{% item name="minecolonies/supplychestdeployer" /%} Example 2](../../../assets/images/wiki/misc/ship2.png){% /image_row %}


---
# File: MinecoloniesWiki\src\content\wiki\items\waypoint.mdoc

---
type: item
item: minecolonies/blockwaypoint
---
Waypoints tell your citizens where to walk. For example, you might put one on a road so your citizens will walk on it.

## Using the Waypoint

In schematics, you can place a waypoint to denote where workers should walk. When traveling from place to place, your citizens will walk through the waypoints along the way. They are mostly used in road schematics but can be used in any schematic. However, they should be used sparingly. There are no restrictions on where they can be placed, but when a citizen is nearby they will go to it or try to, so make sure they are placed in appropriate and accessible locations.

When placing a waypoint, do **not** craft one and place it on the ground. 

{% version range="--1.18.2" %}
Open the {% item name="structurize/sceptergold" /%} GUI, then go to Decorations -> Infrastructure -> Waypoint, and place the build order.
{% /version %}

{% version range="1.19.2--" %}
Open the build tool GUI, use the Minecolonies Original pack, then go to Infrastructure -> misc -> Waypoint, and place the build order.
{% /version %}

This tells the {% worker name="builder" /%} to place the waypoint where you want it. If you did it right, the waypoint should be invisible.

To remove a waypoint, place a solid block, such as dirt, on the waypoint and wait a few minutes. If you're not sure where the waypoint is, you can hold a build tool to see it. After a while, you can remove the block, and the waypoint should be removed.



---
# File: MinecoloniesWiki\src\content\wiki\misc\configfile.mdoc

---
type: page
title: Configuration Options
---

{% configuration_list /%}



---
# File: MinecoloniesWiki\src\content\wiki\misc\custom_citizen_names.mdoc

---
type: page
title: Custom Citizen Names
---

Would you like to have citizen names in your own language? Then this is your page!
Everything you have to change is in one file.

## Procedure

1. Download one from the official datapacks below, or create your own.
   - If you decide to make your own, check the [datapacks](/wiki/tutorials/datapacks/citizen_names) page on how to make one.
2. Install the datapack in the `datapacks` folder within the world folder.
   - In singleplayer, the world folder is in your `<installation directory>/saves` folder.
   - In multiplayer, the folder is on the server, in the root of your installation directory.
3. Restart the world and new citizens should start using the new names.

---

## Official Datapacks

You may use the following official datapacks to customize your citizen names:

> These may also be selected directly in-game if you're a {% social_link id="patreon" /%} member!

{% official_citizen_name_pack_list /%}

---

## Community Datapacks

Or use one of the community provided ones downloadable here on the wiki:

{% citizen_name_pack_list /%}



---
# File: MinecoloniesWiki\src\content\wiki\misc\enchantments.mdoc

---
type: page
title: Enchantments
---

MineColonies adds one new enchantment, Raider's Bane. It can be applied to weapons (swords and axes) and increases the weapon's damage against MineColonies raiders.
It goes up to level 2 and can be obtained from the {% worker name="enchanter" /%} when their {% building name="enchanter" /%} is level 3+. 
(For them to obtain a level 2 Raider's Bane enchant, their hut must be level 5.)



---
# File: MinecoloniesWiki\src\content\wiki\misc\multilingual.mdoc

---
type: page
title: Multilingual Sources
---
Here are some links to articles about MineColonies in other languages:

### French

- [Minecraft.fr](https://minecraft.fr/minecolonies-mod/)



---
# File: MinecoloniesWiki\src\content\wiki\systems\border.mdoc

---
type: page
title: Colony Border System
---

Colonies have an area of claimed chunks (16x16 block areas) around them. Upon colony creation, a colony claims chunks in a square around it. The radius is set with the [config](/wiki/misc/configfile) setting *initialColonySize*, which has a default of 4 chunks (not including the chunk the Town Hall is in).

Those claims are protected from modification through other players. See the [Colony Protection System](/wiki/systems/protection) page for more information.

Extending your claim area can be done by building huts. Huts claim a square area around them after they are built, expanding all sides from the chunk the hutblock is located in. How much they claim depends on the building. They won't claim over the max range, set with the [config](/wiki/misc/configfile) setting *maxColonySize*, which has a default radius of 20 chunks from the chunk the Town Hall is in. 

Deconstructing a building *will* remove the chunks it claimed. If you want to delete the colony, check the {% building name="townhall" /%} page.

### Building Claim Areas:

#### {% building name="townhall" /%}

| Level | Additional Chunks |
| ----- | ----------------- |
| 1     | 1 Chunk Radius    |
| 2     | 1 Chunk Radius    |
| 3     | 2 Chunk Radius    |
| 4     | 3 Chunk Radius    |
| 5     | 5 Chunk Radius    |

#### {% building name="guardtower" /%}

| Level | Additional Chunks |
| ----- | ----------------- |
| 1     | 2 Chunk Radius    |
| 2     | 3 Chunk Radius    |
| 3     | 3 Chunk Radius    |
| 4     | 4 Chunk Radius    |
| 5     | 5 Chunk Radius    |

#### {% building name="barracks" /%}

| Level | Additional Chunks |
| ----- | ----------------- |
| 1     | 2 Chunk Radius    |
| 2     | 2 Chunk Radius    |
| 3     | 2 Chunk Radius    |
| 4     | 2 Chunk Radius    |
| 5     | 2 Chunk Radius    |

> When the {% building name="barracks" /%} is level 4, and all it's {% building name="barrackstower" /%} are level 4 as well, the radius is increased to **3 Chunks**.

#### Other buildings

| Level | Additional Chunks |
| ----- | ----------------- |
| 1     | 1 Chunk Radius    |
| 2     | 1 Chunk Radius    |
| 3     | 1 Chunk Radius    |
| 4     | 2 Chunk Radius    |
| 5     | 2 Chunk Radius    |



---
# File: MinecoloniesWiki\src\content\wiki\systems\command.mdoc

---
type: page
title: Command System
---

These are the in-game commands currently available. Most require that a user have operator privileges to work and that a singleplayer world has cheats enabled.

**Warning:** These commands are not intended for normal gameplay use and should only be used when debugging. Some have the potential to delete your colony or break it!

- [Base Commands](#base-commands)
- [Colony Commands](#colony-commands)
- [Citizens Commands](#citizens-commands)
- [Kill Commands](#kill-commands)

**Command Syntax**

| If you see this...  | Then...                                                 |
| ------------------- | ------------------------------------------------------- |
| `plaintext`         | Enter this exactly as shown                             |
| `<angle brackets>`  | This is a **required** argument                         |
| `[square brackets]` | This is an **optional** argument                        |
| `x - y - z`         | Pick one of these options (can be optional or required) |

## Base Commands

| Command                                    | Permissions | Command Description                                                                                                   |
| ------------------------------------------ | ----------- | --------------------------------------------------------------------------------------------------------------------- |
| `/mc backup`                               | OP          | Makes a backup of all colony data.                                                                                    |
| `/mc help`                                 | ALL         | Lists the wiki and the {% social_link id="discord" /%} links in the chat.                                             |
| `/mc home`                                 | ALL         | Teleports a colony owner back to the {% building name="townhall" /%}of their colony.                                  |
| `/mc raid-All <now - tonight> <raid type>` | OP          | Schedules a raid for every colony. You can choose if it starts now or the next Minecraft night, as well as its type.  |
| `/mc resetsupplies <player>`               | OP          | Resets the ability to place a supply camp or ship.                                                                    |
| `/mc rtp`                                  | ALL         | Randomly teleports you. Will place you outside the range of someone else's colony.                                    |
| `/mc whereami`                             | ALL         | Outputs how far away the player is from a colony (if they are near one) or that there are no colonies nearby.         |
| `/mc whoami`                               | ALL         | Outputs your name, the name of your colony, your colony ID number, and the coordinates for the center of your colony. |

## Colony Commands

{% version range="1.19--" %}
| Command                                                          | Permissions | Command Description                                                                                                                                                 |
| ---------------------------------------------------------------- | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `/mc colony addOfficer <colony ID> <player>`                     | ALL         | Adds an officer to a colony. For more information on officers, visit the {% building name="townhall" /%} page and look at the Permissions section of the GUI.       |
| `/mc colony canSpawnRaiders <colony ID> <canSpawn>`              | OP          | Adds or removes the ability to spawn raiders in a colony.                                                                                                           |
| `/mc colony claim <colony ID> [number of chunks] [true - false]` | OP          | Claims a specified number of chunks for a colony (or removes claimed chunks) based off the player's location.                                                       |
| `/mc colony delete <colony ID> [true - false] [true - false]`    | ALL         | Deletes a colony and optionally delete all buildings (as long as the hut block is still placed).                                                                    |
| `/mc colony info <colony ID>`                                    | ALL         | Shows some basic information about the colony specified (ID, name, mayor, citizens, coordinates, last contact with mayor, and if the colony can be deleted or not). |
| `/mc colony list [page]`                                         | ALL         | Shows a list of all the colonies in this world and their ID, name, and coordinates.                                                                                 |
| `/mc colony loadAllColoniesFromBackup`                           | OP          | Loads all colonies from a backup.                                                                                                                                   |
| `/mc colony loadBackup <colony ID>`                              | OP          | Loads an individual colony from a backup.                                                                                                                           |
| `/mc colony raid <now - tonight> <colony ID> <raid type>`        | OP          | Schedules a raid for the specified colony. You can choose if it starts now or the next Minecraft night, as well as its type.                                        |
| `/mc colony requestsystem-reset <colony ID>`                     | ALL         | Refreshes a specified colony's request system, making all workers resubmit requests.                                                                                |
| `/mc colony requestsystem-reset-all`                             | OP          | Refreshes all colonies' request systems, making all workers in all colonies resubmit requests.                                                                      |
| `/mc colony setAbandoned <colony ID>`                            | ALL         | Sets a colony to abandoned and without a mayor.                                                                                                                     |
| `/mc colony setDeletable <colony ID>`                            | OP          | Sets whether a colony can be marked for auto-deletion or not.                                                                                                       |
| `/mc colony setowner <colony ID> <player>`                       | ALL         | Changes the owner of a colony                                                                                                                                       |
| `/mc colony teleport <colony ID>`                                | ALL         | Teleports the player to the specified colony.                                                                                                                       |
{% /version %}

{% version range="--1.18.2" %}
| Command                                                          | Permissions | Command Description                                                                                                                                                 |
| ---------------------------------------------------------------- | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `/mc colony addOfficer <colony ID> <player>`                     | ALL         | Adds an officer to a colony. For more information on officers, visit the {% building name="townhall" /%} page and look at the Permissions section of the GUI.       |
| `/mc colony canSpawnRaiders <colony ID> <canSpawn>`              | OP          | Adds or removes the ability to spawn raiders in a colony.                                                                                                           |
| `/mc colony claim <colony ID> [number of chunks] [true - false]` | OP          | Claims a specified number of chunks for a colony (or removes claimed chunks) based off the player's location.                                                       |
| `/mc colony delete <colony ID> [delete Buildings]`               | ALL         | Deletes a colony and optionally delete all buildings (as long as the hut block is still placed).                                                                    |
| `/mc colony info <colony ID>`                                    | ALL         | Shows some basic information about the colony specified (ID, name, mayor, citizens, coordinates, last contact with mayor, and if the colony can be deleted or not). |
| `/mc colony list [page]`                                         | ALL         | Shows a list of all the colonies in this world and their ID, name, and coordinates.                                                                                 |
| `/mc colony loadAllColoniesFromBackup`                           | OP          | Loads all colonies from a backup.                                                                                                                                   |
| `/mc colony loadBackup <colony ID>`                              | OP          | Loads an individual colony from a backup.                                                                                                                           |
| `/mc colony raid <now - tonight> <colony ID> <raid type>`        | OP          | Schedules a raid for the specified colony. You can choose if it starts now or the next Minecraft night, as well as its type.                                        |
| `/mc colony requestsystem-reset <colony ID>`                     | ALL         | Refreshes a specified colony's request system, making all workers resubmit requests.                                                                                |
| `/mc colony requestsystem-reset-all`                             | OP          | Refreshes all colonies' request systems, making all workers in all colonies resubmit requests.                                                                      |
| `/mc colony setAbandoned <colony ID>`                            | ALL         | Sets a colony to abandoned and without a mayor.                                                                                                                     |
| `/mc colony setDeletable <colony ID>`                            | OP          | Sets whether a colony can be marked for auto-deletion or not.                                                                                                       |
| `/mc colony setowner <colony ID> <player>`                       | ALL         | Changes the owner of a colony                                                                                                                                       |
| `/mc colony teleport <colony ID>`                                | ALL         | Teleports the player to the specified colony.                                                                                                                       |
{% /version %}

## Citizens Commands

| Command                                                     | Permissions | Command Description                                                                      |
| ----------------------------------------------------------- | ----------- | ---------------------------------------------------------------------------------------- |
| `/mc citizens info <colony ID> <citizen ID>`                | ALL         | Gives basic information about a specified citizen in a colony such as their ID and name. |
| `/mc citizens kill <colony ID> <citizen ID>`                | ALL         | Kills a specified citizen in a colony.                                                   |
| `/mc citizens list <colony ID> [page]`                      | ALL         | Lists all citizens in a colony with each citizen's ID and name (9 results per page).     |
| `/mc citizens reload <colony ID> <citizen ID>`              | ALL         | Reloads a specified citizen in a colony.                                                 |
| `/mc citizens spawnNew <colony ID>`                         | ALL         | Spawns a new citizen in a colony.                                                        |
| `/mc citizens teleport <colony ID> <citizen ID> <location>` | ALL         | Teleports a specified citizen to a given location.                                       |
| `/mc citizens walk <colony ID> <citizen ID> <location>`     | ALL         | Tells a specified citizen to walk to a given location.                                   |

## Kill Commands

| Command            | Permissions | Command Description                                                                        |
| ------------------ | ----------- | ------------------------------------------------------------------------------------------ |
| `/mc kill raider`  | OP          | Kills all raiders (barbarians, pirates, vikings, or egyptian raiders) inside all colonies. |
| `/mc kill animals` | OP          | Kills all animals (peaceful mobs) inside all colonies.                                     |
| `/mc kill monster` | OP          | Kills all monsters (hostile mobs) inside all colonies.                                     |
| `/mc kill chicken` | OP          | Kills all chickens inside all colonies.                                                    |
| `/mc kill cow`     | OP          | Kills all cows inside all colonies.                                                        |
| `/mc kill pig`     | OP          | Kills all pigs inside all colonies.                                                        |
| `/mc kill sheep`   | OP          | Kills all sheep inside all colonies.                                                       |


---
# File: MinecoloniesWiki\src\content\wiki\systems\happinessandsaturation.mdoc

---
type: page
title: Happiness and Saturation Systems
---

The Saturation and Happiness systems for the citizens are locked in to each other, which means that the citizens require food to increase their happiness.

The citizens, however, will NOT die if their saturation goes down to 0, they will simply stop leveling, request food in chat regularly, and gain the slowness effect. The workers will stop working. This will affect the overall happiness of each citizen as well as the entire colony.

## Happiness System

There is an **overall** colony happiness and an **individual** citizen happiness. Higher colony happiness increases the initial level and skills of new colonists. Higher citizen happiness increases the maximum possible Intelligence when studying at a {% building name="library" /%}.

Overall colony happiness is 1-10 (initially set to 5). Happiness depends on three basic factors: **security, housing, and saturation**.

If a colonist's saturation is above 14 shanks, they will be happier.
If the colonist's home (or for Guards/trainees, their workplace) is above level 2.5, the colonist will be happier.
If at least two Guards exist for every three citizens, the colonists will be happier.

These basic factors benefit from partial completion. Citizens will be happier with 5 shanks of saturation than 1, with 1 Guard for every 4 workers than 1 Guard for every 10, and enjoy a level 2 house more than a level 1 house. Colonists also become happier as these values increase further, such as from upgrading a house to level 4 or 5 or having completely full saturation.

Each time a citizen (other than a Guard) dies, other citizens will mourn the next day and be less happy for the next three days. All citizens become slightly less happy for the next day when injured.

Every night, colonists will also become less happy if they are sick, homeless, unemployed, or have nothing to do at their job, and will become increasingly unhappy the longer the problem persists. Citizens (other than Guards) unable to make it to their bed at night to sleep will become unhappy. Fellow colonists' livelihood also matters for happiness (the Social Factor in the {% building name="townhall" /%} its happiness page).

Surviving a raid without losing any colonists provides a colony-wide happiness boost.

Colonists also like to have Guards near their homes and work places. Each {% building name="guardtower" /%} will provide a feeling of protection to the colonists based on how far it expands your [borders](/wiki/systems/border). However, sometimes colonists may not recognize a newly placed Guard Tower until you tell them that their concerns for their safety have to do for now.

## Hunger/Saturation System

The citizen saturation system is between 0-20. If it's 0, the citizen won't level anymore, won't work anymore, will request food in chat regularly, and will have the slowness effect. If it's less than 6, the citizen won't heal. If it's 20, the citizen will have a double healing speed. They increase their saturation by eating, just like the player. Saturation is displayed with the saturation bar in each citizen's GUI (it looks like the hunger bar in the player's HUD).

Every time a citizen goes to sleep (starts the walk back to their {% building name="residence" /%} or {% building name="tavern" /%}), they will decrease their saturation by 0.2 times their worker hut level. They will also decrease their saturation while working.

Citizens will demand higher levels of food based on their workplace level. **They will not eat food that doesn't meet (or exceed) their requirements.**

| Building Level | Min Food Level Requested (in shanks) |
| -------------- | ------------------------------------ |
| 0              | Any                                  |
| 1              | 0.5                                  |
| 2              | 1                                    |
| 3              | 1.5                                  |
| 4              | 2                                    |
| 5              | 2.5                                  |

When a citizen's hunger level gets to 6 or lower, they will head to the {% building name="cook" /%} to get food from the {% worker name="cook" /%}. If there is no Cook, they will request in their GUI that you build them a {% building name="cook" /%} or provide them with food manually.



---
# File: MinecoloniesWiki\src\content\wiki\systems\pathing.mdoc

---
type: page
title: Pathing System
---
You're sure to have noticed that getting your colonists to run the right way to their job isn't always easy. It almost seems like they have a mind of their own in how they move. Fortunately, there are some basic guidelines that help you corral these incorrigible colonists to go where you want them to faster.

## Getting Started

Unlike the normal villagers you might see in the world, your colonists are a bit smarter. They'll generally find a pretty good path to get where they want to go due to their advanced degrees in walking from Raycoms University and their RavenEyes© vision.

As such, they're able to move across flat ground, single-block steps, stairs, slabs, and other such blocks. They know how to open both wood and iron doors — even without any redstone input — trapdoors, and fence gates. They'll climb up and down ladders and vines just fine as well. However, they will try to avoid going through fences, as is expected, and generally will find a way around dangerous obstacles like fire, magma, lava, cacti, and sweet berry bushes.

## Getting Stuck

But what if a colonist can't find a path to where they want to go? What if, say, they fall in a hole and get stuck, or they're trying to climb somewhere high up that doesn't have any way there yet, or if they get trapped inside a wall by an overenthusiastic Builder? Not to fear! Your colonists are also slightly magical in that, if they're stuck in one place for too long, they'll build up enough willpower to relocate themselves forcibly to a nearby valid location and continue on their way. 

This does take a while, though, and sometimes can result in unforeseen circumstances, like a guard Leeroy Jenkins-ing into a cave, only to be overwhelmed by monsters. However, this is rare and generally won't be an issue.

## Pathblocks

On certain [pathblocks](https://github.com/ldtteam/minecolonies/blob/version/main/src/datagen/generated/minecolonies/data/minecolonies/tags/blocks/pathblocks.json), your colonists move faster and stick to the paths when pathfinding. Building roads out of these can help keep them on a safe path and get them around the colony faster.

## Waypoints and Decorative Paths

{% item name="minecolonies/blockwaypoint" /%} are also used to guide your citizens along the right path, but whereas the pathblocks are for local pathing, waypoints are used more for long-distance pathing. Adding a waypoint onto bridges, for example, or in areas that you want citizens to traverse a little more frequently, can help them path across the colony more easily. As such, you don't need very many waypoints: having a couple here and there where the pathing may be more difficult is plenty.

The Builder can build waypoints if you request them with the {% item name="structurize/sceptergold" /%}.

The build tool also includes several decorative paths as part of the schematics. These paths often include waypoints, so you can see how the professional schematics designers used them.

## Research

There are several researches your {% building name="university" /%} can unlock that increase colonist move speed. Check the [Research System](/wiki/systems/research) page for more details.

One research of note is the Rails research. It allows colonists to use rails to move around the colony. They automagically create their own minecart, so all you need to do is place rails down. The rails don't need to be powered, either! However, the extra boost of powered rails can still help with some of the tougher hills.

Intersections should include a block or two gap. Colonists will sometimes overshoot a turn in their minecart, so including a gap at intersections will ensure they don't overshoot their turn.

## Water

Although your colonists generally prefer to be on land, they can slowly bob up and down on the surface of the water the same way you do. However, they don't know how to use boats. Do try to keep them from drowning!

## Mounts

Colonists do not interact with any mounts yet, but mounted knights are planned eventually!



---
# File: MinecoloniesWiki\src\content\wiki\systems\protection.mdoc

---
type: page
title: Colony Protection System
---

Our Colony Protection System (once a {% building name="townhall" /%} is placed) can be toggled in the [config file](/wiki/misc/configfile).

The protection turns all explosions off (by default), so creepers, TNT, and any modded blocks won't destroy your colony. By default, only the colony owner can break or place blocks within a colony, but you can add other players in the permissions section of the Town Hall GUI.

It covers from bedrock to the sky limit, so you don't have to worry about griefing under or above your colony.

> The protection system depends on the border system. Only areas that are within the colony's border will be protected. See the [Colony Border System](/wiki/systems/border) page for more information.



---
# File: MinecoloniesWiki\src\content\wiki\systems\raid.mdoc

---
type: page
title: Raid System
---
![Sleep](../../../assets/images/wiki/misc/sleep.png) **If a raid is currently in progress, your colonists will stop working and run home!**

## Before the Raid
As your colony develops, you may get raided by hostile forces. These forces can spawn almost anywhere except near your buildings. Generally, this means they'll spawn outside of the colony, but if your buildings are spread out, they could spawn between them!

Raids will only spawn at the start of a night. There will be a chat message giving a general direction of where the raid spawned and a bar at the top of your screen showing your progress in defeating the raiders. Raids spawn randomly, with a minimum number of nights between them and an average number of nights as set in the [config file](/wiki/misc/configfile). Larger colonies may get multiple waves or groups of raiders from different directions, even of different types. Be careful!

![Multiple barbarian raids in progress](../../../assets/images/wiki/misc/multiraidbars.png)

If you have a {% building name="barracks" /%} at level 3 or higher, you can hire spies to make raiders glow. This can help you find that one pesky raider that got stuck or is hiding and finish the raid.

## Types of Raids
The type of raiders that can spawn are determined by the biome in which they spawn.

| Biome               | Raider type |
| ------------------- | ----------- |
| Taiga               | Nordic      |
| Desert              | Mummy       |
| Large body of water | Pirate      |
| Jungle              | Amazon      |
| Other               | Barbarian   |

Notably, Pirate raids can spawn on any large body of water, not just the ocean. They will spawn with a ship that has spawners that continuously spawn more pirates until you break the spawners. If any of the environment would be clipped by the pirate ship's spawning, the environment will be restored after the ship sails away (in 3 days, by default).

Mummy raids may include Pharaohs, who may drop their scepter on defeat. These scepters are essentially bows, and as such can be used by your {% worker name="archer" plural=true /%}.

## During the Raid
Raiders will run around the colony breaking down doors and trying to attack your Guards and colonists. Generally, raiders will path toward hut blocks in your colony, similar to what Guards do on patrol.

Trying to "cheese" the raiders usually will not work. They are able to path fairly well, though if they are unable to find any path to their destination, they will break through any block, place ladders to climb up obstacles, or place blocks to bridge gaps to get to your colony. They can swim straight through moving water and lava, taking no damage from lava. Any attack that hits a raider *too* hard will be reflected back at the attacker. Similarly, turrets (from other mods) generally will not be effective on raiders.

Raiders have a chance to drop their equipment when you or your Guards defeat them. They may also drop {% item name="minecolonies/ancienttome" /%}, which are used in enchanting.

## After the Raid
After all of the raiders have been defeated and spawners broken, your colonists will go back to their jobs. If any of your colonists die, then their family will mourn their death and not work that day. However, Guards will not be mourned.

As you defeat more raiders and develop your colony, the raids will increase in difficulty. How quickly they increase in difficulty or what affects their difficulty is not publicly known.



---
# File: MinecoloniesWiki\src\content\wiki\systems\request.mdoc

---
type: page
title: Request System
---

As the mayor of a fledgling colony, you are sure to be busy managing your citizens and the multitude of issues that arise. At some point, you’ll have too many citizens to deal with every single one of their demands individually.

Thankfully, there’s the Request System! Citizens will automatically ask for the items they need to perform their jobs, and if those items can be provided by your other citizens, they will do so! 

## Getting Started

The request system is based on two components: the {% building name="warehouse" /%} — which stores all of your colony’s resources — and the {% worker name="courier" /%} — who pick up and drop off items between buildings. The Couriers wait in the Warehouse until a request comes in.

When a citizen realizes that they need an item, they’ll first look in their inventory, hut block, and {% item name="minecolonies/blockminecoloniesrack" /%} in their hut. If they cannot find the item in these locations, they’ll then make a request automatically. If this item is in the Warehouse, a Courier will then take it from the Warehouse and deliver it to your citizen automatically.

## Crafting Requests

But what if you don’t have that item in your Warehouse? In that case, your citizens will check who is able to make that item and attempt to ask them to make it. For example, if your builder needs some oak stairs and there aren’t any in the Warehouse, your {% worker name="carpenter" /%} will check if they know how to make oak stairs. If they do, then they’ll request some oak planks to turn into stairs. If there aren’t any oak planks but the carpenter also knows how to make them from oak logs, then they’ll request oak logs to turn into oak planks to turn into oak stairs. Your citizens will keep making and trying to fulfill requests until they have the items they need or they cannot fulfill a request given their jobs and knowledge. As such, it’s convenient to get as many citizens working different jobs as you can to fulfill as many of their requests as they can automatically!

## Manual Requests

What if your builder asks for oak stairs but you don’t have a carpenter yet? In that case, the request can’t be filled automatically, and the citizen will wait for you, the mayor, to fulfill the request manually. These citizens will have a red gear over their head and, in talking to them, will tell you what they need. Once you have that item they need, you can give it to them directly or put it in the Warehouse for your Couriers to deliver.

## Clipboard

Of course, being the busy mayor you are, you’ll want to be able to see all of your citizens’ requests without running to each one. For this, there’s the {% item name="minecolonies/clipboard" /%}. Simply right-click the air with your clipboard and you can see all of your citizens’ requests, no matter where you are (as long as you're close to your colony)!

![Clipboard GUI](../../../assets/images/wiki/gui/items/clipboardgui1.png)

Some of your citizens will always have constant requests (e.g., the {% worker name="smelter" /%} requesting ores). Clicking the "Show Important Requests Only" hides these constant requests, so you can focus on the requests that bottleneck your citizens' work.

## Stash/Postbox

As your colony grows, so too will the number of items and blocks in your Warehouse and that your citizens can produce. Searching the Warehouse each time you want something can be tedious. Thankfully, your colony has a {% item name="minecolonies/blockpostbox" /%}, from which you can make requests just like your citizens! Simply place it down somewhere you can easily access and, when you want to request an item from your colony but don’t want to hunt it down, make a request at the postbox. These can range from held items to placeable blocks to any items your workers can craft.

For convenience, your colony also has a {% item name="minecolonies/blockstash" /%}. The stash behaves like a reverse postbox: Couriers will take any items you place in the stash and put them back in the Warehouse.

## Priority

Finally, you can adjust the order in which Couriers pick up items from each building by changing that building's *priority*. Higher numbers mean higher priority, so buildings with a priority of 10/10 are visited before buildings with a priority of 1/10. This can help ensure the colonists doing urgent jobs are not delayed by those with less urgent jobs.

Priority only affects pickup, though; deliveries *from* the Warehouse *to* other buildings or the postbox are always high priority.



---
# File: MinecoloniesWiki\src\content\wiki\systems\research.mdoc

---
type: page
title: Research system
---

At the {% building name="university" /%}, {% worker name="researcher" /%} can research various upgrades to your colony. These are split into multiple trees: {% research_trees /%}. You access these from the second page of the {% building name="university" /%} GUI.

Each column of a research tree is also the level the {% building name="university" /%} needs to be to begin a research from that column. So:

| Research Tree Column | Minimum {% building name="university" /%} Level |
| -------------------- | ----------------------------------------------- |
| 1                    | 1                                               |
| 2                    | 2                                               |
| 3                    | 3                                               |
| 4                    | 4                                               |
| 5+                   | 5                                               |

You can only have one column 6 research in each of the trees. To unlock a different column 6 research for that tree, you must undo the completed one first.

| Symbol                                                               | Description                                                                                                                                                                                                                                                                                                                                 |
| -------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![Blocked Research](/images/wiki/research/research_blocked.png)      | A research can be **blocked**, either by an unfinished prerequisite research, by a completed blocking research, or because the research tree has another column 6 research already active.                                                                                                                                                  |
| ![Locked Research](/images/wiki/research/research_locked.png)        | A **locked** research requires a building or buildings, or other unrelated research, before it can be started.                                                                                                                                                                                                                              |
| ![Unlocked Research](/images/wiki/research/research_unlocked.png)    | An **unlocked** research has all colony and research requirements met, but requires an item or items. **These items must be in the player's inventory.**                                                                                                                                                                                    |
| ![Available Research](/images/wiki/research/research_ready.png)      | An **available** research is ready to begin. Clicking the title of the research will consume the items from the player's inventory and start the research.                                                                                                                                                                                  |
| ![Progressing Research](/images/wiki/research/research_progress.png) | A **progressing** research is being worked on currently. This research will show its current progression and a rough estimate of the remaining time to completion. A progressing research can be canceled by clicking the research title and then clicking the Cancel pop-up. Cancelling a research will **not** refund the material costs. |
| ![Complete Research](/images/wiki/research/research_complete.png)    | A **complete** research has been fully unlocked by your {% building name="university" /%}. Its effects have been applied to the colony and colonists.                                                                                                                                                                                       |
| ![Exclusive Research](/images/wiki/research/research_or.png)         | Some researches are **exclusive**, requiring such extreme focus that they aren't compatible with each other. Only one research from a specific **or** selection may be learned in a colony at a time.                                                                                                                                       |
| ![Undo Research](/images/wiki/research/research_undo.png)            | Some completed researches may be **undone** if they block another research in some way, do not have a completed research that depends on them, and are not marked with a redstone torch as irreversible. Undoing a research does *not* refund the research costs and consumes the displayed item.                                           |

{% research_list /%}



---
# File: MinecoloniesWiki\src\content\wiki\systems\sleep.mdoc

---
type: page
title: Sleep System
---

No matter how busy your thriving colony is, your colonists need to sleep. At the end of each day until sunrise the next day, they will go to their homes and sleep in their beds.

## Happiness

Letting your colonists sleep will make them happier. If they're unable to reach their beds for at least 3 days, they'll tell you they're unhappy. 

## Assigning Beds

{% building name="residence" /%} and {% building name="tavern" /%} provide most of your colonists with bedspaces to sleep in. If set in the {% building name="townhall" /%}, you can manually assign colonists to beds in Houses or the Tavern. Else, they'll be randomly assigned to an open bed.

Each colony can have only one Tavern by default. The Tavern will provide 4 beds. Upgrading does not increase the number of beds; however, it does affect skill caps and number and quality of visitors.

Each colony can have as many Houses as you'd like. Each House will provide one bed per level; i.e., a level 4 House will provide 4 beds, and a level 1 House will provide 1 bed. The House level also restricts colonist skill caps.

{% building name="guardtower" plural=true /%} provide 1 bed for a Guard, while a {% building name="barracks" /%} provides housing for many Guards. See those pages for more details.

Colonists don't like to commute too far, whether by rail or by foot. If they're over 100 blocks from their work hut, they'll complain that they're living too far from work. If you see this, have them switch bed with someone else!

If you have at least one female and one male colonist in your colony, then children can be born. Children also take up a bed space, but that bed space does not need to be with either of their birth parents. Instead, their parents will be the adults living in the building they're born into, which can be two colonists (of either gender), a single colonist, or none at all! Children will take some of their skills from their parents, though, so a child with highly skilled parents in a very happy colony will start with pretty good stats!

## Homelessness

Colonists that do not have a home will gather at the Town Hall at night. You should build them a home, so they have somewhere safe to be at night and during [raids](/wiki/systems/raid). Colonists become upset after two weeks of not having a home.

## Ticking Events at Night

Turning off the daylight cycle in-game can affect some events and internal updates unpredictably. Most notably, this includes [raids](/wiki/systems/raid) and {% worker name="farmer" /%}, but may include things like sicknesses or the growth of children. However, this is not publicly known.



---
# File: MinecoloniesWiki\src\content\wiki\systems\worker.mdoc

---
type: page
title: Worker System
---

![Sleep](../../../assets/images/wiki/misc/sleep.png) **If it is raining, snowing, nighttime, or a citizen died yesterday (in-game time), your citizens will stop working!**

## Traits
When hiring a worker at a hut, the primary trait used in that work will be highlighted in **green** and the secondary trait in **yellow** to help you decide which citizen is the best worker for the job. The higher a worker's skill, the faster and more efficient they will be. Each trait also has a specific bonus for the job. To see these bonuses, please visit the worker pages.

![Traits](../../../assets/images/wiki/misc/traits.png)

Workers their skills increase as they work. They are limited by the level of the worker's home. The level of a worker's work hut and their Intelligence skill level affect the speed that they level up.

| Home Level | Max Skill Level |
| ---------- | --------------- |
| 0          | 10              |
| 1          | 20              |
| 2          | 30              |
| 3          | 40              |
| 4          | 50              |
| 5          | 99              |

{% workers_table /%}

## Tool / Sword Levels

The type of tools/swords workers can use depends on the level of a worker's work hut.

Everything combination marked with an `X` is allowed, the rest is not.

| Max Tool/Sword Level | Work Hut Level | No Enchants | Level 1 Enchants | Level 2 Enchants | Level 3 Enchants | Level 4 Enchants | Level 5+ Enchants |
| -------------------- | -------------- | ----------- | ---------------- | ---------------- | ---------------- | ---------------- | ----------------- |
| Wood or Gold         | 0              | X           |                  |                  |                  |                  |                   |
|                      | 1              | X           | X                |                  |                  |                  |                   |
|                      | 2              | X           | X                | X                |                  |                  |                   |
|                      | 3              | X           | X                | X                | X                |                  |                   |
|                      | 4              | X           | X                | X                | X                | X                |                   |
|                      | 5              | X           | X                | X                | X                | X                | X                 |
| Stone                | 1              | X           |                  |                  |                  |                  |                   |
|                      | 2              | X           | X                |                  |                  |                  |                   |
|                      | 3              | X           | X                | X                |                  |                  |                   |
|                      | 4              | X           | X                | X                | X                |                  |                   |
|                      | 5              | X           | X                | X                | X                | X                | X                 |
| Iron                 | 2              | X           |                  |                  |                  |                  |                   |
|                      | 3              | X           | X                |                  |                  |                  |                   |
|                      | 4              | X           | X                | X                |                  |                  |                   |
|                      | 5              | X           | X                | X                | X                | X                | X                 |
| Diamond              | 3              | X           |                  |                  |                  |                  |                   |
|                      | 4              | X           | X                |                  |                  |                  |                   |
|                      | 5              | X           | X                | X                | X                | X                | X                 |
| Netherite or above   | 4              | X           |                  |                  |                  |                  |                   |
|                      | 5              | X           | X                | X                | X                | X                | X                 |

## Bow / Fishing Rod Levels

There is also a system in place for the type of bows and fishing rods workers can use. This also depends on the level of a worker's work hut.

| Work Hut Level | Enchantments           |
| -------------- | ---------------------- |
| 0              | No enchantments        |
| 1              | No enchantments        |
| 2              | One enchantment        |
| 3              | Two enchantments       |
| 4              | Three enchantments     |
| 5              | Unlimited enchantments |

Modded fishing rods with higher durability may require a higher Work Hut level.

## Guard Armor Levels

There is also a system in place for the type of armor guards can use. This depends on the level of their building.

| Maximum Allowed Armor | Tower Level | No Enchants | Level 1 Enchants | Level 2 Enchants | Level 3 Enchants | Level 4 Enchants | Level 5+ Enchants |
| --------------------- | ----------- | ----------- | ---------------- | ---------------- | ---------------- | ---------------- | ----------------- |
| Leather or Gold       | 1           | X           |                  |                  |                  |                  |                   |
|                       | 2           | X           | X                |                  |                  |                  |                   |
|                       | 3           | X           | X                | X                |                  |                  |                   |
|                       | 4           | X           | X                | X                | X                |                  |                   |
|                       | 5           | X           | X                | X                | X                | X                | X                 |
| Chainmail             | 2           | X           |                  |                  |                  |                  |                   |
|                       | 3           | X           | X                |                  |                  |                  |                   |
|                       | 4           | X           | X                | X                |                  |                  |                   |
|                       | 5           | X           | X                | X                | X                | X                | X                 |
| Iron                  | 3           | X           |                  |                  |                  |                  |                   |
|                       | 4           | X           | X                |                  |                  |                  |                   |
|                       | 5           | X           | X                | X                | X                | X                | X                 |
| Diamond               | 4           | X           |                  |                  |                  |                  |                   |
|                       | 5           | X           | X                | X                | X                | X                | X                 |
| Netherite or above    | 5           | X           | X                | X                | X                | X                | X                 |

Guards can use most modded armor within these rules, though they may not be able to use or benefit from special traits of that armor.



---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks.mdoc

---
type: page
title: Datapacks
sections:
 - tutorials/datapacks/citizen_names
 - tutorials/datapacks/custom_visitors
 - tutorials/datapacks/loot_tables
 - tutorials/datapacks/recipes
 - tutorials/datapacks/research
 - tutorials/datapacks/tags
---

MineColonies allows modifications of many features using data packs, including player and worker recipes, loot tables, and mob drops. This allows broad expansion by players or modpack makers to support other mods, design choices, forms of progression, or styles of play. For general information on Minecraft data packs, [see the official wiki](https://minecraft.wiki/w/Data_Pack).

Data packs exist as part of a world, and they must be [installed](https://minecraft.wiki/w/Tutorials/Installing_a_data_pack) on each world.

## Concepts

Data packs are one or more files in the JSON format, stored within a folder or a zip file. Despite their names, these are text files, and can be opened with text editors. Note that Windows will, by default, hide the extension of known files, and this [should be changed](https://support.microsoft.com/en-us/windows/common-file-name-extensions-in-windows-da4a4430-8e76-89c5-59f7-1cdbbc75cb01) to avoid accidentally appending .txt to the file name. Avoid using a rich text editor like WordPad or Microsoft Word, which may insert additional formatting into the file. On Windows, simple text editors like Notepad, NotePad++, or WattPad are more useful for making small numbers of these files, and development environments like IntelliJ may be worth installing if creating many JSONs.

The JSON format is both generous and fastidious. It does not particularly care if you add extra fields, but it will choke on a missing comma or brace. Minecraft will report the file and location of a JSON error in the error log, but it may also be useful to check files in a JSON validator tool, colloqually known as a [linter](https://jsonlint.com/), as you create them. The JSON format will accept most characters, though the double-quotes (`"`) and backslash (`\`) characters must first be 'escaped' by prefixing them with a backslash (such that `"` becomes `\"` and `\\` becomes `\\`).

For most users, looking at other similar JSONs will be the fastest way to get started. For those interested in the specific rules of the format, [see here](https://www.json.org/json-en.html).

The data pack folder or zip file can be any valid file name, and will be used to determine the name of the data pack. This folder or zip file must contain in its root level a file named **pack.mcmeta**, [with a specific format](https://Minecraft.gamepedia.com/Data_Pack#pack.mcmeta). It is strongly encouraged to provide a distinct name and description for your data pack: this will show up as a tooltip from the in-game interfaces and /datapack list command. To act as a data pack, this should also contain a "data" directory.

Each folder within that `data` directory acts as a different **namespace**. Most mods have their own namespaces; for MineColonies, this is `minecolonies`. As a rule, all folders and files within a datapack, including the namespace folders, **must** have names consisting solely of lowercase alphanumeric characters, underscores (`_`), dashes (-), and/or dots (.). Any other characters, including uppercase letters, will cause Minecraft to fail to load the data pack, and give a largely unhelpful error. Completely empty names are considered legal and read, but not all mods will support them.

**Data packs are very picky. A single misplaced comma, missing quotation mark, or invalid file name will give an error. If a file doesn't seem to be applying, or a datapack is giving errors on world load, check your file's formatting first.**
Files that exactly match the namespace, directory, and name of a file from vanilla Minecraft, a mod, or another data pack will either completely override or merge with that other JSON, depending on the file's type. A data pack can have multiple namespace directories, and the most common approach is to use a mod's namespace when directly overriding an existing JSON from vanilla or a mod, and a unique namespace when adding or modifying content or modifying another data pack. It's encouraged to add to the `forge` and `minecraft` namespaces only when adding to or modifying vanilla or forge defaults, to use the `minecolonies` namespace when modifying existing MineColonies files, and to use your own namespace when adding new types, or completely removing a crafter recipe or research.

### Terminology

| Keyword           | Explanation                                                                                                                                                                                                                                                                                                                                                                                                                |
| ----------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Resource Location | The common word for Mojang's [Namespaced IDs](https://minecraft.wiki/w/Namespaced_ID#Namespace). A string of format `namespace:path`, with strict limitations on allowed characters, all lower-case, and only one colon (:). Used heavily in newer versions of Minecraft to uniquely identify nearly everything.                                                                                                           |
| Namespace         | The first half of a Resource Location, before the colon (:). In `minecraft:cobblestone`, "minecraft" is the namespace. Commonly used namespaces are "minecraft", "forge", and "minecolonies". Modpack makers may want to select their own namespace to avoid potential conflicts. In data packs, namespaces are derived from the names of the folders at the top level within the "data" directory.                        |
| Data Location     | The internal location within namespaces that Minecraft and mods examine for specific uses, such as `tags/blocks` for Block Tags, or `crafterrecipes` for Crafter Recipes. Only JSONs within a known data location are applied by Minecraft or Minecraft mods, and Data Locations control how these JSONs apply and what format is expected. Relevant Data Locations are described in more detail throughout this document. |
| Path              | The second half of a Resource Location, after the colon (:). In `minecraft:cobblestone`, "cobblestone" is the path. In data packs, Paths are derived from the folders and filenames within a specific Data Location. `data/minecraft/tags/items/cobblestone.json` will have a namespace of "minecraft", a Data Location of "tag/items", a path of "cobblestone".                                                           |
| Type              | The supported format for a specific context. Includes Objects, Arrays, Strings, Booleans, Integers, and Doubles.                                                                                                                                                                                                                                                                                                           |
| Object            | In the context of the JSON standard, a group of key-value pairs held together by a pair of curly brackets (`{ }`). All JSON files must be a JSON Object, and name-value pairs may use an Object as a value.                                                                                                                                                                                                                |
| Array             | In the context of the JSON standard, a group of value types held together by a pair of square brackets(`[ ]`). JSON Arrays may contain multiple Values, or multiple Objects, but not name-value pairs directly.                                                                                                                                                                                                            |
| String            | A set of characters. In JSON, strings must always be within quotation marks (`" "`).                                                                                                                                                                                                                                                                                                                                       |
| Boolean           | The values `true` or `false`, not contained within quotation marks.                                                                                                                                                                                                                                                                                                                                                        |
| Integer           | A whole number. For this document, between positive and negative two billion, not contained within quotation marks. You generally won't use numbers that high.                                                                                                                                                                                                                                                             |
| Double            | A number, including decimal numbers, not contained within quotation marks.                                                                                                                                                                                                                                                                                                                                                 |
| Name-Value Pair   | In the context of the JSON standard, a string key and an matching value, usually in the format `"name": value`. Name-value pairs that are not the last name-value pair in an Object must be separated by a comma (`,`).                                                                                                                                                                                                    |
| Name              | In the context of the JSON standard, the left half of a name-value pair. Must be a String. Only one occurance of a name will be read in a single object's top level, usually the first, though sibling in an array or descendants in objects may hold the same Name.                                                                                                                                                       |
| Value             | In the context of the JSON standard, the right half of a name-value pair. May be any type that matches the context. Values within quotation marks (`" "`) are treated as Strings.                                                                                                                                                                                                                                          |
| Translation Key   | A specially formatted string, which will attempt to be processed through the Minecraft translation file. If the client language file contains a matching Name, substitutes the corresponding Value, otherwise, presents the key to the user directly.                                                                                                                                                                      |

### Example Folder Layout

A complex data pack can have many files across many types in many namespaces, as shown below. Smaller data packs consisting of tweaks or settings changes may only have two or three files.

![Example Complex Data Pack Layout](../../../assets/images/wiki/misc/datapack_example.png)

**The pack.mcmeta file is mandatory, and Minecraft will not load a data pack without one, or with an improperly formatted one.**

A typical pack.mcmeta file looks like this: 

```json
{
  "pack": {
    "pack_format": /* Any number, refer to https://minecraft.wiki/w/Pack_format for your Minecraft version */,
    "description": "Rename To Your Preferences"
  }
}
```

The `"description"`'s value is displayed to the user as an in-game title for the data pack, so it's best to make it descriptive and unique.


---
# File: MinecoloniesWiki\src\content\wiki\tutorials\getting-started.mdoc

---
type: page
title: Getting Started
---

- [Quick Walkthrough](#quick-walkthrough)
  - [Step 1: Supply Camp/Supply Ship](#step-1-supply-campsupply-ship)
  - [Step 2: Town Hall](#step-2-town-hall)
  - [Step 3: Builder](#step-3-builder)
  - [Step 4: Tavern](#step-4-tavern)
  - [Step 5: Food](#step-5-food)
  - [Step 6: Forester](#step-6-forester)
  - [Step 7: Miner](#step-7-miner)
  - [Step 8: Warehouse and courier](#step-8-warehouse-and-courier)
  - [Step 9: Rest of the Colony](#step-9-rest-of-the-colony)
- [Detailed Walkthrough](#detailed-walkthrough)
  - [Placement of the Supply Ship or Supply Camp](#placement-of-the-supply-ship-or-supply-camp)
  - [Town Hall](#town-hall)
    - [Build Tool Video](#build-tool-video)
- [Setting Up Your Colony](#setting-up-your-colony)
  - [Step 1](#step-1)
  - [Step 2](#step-2)
  - [Step 3](#step-3)
  - [Step 4](#step-4)
  - [Final Notes](#final-notes)

This tutorial assumes you have already installed the mod or modpack. If you'd like instructions on how to install our [Official Modpack](https://www.curseforge.com/minecraft/modpacks/minecolonies-testpack), you can find instructions for doing that on Java (Forge) [here](/wiki/installation/java), on GDLauncher [here](/wiki/installation/gdlauncher), and on the Curseforge Launcher [here](/wiki/installation/curseforge). 

## Quick Walkthrough 

Before you get started on your new colony, you should find your perfect location. You need a large enough area for a full city-at least 8x8 chunks, and decently flat.

You should also gather as many resources as you can-wood, as well as cobble, coal, iron, flowers, string, leather, wool, saplings, and food. 

### Step 1: Supply Camp/Supply Ship
  
Once you have chosen a location for your colony, you must place a {% item_page name="supply_camp_and_ship" /%}. Go inside the ship/camp and acquire the {% item name="structurize/sceptergold" /%}. The remaining camp/ship is just decoration. You can leave it or tear it down. (Note: if you do not obtain a {% item name="structurize/sceptergold" /%} from the ship/camp, you may craft one to continue).
  
### Step 2: Town Hall
  
The {% building name="townhall" /%} block is what actually creates the colony. It also functions as the center of it. This is where you start using the build tool to place buildings. Using the build tool ensures you can see exactly where the building will be, which direction it will face, and if it is at the right Y level (some buildings need to be raised to the correct Y level).

Once the {% building name="townhall" /%} block is placed, you will start receiving your initial 4 citizens.
  
### Step 3: Builder
  
Before anything can be built, you need a {% worker name="builder" /%}. The Builder must build their own {% building name="builder" /%}  before building any other huts, and they cannot build or upgrade a hut to a higher level than their own hut level. Craft the {% building name="builder" /%} block and place it down like the previous examples to begin the construction process. 
  
### Step 4: Tavern

Your colonists will need a place to sleep and a house, so it is recommended to build a {% building name="tavern" /%} next. The Tavern also allows visitors to come to your colony, and you can recruit them to stay as citizens.

### Step 5: Food

The next major thing you need for your colony is food. The fastest way to get food is to build a {% building name="fisherman" /%}. You could build a {% building name="farmer" /%} or one of the animal herders, but a Fisher is faster. Building a {% building name="cook" /%} may also be advisable if you don't want to feed your colonists (or cook their food) by hand.

### Step 6: Forester
  
If your style is wood, it's recommended to build a {% building name="lumberjack" /%} next. That way your new {% worker name="forester" /%} can start gathering wood for your next builds and for tools and other items.
  
### Step 7: Miner
  
Stone and ores are another major need for any colony, so a {% building name="miner" /%} is the next logical step.
  
### Step 8: Warehouse and courier

At this point, you might be tired of bringing all of the resources to the Builder and taking them from the Fisher, Forester, Miner, etc. You also might want to store items in one place. Well, the {% building name="warehouse" /%} and {% building name="deliveryman" /%} are the solution you are looking for! {% worker name="courier" /%} transport items between the Warehouse and all of the workplaces. The {% item name="minecolonies/resourcescroll" /%} may be helpful!
  
### Step 9: Rest of the Colony
  
This is where you need to decide what you need next, based on how you play and what your colony needs. But you have several things to look at. Once you get seven citizens you can get attacked by raiders and you may want to build a {% building name="guardtower" /%}, or you may get a sick citizen and want to build a {% building name="hospital" /%}, or you may decide you want a {% building name="sawmill" /%} to make the needed items for a worker. Or you may just want to have a specific worker, or you found a great place for a certain hut. You decide how your colony will grow!

## Detailed Walkthrough

If you're here, **CONGRATULATIONS!** You have already installed MineColonies and are ready to embark on an adventure to explore, conquer and establish your own colony. Work hard and turn it into a thriving town. Hone your leadership skills and grow grow grow into a city! Lastly, can you be a grand leader and finally achieve a metropolis? (Check your ingame MineColonies Achievements.)

![Achievements](../../../assets/images/wiki/tutorial/achievements.png)

To avoid mistakes and frustation, read through this entire guide carefully before starting to play.

### Placement of the Supply Ship or Supply Camp 

To place the ship variant of the {% item_page name="supply_camp_and_ship" /%} structure, you need to have a large enough body of water (at least 32 x 20).
To place the camp variant of the {% item_page name="supply_camp_and_ship" /%} structure, you need to have a large enough flat, clear piece of land (at least 16x17 blocks).

When you place the Supply Ship/Camp, it will spawn a chest/rack inside that will contain the {% building name="townhall" /%} block and the {% item name="structurize/sceptergold" /%}. **Once the Supply Ship/Camp is placed, you can't place another in the world.**

Place the Supply Ship/Camp by right-clicking the item directly on the water/ground -- if you have a build tool already, don't use it for this (you're expected to get the tool from the camp/ship rather than already having one).

> **Note:** If you're not able to place the Supply Camp/Ship, try it block by block: one to the right, left, closer or further from the area you cleared. The area cannot contain any holes or have any flowers, grass, ferns, seaweed, coral etc.

### Town Hall

The protected area of your colony (once the {% building name="townhall" /%} block has been placed) will be a square 4 chunks in radius, measured from the chunk where you placed your {% building name="townhall" /%} block the first time (with the default [config](/wiki/misc/configfile)). Therefore, plan carefully where you want to place your {% building name="townhall" /%}. 64 blocks (4 chunks x 4 chunks) in every direction from that chunk will be your protected Town area including mountains, hills, lakes, oceans, caves, world generated structures, etc. from bedrock to sky limit.

**Hint:** The most important tool you will need for the entire mod is the build tool (check our {% item name="structurize/sceptergold" /%} page). Obtain one from the Supply Ship/Camp or craft another so you can place all the huts (including the {% building name="townhall" /%}) in the perfect spot.

#### Build Tool Video

[![Watch the video](https://img.youtube.com/vi/DVGGDUXbTOY/hqdefault.jpg)](https://youtu.be/DVGGDUXbTOY)

Due to the protected area of each colony, you have to carefully scout your surrounding to make sure you are clear of any other colonies nearby preventing you from placing your {% building name="townhall" /%} or limiting your colony area in that direction (in multiplayer, etc.). If there is another colony too close to your current position, you won't be able to place a {% building name="townhall" /%}.

Location info of the next closest colony exists in the debug screen (displayed when you press F3). If there is a colony close to your current position, it will also show you the required distance away to place your {% building name="townhall" /%}.

![Next Colony](../../../assets/images/wiki/tutorial/next_colony.png)

![Player's Colony](../../../assets/images/wiki/tutorial/players_colony.png)

![No Colony](../../../assets/images/wiki/tutorial/no_colony.png)

For example, in the official MineColonies server it will be the default 4 chunk radius (4 + 4 + 1 {center Chunk where {% building name="townhall" /%} was placed} = 9 chunks or 144 total blocks required). It therefore, will tell you: "Next colony is XXX blocks away. (272 required to place a colony)."

**Now, after you have explored, thought carefully, planned, and found the perfect spot to start your colony, let's take a look at the steps to get your colony running!**

## Setting Up Your Colony

### Step 1

Get enough materials and craft your {% item_page name="supply_camp_and_ship" /%}, then place it by right-clicking a block with it (do not use the build tool, if you happen to already have one). There will be a chest (or {% item name="minecolonies/blockminecoloniesrack" /%}) inside the placed Ship/Camp with a {% building name="townhall" /%} block and a {% item name="structurize/sceptergold" /%}.

> **Note:** Gather as many materials as possible to give to your {% worker name="builder" /%} when they start building. They will ask you for all kinds of materials for each building or upgrade, depending on the style of building you have chosen. The materials needed for most styles will be mainly wood, cobblestone, and torches for the lower levels. Further upgrades will require more elaborate materials.

### Step 2

After you have carefully decided where you want to place your {% building name="townhall" /%} (remember, the position where you placed the {% building name="townhall" /%} block at first will be the center of your colony's protected area. Once placed, the area will be set and cannot be changed), use your {% item name="structurize/sceptergold" /%} to place the {% building name="townhall" /%} block and see what the completed structure will look like after construction around it is eventually complete. Once you commit to the placement of the {% building name="townhall" /%} (the green checkmark), the {% building name="townhall" /%} block will be placed. To officially start a colony, you need to right-click on the block, then select Create New Colony. Once you've done this, your initial four citizens will start appearing! 

You can now right-click the {% building name="townhall" /%} block again to look what else you can configure in your colony, like your colony name!

### Step 3

Before any other building can be built, you must craft the {% building name="builder" /%} block and use your {% item name="structurize/sceptergold" /%} to place it. Once you commit to the placement of the Builder's Hut (the green checkmark), the block will be placed and a Builder will be automatically assigned (or you can manually assign one with the best [traits](/wiki/systems/worker) for a Builder if you changed this in the setting tab in the {% building name="townhall" /%} its GUI).

You now officially have a {% worker name="builder" /%}! CONGRATULATIONS!

When accessing the {% building name="builder" /%} block by right-clicking on it, you will see a GUI with different options:

![Builder's hut GUI](../../../assets/images/wiki/gui/buildings/builder/main.png)

You will have to click Build Options on the {% building name="builder" /%}, then click Build Building. This will give your new {% worker name="builder" /%} the build assignment so they can build their own hut first. If the {% building name="builder" /%} is not built, the {% worker name="builder" /%} cannot build any other buildings. 

The {% worker name="builder" /%} will ask for the materials they need. Right-click on them when they have a red icon above their head to see the materials they currently need.

> **Note:** If you see that the Builder hasn't finished a build order and you don't see them asking for any materials, go to the Builder's Hut, recall the Builder, and wait a bit to see what they need. Also, check page 2 of the Builder's Hut GUI and see what resources are in red (those are missing resources).

### Step 4

Once the Builder has finished their hut, you're ready to start building any other building you want! Just craft the desired block and use your {% item name="structurize/sceptergold" /%} to place the building. Once it's placed, go into the GUI for the new block, click Build Options, and then click Build Building. It's that simple!

> **Note:** Consider upgrading the Builder's Hut to level 2 as soon as you can so the Builder can upgrade other buildings.

### Final Notes

You have now completed the most important steps to start and create your colony!

Your question now is probably, "What to build next?" The {% building name="townhall" /%}, {% building name="miner" /%}, {% building name="lumberjack" /%}, {% building name="farmer" /%}, {% building name="fisherman" /%}, {% building name="guardtower" /%}, {% building name="deliveryman" /%}, {% building name="baker" /%}, {% building name="cook" /%}, and more are probably good places to start. 

But you start with only 4 citizens! Now what? Well, the next step after your 4 citizens are employed and are working hard is to plan and place the {% building name="residence" /%}. This will give you additional citizens for each level of a House you build in your colony (after your inital four citizens are housed)! Now, what do you do with so many citizens? You cannot assign more than 1 worker per hut. Therefore, consider building additional huts for more Miners, Foresters, Builders, Farmers, Fishers, and many more! If you struggle with keeping track of what everyone needs, consider making a {% item name="minecolonies/resourcescroll" /%}. Though you only need one Clipboard, you can make a Resource Scroll for each Builder to keep track of their needs!

> **Note:** Upgrade your huts to the highest level possible (level 5 for all of them except the {% building name="tavern" /%}) to get achievements (and increase the speed at which the worker [levels](/wiki/systems/worker)).



---
# File: MinecoloniesWiki\src\content\wiki\tutorials\schematics.mdoc

---
type: page
title: Schematics
---
# Schematics

Schematics are files containing block and entity information of a certain area a player scanned with the {% item name="structurize/sceptersteel" /%} in-game. You can use the {% item name="structurize/sceptersteel" /%} and scan ANY building or structure you like in singleplayer or multiplayer and then have your {% worker name="builder" /%} build it for you (provided that you give them all the materials needed, of course).

- [Schematics](#schematics)
  - [A Schematic World](#a-schematic-world)
  - [Scanning a New Structure](#scanning-a-new-structure)
  - [Placing a Schematic.](#placing-a-schematic)
  - [Style packs](#style-packs)
    - [The pack.json](#the-packjson)
    - [The folder structure](#the-folder-structure)
  - [FAQ](#faq)
      - [How do I install custom schematics I just downloaded?](#how-do-i-install-custom-schematics-i-just-downloaded)
      - [What and where is the scans folder?](#what-and-where-is-the-scans-folder)
      - [Where is the schematic folder?](#where-is-the-schematic-folder)
      - [I have a "\*/minecolonies/01e6a291-8a01-4763-bcae-f3a8797b1d52/cache/" folder, what is that for?](#i-have-a-minecolonies01e6a291-8a01-4763-bcae-f3a8797b1d52cache-folder-what-is-that-for)
      - [I have a "*/blueprints/clients/*" folder, what is that for?](#i-have-a-blueprintsclients-folder-what-is-that-for)
      - [Can I just build my own buildings and then get the colonists to "move in"?](#can-i-just-build-my-own-buildings-and-then-get-the-colonists-to-move-in)
      - [How to create custom huts?](#how-to-create-custom-huts)
  - [\[1.18\] Custom Hut Filenames](#118-custom-hut-filenames)
  - [Custom Supply Ships and Camps](#custom-supply-ships-and-camps)
  - [Supplycamp/ship Content Requirements](#supplycampship-content-requirements)
  - [Hut Requirements](#hut-requirements)
  - [Level Requirements for Overworld Styles](#level-requirements-for-overworld-styles)
  - [Level Requirements for Nether Styles](#level-requirements-for-nether-styles)
  - [Plantation Fields](#plantation-fields)
    - [Vertically Growing Plants (Upwards and Downwards)](#vertically-growing-plants-upwards-and-downwards)
    - [Treeside Plants](#treeside-plants)
    - [Bonemealed Fields](#bonemealed-fields)
    - [Percentage-based Harvesting](#percentage-based-harvesting)
  - [Tips on Building](#tips-on-building)
    - [Do](#do)
    - [Don't](#dont)
  - [Additional Information](#additional-information)
    - [How to override some built-in schematics?](#how-to-override-some-built-in-schematics)
    - [How to use custom huts?](#how-to-use-custom-huts)
    - [How to allow my players to use their own huts' schematics on my server?](#how-to-allow-my-players-to-use-their-own-huts-schematics-on-my-server)
    - [How to allow my players to use their scanned decoration schematics on my server?](#how-to-allow-my-players-to-use-their-scanned-decoration-schematics-on-my-server)
    - [How to disable built-in schematics completely?](#how-to-disable-built-in-schematics-completely)
    - [How to create upgradable decoration schematics?](#how-to-create-upgradable-decoration-schematics)
    - [How to use custom mineshafts in style packs?](#how-to-use-custom-mineshafts-in-style-packs)
    - [How to make custom quarries in style packs?](#how-to-make-custom-quarries-in-style-packs)
    - [Can I make dedicated buildings for specific jobs within the same building type?](#can-i-make-dedicated-buildings-for-specific-jobs-within-the-same-building-type)
    - [How to create parent/child buildings or decorations?](#how-to-create-parentchild-buildings-or-decorations)
  - [What if I have another question?](#what-if-i-have-another-question)

## A Schematic World

While you can build and scan schematics in any world, it's strongly recommended to create a separate creative world to design and build them, separate from your regular colony worlds. It's also often easier to design and build in single-player, though you can of course do it on a server if you want to collaborate with others. It's also recommended to do your builds outside of any existing colony (typically you would not create any colonies at all in that world) and even to disable mobs/weather/ticks to reduce disturbance of your builds.

If you're designing a full style pack, then on the [Discord server](https://discord.minecolonies.com/) you can find a player-created world download that contains build "pads" for most of the building types, along with a command-block-based auto-scanning system to help speed things up -- though of course you can do things a different way if you prefer.

{% version range="1.19--" %}
It's recommended to enable the `blueprintbuildmode` setting in the MineColonies server config file; this makes it a bit easier to do certain things without creating a colony.
{% /version %}

If you're a Patreon, you have access to the official schematic server, and can request a plot area to make your own blueprints there, which may be useful if you want to collaborate with other Patreons.

## Scanning a New Structure

Once you have a structure or area you want to scan to have your Builder build, you need to determine the exact area that needs to be scanned. 

If the area you want to scan contains multiple anchor blocks (hut blocks, {% item name="structurize/blocktagsubstitution" /%}, or {% item name="minecolonies/decorationcontroller" /%}; also including some special blocks like the {% item name="minecolonies/blockstash" /%}) you should shift-left-click the correct anchor block (e.g. the {% building name="barracks" /%} hut block in a {% building name="barracks" /%} schematic) first, before selecting corners.

![TestBuilding](../../../assets/images/wiki/tutorial/testbuilding.png)

Using the {% item name="structurize/sceptersteel" /%}, left-click on the bottom-left corner of your blueprint.

![ScanPoint1](../../../assets/images/wiki/tutorial/scan1.png)

Next, right-click on the top-right corner of your blueprint. It's often helpful to place a {% item name="structurize/blocksubstitution" /%} there so that you have something convenient to click on, without disturbing your actual build.

![ScanPoint2](../../../assets/images/wiki/tutorial/scan2.png)

Then click in the air to see the entire structure.

![ScanFull](../../../assets/images/wiki/tutorial/scan3.png)

Once you have the full area set, you can press Escape and the white outline of the scan area will stay in place. Go around it to double-check that everything you want is in the scan area. When you are ready, you can right-click in the air again to get the GUI to display where you can enter your scan name. Then press the green checkmark to save the scan.

> Special note:
> 
> 1. Do not rename the file after scanning. It **must** be scanned with the correct name.  
> 2. If you include two or more of anchor blocks in your structure you **must** set which will be your anchor for the schematic by sneak left clicking on the desired anchor block.
> 3. Hut buildings use the associated hut block as the anchor, upgradable decorations should use the {% item name="minecolonies/decorationcontroller" /%} as the anchor and non upgradable decorations should use the {% item name="structurize/blocktagsubstitution" /%} block as the anchor.
>    * If you want non upgradeable decorations acting as leisure sites, you must use a {% item name="minecolonies/decorationcontroller" /%} as only those sites are selected to be leisure sites.

{% version range="--1.18.2" %}
Scans are saved in `../minecolonies/scan/new/...`.

Once the scans are saved, they need to be moved to the `../structurize/schematics/(folder)/file` if they are a [custom hut](#how-to-create-custom-huts).
{% /version %}

{% version range="1.19--" %}
Scans are saved in `*/blueprints/<yourplayername>/scans`.

Once the scans are saved, they need to be placed in a style pack, preferably in the correct folder. See the [style packs](#style-packs) chapter for more details.
{% /version %}

## Placing a Schematic.

{% version range="--1.18.2" %}
Once you have scanned a structure, you can use the {%item name="structurize/sceptergold" /%} to have your {% worker name="builder" /%} build it for you. Once you right-click with the build tool, you will have to select "My Schematics" (in the left dropdown menu) and on the right dropdown menu you will see the scans that you have made. There is also a Rename button where you can change the name of the scan. You can also delete any of your saved scans.

![Schematic](../../../assets/images/wiki/tutorial/schematic.png)
{% /version %}

{% version range="1.19--" %}
The scanned structure can be found in the {% item name="structurize/sceptergold" /%} under the style pack with your own name. Click "Switch Pack" -> "&lt;yourplayername&gt;" (icon looks like the {% item name="structurize/sceptersteel" /%}) -> "scans". 
{% /version %}

{% version range="1.19--" %}
## Style packs
Styles are now structured in so-called style packs. This is similar to a resource pack or data pack, in that it has a file with some metadata about the style (the name, a description, optionally a link to an image, etc.), and a folder structure with the actual files.

Stylepacks live in the blueprints folder, within your Minecraft folder. This folder already contains one style pack: One with your player's name. This style pack will contain your scanned files, and can be used to test schematics. In order to make a new style, you need to make a new schematic pack.

### The pack.json
This json file contains metadata describing the style:

| Key Name        | Type             | Description                                                                                                            |
| --------------- | ---------------- | ---------------------------------------------------------------------------------------------------------------------- |
| `"version"`     | number           | Pack version, start at 1, increase it whenever you make a new version of the pack                                      |
| `"pack-format"` | number           | Descriptor for the pack format, needs to be 1 at the moment                                                            |
| `"desc"`        | string           | Description of the style. This will be visible in the build tool to explain what your style is about                   |
| `"authors"`     | array of strings | Names of the authors, in order to credit them. This is visible in the build tool                                       |
| `"mods"`        | array of strings | Names of used mods (ids). The style is not visible if one of those mods is not installed, to prevent broken schematics |
| `"name"`        | string           | The name of the style pack                                                                                             |
| `"icon"`        | string           | The name of the file with an icon which is showed in the style packs selection screen                                  |

You have to increase the version number whenever you release your pack for others to use. You don't need to increase it when testing changes purely in single-player. In particular, whenever the pack is installed on a server, it must have a higher version than what other players already have, or they won't be sent the updates automatically.

### The folder structure

There are several folders, separating the buildings and decorations in categories.
Each of the folders at the highest level can have a couple of icons, named `icon.png` and `icon_disabled.png`. Those are shown in the button bar right above the hot bar.

You can download a template folder structure from [github](https://github.com/ldtteam/minecolonies/tree/version/1.19/src/main/resources/blueprints/minecolonies) (template.zip), which contains the icons used for the official styles already.
That github page also contains examples how different styles are structured.
An overview with which buildings go into which folders can also be found [here](https://airtable.com/shruNNUKhTNk0saz5).

With a few specific exceptions, you don't *have* to strictly follow the standard folder structure -- but it's recommended to stick to it when possible to make it easier for others to find specific buildings and decorations. But you're free to make extra folders to help group separate but related sets together, especially if you don't want them to appear directly as alternate buildings. E.g. if you have two styles of roads, one for early game and one for later game, you could put them in infrastructure/roads/simple/ and infrastructure/roads/nice/.

> **Note:** It's strongly recommended to have each folder only contain files or subfolders, but not both. While it is possible to mix them, the build tool doesn't display it as nicely. Also, style pack folders MUST be unique and style names MUST be unique.
{% /version %}

## FAQ

This is a FAQ section to answer common questions regarding schematics in MineColonies.

#### How do I install custom schematics I just downloaded?
{% version range="--1.18.2" %}
Those custom schematics go in `*/structurize/schematics`. Unzip the zip you downloaded, and put all subfolders in the schematics folder. That folder should contain folders like &lt;stylename&gt;, decorations, walls, supplycamps etc. (depending on which style you installed)
{% /version %}

{% version range="1.19--" %}
The style pack goes in the "blueprints" folder. Unzip the zip, and find the folder containing the pack.json (either the unzipped folder, or a folder directly under it, depending on how the zip was made). This folder needs to be placed in `*/blueprints` directly, not any subfolder thereof -- i.e. you should have "blueprints/onestyle/pack.json", "blueprints/anotherstyle/pack.json", etc.
{% /version %}

#### What and where is the scans folder?

The scans folder is where the schematics are saved after performing a scan using the {% item name="structurize/sceptersteel" /%} in MineColonies.
{% version range="--1.18.2" %}
This is a client-side-only directory which is located in Minecraft's folder under: `*/structurize/scans/`. Freshly scanned schematics can be found in `*/structurize/scans/new/` unless they have been renamed in-game. (If they aren't there, look in `*/minecolonies/scans/new`.) This directory is shared between all your singleplayer games and multiplayer games.
{% /version %}

{% version range="1.19--" %}
This is located in your own style pack in Minecraft's folder under: `*/blueprints/<yourplayername>/scans/`. This folder is shared between all your singleylayer and multiplayer games.
{% /version %}

#### Where is the schematic folder?

{% version range="--1.18.2" %}
Custom schematics need to be copied inside the schematic folder. For both singleplayer and multiplayer games, the folder is under `*/structurize/schematics/`.
{% /version %}

{% version range="1.19--" %}
Custom schematics need to be placed in a (custom) [style pack](#style-packs). For more information about that, look there.
{% /version %}

#### I have a "*/minecolonies/01e6a291-8a01-4763-bcae-f3a8797b1d52/cache/" folder, what is that for?

{% version range="--1.18.2" %}
When playing on a server, the server needs to send the schematics to the players so that the build tool's preview works. Those schematics are saved in Minecraft's directory under `*/structurize/{ServerUUID}/cache/`, where ServerUUID is the unique identifier of the server. Those directories can be safely removed as they are automatically created by the server when needed.
{% /version %}

{% version range="1.19.2--" %}
This was previously used to save schematics from a server and was automatically created as needed. However, this is no longer needed in 1.19.2 and later, so this folder can be removed safely.
{% /version %}

{% version range="1.19.2--" %}
#### I have a "*/blueprints/clients/*" folder, what is that for?

On a server, this folder holds a cached copy of the decorations and shapes used by your players -- possibly even including entire style packs that they've installed themselves (though note that for game balance purposes they won't be able to make functional buildings this way; these must be installed "properly" on the server to be usable).

You can delete these folders at any time (though preferably when the player is not logged in); they will be re-created as needed.
{% /version %}

#### Can I just build my own buildings and then get the colonists to "move in"?

No. Functional buildings must be constructed by the Builder. You have to either use one of the prefabs provided by existing style packs (either included in the mod or via various addons installed separately), or design your own custom huts as an explicitly separate step (typically in a special creative designing world), before getting the builder to place them in your real colony. MineColonies is more like an RTS than it is like classic Minecraft building.

#### How to create custom huts?

To create new schematics, there are some guidelines that you must follow: the scans **must** have the same footprint for each level of the hut; the scans must contain the hut's block, for example the {% building name="builder" /%} block for the {% building name="builder" /%}; the hut block need to be exactly at the same place and have the same rotation for each level.

Ensure you are building your custom hut outside of any colony borders. When placing the hut block inside the custom schematic, shift+right click to place it without activating it. Then you can scan and save the schematic.

{% version range="--1.18.2" %}
The scans' filenames need to follow the naming convention: {StyleName}/{HutName}{HutLevel}.blueprint. For example, for the {% building name="builder" /%} with the MyOwn style, we would have:

*myown/builder1.blueprint*
*myown/builder2.blueprint*
*myown/builder3.blueprint*
*myown/builder4.blueprint*
*myown/builder5.blueprint*

- **Note:** In the {% item name="structurize/sceptergold" /%}, the extension is hidden. HutName can be any of the listed huts below. The maximum level is 5 (except for the {% building name="tavern" /%}; its max level is 3).

Alternative designs can be placed under a style name like "myownalternative", or a subfolder like `myown/alt/*`.
{% /version %}

Once ready, move the `myown` folder into the blueprints folder and start your game. You should be able to see it with the the build tool.

> **Note:** Remember that you need the appropriate hut in your inventory to be able to see the schematics in the build tool.

{% version range="1.19--" %}
The naming for the buildings is not strict anymore. The only things that are important are that they are named consistently, that their names end with the hut level, and that you only use lowercase letters in the hut names (avoid capitals, spaces, or other punctuation).
Alternate designs can just have a different name than the primary one. E.g. if you named the level 1 {% building name="builder" /%} "builder1", an alternative version could be called "altbuilder1" or "builderalt1" or even something completely different ("constructionworker1").
Don't use numbers anywhere in the name except right at the end for the level. E.g. "alt1builder1" won't work as expected.

Once ready, you need to make a [style pack](#style-packs) out of them.
The schematics are visible in the build tool without the hut block, but you can't view them in survival mode (their button is greyed out, with an error message that you need to have the hut block).
{% /version %}

## [1.18] Custom Hut Filenames

Here is a full list, up-to-date as of 14 October 2022, of the building names. Please note *do not use capital letters in hut names*.

{% version range="1.19--" %}
As previously noted, you don't need to stick to these names if you have something better, as long as you're consistent at each level.
{% /version %}


| Level 1        | Level 2        | Level 3        | Level 4        | Level 5        |
| :------------- | :------------- | :------------- | :------------- | :------------- |
| archery1       | archery2       | archery3       | archery4       | archery5       |
| alchemist1     | alchemist2     | alchemist3     | alchemist4     | alchemist5     |
| baker1         | baker2         | baker3         | baker4         | baker5         |
| barracks1      | barracks2      | barracks3      | barracks4      | barracks5      |
| barrackstower1 | barrackstower2 | barrackstower3 | barrackstower4 | barrackstower5 |
| beekeeper1     | beekeeper2     | beekeeper3     | beekeeper4     | beekeeper5     |
| blacksmith1    | blacksmith2    | blacksmith3    | blacksmith4    | blacksmith5    |
| builder1       | builder2       | builder3       | builder4       | builder5       |
| chickenherder1 | chickenherder2 | chickenherder3 | chickenherder4 | chickenherder5 |
| citizen1       | citizen2       | citizen3       | citizen4       | citizen5       |
| combatacademy1 | combatacademy2 | combatacademy3 | combatacademy4 | combatacademy5 |
| composter1     | composter2     | composter3     | composter4     | composter5     |
| concretemixer1 | concretemixer2 | concretemixer3 | concretemixer4 | concretemixer5 |
| cook1          | cook2          | cook3          | cook4          | cook5          |
| cowboy1        | cowboy2        | cowboy3        | cowboy4        | cowboy5        |
| crusher1       | crusher2       | crusher3       | crusher4       | crusher5       |
| deliveryman1   | deliveryman2   | deliveryman3   | deliveryman4   | deliveryman5   |
| dyer1          | dyer2          | dyer3          | dyer4          | dyer5          |
| enchanter1     | enchanter2     | enchanter3     | enchanter4     | enchanter5     |
| farmer1        | farmer2        | farmer3        | farmer4        | farmer5        |
| fisherman1     | fisherman2     | fisherman3     | fisherman4     | fisherman5     |
| fletcher1      | fletcher2      | fletcher3      | fletcher4      | fletcher5      |
| florist1       | florist2       | florist3       | florist4       | florist5       |
| glassblower1   | glassblower2   | glassblower3   | glassblower4   | glassblower5   |
| graveyard1     | graveyard2     | graveyard3     | graveyard4     | graveyard5     |
| guardtower1    | guardtower2    | guardtower3    | guardtower4    | guardtower5    |
| hospital1      | hospital2      | hospital3      | hospital4      | hospital5      |
| library1       | library2       | library3       | library4       | library5       |
| lumberjack1    | lumberjack2    | lumberjack3    | lumberjack4    | lumberjack5    |
| mechanic1      | mechanic2      | mechanic3      | mechanic4      | mechanic5      |
| miner1         | miner2         | miner3         | miner4         | miner5         |
| mysticalsite1  | mysticalsite2  | mysticalsite3  | mysticalsite4  | mysticalsite5  |
| netherworker1  | netherworker2  | netherworker3  | netherworker4  | netherworker5  |
| plantation1    | plantation2    | plantation3    | plantation4    | plantation5    |
| rabbithutch1   | rabbithutch2   | rabbithutch3   | rabbithutch4   | rabbithutch5   |
| sawmill1       | sawmill2       | sawmill3       | sawmill4       | sawmill5       |
| school1        | school2        | school3        | school4        | school5        |
| shepherd1      | shepherd2      | shepherd3      | shepherd4      | shepherd5      |
| sifter1        | sifter2        | sifter3        | sifter4        | sifter5        |
| smeltery1      | smeltery2      | smeltery3      | smeltery4      | smeltery5      |
| stonemason1    | stonemason2    | stonemason3    | stonemason4    | stonemason5    |
| stonesmeltery1 | stonesmeltery2 | stonesmeltery3 | stonesmeltery4 | stonesmeltery5 |
| swineherder1   | swineherder2   | swineherder3   | swineherder4   | swineherder5   |
| tavern1        | tavern2        | tavern3        | N/A            | N/A            |
| townhall1      | townhall2      | townhall3      | townhall4      | townhall5      |
| university1    | university2    | university3    | university4    | university5    |
| warehouse1     | warehouse2     | warehouse3     | warehouse4     | warehouse5     |

## Custom Supply Ships and Camps

The process for custom {% item_page name="supply_camp_and_ship" /%} is slightly different: 

{% version range="--1.18.2" %}
| Camp or Ship | File Name and Path                                |
| ------------ | ------------------------------------------------- |
| Camp         | structurize/schematics/supplycamp/myownsupplycamp |
| Ship         | structurize/schematics/supplyship/myownsupplyship |

So, for example, the path would be `structurize/schematics/wildwest/builder1` for the {% building name="builder" /%} level 1 and `structurize/schematics/supplycamp/wildwestsupplycamp` for the supply camp.
{% /version %}

{% version range="1.19--" %}
| Camp or Ship | File Name and Path                                      |
| ------------ | ------------------------------------------------------- |
| Camp         | blueprints/<myownstyle>/decorations/supplies/supplycamp |
| Ship         | blueprints/<myownstyle>/decorations/supplies/supplyship |
| Nether Ship  | blueprints/<myownstyle>/decorations/supplies/nethership |

So, for example, the path would be `blueprints/wildwest/fundamentals/builder1` for the {% building name="builder" /%} level 1 and `blueprints/wildwest/decorations/supplies/supplycamp` for the supply camp.

Because these have a fixed name, you can't have more than one per style and type.
{% /version %}
## Supplycamp/ship Content Requirements
We have a set of rules a {% item_page name="supply_camp_and_ship" /%} needs to adhere to, as their placement is free in the world:

Size: Maximum 32x32 blocks

Materials:
- Place up to two stacks (in total) of the two or three most used materials in lower building levels, e.g. Cobblestone, Oak logs and diorite.
- 12 White wool for beds
- Other low level materials are fine, keep their amount in moderation, less than 32, use blocks made in the Architects Cutter else
- No high end/valuable materials, like nether/end/prismarine/sea laterns/diamonds/iron
- No unique blocks like bells, sponges, enchanted books
- More valuable blocks like bookshelfs are allowed, but keep them in small amounts <=4
- Hide 1 gold (or raw gold) and 1 coal block in the camp (not visible)

Utilities:
- Add an Architects Cutter
- Add a Crafting Table
- Add a Bed
- Add wooden tools 
- Add buildtool and townhall item into one of the storage blocks

Saplings: Two different variants, for the main wooden material of the lower levels. (6-8 saplings each)
Crops: Only wheat/wheat seeds, can mix in (1-2) durum
Food:
- 32 {% item name="minecraft/bread" /%}
- 16 {% item name="minecraft/cooked_porkchop" /%} OR 8 of some tier 1 Minecolonies food items
- 16 {% item name="minecraft/baked_potato" /%}
- 16 {% item name="minecraft/cooked_beef" /%}

Apply a `groundlevel` tag using the {% item name="structurize/sceptertag" /%} on the floor level (any block).

## Hut Requirements

**Some buildings may also require tags to be set on certain blocks using the {% item name="structurize/sceptertag" /%}.**

| Building                             | Requirements                                                                                                               | Suggested                                                                                                                                  |
| ------------------------------------ | -------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| {% building name="archery" /%}       | 1 practice dummy (a target block); 1 bed per level                                                                         | at least 1 standing position per level (a glowstone block, or any block tagged with `work`)                                                |
| {% building name="alchemist" /%}     | 1 brewing stand per level; 2 soul sand per level starting at level 2 (with 4 soul sand); leaves next to logs, i.e. "trees" |                                                                                                                                            |
| {% building name="baker" /%}         | 1 furnace                                                                                                                  |                                                                                                                                            |
| {% building name="barracks" /%}      | 1 {% building name="barrackstower" /%} per level (up to level 4)                                                           |                                                                                                                                            |
| {% building name="barrackstower" /%} |                                                                                                                            | 1 bed per level                                                                                                                            |
| {% building name="builder" /%}       |                                                                                                                            | 1 rack per level                                                                                                                           |
| {% building name="combatacademy" /%} | 1 practice dummy per level (a carved pumpkin on top of a hay bale); 1 bed per level                                        |                                                                                                                                            |
| {% building name="composter" /%}     | 1 {% item name="minecolonies/barrel_block" /%} per level                                                                   |                                                                                                                                            |
| {% building name="concretemixer" /%} | 3 blocks of flowing water with solid blocks below and air blocks above, which the worker can stand next to                 |                                                                                                                                            |
| {% building name="dyer" /%}          | 1 furnace                                                                                                                  |                                                                                                                                            |
| {% building name="fisherman" /%}     |                                                                                                                            | At least {% meta key="fisher_pond_size" /%} unobstructed body of water if integrating fishing location in the schematic                    |
| {% building name="florist" /%}       | 4 {% item name="minecolonies/compost" /%} per level                                                                        |                                                                                                                                            |
| {% building name="glassblower" /%}   | 1 furnace per level                                                                                                        |                                                                                                                                            |
| {% building name="graveyard" /%}     | Named Graves, with the amount increasing per level                                                                         | 14 named graves at level 1, 18 named graves at level 2, 27 named graves at level 3, 36 named graves at level 4, 50 named graves at level 5 |
| {% building name="guardtower" /%}    |                                                                                                                            | 1 bed for aesthetics                                                                                                                       |
| {% building name="hospital" /%}      | 1 bed per level                                                                                                            |                                                                                                                                            |
| {% building name="residence" /%}     | 1 bed per level                                                                                                            |                                                                                                                                            |
| {% building name="library" /%}       | Bookshelves                                                                                                                |                                                                                                                                            |
| {% building name="miner" /%}         | A few starting ladders where the shaft's ladders will go with the tags [cobble] and [ladder]                               |                                                                                                                                            |
| {% building name="netherworker" /%}  | A nether portal, and an enclosed 1x1x2 room                                                                                |                                                                                                                                            |
| {% building name="plantation" /%}    | 12 per level, 4 for each of sugar cane, cactus and bamboo                                                                  |                                                                                                                                            |
| {% building name="cook" /%}          | 1 furnace per level                                                                                                        |                                                                                                                                            |
| {% building name="school" /%}        | 2 carpets per level                                                                                                        | 4 carpets per level                                                                                                                        |
| {% building name="smeltery" /%}      | 1 furnace per level                                                                                                        |                                                                                                                                            |
| {% building name="stonesmeltery" /%} | 1 furnace per level                                                                                                        |                                                                                                                                            |
| {% building name="tavern" /%}        | 4 beds and a dining room                                                                                                   | {% item_page name="barrels" /%}                                                                                                            |
| {% building name="university" /%}    | Bookshelves                                                                                                                |                                                                                                                                            |
| {% building name="warehouse" /%}     | {% item name="minecolonies/blockminecoloniesrack" /%} (more each level)                                                    |                                                                                                                                            |

It's usually recommended to include a few {% item name="minecolonies/blockminecoloniesrack" /%}s in the various crafter's huts (and more in the {% building name="builder" /%}). A rough guide might be to include 0-1 at level 1, then add 1-2 per level — but you can vary this quite a bit based on your design, layout, and playtesting.

## Level Requirements for Overworld Styles

| Level   | Requirements         |
| ------- | -------------------- |
| Level 1 | Very Easy - Wooden   |
| Level 2 | Easy - Iron          |
| Level 3 | Medium - Nether      |
| Level 4 | Difficult - Ocean    |
| Level 5 | Very Difficult - End |

## Level Requirements for Nether Styles

| Level   | Requirements         |
| ------- | -------------------- |
| Level 1 | Very Easy - Nether   |
| Level 2 | Easy - Rarer Nether  |
| Level 3 | Medium - Overworld   |
| Level 4 | Difficult - Ocean    |
| Level 5 | Very Difficult - End |

{% version range="1.19.2--" %}
## Plantation Fields

In 1.19.2 and beyond, the plantation has been changed to include fields, just like the {% worker name="farmer" /%}. However, unlike the {% worker name="farmer" /%}, these fields can be completely free-form and created by the style designers. Each field has certain requirements for the {% worker name="planter" /%} to do their work successfully.

Each plant has 2 separate tags: a field tag and a work tag.
The field tags are given to the plantation field block to define what plants this field handles. The work tag is given based on the implementation of the field.

A field can have as many field tags as you want, but not 2 of the same.

| Plant          | Field tag       | Work tag        | Maximum work tags                         |
| -------------- | --------------- | --------------- | ----------------------------------------- |
| Sugar cane     | sugar_field     | sugar           | 20                                        |
| Cactus         | cactus_field    | cactus          | 20                                        |
| Bamboo         | bamboo_field    | bamboo          | 20                                        |
| Cocoa          | cocoa_field     | cocoa           | 5 (totalling 20 positions; details below) |
| Vine           | vine_field      | vine            | 20                                        |
| Kelp           | kelp_field      | kelp            | 20                                        |
| Seagrass       | seagrass_field  | seagrass        | 121 (11 x 11 area)                        |
| Sea Pickles    | seapickle_field | seapickle       | 10                                        |
| Glow Berries   | glowb_field     | glowb_vine      | 20                                        |
| Weeping Vines  | weepv_field     | weepv_vine      | 20                                        |
| Twisting Vines | twistv_field    | twistv_vine     | 20                                        |
| Crimson Plants | crimsonp_field  | crimsonp_ground | 121 (11 x 11 area)                        |
| Warped Plants  | warpedp_field   | warpedp_ground  | 121 (11 x 11 area)                        |

The {% worker name="planter" /%} will always attempt to walk to any adjacent air block around the planting position. If none of the adjacent positions are air, the planter will attempt to walk to the block itself. This prevents the {% worker name="planter" /%} from standing on the block itself whilst, for example, placing a block down like cactus, after which the {% worker name="planter" /%} would be standing inside of the plant.

> **Note:** Make sure that the {% worker name="planter" /%} can always get within about 4 blocks of the desired position. If not, they will teleport around to reach the position, which may not always work.

> **Note:** Kelp is an exception to this behaviour. To prevent planters from diving into the water, the planter will walk to the first air block above the work tagged block and look up 26 blocks from the work tagged block. If this is not possible, they will not be able to harvest this plant, so ensure there is air above the water above the work tagged block.
> 
> ![Kelp field movement explanation](../../../assets/images/wiki/misc/planter_kelp_explanation.png)
>
> - The red X is the position where the planter will walk to in the example image.
> - The blue X is the position where the work tag of the block is.

For downwards-growing plants, the planter will attempt to stand above the work tagged block and harvest below them. Make sure the planter can reach the top of the stem.

### Vertically Growing Plants (Upwards and Downwards)
A "vertically growing plant" is a plant that grows in a single line, either upwards or downwards; for example, Sugar Cane is a vertically growing plant that grows upwards. These plants always break fully when their root blocks are broken. The planter will break these at the second block from the root.

Each of these plants have a minimum and sometimes a maximum growth height.
The planter can only harvest them when they reach the minimum. If plants have a maximum height, the planter will have an increasingly higher chance to harvest the plant the taller it gets. Plants are only required to grow to the minimum height within the bounds of the schematic.

| Plant          | Minimum height | Maximum height |
| -------------- | -------------- | -------------- |
| Sugar cane     | 3              | N/A            |
| Cactus         | 3              | N/A            |
| Bamboo         | 6              | 16             |
| Kelp           | 2              | 25             |
| Glowberries    | 3              | N/A            |
| Weeping vines  | 2              | 25             |
| Twisting vines | 2              | 25             |

### Treeside Plants

Treeside plants grow directly on the sides of trees. For these plants, you only need to tag the tree's stem; the working positions will automatically be set to every horizontally adjacent block of the tagged stem. Currently this is only used for Cocoa beans.

Note that this means that the amount of tags you can actually place is the amount of working positions divided by 4!

### Bonemealed Fields

Bonemealed fields will tell the planter to use bonemeal somewhere on the ground to grow plants as if the player had used bonemeal.

The amount of planting positions on these fields are usually unlimited because bonemealing the ground has a set area of effect. However, it is suggested not to make the fields too big; an area around 7x7 is lightly suggested.

Every bonemealed plant works slightly differently.

| Plant          | Work tag location                                                                                                                                                                                                                                        |                                                                                                |
| -------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| Seagrass       | The block directly below the water should tagged; the planter will bonemeal the tagged block itself. Red Xs are where the planter will attempt to walk. Blue Xs are the blocks that are tagged with the work tag.                                        | ![Seagrass explanation](../../../assets/images/wiki/misc/planter_seagrass_explanation.png)     |
| Sea pickles    | The block directly below the water should tagged. The planter will initially place the pickles, then bonemeal the pickles to let them grow. Red Xs are where the planter will attempt to walk. Blue Xs are the blocks that are tagged with the work tag. | ![Sea Pickle explanation](../../../assets/images/wiki/misc/planter_seapickles_explanation.png) |
| Crimson plants | Tag all the nylium ground blocks where the plants are supposed to grow.                                                                                                                                                                                  |                                                                                                |
| Warped plants  | Tag all the nylium ground blocks where the plants are supposed to grow.                                                                                                                                                                                  |                                                                                                |

### Percentage-based Harvesting

Percentage based harvesting fields will attempt to place a minimum percentage of plants down on given spots. These plants &mdash; such as vines &mdash; should then naturally spread to their surroundings without the player's help. The planter has no involvement in this process.

| Plant | Tag location                                                                                                    |
| ----- | --------------------------------------------------------------------------------------------------------------- |
| Vine  | Tag all the positions where the vines themselves can spread to, NOT the blocks where the vines are attached to. |

{% /version %}

## Tips on Building

### Do

- Make all levels of a hut have the same footprint for x, y, and z
- Place the hut block in the same location with the same rotation
- Make sure all {% item name="minecolonies/blockminecoloniesrack" /%} and furnaces are in the same location through all levels (to prevent the contents spilling out when they're getting moved)
- Use {% item_page name="placeholderblocks" /%} where you want to keep any existing block (including from level to level), like the {% building name="barrackstower" /%} in the {% building name="barracks" /%} schematic
- Use {% item_page name="placeholderblocks" /%} at or below ground level
- Place a `groundlevel` tag at ground level if your hut isn't sitting directly on the ground.
- Use only vanilla blocks or Structurize blocks (for official styles)
- Use a Written Book on a lectern if you want to include some instructions. (Keep a copy of the original book and quill elsewhere, so you can edit it for pack updates!)
- If you do use blocks from additional mods, and your buildings would look weird if those blocks simply vanished, then list the mod's id in your pack.json.

### Don't

- Use unobtainable items in builds (no command blocks, petrified wood, infested blocks, or mob heads (including player heads))
- Change someone else's style (officially) unless specifically asked to do so
- Rename schematics after scanning
- Rename, move, or delete existing buildings after a style pack has been released (it will cause problems for existing colonies)
- Use capital letters or punctuation (or spaces) in your schematic names; all lower-case is preferred.
- Put a number anywhere in the schematic name except right at the end to indicate level
- Put a unique item (something with special data, such as a written book or a patterned banner) into an item frame, chest, or other container (lecterns are ok). Also avoid putting complex-dyed armor anywhere; single-dyed is ok but remember that the player will have to figure out and duplicate it.
- Don't use Tag Anchor blocks in schematics that have a Decoration Controller or a Hut Block

## Additional Information

### How to override some built-in schematics?

{% version range="--1.18.2" %}
Simply create a schematic file with the same style/name. For instance, to override the {% building name="builder" /%} wooden level 1, create a schematic file name called wooden/builder1.blueprint.
{% /version %}

{% version range="1.19--" %}
Unfortunately, this is not possible, unless you override the entire style (make a style pack with the same name as an existing style pack, in that case).

However, you can create an "addon pack" with only a few buildings and/or decorations, intended to be used with the original pack; players will be able to mix'n'match from all packs.
{% /version %}

### How to use custom huts?

{% version range="--1.18.2" %}
The custom huts need to be copied in the schematics folder.
{% /version %}

{% version range="1.19--" %}
The custom huts need to be copied into a style pack.
{% /version %}

Once copied, you can start your singleplayer or multiplayer game as usual. You should see them in the {% item name="structurize/sceptergold" /%} (if you have the hut block in your inventory).

### How to allow my players to use their own huts' schematics on my server?

You will have to copy them yourself in the blueprints folder on the server and restart it.

### How to allow my players to use their scanned decoration schematics on my server?

This is enabled by default, but if you want to change this, edit the Structurize configuration file at `minecraft/config/structurize-common.toml` and set allowPlayerSchematics to true. This allows the player to use their own decorations. It is not possible for the player to use their own huts' schematics. You can also limit the number of players' schematics at any given time by editing maxCachedSchematics (default is 100). When the limit is reached, the server will start deleting unused schematics.

### How to disable built-in schematics completely?

Edit the Structurize configuration file at `minecraft/config/structurize-common.toml` and set ignoreSchematicsFromJar to true. Be aware: things will break if some huts' schematics are missing.

### How to create upgradable decoration schematics?

{% version range="--1.18.2" %}
Add the {% item name="minecolonies/decorationcontroller" /%} somewhere in the schematic with the name of the schematic, where you'll put it in the file directory, and its level. Make sure to actually put the decoration in that file path, but only after scanning - don't include the path in the scan name.
{% /version %}

{% version range="1.19--" %}
Put the {% item name="minecolonies/decorationcontroller" /%} somewhere in the schematic, and make sure the name of the schematic ends with a number while scanning. The decoration controller will automatically find the decoration in the same folder with the next number.

Contrary to the image below, you don't need to enter anything into the deco controller's GUI before scanning any more -- but the rest of that image is still useful.
{% /version %}

![Upgradable Decos](../../../assets/images/wiki/tutorial/upgradabledecos.png)

### How to use custom mineshafts in style packs?

The size must be 9 x 4 x 9 blocks. They must be named and oriented exactly the same (i.e. do not rotate at all) as in the default style pack (careful: the names aren't entirely consistent with the layout, so make careful note which is which!) Use an existing style pack as a template along with the {% item name="structurize/sceptersteel" /%} to create the blueprints.

{% version range="--1.18.2" %}
The custom mineshafts need to be at `schematics/yourstyle/miner/*`.
{% /version %}

{% version range="1.19--" %}
The custom mineshafts need to be at `blueprints/yourstyle/infrastructure/mineshafts/*.

It's recommended that you use the {% item name="structurize/blocktagsubstitution" /%} to make the mineshafts `invisible`. Take care that the anchor is in the same position as in the original mineshafts -- the very center bottom block.
{% /version %}

### How to make custom quarries in style packs?
The {% building name="quarry" /%} is split into a "top part" and a "bottom part". Both parts only have one level each (but unlike decorations, are expected to still include the level number in the filename).

The top part is constructed by the {% worker name="builder" /%} and is the part outside of the quarry pit -- decorative walls, fences, cranes, racks, etc. This contains the actual {% building name="quarry" /%} hut block itself , which should pretty much always be on the second y level up from the bottom (i.e. the bottom layer is the ground level, then the hut is on the next layer up), although with some caveats this is not absolutely required.

The bottom part is constructed by the {% worker name="quarrier" /%} and is the actual quarry pit itself, consisting mostly of placeholders, air blocks, and decorative elements. While you can also set the anchor manually, it's recommended to use a {% item name="structurize/blocktagsubstitution" /%}. The anchor should normally be at the very top layer, although with some caveats it can be elsewhere.It's also recommended to tag this as `invisible` to avoid cluttering the build menu -- it usually doesn't make sense to let it be built as a decoration.

{% version range="--1.18.2" %}
The top parts must be named `simplequarry1` and `mediumquarry1`, and the corresponding bottom parts are `simplequarryshaft1` and `mediumquarryshaft1`.
{% /version %}

{% version range="1.19--" %}
The top part can be in any folder and name that you like (and you can have more than one alternate), but the canonical names are `infrastructure/mineshafts/simplequarry1` and `infrastructure/mineshafts/mediumquarry1`. Players will usually expect to find them in this folder.

The bottom part should usually be in the same folder and with the matching name but with `shaft` before the number -- e.g. `infrastructure/mineshafts/simplequarryshaft1` and `infrastructure/mineshafts/mediumquarryshaft1`.

Alternatively, you can put a `shaft=` tag on the top part's {% building name="quarry" /%} block to specify a different name -- e.g. `shaft=bighole1` to look for `bighole1` in the same folder as the quarry, or `shaft=decorations/quarrypit1` to look in a different folder.
{% /version %}

Importantly: when built, the two schematics are aligned such that the anchor of the bottom part is exactly two blocks below the anchor of the top part. You should carefully align them when designing. It may be helpful to build them directly on top of each other when designing and simply scan them as two separate parts.

It is permitted for the quarry to be a slightly different size from the default versions, but it's strongly encouraged (for game balance reasons) to make each one approximately the same size as the originals, and in particular to have the same amount of air blocks in the bottom part, since this affects the final yield of cobble or other stone.

### Can I make dedicated buildings for specific jobs within the same building type?

{% version range="--1.18.2" %}
Nope.
{% /version %}

{% version range="1.19--" %}
Using the {% item name="structurize/sceptertag" /%}, you can place one or more `job=` tags on the hut block to limit which jobs can actually work there. Currently, the only sensible combinations are:

* {% building name="guardtower" /%}: one or more of `job=knight`, `job=ranger`, `job=druid`. You can use these if you want to decorate your towers with specific themes (e.g. archery motifs).
* {% building name="miner" /%}: one of `job=miner` or `job=quarrier`. You can use this if you want to make a large building with a mineshaft for the {% worker name="miner" /%} and a smaller one without for the {% worker name="quarrier" /%}.
{% /version %}

### How to create parent/child buildings or decorations?

The {% building name="barracks" /%} and {% building name="barrackstower" /%} always have a parent/child relationship (i.e. the {% building name="barrackstower" plural=true /%} are embedded within the {% building name="barracks" /%}, not directly built separately with the build tool). It's also possible to do the same with other buildings -- either putting one or more buildings into a containing decoration (e.g. a "district" of related buildings) or even embedding buildings within other buildings.

Some popular combinations are to embed couriers within the warehouse, and fields within the farmer. Other combinations are possible, depending on your goals for the style -- but don't go too overboard! Some players like combination buildings since they fit nicely together, but others don't like them since they can take away flexibility and creativity when building a colony.

When designing parent/child schematics, the key is the {% item name="structurize/blocksubstitution" /%}. The parent schematic needs to contain the child hut block in the correct position and rotation, along with {% item name="structurize/blocksubstitution" /%}s wherever there should be a block from the child, and the parent's own blocks. Similarly, the child schematic needs its own hut block and other blocks, and {% item name="structurize/blocksubstitution" /%}s wherever there should be a block from the parent. It can be helpful to make a temporary scan of either the parent or child and overlay them over the other to help line things up, or to build both together and then duplicate it and split apart the designs.

While strictly speaking it's only mandatory to include the child hut at the level that it's introduced into the parent and you *could* put only a {% item name="structurize/blocksubstitution" /%} at higher levels, it's strongly recommended to always include the child hut in every higher level of the parent. This works better when someone moves or repairs the parent, or skips levels and pastes it directly at a higher level.

Also remember that the child building can't be upgraded to a higher level than the parent building. This limit doesn't apply if the parent is a non-upgradeable decoration.

Be careful of "research loops" -- if the player needs to build a child before they can unlock a parent, that's a problem (unless you also have an alternate standalone of the child).

Since the parent will contain multiple hut blocks, you will always need to explicitly specify the anchor block (the main parent hut block if a building, or a {% item name="structurize/blocktagsubstitution" /%}), otherwise you'll get an error that the anchor was ambiguous and it will not work correctly.

{% version range="--1.18.2" %}
Since you can only have one version of each building in each folder, combinations should be used very sparingly. The parent and child need to be in the same folder.

To place the child hut in the parent, you can simply shift-click it, just like when placing it in the child itself. Be sure to get the location and rotation correct -- the child hut will be built with the matching orientation relative to that.
{% /version %}

{% version range="1.19--" %}
The parent and child need to be in the same folder. This doesn't mean that you can't combine buildings that are normally in different folders -- just that the version that's intended to be the child must be in the same folder as the parent. You may still have another version of the child (to be used by itself, not as a child) in the original folder if you like.

It's not supported to have a child contain additional children of its own -- you're limited to just the two layers (though the parent can contain multiple children of either the same or different types).

Regardless of which method you use to build, be sure to get the location and rotation of the child hut correct when placing it in the parent -- the building will be built with the matching orientation relative to that.

If you've used the default folder and filenames for your child, then you can simply shift-click the child hut to place it into the parent, similar to older versions. However this is not the most recommended way to do things any more.

The preferred method is to make another level of your child containing only the hut block, giving it the same folder and name as your actual child, but level 0 (e.g. `craftsmanship/storage/mycourier0`). You can make it the same size as your real child (surrounded by {% item name="structurize/blocksubstitution" /%}s) if you wish, but scanning a 1x1x1 is fine too. After scanning, you need to move this to its final location in your actual style pack, and then paste it from there (*not* from your scans!) into your parent. It doesn't matter whether you use schematic or constructed paste. Paste the same level 0 into all levels of the parent. After it's pasted, you can delete the level 0 blueprint -- it should not be included in your final released style pack. (Note that when you go to paste it, the build tool labels it as "level 1" of 6. You can confirm you have the right one by checking the tooltip name.)

Another option is called "auto-levelling". This is where instead of making and pasting a level 0 into each level of the parent, you instead paste the actual matching child level (i.e. level 1 child in the level 1 parent, level 2 child in the level 2 parent, etc). Again it doesn't matter whether you use schematic or constructed paste, but either way you'll probably have to fix up some of the overlapping blocks afterward. You do still need to include the child hut's blueprints in your released style pack, and you do still have to paste it from your actual style pack and not your scans folder.

With auto-levelling, the builder will upgrade the child at the same time as upgrading the parent, instead of the player needing to explicitly build or upgrade one after the other. While this may sound simpler, there are some downsides: the biggest is that won't work well for child buildings that have required functional blocks (such as beds, furnaces, racks, etc), although purely decorative ones are fine. You also should not use this where the child is locked behind research, unless you can be absolutely certain that it's already unlocked (e.g. if the parent is unlocked after the child -- though still be careful of loops). The "level 0" method doesn't have these issues.

Since you can have multiple alternates of buildings (in the same or separate folders), it's possible to make a particular building type have both a standalone version as well as a version embedded as a child. It's strongly recommended to use the {% item name="structurize/sceptertag" /%} to mark any blueprint intended for use only as a child (in the child schematic itself) as `invisible` so that it doesn't show up for building standalone -- especially as child versions are often simpler or cheaper and may be missing walls or other things intended to be provided by the parent, so won't look good on their own or might break game balance. It's also possible to have each child of a parent be its own unique blueprint -- but that requires even more scans and more care when pasting to use the right alternate.
{% /version %}

## What if I have another question?

The `#schematics-design` channel in the {% social_link id="discord" /%} is for asking questions about designing your own schematics.



---
# File: MinecoloniesWiki\src\content\wiki\tutorials\worldgencolonies.mdoc

---
type: page
title: World Generation Colonies
---

As you explore the world, you'll notice that there are some colonies abandoned by the former residents. Moving into one of these abandoned colonies is a way to quickstart your own colony!

{% version range="1.18--" %}
> **Note:** Vanilla villages will still spawn as normal. If you wish to turn off this feature, you can do so in the configuration options for your world.
{% /version %}

## Getting Started

To find a world generation colony... just start exploring! They spawn randomly at a low chance in the world in one of several styles. Currently, only a subset of official style colonies can spawn naturally in the world, but more will be added in the future!

Only a subset of possible buildings can generate in a world generation colony, and most will be level 1, though some buildings might be level 2. Sometimes, multiple of the same type of building can spawn, too!

First, find the town hall and click "Create new colony." This is equivalent to you placing a town hall down from the supply camp.

From there, go to each hut block in the found (and now, founded) colony and click "Reactivate" to reclaim the building and add it to the town hall.

Additionally, you can repair each building to make your new colony look like new.

That's it! You've claimed a colony in the world, and now you can start building your new colony!

# Creating Your Own World Generation Colonies Style

First, you need to design and build your own [schematic](schematics). Once you've built your style, you can add jigsaw block connectors at appropriate places and export the structure as a .nbt file. That way, Minecraft knows how to build an abandoned colony in your style!

## Tagging and Prepping

The most important thing to ensure that colonies register as abandoned — and can be restored — is to use the tag tool to tag the hut block appropriately.

1. Tag the hut block with its style name (e.g., "medieval_oak" for the Medieval Oak style). The name must match the style's folder name in the MineColonies.jar file or resource pack.
2. Add the tag "abandoned" to the hut block. If you right-click the building with an empty hand, it should now say that the building is abandoned and tell you to seek out the town hall.

## Adding Jigsaw Blocks

Place jigsaw blocks where a structure would naturally connect to a road or — if creating a road — where it would connect to a structure. Make sure the block is placed at the edge of the structure facing toward a connecting block.

When editing a jigsaw block, there are a few different properties that can be set.

- `name:` the name of this jigsaw block. The name can be unique or generic. For the styles in MineColonies, all buildings have the name "minecolonies:building_entrance" and all roads "minecolonies:street". Some styles will also use "minecolonies:field"; this one is used exclusively to connect a field to a farmer building when the farmer building does not a field as part of the level 1 structure.
- `target`: When a structure is placed, the target of its jigsaw block determines the next jigsaw block that is added. The target of a placed structure must match the name of the next one to be placed.
- `pool`: To restrict the list of applicable structures, use the target pool. MineColonies uses three dedicated pools for each colony style: "&#60;stylename&#62;/roads", "&#60;stylename&#62;/buildings", and "&#60;stylename&#62;/terminators".

Edit the jigsaw block to set the following properties. 
(Note that the following is an example using the Medieval Oak style. Replace "medieval_oak" with your style's name.)

- Buildings
    - `name`: "minecolonies:building_entrance"
    - `pool`: "minecolonies:medieval_oak/roads"
    - `target`: "minecolonies:building_entrance"
    - `turns into`: "minecraft:structure_void"
- Roads (Connecting to buildings)
    - `name`: "minecolonies:street"
    - `pool`: "minecolonies:medieval_oak/buildings"
    - `target`: "minecolonies:building_entrance"
    - `turns into`: "minecraft:structure_void"
- Roads (Connecting to roads)
    - `name`: "minecolonies:street"
    - `pool`: "minecolonies:medieval_oak/roads"
    - `target`: "minecolonies:street"
    - `turns into`: "minecraft:structure_void"
- Terminators (to end roads more decoratively and as fallback structures for roads and buildings)
    - `name`: "minecolonies:street"
    - `turns into`: "minecraft:structure_void"

## Processors

Processors are used to alter a structure's blocks during placement. Specifically for MineColonies, we use different processors depending on the structure that is placed.

There are three processor files for each style.

### street.json
Road processors are defined in "&#60;stylename&#62;/street.json". The main purpose of this processor is to replace porous materials like dirt, dirt paths, and gravel with wooden planks when the road is in water. This will also add minor decay in the form of grass blocks instead of dirt paths.

### decoration.json

Park processors are defined in "&#60;stylename&#62;/decoration.json". This processor adds decay to the parks by turning some stone brick into weathered variants like mossy or cracked stone bricks or replaces some dirt blocks with coarse dirt.

### placeholder_replacement.json

Building processors are defined in "&#60;stylename&#62;/placeholder_replacement.json". Similar to how zombie villages decay, this processor removes various building blocks or replaces them with cobweb. Which blocks and how often depends on the given style. In addition to decay, the placeholder replacement is also responsible for converting the Structurize placeholder blocks into suitable in-game similar to how a builder treats these blocks when constructing a building.

- "structurize:blocksolidsubstitution" turns into dirt, which may be converted to another block by a processor defined later in the same file.
- "structurize:blocksubstitution" is ignored, meaning that existing blocks in the world will remain in place when the structure generates.

#### Decay Example

The rule below replaces oak logs with cobwebs at a 4&#37; probability. That probability may be fine for one style but be too high or too low for another style.

```json
{
  "output_state": {
    "Name": "minecraft:cobweb"
  },
  "input_predicate": {
    "block": "minecraft:oak_log",
    "probability": 0.04,
    "predicate_type": "minecraft:random_block_match"
  },
  "location_predicate": {
    "predicate_type": "minecraft:always_true"
  }
}
```



---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\citizen_names.mdoc

---
type: page
title: Citizen Names
---

Citizen names can be changed via a datapack. For the ones already in the mod, you can look it [on Github](https://github.com/ldtteam/minecolonies/blob/version/main/src/main/resources/data/minecolonies/citizennames).
The default.json file is loaded by default, patreons have a button in the town hall to change to any of the other ones in data packs in the `citizennames` data location.

There are also sample data packs on the [citizen names](/wiki/misc/custom_citizen_names) page.

{% schema name="citizen_names" /%}


---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\custom_visitors.mdoc

---
type: page
title: Custom Visitors
---

Custom visitors can allow you to make specially skinned visitors in your colony that you can hire to become citizens.

> **Note:** Due to limitations, custom visitors skins cannot carry over to citizens.

By default, these include some of our Patrons, however you may add custom ones to your desire.

{% schema name="custom_visitors" /%}


---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\loot_tables.mdoc

---
type: page
title: Loot Tables
---

Loot Tables control a variety of item drop behaviors, and can be used to add randomized chance to Crafter Recipes. They are loaded from `loot_tables`. See the [Official Wiki](https://minecraft.wiki/w/Loot_table) for technical details, and the GitHub for some MineColonies default loot tables [here](https://github.com/ldtteam/minecolonies/tree/version/main/src/main/resources/data/minecolonies/loot_tables) and [here](https://github.com/ldtteam/minecolonies/tree/version/main/src/datagen/generated/minecolonies/data/minecolonies/loot_tables).


---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\recipes.mdoc

---
type: page
title: Recipes
---

While Item Tags determine the recipes that workers can be taught, colonists can also automatically learn special **Crafter Recipes**. These recipes can be different than those available to the player or even reflect items that can't be made in any other way. Unlike Tags, Crafter Recipes can and must hold a large number of properties, some containing arrays or objects of properties themselves. Supported properties include: 

| Crafter Recipe Key Name      | Type             | Description                                                                                                                                                                                                                                                   |
| ---------------------------- | ---------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `type`                       | String           | `"recipe"` to add a recipe, and `"remove"` to disable one.                                                                                                                                                                                                    |
| `crafter`                    | String           | The name of the worker who will learn the recipe. Mandatory.                                                                                                                                                                                                  |
| `inputs`                     | Array Of Objects | The `"item"`s and `"count"`s of the required input items that will be consumed for the recipe.                                                                                                                                                                |
| `inputs[].item`              | String           | The Resource Location identifier of an item consumed by the recipe.                                                                                                                                                                                           |
| `inputs[].count`             | Integer          | The number of that item consumed by default. If not present, defaults to 1.                                                                                                                                                                                   |
| `result`                     | String           | The Resource Location identifier of the item the recipe produces.                                                                                                                                                                                             |
| `count`                      | Integer          | The count of `"result"` items that should be returned. If not present, defaults to 1.                                                                                                                                                                         |
| `loot-table`                 | String           | The Resource Location of a [loot table](https://minecraft.wiki/w/Loot_table), used for outputs that require some randomization.                                                                                                                               |
| `additional-output`          | Object           | The `"item"` and `"count"` of a single additional output item.                                                                                                                                                                                                |
| `additional-output.item`     | String           | The Resource Location identifier of an item consumed by the recipe.                                                                                                                                                                                           |
| `additional-output.count`    | Integer          | The number of that item consumed by default. If not present, defaults to 1.                                                                                                                                                                                   |
| `alternative-output`         | Array Of Objects | Contains the `"item"` and `"count"` of alternative output items. These consume the same inputs, share the same "loot-table", and return the same "additional-output" as the main recipe, but produce a different result on demand through the Request System. |
| `alternative-output[].item`  | String           | The Resource Location identifier of an item consumed by the recipe.                                                                                                                                                                                           |
| `alternative-output[].count` | Integer          | The number of that item consumed by default. If not present, defaults to 1.                                                                                                                                                                                   |
| `intermediate`               | String           | The Resource Location identifier of a block required to craft the item, typically a furnace.                                                                                                                                                                  |
| `research-id`                | String           | The Resource Location identifier of a research that is automatically learned after the colony has a research completed, if all other requirements are met.                                                                                                    |
| `not-research-id`            | String           | The Resource Location identifier of a research that automatically causes the recipe to be unlearned. Most commonly used to replace a default recipe.                                                                                                          |
| `min-building-level`         | Integer          | The minimum building level, inclusive, at which the recipe may be automatically learned, if all other requirements are complete.                                                                                                                              |
| `max-building-level`         | Integer          | The building level where the recipe will be automatically unlearned, if the building meets or exceeds it.                                                                                                                                                     |
| `must-exist`                 | Boolean          | A special requirement. If a recipe matching the output exists, automatically adds all `"alternative-output"` as recipes.                                                                                                                                      |
| `show-tooltip`               | Boolean          | If true, displays the building that can craft the recipe, as well as any requirements to unlock the recipe, as a tooltip for the item.                                                                                                                        |
| `recipe-id-to-remove`        | String           | A Resource Location of a different crafter recipe to remove, preventing buildings from learning it. Requires `"type":"remove"`. All other properties are ignored. Example: `"recipe-id-to-remove": "minecolonies:blacksmith/platearmorchestplate"`.           |

On existing worlds, removing a Crafter Recipe through data packs will automatically remove it from existed constructed buildings that have learned it.

For example crafter recipes, and their canonical names, see GitHub [here](https://github.com/ldtteam/minecolonies/tree/version/main/src/main/resources/data/minecolonies/crafterrecipes) and [here](https://github.com/ldtteam/minecolonies/tree/version/main/src/datagen/generated/minecolonies/data/minecolonies/crafterrecipes).

## Player Recipes

**Player Recipes** can be added by data packs using vanilla features, by adding to the `recipes` Data Location. See the [Official Wiki](https://minecraft.wiki/w/Recipe) for details, and the [GitHub for MineColonies default recipes](https://github.com/ldtteam/minecolonies/tree/version/main/src/main/resources/data/minecolonies/recipes).



---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\research.mdoc

---
type: page
title: Research
---

The [Research System](/wiki/systems/research) used by the {% building name="university" /%}  can be lightly tweaked or heavily modified through the use of data packs in MineColonies versions 0.14.0 or higher. For the default research data and example files, see [the GitHub](https://github.com/ldtteam/minecolonies/tree/version/main/src/datagen/generated/minecolonies/data/minecolonies/researches).
Researches consist of three components: the branch that contains the research, the research itself, and the research effect.

## Branches

Branches are the groups of research, such as "Civilian" or "Combat". Branch files must contain one of `"branch-name"` or `"base-time"`.

{% schema name="research_branch" /%}

## Research

Researches are the individual components of a branch. Branches must have at least one present research to be displayed to the user.

{% schema name="research" /%}

## Research Effects

Research Effects describe the actual impact and strength of that impact on the colony, players, or colonists. All Research Effects have an automatically generated effect strength 0 at level 0. If no other levels are defined, defaults to level one having a strength of 5, enough to unlock all levels of a building.

{% schema name="research_effect" /%}

### MineColonies Research Effects

For now, Research Effects must have a corresponding code effect, as listed below. Suggested ranges give guidelines assuming a modpack includes no significant balance reworks.

| Effect Resource Location                              | Suggested Range  | Description                                                                                                                                                                             |
| ----------------------------------------------------- | ---------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `minecolonies:effects/archerdamageaddition`           | -2 to 10         | Increases damage by {% worker name="archer" plural=true /%}.                                                                                                                            |
| `minecolonies:effects/citizeninvslotsaddition`        | 0, 9, 18, and 27 | Increases the number of inventory slots for colonists.                                                                                                                                  |
| `minecolonies:effects/citizencapaddition`             | -21 to 475       | Increases the total number of colonists that may be active at a time above 25. Does not override serverconfig maximum.                                                                  |
| `minecolonies:effects/enhancesgatedurabilityaddition` | -4 to 20         | Increases the durability of MineColonies Gates when attacked by raiders.                                                                                                                |
| `minecolonies:effects/fleeingspeedaddition`           | 0 to 5           | Applies a Speed potion effect to injuried Guards when they flee for ten ticks, of Speed level equal to strength.                                                                        |
| `minecolonies:effects/healingsaturationlimitaddition` | 0 to 20          | **Reduces** the required saturation before a colonist gets a bonus to regeneration rate.                                                                                                |
| `minecolonies:effects/healthaddition`                 | -12 to 20        | Increases colonist max health.                                                                                                                                                          |
| `minecolonies:effects/meleedamageaddition`            | -3 to 10         | Increases Knight Guard damage.                                                                                                                                                          |
| `minecolonies:effects/workingdayhaddition`            | -2 to 2          | Increases the working day period for Worker Colonists.                                                                                                                                  |
| `minecolonies:effects/archerarmormultiplier`          | -0.8 to 2        | Increases {% worker name="archer" /%} armor damage reduction.                                                                                                                           |
| `minecolonies:effects/armordurabilitymultiplier`      | 0 to 4           | Increases effective armor durability of all Guards (and other colonists that can wear armor).                                                                                           |
| `minecolonies:effects/blockattacksmultiplier`         | 0 to 1           | Increases Knight Guard rate of successfully blocking attacks.                                                                                                                           |
| `minecolonies:effects/blockbreakspeedmultiplier`      | -0.8 to 5        | Increases the Block Break speed of Miners and Builders.                                                                                                                                 |
| `minecolonies:effects/blockplacespeedmultiplier`      | -0.8 to 5        | Increases the Block Place speed of Miners and Builders.                                                                                                                                 |
| `minecolonies:effects/doublearrowsmultiplier`         | 0 to 1           | Applies a chance of Multishot effect on {% worker name="archer" plural=true /%}.                                                                                                        |
| `minecolonies:effects/farmingmultiplier`              | 0 to 1           | Applies a chance to get additional crops from farming.                                                                                                                                  |
| `minecolonies:effects/fleeingdamagemultiplier`        | -3.0 to 3.0      | Reduces damage suffered by low-health colonists when fleeing from attackers.                                                                                                            |
| `minecolonies:effects/growthmultiplier`               | -0.8 to 5        | Increases rate children mature.                                                                                                                                                         |
| `minecolonies:effects/happinessmultiplier`            | -0.5 to 3.0      | Increases colonist Happiness.                                                                                                                                                           |
| `minecolonies:effects/levelingmultiplier`             | -1 to 3.0        | Increases the rate colonists gain experience.                                                                                                                                           |
| `minecolonies:effects/meleearmormultiplier`           | -0.8 to 2        | Increases Knight Guard armor damage reduction.                                                                                                                                          |
| `minecolonies:effects/minimumstockmultiplier`         | -0.8 to 5        | Increases the number of minimum stock a building can have simultaneously.                                                                                                               |
| `minecolonies:effects/moreoresmultiplier`             | -0.8 to 5        | Increases chance of Miners finding additional ores when mining blocks with the `"#minecolonies:orechanceblocks"` Tag.                                                                   |
| `minecolonies:effects/podzolchancemultiplier`         | -0.8 to 5        | Increases chance of a Composter producing Podzol when composting for dirt.                                                                                                              |
| `minecolonies:effects/recipesmultiplier`              | -0.8 to 5        | Increases maximum number of recipes a building can contain.                                                                                                                             |
| `minecolonies:effects/regenerationmultiplier`         | -0.8 to 3        | Increases colonist regeneration rate, if not starving.                                                                                                                                  |
| `minecolonies:effects/saturationmultiplier`           | -0.5 to 3        | Increases colonist Saturation from eating foods.                                                                                                                                        |
| `minecolonies:effects/sleeplessmultiplier`            | -5 to 1          | **Reduces** the rate Guards fall asleep when increased.                                                                                                                                 |
| `minecolonies:effects/teachingmultiplier`             | -0.8 to 5        | Increases student experience when learning from a Teacher as a child.                                                                                                                   |
| `minecolonies:effects/tooldurabilitymultiplier`       | -5 to 10         | Increases effective durability of colonist tools, including weapons.                                                                                                                    |
| `minecolonies:effects/walkingmultiplier`              |                  | Increases colonist walk speed.                                                                                                                                                          |
| `minecolonies:effects/crushing11unlock`               |                  | If above 0, allows the Crusher to produce one output item for one input.                                                                                                                |
| `minecolonies:effects/consumearrowsunlock`            |                  | If above 0, allows {% worker name="archer" plural=true /%} to use arrows to increase damage. If they run out of arrows, they'll still fire, just for less damage. Don't think about it. |
| `minecolonies:effects/knighttauntmobsunlock`          |                  | If above 0, allows Knights to Taunt enemies, forcing most to target the Knight.                                                                                                         |
| `minecolonies:effects/minerfireresunlock`             |                  | If above 0, prevents any fire or lava damage to the Miner.                                                                                                                              |
| `minecolonies:effects/piercingarrowsunlock`           |                  | If above 0, provides Piercing II to {% worker name="archer" plural=true /%}.                                                                                                            |
| `minecolonies:effects/plant2unlock`                   |                  | If above 0, allows the Plantation to have two output materials set at once.                                                                                                             |
| `minecolonies:effects/platearmorunlock`               |                  | If above 0, allows the Blacksmith to make plate armor.                                                                                                                                  |
| `minecolonies:effects/railsunlock`                    |                  | If above 0, allows Colonists to magically summon carts and ride railways.                                                                                                               |
| `minecolonies:effects/retreatunlock`                  |                  | If above 0, allows Guards to retreat if under 20% HP.                                                                                                                                   |
| `minecolonies:effects/shieldusageunlock`              |                  | If above 0, allows Knight Guards to use and request Shields.                                                                                                                            |
| `minecolonies:effects/whirlwindabilityunlock`         |                  | If above 0, allows Knight Guards to use a Whirlwind Attack.                                                                                                                             |
| `minecolonies:effects/workinginrainunlock`            |                  | If above 0, allows all workers and guards to work in rain. Has no effect if the server is set to do that anyway.                                                                        |

## MineColonies Building Unlocks

Research Effects may also optionally unlock a building. These reserved resource Locations will limit, and a research using the effect is loaded.
The strength of the effect determines the maximum level of upgrade that can be requested for the building: an effect of strength 1 would allow only level 1 buildings, while strength 2 would allow upgrades to level 2, and so forth.
They do not validate that these unlocks are reasonable, or even that they do not require the building that they unlock.


---
# File: MinecoloniesWiki\src\content\wiki\tutorials\datapacks\tags.mdoc

---
type: page
title: Tags
---

Tags are a vanilla Minecraft feature, used to give properties to specific items (if within the `tags\items` directory) or blocks (if within `tags\blocks` directory). Item tags also used for Ore Dictionary behaviors. Tags apply a property based on the file name: `\data\minecolonies\tags\blocks\concrete.json` applies a `#minecolonies:concrete` tag to all blocks matching the Resource Locations contained within it or within Block Tags matching that Resource Location, and that `#minecolonies:concrete` block tag determines what materials a {% worker name="concretemixer" /%} can mine.

All Tag JSONs operate in **merge** mode by default. They can instead override, removing any other blocks or items from JSONs matching that name that were loaded first. To use override mode, you must explicitly set `"replace": true` in addition to the `"values": ""` name-value pair. This `"replace"` name is not mandatory for merge mode, but for ease of readability, it's strongly encouraged to use `"replace": false` if intentionally adding to existing Tags.

The only other supported name-value pair for a Tag JSON is the `"value":` Name. This accepts an Array of identifiers or tags as individual Resource Location strings. These string must contain the namespace and an item identifier in resource location format, matching either a single object of that tag's type, or another Tag prefixed by the # symbol. Missing or mistyped targets may cause the file to be ignored, or for Minecraft to throw an error on world load. Use the Advanced Tooltip functionality (F3 + H) in Minecraft to turn on display of Resource Locations in item tooltips for help finding specific strings.

A typical tag file to add cobblestone and every type of vanilla anvil to a Tag would thus look like: 
 
```json
{
    "replace": false,
    "values": [
      "minecraft:cobblestone",
      "#minecraft:anvil"
    ]
}
```

**While this example works for both Blocks and Items, it's a rare exception. Block Tags and Item Tags are entirely different things, and while some Block Tags have Item Tag equivalents, many do not. Some mods will *only* use Item Tags or Block Tags; some items, even vanilla items, have different Resource Locations when in Item form than in Block form.**

### Block Tags

 **Block Tags** are loaded in the `tags/blocks` directory. MineColonies reads the following Block Tags:
 
| Namespace      | Block Tag             | Effect                                                                                                                                                         |
| -------------- | --------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `minecolonies` | `concrete`            | Blocks that a {% worker name="concretemixer" /%} can mine.                                                                                                     |
| `minecolonies` | `decoblocks`          | Blocks that are not replaced by builders during construction phases.                                                                                           |
| `minecolonies` | `indestructable`      | Blocks that can't be destroyed. Prevents these blocks from being overwritten by Survival Build Tools, and has special considerations for colonist pathfinding. |
| `minecolonies` | `orechanceblocks`     | Blocks that which will have a low chance of dropping extra ores when mined by a {% worker name="miner" /%}.                                                    |
| `minecolonies` | `pathblocks`          | Colonists walk faster on and preferentially follow roads made of these blocks.                                                                                 |
| `minecolonies` | `protectionexception` | Blocks that can be used, or alt-clicked, within a [colony's protection range](/wiki/systems/protection), even by neutral or enemy players.                     |
| `forge`        | `dirt`                | Blocks that can be used as farmland by {% worker name="farmer" /%}.                                                                                            |
| `minecraft`    | `beds`                | Blocks that can be used by colonists to rest, if included in a schematic.                                                                                      |
| `minecraft`    | `doors`               | Used for pathfinding.                                                                                                                                          |
| `minecraft`    | `leaves`              | Used to determine eligible trees for the {% worker name="forester" /%}.                                                                                        |
| `minecraft`    | `logs`                | Used to determine eligible trees for the {% worker name="forester" /%}.                                                                                        |
| `minecraft`    | `shroomlight`         | Used to determine eligible trees for the {% worker name="forester" /%}.                                                                                        |
| `minecraft`    | `wart_blocks`         | Used to determine eligible trees for the {% worker name="forester" /%}.                                                                                        |
 
### Item Tags

 Item Tags are loaded in the `tags\items` directory. MineColonies reads the following Item Tags:
 
| Namespace      | tags\items                   | Effect                                                                                                                                                                                     |
| -------------- | ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `minecolonies` | `breakable_ore`              | (1.18+ only) Items with this tag are processed by the smelter using fortune                                                                                                                |
| `minecolonies` | `compostables`               | Items that can be placed into a Composter, and give moderate compost.                                                                                                                      |
| `minecolonies` | `compostables_poor`          | Items that can be placed into a Composter, and give little compost.                                                                                                                        |
| `minecolonies` | `compostables_rich`          | Items that can be placed into a Composter, and give a lot of compost.                                                                                                                      |
| `minecolonies` | `concrete_powder`            | Crafted and placed by a {% worker name="concretemixer" /%}                                                                                                                                 |
| `minecolonies` | `florist_flowers`            | Grown by the {% worker name="florist" /%}, if they have a valid block form, at building level 3 or higher.                                                                                 |
| `minecolonies` | `fungi`                      | Items that can be grown by a {% worker name="forester" /%} on Warped Nylium or Crimson Nylium.                                                                                             |
| `minecolonies` | `meshes`                     | Items that can be held as meshes by a {% worker name="sifter" /%}. This only allows the Sifter to use the tool, it does not add benefits to doing so. See CrafterRecipes for more details. |
| `minecolonies` | `raw_ore`                    | (1.18+ only) Items with this tag, if processable in a furnace, can be processed at the smeltery                                                                                            |
| `minecolonies` | `reducible_ingredient`       | Items that may be reduced in cost by one, to a minimum of one, when in a colonist recipe that originally required more than one of the item.                                               |
| `minecolonies` | `reducible_product_excluded` | Output items that can never have their crafter recipe efficiency improved. Most storage blocks or reversable recipes should be in this tag, to avoid possible infinite item loops.         |
| `forge`        | `crops_wheat`                | The {% worker name="baker" /%} can cook any recipe including an in this tag, if the output is a food item.                                                                                 |
| `forge`        | `glass`                      | All items with this tag, if produces in a furnace from an item tagged with #forge:glass, can be made at the Glassblower.                                                                   |
| `forge`        | `ores`                       | All items with this tag are treated as ores by the miner, if processable in a furnace, can be processed in the smeltery.                                                                   |
| `forge`        | `sand`                       | All items with this tag, if smeltable into an item tagged with #forge:glass, can be made at the Glassblower.                                                                               |
| `forge`        | `seeds`                      | Only items with this tag are valid to set in a Scarecrow, and are planted by a Farmer.                                                                                                     |
| `minecraft`    | `flowers`                    | Used by the {% worker name="beekeeper" /%} to breed bees.                                                                                                                                  |
| `minecraft`    | `fishes`                     | Used by the {% worker name="fisher" /%} to render fish on bandolier.                                                                                                                       |
| `minecraft`    | `leaves`                     | Items that Builders will place 'for free', without having in their inventory.                                                                                                              |
| `minecraft`    | `logs`                       | Recipes consisting of 75% or more this tag and #minecraft:planks can be taught to the Sawmill.                                                                                             |
| `minecraft`    | `planks`                     | Recipes consisting of 75% or more this tag and #minecraft:logs can be taught to the Sawmill. A stack is stored by the Miner.                                                               |
| `minecraft`    | `saplings`                   | Used by the Forester to grow trees.                                                                                                                                                        |
| `minecraft`    | `slabs`                      | A stack is stored by the Miner.                                                                                                                                                            |
| `minecraft`    | `small_flowers`              | Grown by a building level 1 or 2 {% worker name="florist" /%}, if they have a valid block form, and are in #minecolonies:florist_flowers.                                                  |
| `minecraft`    | `wool`                       | Used to by the Dyer to produce white wool, if not already White Wool.                                                                                                                      |

**Some Vanilla and Forge Item Tags are very expansive, or are used by some mods in ways that might surprise you. See [list of tags](https://minecraft.wiki/w/Tag#List_of_tags) for Minecraft behaviors.**

### Crafter Item Tags

Additionally, some **Item Tags** are used to control what recipes can be taught to a worker by a player in-game. Each worker has a different set of tags. For a full list, see [here](https://github.com/ldtteam/minecolonies/tree/version/main/src/datagen/generated/minecolonies/data/minecolonies/tags/items).
 
| Item Tag                | Effect                                                                                                                                            |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| `x_product_excluded`    | Items that cannot be made by this worker. Overrides all other crafter Tags.                                                                       |
| `x_product`             | Items that can be made by this worker.                                                                                                            |
| `x_ingredient_excluded` | Items that cannot be used to craft by this worker, unless the recipe product is in `x_product`.                                                   |
| `x_ingredient`          | Items that can be used to craft by this worker, unless the ingredient is in `x_ingredient_excluded`, or the recipe output is in `x_product.json`. |

A few colonists have other hard-coded rules that are not dependent on tags. The {% worker name="blacksmith" /%} can make all tools, swords, armor, hoes, and shields. {% worker name="cook" /%} will always accept recipes for items that have valid foods as results.