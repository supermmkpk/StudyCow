package com.studycow.service.file;


import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * <pre>
 *  GCS 파일 관리 서비스 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}") // application.properties에 써둔 bucket 이름
    private String bucketName;

    /**
     * 파일 업로드
     *
     * @param file 업로드할 파일
     * @return String GCS 파일 링크
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new RuntimeException("업로드할 파일이 없습니다.");
        }

        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String contentType = file.getContentType(); // 파일의 형식 ex) JPG

        // Cloud에 파일 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(contentType)
                        .build(),
                file.getInputStream()
        );

        // Cloud 링크
        String fileLink = "https://storage.googleapis.com/studycow-bucket/" + uuid;

        return fileLink;
    }

}
