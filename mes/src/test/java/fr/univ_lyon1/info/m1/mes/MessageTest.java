package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.model.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MessageTest {
    private static Message message;

    @BeforeAll
    static void setUp() {
        //GIVEN
        message = new Message("text");
    }

    @Test
    void addMessageTest() {
        //WHEN
        message.addMessage("text");
        List <String> messages = message.getMessages();
        // THEN
        assertThat(messages, hasItem("text"));

    }
}