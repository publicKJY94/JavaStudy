package team3_1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Book {
	
   private int id;// key
   private String publisher;// 출판사
   private String writer;// 작가
   private int price;// 가격
   private String bookTitle;// 책제목
   private String genre;// 장르
   private int inventory;// 재고

   public Book(int id, String publisher, String writer, int price, String bookTitle, String genre, int inventory) {
      this.id = id;
      this.publisher = publisher;
      this.writer = writer;
      this.price = price;
      this.bookTitle = bookTitle;
      this.genre = genre;
      this.inventory = inventory;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getInventory() {
      return inventory;
   }

//   public void setInventory(int inventory) {
//      this.inventory = inventory;
//   }

   public String getPublisher() {
      return publisher;
   }

   public String getWriter() {
      return writer;
   }

   public String getBookTitle() {
      return bookTitle;
   }

   public String getGenre() {
      return genre;
   }

   public void sell() {// 판매
      this.inventory--;

   }

   public void addInventory(int inventory) {// 재고 추가
      this.inventory += inventory;
   }

   public int getId() {
      return id;
   }

   public void printInfo() {
      System.out.println(this.bookTitle + " " + this.price + " " + this.writer);
   }

   @Override
   public String toString() {
      return "제목 : " + this.bookTitle + "\n출판사 : " + this.publisher + "\n작가 : " + this.writer + "\n가격 : " + this.price
            + "\n장르 : " + this.genre + "\n재고 : " + this.inventory + "\n";
   }

}

class Comic extends Book {

   public Comic(int id, String publisher, String writer, int price, String bookTitle, int inventory) {
      super(id, publisher, writer, price, bookTitle, "코믹", inventory);
   }

}

class Novel extends Book {
   private String conte;// 장/단편 소설

   public Novel(int id, String publisher, String writer, int price, String bookTitle, int inventory, String conte) {
      super(id, publisher, writer, price, bookTitle, "소설", inventory);
      this.conte = conte;
   }

   public String getConte() {
      return conte;
   }

   @Override
   public String toString() {
      return "제목 : " + getBookTitle() + "\n장/단편 : " + this.conte + "\n출판사 : " + getPublisher() + "\n작가 : "
            + getWriter() + "\n가격 : " + getPrice() + "\n장르 : " + getGenre() + "\n재고 : " + getInventory() + "\n";
   }
}

public class BookStore {

   static Scanner sc = new Scanner(System.in);

   public static int dummy(ArrayList<Book> book, int id) {
      book.add(new Comic(id++, "A", "박현구", 1000, "마블", 10));
      book.add(new Novel(id++, "A", "박현구", 1000, "어린왕자", 20, "장편"));
      book.add(new Comic(id++, "A", "박현민", 2000, "집", 10));
      book.add(new Novel(id++, "A", "박현민", 3000, "마가자", 20, "단편"));
      book.add(new Comic(id++, "B", "노승현", 1000, "나는", 10));
      book.add(new Novel(id++, "B", "노승현", 1000, "잔다", 20, "장편"));
      book.add(new Comic(id++, "B", "박현민", 2000, "수고", 10));
      book.add(new Novel(id++, "B", "박현민", 3000, "안녕", 20, "단편"));
      return id;
   }

   public static void notFound() { // 검색결과 없음
      System.out.println("검색결과가 없습니다.");
   }

   public static Book instanceCreate(int PK, String type) {
      String publisher;// 출판사
      String writer;// 작가
      int price;// 가격
      String bookTitle;// 책제목
      int inventory;// 재고
      int actionConte;// 장-단
      String conte = "";
      System.out.println(type + "을 추가합니다.");
      System.out.print("책제목 : ");
      bookTitle = sc.next();

      if (type.equals("소설")) {
         System.out.println("1.장편 2.단편");
         System.out.print(">>> ");
         actionConte = numCk(1, 2);
         if (actionConte == 1)
            conte = "장편";
         else if (actionConte == 2)
            conte = "단편";
      }
      System.out.print("작가 : ");
      writer = sc.next();
      System.out.print("가격 : ");
      price = minCk();
      System.out.print("출판사 : ");
      publisher = sc.next();
      System.out.print("재고 : ");
      inventory = minCk();
      if (type.equals("코믹")) {
         return new Comic(PK, publisher, writer, price, bookTitle, inventory);
      } else if (type.equals("소설")) {
         return new Novel(PK, publisher, writer, price, bookTitle, inventory, conte);
      }
      return null;
   }

   public static void bookAdd(ArrayList<Book> book, int PK) {
      int actionAddBook = genreMenu();
      if (actionAddBook == 1) {// 코믹 추가
         book.add(instanceCreate(PK++, "코믹"));
      } else if (actionAddBook == 2) {
         book.add(instanceCreate(PK++, "소설"));
      }
   }

   public static int genreMenu() {
      System.out.println("1. 코믹");
      System.out.println("2. 소설");
      System.out.print("장르를 선택해주세요 : ");
      return numCk(1, 2);
   }

   public static int detail(ArrayList<Book> searchList) { // 상세보기
      System.out.println("0. 돌아가기");
      System.out.print("책 선택 : ");
      int action = numCk(0, searchList.size());

      if (action == 0) { // 돌아가기
         return -1;
      }
      System.out.println(searchList.get(action - 1));
      return searchList.get(action - 1).getId(); // PK값
   }

   public static int searchTitle(ArrayList<Book> book, ArrayList<Book> searchList) { // 제목 검색
      boolean flag = false;

      System.out.print("검색하고 싶은 제목을 입력하세요 : ");
      String search = sc.next();
      for (int i = 0; i < book.size(); i++) {
         if (book.get(i).getBookTitle().contains(search)) {
            System.out.print(searchList.size() + 1 + ". ");
            book.get(i).printInfo();
            searchList.add(book.get(i));
            flag = true;
         }
      }
      int pkValue = 0;
      if (!flag) {
         notFound();
      } else {
         pkValue = detail(searchList);
      }
      return pkValue;
   }

   public static int searchWriter(ArrayList<Book> book, ArrayList<Book> searchList) { // 작가 검색
      boolean flag = false;
      System.out.print("검색하고 싶은 작가를 입력하세요 : ");
      String search = sc.next();
      for (int i = 0; i < book.size(); i++) {
         if (book.get(i).getWriter().contains(search)) {
            System.out.print(searchList.size() + 1 + ". ");
            book.get(i).printInfo();
            searchList.add(book.get(i));
            flag = true;
         }
      }
      int pkValue = 0;
      if (!flag) {
         notFound();
      } else {
         pkValue = detail(searchList);
      }
      return pkValue;
   }

   public static int searchPublisher(ArrayList<Book> book, ArrayList<Book> searchList) { // 출판사 검색
      boolean flag = false;
      System.out.print("검색하고 싶은 출판사를 입력하세요 : ");
      String search = sc.next();
      for (int i = 0; i < book.size(); i++) {
         if (book.get(i).getPublisher().contains(search)) {
            System.out.print(searchList.size() + 1 + ". ");
            book.get(i).printInfo();
            searchList.add(book.get(i));
            flag = true;
         }
      }
      int pkValue = 0;
      if (!flag) {
         notFound();
      } else {
         pkValue = detail(searchList);
      }
      return pkValue;
   }

   public static int searchGenre(ArrayList<Book> book, ArrayList<Book> searchList) { // 장르 검색
      boolean flag = false;
      System.out.println("검색하실 장르를 선택하세요");
      int option = genreMenu();
      if (option == 1) {
         for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getGenre().equals("코믹")) {
               System.out.print(searchList.size() + 1 + ". ");
               book.get(i).printInfo();
               searchList.add(book.get(i));
               flag = true;
            }
         }
      } else if (option == 2) {
         for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getGenre().equals("소설")) {
               System.out.print(searchList.size() + 1 + ". ");
               book.get(i).printInfo();
               searchList.add(book.get(i));
               flag = true;
            }
         }

      }
      int pkValue = 0;
      if (!flag) {
         notFound();
      } else {
         pkValue = detail(searchList);
      }
      return pkValue;
   }

   public static int search(ArrayList<Book> book, ArrayList<Book> searchList) { // 전체 검색
      boolean flag = false;
      for (int i = 0; i < book.size(); i++) {
         System.out.print(searchList.size() + 1 + ". ");
         book.get(i).printInfo();
         searchList.add(book.get(i));
         flag = true;
      }
      int pkValue = 0;
      if (!flag) {
         notFound();
      } else {
         pkValue = detail(searchList);
      }
      return pkValue;
   }

   public static int sellBook(int pkValue, ArrayList<Book> searchList) {
      int totalBookPrice = 0; // 구매할 책의 총 가격
      Book book = null;
      while (true) {
         for (Book data : searchList) {
            if (data.getId() == pkValue) {
               System.out.println("이 책의 가격은 " + data.getPrice() + "원 입니다.");
               book = data;
            }
         }
         if (book.getInventory() <= 0) {
            System.out.println("재고가 부족하여 구매가 불가능합니다.");
            break;
         } else {
            System.out.println("구매하시겠습니까? 1.예   / 2. 아니오 ");
            System.out.print(">>> ");
            int action = numCk(1, 2);
            if (action == 2) {
               break;
            } else if (action == 1) {
               System.out.println("현재 구매 가능 개수 : " + book.getInventory());
               System.out.println("몇 개 구매하시겠습니까?");
               System.out.print(">>> ");
               int cnt = numCk(1, book.getInventory()); // 책 구매 개수

               totalBookPrice = cnt * (book.getPrice());
               System.out.println(
                     "선택하신 " + book.getBookTitle() + "책 " + cnt + "개는 " + "총 " + totalBookPrice + "원 입니다.");

               System.out.println("얼마를 내시겠습니까?");
               System.out.print(">>> ");
               int money = minCk();
               if (money < totalBookPrice) { // 돈이 부족한 상황
                  System.out.println("돈이 부족합니다.");
                  continue;
               } else {
                  System.out.println("구매 완료되었습니다.");
                  System.out.println("총 금액 : " + totalBookPrice + "원");
                  System.out.println("내신 금액 : " + money + "원");
                  int change = money - totalBookPrice; // 거스름돈
                  System.out.println("거스름돈 : " + change + "원");
                  for (int i = 0; i < cnt; i++) { // 구매한 개수만큼 재고 감소
                     book.sell();
                  }
                  break;
               }
            }
         }
      }
      return totalBookPrice;

   }

   public static int numCk(int min, int max) {

      int input = -1;
      while (true) {
         try {
            input = sc.nextInt();
            sc.nextLine();
         } catch (Exception e) {
            sc.nextLine();
         }

         if (min <= input && input <= max) {
            break;
         }
         System.out.println("입력이 잘못 되었습니다");
         // System.out.println(min + "~" + max + " 사이의 숫자를 입력해 주세요");
         System.out.print(">>> ");
      }
      return input;
   }

   public static int minCk() {
      int input = -1;
      while (true) {
         try {
            input = sc.nextInt();
            sc.nextLine();
         } catch (Exception e) {
            sc.nextLine();
         }

         if (0 <= input) {
            break;
         }
         System.out.println("입력이 잘못 되었습니다");
         System.out.println("0이상의 정수 입력해 주세요");
         System.out.print(">>> ");
      }
      return input;
   }

   public static int mainMenu() {
      int action = -1;
      System.out.println("=====도서 프로그램=======");
      System.out.println("1. 책 검색 및 구매");
      System.out.println("2. 장르별 베스트셀러");
      System.out.println("0. 프로그램 종료");
      System.out.print("메뉴를 선택하세요 : ");
      while (true) {
         try {
            action = sc.nextInt();
            sc.nextLine();
         } catch (Exception e) {
            System.out.println("정수를 입력하세요!");
            sc.nextLine();
         }
         if (0 <= action && action <= 2 || action == 19991001) {
            break;
         }
         System.out.println("입력이 잘못 되었습니다");
         System.out.println("0~2 사이의 숫자를 입력해 주세요");
         System.out.print(">>> ");
      }
      return action;
   }

   public static int searchMenu(ArrayList<Book> book, ArrayList<Book> searchList) {
      int pkValue = -1;
      int actionBookSearch;
      if (book.size() <= 0) {
         System.out.println("등록된 책이 없습니다");
         return pkValue;
      }
      System.out.println("======도서 검색======");
      System.out.println("1. 제목 검색");
      System.out.println("2. 작가 검색");
      System.out.println("3. 출판사 검색");
      System.out.println("4. 장르 검색");
      System.out.println("5. 전체 검색");
      System.out.println("0. 돌아가기");
      System.out.print("검색할 방식을 선택해주세요 : ");
      actionBookSearch = numCk(0, 5);
      if (actionBookSearch == 0) { // 0. 돌아가기

      } else if (actionBookSearch == 1) { // 1. 제목 검색
         pkValue = searchTitle(book, searchList);

      } else if (actionBookSearch == 2) { // 2. 작가 검색
         pkValue = searchWriter(book, searchList);

      } else if (actionBookSearch == 3) { // 3. 출판사 검색
         pkValue = searchPublisher(book, searchList);

      } else if (actionBookSearch == 4) { // 4. 장르 검색
         pkValue = searchGenre(book, searchList);

      } else if (actionBookSearch == 5) { // 5. 전체 검색
         pkValue = search(book, searchList);

      }
      return pkValue;
   }

   public static int managerMenu() {
      System.out.println("======관리자 모드======");
      System.out.println("1. 책 등록");
      System.out.println("2. 총 매출");
      System.out.println("3. 전체 검색");
      System.out.println("4. 책 관리");
      System.out.println("5. 책 폐기");
      System.out.println("6. 베스트셀러 선정하기");
      System.out.println("0. 관리자모드 종료");
      System.out.print("메뉴를 선택하세요 : ");
      int actionManager = numCk(0, 6); // 관리자모드 메뉴 선택
      return actionManager;

   }

   public static void bookDelete(ArrayList<Book> book, ArrayList<Book> searchList, ArrayList<Book> bestSeller) {
      int pkValue = searchMenu(book, searchList);
      if (pkValue > 0) {
         System.out.println("폐기하시겠습니까? 1. yes 2. no");
         System.out.print(">>> ");
         int actionDelete = numCk(1, 2);
         if (actionDelete == 1) {
            for (int i = 0; i < book.size(); i++) {
               if (book.get(i).getId() == pkValue) {
                  book.remove(i);
                  System.out.println("책 폐기 완료");

               }
            }
            for (int i = 0; i < bestSeller.size(); i++) {
               if (bestSeller.get(i).getId() == pkValue) {
                  bestSeller.remove(i);
               }
            }

         } else if (actionDelete == 2) {
            System.out.println("폐기를 취소합니다");
         }
      }
   }

   public static void bookUpdate(ArrayList<Book> searchList, int pkValue) {
      System.out.println("1. 재고 추가  2. 가격 변경");
      System.out.print(">>> ");
      int option, actionBookManegeOption = numCk(1, 2);
      if (actionBookManegeOption == 1) {
         System.out.print("추가할 재고 입력 : ");
         option = minCk();
         for (Book data : searchList) {
            if (data.getId() == pkValue) {
               data.addInventory(option);
            }
         }
      } else if (actionBookManegeOption == 2) {
         System.out.print("가격 입력 : ");
         option = minCk();
         for (Book data : searchList) {
            if (data.getId() == pkValue) {
               data.setPrice(option);
            }
         }
      }
   }

   public static void bestSellerAdd(ArrayList<Book> book, ArrayList<Book> searchList, ArrayList<Book> bestSeller) {
      int pkValue = 0;
      int cnt = 0;
      String type = null;
      Random rand = new Random();
      // 6. 베스트셀러 추출
      System.out.println("1. 랜덤으로 뽑기");// 1.랜덤으로 뽑기
      System.out.println("2. 직접 뽑기");// 2.직접뽑기
      System.out.print(">>> ");
      int actionBestSeller = numCk(1, 2);
      if (actionBestSeller == 1) {// 랜덤으로 뽑기 선택시
         int option = genreMenu();
         if (option == 1) {
            type = "코믹";
         } else if (option == 2) {
            type = "소설";
         }
         for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getGenre().equals(type)) {
               searchList.add(book.get(i));
               cnt++;
            }
         }
         int i=0;
         int duplicateCnt=cnt;
         while (true) {
            
            int randNum= rand.nextInt(cnt);
            Boolean flag=true;
            pkValue = searchList.get(randNum).getId();
            for (i=0;i<bestSeller.size();i++) {
               if (bestSeller.get(i).getId() == pkValue) {
                  duplicateCnt--;
                  flag=false;
               }
            }
            if(duplicateCnt<0) {
               pkValue=-1;
               System.out.println("배스트셀러로 선정 할 수 있는 책이 없습니다");
               break;
            }
            if(flag) {
               break;
            }
         }
      } else if (actionBestSeller == 2) {// 직접 뽑기 선택시
         pkValue = searchMenu(book, searchList);// 검색
      }

      if (pkValue > 0) {// 베스트셀러 등록
         boolean flag = true;
         for (Book data : bestSeller) {
            if (data.getId() == pkValue) {
               System.out.println("이미 " + data.getBookTitle() + "은(는) 베스트셀러입니다");
               flag = false;
            }

         }
         if (flag) {
            for (Book data : book) {
               if (data.getId() == pkValue) {
                  System.out.println(data.getBookTitle() + "이(가) 베스트셀러로 선정 되었습니다 ");
                  bestSeller.add(data);
               }
            }
         }
      }
   }

   public static void main(String[] args) {
      int PK = 1;
      int action = -1, actionManager; // 메뉴 선택 action
      int pkValue = -1; // detail함수 -> searchList PK값
      int totalSales = 0; // 총 매출
      ArrayList<Book> book = new ArrayList<Book>();
      ArrayList<Book> searchList = new ArrayList<Book>();
      ArrayList<Book> bestSeller = new ArrayList<Book>();
      PK = dummy(book, PK);

      while (true) {
         action = mainMenu();
         if (action == 0) { // 프로그램 종료
            System.out.println("프로그램이 종료되었습니다.");
            break;
         } else if (action == 1) { // 1. 책 검색 및 구매
            pkValue = searchMenu(book, searchList);
            if (pkValue > 0) {
               totalSales += sellBook(pkValue, searchList);
            }
         } else if (action == 2) { // 2. 장르별 베스트셀러
            searchGenre(bestSeller, searchList);
         } else if (action == 19991001) { // 관리자모드
            while (true) {
               actionManager = managerMenu();
               if (actionManager == 0) { // 관리자모드 종료
                  break;
               } else if (actionManager == 1) { // 1. 책 등록[관리자]
                  bookAdd(book, PK);
               } else if (actionManager == 2) { // 2. 총 매출[관리자]
                  System.out.println("총 매출은 " + totalSales + "원 입니다.");
               } else if (actionManager == 3) { // 3. 전체 검색[관리자]
                  search(book, searchList);
               } else if (actionManager == 4) { // 4. 책 관리[관리자]
                  pkValue = searchMenu(book, searchList);
                  if (pkValue > 0) {
                     bookUpdate(searchList, pkValue);
                  }
               } else if (actionManager == 5) { // 5. 책 폐기[관리자]
                  bookDelete(book, searchList, bestSeller);
               } else if (actionManager == 6) { // 6. 베스트셀러 선정[관리자]
                  bestSellerAdd(book, searchList, bestSeller);
               }
               searchList.clear();
            }
         }
         searchList.clear();
      }

   }
}