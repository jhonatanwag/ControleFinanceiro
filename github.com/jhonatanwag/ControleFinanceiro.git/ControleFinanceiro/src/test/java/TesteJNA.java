import com.sun.jna.Native;

public class TesteJNA {

	public static void main(String[] args) {
		String SEFAZReturnCode = "    ";
		String protocol = "                ";
		String dateHourProtocol = "                    ";

		BemaNFCe32 bemaNFCe32 = (BemaNFCe32) Native.loadLibrary("BemaNFCe32", BemaNFCe32.class);

		int answer = (int) bemaNFCe32.Bematech_NFCe_StatusInutilizaNota("001", "8", SEFAZReturnCode, protocol,
				dateHourProtocol);
		System.out.println(Integer.toString(answer));

	}

}
