// Fig. 28.4: ServerTest.java
// Test the Server application.
// Modified from above example.

/**
 * The ServerTest class contains the main method to start the Server application.
 * It creates an instance of the Server and initiates the server's operation.
 */
public class ServerTest
{

   /**
    * The main method creates and starts the server application.
    *
    * @param args -
    */
   public static void main(String[] args)
   {
      Server application = new Server(); // create server
      application.runServer(); // run server application
   } 
}