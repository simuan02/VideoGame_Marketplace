package model;

public class ProdottoOrdine {

    public int getCodiceProdotto() {
        return codiceProdotto;
    }

    public void setCodiceProdotto(int codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public float getCostoAcquisto() {
        return costoAcquisto;
    }

    public void setCostoAcquisto(float costoAcquisto) {
        this.costoAcquisto = costoAcquisto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    private int codiceProdotto;
    private String codiceOrdine;
    private float costoAcquisto;
    private int quantita;
}
