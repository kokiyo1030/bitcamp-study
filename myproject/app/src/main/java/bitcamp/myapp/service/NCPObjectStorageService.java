package bitcamp.myapp.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class NCPObjectStorageService implements StorageService {
    final String endPoint = "https://kr.object.ncloudstorage.com";
    final String regionName = "kr-standard";
    final String accessKey = "ncp_iam_BPASKR1yeFGPyX34D8yg";
    final String secretKey = "ncp_iam_BPKSKRZ1xZu9Jqam67jXusKzyag5iixyLf";

    final String bucketName = "bitcamp-70";

    final AmazonS3 s3;

    public NCPObjectStorageService() {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public void upload(String filePath, InputStream fileIn) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/x-directory");

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, // 버킷 이름
                filePath, // 업로드 파일의 이름 및 디렉토리 경로
                fileIn, // 업로드 할 파일의 InputStream
                objectMetadata // 업로드에 필요한 부가 정보
        );

        try {
            s3.putObject(putObjectRequest);

        } catch (Exception e) {
            throw new StorageServiceException(e);
        }
    }


}
