/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Yuri Willians
 */
public class Registro {
    
    private String nome;
    private String matricula;
    private String horario;
    private String computador;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the computador
     */
    public String getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(String computador) {
        this.computador = computador;
    }
    
    @Override
    public String toString() {
        return getMatricula();
    }
    
}
