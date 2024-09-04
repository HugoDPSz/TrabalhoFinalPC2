package model;

import java.sql.Date;

public class Hospedagem {
	private Integer codHospedagem;
	private Integer codChale;
	private String estado;
	private Date dataInicio;
	private Date dataFim;
	private Integer qtdPessoas;
	private Double desconto;
	private Double valorFinal;

	public Integer getCodHospedagem() {
		return codHospedagem;
	}

	public void setCodHospedagem(Integer codHospedagem) {
		this.codHospedagem = codHospedagem;
	}

	public Integer getCodChale() {
		return codChale;
	}

	public void setCodChale(Integer codChale) {
		this.codChale = codChale;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(Integer qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

}
