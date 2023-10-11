package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.RegisterVolumeCategoryEvent;

public class SkriptRegisterVolumeCategoryEvent extends SkriptVolumeCategoryEvent<RegisterVolumeCategoryEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptRegisterVolumeCategoryEvent(RegisterVolumeCategoryEvent event) {
		super(event, event.getVolumeCategory());
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
