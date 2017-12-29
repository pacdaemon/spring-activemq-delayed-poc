package poc.models;

import java.io.Serializable;

public class Lote implements Serializable {

    private static final long serialVersionUID = 7526272291622716147L;

    private int nroLote;

    public Lote(int nroLote) {
        this.nroLote = nroLote;
    }

    public int getNroLote() {
        return nroLote;
    }

    public void setNroLote(int nroLote) {
        this.nroLote = nroLote;
    }
}
