package nia.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.1 BIO 示例
 */
public class BlockingIoExample {


    public void serve(int portNumber) throws IOException {

        ServerSocket serverSocket = new ServerSocket(portNumber);

        Socket clientSocket = serverSocket.accept();  //阻塞

        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);

        String request, response;

        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            response = processRequest(request);
            out.println(response);// 写入clientSocket
        }
    }

    private String processRequest(String request) {
        return "Processed";
    }
}
