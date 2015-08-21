package com.job5156.core.common.image;

import org.apache.commons.lang.StringUtils;

import java.awt.*;

/**
 * 动态生成图片常量配置
 * 
 * @author leo
 * 
 */
public class ImageEncryptConfig {
	// 参数分隔符
	public static final String PARAM_SPLIT = ",";
	// 多个字体分隔符
	public static final String PARAM_FONT_NAME_SPLIT = ":";
	// 图片内容参数缩写
	public static final String PARAM_VALUE_SHORT_NAME = "v";
	// 认证密钥参数缩写
	public static final String PARAM_KEYT_SHORT_NAME = "k";
	// 图片长度参数缩写
	public static final String PARAM_WIDTH_SHORT_NAME = "w";
	// 图片宽度参数缩写
	public static final String PARAM_HEIGHT_SHORT_NAME = "h";
	// 字体大写参数缩写
	public static final String PARAM_FONT_SIZE_SHORT_NAME = "fsi";
	// 字体颜色参数缩写
	public static final String PARAM_FONT_COLOR_SHORT_NAME = "fc";
	// 字体样式参数缩写
	public static final String PARAM_FONT_STYLE_SHORT_NAME = "fst";
	// 字体对齐参数缩写
	public static final String PARAM_FONT_ALIGN_SHORT_NAME = "fa";
	// 字体名称参数缩写
	public static final String PARAM_FONT_NAME_SHORT_NAME = "fn";
	// 图片显示样式
	public static final String PARAM_DISTORT_STYLE_SHORT_NAME = "ds";

	// 扩展动态图片生成组件字体样式
	public static final String KAPTCHA_TEXTPRODUCER_FONT_STYLE = "kaptcha.textproducer.font.style";
	// 扩展动态图片生成组件字体对齐方式
	public static final String KAPTCHA_TEXTPRODUCER_FONT_ALIGN = "kaptcha.textproducer.font.align";

	/**
	 * 图片引擎样式枚举
	 */
	public enum ImageDistortStyle {
		WATER("water", "com.google.code.kaptcha.impl.WaterRipple"), 
		FISH("fish", "com.google.code.kaptcha.impl.FishEyeGimpy"), 
		SHADOW("shadow", "com.google.code.kaptcha.impl.ShadowGimpy");

		private String name;
		private String value;

		private ImageDistortStyle(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public static ImageDistortStyle getDistortStyleByName(String name) {
			for (ImageDistortStyle distortStyle : values()) {
				if (StringUtils.equalsIgnoreCase(distortStyle.getName(), name)) {
					return distortStyle;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 字体样式
	 * 
	 * @author leo
	 * 
	 */
	public enum FontStyle {
		PLAIN("plain", Font.PLAIN), BOLD("bold", Font.BOLD), ITALIC("italic", Font.ITALIC);

		private String name;
		private Integer value;

		private FontStyle(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public static FontStyle getFontStyleByName(String name) {
			for (FontStyle style : values()) {
				if (StringUtils.equals(style.getName(), name)) {
					return style;
				}
			}
			return FontStyle.PLAIN;
		}

		public String getName() {
			return name;
		}

		public Integer getValue() {
			return value;
		}
	}

	/**
	 * 字体对齐方式
	 * 
	 * @author leo
	 * 
	 */
	public enum FontAlign {
		LEFT("left"), CENTER("center"), RIGHT("right");

		private String name;

		private FontAlign(String name) {
			this.name = name;
		}

		public static FontAlign getFontAlignByName(String name) {
			for (FontAlign align : values()) {
				if (StringUtils.equals(align.getName(), name)) {
					return align;
				}
			}
			return FontAlign.LEFT;
		}

		public String getName() {
			return name;
		}
	}
}
