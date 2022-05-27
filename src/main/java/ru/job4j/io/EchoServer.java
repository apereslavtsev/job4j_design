package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                boolean closeRequestReceived = false;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.matches("(.+)msg=(.+)")) {
                            String msg = str.split("=")[1].split("HTTP")[0].trim();
                            if ("Hello".equals(msg)) {
                                out.write("Hello, my dear friend!".getBytes());
                            } else if ("Exit".equals(msg)) {
                                out.write("Bye bye!".getBytes());
                                closeRequestReceived = true;
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                    }
                    out.flush();
                    if (closeRequestReceived) {
                        server.close();
                    }
                }
            }
        }
    }
}
