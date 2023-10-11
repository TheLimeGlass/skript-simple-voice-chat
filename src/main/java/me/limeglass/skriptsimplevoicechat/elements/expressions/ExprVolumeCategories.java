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
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.VolumeCategory;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;

@Name("Voice Chat Volume Categories")
@Description("Returns the voice chat volume categories.")
@Since("1.0.0")
public class ExprVolumeCategories extends SimpleExpression<VolumeCategory> {

	static {
		Skript.registerExpression(ExprVolumeCategories.class, VolumeCategory.class, ExpressionType.SIMPLE, "[(all|the|all [of] the)] [voice] [chat] volume categories");
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	@Nullable
	protected VolumeCategory[] get(Event event) {
		return SkriptSimpleVoiceChat.getVoiceChatAPI().getVolumeCategories().toArray(new VolumeCategory[0]);
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case REMOVE_ALL:
			case REMOVE:
				return CollectionUtils.array(VolumeCategory[].class);
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
				API.getVolumeCategories().forEach(category -> API.unregisterVolumeCategory(category));
				break;
			case REMOVE:
			case REMOVE_ALL:
				for (VolumeCategory category : (VolumeCategory[]) delta)
					API.unregisterVolumeCategory(category);
				break;
			case RESET:
			case SET:
				for (VolumeCategory category : (VolumeCategory[]) delta)
					API.unregisterVolumeCategory(category);
				// Fall down
			case ADD:
				for (VolumeCategory category : (VolumeCategory[]) delta)
					API.registerVolumeCategory(category);
			default:
				break;
		}
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends VolumeCategory> getReturnType() {
		return VolumeCategory.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "voice chat volume categories";
	}

}
