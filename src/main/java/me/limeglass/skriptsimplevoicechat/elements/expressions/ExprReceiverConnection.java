package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
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
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.events.PacketEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptStaticSoundPacketEvent;
import me.limeglass.skriptsimplevoicechat.events.VoiceChatConnectionEvent;

@Name("Receiving Voice Chat Connection")
@Description("The receiving connection of any voice chat packet.")
@Examples({
	"on microphone use:",
		"\tname of the receiving voice connection is \"LimeGlass\"",
		"\tcancel the event"
})
@Since("1.0.0")
public class ExprReceiverConnection extends SimpleExpression<VoicechatConnection> {

	static {
		Skript.registerExpression(ExprReceiverConnection.class, VoicechatConnection.class, ExpressionType.SIMPLE, "[the] ([voice chat] connection receiver|receiving [voice chat] connection)");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(VoiceChatConnectionEvent.class, SkriptStaticSoundPacketEvent.class)) {
			Skript.error("You can only get a 'voice chat receiver' or 'static sound event' in a voice chat event.");
			return false;
		}
		return true;
	}

	@Override
	@Nullable
	@SuppressWarnings("rawtypes")
	protected VoicechatConnection[] get(Event event) {
		if (event instanceof VoiceChatConnectionEvent)
			return CollectionUtils.array(((PacketEvent) ((VoiceChatConnectionEvent) event).getEvent()).getReceiverConnection());
		if (event instanceof SkriptStaticSoundPacketEvent)
			return CollectionUtils.array(((PacketEvent) ((SkriptStaticSoundPacketEvent) event).getEvent()).getReceiverConnection());
		return new VoicechatConnection[0];
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends VoicechatConnection> getReturnType() {
		return VoicechatConnection.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat connection receiver";
	}

}
