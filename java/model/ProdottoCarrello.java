package model;

public class ProdottoCarrello {

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getCodiceCarrello() {
        return codiceCarrello;
    }

    public void setCodiceCarrello(String codiceCarrello) {
        this.codiceCarrello = codiceCarrello;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }


    public float getPrezzoCad() {
        return prezzoCad;
    }

    public void setPrezzoCad(float prezzoCad) {
        this.prezzoCad = prezzoCad;
    }

    public float getPrezzoTotale (){
        return prezzoTotale;
    }

    public void setPrezzoTotale () {
        prezzoTotale = quantita * prezzoCad;
    }

    private int idProdotto;
    private String codiceCarrello;
    private int quantita;
    private float prezzoCad;
    private float prezzoTotale;
}
