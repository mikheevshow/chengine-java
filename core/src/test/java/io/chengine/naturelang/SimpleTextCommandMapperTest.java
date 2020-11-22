package io.chengine.naturelang;

import io.chengine.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SimpleTextCommandMapperTest {

    private TextCommandMapper mapper;

    @BeforeEach
    public void preInit() {
        mapper = new SimpleTextCommandMapper();
    }

    @Test
    public void mapCommandText() {
        final Command command = Command.builder().put("registration").build();
        final String textCommand = "User registration";

        mapper.map(command, textCommand);

        final Command c = mapper.get(textCommand);

        assertEquals(command, c);
    }

    @Test
    public void mapNullCommand2Text() {
        assertThrows(NullPointerException.class, () -> mapper.map(null, "User registration"));
    }

    @Test
    public void mapCommand2Null() {
        final Command c = Command.empty();
        assertThrows(NullPointerException.class, () -> mapper.map(c, null));
    }

    @Test
    public void mapSameTextTwiceIdempotentTest() {
        final String text = "Registration";

        // Commands equals by path
        final Command command1 = Command.builder().put("registration", "1111").build();
        final Command command2 = Command.builder().put("registration", "2222").build();

        mapper.map(command1, text);
        mapper.map(command2, text);
    }

    @Test
    public void mapSameTextTwiceNoIdempotentTest() {
        final String text = "Registration";

        final Command command1 = Command.builder().put("ping").build();
        final Command command2 = Command.builder().put("ding").build();

        mapper.map(command1, text);
        assertThrows(BindingAlreadyExists.class, () -> mapper.map(command2, text));
    }

    @Test
    public void mapCollection() {

        final Command command1 = Command.builder().put("ping").build();
        final List<String> texts = new ArrayList<>();

        texts.add("registration");
        texts.add("user registration");
        texts.add("user registration");

        mapper.mapAll(command1, texts.toArray(String[]::new));

    }



}
