package br.com.twinsflammer.discordbot.group.data;

import br.com.twinsflammer.discordbot.configuration.Configuration;
import br.com.twinsflammer.common.shared.permissions.group.data.Group;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import org.json.simple.JSONArray;
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

        JSONArray guilds = (JSONArray) jsonObject.get("guilds");

        Object object = guilds.stream()
                .filter(o -> {
                    JSONObject jsonObject2 = (JSONObject) o;

                    Long guildId = (Long) jsonObject2.get("id");

                    return guild.getId().equals(guildId);
                })
                .findFirst()
                .orElse(null);

        JSONObject jsonObject1 = (JSONObject) object;

        System.out.println(jsonObject1);

        return null;
    }
}
