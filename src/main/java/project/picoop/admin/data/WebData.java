package project.picoop.admin.data;

import lombok.Data;

@Data
public class WebData {
    long uploadedImages;
    long uploadedPetitions;
    long registeredUsers;

    public long getUploadedImages() {
        return uploadedImages;
    }

    public void setUploadedImages(long uploadedImages) {
        this.uploadedImages = uploadedImages;
    }

    public long getUploadedPetitions() {
        return uploadedPetitions;
    }

    public void setUploadedPetitions(long uploadedPetitions) {
        this.uploadedPetitions = uploadedPetitions;
    }

    public long getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(long registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}
