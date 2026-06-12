package cat.linky.linky_cat_api.adapters.out.aws.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.ports.out.dto.FileUploadData;
import cat.linky.linky_cat_api.core.ports.out.file_upload.FileUploaderPort;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class AwsS3FileUploaderAdapter implements FileUploaderPort {
    
    @Value("${aws.s3.endpoint}")
    private String endpoint;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public AwsS3FileUploaderAdapter(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String upload(FileUploadData file) {

        String fileName = file.filename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .contentType(file.contentType())
            .build();

        s3Client.putObject(
            putObjectRequest, 
            RequestBody.fromBytes(file.bytes())
        );

        return endpoint + "/" + bucketName + "/" + fileName;
    }
    
}
