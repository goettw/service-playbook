package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import serviceplaybook.model.Comment;
import serviceplaybook.model.MongoLocalEntity;

public interface CommentRepository extends PagingAndSortingRepository<Comment, String>{
List<Comment> findCommentsByLocalEntity(MongoLocalEntity localEntity, Sort sort);
}
