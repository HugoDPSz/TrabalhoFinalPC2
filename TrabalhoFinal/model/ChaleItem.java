package model;

public class ChaleItem {
    public Integer codChale;
    public String nomeItem;

    public ChaleItem(Integer codChale, String nomeItem) {
        this.codChale = codChale;
        this.nomeItem = nomeItem;
    }

    public ChaleItem() {

    }

    public Integer getCodChale() {
        return codChale;
    }

    public void setCodChale(Integer codChale) {
        this.codChale = codChale;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }
}
