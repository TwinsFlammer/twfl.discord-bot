package br.com.twinflammer.dicordbot.group.data;

import br.com.twinflammer.dicordbot.configuration.Configuration;
import br.com.twinsflammer.common.shared.permissions.group.data.Group;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import org.json.simple.JSONObject;

/**
 * @author SrGutyerrez
 */
public class DiscordGroup extends Group {
    public DiscordGroup(Group group) {
        super(
                group.getId(),
                group.getName(),
                group.getPrefix(),
                group.getSuffix(),
                group.getColor(),
                group.getPriority(),
                group.getTabListListOrder(),
                group.getDiscordGroupId(),
                group.getServerId(),
                group.getPermissions()
        );
    }

    public Role getRole(Guild guild) {
        JSONObject jsonObject = Configuration.getJsonObject();



        return guild.getRoleById(this.getDiscordGroupId());
    }
}
