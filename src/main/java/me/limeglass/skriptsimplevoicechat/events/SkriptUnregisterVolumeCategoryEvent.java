package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.HandlerList;

import de.maxhenkel.voicechat.api.events.UnregisterVolumeCategoryEvent;

public class SkriptUnregisterVolumeCategoryEvent extends SkriptVolumeCategoryEvent<UnregisterVolumeCategoryEvent> {

	private static final HandlerList handlers = new HandlerList();

	public SkriptUnregisterVolumeCategoryEvent(UnregisterVolumeCategoryEvent event) {
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
