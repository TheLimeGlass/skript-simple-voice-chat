package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

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
import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

@Name("Voice Chat Groups")
@Description("Returns the registered voice chat groups.")
@Since("1.0.0")
public class ExprGroups extends SimpleExpression<Group> {

	static {
		Skript.registerExpression(ExprGroups.class, Group.class, ExpressionType.SIMPLE, "[(all|the|all [of] the)] [voice] [chat] groups");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	@Nullable
	protected Group[] get(Event event) {
		return SkriptSimpleVoiceChat.getVoiceChatAPI().getGroups().toArray(new Group[0]);
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case REMOVE_ALL:
			case REMOVE:
				return CollectionUtils.array(Group[].class);
			case DELETE:
				return CollectionUtils.array();
			case RESET:
			case ADD:
			default:
				break;
		}
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		VoicechatServerApi API = SkriptSimpleVoiceChat.getVoiceChatAPI();
		switch (mode) {
			case DELETE:
				API.getGroups().forEach(group -> API.removeGroup(group.getId()));
				break;
			case REMOVE:
			case REMOVE_ALL:
				for (Group group : (Group[]) delta)
					API.removeGroup(group.getId());
				break;
			case RESET:
			case SET:
			case ADD:
			default:
				break;
		}
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Group> getReturnType() {
		return Group.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat groups";
	}

}
