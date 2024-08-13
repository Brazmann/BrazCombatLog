# BrazCombatLog
This is a simple Paper Spigot plugin I made in 3 hours to learn how making them works. Turns out it's easier than full modding!

When active, hitting another player will result in the two being placed into combat with each other. In this state, neither player can disconnect from the server without dying and dispersing their loot on the ground for the other to claim.
It also prints a colorful message for when a player loses a combat, wins one, or flees far enough for it to expire.

This plugin is very untested, so I wouldn't recommend running it on an actual server unless you're okay with possible item duplication or other issues.

# Config
After running your server once with the plugin installed, a folder named "BrazCombatPlugin" should appear in your plugins folder. Inside, the config.yml file resides.

```yaml 
distance-to-expire-combat: 8
ticks-to-expire-combat: 100
```
`distance-to-expire-combat` is the value that determines the minimum distance two players locked in combat have to be from each other for expiry to be possible.

`ticks-to-expire-combat` is, in turn, how long (in ticks) it will take for combat to expire once the players are out of range. If they fall back in range, the counter will reset, and if it falls to zero then the combat will expire and both participants can disconnect safely.
