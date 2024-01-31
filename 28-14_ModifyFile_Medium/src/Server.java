import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server class sets up a server-side application that listens for client connections and processes file requests.
 */
public class Server {
   /**
    * Port number for the server
    */
   private static final int PORT = 23508;

   /**
    * ServerSocket for accepting connections
    */
   private ServerSocket server;

   /**
    * Socket for communication with a client
    */
   private Socket connection;

   /**
    * Output stream to client
    */
   private ObjectOutputStream output;

   /**
    * Input stream from client
    */
   private ObjectInputStream input;

   /**
    * File object for the requested file
    */
   private File inputFile;

   /**
    * Constructor for Server. Initializes the ServerSocket.
    */
   public Server() {
      try {
         // Create a ServerSocket to listen on PORT with a maximum queue length of 10
         server = new ServerSocket(PORT, 10);
      } catch (IOException exception) {
         exception.printStackTrace();
         System.exit(0);
      }
   }

   /**
    * Runs the server, accepting client connections and processing file requests.
    */
   public void runServer() {
      try {
         // Accept a connection from a client
         connection = server.accept();

         // Set up streams for communication with the client
         output = new ObjectOutputStream(connection.getOutputStream());
         output.flush();

         input = new ObjectInputStream(connection.getInputStream());

         while(true) {
            try {
               // Read the file path from the client
               String filePath = (String) input.readObject();
               inputFile = new File(filePath);
            } catch (EOFException eofException) {
               throw new RuntimeException(eofException);
            }
            System.out.println("filePath = " + inputFile.getPath());

            // Check if the file exists and is a normal file (not a directory)
            if (inputFile.exists() && inputFile.isFile()) {
               FileReader reader = new FileReader(inputFile);
               BufferedReader bufferedReader = new BufferedReader(reader);
               String text = "";
               String file;
               while ((file=bufferedReader.readLine()) != null) {
                  text = text + file + "\n";
               }
               output.writeObject(inputFile);
               output.flush();
            } else {
               output.writeObject(new String(inputFile.getName() + " does not exist. Please try again.\n"));
               output.flush();
               connection.close();
            }
         }

      } catch (IOException exception) {
         exception.printStackTrace();
         System.exit(0);
      }
      catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
      }
      finally {
         // Close everything
         try {
            input.close();
            output.close();
            connection.close();
         } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
         }
      }
   }

}