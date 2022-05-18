package App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
        private static final String  url = "D:\\workspace\\Dictionary-main\\models\\Binary Search Tree.txt";
        
	public static void main(String[] args){
                System.out.println("----[Dictionary - BTS]----");
                Scanner input = new Scanner(System.in);
                int n;
                String keyWord ;
                boolean exit = false;
                List<Node> nodesSearched = new ArrayList<>();
                List<Word> words = new ArrayList<>();
                BSTree tree = new BSTree();
                // Đọc dữ liệu ra từ file
                tree  = tree.readFormFile(url);
		// TODO Auto-generated method stub
                showMenu();
                while(true){
                    n = input.nextInt();
                    switch ( n ) {
                        case  1:
                            System.out.println("----Nhập các thông tin từ mới----");
                            Word word = Nhap(tree);
                            // ton tai word
                            if(word != null){
                                tree.insert(new Node(word));
                                System.out.println("Thêm từ mới thành công!");
                            }
                            else{
                                System.out.println("Từ đã tồn tại và đã được cập nhật");
                            }
                            break;
                        case  2:
                            System.out.println("----Nhập từ cần xóa----");
                            keyWord = input.next();
                            tree.delete(keyWord);
                            break;
                        case  3:
                            System.out.println("----Nhập từ cần tìm----");
                            keyWord = input.next();
                            Node nodeSearch = tree.search(keyWord);
                            if(nodeSearch == null){
                                // tìm những từ gần đúng
                                words = tree.searchNearest(keyWord);
                                if(words.size() > 0) {
									System.out.println("----Từ tìm không có!, Đây là những từ có liên quan----");
									for (Word o : words) {
									o.printInfo();
								}
                                }
                            }
                            else
                            {
                            	// Lưu danh sách từ đã tra
                                nodesSearched.add(nodeSearch);
                                System.out.println("----Thông tin từ cần tìm----");
                                nodeSearch.printData();
                            }
                            break;
                        case  4:    
                            System.out.println("----In cây theo Pre Order----");          
                            tree.printPreOrder();
                            break;
                        case  5:
                             System.out.println("----In cây theo In Order----");           
                            tree.printInOrder();
                            break;
                        case  6:
                            System.out.println("----In cây theo Post Order----");
                            tree.printPostOrder();
                            break;
                        case  7:
                            System.out.println("----Số lượng từ trong từ điển----");
                            System.out.println((tree.size));
                        break;
                        case  8:
                            if(nodesSearched.size() > 0){
                                System.out.println("----Danh sách từ đã tra----");
                                System.out.println(nodesSearched);
                            }
                            else{
                                System.out.println("Danh sách rỗng");
                            }
                        break;
                        case 0 :
                            System.out.println("exited!");
                            exit = true;
                            break;
                        default:
                            System.out.println("----Nhập số không hợp lệ! mời bạn nhập lại----");
                    }
                    if(exit){
                        System.out.println("----Bạn có muốn lưu dữ liệu mới hay không----");
                        System.out.println("----Nhập (số) tương ứng---- \n| 1 - lưu lại |\n| 2 - Hủy bỏ |");
                        int numberCheck = input.nextInt();
                        if(numberCheck == 1){
                            tree.writeToFile(url);
                            nodesSearched.clear();
                        }
                        else {
                            nodesSearched.clear();
                        }
                        // Ghi file
                        System.out.println("----Cảm ơn đã sử dụng ứng dụng----");
                        System.out.println(""
                      + "(¯`*•.¸,¤°´’`°¤,¸.•*´¯)\n" +
                        "    Cảm ơn rất nhiều \n" +
                        "  đã sử dụng ứng dụng\n" +
                        "\n" +
                        "(_¸.•*´’`°¤¸’¸¤°´’`*•.¸_)");
                        break;
                    }
                    showMenu();
                }
	
        }
        public static Word Nhap(BSTree tree){
            Scanner input = new Scanner(System.in);
            String wordNew, mean, pronounce; 
            System.out.println("-----Nhập Word(Từ)----");
            wordNew = input.nextLine();
            Node nodeExist = tree.search(wordNew);
            if(nodeExist != null) {
                Word wordExist = nodeExist.word;
                int next ;
                System.out.println("----Từ đã tồn tại muốn thêm ví dụ không cho từ " + wordNew + "----\n");
                System.out.println("----Chọn 1 đồng ý hoặc bất kì(trừ 1) để bỏ qua----\n");
                next = input.nextInt();
                switch(next){
                    case 1 : 
                        tree.update(nodeExist);
                    break;
                    default :
                }
                return null;
            }
            else{
                System.out.print("----Nhập Mean(Nghĩa của từ)----\n");
                mean = input.nextLine();
                System.out.println("----Nhập Pronounce(Phát âm)----\n");
                pronounce = input.nextLine();
                System.out.println("----Nhập số lượng Example(Ví dụ) tối đa 3 Example----\n");
                int sl = input.nextInt();
                input.nextLine();
                String[] example = new String[sl];
                System.out.println(example.length);
                for(int i = 0 ; i<example.length ; i++){
                    System.out.println("----Nhập ví dụ thứ : " + (i+1) + "----\n");
                    example[i] = input.nextLine();
                }
            
            return new Word(wordNew,mean,pronounce,example) ;
            }
            
        }
        public static void showMenu() {
            System.out.println("\n -------------MENU---------------");
            System.out.println("| 1 - Thêm từ mới                |");
            System.out.println("| 2 - Xóa từ                     |");
            System.out.println("| 3 - Tìm từ                     |");
            System.out.println("| 4 - In cây theo Pre Order      |");
            System.out.println("| 5 - In cây theo In Order       |");
            System.out.println("| 6 - In cây theo Post Order     |");
            System.out.println("| 7 - Số lượng từ trong từ điển  |");
            System.out.println("| 8 - Danh sách từ đã tra        |");
            System.out.println("| 0 - exit.                      |");
            System.out.println(" --------------------------------\n");
            System.out.println("----[Người dùng hãy nhập (số) tương ứng với chức năng cần dùng]----");
        }
}
// thêm ví dụ ........ success
// các từ gần giống ...... success
// lưu từ đã tra ......  success
// khi thoát hỏi lưu hay không  success
