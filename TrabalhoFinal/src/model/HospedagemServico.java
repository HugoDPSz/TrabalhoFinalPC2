package model;

import java.time.LocalDate;

public class HospedagemServico {
    private Integer codHospedagem;
    private LocalDate dataServico;
    private Integer codServico;
    private Integer valorServico;

    public HospedagemServico(Integer codHospedagem, LocalDate dataServico, Integer codServico, Integer valorServico) {
        this.codHospedagem = codHospedagem;
        this.dataServico = dataServico;
        this.codServico = codServico;
        this.valorServico = valorServico;
    }

    public HospedagemServico() {

    }

    public Integer getValorServico() {
        return valorServico;
    }

    public void setValorServico(Integer valorServico) {
        this.valorServico = valorServico;
    }

    public Integer getCodHospedagem() {
        return codHospedagem;
    }

    public void setCodHospedagem(Integer codHospedagem) {
        this.codHospedagem = codHospedagem;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }

    public Integer getCodServico() {
        return codServico;
    }

    public void setCodServico(Integer codServico) {
        this.codServico = codServico;
    }
}
