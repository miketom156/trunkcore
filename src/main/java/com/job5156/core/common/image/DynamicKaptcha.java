package com.job5156.core.common.image;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.util.ConfigException;
import com.google.code.kaptcha.util.Configurable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 生成动态图片组件
 * 
 * @author leo
 * 
 */
public class DynamicKaptcha extends Configurable implements Producer {

	private int width = 200;
	private int height = 50;

	@Override
	public BufferedImage createImage(String text) {
		WordRenderer wordRenderer = getConfig().getWordRendererImpl();
		GimpyEngine gimpyEngine = this.getObscurificatorImpl();
		BackgroundProducer backgroundProducer = getConfig().getBackgroundImpl();
		boolean isBorderDrawn = getConfig().isBorderDrawn();
		this.width = getConfig().getWidth();
		this.height = getConfig().getHeight();

		BufferedImage bi = wordRenderer.renderWord(text, width, height);
		if(gimpyEngine != null){
			bi = gimpyEngine.getDistortedImage(bi);
		}
		bi = backgroundProducer.addBackground(bi);
		Graphics2D graphics = bi.createGraphics();
		if (isBorderDrawn) {
			drawBox(graphics);
		}
		return bi;
	}

	private void drawBox(Graphics2D graphics) {
		Color borderColor = getConfig().getBorderColor();
		int borderThickness = getConfig().getBorderThickness();

		graphics.setColor(borderColor);

		if (borderThickness != 1) {
			BasicStroke stroke = new BasicStroke((float) borderThickness);
			graphics.setStroke(stroke);
		}

		Line2D line1 = new Line2D.Double(0, 0, 0, width);
		graphics.draw(line1);
		Line2D line2 = new Line2D.Double(0, 0, width, 0);
		graphics.draw(line2);
		line2 = new Line2D.Double(0, height - 1, width, height - 1);
		graphics.draw(line2);
		line2 = new Line2D.Double(width - 1, height - 1, width - 1, 0);
		graphics.draw(line2);
	}

	/**
	 * @return the text to be drawn
	 */
	public String createText() {
		return getConfig().getTextProducerImpl().getText();
	}

	/**
	 * 设置图片属性
	 * 
	 * @param imageMap
	 */
	public void setImageProperties(Map<String, String> imageMap) {
		String width = imageMap.get(ImageEncryptConfig.PARAM_WIDTH_SHORT_NAME);
		String height = imageMap.get(ImageEncryptConfig.PARAM_HEIGHT_SHORT_NAME);
		String fontSize = imageMap.get(ImageEncryptConfig.PARAM_FONT_SIZE_SHORT_NAME);
		String fontColor = imageMap.get(ImageEncryptConfig.PARAM_FONT_COLOR_SHORT_NAME);
		String fontStyle = imageMap.get(ImageEncryptConfig.PARAM_FONT_STYLE_SHORT_NAME);
		String fontAlgin = imageMap.get(ImageEncryptConfig.PARAM_FONT_ALIGN_SHORT_NAME);
		String fontName = imageMap.get(ImageEncryptConfig.PARAM_FONT_NAME_SHORT_NAME);
		String distortStyle = imageMap.get(ImageEncryptConfig.PARAM_DISTORT_STYLE_SHORT_NAME);

		if (NumberUtils.toInt(width) > 0) {
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_IMAGE_WIDTH, width);
		}
		if (NumberUtils.toInt(height) > 0) {
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, height);
		}
		if (NumberUtils.toInt(fontSize) > 0) {
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, fontSize);
		}
		if (StringUtils.isNotBlank(fontColor)) {
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, fontColor);
		}
		if (StringUtils.isNotBlank(distortStyle)) {
			String obscurificatorImpl = this.getObscurificatorImpl(distortStyle);
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, obscurificatorImpl);
		}
		if (StringUtils.isNotBlank(fontStyle)){
			this.getConfig().getProperties().setProperty(ImageEncryptConfig.KAPTCHA_TEXTPRODUCER_FONT_STYLE, fontStyle);
		}
		if (StringUtils.isNotBlank(fontAlgin)){
			this.getConfig().getProperties().setProperty(ImageEncryptConfig.KAPTCHA_TEXTPRODUCER_FONT_ALIGN, fontAlgin);
		}
		if (StringUtils.isNotBlank(fontName)){
			fontName = StringUtils.replace(fontName, ImageEncryptConfig.PARAM_FONT_NAME_SPLIT, ",");
			this.getConfig().getProperties().setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, fontName);
		}
	}

	/**
	 * 获取图片引擎映射类
	 * 
	 * @param distortStyle
	 * @return
	 */
	private String getObscurificatorImpl(String distortStyle) {
		ImageEncryptConfig.ImageDistortStyle imageDistortStyle = ImageEncryptConfig.ImageDistortStyle.getDistortStyleByName(distortStyle);
		if (imageDistortStyle != null) {
			return imageDistortStyle.getValue();
		}
		return "";
	}

	/**
	 * 创建图片引擎映射类
	 * 
	 * @return
	 */
	private GimpyEngine getObscurificatorImpl() {
		String paramName = Constants.KAPTCHA_OBSCURIFICATOR_IMPL;
		String paramValue = this.getConfig().getProperties().getProperty(paramName);

		if (StringUtils.isNotBlank(paramValue)) {
			try {
				Object object = Class.forName(paramValue).newInstance();
				if (object instanceof Configurable) {
					((Configurable) object).setConfig(this.getConfig());
				}
				return (GimpyEngine) object;
			} catch (IllegalAccessException iae) {
				throw new ConfigException(paramName, paramValue, iae);
			} catch (ClassNotFoundException cnfe) {
				throw new ConfigException(paramName, paramValue, cnfe);
			} catch (InstantiationException ie) {
				throw new ConfigException(paramName, paramValue, ie);
			}
		}
		return null;
	}
}
