package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.JoinGroupEvent;

public class SkriptJoinGroupEvent extends SkriptGroupEvent<JoinGroupEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptJoinGroupEvent(JoinGroupEvent event) {
		super(event.getConnection(), event, event.getGroup());
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
