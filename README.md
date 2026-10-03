# Squaremap Banner for Fabric 26.2

Fork of [FaeWulf/squaremap_banner](https://github.com/FaeWulf/squaremap_banner), licensed under CC0-1.0.

Server-side banner markers for the Squaremap web map. Clients do not need this mod.

## Requirements

- Minecraft Java Edition **26.2**, Java **25**.
- Fabric Loader **0.19.3** or newer.
- Fabric API **0.161.0+26.2** or newer for Minecraft 26.2.
- [Squaremap **1.3.15** for Fabric 26.2](https://modrinth.com/plugin/squaremap/version/QoKeDHFP).

The original 1.21.10 jar cannot run on 26.2. This fork uses the unobfuscated 26.2 APIs and the corresponding Fabric build tools.

## Install and use

Place `squaremap-banner-1.1.0+26.2.jar` in the server's `mods` directory alongside Fabric API and Squaremap, then restart the server. Replace any older Squaremap Banner jar.

1. Name a banner with an anvil and place it in a world enabled in Squaremap.
2. Hold a filled map, sneak, and right-click the banner to register or update its marker.
3. On the Squaremap website, enable the **Banner** layer and hover over the marker to see its name. Characters such as `<`, `&`, and quotes are displayed literally.
4. Sneak and left-click with a filled map to remove its marker, or break the banner.

Players need `squaremap.banner.use`. Without a permission provider, operator permission level 1 or higher is required. Fabric Permissions API is bundled; a permission manager is optional.

## Configuration and existing data

`config/squaremap_banner.json` contains only `blacklist`, a list of blocked whole words in banner names.

The area notification system has been removed, including movement mixins, player tracking, radius settings, and enter-area messages. Old `banner_radius` and `announce_when_near_banner` entries are ignored and removed when the configuration is saved.

Existing markers in `squaremap/banner.json` retain their names, colors, positions, and IDs. Hover tooltips are recreated when data is restored.

## Build and validation

With Java 25 installed, run `./gradlew build` (Windows: `gradlew.bat build`). The jar is written to `build/libs/`. Tests cover tooltip text escaping, persistence of marker names/colors/coordinates/IDs, legacy data, and removal of area configuration settings.

For an in-game check, register a named banner, hover over its web marker, restart the server, and verify the marker and tooltip remain. Check removal both by map interaction and by breaking the banner, and confirm approaching a banner does not produce area messages.
