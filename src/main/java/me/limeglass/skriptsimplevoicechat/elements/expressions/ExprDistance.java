package me.limeglass.skriptsimplevoicechat.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatVoiceDistanceEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Voice Distance")
@Description("This returns either the default distance that's set on the server or the modified distance if another subscriber changed it already.")
@Since("1.0.5")
public class ExprDistance extends SimpleExpression<Float> {

	static {
		Skript.registerExpression(ExprDistance.class, Float.class, ExpressionType.SIMPLE, "[the] voice [chat] distance ");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(SkriptVoicechatVoiceDistanceEvent.class)) {
			Skript.error("You can only get a 'voice distance event' in a voice chat event.");
			return false;
		}
		return true;
	}

	@Override
	@Nullable
    protected Float[] get(Event event) {
		return CollectionUtils.array(((SkriptVoicechatVoiceDistanceEvent) event).getEvent().getDistance());
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return switch (mode) {
			case SET, ADD, REMOVE -> CollectionUtils.array(Float.class);
			default -> null;
		};
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		SkriptVoicechatVoiceDistanceEvent voiceDistanceEvent = (SkriptVoicechatVoiceDistanceEvent) event;
		if (mode == ChangeMode.SET) {
			voiceDistanceEvent.getEvent().setDistance((Float) delta[0]);
		} else if (mode == ChangeMode.ADD) {
			voiceDistanceEvent.getEvent().setDistance(voiceDistanceEvent.getEvent().getDistance() + (Float) delta[0]);
		} else if (mode == ChangeMode.REMOVE) {
			voiceDistanceEvent.getEvent().setDistance(voiceDistanceEvent.getEvent().getDistance() - (Float) delta[0]);
		}
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Float> getReturnType() {
		return Float.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice distance";
	}

}
