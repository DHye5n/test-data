package dh.javaproject.testdata.repository;

import dh.javaproject.testdata.domain.MockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockDataRepository extends JpaRepository<MockData, Long> {

}
