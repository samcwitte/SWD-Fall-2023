// Fig. 28.6: ClientTest.java
// Class that tests the Client.

import javax.swing.*;

/**
 * The ClientTest class creates an instance of the Client and initiates the client's operation.
 * The server's address can be specified via command line arguments.
 */
public class ClientTest 
{

   /**
    * The main method creates a Client instance and starts the client application.
    * The server's address is determined based on the command line arguments.
    *
    * @param args Command line arguments, can specify the server's address.
    */
   public static void main(String[] args)
   {
      Client application; // declare client application

      // if no command line args
      if (args.length == 0)
         //application = new Client("128.255.17.134");
         application = new Client("localhost");
      else
         application = new Client(args[0]); // use args to connect

      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runClient(); // run client application
   } 
}