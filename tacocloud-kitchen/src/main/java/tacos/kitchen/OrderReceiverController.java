package tacos.kitchen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tacos.Order;

@Profile({"jms-template", "rabbitmq-template", "kafka-listener"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

  private final KitchenUI kitchenUI;
  //private final OrderReceiver orderReceiver;

  // jms, rabbit은 listener로 queue를 받으면 사라진다.
  // 따라서 profile을 통해서 listener 빈을 사용하지 않고, template 빈만 사용해서
  // controller에서 url 요청이 오면 orderReceiver을 통해서 받도록 한다
//  @GetMapping("/receive")
//  public String receiveOrder(Model model) {
//    Order order = orderReceiver.receiveOrder();
//    if (order != null) {
//      model.addAttribute("order", order);
//      return "receiveOrder";
//    }
//    return "noOrder";
//  }
  @GetMapping("/receive")
  public String receiveOrder(Model model) {
    Order order = kitchenUI.getLastReceivedOrder();

    if (order != null) {
      model.addAttribute("order", order);
      return "receiveOrder";
    }
    return "noOrder";
  }
}
