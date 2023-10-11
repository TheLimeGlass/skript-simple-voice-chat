package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.LeaveGroupEvent;

public class SkriptLeaveGroupEvent extends SkriptGroupEvent<LeaveGroupEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptLeaveGroupEvent(LeaveGroupEvent event) {
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
