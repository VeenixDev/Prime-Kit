# Prime Kit

Prime Kit is a Minecraft 1.8 plugin which allows you to simply add kits to your Minecraft world!  

- [Installation](##installation)
- [Configuration](##configuration)
  - [Messages](###messages)
  - [File System](###file-system)

---

## Installation

Download the latest release from the plugin.  
After you downloaded the plugin put it inside you `~/server/plugins` directory and start your Server.  
Next you can go into the `~/server/plugins` directory and start configuring the plugin.

---

## Configuration

The Configuration of the plugin is very easy.  
Most things you don't even have to configure because its done by the plugin already.

### Messages

In the `config.yml` file  you have many messages(or parts of messages).  
All Messages and options except in the `settings`-section support color codes. (example &b)

- `message`-section:  
  - kitSelect: {{Kit}} will be replaced with the selected kit
  - death: {{Enemy}} will be replaced with the player-name of the player who killed you
  - kill: {{Victim}} will be replaced with the player-name of the player you killed
  - useKit: {{Command}} will be replaced with the command syntax from the kit command
  - kitCreate: {{Kit}} will be replaced with the name of the created kit
  - kitLoad: {{Kit}} will be replaced with the name of the  loaded kit
  - kitDelete: {{Kit}} will be replaced with the name of the deleted kit
  
- `error`-section:
  - kitCreate: {{Kit}} will be replaced with the name of the created kit
  - kitLoad: {{Kit}} will be replaced with the name of the  loaded kit
  - kitDelete: {{Kit}} will be replaced with the name of the deleted kit

- `lore`-section:
  - kitSelector: Nothing will be replaced
  
- `selector`:
  - title: Nothing will be replaced
  
- `settings`:
  - deathBelow: This setting allows you to set a height where the player instantly dies if he goes beneath it

### File System

In the following section I will explain what which file does.  

- `config.yml`: General settings for the plugin and messages send to players

- `data/`: Is a directory in which you usually don't need to change anything.  Inside this directory the plugin handles everything automatically
- `data/kits.conf`: Saves all items used in the kits
- `data/selectedKits.conf`: Saves the last selected kit from every player who selected at least a kit once
