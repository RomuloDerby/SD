package trabalhodesd.UDP;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import trabalhodesd.UDP.JanelaServidorUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class ServidorUDP extends Thread {

    JTextArea console = null;

    public ServidorUDP(JTextArea textDestino) {
        if (textDestino == null) {
            throw new NullPointerException("Destino não pode ser nulo!");
        }
        this.console = textDestino;
    }

    //Thread para inciar o  servidor e ficar ouvindo...
    public void run() {
        try {
            //Usado para enviar e receber pacotes
            DatagramSocket serverSocket = new DatagramSocket(6666);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            console.append("Servidor inciado na porta:" + 6666 + "... \n");
            while (true) {
                //Contem as informacoes do pacote que sera recebido
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                //Ouvindo na porta 9876
                serverSocket.receive(receivePacket);

                console.append("Pacote recebido \n");

                //Salvando o pacote dentro de uma String 
                String mensagemCliente = new String(receivePacket.getData());

                console.append("RECEBIDO: " + mensagemCliente + "\n");

                //Objeto para pegar o endereço ip  pra usar
                InetAddress IPAddress = receivePacket.getAddress();

                //Pegando a porta utilizada no pacote que o servidor recebeu
                int port = receivePacket.getPort();
                String mensagemServidor = mensagemCliente;
                mensagemServidor = "Servidor: " + mensagemCliente;

                //Convertendo em bytes as mensagem do servidor para enviar para o Cliente
                sendData = mensagemServidor.getBytes();

                //Contem as informacoes do pacote que sera enviado
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                //Enviando para o cliente
                serverSocket.send(sendPacket);
            }
        } catch (SocketException ex) {
            Logger.getLogger(JanelaServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JanelaServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
