/**
 * 
 */
package cliente;

import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author charles
 *
 */

public class SocketCliente {

	private static final String HOST = "127.0.0.1";
	private static final int PORTA = 1234;
	private static final int FILE_SIZE = 6022386;
	//6022386


	public static String SolicitaNomeArquivo(){

		String mensagemIn = null;

		try {
			Socket socket1 = new Socket(HOST, PORTA);


			ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
			oos.writeObject("requisitando Uma Arquivo");
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
			mensagemIn = ois.readObject().toString();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mensagemIn;

	}
	
	public static void solicitacaoArquivo(String nomeArquivo){
		
		try {			

			Socket socket = new Socket(HOST, PORTA);

			DataInputStream dis = new DataInputStream(socket.getInputStream());

			byte[] mybytearray = new byte[FILE_SIZE];

			//FileOutputStream fos = new FileOutputStream("C:\\Users\\Guilherme\\Desktop\\android_copy.gif");



			FileOutputStream fos = new FileOutputStream(nomeArquivo);


			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int bytesRead = dis.read(mybytearray, 0, mybytearray.length);

			int current = bytesRead;

			do {
				bytesRead = dis.read(mybytearray, current, (mybytearray.length - current));
				if (bytesRead >= 0) {
					current += bytesRead;
				}
			} while (bytesRead > -1);

			bos.write(mybytearray, 0, current);

			System.out.println("Arquivo recebido com sucesso!");

			bos.flush();

			bos.close();

			socket.close();

			if(nomeArquivo.contains(".png") || nomeArquivo.contains(".jpg")|| nomeArquivo.contains(".GIF")){
				JFMostraImagem janela = new JFMostraImagem(nomeArquivo);     
				

			}
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		// solicitação do nome do arquivo
		String mensagemIn = SolicitaNomeArquivo();
		// Solicitação do arquivo
		solicitacaoArquivo(mensagemIn);



	}
}
