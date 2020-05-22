package br.com.twinsflammer.discordbot.user.data;

import br.com.twinsflammer.discordbot.DiscordBot;
import br.com.twinsflammer.common.shared.permissions.user.data.User;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

/**
 * @author SrGutyerrez
 */
public class DiscordUser extends User {
    public DiscordUser(User user) {
        super(
                user.getId(),
                user.getName(),
                user.getDisplayName(),
                user.getUniqueId(),
                user.getEmail(),
                user.getPassword(),
                user.getCash(),
                user.getDiscordId(),
                user.isTwoFactorAuthenticationEnabled(),
                user.getTwoFactorAuthenticationCode(),
                user.getCreatedAt(),
                user.getFirstLogin(),
                user.getLastLogin(),
                user.getLastAddress(),
                user.getLastLobbyId(),
                user.getLanguageId(),
                user.getTwitterAccessToken(),
                user.getTwitterTokenSecret(),
                user.getGroups(),
                user.getPreferences(),
                user.getFriends(),
                user.getIgnored(),
                user.getReports(),
                user.getSkins(),
                user.isChangingSkin(),
                user.isWaitingTabListRefresh()
        );
    }

    public void setRole(Guild guild, Role role) {
        if (this.getDiscordId() == null) return;

        Member member = this.getMember(guild);

        member.getRoles().forEach(role1 -> guild.removeRoleFromMember(member, role1));

        guild.addRoleToMember(member, role);
    }

    public void updateDisplayName(Guild guild) {
        Member member = this.getMember(guild);

        JDA jda = DiscordBot.getInstance().getClient();

        member.modifyNickname(this.getDisplayName());
    }

    public Member getMember(Guild guild) {
        return guild.getMemberById(this.getDiscordId());
    }
}
