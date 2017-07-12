package managedbeans;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



@ManagedBean
public class FileDownloadController {

	private StreamedContent file;
	private static final String HOST = "127.0.0.1";
	private static final int PORTA = 1234;
	private static final int FILE_SIZE = 6022386;

	
	@PostConstruct
	public void init() {		
		

	}

	
	public FileDownloadController() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/images/galleria1.jpg");
		file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_galleria1.jpg");
	}

    public StreamedContent getFile() {
        return file;
    }
    
    
    public void receber() {
    	
	    try {
	        Socket socket = new Socket(HOST, PORTA);

	        DataInputStream dis = new DataInputStream(socket.getInputStream());

	        byte[] mybytearray = new byte[FILE_SIZE];

	        //FileOutputStream fos = new FileOutputStream("C:\\Users\\Guilherme\\Desktop\\android_copy.gif");
	        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\images\\"+"android_copy.png"));
	        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getContextName()+"\\android_copy.png");
	        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"\\android_copy.png");
	        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getContext().toString());

	        FileOutputStream fos = new FileOutputStream("D:\\PARALLEL\\CURSO_JAVA\\workspace\\Cliente33\\WebContent\\android_copy.png");


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
	      } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	      }
	}
    
    
}