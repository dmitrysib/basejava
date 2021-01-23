package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(Object object) {
        super("Resume " + object.toString() + " not exist");
    }
}
