package cat.linky.linky_cat_api.infra.config.external.aws;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class AwsS3Config {

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.endpoint:#{null}}")
    private String endpoint;

    @Value("${aws.s3.local-mode}")
    private boolean localMode;
    
    @Bean
    public S3Client s3Client() {

        S3ClientBuilder builder = S3Client.builder()
            .region(Region.of(region));

        if (localMode) {
            S3Configuration s3Configuration = S3Configuration.builder()
                .pathStyleAccessEnabled(true) // <--- Isso força o formato http://localhost:4566/my-local-bucket
                .build();

            builder.endpointOverride(URI.create(endpoint))
             .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create("local", "local")
            ))
            .serviceConfiguration(s3Configuration);
        } else {
            builder.credentialsProvider(DefaultCredentialsProvider.create());
        }

        return builder.build();
    }
}
