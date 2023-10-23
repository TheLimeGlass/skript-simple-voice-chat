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
import de.maxhenkel.voicechat.api.Group;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;
import me.limeglass.skriptsimplevoicechat.elements.Types.GroupType;

@Name("Voice Chat New Group")
@Description("Creates a new group.")
@Examples("set {_group} to a new normal voice chat group named \"Example\" with password \"123\" and to persist")
@Since("1.0.0")
public class ExprNewGroup extends SimpleExpression<Group> {

	static {
		Skript.registerExpression(ExprNewGroup.class, Group.class, ExpressionType.COMBINED, "[a] [new] [%-voicechatgrouptype%] [voice chat] group named %string% [with password %-string%] [persists:and persists(persists|to persist)]");
	}

	@Nullable
	private Expression<GroupType> type;

	@Nullable
	private Expression<String> password;
	private Expression<String> name;
	private boolean persists;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		type = (Expression<GroupType>) exprs[0];
		name = (Expression<String>) exprs[1];
		password = (Expression<String>) exprs[2];
		persists = parseResult.hasTag("persists");
		return true;
	}

	@Override
	@Nullable
	protected Group[] get(Event event) {
		return CollectionUtils.array(SkriptSimpleVoiceChat.getVoiceChatAPI().groupBuilder()
				.setType(type == null ? GroupType.NORMAL.getType() : type.getOptionalSingle(event).orElse(GroupType.NORMAL).getType())
				.setPassword(password.getSingle(event))
				.setName(name.getSingle(event))
				.setPersistent(persists)
				.build()
		);
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Group> getReturnType() {
		return Group.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (type == null)
			return "a new normal voice chat group named " + name.toString(event, debug);
		return "a new " + type.toString(event, debug) + " voice chat group named " + name.toString(event, debug);
	}

}
