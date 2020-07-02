package vn.plusplus.database.services;

import vn.plusplus.database.models.LaptopEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopService {
    private Connection con;

    public LaptopService() {
    }

    public LaptopService(Connection connection) {
        this.con = connection;
    }
    public Float findMaxPrice(){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(price) from laptop");
            return rs.getFloat(1);
        }catch (Exception e){
            System.out.println("Error when find price max!");
        }
        return null;
    }
    public List<LaptopEntity> findAllByMaker(String maker) {
        try {
            List<LaptopEntity> responses = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM laptop WHERE maker='" + maker + "'");
            while (rs.next()) {
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                responses.add(laptopEntity);
            }
            return responses;
        } catch (Exception e) {
            System.out.println("Error when find laptop by maker. " + e);
        }
        return null;
    }
    public List<LaptopEntity> findAllByPrice(Float start,Float end){
        try{
            List<LaptopEntity> responses = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM laptop WHERE price BETWEEN '"+start+ "' AND '" +end+"'");
            while (rs.next()) {
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                responses.add(laptopEntity);
            }
            return responses;
        }catch (Exception e){
            System.out.println("Error when find laptop by price"+e);
        }
        return null;
    }
    public List<LaptopEntity> findAllByMakerAndSSD(String maker,String ssd){
        try{
            List<LaptopEntity> responses = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM laptop WHERE maker = '"+maker+ "' AND ssd = '" +ssd+"'");
            while (rs.next()) {
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                responses.add(laptopEntity);
            }
            return responses;
        }catch (Exception e){
            System.out.println("Error when find laptop by maker and SSD"+e);
        }
        return null;
    }
}
