import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class Main {
    Categories[] arrCategories = new Categories[100];
    Product[] arrProduct = new Product[100];
    int catalogIndex = 0;
    int productIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        do {
            System.out.println("*********************SHOP MENU******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    main.displayMenu(scanner, main);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập 1-3");
            }
        } while (true);
    }

    public void displayMenu(Scanner scanner, Main main) {
        boolean isExit = true;
        do {
            System.out.println("***************CATEGORIES MANAGEMENT**************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    main.inputListCategories(scanner);
                    break;
                case 2:
                    main.displayListCategories();
                    ;
                    break;
                case 3:
                    main.updateCategories(scanner);
                    break;
                case 4:
                    main.deleteCatalog(scanner);
                    break;
                case 5:
                    main.updateCatalogStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public void inputListCategories(Scanner scanner) {
        System.out.println("Nhập vào số lượng danh mục cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrCategories[catalogIndex] = new Categories();
            arrCategories[catalogIndex].inputData(scanner, arrCategories, catalogIndex);
            catalogIndex++;
        }
    }

    public void displayListCategories() {
        System.out.println("THÔNG TIN CÁC DANH MỤC:");
        for (int i = 0; i < catalogIndex; i++) {
            arrCategories[i].displayData();
        }
    }

    public void updateCategories(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        //1. Kiểm tra mã có tồn tại không
        //2. Tồn tại mới thực hiện cập nhật
        int indexUpdate = getIndexById(catalogId);
        if (indexUpdate >= 0) {
            //Tồn tại
            arrCategories[indexUpdate].updateCatalog(scanner, arrCategories, catalogIndex, indexUpdate);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public void deleteCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogIdDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(catalogIdDelete);
        if (indexDelete >= 0) {
            //1. Kiểm tra trong danh mục chứa sản phẩm chưa
            boolean hasProduct = false;//danh mục chưa chứa sản phẩm
            for (int i = 0; i < productIndex; i++) {
                if (arrProduct[i].getCatalogId() == catalogIdDelete) {
                    hasProduct = true;
                    break;
                }
            }
            //2. Nếu chưa chứa sản phẩm --> xóa
            if (!hasProduct) {
                //Tiến hành xóa
                for (int i = indexDelete; i < catalogIndex; i++) {
                    arrCategories[i] = arrCategories[i + 1];
                }
                arrCategories[catalogIndex - 1] = null;
                catalogIndex--;
            } else {
                System.err.println("Danh mục đã chứa sản phẩm, không thể xóa được");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public void updateCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = getIndexById(catalogId);
        if (indexUpdateStatus >= 0) {
            arrCategories[indexUpdateStatus].setCatalogStatus(!arrCategories[indexUpdateStatus].isCatalogStatus());
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public int getIndexById(int catalogId) {
        for (int i = 0; i < catalogIndex; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }
}
