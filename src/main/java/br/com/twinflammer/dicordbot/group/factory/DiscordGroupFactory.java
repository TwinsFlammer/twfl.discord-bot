package br.com.twinflammer.dicordbot.group.factory;

import br.com.twinflammer.dicordbot.group.data.DiscordGroup;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author SrGutyerrez
 */
public class DiscordGroupFactory<G extends DiscordGroup> {
    private List<G> groups = Lists.newArrayList();

    public G getGroup(Integer id) {
        return this.groups.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public G getGroup(String name) {
        return this.groups.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
