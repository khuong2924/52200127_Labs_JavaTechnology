package khuong.bt.demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>, JpaRepository<Student, Long> {


    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> byAge(@Param("age") int age);

    @Query("SELECT s FROM Student s WHERE s.ieltsScore = :score")
    List<Student> byScore(@Param("score") double score);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> byName(@Param("name") String name);
}
