package me.limeglass.skriptsimplevoicechat.elements.effects;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.packets.StaticSoundPacket;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

@Name("Send Static Sound Packet")
@Description("Send static sound packets to voice connections.")
@Examples({
	"on microphone use:",
		"\tsend event-static sound packet to all players where [player input is not player]"
})
@Since("1.0.0")
public class EffSendStaticPacket extends Effect {

	static {
		Skript.registerEffect(EffSendStaticPacket.class, "send [static sound packet] %staticsoundpacket% to %voicechatconnections%");
	}

	private Expression<VoicechatConnection> connections;
	private Expression<StaticSoundPacket> packet;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		packet = (Expression<StaticSoundPacket>) exprs[0];
		connections = (Expression<VoicechatConnection>) exprs[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		StaticSoundPacket packet = this.packet.getSingle(event);
		if (packet == null)
			return;
		VoicechatServerApi API = SkriptSimpleVoiceChat.getVoiceChatAPI();
		for (VoicechatConnection connection : connections.getArray(event))
			API.sendStaticSoundPacketTo(connection, packet);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "send static sound packet " + packet.toString(event, debug) + " to " + connections.toString(event, debug);
	}

}
