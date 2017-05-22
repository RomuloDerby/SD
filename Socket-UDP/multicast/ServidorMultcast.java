package trabalhodesd.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class ServidorMultcast {
	 final static String ENDERECO = "224.0.3";
	    final static int PORTA = 9876;

	    public static void main(String[] agrs) throws UnknownHostException, InterruptedException {
	        InetAddress addr = InetAddress.getByName(ENDERECO);
	        String mensagem = "oi mundo";
	        try (DatagramSocket serverSocket = new DatagramSocket()) {
	            for (int i = 0; i < 5; i++) {
	                //pegando as informacoes do pacote
	                DatagramPacket msgPacket = new DatagramPacket(mensagem.getBytes(), mensagem.getBytes().length, addr, PORTA);

	                //enviando mensagem para o grupo de clientes
	                serverSocket.send(msgPacket);

	                System.out.println("Mensagem enviada do servidor: " + mensagem);
	                //Dormindo pq ngm eh de ferro
	                Thread.sleep(500);
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
}
