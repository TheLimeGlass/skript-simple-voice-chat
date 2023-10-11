package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.VoiceHostEvent;

public class SkriptVoiceHostEvent extends VoiceChatEvent<VoiceHostEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptVoiceHostEvent(VoiceHostEvent event) {
		super(event);
	}

	public String getVoiceHost() {
		return event.getVoiceHost();
	}

	public void setVoiceHost(String voicehost) {
		event.setVoiceHost(voicehost);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
