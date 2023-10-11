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
import me.limeglass.skriptsimplevoicechat.events.SkriptMicrophonePacketEvent;

@Name("Microphone Use Is Whispering")
@Description("Returns true if the incoming microphone use event is whispering.")
@Since("1.0.0")
public class ConMicrophonePacketEventIsWhispering extends Condition {

	static {
		Skript.registerCondition(ConMicrophonePacketEventIsWhispering.class, "[the (microphone|event|player)] is whispering");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(SkriptMicrophonePacketEvent.class)) {
			Skript.error("The 'is whispering' condition can only be used in a 'on microphone use' event!");
			return false;
		}
		return true;
	}

	@Override
	public boolean check(Event event) {
		if (!(event instanceof SkriptMicrophonePacketEvent))
			return false;
		return ((SkriptMicrophonePacketEvent) event).getEvent().getPacket().isWhispering();
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "microphone is whispering";
	}

}
