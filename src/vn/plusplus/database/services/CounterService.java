package vn.plusplus.database.services;

import vn.plusplus.database.models.CounterEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CounterService{
    private Connection con;

    public CounterService() {
    }

    public CounterService(Connection con) {
        this.con = con;
    }
    private List<CounterEntity> queryDatabase(String sql){
        List<CounterEntity> counterEntities = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                CounterEntity counterEntity = new CounterEntity(
                        rs.getString(1),
                        rs.getInt(2)
                );
                counterEntities.add(counterEntity);
            }
        }catch (Exception e) {
            System.out.println("Error when query!" + e);
        }
        return counterEntities;
    }
    public List<CounterEntity> getCounterByMaker(){
        String sql = "SELECT maker,count(maker) as quantity FROM laptop GROUP BY maker ORDER BY quantity DESC";
        List<CounterEntity> counterEntities = queryDatabase(sql);
        return counterEntities;
    }
}
