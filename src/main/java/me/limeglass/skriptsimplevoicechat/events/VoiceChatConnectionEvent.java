package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.entity.Player;

import de.maxhenkel.voicechat.api.VoicechatConnection;

public abstract class VoiceChatConnectionEvent<E extends de.maxhenkel.voicechat.api.events.Event> extends VoiceChatEvent<E> {

	private final VoicechatConnection connection;
	private final Player player;

	public VoiceChatConnectionEvent(VoicechatConnection connection, E event) {
		super(event);
		this.player = (Player) connection.getPlayer().getPlayer();
		this.connection = connection;
	}

	public VoicechatConnection getConnection() {
		return connection;
	}

	public Player getPlayer() {
		return player;
	}

}
