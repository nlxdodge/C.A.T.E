package aq3d.bot;

import aq3d.bot.api.ServerApi;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.lang3.NotImplementedException;

public class AQ3DBot {
    
    private static final String LOGO_URL = "https://gerbenveenhof.nl/images/bot-log.png";
    private static final String COMMAND_PREFIX = "-";
    private static final Logger logger = Logger.getGlobal();
    private static final ServerApi api = new ServerApi(logger);
    
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Please insert your Discord Bot token as first parameter when starting");
        }
        GatewayDiscordClient client = DiscordClientBuilder.create(args[0]).build().login().block();
        
        client.on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
                  .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                  .filter(message -> message.getContent().equalsIgnoreCase(COMMAND_PREFIX + "servers"))
                  .flatMap(Message::getChannel).flatMap(channel -> channel.createMessage(
                            EmbedCreateSpec.builder().author("AQ3D Bot", null, LOGO_URL).title("Adventure Quest 3D Servers")
                                      .description(api.getServerInfo()).build())).subscribe();

        client.on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
                  .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                  .filter(message -> message.getContent().contains(COMMAND_PREFIX + "character"))
                  .flatMap(Message::getChannel).flatMap(channel -> channel.createMessage(
                            EmbedCreateSpec.builder().author("AQ3D Bot", null, LOGO_URL).title(String.format("Character: %s", "test"))
                                      .description(api.getCharachterPageUrl("NLxDoDge")).build())).subscribe();
        
        client.onDisconnect().block();
    }
}
