package br.com.controlefinanceiro.enums;

public enum TipoPagamento {

	A("DINHEIRO"), 
	B("CARTÃO DE DÉBITO"), 
	C("CARTÃO DE CRÉDITO"), 
	D("CHEQUE"), 
	E("CHEQUE SEM CADASTRO"), 
	F("CREDIÁRIO"), 
	G("BOLETO BANCÁRIO"), 
	H("DEPÓSITO BANCÁRIO"), 
	I("PAGSEGURO"), 
	J("PROMISSORIA"), 
	K("VALE PRESENTE"), 
	L("CARTÃO FIDELIDADE"), 
	M("GARANTIA"), 
	N("OUTRO");

	private String value;

	private TipoPagamento(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public static TipoPagamento getTipoVenda(Integer value) {
		switch (value) {
		case 0:
			return A;
		case 1:
			return B;
		case 2:
			return C;
		case 3:
			return D;
		case 4:
			return E;
		case 5:
			return F;
		case 6:
			return G;
		case 7:
			return H;
		case 8:
			return I;
		case 9:
			return J;
		case 10:
			return K;
		case 11:
			return L;
		case 12:
			return M;
		case 13:
			return N;
		default:
			return null;
		}
	}

	public static String getTipoVenda(TipoPagamento tipoVenda) {
		switch (tipoVenda) {
		case A:
			return "A";
		case B:
			return "B";
		case C:
			return "C";
		case D:
			return "D";
		case E:
			return "E";
		case F:
			return "F";
		case G:
			return "G";
		case H:
			return "H";
		case I:
			return "I";
		case J:
			return "J";
		case K:
			return "K";
		case L:
			return "L";
		case M:
			return "M";
		case N:
			return "N";
		default:
			return null;
		}
	}
}
