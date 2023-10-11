package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.PlayerConnectedEvent;

public class SkriptPlayerConnectedEvent extends VoiceChatConnectionEvent<PlayerConnectedEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptPlayerConnectedEvent(PlayerConnectedEvent event) {
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
