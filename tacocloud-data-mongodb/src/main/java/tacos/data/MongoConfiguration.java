//package tacos.data;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//@Configuration
//@EnableReactiveMongoRepositories
//public class MongoConfiguration {
//
//    private final MongoMappingContext mongoMappingContext;
//
//    public MongoConfiguration(MongoMappingContext mongoMappingContext) {
//        this.mongoMappingContext = mongoMappingContext;
//    }
//
//    @Bean
//    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(MongoClient mongoClient) {
//        return new SimpleReactiveMongoDatabaseFactory(mongoClient, "taeng");
//    }
//
//    @Bean
//    public MappingMongoConverter reactiveMappingMongoConverter() {
//        MappingMongoConverter converter = new MappingMongoConverter(ReactiveMongoTemplate.NO_OP_REF_RESOLVER,
//                mongoMappingContext);
//
//        // 핵심은 이 부분으로, '_class' 필드를 제거하는 설정이다.
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        return converter;
//    }
//
//    @Bean
//    public ReactiveMongoTemplate simpleReactiveMongoTemplate(ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory,
//                                                             MappingMongoConverter reactiveMappingMongoConverter) {
//        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory, reactiveMappingMongoConverter);
//    }
//}
