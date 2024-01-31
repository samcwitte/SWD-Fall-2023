import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * The Client class extends JFrame.
 * It handles the user interface, connection setup, message sending and receiving functionalities.
 */
public class Client extends JFrame {
   /**
    * Text field for input
    */
   private JTextField enterField;

   /**
    * Text area for displaying messages
    */
   private JTextArea displayArea;

   /**
    * Stream for sending data
    */
   private ObjectOutputStream output;

   /**
    * Stream for receiving data
    */
   private ObjectInputStream input;

   /**
    * Socket for connection
    */
   private Socket client;

   /**
    * Server address
    */
   private String chatServer;

   /**
    * Panel for layout
    */
   private JPanel panel;

   /**
    * Label for text field
    */
   private JLabel label;

   /**
    * Scroll pane for display area
    */
   private JScrollPane scroll;

   /**
    * Constructor for the Client class.
    * Sets up the user interface components and initializes connection parameters.
    *
    * @param host The address of the chat server
    */
   public Client (String host) {
      super("Client");
      chatServer = host;

      // Interface setup and layout
      label = new JLabel("Enter filename: ");

      panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2, 0, 0));
      panel.add(label);

      enterField = new JTextField(2);
      enterField.addActionListener(
              new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    try {
                       output.writeObject(enterField.getText());
                    } catch (IOException ioException) {
                       System.out.println(ioException);
                    }
                 }
              }
      );

      panel.add(enterField);

      displayArea = new JTextArea();
      scroll = new JScrollPane(displayArea);

      setLayout(new BorderLayout());

      add(panel, BorderLayout.NORTH);
      add(scroll, BorderLayout.CENTER);

      setSize(600, 400); // set size of window
      setVisible(true); // show window

   }


   /**
    * Starts the client application.
    * Establishes a connection to the server, sets up streams, and processes the connection.
    */
   public void runClient()
   {
      try // connect to server, get streams, process connection
      {
         connectToServer(); // create a Socket to make connection
         getStreams(); // get the input and output streams
         // processConnection(); // process connection
      }
      catch (EOFException eofException)
      {
         displayMessage("\nClient terminated connection");
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
      finally
      {
         closeConnection(); // close connection
      }
   }

   /**
    * Attempts to connect to the server.
    * It uses chatServer's address to establish a socket connection.
    *
    * @throws IOException If an I/O error occurs when creating the socket
    */
   private void connectToServer() throws IOException
   {
      System.out.println("Attempting connection\n");

      // create Socket to make connection to server
      client = new Socket(InetAddress.getByName(chatServer), 23508);

      // display connection information
      System.out.println("Connected to: " +
              client.getInetAddress().getHostName());
   }

   /**
    * Sets up the input and output streams for communication with the server.
    *
    * @throws IOException If an I/O error occurs when creating the input and output streams
    */
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream(client.getOutputStream());
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream(client.getInputStream());

      displayMessage("\nGot I/O streams\n");
   }

   /**
    * Closes the streams and socket when the client is finished.
    */
   private void closeConnection()
   {
      displayMessage("\nClosing connection");

      try
      {
         output.close(); // close output stream
         input.close(); // close input stream
         client.close(); // close socket
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
      catch (NullPointerException nullPointerException) {
         nullPointerException.printStackTrace();
      }
   }

   /**
    * Sends a message to the server.
    * Used to send data through the output stream.
    *
    * @param message The message to be sent to the server
    */
   private void sendData(String message)
   {
      try // send object to server
      {
         output.writeObject(message);
         output.flush(); // flush data to output
         System.out.println(message);
      }
      catch (IOException ioException)
      {
         displayArea.append("\nError writing object");
      }
   }

   /**
    * Updates the display area in the GUI.
    *
    * @param messageToDisplay The message to be displayed in the text area
    */
   private void displayMessage(final String messageToDisplay)
   {
      SwingUtilities.invokeLater(
              new Runnable()
              {
                 public void run() // updates displayArea
                 {
                    displayArea.append(messageToDisplay);
                 }
              }
      );
   }
}