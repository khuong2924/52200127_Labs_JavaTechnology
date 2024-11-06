package khuong.bt.demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>, JpaRepository<Student, Long> {

    List<Student> findByAgeGreaterThanEqual(int age);
    List<Student> findByIeltsScoreEquals(double score);
    List<Student> findByNameContainingIgnoreCase(String name);
}
