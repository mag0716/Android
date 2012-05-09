package m.k.android.sample.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import android.text.TextUtils;
import android.util.Base64;

/**
 * 暗号・復号化ユーティリティクラス
 *
 */
public class CryptUtils {
	
	/**
	 * 暗号化の実行
	 * @param str
	 * @param key
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String execEncrypted(String str, Key key) throws NoSuchAlgorithmException, 
																	NoSuchPaddingException, 
																	InvalidKeyException, 
																	IllegalBlockSizeException, 
																	BadPaddingException, 
																	UnsupportedEncodingException {
		String encryptedStr = null;
		
		if(!TextUtils.isEmpty(str) && key != null) {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
			
			encryptedStr = Base64.encodeToString(encrypted, Base64.DEFAULT);
		}
		
		return encryptedStr;
	}
	
	/**
	 * 復号化の実行
	 * @param str
	 * @param key
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String execDecrypted(String str, Key key) throws NoSuchAlgorithmException, 
																	NoSuchPaddingException, 
																	InvalidKeyException, 
																	IllegalBlockSizeException, 
																	BadPaddingException, 
																	UnsupportedEncodingException {
		String decryptedStr = null;
		
		if(!TextUtils.isEmpty(str) && key != null) {
			byte[] encByte = Base64.decode(str, Base64.DEFAULT);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypted =  cipher.doFinal(encByte);
			
			decryptedStr = new String(decrypted, "UTF-8");
		}
		
		return decryptedStr;
	}
	
	/**
	 * キーの生成
	 * @param keyStr
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static SecretKey generateKey(final String keyStr, final byte[] salt) throws NoSuchAlgorithmException, 
																						InvalidKeySpecException {
		SecretKey key = null;
		if(!TextUtils.isEmpty(keyStr) && salt != null) {
			char[] keyChar = keyStr.toCharArray();
			KeySpec keySpec = new PBEKeySpec(keyChar, salt, 1024, 256);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC");
			key = factory.generateSecret(keySpec);
		}
		return key;
	}
}
