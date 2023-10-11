package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.VoicechatSocket;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartingEvent;

public class SkriptVoicechatServerStartingEvent extends VoiceChatEvent<VoicechatServerStartingEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptVoicechatServerStartingEvent(VoicechatServerStartingEvent event) {
		super(event);
	}

	public VoicechatSocket getSocketImplementation() {
		return event.getSocketImplementation();
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
