package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;

public class SkriptVoicechatServerStartedEvent extends VoiceChatEvent<VoicechatServerStartedEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptVoicechatServerStartedEvent(VoicechatServerStartedEvent event) {
		super(event);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
