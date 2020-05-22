package br.com.twinsflammer.discordbot.user.factory;

import br.com.twinsflammer.discordbot.user.data.DiscordUser;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

/**
 * @author SrGutyerrez
 */
public class DiscordUserFactory<U extends DiscordUser> {
    private List<U> users = Lists.newArrayList();

    public U getUser(Integer id) {
        return this.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public U getUser(UUID uniqueId) {
        return this.users.stream()
                .filter(u -> u.getUniqueId().equals(uniqueId))
                .findFirst()
                .orElse(null);
    }

    public U getUser(String name) {
        return this.users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
