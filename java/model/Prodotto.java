package model;

public class Prodotto {

    public void setNome (String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public void setDescrizione (String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione (){
        return descrizione;
    }

    public void setPrezzoCatalogo (float prezzoCatalogo){
        this.prezzoCatalogo = prezzoCatalogo;
    }

    public float getPrezzoCatalogo (){
        return prezzoCatalogo;
    }

    public void setPrezzoAttuale (float prezzoAttuale){
        this.prezzoAttuale = prezzoAttuale;
    }

    public float getPrezzoAttuale (){
        return prezzoAttuale;
    }

    public void setQuantita (int quantita){
        this.quantita = quantita;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nome;
    private int id;
    private String descrizione;
    private float prezzoCatalogo;
    private float prezzoAttuale;
    private int quantita;
    private int categoria;
    private String piattaforma;
}
