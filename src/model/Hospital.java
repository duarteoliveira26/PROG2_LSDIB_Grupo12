package model;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Representa um hospital composto por várias enfermarias.
 */

public class Hospital {
    private String nome;
    private ArrayList<Enfermaria> enfermarias;
    /**
     * Cria um hospital.
     *
     * @param nome nome do hospital
     */

    public Hospital(String nome) {
        this.nome = nome;
        this.enfermarias = new ArrayList();
    }
    /**
     * Adiciona uma enfermaria ao hospital.
     *
     * @param e enfermaria a adicionar
     */

    public void addEnfermaria(Enfermaria e) {
        this.enfermarias.add(e);
    }
    /**
     * Devolve lista de enfermarias.
     *
     * @return lista de enfermarias
     */

    public ArrayList<Enfermaria> getEnfermarias() {
        return this.enfermarias;
    }
    /**
     * Ordena enfermarias por taxa de ocupação decrescente.
     *
     * @param data data de referência
     */

    public void ordenarPorTaxa(LocalDate data) {
        this.enfermarias.sort((a, b) -> Double.compare(b.taxaOcupacao(data), a.taxaOcupacao(data)));
    }
    /**
     * Devolve uma enfermaria pelo seu identificador.
     *
     * @param id identificador da enfermaria
     * @return enfermaria correspondente ou null se não existir
     */

    public Enfermaria getEnfermariaPorId(String id) {
        for(Enfermaria e : this.enfermarias) {
            if (e.getId().equals(id)) {
                return e;
            }
        }

        return null;
    }
}
