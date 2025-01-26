# &lt;mod&gt;.json

## Properties

| Property  | Type                               | Required | Description                   |
|-----------|------------------------------------|----------|-------------------------------|
| name      | string                             | Yes      | The name of the mod.          |
| source    | one of [mod sources](#mod-sources) | Yes      | The source of the mod.        |
| projectID | string                             | Yes      | The project ID of the mod.    |
| fileID    | string                             | No       | The file ID of the mod.       |
| side      | one of [mod sides](#mod-sides)     | No       | THe side the mod is used for. |

### Mod Sources

| Name       | Value        | Note |
|------------|--------------|------|
| CURSEFORGE | "curseforge" |      |
| MODRINTH   | "modrinth"   |      |

### Mod Sides

| Name   | Value    | Note                                                 |
|--------|----------|------------------------------------------------------|
| CLIENT | "client" | This mod is only required for the client.            |
| SERVER | "server" | This mod is only required for the server.            |
| BOTH   | "both"   | This mod is required for both the client and server. |
