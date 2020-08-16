package BusinessLogic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import DataAccess.CustomerDA;
import Entity.Customer;

public class CustomerBL {
    CustomerDA customerDAL = new CustomerDA();
    Customer c;

    public boolean Login(String Email, String Password) throws SQLException {
        Password = MD5(Password);
        c = customerDAL.getCustomer(Email);
        if (c != null) {
            if (c.getPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer() {
        return c;
    }

    private String MD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}