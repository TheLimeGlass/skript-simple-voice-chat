package me.limeglass.skriptsimplevoicechat.elements;

import org.bukkit.entity.Player;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VolumeCategory;
import me.limeglass.skriptsimplevoicechat.events.SkriptCreateGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptJoinGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptLeaveGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptMicrophonePacketEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerConnectedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerDisconnectedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerStateChangedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptRegisterVolumeCategoryEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptRemoveGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptStaticSoundPacketEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptUnregisterVolumeCategoryEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoiceHostEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStartedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStartingEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStoppedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVolumeCategoryEvent;
import me.limeglass.skriptsimplevoicechat.events.VoiceChatConnectionEvent;

public class Events {

	static {
		EventValues.registerEventValue(VoiceChatConnectionEvent.class, VoicechatConnection.class, VoiceChatConnectionEvent::getConnection, EventValues.TIME_NOW);
		EventValues.registerEventValue(VoiceChatConnectionEvent.class, Player.class, event -> {
			return (Player) event.getConnection().getPlayer().getPlayer();
		}, EventValues.TIME_NOW);
		EventValues.registerEventValue(SkriptGroupEvent.class, Group.class, SkriptGroupEvent::getGroup, EventValues.TIME_NOW);

		// MicrophonePacketEvent
		Skript.registerEvent("microphone packet", SimpleEvent.class, SkriptMicrophonePacketEvent.class, "microphone (use|talk|packet)")
				.description(
					"This event is emitted when a microphone packet arrives at the server.",
					"WARNING: This event only runs async, so don't place sync syntaxes inside this event!"
				)
				.examples(
						"on microphone use:",
							"\tevent-voice chat connection is set",
							"\tplayer has permission \"voicechat_broadcast.broadcast\"",
							"\tcancel the event"
				)
				.since("1.0.0");

		// CreateGroupEvent
		Skript.registerEvent("create group", SimpleEvent.class, SkriptCreateGroupEvent.class, "[[simple] voice[ ]chat] (group create|create group)")
				.description("This event is called when a player creates a voice chat group.")
				.examples(
						"on group create:",
							"\tevent-group is named \"bad word\"",
							"\tcancel event"
				)
				.since("1.0.0");

		// RemoveGroupEvent
		Skript.registerEvent("remove group", SimpleEvent.class, SkriptRemoveGroupEvent.class, "[[simple] voice[ ]chat] (group remove|remove group)")
				.description("This event is called when a player or server removes a voice chat group.")
				.since("1.0.0");

		// LeaveGroupEvent
		Skript.registerEvent("leave group", SimpleEvent.class, SkriptLeaveGroupEvent.class, "[[simple] voice[ ]chat] (group leave|leave group)")
				.description("This event is called when a player leaves a voice chat group.")
				.since("1.0.0");

		// JoinGroupEvent
		Skript.registerEvent("join group", SimpleEvent.class, SkriptJoinGroupEvent.class, "[[simple] voice[ ]chat] (group join|join group)")
				.description("This event is called when a player joins a voice chat group.")
				.since("1.0.0");

		// PlayerDisconnectEvent
		Skript.registerEvent("voice chat player disconnect", SimpleEvent.class, SkriptPlayerDisconnectedEvent.class, "[simple] voice[ ]chat [player] disconnect[ed]")
				.description("This event is called when a player disconnects from the voice chat. You can only use 'event-string' to get the UUID of the player for this event.")
				.since("1.0.0");
		EventValues.registerEventValue(SkriptPlayerDisconnectedEvent.class, String.class, event -> {
			return event.getPlayerUUID() + "";
		}, EventValues.TIME_NOW);
		EventValues.registerEventValue(SkriptPlayerDisconnectedEvent.class, String.class, event -> {
			return event.getPlayerUUID() + "";
		}, EventValues.TIME_PAST);

		// PlayerConnectedEvent
		Skript.registerEvent("voice chat player connected", SimpleEvent.class, SkriptPlayerConnectedEvent.class, "[simple] voice[ ]chat [player] connect[ed]")
				.description("This event is called when a player connects to the voice chat.")
				.since("1.0.0");

		// PlayerChangedStateEvent
		Skript.registerEvent("voice chat player changed state", SimpleEvent.class, SkriptPlayerStateChangedEvent.class, "voice[ ]chat [player] state changed")
				.description("This event is called when a player's state changes in a voice chat.")
				.since("1.0.0");

		// RegisterVolumeCategoryEvent
		Skript.registerEvent("voice chat register volume category", SimpleEvent.class, SkriptRegisterVolumeCategoryEvent.class, "voice[ ]chat register[ing [a]] volume category")
				.description("This event is called when the server registers a volume category.")
				.since("1.0.0");

		// UnregisterVolumeCategoryEvent
		Skript.registerEvent("voice chat unregister volume category", SimpleEvent.class, SkriptUnregisterVolumeCategoryEvent.class, "voice[ ]chat unregister[ing [a]] volume category")
				.description("This event is called when the server unregisters a volume category.")
				.since("1.0.0");
		EventValues.registerEventValue(SkriptVolumeCategoryEvent.class, VolumeCategory.class, SkriptVolumeCategoryEvent::getVolumeCategory, EventValues.TIME_NOW);

		// VoiceHostEvent
		Skript.registerEvent("voice chat send host", SimpleEvent.class, SkriptVoiceHostEvent.class, "voice[ ]chat send host")
				.description("This event is called when the server sends the voice host to the server.")
				.since("1.0.0");

		// VoicechatServerStartingEvent
		Skript.registerEvent("voice chat server starting", SimpleEvent.class, SkriptVoicechatServerStartingEvent.class, "voice[ ]chat server starting")
				.description("This event is called before the voice chat server is started.")
				.since("1.0.0");

		// VoicechatServerStartingEvent
		Skript.registerEvent("voice chat server started", SimpleEvent.class, SkriptVoicechatServerStartedEvent.class, "voice[ ]chat server started")
				.description("This event is called when the voice chat server has started.")
				.since("1.0.0");

		// VoicechatServerStoppingEvent
		Skript.registerEvent("voice chat server stopping", SimpleEvent.class, SkriptVoicechatServerStoppedEvent.class, "voice[ ]chat server stop[ping]")
				.description("This event is called as the voice chat server stops.")
				.since("1.0.0");

		// SkriptStaticSoundPacketEvent
		Skript.registerEvent("static packet event", SimpleEvent.class, SkriptStaticSoundPacketEvent.class, "static sound packet event")
				.description("This event is emitted when a static sound packet is about to get sent to a client.")
				.since("1.0.2");
	}

}
