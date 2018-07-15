package br.com.controlefinanceiro.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.controlefinanceiro.util.jsf.FacesUtil;

@Named
//@RequestScoped
@ApplicationScoped
public class UploadImagensBean implements Serializable {
	
			
	private static final long serialVersionUID = 1L;

	
	public void upload(FileUploadEvent event) {
		UploadedFile uf = event.getFile();
		// Tools t = new Tools();
		// String nomeArq = t.agora() + "-" +
		// t.trataAcentoString(uf.getFileName());
		// this.avaliacao.setAnexo_resp(nomeArq);
		String nomeArq = uf.getFileName();
		String path = "";
		// aqui verifico se é linux ou windows
		if (System.getProperties().get("os.name").toString().trim().equalsIgnoreCase("Linux")) {
			path = "/home/workspace/gca/WebContent/resources/files/";
		} else {
			path = "c://files//";	
			//path = FacesUtil.getServletContext().getRealPath("/WEB-INF/imagens/");
		}

		File f = new File(path + nomeArq);
		OutputStream os = null;
		InputStream is = null;
		try {
			is = uf.getInputstream();
			byte[] b = new byte[is.available()];
			os = new FileOutputStream(f);
			while (is.read(b) > 0) {
				os.write(b);
			}
			// aqui você pode colcar a gravação do path no BD

			// Tools.msgAlert("Alerta", "O arquivo foi enviado corretamente,
			// clique em enviar para concluir a operação.");
			FacesUtil.addInfoMessage("Imagens enviadas com sucesso!");
		} catch (IOException ex) {
			FacesUtil.addErrorMessage("Erro ao enviar as imagens!" + ex);
		} finally {
			try {
				os.flush();
				os.close();
				is.close();
			} catch (IOException ex) {
				FacesUtil.addInfoMessage("Erro ao enviar as imagens!" + ex);
			}
		}
	}
	
	public InputStream getImage(String filename) {
	    try {
	    	
	    	//http://www.javaknowledge.info/upload-and-display-image-from-mysql-db-using-primefaces/
	    	String path="c://files//";
	    	String caminho = FacesUtil.getServletContext().getRealPath("/WEB-IF");
	        //InputStream is= FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
	        //String path2 = FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getRequestContextPath();
	    	//InputStreamReader r = new InputStreamReader(is);
		    //BufferedReader br = new BufferedReader(r);
	        System.out.println(caminho);
	       	       	    	
	    	filename = "indice.jpg";
			return new FileInputStream(new File(path, filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}