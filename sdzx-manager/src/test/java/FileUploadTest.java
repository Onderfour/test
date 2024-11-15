import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

public class FileUploadTest {
    public static void main(String[] args) throws Exception {
        // 创建一个Minio的客户端对象
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://123.56.86.30:9000/")
//                .endpoint("http://192.168.75.138:9000")
//                .credentials("minioadmin", "minioadmin")
                .credentials("adminminio", "adminminio")
//                .credentials("admin", "admin123")
                .build();

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("sdzx-bucket").build());

        // 如果不存在，那么此时就创建一个新的桶
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("sdzx-bucket").build());
        } else {  // 如果存在打印信息
            System.out.println("Bucket 'sdzx-bucket' already exists.");
        }

        FileInputStream fis = new FileInputStream("D://2.jpg") ;
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket("sdzx-bucket")
                .stream(fis, fis.available(), -1)
                .object("2.jpg")
                .build();
        minioClient.putObject(putObjectArgs) ;

        // 构建fileUrl
        String fileUrl = "http://123.56.86.30:9000/sdzx-bucket/2.jpg" ;
//        String fileUrl = "http://192.168.75.138:9000/sdzx-bucket/2.jpg" ;
        System.out.println(fileUrl);
    }
}
