package com.yangsx95.demo.captcha;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码相关工具类
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/6/14
 */
public class Captcha {

    private BufferedImage bufferedImage;
    private String code;

    public static class Builder {

        private Random random = new Random();

        private int width = 30;
        private int height = 10;
        private Font font;
        private Color color;
        private int lineSize;
        private String code;

        Builder() {
        }

        public Builder fill(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder font(Font font) {
            this.font = font;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder randomColor() {
            
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder chaosLine(int lineSize) {
            this.lineSize = lineSize;
            return this;
        }

        public Captcha build() {
            Captcha captcha = new Captcha();

            BufferedImage bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_BGR);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setFont(font);
            graphics.setColor(color);

            // 绘制多条干扰线
            for (int i = 0; i < this.lineSize; i++) {
                this.drawLine(graphics);
            }

            // 绘制code
            for (int i = 0; i < code.length(); i++) {
                graphics.translate(random.nextInt(3), random.nextInt(3));
                graphics.drawString(String.valueOf(code.charAt(i)), 13 * i, 16);
            }
            graphics.dispose();
            
            captcha.setBufferedImage(bufferedImage);
            captcha.setCode(this.code);
            return captcha;
        }

        /**
         * 绘制干扰线
         */
        private void drawLine(Graphics g) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(13);
            int yl = random.nextInt(15);
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public BufferedImage writeAsBufferedImage() {
        return this.bufferedImage;
    }

    public void writeAsOutputStream(String imageFormat, OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage, imageFormat, outputStream);
    }

    public void writeToResponse(String imageFormat, HttpServletResponse resp) throws IOException {
        ImageIO.write(bufferedImage, imageFormat, resp.getOutputStream());
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
