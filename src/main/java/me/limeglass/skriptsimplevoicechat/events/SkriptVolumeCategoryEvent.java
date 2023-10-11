package me.limeglass.skriptsimplevoicechat.events;

import de.maxhenkel.voicechat.api.VolumeCategory;
import de.maxhenkel.voicechat.api.events.VolumeCategoryEvent;

public abstract class SkriptVolumeCategoryEvent<E extends VolumeCategoryEvent> extends VoiceChatEvent<E> {

	public SkriptVolumeCategoryEvent(E event, VolumeCategory category) {
		super(event);
	}

	public VolumeCategory getVolumeCategory() {
		return event.getVolumeCategory();
	}

}
