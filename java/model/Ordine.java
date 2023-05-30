package model;

import java.util.Random;

public class Ordine {

    public Ordine(){
        this.createRandomCode();
    }

    public String getId() {
        return id;
    }

    public void createRandomCode (){
        Random r = new Random();
        String s = "";
        for(int i=0; i<15; i++){
            int x = (r.nextInt(43) + 48);
            while ((x < 65) && (x > 57)){
                x = (r.nextInt(43) + 48);
            }
            char c = (char)x;
            s += c;
        }
        this.setId(s);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public float getSpesaTotale() {
        return spesaTotale;
    }

    public void setSpesaTotale(float spesaTotale) {
        this.spesaTotale = spesaTotale;
    }

    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    public void setNumeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getCittaSpedizione() {
        return cittaSpedizione;
    }

    public void setCittaSpedizione(String cittaSpedizione) {
        this.cittaSpedizione = cittaSpedizione;
    }

    public String getNazioneSpedizione() {
        return nazioneSpedizione;
    }

    public void setNazioneSpedizione(String nazioneSpedizione) {
        this.nazioneSpedizione = nazioneSpedizione;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getCognomeDestinatario() {
        return cognomeDestinatario;
    }

    public void setCognomeDestinatario(String cognomeDestinatario) {
        this.cognomeDestinatario = cognomeDestinatario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String id;
    private String stato;
    private String metodoPagamento;
    private String indirizzoSpedizione;
    private String cittaSpedizione;
    private String nazioneSpedizione;
    private String nomeDestinatario;
    private String cognomeDestinatario;
    private float spesaTotale;
    private int numeroArticoli;
    private String data;
    private String utente;
}
