# modpack.json

## Properties

| Property   | Type                             | Required | Description                                                     |
|------------|----------------------------------|----------|-----------------------------------------------------------------|
| name       | string                           | Yes      | The name of the modpack.                                        |
| modloaders | array of [modloader](#modloader) | Yes      | The modloaders used for the modpack.                            |
| minecraft  | [game](#game) object             | Yes      | The version and other information of minecraft for the modpack. |
| version    | string                           | No       | The version of the modpack.                                     |
| author     | string                           | No       | The author of the modpack.                                      |

### Modloader

| Property | Type                             | Required | Description                  |
|----------|----------------------------------|----------|------------------------------|
| name     | one of [modloaders](#modloaders) | Yes      | The name of the modloader    |
| version  | string                           | Yes      | The version of the modloader |

#### Modloaders

| Name     | Value      | Note                                                                                                                   |
|----------|------------|------------------------------------------------------------------------------------------------------------------------|
| NEOFORGE | "neoforge" | See [NeoForged Project Listing](https://projects.neoforged.net/neoforged/neoforge) for possible versions.              |
| FABRIC   | "fabric"   | See [Develop](https://fabricmc.net/develop/) for latest versions per minecraft version.                                |
| FORGE    | "forge"    | See [Downloads for Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge/) for possible versions. |

### Game

| Property | Type   | Required | Description              |
|----------|--------|----------|--------------------------|
| version  | string | Yes      | The version of the game. |
