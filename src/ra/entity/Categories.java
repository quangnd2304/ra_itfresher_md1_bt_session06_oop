package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int index) {
        this.catalogId = generateIdentityValue(arrCategories, index);
        this.catalogName = inputCatalogName(scanner, arrCategories, index, -1);
        this.description = inputDescription(scanner);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    public void displayData() {
        System.out.printf("CatalogId: %d - CatalogName: %s - Description: %s - Status: %s\n",
                this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Active" : "Inactive");

    }

    public int generateIdentityValue(Categories[] arrCategories, int index) {
        //Sinh ra mã tự động --> max + 1
        if (index == 0) {
            return 1;
        } else {
            int max = arrCategories[0].getCatalogId();
            for (int i = 1; i < index; i++) {
                if (arrCategories[i].getCatalogId() > max) {
                    max = arrCategories[i].getCatalogId();
                }
            }
            return max + 1;
        }
    }

    public String inputCatalogName(Scanner scanner, Categories[] arrCategories, int index, int indexUpdate) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.length() <= 50) {
                boolean isExist = false;//Chưa tồn tại
                for (int i = 0; i < index; i++) {
                    if (arrCategories[i].getCatalogName().equals(catalogName) && i != indexUpdate) {
                        isExist = true;//Bị trùng
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogName;
                }
            } else {
                System.err.println("Tên danh mục có độ dài tối đa 50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner){
        System.out.println("Nhập mô tả danh mục:");
        return scanner.nextLine();
    }

    public void updateCatalog(Scanner scanner, Categories[] arrCategories, int catalogIndex, int indexUpdate) {
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên danh mục");
            System.out.println("2. Cập nhật mô tả danh mục");
            System.out.println("3. Cập nhật trạng thái danh mục");
            System.out.println("4. Thoát");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    this.catalogName = inputCatalogName(scanner, arrCategories, catalogIndex, indexUpdate);
                    break;
                case 2:
                    this.description = inputDescription(scanner);
                    break;
                case 3:
                    this.catalogStatus = inputCatalogStatus(scanner);
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }

}
