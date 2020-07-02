package vn.plusplus.database;

import vn.plusplus.database.services.LaptopService;
import vn.plusplus.database.models.LaptopEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập lựa chọn:");
        System.out.println("1. Tìm kiếm latop theo hãng");
        System.out.println("2. Tìm kiếm latop theo giá");
        System.out.println("3. Tìm kiếm latop theo hãng và ổ SSD");
        LaptopService laptopService = new LaptopService(connection);

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.println("Nhập vào hãng:");
                String maker = scanner.nextLine();
                List<LaptopEntity> laptopEntities = laptopService.findAllByMaker(maker);
                if(laptopEntities == null || laptopEntities.isEmpty()){
                    System.out.println("Không tìm thấy");
                }else{
                    for(LaptopEntity result: laptopEntities){
                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
                    }
                }
                break;
            case 2:
                System.out.println("Nhập vào khoảng giá(VND): ");
                System.out.println("Giá min: ");
                Float priceFrom = scanner.nextFloat();
                System.out.println("Giá max: ");
                Float priceTo = scanner.nextFloat();
                if(priceFrom == null){
                    priceFrom = 0.0f;
                }else if(priceTo == null){
                    priceTo = laptopService.findMaxPrice();
                }else if(priceFrom == null && priceTo == null){
                    priceFrom = 0.0f;
                    priceTo = laptopService.findMaxPrice();
                }
                List<LaptopEntity> listFindByPrice = laptopService.findAllByPrice(priceFrom,priceTo);
                if(listFindByPrice == null || listFindByPrice.isEmpty()){
                    System.out.println("Không tìm thấy");
                }else{
                    for(LaptopEntity result: listFindByPrice){
                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
                    }
                }
                break;
            case 3:
                System.out.println("Nhập vào hãng:");
                String maker1 = scanner.nextLine();
                System.out.println("Nhập vào loại ổ cứng SSD(VD: 256GB):");
                String ssd = scanner.nextLine();
                List<LaptopEntity> listFindByMakerAndSSD = laptopService.findAllByMakerAndSSD(maker1,ssd);
                if(listFindByMakerAndSSD == null || listFindByMakerAndSSD.isEmpty()){
                    System.out.println("Không tìm thấy");
                }else{
                    for(LaptopEntity result: listFindByMakerAndSSD){
                        System.out.println("Tên sản phẩm: "+result.getName()+" | Giá sản phẩm: "+result.getPrice()+"");
                    }
                }
                break;
        }
    }
}
