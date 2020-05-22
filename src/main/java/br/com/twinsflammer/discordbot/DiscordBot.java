package br.com.twinsflammer.discordbot;

import br.com.twinsflammer.common.shared.Common;
import br.com.twinsflammer.discordbot.configuration.Configuration;
import br.com.twinsflammer.discordbot.group.data.DiscordGroup;
import br.com.twinsflammer.discordbot.group.factory.DiscordGroupFactory;
import br.com.twinsflammer.discordbot.manager.StartManager;
import br.com.twinsflammer.discordbot.user.data.DiscordUser;
import br.com.twinsflammer.discordbot.user.factory.DiscordUserFactory;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.Presence;
import org.json.simple.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author SrGutyerrez
 */
public class DiscordBot {
    @Getter
    private static DiscordBot instance;

    @Getter
    private static DiscordGroupFactory<? extends DiscordGroup> discordGroupFactory;
    @Getter
    private static DiscordUserFactory<? extends DiscordUser> discordUserFactory;

    @Getter
    private final JDA client;

    private ClassLoader classLoader;

    public DiscordBot() throws LoginException {
        new Common();

        this.classLoader = this.getClass().getClassLoader();

        DiscordBot.instance = this;

        new StartManager();

        DiscordBot.discordGroupFactory = new DiscordGroupFactory<>();
        DiscordBot.discordUserFactory = new DiscordUserFactory<>();

        this.client = new JDABuilder(DiscordBot.getAccessToken())
                .build();

        Presence presence = this.client.getPresence();

        presence.setStatus(OnlineStatus.OFFLINE);
    }

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
    }

    public static String getAccessToken() {
        JSONObject jsonObject = Configuration.getJsonObject();

        return (String) jsonObject.get("access_token");
    }

    public static Guild getStaffGuild() {
        return null;
    }

    public static Guild getOfficialGuild() {
        return null;
    }

    public static List<Guild> getGuilds() {
        return DiscordBot.getInstance().getClient().getGuilds();
    }

    public InputStream getResource(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        } else {
            try {
                URL url = this.classLoader.getResource(filename);
                if (url == null) {
                    return null;
                } else {
                    URLConnection connection = url.openConnection();
                    connection.setUseCaches(false);
                    return connection.getInputStream();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }
}
