package ar.edu.unlp.pas.cart.services.storage;

public class TokenStorage {
    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void clearToken() {
        tokenThreadLocal.remove();
    }
}
