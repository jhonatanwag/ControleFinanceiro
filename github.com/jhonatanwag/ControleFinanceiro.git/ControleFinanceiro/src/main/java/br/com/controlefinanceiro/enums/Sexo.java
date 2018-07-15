/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;


/**
 *
 * @author Douglas
 */
public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");
    private String value;

    private Sexo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Sexo getSexo(Integer value) {
        switch (value) {
            case 0:
                return MASCULINO;
            case 1:
                return FEMININO;
            default:
                return null;
        }
    }
}
