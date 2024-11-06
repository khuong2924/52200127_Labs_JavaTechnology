package khuong.bt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			//add
			studentRepository.save(new Student(null, "Nguyen A", 20, "a@gmail.com", 6.5));
			studentRepository.save(new Student(null, "Tran B", 22, "b@gmail.com", 7.0));
			studentRepository.save(new Student(null, "Le C", 21, "c@gmail.com", 8.0));

			// get
			List<Student> students = (List<Student>) studentRepository.findAll();
			System.out.println("Danh sách sinh viên:");
			students.forEach(student -> System.out.println(student));

			// put
			Student studentToUpdate = students.get(0);
			studentToUpdate.setAge(21);
			studentRepository.save(studentToUpdate);
			System.out.println("Thông tin sau khi cập nhật:");
			System.out.println(studentToUpdate);

			// delete
			studentRepository.deleteById(students.get(1).getId());
			System.out.println("Danh sách sinh viên sau khi xóa:");
			studentRepository.findAll().forEach(System.out::println);
		};
	}
}

