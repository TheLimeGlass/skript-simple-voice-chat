package me.limeglass.skriptsimplevoicechat.elements.group;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import de.maxhenkel.voicechat.api.Group;

@Name("Voice Connection Group Can Persist")
@Description("Returns true if a <a href='./classes.html#voicechatconnectiongroup'>voice chat connection group</a> will persist over server restart. Must create a new group to change value.")
@Since("1.0.0")
public class ConGroupIsPersistent extends PropertyCondition<Group> {

	static {
		// TODO change this to ProeprtyType.WILL in Skript 2.8+
		register(ConGroupIsPersistent.class, PropertyType.CAN, "(be persistent|persist)", "voicechatgroups");
	}

	@Override
	public boolean check(Group group) {
		return group.isPersistent();
	}

	@Override
	protected String getPropertyName() {
		return "persist";
	}

}
