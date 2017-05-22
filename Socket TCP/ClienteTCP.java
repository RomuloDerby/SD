

package trabalhodesd.TCP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    String nomeArquivo;

    public ClienteTCP(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void enviarArquivo() throws Exception {

        File arquivo = new File("C:\\Users\\emanu\\Desktop\\Cliente/" + nomeArquivo);
        FileInputStream in = new FileInputStream(arquivo);
        Socket socket = new Socket("localhost", 6666);
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter writer = new BufferedWriter(osw);
        writer.write(arquivo.getName() + "\n");
        writer.flush();
        int c;
        while ((c = in.read()) != -1) {
            System.out.println(c);
            out.write(c);
        }
        out.flush();
        out.close();
        socket.close();
        in.close();
        writer.flush();
        writer.close();

    }
}
