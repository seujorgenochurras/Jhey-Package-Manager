//package io.github.seujorgenochurras.command;
//
//
//import java.util.HashMap;
//
//public class Teste {
//   public static HashMap<String, ? extends Command> commands = new Hashmap<>();
//
//   class RemoteCommand extends Command{
//      HashMap<String, ? extends Command> subCommands = new HashMap<>();
//
//      public HashMap<String, ? extends Command> getSubCommands() {
//         subCommands.put("add", new RemoteAddCommand())
//      }
//
//      @Override
//      public void invoke(String[] params) {
//
//      }
//
//      @Override
//      public String getName() {
//         return null;
//      }
//   }
//
//   {
//      commands.put("remote", new RemoteCommand());
//   }
//
//}
//
