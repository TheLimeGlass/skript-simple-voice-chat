package me.limeglass.skriptsimplevoicechat.elements;

import java.util.UUID;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;
import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VolumeCategory;
import de.maxhenkel.voicechat.api.packets.StaticSoundPacket;
import me.limeglass.skriptsimplevoicechat.SkriptSimpleVoiceChat;
import me.limeglass.skriptsimplevoicechat.events.SkriptRemoveGroupEvent;

public class Types {

	static {
		Classes.registerClass(new ClassInfo<>(VoicechatConnection.class, "voicechatconnection")
				.user("voice ?(chat ?)?connections?")
				.name("Voicechat Connection")
				.defaultExpression(new EventValueExpression<VoicechatConnection>(VoicechatConnection.class) {
					@Override
					public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
						if (getParser().isCurrentEvent(SkriptRemoveGroupEvent.class))
							Skript.warning(
									"A group may be removed from the server. " +
									"So getting the group creator connection of a group is deprecated under the voice chat API. " + 
									"The 'event-voice connection' is not set if the server removed the group."
							);
						return super.init(exprs, matchedPattern, isDelayed, parser);
					}
				})
				.parser(new Parser<VoicechatConnection>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(VoicechatConnection connection, int flags) {
						return connection.getPlayer().toString();
					}

					@Override
					public String toVariableNameString(VoicechatConnection connection) {
						return toString(connection, 0);
					}

				}));
		Classes.registerClass(new ClassInfo<>(Group.class, "voicechatgroup")
				.user("(voice ?chat ?connection ?)?groups?")
				.name("Voice Chat Connection Group")
				.description(
						"Voice chat connections can be involved in a group. " + 
						"This type collects the name, id, password and if it persists. " +
						"The parse must be the UUID of the group."
				)
				.defaultExpression(new EventValueExpression<Group>(Group.class))
				.parser(new Parser<Group>() {

					@Nullable
					public Group parse(String input, ParseContext context) {
						try {
							return SkriptSimpleVoiceChat.getVoiceChatAPI().getGroup(UUID.fromString(input));
						} catch (IllegalArgumentException exception) {
							return null;
						}
					}

					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}

					@Override
					public String toString(Group group, int flags) {
						return group.getName();
					}

					@Override
					public String toVariableNameString(Group group) {
						return group.getId() + ":" + group.getName();
					}

				}));

		Classes.registerClass(new ClassInfo<>(VolumeCategory.class, "voicechatvolumecategory")
				.user("(voice ?chat ?)?volume categor(ies|y)")
				.name("Voice Chat Volume Category")
				.description("Voice chat volume category.")
				.defaultExpression(new EventValueExpression<VolumeCategory>(VolumeCategory.class))
				.parser(new Parser<VolumeCategory>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(VolumeCategory category, int flags) {
						return category.getName();
					}

					@Override
					public String toVariableNameString(VolumeCategory category) {
						return category.getId() + ":" + category.getName();
					}

				}));

		Classes.registerClass(new ClassInfo<>(StaticSoundPacket.class, "staticsoundpacket")
				.user("(voice ?chat ?)?static sound packets?")
				.name("Voice Chat Static Sound Packets")
				.description("A static sound packet from the microphone use event.")
				.defaultExpression(new EventValueExpression<StaticSoundPacket>(StaticSoundPacket.class))
				.parser(new Parser<StaticSoundPacket>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(StaticSoundPacket packet, int flags) {
						return "static sound packet in category " + packet.getCategory() + " to " + packet.getSender();
					}

					@Override
					public String toVariableNameString(StaticSoundPacket packet) {
						return packet.getCategory() + ":" + packet.getSender();
					}

				}));

		Classes.registerClass(new EnumClassInfo<>(GroupType.class, "voicechatgrouptype", "voicechatgrouptype")
				.user("(voice ?chat ?)?group ?types?")
				.name("Voice Chat Group Type")
				.description("The types that a voice chat group can be.")
		);
	}

	public enum GroupType {
		ISOLATED(Group.Type.ISOLATED),
		NORMAL(Group.Type.NORMAL),
		OPEN(Group.Type.OPEN);

		private final Group.Type type;

		GroupType(Group.Type type) {
			this.type = type;
		}

		public Group.Type getType() {
			return type;
		}

	}

}
