package me.limeglass.skriptsimplevoicechat.elements.events;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerStateChangedEvent;

public class EvtDisabledVoiceChat extends SkriptEvent {

	static {
		Skript.registerEvent("disable voice chat", EvtDisabledVoiceChat.class, SkriptPlayerStateChangedEvent.class, "(player disabl(ing|e) voice chat|voice chat disabl(ing|ed))")
				.description("called when a player disables their voice chat")
				.since("1.0.0");
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event event) {
		if (!(event instanceof SkriptPlayerStateChangedEvent))
			return false;
		return ((SkriptPlayerStateChangedEvent) event).getEvent().isDisabled();
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "player disabling voice chat";
	}

}
