# fullmash add

Add a mod to the modpack.

```
fullmash add [--[no-]required] [-s=<side>] -src=<source> (url | id |
                    slug | search)
Add a mod to the modpack.
      --[no-]required   Whether the mod is required for the modpack.
                          (default: true)
  -s, --side=<side>     The side the mod will be used for. (default:
                          both)
                        Possible values: client, server, both
      -src, --source=<source>

Search for a mod by URL, ID, slug or query.
      url               The URL to use to find the mod.
      id                The project id to use to find the mod.
      slug              The slug to use to find the mod.
      search            The search query to use to find the mod.
```