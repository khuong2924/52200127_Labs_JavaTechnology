package khuong.bt.demo;

import khuong.bt.demo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			studentRepository.save(new Student(null, "Nguyen A", 20, "a@gmail.com", 6.5));
			studentRepository.save(new Student(null, "Tran B", 22, "b@gmail.com", 7.0));
			studentRepository.save(new Student(null, "Le C", 21, "c@gmail.com", 8.0));
			studentRepository.save(new Student(null, "Khuong Ne", 19, "kkk@gmail.com", 10.0));

			Pageable pageable = PageRequest.of(0, 10); // 10 SV đầu tiên
			Page<Student> students = studentRepository.findByOrderByAgeDescIeltsScoreAsc(pageable);
			System.out.println("List sinh viên (sắp xếp theo tuổi giảm dần và IELTS tăng dần):");
			students.forEach(student -> System.out.println(student));

			Pageable pageable4to6 = PageRequest.of(0, 3); // 3 SV từ chỉ mục 4 đến 6
			Page<Student> students4to6 = studentRepository.findStudentsBy(pageable4to6);
			System.out.println("\nSinh viên từ 4-5-6:");
			students4to6.forEach(student -> System.out.println(student));
		};
	}
}
