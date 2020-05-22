package br.com.twinsflammer.discordbot.queue.manager;

import br.com.twinsflammer.common.shared.permissions.user.data.User;
import br.com.twinsflammer.common.shared.permissions.user.manager.UserManager;
import br.com.twinsflammer.discordbot.user.data.DiscordUser;
import br.com.twinsflammer.common.shared.Common;
import com.google.common.collect.Queues;

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


                },
                0,
                1,
                TimeUnit.SECONDS
        );
    }
}
