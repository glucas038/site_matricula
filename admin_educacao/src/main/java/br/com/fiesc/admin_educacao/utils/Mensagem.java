package br.com.fiesc.admin_educacao.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	public static void  adicionarMensagemErro(String resumo, String detalhe) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
