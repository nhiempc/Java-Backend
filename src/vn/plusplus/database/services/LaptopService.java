package vn.plusplus.database.services;

import vn.plusplus.database.models.LaptopEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private List<LaptopEntity> queryDatabase(String sql){
        List<LaptopEntity> laptopEntities = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                LaptopEntity laptopEntity = new LaptopEntity(
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
                laptopEntities.add(laptopEntity);
            }
        }catch (Exception e) {
            System.out.println("Error when query!" + e);
        }
        return laptopEntities;
    }
    public List<LaptopEntity> topSold(){
        String sql = "SELECT * FROM laptop ORDER BY sold desc LIMIT 5";
        List<LaptopEntity> laptopEntities = queryDatabase(sql);
        return laptopEntities;
    }
    public List<LaptopEntity> findLaptop(List<Integer> lstOptions){
        Scanner scanner = new Scanner(System.in);
        String sql ="SELECT * FROM laptop WHERE ";
        for(int i = 0; i< lstOptions.size();i++){
            if(lstOptions.get(i)==1){
                Scanner sc = new Scanner(System.in);
                System.out.println("Nhập vào giá min: ");
                Float priceFrom = sc.nextFloat();
                System.out.println("Nhập vào giá max: ");
                Float priceTo = sc.nextFloat();
                if(priceFrom == null){
                    priceFrom = 0.0f;
                }else if(priceTo == null){
                    priceTo = findMaxPrice();
                }else if(priceFrom == null && priceTo == null){
                    priceFrom = 0.0f;
                    priceTo = findMaxPrice();
                }
            sql+= "price BETWEEN '"+priceFrom+ "' AND '" +priceTo+"'";
            }else if(lstOptions.get(i)==2){
                System.out.println("Nhập vào hãng: ");
                String maker = scanner.nextLine();
                sql+= "maker LIKE '%" + maker + "%'";
            }else if(lstOptions.get(i)==3){
                System.out.println("Nhập vào kích thước màn hình: ");
                String screenSize = scanner.nextLine();
                sql+= "screen_size LIKE '%" + screenSize + "%'";
            }else if(lstOptions.get(i)==4){
                System.out.println("Nhập vào dung lượng RAM: ");
                String ram = scanner.nextLine();
                sql+= "ram LIKE '%" + ram + "%'";
            }else if(lstOptions.get(i)==5){
                System.out.println("Nhập vào cấu hình CPU: ");
                String cpu = scanner.nextLine();
                sql+= "cpu LIKE '%" + cpu + "%'";
            }else if(lstOptions.get(i)==6){
                System.out.println("Nhập vào loại máy tính: ");
                String type = scanner.nextLine();
                sql+= "type LIKE '%" + type + "%'";
            }else if(lstOptions.get(i)==7){
                System.out.println("Nhập vào card màn hình: ");
                String card = scanner.nextLine();
                sql+= "card LIKE '%" + card + "%'";
            }else if(lstOptions.get(i)==8){
                System.out.println("Nhập vào kiểu sắp xếp(DESC or ASC):");
                String sort = scanner.nextLine();
                sql+= " ORDER BY price "+sort;
            }
            if(lstOptions.size()==1||lstOptions.get(i)==8||lstOptions.size()==2&&lstOptions.contains(8)||lstOptions.size()==2&&!lstOptions.contains(8)&&i==1||lstOptions.size()>2&&i==lstOptions.size()-2&&lstOptions.contains(8)||i==lstOptions.size()-1&&lstOptions.size()>2&&!lstOptions.contains(8)){
                sql+="";
            }else{
                sql+=" AND ";
            }
        }
        List<LaptopEntity> laptopEntities = queryDatabase(sql);
        return laptopEntities;
    }
    public List<LaptopEntity> findAllByMaker(String maker) {
        String sql = "SELECT * FROM laptop WHERE maker='" + maker + "'";
        List<LaptopEntity> laptopEntities = queryDatabase(sql);
        return laptopEntities;
    }
    public List<LaptopEntity> findAllByPrice(Float start,Float end){
        String sql = "SELECT * FROM laptop WHERE price BETWEEN '"+start+ "' AND '" +end+"'";
        List<LaptopEntity> laptopEntities = queryDatabase(sql);
        return laptopEntities;
    }
    public List<LaptopEntity> findAllByMakerAndSSD(String maker,String ssd){
        String sql = "SELECT * FROM laptop WHERE maker = '"+maker+ "' AND ssd = '" +ssd+"'";
        List<LaptopEntity> laptopEntities = queryDatabase(sql);
        return laptopEntities;
    }
    public List<LaptopEntity> addLaptop(LaptopEntity laptopEntity, String name){
        String sql_insert = "INSERT IGNORE INTO laptop(`name`, `url`, `maker`, `type`, `ram`, `cpu`, `ssd`, `hdd`, `price`, `card`, `screen_resolution`, `screen_size`, `sold`) VALUES('"+laptopEntity.getName()+"','"+laptopEntity.getUrl()+"','"+laptopEntity.getMaker()+"','"+laptopEntity.getType()+"','"+laptopEntity.getRam()+"','"+laptopEntity.getCpu()+"','"+laptopEntity.getSsd()+"','"+laptopEntity.getHdd()+"','"+laptopEntity.getPrice()+"','"+laptopEntity.getCard()+"','"+laptopEntity.getScreenResolution()+"','"+laptopEntity.getScreenSize()+"','"+laptopEntity.getSold()+"')";
        try{
            Statement stmt = con.createStatement();
            stmt.execute(sql_insert);
        }catch (Exception e){
            System.out.println("Error when insert laptop! "+e);
        }
        String sql_find_name = "SELECT * FROM laptop WHERE name = '"+name+"' ";
        List<LaptopEntity> laptopEntity1 = queryDatabase(sql_find_name);
        return laptopEntity1;
    }
}
