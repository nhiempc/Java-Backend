package vn.plusplus.database;

import vn.plusplus.database.services.LaptopService;
import vn.plusplus.database.models.LaptopEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        String strOption = scanner.nextLine();
        String[] arrOption = strOption.split(" ");
        List<Integer> lstOption = new ArrayList<>();
        for(int i = 0; i< arrOption.length;i++){
            lstOption.add(Integer.parseInt(arrOption[i]));
        }
        Collections.sort(lstOption);
        List<LaptopEntity> laptopEntities = laptopService.findLaptop(lstOption);
        if(laptopEntities==null||laptopEntities.isEmpty()){
            System.out.println("Không tìm thấy");
        }else{
            for(LaptopEntity result: laptopEntities){
                System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
            }
        }
//        switch (option){
//            case 1:
//                System.out.println("Nhập vào hãng:");
//                String maker = scanner.nextLine();
//                List<LaptopEntity> laptopEntities = laptopService.findAllByMaker(maker);
//                if(laptopEntities == null || laptopEntities.isEmpty()){
//                    System.out.println("Không tìm thấy");
//                }else{
//                    for(LaptopEntity result: laptopEntities){
//                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
//                    }
//                }
//                break;
//            case 2:
//                System.out.println("Nhập vào khoảng giá(VND): ");
//                System.out.println("Giá min: ");
//                Float priceFrom = scanner.nextFloat();
//                System.out.println("Giá max: ");
//                Float priceTo = scanner.nextFloat();
//                if(priceFrom == null){
//                    priceFrom = 0.0f;
//                }else if(priceTo == null){
//                    priceTo = laptopService.findMaxPrice();
//                }else if(priceFrom == null && priceTo == null){
//                    priceFrom = 0.0f;
//                    priceTo = laptopService.findMaxPrice();
//                }
//                List<LaptopEntity> listFindByPrice = laptopService.findAllByPrice(priceFrom,priceTo);
//                if(listFindByPrice == null || listFindByPrice.isEmpty()){
//                    System.out.println("Không tìm thấy");
//                }else{
//                    for(LaptopEntity result: listFindByPrice){
//                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
//                    }
//                }
//                break;
//            case 3:
//                System.out.println("Nhập vào hãng:");
//                String maker1 = scanner.nextLine();
//                System.out.println("Nhập vào loại ổ cứng SSD(VD: 256GB):");
//                String ssd = scanner.nextLine();
//                List<LaptopEntity> listFindByMakerAndSSD = laptopService.findAllByMakerAndSSD(maker1,ssd);
//                if(listFindByMakerAndSSD == null || listFindByMakerAndSSD.isEmpty()){
//                    System.out.println("Không tìm thấy");
//                }else{
//                    for(LaptopEntity result: listFindByMakerAndSSD){
//                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
//                    }
//                }
//                break;
//        }
    }
}
