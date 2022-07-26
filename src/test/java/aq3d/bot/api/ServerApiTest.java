package aq3d.bot.api;


import java.util.logging.Logger;


import aq3d.bot.AQ3DBot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerApiTest {

    private static final Logger logger = Logger.getLogger(AQ3DBot.class.getName());
    ServerApi api = new ServerApi(logger);

    @Test
    public void getServerData() {
        String output = api.getServerInfo();
        Assertions.assertFalse(output.isBlank());
    }
}