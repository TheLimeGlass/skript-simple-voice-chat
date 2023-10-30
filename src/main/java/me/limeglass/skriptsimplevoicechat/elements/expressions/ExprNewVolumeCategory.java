package me.limeglass.skriptsimplevoicechat.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

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
import de.maxhenkel.voicechat.api.VolumeCategory;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

@Name("Voice Chat New Volume Category")
@Description("Creates a new volume category. This allows players to turn the volume up or down in the adjust volumes menu.")
@Since("1.0.1")
public class ExprNewVolumeCategory extends SimpleExpression<VolumeCategory> {

	static {
		Skript.registerExpression(ExprNewVolumeCategory.class, VolumeCategory.class, ExpressionType.COMBINED, "[a] [new] [voice chat] volume category with id %string% named %string% [with description %-string%]");
	}

	@Nullable
	private Expression<String> description;
	private Expression<String> name;
	private Expression<String> id;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		id = (Expression<String>) exprs[0];
		name = (Expression<String>) exprs[1];
		description = (Expression<String>) exprs[2];
		return true;
	}

	@Override
	@Nullable
	protected VolumeCategory[] get(Event event) {
		VolumeCategory category = SkriptSimpleVoiceChat.getVoiceChatAPI().volumeCategoryBuilder()
				.setDescription(description.getSingle(event))
				.setName(name.getSingle(event))
				.setId(id.getSingle(event))
				.build();
		// Because the builder doesn't automatically register like GroupBuilder does.
		SkriptSimpleVoiceChat.getVoiceChatAPI().registerVolumeCategory(category);
		return CollectionUtils.array(category);
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends VolumeCategory> getReturnType() {
		return VolumeCategory.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "a new volume category with id " + id.toString(event, debug) + " named "  + name.toString(event, debug) +
				(description == null ? "" : " with description " + description.toString(event, debug));
	}

}
