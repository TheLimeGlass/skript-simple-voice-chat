package me.limeglass.skriptsimplevoicechat.events;

import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.events.GroupEvent;

public abstract class SkriptGroupEvent<E extends GroupEvent> extends VoiceChatConnectionEvent<E> {

	public SkriptGroupEvent(VoicechatConnection connection, E event, Group group) {
		super(connection, event);
	}

	public Group getGroup() {
		return event.getGroup();
	}

}
