package me.limeglass.skriptsimplevoicechat;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.events.CreateGroupEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.JoinGroupEvent;
import de.maxhenkel.voicechat.api.events.LeaveGroupEvent;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.events.PlayerConnectedEvent;
import de.maxhenkel.voicechat.api.events.PlayerDisconnectedEvent;
import de.maxhenkel.voicechat.api.events.RemoveGroupEvent;
import de.maxhenkel.voicechat.api.events.VoiceHostEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartingEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStoppedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptCreateGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptJoinGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptLeaveGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptMicrophonePacketEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerConnectedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptPlayerDisconnectedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptRemoveGroupEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoiceHostEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStartedEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStartingEvent;
import me.limeglass.skriptsimplevoicechat.events.SkriptVoicechatServerStoppedEvent;
import me.limeglass.skriptsimplevoicechat.events.VoiceChatEvent;

public final class SkriptSimpleVoiceChat extends JavaPlugin implements VoicechatPlugin {

	private static VoicechatServerApi VOICE_CHAT_API;
	private static SkriptSimpleVoiceChat INSTANCE;
	private SkriptAddon ADDON;

	@Override
	public void onEnable() {
		INSTANCE = this;
		if (Skript.getVersion().isSmallerThan(new Version(2, 7))) {
			getLogger().warning("The current version of Skript is not 2.7.0 or newer. Disabling Skript Simple Voice Chat Addon.");
			setEnabled(false);
			return;
		}
		try {
			ADDON = Skript.registerAddon(this)
					.loadClasses("me.limeglass.skriptsimplevoicechat", "elements")
					.setLanguageFileDirectory("lang");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
		if (service != null) {
			service.registerPlugin(this);
		} else {
			getLogger().info("Failed to register skript simple voice chat addon.");
		}
	}

	@Override
	public void registerEvents(EventRegistration registration) {
		registration.registerEvent(VoicechatServerStartedEvent.class, event -> VOICE_CHAT_API = event.getVoicechat(), 100);

		// Other
		registration.registerEvent(VoiceHostEvent.class, event -> callEvent(new SkriptVoiceHostEvent(event)));
		registration.registerEvent(VoicechatServerStartingEvent.class, event -> callEvent(new SkriptVoicechatServerStartingEvent(event)));
		registration.registerEvent(VoicechatServerStartedEvent.class, event -> callEvent(new SkriptVoicechatServerStartedEvent(event)));
		registration.registerEvent(VoicechatServerStoppedEvent.class, event -> callEvent(new SkriptVoicechatServerStoppedEvent(event)));

		// Player
		registration.registerEvent(PlayerDisconnectedEvent.class, event -> callEvent(new SkriptPlayerDisconnectedEvent(event)));
		registration.registerEvent(MicrophonePacketEvent.class, event -> callEvent(new SkriptMicrophonePacketEvent(event)));
		registration.registerEvent(PlayerConnectedEvent.class, event -> callEvent(new SkriptPlayerConnectedEvent(event)));

		// Group
		registration.registerEvent(CreateGroupEvent.class, event -> callEvent(new SkriptCreateGroupEvent(event)));
		registration.registerEvent(CreateGroupEvent.class, event -> callEvent(new SkriptCreateGroupEvent(event)));
		registration.registerEvent(RemoveGroupEvent.class, event -> callEvent(new SkriptRemoveGroupEvent(event)));
		registration.registerEvent(LeaveGroupEvent.class, event -> callEvent(new SkriptLeaveGroupEvent(event)));
		registration.registerEvent(JoinGroupEvent.class, event -> callEvent(new SkriptJoinGroupEvent(event)));
	}

	/**
	 * Method required to keep event loaded.
	 */
	private void callEvent(VoiceChatEvent<?> event) {
		Runnable runnable = () -> {
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled())
				event.getEvent().cancel();
		};
		if (!Bukkit.isPrimaryThread()) {
			runnable.run();
			return;
		}
		Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
	}

	@Override
	public void onDisable() {
		getServer().getServicesManager().unregister(this);
	}

	public static VoicechatServerApi getVoiceChatAPI() {
		return VOICE_CHAT_API;
	}

	public static SkriptSimpleVoiceChat getInstance() {
		return INSTANCE;
	}

	public SkriptAddon getAddonInstance() {
		return ADDON;
	}

	@Override
	public String getPluginId() {
		return "skript-simple-voice-chat";
	}

}
