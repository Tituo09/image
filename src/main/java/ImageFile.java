/**
 * @author Tituo
 * @create 2020-05-20
 */

/**
 * //TODO 添加类/接口功能描述
 *
 * @author fuqm
 * @date 2020-05-20
 */
public class ImageFile {

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 影像类型
     */
    private String imageType;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件格式
     */
    private String fileFormat;
    /**
     * 文件字节大小
     */
    private Long fileSize;
    /**
     * 影响状态
     */
    private String status;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 影像URL
     */
    private String imageUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
