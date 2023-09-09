package tacos.web.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@RepositoryRestController
@CrossOrigin(origins="*")
public class RecentTacosController {

  private TacoRepository tacoRepo;

  public RecentTacosController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  /*  produces : 전송할 데이터의 타입
      consume : 수신할 데이터의 타입
  */
  @GetMapping(path="/tacos/recent", produces="application/hal+json")
  public Mono<ResponseEntity<CollectionModel<TacoResource>>> recentTacos() {
    return tacoRepo.findAll()
            .take(12)
            .collectList()
            .map(tacos -> {
              CollectionModel<TacoResource> tacoResources =
                      new TacoResourceAssembler().toCollectionModel(tacos);
              tacoResources.add(
                      linkTo(methodOn(RecentTacosController.class).recentTacos())
                              .withRel("recents"));
              return new ResponseEntity<>(tacoResources, HttpStatus.OK);
            });
  }
}