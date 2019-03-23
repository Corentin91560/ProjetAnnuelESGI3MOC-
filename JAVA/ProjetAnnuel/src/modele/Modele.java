package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

public class Modele {

	public static String verifConnexion(String login,String mdp){
		String droits="";
		String requete="select count(*) as nb from user where email='"+login+"' and password='"+encryptPassword(mdp)+"' and status=9";
		Bdd uneBdd=new Bdd("localhost","neige","root","");
		try{
			uneBdd.seConnecter();
			Statement unStat=uneBdd.getMaConnexion().createStatement();
			ResultSet unRes=unStat.executeQuery(requete);
			System.out.println(unRes);
			if (unRes.next()){
				int nb = unRes.getInt("nb");
				if(nb>0){
					droits = "admin";
				}
			}
			unStat.close();
			unRes.close();
			uneBdd.seDeconnexter();
		}catch (SQLException exp){
			System.out.println("Erreur: "+requete);
			
		}
		return droits;
	}
	
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}

	
	
}
