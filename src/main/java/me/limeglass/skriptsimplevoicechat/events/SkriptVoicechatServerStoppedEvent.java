package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.VoicechatServerStoppedEvent;

public class SkriptVoicechatServerStoppedEvent extends VoiceChatEvent<VoicechatServerStoppedEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptVoicechatServerStoppedEvent(VoicechatServerStoppedEvent event) {
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
