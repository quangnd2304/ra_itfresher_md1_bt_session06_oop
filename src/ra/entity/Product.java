package ra.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int productIndex,
                          Categories[] arrCategories, int catalogIndex) {
        this.productId = inputProductId(scanner, arrProduct, productIndex);
        this.productName = inputProductName(scanner, arrProduct, productIndex);
        this.price = inputPrice(scanner);
        System.out.println("Nhập vào mô tả sản phẩm:");
        this.description = scanner.nextLine();
        this.created = inputCreated(scanner);
        this.catalogId = inputCatalogId(scanner, arrCategories, catalogIndex);
        this.productStatus = inputProductStatus(scanner);
    }

    public void displayData(Categories[] arrCategories, int catalogIndex) {
        System.out.printf("ProductId: %s - ProductName: %s - Price: %f\n", this.productId, this.productName, this.price);
        System.out.printf("Description: %s - Created: %s\n", this.description, this.created);//Convert Date --> String
        System.out.printf("CatalogName: %s - Status: %s\n", getCatalogNameById(arrCategories, catalogIndex, this.catalogId),
                (this.productStatus == 0) ? "Đang bán" : ((this.productStatus == 1) ? "Hết hàng" : "Không bán"));
    }

    public String getCatalogNameById(Categories[] arrCategories, int catalogIndex, int catalogId) {
        for (int i = 0; i < catalogIndex; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return arrCategories[i].getCatalogName();
            }
        }
        return null;
    }

    public String inputProductId(Scanner scanner, Product[] arrProduct, int productIndex) {
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String productId = scanner.nextLine();
            if (productId.length() == 4) {
                if (productId.charAt(0) == 'A' || productId.charAt(0) == 'S' || productId.charAt(0) == 'C') {
                    boolean isExist = false;
                    for (int i = 0; i < productIndex; i++) {
                        if (arrProduct[i].getProductId().equals(productId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                    } else {
                        return productId;
                    }
                } else {
                    System.err.println("Mã sản phẩm bắt đầu là C, S, A, vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sản phẩm gồm 4 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner, Product[] arrProduct, int productIndex) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (productName.length() >= 10 && productName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < productIndex; i++) {
                    if (arrProduct[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm có độ dài từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            float price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                return price;
            } else {
                System.err.println("Giá sản phẩm phải có giá trị lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public Date inputCreated(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo sản phẩm:");
        do {
            try {
                Date created = sdf.parse(scanner.nextLine());
                return created;
            } catch (Exception ex) {
                System.err.println("Không đúng định dạng ngày tháng, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputCatalogId(Scanner scanner, Categories[] arrCategories, int catalogIndex) {
        System.out.println("Chọn danh mục của sản phẩm:");
        for (int i = 0; i < catalogIndex; i++) {
            System.out.printf("%d.%s", i + 1, arrCategories[i].getCatalogName());
        }
        System.out.println("Lựa chọn của bạn:");
        int choice = Integer.parseInt(scanner.nextLine());
        return arrCategories[choice - 1].getCatalogId();
    }

    public int inputProductStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm:");
        do {
            int status = Integer.parseInt(scanner.nextLine());
            if (status == 0 || status == 1 || status == 2) {
                return status;
            } else {
                System.err.println("Trạng thái sản phẩm chỉ nhận 0,1,2, vui lòng nhập lại");
            }
        } while (true);
    }

}
