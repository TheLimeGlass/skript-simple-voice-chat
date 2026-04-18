package me.limeglass.skriptsimplevoicechat.elements;

import org.bukkit.entity.Player;
import org.skriptlang.skript.lang.converter.Converters;

import de.maxhenkel.voicechat.api.VoicechatConnection;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

public class DefaultConverters {

	static {
		Converters.registerConverter(VoicechatConnection.class, Player.class, connection -> {
            if (connection == null)
                return null;
            return (Player) connection.getPlayer().getPlayer();
        });
		Converters.registerConverter(Player.class, VoicechatConnection.class, player -> {
            if (player == null)
                return null;
            return SkriptSimpleVoiceChat.getVoiceChatAPI().getConnectionOf(player.getUniqueId());
        });
	}

}
