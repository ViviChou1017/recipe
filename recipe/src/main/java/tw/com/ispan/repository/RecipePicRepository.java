package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.dao.RecipePicDao;
import tw.com.ispan.domain.RecipePicBean;


@Repository
public interface RecipePicRepository extends JpaRepository<RecipePicBean, Integer>, RecipePicDao {

}
