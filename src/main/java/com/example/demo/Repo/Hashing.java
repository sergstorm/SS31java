package com.example.demo.Repo;

public interface Hashing
{
    static String hash(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    static boolean verificar(String password ,String hash)
    {
        return BCrypt.checkpw(password, hash);
    }
}
