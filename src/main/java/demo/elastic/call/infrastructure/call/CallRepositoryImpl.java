package demo.elastic.call.infrastructure.call;

import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.elastic.call.domain.Call;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CallRepositoryImpl implements CallRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

//    public Call save(Call call){
//        jpaQueryFactory.insert(call);
//    }

}
