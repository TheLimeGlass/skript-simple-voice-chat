package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.PlayerStateChangedEvent;

public class SkriptPlayerStateChangedEvent extends VoiceChatConnectionEvent<PlayerStateChangedEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptPlayerStateChangedEvent(PlayerStateChangedEvent event) {
		super(event.getConnection(), event);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
