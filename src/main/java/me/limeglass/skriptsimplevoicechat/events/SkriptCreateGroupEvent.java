package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.CreateGroupEvent;

public class SkriptCreateGroupEvent extends SkriptGroupEvent<CreateGroupEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptCreateGroupEvent(CreateGroupEvent event) {
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
