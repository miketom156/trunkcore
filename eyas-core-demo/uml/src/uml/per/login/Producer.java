package uml.per.login;

import java.awt.image.BufferedImage;

/**
 * <p></p>
 * Date:2015/6/9 8:25
 *
 * @author hjs
 * @version 1.0
 */
public interface Producer {
        /**
         * Create an image which will have written a distorted text.
         *
         * @param text
         *            the distorted characters
         * @return image with the text
         */
        public BufferedImage createImage(String text);

        /**
         * @return the text to be drawn
         */
        public abstract String createText();

}
