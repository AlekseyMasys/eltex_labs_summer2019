package ru.masis;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try(BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream())) {
            File file = new File("src/main/resources/notFound.html");
            String startingLine = buf.readLine();
            String[] elementOfStartingLine = startingLine.split(" ");
            String url = elementOfStartingLine[1];
            if (url.equals("/index.html")) {
                file = new File("src/main/resources/index.html");
                String responce = "HTTP/1.1 200 OK" + System.lineSeparator() + "Content-Type: text/html"
                        + System.lineSeparator() + "Content.Length: ";
                BufferedReader readerFromFile = new BufferedReader(new FileReader(file));
                String htmlString = "";
                while (readerFromFile.ready()) {
                    htmlString += readerFromFile.readLine();
                }
                readerFromFile.close();
                responce = responce + htmlString.length() + System.lineSeparator() + System.lineSeparator() + htmlString;
                printWriter.println(responce);
            }
            else {
                String responce = "HTTP/1.1 404 file not found" + System.lineSeparator() + "Content-Type: text/html"
                        + System.lineSeparator() + "Content.Length: ";
                BufferedReader readerFromFile = new BufferedReader(new FileReader(file));
                String htmlString = "";
                while (readerFromFile.ready()) {
                    htmlString += readerFromFile.readLine();
                }
                readerFromFile.close();
                responce = responce + htmlString.length() + System.lineSeparator() + System.lineSeparator() + htmlString;
                printWriter.println(responce);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
