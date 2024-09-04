package model;

import java.util.Date;


public class Cliente {
    public Integer codCliente;
    private String nomeCliente;
    private String rgCliente;
    private String bairroCliente;
    private String cidadeCliente;
    private String cepCliente;
    private Date nascimentoCliente;

    public Cliente(Integer codCliente, String nomeCliente, String rgCliente, String bairroCliente, String cidadeCliente, String cepCliente, Date nascimentoCliente) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.bairroCliente = bairroCliente;
        this.cidadeCliente = cidadeCliente;
        this.cepCliente = cepCliente;
        this.nascimentoCliente = nascimentoCliente;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public Date getNascimentoCliente() {
        return nascimentoCliente;
    }

    public void setNascimentoCliente(Date nascimentoCliente) {
        this.nascimentoCliente = nascimentoCliente;
    }
}
