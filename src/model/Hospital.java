package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hospital {
    private String nome;
    private ArrayList<Enfermaria> enfermarias;

    public Hospital(String nome) {
        this.nome = nome;
        this.enfermarias = new ArrayList();
    }

    public void addEnfermaria(Enfermaria e) {
        this.enfermarias.add(e);
    }

    public ArrayList<Enfermaria> getEnfermarias() {
        return this.enfermarias;
    }

    public void ordenarPorTaxa(LocalDate data) {
        this.enfermarias.sort((a, b) -> Double.compare(b.taxaOcupacao(data), a.taxaOcupacao(data)));
    }

    public Enfermaria getEnfermariaPorId(String id) {
        for(Enfermaria e : this.enfermarias) {
            if (e.getId().equals(id)) {
                return e;
            }
        }

        return null;
    }
}
