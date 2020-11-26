package aq3d.bot.api;

import static org.junit.Assert.assertFalse;

import java.util.logging.Logger;

import org.junit.Test;

import aq3d.bot.AQ3DBot;

public class ServerApiTest {

    private static final Logger logger = Logger.getLogger(AQ3DBot.class.getName());
    ServerApi api = new ServerApi(logger);

    @Test
    public void getServerData() {
        String output = api.getServerInfo();
        assertFalse(output.isBlank());
    }
}