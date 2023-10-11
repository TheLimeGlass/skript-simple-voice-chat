# skript-simple-voice-chat

A Skript addon for interacting with the simple voice chat plugin on the Bukkit side.

Requires voicechat https://www.spigotmc.org/resources/simple-voice-chat.93738/

```
on microphone use:
    player is holding an end rod named "Microphone"
    send event-static voice packet to all players where [player input is not player]

on voice chat join group:
    loop all players:
        voice chat group of loop-player is event-voice chat group
        message "&6%player% has joined your voice group" to loop-player
```
