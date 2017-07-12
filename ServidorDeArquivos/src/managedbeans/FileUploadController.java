package managedbeans;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;












import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

















import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

@ManagedBean
public class FileUploadController {

	private UploadedFile file;
	private static final String HOST = "127.0.0.1";
	private static final int PORTA = 1234;
	private static final int FILE_SIZE = 6022386;
	private List<String> images;
	//Socket socket;
	//ServerSocket server;	





	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public void handleFileUpload(FileUploadEvent event) throws IOException {
		FacesMessage msg = new FacesMessage("Sucesso!", event.getFile()
				.getFileName() + " é Carregado.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); // diretorio o qual vou salvar o
		// arquivo do upload, equivale ao nome completamente qualificado

		byte[] conteudo = event.getFile().getContents(); 

		//while(true){

		transferenciaNomeArquivo(event.getFile().getFileName());
		transferenciaArquivo(event.getFile().getFileName());

		//}

	}

	private void transferenciaNomeArquivo(String nomeArquivo){

		try {

			ServerSocket server1 = new ServerSocket(PORTA);

			Socket socket1 = server1.accept();

			//Cria o objeto responsável por ler o stream
			ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
			//cria o objeto responsável por escrever no stream
			ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());

			String mensagemIn = ois.readObject().toString();
			System.out.println("Enviando o Titulo do Arquivo:");
			String mensagemOut = nomeArquivo;

			oos.writeObject(mensagemOut);
			oos.flush();

			ois.close();
			oos.close();
			socket1.close();
			server1.close();

		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void transferenciaArquivo(String nomeArquivo){

		try {


			ServerSocket server = new ServerSocket(PORTA);

			Socket socket = server.accept();



			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			//File arquivo = new File("C:\\Users\\Guilherme\\Desktop\\android.gif");
			//System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\RepositorioDeArquivos\\"+ nomeArquivo));
			//System.out.println( new File(nomeArquivo).getCanonicalPath());
			File arquivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\RepositorioDeArquivos\\"+ nomeArquivo));
			//File arquivo = new File( new File(nomeArquivo).getCanonicalPath());
			byte[] buffer = new byte[(int) arquivo.length()];

			FileInputStream fis = new FileInputStream(arquivo);

			BufferedInputStream bis = new BufferedInputStream(fis);

			bis.read(buffer, 0, buffer.length);

			System.out.println("Enviando arquivo \"" + arquivo.getName() + "\"");

			fis.close();
			bis.close();


			dos.write(buffer, 0, buffer.length);

			dos.flush();

			dos.close();

			socket.close();

			server.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void processFileUpload(FileUploadEvent event) throws IOException {

		try {

			// Cria um arquivo UploadFile, para receber o arquivo do evento

			UploadedFile arq = event.getFile();

			InputStream in = new BufferedInputStream(arq.getInputstream());

			// copiar para pasta do projeto

			File file = new File("D://arquivos//" + arq.getFileName());

			// O método file.getAbsolutePath() fornece o caminho do arquivo
			// criado

			// Pode ser usado para ligar algum objeto do banco ao arquivo
			// enviado

			String caminho = file.getAbsolutePath();

			FileOutputStream fout = new FileOutputStream(file);

			while (in.available() != 0) {

				fout.write(in.read());

			}

			fout.close();

			FacesMessage msg = new FacesMessage("O Arquivo ", file.getName()
					+ " salvo.");

			FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);

		}

		catch (Exception ex) {

			ex.printStackTrace();

		}

	}



	public void upload() {
		if (file != null) {
			//			FacesMessage msg = new FacesMessage("Succesful", file.getFileName()
			//					+ " is uploaded.");
			//			FacesContext.getCurrentInstance().addMessage(null, msg);

			byte[] conteudo = file.getContents(); // daqui pra baixo é somente
			// operações de IO.

			//			String[] nada = null;
			//			SocketServidor.main(nada);
			//			Arquivo arquivo = new Arquivo();
			//			arquivo.processaArquivo(file.getFileName());

			//-----------------------------------------------


			//			try {
			//				ServerSocket server = new ServerSocket(PORTA);
			//				Socket socket = server.accept();
			//
			//				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			//
			//				//File arquivo = new File("C:\\Users\\Guilherme\\Desktop\\android.gif");
			//
			//				File arquivo = new File("image.png");
			//
			//				byte[] buffer = new byte[(int) arquivo.length()];
			//
			//				FileInputStream fis = new FileInputStream(arquivo);
			//
			//				BufferedInputStream bis = new BufferedInputStream(fis);
			//
			//				bis.read(buffer, 0, buffer.length);
			//
			//				System.out.println("Enviando arquivo \"" + arquivo.getName() + "\"");
			//
			//				dos.write(buffer, 0, buffer.length);
			//
			//				dos.flush();
			//
			//				dos.close();
			//
			//				socket.close();
			//
			//				server.close();
			//			} catch (Exception ex) {
			//				System.out.println(ex.getMessage());
			//			}

		}



	}





}
