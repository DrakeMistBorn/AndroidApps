package com.alivelife;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    private Drive mDriveService;

    public DriveServiceHelper(Drive mDriveService){
        this.mDriveService = mDriveService;
    }

    public Task<String> createFileImg(String Filepath){
        return Tasks.call(mExecutor, () -> {
            File fileMetaData = new File();
            fileMetaData.setName("image");

            java.io.File file = new java.io.File(Filepath);

            FileContent mediaContent = new FileContent("image/jpeg", file);

            File myFile = null;

            try {
                myFile = mDriveService.files().create(fileMetaData,mediaContent).execute();
            }catch (Exception e){
                e.printStackTrace();
            }

            if (myFile == null){
                throw new IOException("Null result in file creation");
            }

            return myFile.getId();

        });
    }
}
