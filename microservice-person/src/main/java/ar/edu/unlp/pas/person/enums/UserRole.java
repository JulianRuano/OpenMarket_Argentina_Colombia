package ar.edu.unlp.pas.person.enums;

public enum UserRole {

    ADMIN(1),
    USER(2),
    SELLER(3),
    DELIVERER(4);

    private Integer role;

    UserRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return this.role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
