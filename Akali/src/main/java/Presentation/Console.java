package Presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import BusinessLogic.BookBL;
import BusinessLogic.CustomerBL;
import BusinessLogic.OrderBL;
import BusinessLogic.OrderDetailBL;
import Entity.Book;
import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;

public class Console {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        BookBL bookBL = new BookBL();
        ArrayList<Book> books = bookBL.getList();
        Order order = new Order();
        ArrayList<OrderDetail> orderDetails = null;
        Boolean login = false;
        CustomerBL customerBL = new CustomerBL();
        OrderBL orderBL = new OrderBL();
        OrderDetailBL orderDetailBL = new OrderDetailBL();
        String a;
        String choose;
        clearScreen();
        NextPage(0, 10, books);
        int batdau = 0;
        do {
            if (batdau == 1) {
                clearScreen();
                NextPage(0, 10, books);
                batdau = 0;
            }
            System.out.printf("\n[1..10: Chuyển trang], [0: Thoát], [C: Chọn sách], [D: Đặt hàng]: ");
            choose = sc.nextLine();
            switch (choose) {
                case "1":
                    clearScreen();
                    NextPage(0, 10, books);
                    break;
                case "2":
                    clearScreen();
                    NextPage(10, 20, books);
                    break;
                case "3":
                    clearScreen();
                    NextPage(20, 30, books);
                    break;
                case "4":
                    clearScreen();
                    NextPage(30, 40, books);
                    break;
                case "5":
                    clearScreen();
                    NextPage(40, 50, books);
                    break;
                case "6":
                    clearScreen();
                    NextPage(50, 60, books);
                    break;
                case "7":
                    clearScreen();
                    NextPage(60, 70, books);
                    break;
                case "8":
                    clearScreen();
                    NextPage(70, 80, books);
                    break;
                case "9":
                    clearScreen();
                    NextPage(80, 90, books);
                    break;
                case "10":
                    clearScreen();
                    NextPage(90, 100, books);
                    break;
                case "C":

                    System.out.printf("Nhập ID sách: ");
                    int ID = Integer.parseInt(sc.nextLine());
                    Book book = bookBL.getBook(ID);

                    if (book != null) {
                        clearScreen();
                        System.out.println("\nTHÔNG TIN CHI TIẾT SÁCH");
                        Line(100, "*");
                        System.out.println("\nTiêu đề:           " + book.getTitle());
                        System.out.println("Tác giả:           " + book.getAuthor());
                        System.out.printf("Giá bán:           %.3f₫\n", book.getPrice());
                        System.out.println("Công ty phát hành: " + book.getIssuingCompany());
                        System.out.println("Ngày xuất bản:     " + book.getDateofPublication());
                        System.out.println("Kích thước:        " + book.getDimensions());
                        System.out.println("Loại bìa:          " + book.getCoverType());
                        System.out.println("Số trang:          " + book.getNumberofPages());
                        System.out.println("SKU:               " + book.getSKU());
                        System.out.println("Nhà xuất bản:      " + book.getPublishingCompany());
                        Line(100, "*");
                        System.out.printf("\n[1: Trở về], [2: Thêm vào giỏ hàng]: ");
                        a = sc.nextLine();
                        if (a.equals("2")) {
                            System.out.printf("Số lượng (1..99): ");
                            int amount = Integer.parseInt(sc.nextLine());
                            if (amount > 0 && amount <= 99) {
                                if (orderDetails == null) {
                                    orderDetails = new ArrayList<OrderDetail>();
                                }
                                int check = 0;
                                for (int i = 0; i < orderDetails.size(); i++) {
                                    if (ID == orderDetails.get(i).getBookID()) {
                                        orderDetails.get(i).setAmount(orderDetails.get(i).getAmount() + amount);
                                        check = 1;
                                    }
                                }
                                if (check == 0) {
                                    OrderDetail oDetail = new OrderDetail();
                                    oDetail.setAmount(amount);
                                    oDetail.setBookID(ID);
                                    oDetail.setOrderID(order.getID());
                                    orderDetails.add(oDetail);
                                }
                                System.out.println("Thêm vào giỏ hàng thành công!");
                                System.out.println("Nhấn phím bất kì để trở về!");
                                a = sc.nextLine();
                                batdau = 1;
                            } else {
                                System.out.println("Số lượng chỉ được phép từ 1 đến 99.");
                                System.out.println("Nhấn phím bất kì để trở về!");
                                a = sc.nextLine();
                                batdau = 1;
                            }
                        } else {
                            batdau = 1;
                        }
                    } else {
                        System.out.println("Không tìm thấy cuốn sách bạn vừa nhập...");
                        System.out.println("Nhấn phím bất kì để trở về!");
                        a = sc.nextLine();
                        batdau = 1;
                    }
                    break;
                case "0":
                    clearScreen();
                    System.out.println("Xin chào và hẹn gặp lại...");
                    break;
                case "D":
                    clearScreen();
                    if (orderDetails == null) {
                        System.out.println("Giỏ hàng hiện tại trống...(Nhấn phím bất kì để trở về)");
                        a = sc.nextLine();
                        batdau = 1;
                    } else {
                        System.out.println("Giỏ hàng của bạn");
                        Line(123, "-");
                        System.out.printf("\n| %-10s | %-80s | %-10s | %-10s |\n", "STT", "Tên sách", "Giá",
                                "Số lượng");
                        Line(123, "-");

                        Book b = null;
                        for (int i = 0; i < orderDetails.size(); i++) {
                            b = bookBL.getBook(orderDetails.get(i).getBookID());
                            System.out.printf("\n| %-10d | %-80s | %-10.3f | %-10d |\n", i, b.getTitle(), b.getPrice(),
                                    orderDetails.get(i).getAmount());
                            Line(123, "-");
                        }
                        System.out.printf("\nBạn có muốn đặt hàng không? (Yes or No): ");
                        String yn = sc.nextLine();
                        if (yn.equals("Yes") || yn.equals("yes") || yn.equals("Y") || yn.equals("y")) {
                            if (login) {

                                Customer c = customerBL.getCustomer();
                                order.setCustomerID(c.getID());
                                orderBL.addOrder(order);

                                order = orderBL.getOrder();
                                for (int i = 0; i < orderDetails.size(); i++) {
                                    orderDetails.get(i).setOrderID(order.getID());
                                    orderDetailBL.addOrderDetail(orderDetails.get(i));
                                }

                                Line(140, "*");
                                System.out.println("\nTHÔNG TIN HÓA ĐƠN");
                                System.out.println("Khách hàng:    " + c.getName());
                                System.out.println("Email:         " + c.getEmail());
                                System.out.println("Số điện thoại: " + c.getPhone());
                                System.out.println("Địa chỉ:       " + c.getAddress());
                                System.out.println("Ngày in:       " + order.getDate());
                                Line(140, "-");
                                System.out.printf("\n| %-10s | %-80s | %-10s | %-10s | %-15s |\n", "STT", "Tên sách",
                                        "Giá", "Số lượng", "Tạm tính");
                                Line(140, "-");
                                Double thanhtien = 0.0;
                                for (int i = 0; i < orderDetails.size(); i++) {
                                    Book l = bookBL.getBook(orderDetails.get(i).getBookID());
                                    System.out.printf("\n| %-10d | %-80s | %-9.3f₫ | %-10d | %-14.3f₫ |\n", i,
                                            l.getTitle(), l.getPrice(), orderDetails.get(i).getAmount(),
                                            l.getPrice() * orderDetails.get(i).getAmount());
                                    thanhtien = thanhtien + (l.getPrice() * orderDetails.get(i).getAmount());
                                    Line(140, "-");
                                }
                                System.out.printf("\nThành tiền: %.3f₫", thanhtien);
                                System.out.println();
                                Line(140, "*");
                                orderDetails.clear();
                            } else {
                                Line(140, "*");
                                System.out.println("\nĐĂNG NHẬP");
                                System.out.printf("Email: ");
                                String Email = sc.nextLine();
                                System.out.printf("Password: ");
                                String Password = sc.nextLine();
                                if (customerBL.Login(Email, Password)) {
                                    System.out.println("Đăng nhập thành công!\n");
                                    Line(140, "*");
                                    Customer c = customerBL.getCustomer();
                                    order.setCustomerID(c.getID());
                                    orderBL.addOrder(order);

                                    order = orderBL.getOrder();
                                    for (int i = 0; i < orderDetails.size(); i++) {
                                        orderDetails.get(i).setOrderID(order.getID());
                                        orderDetailBL.addOrderDetail(orderDetails.get(i));
                                    }
                                    clearScreen();
                                    Line(141, "*");
                                    System.out.println("\nTHÔNG TIN HÓA ĐƠN");
                                    System.out.println("Khách hàng: " + c.getName());
                                    System.out.println("Email: " + c.getEmail());
                                    System.out.println("Số điện thoại: " + c.getPhone());
                                    System.out.println("Địa chỉ: " + c.getAddress());
                                    System.out.println("Ngày in: " + order.getDate());
                                    Line(141, "-");
                                    System.out.printf("\n| %-10s | %-80s | %-10s | %-10s | %-15s |\n", "STT",
                                            "Tên sách", "Giá", "Số lượng", "Tạm tính");
                                    Line(141, "-");
                                    Double thanhtien = 0.0;
                                    for (int i = 0; i < orderDetails.size(); i++) {
                                        Book l = bookBL.getBook(orderDetails.get(i).getBookID());
                                        System.out.printf("\n| %-10d | %-80s | %-9.3f₫ | %-10d | %-14.3f₫ |\n", i,
                                                l.getTitle(), l.getPrice(), orderDetails.get(i).getAmount(),
                                                l.getPrice() * orderDetails.get(i).getAmount());
                                        thanhtien = thanhtien + (l.getPrice() * orderDetails.get(i).getAmount());
                                        Line(141, "-");
                                    }
                                    System.out.printf("\nThành tiền: %.3f₫", thanhtien);
                                    System.out.println();
                                    Line(141, "*");
                                    orderDetails.clear();
                                    orderDetails = null;
                                    login = true;
                                } else {
                                    System.out.println("Sai tài khoản hoặc mật khẩu!!!");
                                    System.out.println("Nhấn phím bất kì để trở về!");
                                    a = sc.nextLine();
                                }
                            }
                        } else{
                            batdau = 1;
                        }
                    }
                    break;
                default:
                    batdau = 1;
                    clearScreen();
                    System.out.println("Bạn nhập lại đi...");
                    System.out.println("Nhấn phím bất kì để trở về!");
                    a = sc.nextLine();
                    break;
            }
        } while (!choose.equals("0"));
        sc.close();
    }

    private static void Line(int lenght, String symbol) {
        for (int i = 0; i < lenght; i++) {
            System.out.printf(symbol);
        }
    }

    private static void NextPage(int from, int to, ArrayList<Book> books) {
        System.out.println("CHÀO MỪNG BẠN ĐẾN VỚI AKALI STOREE");
        Line(143, "=");
        System.out.printf("\n| %-10s | %-80s | %-30s | %-10s |\n", "ID", "TÊN SÁCH", "TÁC GIẢ", "GIÁ");
        Line(143, "=");
        for (int i = from; i < to; i++) {
            System.out.printf("\n| %-10d | %-80s | %-30s | %-9.3f₫ |\n", books.get(i).getID(), books.get(i).getTitle(),
                    books.get(i).getAuthor(), books.get(i).getPrice());
            Line(143, "-");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
