package backend.isac.model;

import jakarta.persistence.Embeddable;
@Embeddable
public class Version {
    private int major;
    private int minor;
    private int patch;
    protected Version() {}
    public Version(int major, int minor, int patch) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException("Version numbers must be non-negative.");
        }
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public int getMajor() { return major; }
    public int getMinor() { return minor; }
    public int getPatch() { return patch; }
    public void setMajor(int major) { this.major = major; }
    public void setMinor(int minor) { this.minor = minor; }
    public void setPatch(int patch) { this.patch = patch; }
    public Version incrementMajor() { return new Version(major + 1, 0, 0); }
    public Version incrementMinor() { return new Version(major, minor + 1, 0); }
    public Version incrementPatch() { return new Version(major, minor, patch + 1); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return major == version.major && minor == version.minor && patch == version.patch;
    }
    @Override
    public String toString() { return major + "." + minor + "." + patch; }
}
