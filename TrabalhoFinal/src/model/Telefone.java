package model;

public class Telefone {
    private String telefone;
    private int codCliente;
    private String tipoTelefone;

    public Telefone(String telefone, int codCliente, String tipoTelefone) {
        this.telefone = telefone;
        this.codCliente = codCliente;
        this.tipoTelefone = tipoTelefone;
    }

    public Telefone() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
