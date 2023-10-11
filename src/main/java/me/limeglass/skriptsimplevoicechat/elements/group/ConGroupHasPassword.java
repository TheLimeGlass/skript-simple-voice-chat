package me.limeglass.skriptsimplevoicechat.elements.group;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import de.maxhenkel.voicechat.api.Group;

@Name("Voice Connection Group Has A Password")
@Description("Returns true if a <a href='./classes.html#voicechatconnectiongroup'>voice chat connection group</a> has a password required. Must create a new group to change value.")
@Since("1.0.0")
public class ConGroupHasPassword extends PropertyCondition<Group> {

	static {
		register(ConGroupHasPassword.class, PropertyType.HAVE, "[a] [group] password", "voicechatgroups");
	}

	@Override
	public boolean check(Group group) {
		return group.hasPassword();
	}

	@Override
	protected String getPropertyName() {
		return "group password";
	}

}
