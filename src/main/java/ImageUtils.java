import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class ImageUtil {
    // 设置时间格式
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    // 生成随机数对象
    private static final Random r = new Random();
    // 水映图片位置
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    // 日志相关
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 对图片进行压缩并加水印
     * @param
     * @param targetAddr 目标路径
     * @return
     */
    public static String generateThumbnail(File uploadFile,String targetAddr) {
        // 传过来的图片可能是重名的，因此系统随机生成不重复名字
        String realFileName = getRandomFileName();
        // 获取图片扩展名，如jpg,png等
        String extension = getFileExtension(uploadFile);
        // 目标路径可能不存在，不存在就创建
        makeDirPath(targetAddr);
        // 获取图片相对路径（目标路径+随机名+扩展名）
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        // 新生成文件路径(根路径+targetAddr + realFileName + extension)
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        // 创建缩略图并加水映
        try {
            Thumbnails.of(uploadFile).size(600, 600)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark1.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 将CommonsMultipartFile转换成File,</br>
     * 因为springmvc会自动把前端图片封装成CommonsMultipartFile,</br>
     * 上面压缩图片加水映方法可以直接传入CommonsMultipartFile，但是不方便测试，</br>
     * 所以上面方法是用的File,然后用这个方法把前端传入的CommonsMultipartFile转为File
     * @param cFile
     * @return
     */
    /*public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }*/

    /**
     * 生成随机文件名，当前年月日小时分钟秒+五位随机数
     * @param
     * @throws IOException
     */
    public static String getRandomFileName() {
        // 获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        // 当前时间
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * @param
     * @throws IOException
     */
    private static String getFileExtension(File uploadFile) {
        String fileName = uploadFile.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 目标路径文件夹不存在就创建
     * @param
     * @throws IOException
     */
    private static void makeDirPath(String targetAddr) {
        // 文件全路径
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

    }
}