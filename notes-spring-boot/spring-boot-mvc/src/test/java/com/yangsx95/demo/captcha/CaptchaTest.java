package com.yangsx95.demo.captcha;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/6/14
 */
public class CaptchaTest {

    @Test
    public void genCaptcha() {
        BufferedImage bufferedImage = Captcha.builder()
                .code("123")
                .fill(30, 10)
                .chaosLine(3)
                .font(new Font("Fixedsys", Font.PLAIN, 18))
                .build()
                .writeAsBufferedImage();
    }

}