package model;

public class Servico {
    public Integer codServico;
    private String nomeServico;
    public Integer valorServico;

    public Integer getCodServico() {
        return codServico;
    }

    public void setCodServico(Integer codServico) {
        this.codServico = codServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getValorServico() {
        return valorServico;
    }

    public void setValorServico(Integer valorServico) {
        this.valorServico = valorServico;
    }
}
