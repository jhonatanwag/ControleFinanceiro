package br.com.controlefinanceiro.util;

import java.util.Set;

public final class ValidatorUtil {
	public static boolean diferenteDeNulo(Object o) {
		return o != null;
	}

	public static void checarNull(String paramParametro, Object paramValor) throws NullPointerException {
		if (paramValor == null) {
			throw new NullPointerException("O parametro \"" + paramParametro + "\" nao pode ser null");
		}
	}

	public static boolean brancoOuNulo(String string) {
		return (string == null) || ("".equals(string.trim()));
	}

	public static void checarTamanho(String paramParametro, Object paramValor, int paramTamanho)
			throws NullPointerException {
		checarNull(paramParametro, paramValor);
		if (paramValor.toString().length() > paramTamanho) {
			throw new NullPointerException(
					"O parametro \"" + paramParametro + "\" nao pode ter tamanho maior que " + paramTamanho);
		}
	}

	public static boolean conjuntoEstaNuloOuVazio(Set<?> conjunto) {
		return (conjunto == null) || (conjunto.isEmpty());
	}
}
