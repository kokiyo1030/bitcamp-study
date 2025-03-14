package bitcamp.myapp.service;

import java.io.InputStream;

public interface StorageService {

    void upload(String filePath, InputStream fileIn);

}
