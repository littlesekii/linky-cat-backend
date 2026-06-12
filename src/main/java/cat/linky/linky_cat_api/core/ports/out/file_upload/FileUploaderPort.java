package cat.linky.linky_cat_api.core.ports.out.file_upload;

import cat.linky.linky_cat_api.core.ports.out.dto.FileUploadData;

public interface FileUploaderPort {
    public String upload(FileUploadData file); 
}
