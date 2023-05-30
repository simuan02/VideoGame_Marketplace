package model;

public class Immagine {


    public byte[] getFileImg() {
        return fileImg;
    }

    public void setFileImg(byte[] fileImg) {
        this.fileImg = fileImg;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    private byte[] fileImg;
    private int codice;

}
