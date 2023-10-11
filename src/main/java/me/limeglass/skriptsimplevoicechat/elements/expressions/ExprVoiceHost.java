package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoiceHostEvent;

@Name("Voice Chat Host")
@Description("Returns the voice host of the voice chat. Can also be set in a 'voice chat send host' event.")
@Examples({
	"on voice chat send host:",
		"\tvoice chat host is not \"localhost\"",
		"\tset the voice chat host to \"127.0.0.1\""
})
@Since("1.0.0")
public class ExprVoiceHost extends SimpleExpression<String> {

	static {
		Skript.registerExpression(ExprVoiceHost.class, String.class, ExpressionType.SIMPLE, "[the] voice [chat] [server] host");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(SkriptVoiceHostEvent.class)) {
			Skript.error("The 'voice chat host' expression can only be used in an 'on voice chat send host' event!");
			return false;
		}
		return true;
	}

	@Override
	@Nullable
	protected String[] get(Event event) {
		if (!(event instanceof SkriptVoiceHostEvent))
			return new String[0];
		return CollectionUtils.array(((SkriptVoiceHostEvent) event).getVoiceHost());
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		return mode == ChangeMode.SET ? CollectionUtils.array(String.class) : null;
	}
	
	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (!(event instanceof SkriptVoiceHostEvent))
			return;
		if (delta == null || delta.length < 1)
			return;
		((SkriptVoiceHostEvent) event).setVoiceHost((String) delta[0]);
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat host";
	}

}
