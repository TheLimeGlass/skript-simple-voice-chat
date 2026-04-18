package me.limeglass.skriptsimplevoicechat.events;

import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;
import org.bukkit.event.HandlerList;

public class SkriptVoicechatVoiceDistanceEvent extends VoiceChatConnectionEvent<VoiceDistanceEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptVoicechatVoiceDistanceEvent(VoiceDistanceEvent event) {
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
