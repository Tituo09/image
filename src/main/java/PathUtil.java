

/**
 * @author Tituo
 * 此类是用thumbnails来处理图片的，进行压缩然后加水印，把经过了处理的图片保存到dest路径下
 */
public class PathUtil {
    // 获取分隔符
    private static String separator = System.getProperty("file.separator");

    /**
     * 图片保存的根目录
     * @return
     */
    public static String getImgBasePath() {
        // 获取操作系统
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            // "/home/xiangze/image/"
            basePath = "E:/download/image/";
        } else {
            basePath = "/home/ftpuser/images/";
        }
        // 将斜杆替换成分隔符，这样就能保证路径是有效的
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 图片保存的子目录：</br>
     * upload/meilianMall/shop/" + shopId
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId) {
        //  "/upload/item/shop/"+shopId+"/"
        String imagePath = "upload/meilianMall/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
    
}