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
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

@Name("Voice Chat Broadcast Radius")
@Description("Returns the radius the audio packets are broadcasted at.")
@Since("1.0.0")
public class ExprBroadcastRadius extends SimpleExpression<Double> {

	static {
		Skript.registerExpression(ExprBroadcastRadius.class, Double.class, ExpressionType.SIMPLE, "[the] [voice] [chat] broadcast radius");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	@Nullable
	protected Double[] get(Event event) {
		return CollectionUtils.array(SkriptSimpleVoiceChat.getVoiceChatAPI().getBroadcastRange());
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat broadcast radius";
	}

}
