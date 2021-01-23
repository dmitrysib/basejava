package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(Object object) {
        super("Resume " + object.toString() + " already exist");
    }
}