# SurvivalFarming

SurvivalFarming is a lightweight farming quality-of-life plugin for Spigot and Paper servers.

Harvest fully grown crops by right-clicking them with any hoe. The crop is automatically replanted, while the remaining harvest drops naturally.

## Features

* Harvest fully grown crops with a simple right-click
* Automatically replants harvested crops
* Supports all types of hoes
* Supports the Fortune enchantment
* Unripe crops are not affected
* Optional permission support for rank-based access
* No commands, configuration, or external dependencies required
* Anonymous usage statistics through bStats
* Lightweight and easy to use

## Supported Crops

* Wheat
* Carrots
* Potatoes
* Beetroot
* Nether Wart
* Cocoa Beans

## Usage

1. Hold any hoe in your main hand.
2. Right-click a fully grown supported crop.
3. The crop is harvested and automatically replanted.

## Permission

`survivalfarming.use` allows players to harvest and replant mature crops. It is granted to everyone by default, so the plugin continues to work without any permission setup.

To restrict the feature to specific ranks with LuckPerms, deny the permission for the default group and grant it to the desired group:

```text
/lp group default permission set survivalfarming.use false
/lp group premium permission set survivalfarming.use true
```

## Requirements

* Minecraft 1.21+
* Spigot or Paper
* Java 21

## Installation

1. Download the SurvivalFarming JAR file.
2. Place it inside your server's `plugins` folder.
3. Restart the server.
4. Start farming!

## Metrics

SurvivalFarming uses bStats to collect anonymous usage statistics.
