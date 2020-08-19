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
        ArrayList<Book> books = null;
        Order order = new Order();
        ArrayList<OrderDetail> orderDetails = null;
        Boolean login = false;
        CustomerBL customerBL = new CustomerBL();
        OrderBL orderBL = new OrderBL();
        OrderDetailBL orderDetailBL = new OrderDetailBL();
        String choose;
        clearScreen();
        NextPage(0, 10, books, bookBL);
        int batdau = 0;
        do {
            if (batdau == 1) {
                clearScreen();
                NextPage(0, 10, books, bookBL);
                batdau = 0;
            }
            System.out.printf("\n[1..10: Chuyển trang], [0: Thoát], [C: Chọn sách], [D: Đặt hàng]: ");
            choose = sc.nextLine();
            switch (choose) {
                case "1":
                    clearScreen();
                    NextPage(0, 10, books, bookBL);
                    break;
                case "2":
                    clearScreen();
                    NextPage(10, 20, books, bookBL);
                    break;
                case "3":
                    clearScreen();
                    NextPage(20, 30, books, bookBL);
                    break;
                case "4":
                    clearScreen();
                    NextPage(30, 40, books, bookBL);
                    break;
                case "5":
                    clearScreen();
                    NextPage(40, 50, books, bookBL);
                    break;
                case "6":
                    clearScreen();
                    NextPage(50, 60, books, bookBL);
                    break;
                case "7":
                    clearScreen();
                    NextPage(60, 70, books, bookBL);
                    break;
                case "8":
                    clearScreen();
                    NextPage(70, 80, books, bookBL);
                    break;
                case "9":
                    clearScreen();
                    NextPage(80, 90, books, bookBL);
                    break;
                case "10":
                    clearScreen();
                    NextPage(90, 100, books, bookBL);
                    break;
                case "C":
                    try {
                        System.out.printf("Nhập ID sách: ");
                        int ID = Integer.parseInt(sc.nextLine());
                        Book book = bookBL.getBook(ID);
                        if (book != null) {
                            clearScreen();
                            XemChiTietSach(book);
                            System.out.printf("\n[Khác 2: Trở về], [2: Thêm vào giỏ hàng]: ");
                            String a = sc.nextLine();
                            if (a.equals("2")) {
                                int amount = 0;
                                try {

                                    System.out.printf("Số lượng: ");
                                    amount = Integer.parseInt(sc.nextLine());
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
                                        System.out.println("Thêm vào giỏ hàng thành công.");
                                    } else {
                                        System.out.println("#THÔNG BÁO: \"Vượt quá số lượng cho phép.\"");
                                    }
                                } catch (Exception e) {
                                    System.out.println("#THÔNG BÁO: \"Kí tự không hợp lệ!\"");
                                }
                                NhanPhimBatKi(sc);
                                batdau = 1;
                            } else {
                                batdau = 1;
                            }
                        } else {
                            System.out.println("#THÔNG BÁO: \"ID không tồn tại!\"");
                            NhanPhimBatKi(sc);
                            batdau = 1;
                        }
                    } catch (Exception e) {
                        System.out.println("Bạn nhập cái gì thế?");
                        NhanPhimBatKi(sc);
                        batdau = 1;
                    }

                    break;
                case "0":
                    clearScreen();
                    System.out.println("Goodluck!");
                    break;
                case "D":
                    clearScreen();
                    if (orderDetails == null) {
                        System.out.println("#THÔNG BÁO: \"Giỏ hàng của bạn hiện tại trống.\"");
                        NhanPhimBatKi(sc);
                        batdau = 1;
                    } else {
                        System.out.println("Giỏ hàng của bạn");
                        Line(123, "-");
                        System.out.printf("\n| %-4s | %-60s | %-10s | %-10s |\n", "STT", "Tên sách", "Giá", "Số lượng");
                        Line(123, "-");
                        Book b = null;
                        for (int i = 0; i < orderDetails.size(); i++) {
                            b = bookBL.getBook(orderDetails.get(i).getBookID());
                            System.out.printf("\n| %-4d | %-60s | %-10.3f | %-10d |\n", i, b.getTitle(), b.getPrice(),
                                    orderDetails.get(i).getAmount());
                            Line(123, "-");
                        }
                        System.out.printf("\nBạn có muốn đặt hàng không? [Y: Có], [Khác Y: Không]: ");
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
                                clearScreen();
                                InThongTinHoaDon(c, order, orderDetails, bookBL);
                            } else {
                                System.out.println("\nĐĂNG NHẬP");
                                System.out.printf("Email: ");
                                String Email = sc.nextLine();
                                System.out.printf("Mật khẩu: ");
                                String Password = sc.nextLine();
                                if (customerBL.Login(Email, Password)) {
                                    System.out.println("#THÔNG BÁO: \"Đăng nhập thành công!\"\n");
                                    Customer c = customerBL.getCustomer();
                                    order.setCustomerID(c.getID());
                                    orderBL.addOrder(order);
                                    order = orderBL.getOrder();
                                    for (int i = 0; i < orderDetails.size(); i++) {
                                        orderDetails.get(i).setOrderID(order.getID());
                                        orderDetailBL.addOrderDetail(orderDetails.get(i));
                                    }
                                    clearScreen();
                                    InThongTinHoaDon(c, order, orderDetails, bookBL);
                                    login = true;
                                } else {
                                    System.out.println("#THÔNG BÁO: \"Sai Email hoặc Mật khẩu!\"");
                                    NhanPhimBatKi(sc);
                                    batdau = 1;
                                }
                            }
                        } else {
                            batdau = 1;
                        }
                    }
                    break;
                default:
                    batdau = 1;
                    clearScreen();
                    System.out.println("#THÔNG BÁO: \"Không có kết quả phù hợp với lựa chọn của bạn!\"");
                    NhanPhimBatKi(sc);
                    break;
            }
        } while (!choose.equals("0"));
        sc.close();
    }

    private static void NhanPhimBatKi(Scanner sc) {
        System.out.println("Nhấn Enter để trở về trang chủ!");
        sc.nextLine();
    }

    private static void Line(int lenght, String symbol) {
        for (int i = 0; i < lenght; i++) {
            System.out.printf(symbol);
        }
    }

    private static void NextPage(int from, int to, ArrayList<Book> books, BookBL bookBL) {
        books = bookBL.getList();
        Line(117, "=");
        System.out.printf("\n| %-4s | %-60s | %-30s | %-10s |\n", "ID", "TÊN SÁCH", "TÁC GIẢ", "GIÁ");
        Line(117, "=");
        for (int i = from; i < to; i++) {
            System.out.printf("\n| %-4d | %-60s | %-30s | %-9.3f₫ |\n", books.get(i).getID(), books.get(i).getTitle(),
                    books.get(i).getAuthor(), books.get(i).getPrice());
            Line(117, "-");
        }
        System.out.println();
        if (from == 0 && to == 10) {
            System.out.println("[1] - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 10 && to == 20) {
            System.out.println("1 - [2] - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 20 && to == 30) {
            System.out.println("1 - 2 - [3] - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 30 && to == 40) {
            System.out.println("1 - 2 - 3 - [4] - 5 - 6 - 7 - 8 - 9 - 10");
        } else if (from == 40 && to == 50) {
            System.out.println("1 - 2 - 3 - 4 - [5] - 6 - 7 - 8 - 9 - 10");
        } else if (from == 50 && to == 60) {
            System.out.println("1 - 2 - 3 - 4 - 5 - [6] - 7 - 8 - 9 - 10");
        } else if (from == 60 && to == 70) {
            System.out.println("1 - 2 - 3 - 4 - 5 - 6 - [7] - 8 - 9 - 10");
        } else if (from == 70 && to == 80) {
            System.out.println("1 - 2 - 3 - 4 - 5 - 6 - 7 - [8] - 9 - 10");
        } else if (from == 80 && to == 90) {
            System.out.println("1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - [9] - 10");
        } else if (from == 90 && to == 100) {
            System.out.println("1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - [10]");
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void InThongTinHoaDon(Customer customer, Order order, ArrayList<OrderDetail> orderDetails,
            BookBL bookBL) {
        try {
            Line(141, "*");
            System.out.println("\nTHÔNG TIN HÓA ĐƠN");
            System.out.println("Khách hàng:    " + customer.getName());
            System.out.println("Email:         " + customer.getEmail());
            System.out.println("Số điện thoại: " + customer.getPhone());
            System.out.println("Địa chỉ:       " + customer.getAddress());
            System.out.println("Ngày in:       " + order.getDate());
            Line(141, "-");
            System.out.printf("\n| %-4s | %-60s | %-10s | %-10s | %-15s |\n", "STT", "Tên sách", "Giá", "Số lượng",
                    "Tạm tính");
            Line(141, "-");
            Double thanhtien = 0.0;
            Book l = new Book();
            for (int i = 0; i < orderDetails.size(); i++) {
                try {
                    l = bookBL.getBook(orderDetails.get(i).getBookID());
                } catch (SQLException e) {
                    System.out.println("#THÔNG BÁO: \"Đã xảy ra lỗi!\"");
                }
                System.out.printf("\n| %-4d | %-60s | %-9.3f₫ | %-10d | %-14.3f₫ |\n", i, l.getTitle(), l.getPrice(),
                        orderDetails.get(i).getAmount(), l.getPrice() * orderDetails.get(i).getAmount());
                thanhtien = thanhtien + (l.getPrice() * orderDetails.get(i).getAmount());
                Line(141, "-");
            }
            System.out.printf("\nThành tiền: %.3f₫", thanhtien);
            System.out.println();
            Line(141, "*");
        } catch (Exception e) {
            System.out.println("#THÔNG BÁO: \"Đã xảy ra lỗi! Đặt hàng thất bại.\"");
        }
        orderDetails.clear();
        orderDetails = null;
    }

    private static void XemChiTietSach(Book book) {
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
    }

}
