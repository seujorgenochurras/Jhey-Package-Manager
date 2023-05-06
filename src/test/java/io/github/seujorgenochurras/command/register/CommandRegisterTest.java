package io.github.seujorgenochurras.command.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.github.seujorgenochurras.command.reflections.register.CommandRegister.COMMANDS;


class CommandRegisterTest {

   @Test
    void commandRegisterIsCorrectlyRegistering(){
      Assertions.assertTrue(COMMANDS.containsKey("Hello World"));
   }
}
