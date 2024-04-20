package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//パスワードのハッシュ化クラス
public class PasswordHasher {

	/**
	 * 
	 * @param ユーザーが入力したパスワード
	 * @return ハッシュ化したパスワード
	 */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hashString = new StringBuilder();

            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }

            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String originalPassword = "mySecretPassword";
        String hashedPassword = hashPassword(originalPassword);
        System.out.println("Hashed password: " + hashedPassword);
    }
}
