package me.limeglass.skriptsimplevoicechat.elements.group;

import org.jetbrains.annotations.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.maxhenkel.voicechat.api.Group;

@Name("Voice Connection Group Name/ID")
@Description("The name or UUID of a <a href='./classes.html#voicechatconnectiongroup'>voice chat connection group</a> from <a href='./classes.html#voicechatconnection'>voice chat connections</a>. Must create a new group to change value.")
@Examples({
	"on microphone use:",
		"\tname of the group of the event-voice chat connection is not \"global\"",
		"\tcancel the event"
})
@Since("1.0.0")
public class ExprGroupNameOrID extends SimplePropertyExpression<Group, String> {

	static {
		registerDefault(ExprGroupNameOrID.class, String.class, "[the] [group] (:name|[uu]id)", "voicechatgroups");
	}

	private boolean name;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.name = parseResult.hasTag("name");
		setExpr((Expression<Group>) exprs[0]);
		return true;
	}

	@Override
	@Nullable
	public String convert(Group group) {
		if (name)
			return group.getName();
		return group.getId().toString();
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	protected String getPropertyName() {
		return name ? "group name" : "group id";
	}

}
