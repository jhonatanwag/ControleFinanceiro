package br.com.controlefinanceiro.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StringUtil
{
  private static final int PRIMEIRA_LETRA_ASCII = 32;
  private static final int ULTIMA_LETRA_ASCII = 126;
  private static final char[][] MAPA_CHARS = { { 'a', 'ã', 'á', 'à', 'â', 'ä' }, { 'e', 'é', 'è', 'ê', 'ë' }, { 'i', 'í', 'ì', 'î', 'ï' }, { 'o', 'õ', 'ó', 'ò', 'ô', 'ö' }, { 'u', 'ú', 'ù', 'û', 'ü' }, { 'n', 'ñ' }, { 'c', 'ç' }, { 'A', 'Ã', 'Á', 'À', 'Â', 'Ä' }, { 'E', 'É', 'È', 'Ê', 'Ë' }, { 'I', 'Í', 'Ì', 'Î', 'Ï' }, { 'O', 'Õ', 'Ó', 'Ò', 'Ô', 'Ö' }, { 'U', 'Ú', 'Ù', 'Û', 'Ü' }, { 'N', 'Ñ' }, { 'C', 'Ç' }, { 'o', 'º', 'º' }, { 'a', 'ª' }, { '\'', '´', '`' }, { 'p', '§' } };
  
  public static boolean isVazia(Object str)
  {
    return (str == null) || (str.toString().trim().length() == 0);
  }
  
  public static Boolean characterParaBoolean(Character valor)
  {
    return Boolean.valueOf((valor != null) && ((valor.charValue() == 'S') || (valor.charValue() == 's')));
  }
  
  public static Character booleanParaCharacter(Boolean valor)
  {
    if (valor.booleanValue()) {
      return Character.valueOf('S');
    }
    return Character.valueOf('N');
  }
  
  public static String removerAcentos(String str, boolean removerCaracterEspecial)
  {
    StringBuilder strBui = new StringBuilder(str);
    for (int i = 0; i < strBui.length(); i++)
    {
      char letra = removerAcento(strBui.charAt(i));
      if (removerCaracterEspecial) {
        letra = removerCaracterEspecial(letra);
      }
      strBui.setCharAt(i, letra);
    }
    return strBui.toString();
  }
  
  private static char removerCaracterEspecial(char letra)
  {
    if (((letra < ' ') || (letra > '~')) && (letra != '\n') && (letra != '\r') && (letra != '>') && (letra != '<')) {
      return '*';
    }
    return letra;
  }
  
  public static char removerAcento(char letra)
  {
    for (int i = 0; i < MAPA_CHARS.length; i++) {
      for (int j = 1; j < MAPA_CHARS[i].length; j++) {
        if (MAPA_CHARS[i][j] == letra) {
          return MAPA_CHARS[i][0];
        }
      }
    }
    return letra;
  }
  
  public static String completaComZeros(int tamanho, String string)
  {
    if (string == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < tamanho - string.length(); i++) {
      sb.append("0");
    }
    return sb.toString() + string;
  }
  
  public static String leftPadding(String str, char ch, int len)
  {
    StringBuilder sb = new StringBuilder();
    len -= str.length();
    if (len > 0)
    {
      char[] array = new char[len];
      Arrays.fill(array, ch);
      sb.append(array);
    }
    sb.append(str);
    return sb.toString();
  }
  
  public static String rightPadding(String str, char ch, int len)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(str);
    len -= str.length();
    if (len > 0)
    {
      char[] array = new char[len];
      Arrays.fill(array, ch);
      sb.append(array);
    }
    return sb.toString();
  }
  
  public static String truncarComprimentoMaximo(String paramString, int paramComprimentoMaximo)
  {
    if (paramString != null)
    {
      if (paramString.length() > paramComprimentoMaximo) {
        return paramString.substring(0, paramComprimentoMaximo);
      }
      return paramString;
    }
    return null;
  }
  
  public static boolean isInteiro(String numero)
  {
    return (numero != null) && (numero.matches("-?\\d+"));
  }
  
  public static boolean isDecimal(String numero)
  {
    return (numero != null) && (numero.matches("-?(\\d+\\.?|\\.\\d+|\\d+\\.\\d+)([eE][+\\-]?\\d+)?"));
  }
  
  public static String toString(Throwable erro)
  {
    String message = erro.getMessage();
    if (message != null)
    {
      StringBuilder str = new StringBuilder(message);
      Throwable causa = erro.getCause();
      while (causa != null)
      {
        str.append("\n   ").append(causa.getMessage());
        causa = causa.getCause();
      }
      return str.toString();
    }
    return "";
  }
  
  public static String trocaQuebraLinha(String string, String replacement)
  {
    return string.replaceAll("((\r\n)|(\n)|(\r))", replacement);
  }
  
  public static String substituiParametros(String msg, String[] params)
  {
    if ((params == null) || (params.length % 2 != 0)) {
      throw new RuntimeException("O Array de parâmetros não é válido. Não pode ser nulo e deve possuir tamanho par.");
    }
    String rep = msg;
    for (int i = 0; i < params.length; i += 2) {
      rep = rep.replaceAll(params[i], params[(i + 1)]);
    }
    return rep;
  }
  
  public static List<String> divideNaString(String str, String ini, String fim)
  {
    String content = str;
    List<String> dividido = new ArrayList();
    
    int pIni = content.lastIndexOf(ini);
    int pFim = content.lastIndexOf(fim) + fim.length();
    while ((pIni != -1) && (pFim != -1) && (pIni < pFim))
    {
      String div = content.substring(pIni, pFim);
      dividido.add(div);
      
      content = content.substring(0, pIni);
      
      pIni = content.lastIndexOf(ini);
      pFim = content.lastIndexOf(fim) + fim.length();
    }
    return dividido;
  }
}
