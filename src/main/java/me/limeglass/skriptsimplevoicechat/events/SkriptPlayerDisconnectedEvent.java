package me.limeglass.skriptsimplevoicechat.events;

import java.util.UUID;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.PlayerDisconnectedEvent;

public class SkriptPlayerDisconnectedEvent extends VoiceChatEvent<PlayerDisconnectedEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptPlayerDisconnectedEvent(PlayerDisconnectedEvent event) {
		super(event);
	}

	public UUID getPlayerUUID() {
		return event.getPlayerUuid();
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
