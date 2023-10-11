package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.RemoveGroupEvent;

public class SkriptRemoveGroupEvent extends SkriptGroupEvent<RemoveGroupEvent> {

	private static final HandlerList handlers = new HandlerList();

	@SuppressWarnings("deprecation")
	public SkriptRemoveGroupEvent(RemoveGroupEvent event) {
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
