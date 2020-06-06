package main.services;

import java.io.IOException;
import java.io.OutputStream;

public class DisplayService {

    OutputStream outputStream;

    public DisplayService(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public synchronized void displayMessage(String message) throws IOException {
        outputStream.write(message.getBytes());
        outputStream.write("\n".getBytes());
    }

    public synchronized void displayError(String error) throws IOException {
        // display error.
        outputStream.write(error.getBytes());
        outputStream.write("\n".getBytes());
    }

    public synchronized void displaySuccess(String success) throws IOException {
        // display success.
        outputStream.write(success.getBytes());
        outputStream.write("\n".getBytes());
//        System.out.println(success);
    }
}
