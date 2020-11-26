package aq3d.bot.api;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.lang3.StringUtils;

import aq3d.bot.models.Server;

public class ServerApi {
    private Logger logger;
    public static final String ENDPOINT = "https://game.aq3d.com/";
    public static final String SERVERLIST = "api/Game/ServerList";
    public static final String CHARACTER = "account/Character?id=";

    public ServerApi(Logger logger) {
        this.logger = logger;
    }

    public String getCharachterPageUrl(String character) {
        StringBuilder output = new StringBuilder();
        String url = ENDPOINT.concat(CHARACTER).concat(character);
        try (WebClient client = new WebClient()) {
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = client.getPage(url);
            DomElement charLevel = page.getFirstByXPath("//p[@class='text-big']");
            output.append("Level: " + charLevel.getVisibleText().replace("Level ", "") + "\n");
            DomElement charClass = (DomElement) page.getByXPath("//img[@class='img-responsive center-block']").get(1);
            output.append("Class: " + StringUtils.capitalize(charClass.getAttribute("alt")));
        } catch (Exception e) {
            logger.log(Level.WARNING, "URL malformed", e);
        }
        return output.toString();
    }

    public String getServerInfo() {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(ENDPOINT.concat(SERVERLIST));
            parseServerInfo(url, output);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Endpoint error:", e);
        }
        return output.toString();
    }

    private void parseServerInfo(URL url, StringBuilder output) {
        try (InputStream is = url.openStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Server> servers = objectMapper.readValue(is, new TypeReference<List<Server>>() {
            });
            for (Server server : servers) {
                if (Boolean.FALSE.equals(server.exclude())) {
                    output.append(server + "\n");
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Parsing error:", e);
        }
    }
}