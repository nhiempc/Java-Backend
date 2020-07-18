package vn.plusplus.database;

import vn.plusplus.database.models.CounterEntity;
import vn.plusplus.database.models.StatisticEntity;
import vn.plusplus.database.services.CounterService;
import vn.plusplus.database.services.LaptopService;
import vn.plusplus.database.models.LaptopEntity;
import vn.plusplus.database.services.StatisticService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus", "root", "123456");
            System.out.println("SQL Connection to database established!");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        }
        DecimalFormat df = new DecimalFormat();
        System.out.println("Tìm kiếm:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Tìm kiếm latop theo khoảng giá");
        System.out.println("2. Tìm kiếm latop theo hãng");
        System.out.println("3. Tìm kiếm latop theo kích thước màn hình");
        System.out.println("4. Tìm kiếm latop theo cấu hình RAM");
        System.out.println("5. Tìm kiếm latop theo cấu hình CPU");
        System.out.println("6. Tìm kiếm latop theo loại máy tính");
        System.out.println("7. Tìm kiếm latop theo card màn hình");
        System.out.println("8. Sắp xếp tăng giảm");
        System.out.println("Nhập lựa chọn(mỗi lựa chọn cách nhau dấu cách): ");
        LaptopService laptopService = new LaptopService(connection);
        CounterService counterService = new CounterService(connection);
        StatisticService statisticService = new StatisticService(connection);
        String strOption = scanner.nextLine();
        String[] arrOption = strOption.split(" ");
        List<Integer> lstOption = new ArrayList<>();
        for(int i = 0; i< arrOption.length;i++){
            lstOption.add(Integer.parseInt(arrOption[i]));
        }
        Collections.sort(lstOption);
        List<LaptopEntity> laptopEntities = laptopService.findLaptop(lstOption);
        System.out.println("Kết quả tìm kiếm: ");
        if(laptopEntities==null||laptopEntities.isEmpty()){
            System.out.println("Không tìm thấy");
        }else{
            for(LaptopEntity result: laptopEntities){
                System.out.println("ID: "+result.getId()+" | Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+df.format(result.getPrice())+"");
            }
        }
        System.out.println();
        System.out.println("Top 5 máy tính bán chạy nhất:");
        List<LaptopEntity> topSold = laptopService.topSold();
        if(topSold==null||topSold.isEmpty()){
            System.out.println("Chưa có máy nào được bán!");
        }else{
            for(LaptopEntity result: topSold){
                System.out.println("ID: "+result.getId()+" | Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+df.format(result.getPrice())+" | Đã bán: "+result.getSold()+" máy");
            }
        }
        System.out.println();
        System.out.println("Thống kê hãng và số lượng");
        List<CounterEntity> statisticMakerAndQuantity = counterService.getCounterByMaker();
        if(statisticMakerAndQuantity==null||statisticMakerAndQuantity.isEmpty()){
            System.out.println("Không có dữ liệu");
        }else{
            for (CounterEntity result: statisticMakerAndQuantity){
                System.out.println("Tên hãng: "+result.getMaker()+" | Số lượng: "+result.getQuantity());
            }
        }
        System.out.println();
        System.out.println("Thống kê hãng và số tiền bán được");
        List<StatisticEntity> statisticMakerAndTotalSoldMoney = statisticService.getStatisticByMaker();
        if(statisticMakerAndTotalSoldMoney==null||statisticMakerAndTotalSoldMoney.isEmpty()){
            System.out.println("Không có dữ liệu");
        }else{
            for (StatisticEntity result: statisticMakerAndTotalSoldMoney){
                System.out.println("Tên hãng: "+result.getMaker()+" | Số lượng đã bán: "+result.getSold()+" | Số tiền bán được: "+df.format(result.getTotalMoney())+" vnđ");
            }
        }
        System.out.println();
        System.out.println("Nhập vào dữ liệu máy tính: ");
        LaptopEntity insert_laptop = new LaptopEntity("HP","https://www.hp.com","HP","EliteBook","4GB","Intel","128GB","512GB",10000000f,"AMD","1366x768",17f,12,null,null);
        List<LaptopEntity> laptop_added = laptopService.addLaptop(insert_laptop,insert_laptop.getName());
        System.out.println("Thông tin máy mới được thêm vào");
        if(laptop_added==null||laptop_added.isEmpty()){
            System.out.println("Không có dữ liệu");
        }else {
            for(LaptopEntity rs:laptop_added){
                System.out.println("Tên hãng: "+rs.getName()+" | Giá: "+df.format(rs.getPrice())+"vnđ | Số lượng đã bán: "+rs.getSold());
            }
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ID của sản phẩm: ");
        int id = sc.nextInt();
        System.out.println("Nhập vào số lượng đã bán tăng thêm: ");
        int quantity = sc.nextInt();
        System.out.println("Dữ liệu trước khi thay đổi: ");
        List<LaptopEntity> find_by_id = laptopService.findByID(id);
        if(find_by_id==null||find_by_id.isEmpty()){
            System.out.println("Không có dữ liệu");
        }else {
            for(LaptopEntity rs:find_by_id){
                System.out.println("ID: "+rs.getId()+" | Tên hãng: "+rs.getName()+" | Số lượng đã bán(mới): "+rs.getSold());
            }
        }
        List<LaptopEntity> update_sold = laptopService.updateSold(id,quantity);
        System.out.println("Dữ liệu sau khi thay đổi: ");
        if(update_sold==null||update_sold.isEmpty()){
            System.out.println("Không có dữ liệu");
        }else {
            for(LaptopEntity rs:update_sold){
                System.out.println("ID: "+rs.getId()+" | Tên hãng: "+rs.getName()+" | Số lượng đã bán(mới): "+rs.getSold());
            }
        }
    }
}
