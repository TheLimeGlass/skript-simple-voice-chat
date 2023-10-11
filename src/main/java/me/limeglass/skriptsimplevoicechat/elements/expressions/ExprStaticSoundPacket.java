package me.limeglass.skriptsimplevoicechat.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import de.maxhenkel.voicechat.api.packets.StaticSoundPacket;

@Name("Voice Chat Static Sound Packet")
@Description("Returns the static sound packet involved in a microphone use event.")
@Since("1.0.0")
public class ExprStaticSoundPacket extends EventValueExpression<StaticSoundPacket> {

	static {
		Skript.registerExpression(ExprStaticSoundPacket.class, StaticSoundPacket.class, ExpressionType.SIMPLE, "[the] [voice] [chat] static sound packet");
	}

	public ExprStaticSoundPacket() {
		super(StaticSoundPacket.class);
	}

}
