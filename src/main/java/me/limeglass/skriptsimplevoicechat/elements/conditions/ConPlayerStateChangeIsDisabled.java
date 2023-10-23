package me.limeglass.skriptsimplevoicechat.elements.conditions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerStateChangedEvent;

@Name("Player State Change Is Disabled")
@Description("Returns true if the player disabled their voice chat in an 'player change state event'")
@Since("1.0.0")
public class ConPlayerStateChangeIsDisabled extends Condition {

	static {
		Skript.registerCondition(ConPlayerStateChangeIsDisabled.class, "[[the] player] disabled their voice chat");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(SkriptPlayerStateChangedEvent.class)) {
			Skript.error("The 'disabled their voice chat' condition can only be used in a 'on voice chat' event!");
			return false;
		}
		return true;
	}

	@Override
	public boolean check(Event event) {
		if (!(event instanceof SkriptPlayerStateChangedEvent))
			return false;
		return ((SkriptPlayerStateChangedEvent) event).getEvent().isDisabled();
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "disabled their voice chat";
	}

}
