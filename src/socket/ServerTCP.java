package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerTCP {
    private final int port;

    public ServerTCP (int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Socket connectionSocket = socket.accept();

                InputStream inputStream = connectionSocket.getInputStream();
                OutputStream outputStream = connectionSocket.getOutputStream();

                Reader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String clientData = bufferedReader.readLine();
                System.out.println("Message sent by client: " + clientData);

                String requestedData = clientData.equalsIgnoreCase("Can you send me your phone number ?")
                        ? "+998908991199" : "Incorrect message";

                outputStream.write(requestedData.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();

                System.out.println("Response message: " + requestedData);

                connectionSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerTCP serverTCP = new ServerTCP(8000);

        serverTCP.start();
    }
}