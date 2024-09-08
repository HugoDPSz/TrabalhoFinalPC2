package model;

public class Item {
    public String nomeItem;
    public String descricaoItem;

    public Item(String nomeItem, String descricaoItem) {
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
    }

    public Item() {
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

}
