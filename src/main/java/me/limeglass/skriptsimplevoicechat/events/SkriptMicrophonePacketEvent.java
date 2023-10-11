package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;

public class SkriptMicrophonePacketEvent extends VoiceChatConnectionEvent<MicrophonePacketEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptMicrophonePacketEvent(MicrophonePacketEvent event) {
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
