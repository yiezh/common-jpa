package cn.enigma.project.jpa.test.service;

import cn.enigma.project.jpa.part.PartQuery;
import cn.enigma.project.jpa.test.dao.TestRepository;
import cn.enigma.project.jpa.test.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author luzh
 * Create: 2019/9/6 上午10:36
 * Modified By:
 * Description:
 */
@Service
public class TestService {

    @PersistenceContext
    private EntityManager entityManager;

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestOneBO> listOne() {
        Specification<TestEntity> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();
        return PartQuery.statisticsQuery(entityManager, TestOneBO.class, TestEntity.class, specification);
    }

    public TestEntity update(Integer id) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow(RuntimeException::new);
        testEntity.setColumnSix(LocalDateTime.now().toString());
        testEntity = testRepository.save(testEntity);
        return testEntity;
    }

    public TestEntity add() {
        String time = LocalDateTime.now().toString();
        TestEntity testEntity = new TestEntity("one-" + time, "two-"+ time, "three-" + time, "four-" + time, "five-" + time, "six-" + time);
        return testRepository.save(testEntity);
    }
}
