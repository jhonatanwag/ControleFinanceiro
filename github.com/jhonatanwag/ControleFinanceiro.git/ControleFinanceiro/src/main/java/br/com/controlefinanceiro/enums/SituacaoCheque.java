/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlefinanceiro.enums;


/**
 *
 * @author Danilo	
 */
public enum SituacaoCheque {

	PENDENTE("Pendente"),
	ENVIADO_BANCO("Enviado banco"),
    COMPENSADO("Compensado"),
    DEVOLVIDO_1_VEZ("Devolvido 1º Vez"),
    DEVOLVIDO_2_VEZ("Devolvido 2º Vez"),
	PAGO_A_TERCEIROS("Devolvido 1º Vez"),
	PERDA("perda"),
	ROUBO("Roubo"),
	SUSTADO("Sustado");
    private String value;

    private SituacaoCheque(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static SituacaoCheque getSituacaoCheque(Integer value) {
        switch (value) {
        case 0:
            return PENDENTE;
            case 1:
                return ENVIADO_BANCO;
            case 2:
                return COMPENSADO;
            case 3:
                return DEVOLVIDO_1_VEZ;
            case 4:
                return DEVOLVIDO_2_VEZ;
            case 5:
                return PAGO_A_TERCEIROS;         
            case 6:
                return PERDA;
            case 7:
                return ROUBO;
            case 8:
                return ROUBO;                
            default:
                return ENVIADO_BANCO;
        }
    }
}
