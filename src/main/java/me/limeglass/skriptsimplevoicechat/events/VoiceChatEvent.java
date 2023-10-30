package me.limeglass.skriptsimplevoicechat.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Class wrapper needed to represent a simple-voice-chat event on Bukkit.
 */
public abstract class VoiceChatEvent<E extends de.maxhenkel.voicechat.api.events.Event> extends Event implements Cancellable {

	private boolean cancelled;
	protected final E event;

	public VoiceChatEvent(E event) {
		super(true);
		this.event = event;
	}

	public E getEvent() {
		return event;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
