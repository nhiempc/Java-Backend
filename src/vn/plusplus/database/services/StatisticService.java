package vn.plusplus.database.services;

import vn.plusplus.database.models.CounterEntity;
import vn.plusplus.database.models.StatisticEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticService {
    private Connection con;

    public StatisticService() {
    }

    public StatisticService(Connection con) {
        this.con = con;
    }

    private List<StatisticEntity> queryDatabase(String sql){
        List<StatisticEntity> statisticEntities = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                StatisticEntity statisticEntity = new StatisticEntity(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getFloat(3)
                );
                statisticEntities.add(statisticEntity);
            }
        }catch (Exception e) {
            System.out.println("Error when query!" + e);
        }
        return statisticEntities;
    }
    public List<StatisticEntity> getStatisticByMaker(){
        String sql = "select maker as hang ,sum(sold) as so_luong,sum(sold*price) as tien_ban_duoc from laptop group by maker";
        List<StatisticEntity> statisticEntities = queryDatabase(sql);
        return statisticEntities;
    }
}
