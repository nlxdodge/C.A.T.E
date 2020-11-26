package aq3d.bot;

import java.util.logging.Logger;

import aq3d.bot.api.ServerApi;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class AQ3DBot {

  private static final String LOGO = "https://gerbenveenhof.nl/images/bot-log.png";

  private static final Logger logger = Logger.getLogger(AQ3DBot.class.getName());
  private static final ServerApi api = new ServerApi(logger);

  public static void main(String[] args) {

    GatewayDiscordClient client = DiscordClientBuilder
        .create("TODO").build().login().block();

    client.getEventDispatcher().on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
        .filter(message -> message.getContent().equalsIgnoreCase("-servers")).flatMap(Message::getChannel)
        .flatMap(channel -> channel.createEmbed(spec -> spec.setAuthor("🤖 AQ3D Bot", null, LOGO)
            .setTitle("⚔ Adventure Quest 3D Servers ⚔").setDescription(api.getServerInfo())))
        .subscribe();

    client.getEventDispatcher().on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
        .filter(message -> message.getContent().equalsIgnoreCase("-character")).flatMap(Message::getChannel)
        .flatMap(channel -> channel.createEmbed(
            spec -> spec.setAuthor("🤖 AQ3D Bot", null, LOGO).setTitle(String.format("🕴 Character: %s 🕴", "NLxDoDge"))
                .setDescription(api.getCharachterPageUrl("NLxDoDge"))))
        .subscribe();

    client.onDisconnect().block();
  }
}
