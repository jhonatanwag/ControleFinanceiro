package br.com.controlefinanceiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtils {

	public static Date DATA_INICIO_01_01_2010 = new Date(1262311200000l);
	public static Date DATA_FIM_31_12_2100 = new Date(4133901600000l);
	
    private static Calendar referenceCalendar = Calendar.getInstance();

    public static Date toDateOnly(Date date) {
        Calendar calendar = (Calendar) referenceCalendar.clone();
        calendar.setTime(date);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.AM_PM);
        return calendar.getTime();
    }

    public static boolean isDiaUtil(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        if (!((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer getIdade(Date dataNascimento) {
        Calendar dtNiver = new GregorianCalendar();
        dtNiver.setTime(dataNascimento);
        Calendar dtHoje = Calendar.getInstance();
        Integer idade = dtHoje.get(Calendar.YEAR) - dtNiver.get(Calendar.YEAR);
        dtNiver.add(Calendar.YEAR, idade);
        if (dtHoje.before(dtNiver)) {
            idade--;
        }
        return idade;
    }

    public static boolean validaData(String data, String padrao) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(padrao);
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date converteStringEmData(String data, String formato) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        return formatador.parse(data);
    }

    public static String converteDataEmString(Date data, String formato) {
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        return formatador.format(data);
    }

    public static String converteDataEmStringFormatado(Date data) {
        SimpleDateFormat fDia = new SimpleDateFormat("dd");
        SimpleDateFormat fMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat fAno = new SimpleDateFormat("yyyy");
        return fDia.format(data) + " de " + fMes.format(data) + " de " + fAno.format(data);
    }

    public static Date dataHora(Date data, String hora) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatador.parse(converteDataEmString(data, "dd/MM/yyyy") + " " + hora);
    }

    public static String converteDataEmHora(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        return formatador.format(data);
    }

    public static Date converteDataEmHoraMM(Date data) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        return formatador.parse(converteDataEmString(data, "HH:mm"));
    }

    /**
     * M�todo para comparar as das e retornar o numero de dias de diferen�a
     * entre elas
     *
     * Compare two date and return the difference between them in days.
     *
     * @param dataLow The lowest date
     * @param dataHigh The highest date
     *
     * @return int
     */
    public static int diferencaEntreDatas(java.util.Date dataLow, java.util.Date dataHigh) {

        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();

        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();

        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);

        int dif_multiplier = 1;

        // Verifica a ordem de inicio das datas  
        if (dataLow.compareTo(dataHigh) < 0) {
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier = 1;
        } else {
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier = -1;
        }

        int result_years = 0;
        int result_months = 0;
        int result_days = 0;

        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando  
        // no total de dias. Ja leva em consideracao ano bissesto  
        while (curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR)
                || curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)) {

            int max_day = curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            result_months += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);

        }

        // Marca que � um saldo negativo ou positivo  
        result_months = result_months * dif_multiplier;


        // Retirna a diferenca de dias do total dos meses  
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));

        return result_years + result_months + result_days;
    }
    
    /**
     * 
     * Valida se data fim � maior que a data in�cio.
     * 
     * 
     * @param Date datInicio: data inicio
     * @param Date datFim: data fim
     * 
     * @return boolean
     */
    
    public static boolean validaDataMaior(Date dataInicio, Date dataFim){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(dataFim);
		int diaFim = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(dataInicio);
		int diaInicio = cal.get(Calendar.DAY_OF_MONTH);

		if (diaFim > diaInicio) {
			return true;
		}
		return false;
    	
    }
}
