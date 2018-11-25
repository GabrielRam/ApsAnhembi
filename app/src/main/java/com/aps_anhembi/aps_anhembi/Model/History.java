package com.aps_anhembi.aps_anhembi.Model;

public class History {
    int id;
    int idLocal;
    String entrada;
    String saida;

    public History(int id, int idLocal, String entrada, String saida) {
        this.id = id;
        this.idLocal = idLocal;
        this.entrada = entrada;
        this.saida = saida;
    }

    public History() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida)  {
        this.saida = saida;
    }

}
