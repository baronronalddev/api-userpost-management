package com.bancom.ms.domain.util;

public class UserPostUtil {

    public static String messageCreatedUser(Long id) {
        return "Usuario creado con id : " + id.toString();
    }

    public static String messageCreatedPost(Long id) {
        return "Post creado con id : " + id.toString();
    }

    public static String messageUpdateUser(Long id) {
        return "Usuario modificado con id : " + id.toString();
    }

    public static String messageUpdatePost(Long id) {
        return "Post modificado con id : " + id.toString();
    }

    public static String messageDeleteUser(Long id) {
        return "Usuario eliminado con id : " + id.toString();
    }

    public static String messageDeletePost(Long id) {
        return "Post eliminado con id : " + id.toString();
    }

}
