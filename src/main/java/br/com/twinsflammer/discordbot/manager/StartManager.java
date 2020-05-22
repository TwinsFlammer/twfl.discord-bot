package br.com.twinsflammer.discordbot.manager;

import br.com.twinsflammer.discordbot.configuration.Configuration;
import br.com.twinsflammer.discordbot.queue.manager.QueueManager;

/**
 * @author SrGutyerrez
 */
public class StartManager {
    public StartManager() {
        new Configuration();

        new DataManager();
    }
}

class DataManager {
    DataManager() {
        new QueueManager();
    }
}
