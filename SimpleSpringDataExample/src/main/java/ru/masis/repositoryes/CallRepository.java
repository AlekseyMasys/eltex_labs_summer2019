package ru.masis.repositoryes;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.masis.entities.Call;

import java.util.Date;
import java.util.List;

public interface CallRepository extends MongoRepository<Call, String> {
    List<Call> findByUser1Id(int user1Id);
    List<Call> findByDate(Date date);
}
