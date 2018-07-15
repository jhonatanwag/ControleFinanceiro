package br.com.controlefinanceiro.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

public class ImageUtil {

	public File redimensionarImagem(File arquivo, int width, int height) {
		File arquivoRedimensionado = null;
		try {
			BufferedImage originalImage = ImageIO.read(arquivo);
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
			arquivoRedimensionado = new File(arquivo.getName());
			ImageIO.write(resizedImage, "jpg", arquivoRedimensionado);
		} catch (Exception e) {
			return null;
		}
		return arquivoRedimensionado;
	}

	public static boolean resizeUsingJavaAlgo(String source, File dest, int width, int height) throws IOException {
		BufferedImage sourceImage = ImageIO.read(new FileInputStream(source));
		double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
		if (width < 1) {
			width = (int) (height * ratio + 0.4);
		} else if (height < 1) {
			height = (int) (width / ratio + 0.4);
		}

		Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedScaled.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(scaled, 0, 0, width, height, null);
		dest.createNewFile();
		writeJpeg(bufferedScaled, dest.getCanonicalPath(), 1.0f);
		return true;
	}

	/**
	 * Write a JPEG file setting the compression quality.
	 *
	 * @param image
	 *            a BufferedImage to be saved
	 * @param destFile
	 *            destination file (absolute or relative path)
	 * @param quality
	 *            a float between 0 and 1, where 1 means uncompressed.
	 * @throws IOException
	 *             in case of problems writing the file
	 */
	private static void writeJpeg(BufferedImage image, String destFile, float quality) throws IOException {
		ImageWriter writer = null;
		FileImageOutputStream output = null;
		try {
			writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);
			output = new FileImageOutputStream(new File(destFile));
			writer.setOutput(output);
			IIOImage iioImage = new IIOImage(image, null, null);
			writer.write(null, iioImage, param);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.dispose();
			}
			if (output != null) {
				output.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedImage originalImage = ImageIO.read(new File("teste.jpg"));
		int width = 100;
		int height = 100;
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
		ImageIO.write(resizedImage, "jpg", new File("teste_redimensionado.jpg"));
	}
}
