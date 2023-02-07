package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientTCP {
    private final String ip;
    private final int port;

    public ClientTCP (String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        try {
            Socket socket = new Socket(ip, port);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            String data = "Can you send me your phone number ?\n";

            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            System.out.println("Requested message: " + data);

            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String responseMessage = bufferedReader.readLine();
            System.out.println("Response message: " + responseMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ClientTCP clientTCP = new ClientTCP("127.0.0.1", 8000);

        clientTCP.start();
    }
}