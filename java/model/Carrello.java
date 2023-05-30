package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carrello {

    public Carrello (){
        spesaTot = 0;
        numeroArticoli = 0;
        prodottiCarrello = new ArrayList<>();
    }

    public void createRandomCode (){
        Random r = new Random();
        String s = "";
        for(int i=0; i<10; i++){
            int x = (r.nextInt(43) + 48);
            while ((x < 65) && (x > 57)){
                x = (r.nextInt(43) + 48);
            }
            char c = (char)x;
            s += c;
        }
        this.setCodice(s);
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public float getSpesaTot() {
        return spesaTot;
    }

    public void setSpesaTot(float spesaTot) {
        this.spesaTot = spesaTot;
    }

    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    public void setNumeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
    }

    public List<ProdottoCarrello> getProdottiCarrello (){
        return prodottiCarrello;
    }

    public void setProdottiCarrello (List<ProdottoCarrello> prods){
        prodottiCarrello = prods;
    }

    public ProdottoCarrello cercaProdotto (int prodID){

        for (ProdottoCarrello p : prodottiCarrello){
            if (p.getIdProdotto() == prodID)
                return p;
        }
        return null;
    }

    private String codice;
    private float spesaTot;
    private int numeroArticoli;
    private List<ProdottoCarrello> prodottiCarrello;
}
