import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int resumeCount;

    void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    void save(Resume r) {
        if (resumeCount < 10_000) {
            storage[resumeCount++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean foundUuid = false;
        for (int i = 0; i < resumeCount; i++) {
            if (!foundUuid && storage[i].uuid.equals(uuid)) {
                foundUuid = true;
            }
            if (foundUuid) {
                storage[i] = (i + 1 == 10_000) ? null : storage[i + 1];
            }
        }
        resumeCount--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    int size() {
        return resumeCount;
    }
}
