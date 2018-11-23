package controller;

import java.util.List;

import javax.faces.context.FacesContext;

import DAO.UserDAO;
import model.User;

public class UserController {
	private UserDAO db = new UserDAO();
	
    public User isUsuarioReadyToLogin(String login, String pass) {
        try {
        		login = login.toLowerCase().trim();

        		List<User> query = db.getUserAutentication(login);

        		if (query.size() == 1) {
        			if ( query.get(0).getPassword().equals(pass)){
            	   //if ( query.get(0).getPassword().equals(convertStringToMd5(pass))){
        				User userFound = (User) query.get(0);
        				//Set user object in session
        				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggeduser", userFound);
        				
        				return userFound;
        			}
        		}
        	return null;
        	} catch (Exception e) {
               e.printStackTrace();
        }
		return null;
	}	
    
/*
    private String convertStringToMd5(String value) {
        MessageDigest mDigest;
        try { 
               //Instanciamos o nosso HASH MD5, poderíamos usar outro como
               //SHA, por exemplo, mas optamos por MD5.
               mDigest = MessageDigest.getInstance("MD5");
               
               //Convert a String valor para um array de bytes em MD5
               byte[] valorMD5 = mDigest.digest(value.getBytes("UTF-8"));
               
               //Convertemos os bytes para hexadecimal, assim podemos salvar
               //no banco para posterior comparação se senhas
               StringBuffer sb = new StringBuffer();
               for (byte b : valorMD5){
                      sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
               }

               return sb.toString();
               
        } catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
               return null;
        } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
               return null;
        }
    }   */    
}
