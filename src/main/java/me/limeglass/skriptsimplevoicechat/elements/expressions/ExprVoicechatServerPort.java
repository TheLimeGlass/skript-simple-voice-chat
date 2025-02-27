package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoiceHostEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStartingEvent;

@Name("Voice Chat Server Port")
@Description("Returns the voice chat server port of the voice chat.")
@Since("1.0.0")
public class ExprVoicechatServerPort extends SimpleExpression<Integer> {

	static {
		Skript.registerExpression(ExprVoicechatServerPort.class, Integer.class, ExpressionType.SIMPLE, "[the] voice [chat] server [local] port");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(SkriptVoiceHostEvent.class)) {
			Skript.error("The 'voice chat server port' expression can only be used in an 'on voice chat server starting' event!");
			return false;
		}
		return true;
	}

	@Override
	@Nullable
	protected Integer[] get(Event event) {
		if (!(event instanceof SkriptVoiceHostEvent))
			return new Integer[0];
		return CollectionUtils.array(((SkriptVoicechatServerStartingEvent) event).getSocketImplementation().getLocalPort());
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat server port";
	}

}
