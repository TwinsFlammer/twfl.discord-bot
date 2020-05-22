package br.com.twinflammer.dicordbot.queue.manager;

import br.com.twinflammer.dicordbot.user.data.DiscordUser;
import br.com.twinsflammer.common.shared.Common;
import br.com.twinsflammer.common.shared.permissions.user.data.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author SrGutyerrez
 */
public class QueueManager {
    private final Queue<DiscordUser> waitingUpdate = Queues.newConcurrentLinkedQueue();

    public QueueManager() {
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
