import java.util.List;

public class Program {
    public static void main(String[] args) {
        
        // 1. khởi tạo danh sách sinh viên
        System.out.println("Cau 1: ");
        List<Student> list = StudentUtils.generate();
        StudentUtils.print(list);

        // 2. sắp xếp theo tên và in ra kết quả
        System.out.println("Cau 2: ");
        StudentUtils.sortByName(list);
        StudentUtils.print(list);

        // 3. sắp xếp tăng dần theo điểm trung bình và in ra kết quả
        System.out.println("Cau 3: ");
        StudentUtils.sortByAvg(list);
        StudentUtils.print(list);

     
        // @TODO: sắp xếp giảm dần theo tuổi rồi in kết quả
        System.out.println("Cau 4: ");
        StudentUtils.sortByAgeDescending(list);
        StudentUtils.print(list);
       
        // @TODO: tính điểm trung bình của toàn bộ danh sách rồi in kết quả
        System.out.println("Cau 5: ");
        System.out.print("Diem trung binh la: ");
        System.out.println(StudentUtils.avg(list));
       
        // @TODO: lấy danh sách TÊN học sinh giỏi rồi in kết quả
        System.out.println("Cau 6: ");
        StudentUtils.goodStudentName(list);
        StudentUtils.print(list);
    }
}
