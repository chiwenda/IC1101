package com.biz.system.modules.system.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;

/**
 * 登录验证码工具类
 *
 * @author cwd
 * @date 2022/5/17 下午6:32
 */
public class RandImageUtil {

    public static final String key = "IC1101_LOGIN_KEY";

    /**
     * 定义图形大小
     */
    private static final int width = 105;
    /**
     * 定义图形大小
     */
    private static final int height = 35;

    /**
     * 定义干扰线数量
     */
    private static final int count = 200;

    /**
     * 干扰线的长度=1.414*lineWidth
     */
    private static final int lineWidth = 2;

    /**
     * 图片格式
     */
    private static final String IMG_FORMAT = "JPEG";

    /**
     * base64 图片前缀
     */
    private static final String BASE64_PRE = "data:image/jpg;base64,";

    public static void generate(HttpServletResponse response, String resultCode) {
        BufferedImage image = bufferedImage(resultCode);
        //输出图片
        try {
            ImageIO.write(image, IMG_FORMAT, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generate(String resultCode) {
        BufferedImage image = bufferedImage(resultCode);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        //写入流
        try {
            ImageIO.write(image, IMG_FORMAT, byteStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转换成字节
        byte[] bytes = byteStream.toByteArray();
        //转换成base64
        String base64Str = Base64.getEncoder().encodeToString(bytes).trim();
        base64Str = base64Str.replaceAll("\n", "").replaceAll("\r", "");
        return BASE64_PRE + base64Str;
    }

    private static BufferedImage bufferedImage(String resultCode) {
        //内存中创建图像
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //画图工具
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        //设置背景色
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.drawRect(0, 0, width - 1, height - 1);
        final Random random = new Random();
        //生成干扰线
        for (int i = 0; i < count; i++) {
            graphics2D.setColor(getRandColor(150, 200));

            // 保证画在边框之内
            final int x = random.nextInt(width - lineWidth - 1) + 1;
            final int y = random.nextInt(height - lineWidth - 1) + 1;
            final int xl = random.nextInt(lineWidth);
            final int yl = random.nextInt(lineWidth);
            graphics2D.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码
        for (int i = 0; i < resultCode.length(); i++) {
            // 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            // graphics.setColor(new Color(20 + random.nextInt(130), 20 + random
            // .nextInt(130), 20 + random.nextInt(130)));
            // 设置字体颜色
            graphics2D.setColor(Color.BLACK);
            // 设置字体样式
//			graphics.setFont(new Font("Arial Black", Font.ITALIC, 18));
            graphics2D.setFont(new Font("Times New Roman", Font.BOLD, 24));
            // 设置字符，字符间距，上边距
            graphics2D.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
        }
        // 图象生效
        graphics2D.dispose();
        return image;
    }

    private static Color getRandColor(int fc, int bc) {
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
