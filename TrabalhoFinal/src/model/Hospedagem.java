package model;

import java.sql.Date;

public class Hospedagem {
	private Integer codHospedagem;
	private Integer codChale;
	private Integer codCliente;
	private Date dataInicio;
	private Date dataFim;
	private String estado;
	private Integer qtdPessoas;
	private Double desconto;
	private Double valorFinal;

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getCodHospedagem() { return codHospedagem; }
	public void setCodHospedagem(Integer codHospedagem) { this.codHospedagem = codHospedagem; }

	public Integer getCodChale() { return codChale; }
	public void setCodChale(Integer codChale) { this.codChale = codChale; }

	public Date getDataInicio() { return dataInicio; }
	public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

	public Date getDataFim() { return dataFim; }
	public void setDataFim(Date dataFIM) { this.dataFim = dataFIM; }

	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }

	public Integer getQtdPessoas() { return qtdPessoas; }
	public void setQtdPessoas(Integer qtdPessoas) { this.qtdPessoas = qtdPessoas; }

	public Double getDesconto() { return desconto; }
	public void setDesconto(Double desconto) { this.desconto = desconto; }

	public Double getValorFinal() { return valorFinal; }
	public void setValorFinal(Double valorFinal) { this.valorFinal = valorFinal; }
}
