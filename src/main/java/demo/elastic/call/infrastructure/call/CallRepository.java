package demo.elastic.call.infrastructure.call;

import demo.elastic.call.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Long>, CallRepositoryCustom{

}
