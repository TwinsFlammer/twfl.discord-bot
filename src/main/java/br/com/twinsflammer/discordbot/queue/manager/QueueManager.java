package br.com.twinsflammer.discordbot.queue.manager;

import br.com.twinsflammer.common.shared.permissions.group.data.Group;
import br.com.twinsflammer.common.shared.permissions.user.data.User;
import br.com.twinsflammer.common.shared.permissions.user.manager.UserManager;
import br.com.twinsflammer.discordbot.DiscordBot;
import br.com.twinsflammer.discordbot.group.data.DiscordGroup;
import br.com.twinsflammer.discordbot.user.data.DiscordUser;
import br.com.twinsflammer.common.shared.Common;
import com.google.common.collect.Queues;
import net.dv8tion.jda.api.entities.Role;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author SrGutyerrez
 */
public class QueueManager {
    private final Queue<DiscordUser> waitingUpdate = Queues.newConcurrentLinkedQueue();

    public QueueManager() {
        User user = UserManager.getUser(2);

        DiscordUser discordUser1 = new DiscordUser(user);

        this.waitingUpdate.add(discordUser1);

        Common.getInstance().getScheduler().scheduleAtFixedRate(
                () -> {
                    DiscordUser discordUser = waitingUpdate.poll();

                    Group group = discordUser.getHighestGroup();
                    DiscordGroup discordGroup = new DiscordGroup(group);

                    DiscordBot.getInstance().getClient().getGuilds().forEach(guild -> {
                        Role role = discordGroup.getRole(guild);

                        discordUser.updateDisplayName(guild);
                    });
                },
                0,
                1,
                TimeUnit.SECONDS
        );
    }
}
