package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;

@Name("Voice Chat Connection Group")
@Description("Returns the group that a voice chat connection is within.")
@Examples({
	"on microphone packet:",
		"\tname of group of event-voice chat connection is not \"global\"",
		"\tcancel event"
})
@Since("1.0.0")
public class ExprVoiceConnectionGroup extends SimplePropertyExpression<VoicechatConnection, Group> {

	static {
		registerDefault(ExprVoiceConnectionGroup.class, Group.class, "[the] [voice [chat]] group", "voicechatconnections");
	}

	@Override
	@Nullable
	public Group convert(VoicechatConnection connection) {
		return connection.getGroup();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		return mode == ChangeMode.SET || mode == ChangeMode.DELETE ? CollectionUtils.array(Group.class) : null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		Group group = delta == null ? null : (Group) delta[0];
		for (VoicechatConnection connection : getExpr().getArray(event))
			connection.setGroup(group);
	}

	@Override
	public Class<? extends Group> getReturnType() {
		return Group.class;
	}

	@Override
	protected String getPropertyName() {
		return "voice group";
	}

}
