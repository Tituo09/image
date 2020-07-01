/**
 * @author Tituo
 * @create 2020-05-20
 */

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * //TODO 添加类/接口功能描述
 *
 * @author fuqm
 * @date 2020-05-20
 */
public class TestImage {

    public static final String IMAGE_PATH = "C:/Users/DELL/Desktop/images";



    /**
     * 指定大小进行缩放
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(200, 300).toFile(IMAGE_PATH+"/image_200x300.jpg");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(2560, 2048).toFile(IMAGE_PATH+"/image_2560x2048.jpg");
    }

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        /**
         * scale(比例)
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").scale(0.25f).toFile(IMAGE_PATH+"/image_25%.jpg");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").scale(1.10f).toFile(IMAGE_PATH+"/image_110%.jpg");
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(120, 120).keepAspectRatio(false).toFile(IMAGE_PATH+"/image_120x120" +
                ".jpg");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).rotate(90).toFile(IMAGE_PATH+"/image+90.jpg");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).rotate(-90).toFile(IMAGE_PATH+"/iamge-90.jpg");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(IMAGE_PATH+"/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile(IMAGE_PATH+"/image_watermark_bottom_right.jpg");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).watermark(Positions.CENTER, ImageIO.read(new File(
                IMAGE_PATH+"/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile(IMAGE_PATH+"/image_watermark_center.jpg");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {
        /**
         * 图片中心400*400的区域
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile(IMAGE_PATH+"/image_region_center.jpg");
        /**
         * 图片右下400*400的区域
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile(IMAGE_PATH+"/image_region_bootom_right.jpg");
        /**
         * 指定坐标
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false).toFile(IMAGE_PATH+"/image_region_coord.jpg");
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    @Test
    public void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).outputFormat("png").toFile(IMAGE_PATH+
                "/image_1280x1024.png");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).outputFormat("gif").toFile(IMAGE_PATH+
                "/image_1280x1024.gif");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    @Test
    public void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream(IMAGE_PATH+"/image_1280x1024_OutputStream.png");
        Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    @Test
    public void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of(IMAGE_PATH+"/test.jpg").size(1280, 1024).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File(IMAGE_PATH+"/image_1280x1024_BufferedImage.jpg"));
    }

}
