package by.itstep.blog.entity;

import java.util.Objects;

public class Admin {

    private long adminId;
    private String name;
    private String email;
    private String password;

    public Admin() {}

    public Admin(long adminId, String name, String email, String password) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Admin admin = new Admin();

        public Builder setAdminId(long adminId) {
            admin.adminId = adminId;
            return this;
        }

        public Builder setName(String name) {
            admin.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            admin.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            admin.password = password;
            return this;
        }

        public Admin build() {
            return admin;
        }
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return adminId == admin.adminId && name.equals(admin.name) && email.equals(admin.email) && password.equals(admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, name, email, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
