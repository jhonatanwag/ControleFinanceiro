package br.com.controlefinanceiro.teste;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import javax.faces.context.FacesContext;

import br.com.controlefinanceiro.util.DataUtils;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	      //  String path2 = FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getRequestContextPath();
	        try {
				System.out.print(DataUtils.dataHora(new Date(), "00:00"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
