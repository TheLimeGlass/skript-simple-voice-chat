package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.StaticSoundPacketEvent;

public class SkriptStaticSoundPacketEvent extends VoiceChatConnectionEvent<StaticSoundPacketEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptStaticSoundPacketEvent(StaticSoundPacketEvent event) {
		super(event.getSenderConnection(), event);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
