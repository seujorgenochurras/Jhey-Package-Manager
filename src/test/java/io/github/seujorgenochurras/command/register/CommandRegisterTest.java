//package io.github.seujorgenochurras.command.register;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import io.github.seujorgenochurras.api.commands.HelloCliCommand;
//import io.github.seujorgenochurras.api.commands.HelloCommand;
//import io.github.seujorgenochurras.command.AbstractCommand;
//import io.github.seujorgenochurras.command.reflections.register.CommandRegister;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//
// class CommandRegisterTest {
//
//   @Test
//    void commandRegisterIsCorrectlyRegistering(){
//      HashMap<String, AbstractCommand> actualCommands = new HashMap<>();
//      HelloCommand helloCommandInstance = new HelloCommand();
//      helloCommandInstance.subCommands.put("Cli", new HelloCliCommand());
//      helloCommandInstance.subCommands.put("World", new HelloCliCommand());
//      actualCommands.put("Hello", new HelloCommand());
//
//      assertEquals(CommandRegister.commands.toString(), actualCommands.toString());
//   }
//}
