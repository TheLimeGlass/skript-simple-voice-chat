package me.limeglass.skriptsimplevoicechat.elements;

import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;
import org.skriptlang.skript.lang.converter.Converter;
import org.skriptlang.skript.lang.converter.Converters;

import de.maxhenkel.voicechat.api.VoicechatConnection;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

public class DefaultConverters {

	static {
		Converters.registerConverter(VoicechatConnection.class, Player.class, new Converter<VoicechatConnection, Player>() {
			@Override
			@Nullable
			public Player convert(VoicechatConnection connection) {
				if (connection == null)
					return null;
				return (Player) connection.getPlayer().getPlayer();
			}
		});
		Converters.registerConverter(Player.class, VoicechatConnection.class, new Converter<Player, VoicechatConnection>() {
			@Override
			@Nullable
			public VoicechatConnection convert(Player player) {
				if (player == null)
					return null;
				return SkriptSimpleVoiceChat.getVoiceChatAPI().getConnectionOf(player.getUniqueId());
			}
		});
	}

}
