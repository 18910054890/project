package com.lyzd.om.spring.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author GM
 *
 */
public class PasswordEncoderImpl implements PasswordEncoder{



	@Override
	public String encode(CharSequence rawPassword) {
		String pwd = new BCryptPasswordEncoder().encode(rawPassword);
//		 String salt;
//	        if (this.strength > 0) {
//	            if (this.random != null) {
//	                salt = BCrypt.gensalt(this.strength, this.random);
//	            } else {
//	                salt = BCrypt.gensalt(this.strength);
//	            }
//	        } else {
//	            salt = BCrypt.gensalt();
//	        }
//
//	        return BCrypt.hashpw(rawPassword.toString(), salt);
		return pwd;
	}
//
//	public static String gensalt(int log_rounds, SecureRandom random) {
//        if (log_rounds >= 4 && log_rounds <= 31) {
//            StringBuilder rs = new StringBuilder();
//            byte[] rnd = new byte[16];
//            random.nextBytes(rnd);
//            rs.append("$2a$");
//            if (log_rounds < 10) {
//                rs.append("0");
//            }
//            
//            rs.append(log_rounds);
//            rs.append("$");
//            encode_base64(rnd, rnd.length, rs);
//            return rs.toString();
//        } else {
//            throw new IllegalArgumentException("Bad number of rounds");
//        }
//    }
//	
//	
//	//混淆密码hashpw()
//	public static String hashpw(String password, String salt) throws IllegalArgumentException {
//	        char minor = 0;
//	        int off = false;
//	        StringBuilder rs = new StringBuilder();
//	        if (salt == null) {
//	            throw new IllegalArgumentException("salt cannot be null");
//	        } else {
//	            int saltLength = salt.length();
//	            if (saltLength < 28) {
//	                throw new IllegalArgumentException("Invalid salt");
//	            } else if (salt.charAt(0) == '$' && salt.charAt(1) == '2') {
//	                byte off;
//	                if (salt.charAt(2) == '$') {
//	                    off = 3;
//	                } else {
//	                    minor = salt.charAt(2);
//	                    if (minor != 'a' || salt.charAt(3) != '$') {
//	                        throw new IllegalArgumentException("Invalid salt revision");
//	                    }
//
//	                    off = 4;
//	                }
//
//	                if (saltLength - off < 25) {
//	                    throw new IllegalArgumentException("Invalid salt");
//	                } else if (salt.charAt(off + 2) > '$') {
//	                    throw new IllegalArgumentException("Missing salt rounds");
//	                } else {
//	                    int rounds = Integer.parseInt(salt.substring(off, off + 2));
//	                    String real_salt = salt.substring(off + 3, off + 25);
//
//	                    byte[] passwordb;
//	                    try {
//	                        passwordb = (password + (minor >= 'a' ? "\u0000" : "")).getBytes("UTF-8");
//	                    } catch (UnsupportedEncodingException var13) {
//	                        throw new AssertionError("UTF-8 is not supported");
//	                    }
//
//	                    byte[] saltb = decode_base64(real_salt, 16);
//	                    BCrypt B = new BCrypt();
//	                    byte[] hashed = B.crypt_raw(passwordb, saltb, rounds);
//	                    rs.append("$2");
//	                    if (minor >= 'a') {
//	                        rs.append(minor);
//	                    }
//
//	                    rs.append("$");
//	                    if (rounds < 10) {
//	                        rs.append("0");
//	                    }
//
//	                    rs.append(rounds);
//	                    rs.append("$");
//	                    encode_base64(saltb, saltb.length, rs);
//	                    encode_base64(hashed, bf_crypt_ciphertext.length * 4 - 1, rs);
//	                    return rs.toString();
//	                }
//	            } else {
//	                throw new IllegalArgumentException("Invalid salt version");
//	            }
//	        }
//	    }

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
