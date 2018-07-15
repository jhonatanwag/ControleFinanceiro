package br.com.controlefinanceiro.controle;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.com.controlefinanceiro.model.Pessoa;
import br.com.controlefinanceiro.util.jsf.FacesUtil;
import br.com.controlefinanceiro.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioEmailPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	@Inject
	@PessoaEdicao
	private Pessoa pessoa;

	public void enviarEmail() {
		MailMessage message = mailer.novaMensagem();
		
//		message.to(this.pedido.getCliente().getEmail())
//			.subject("Pedido " + this.pedido.getId())
//			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
//			.put("pedido", this.pedido)
//			.put("numberTool", new NumberTool())
//			.put("locale", new Locale("pt", "BR"))
//			.send();
		
				message.to("nilodp@hotmail.com").subject("teste").send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}

}
