package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    상품 목록
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

//    상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

//    상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    상품 등록 V1
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    상품 등록 V2
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);

//        model.addAttribute("item", item);  -> ModelAttribute 에 이름을 설정해줌으로써 모델이 객체가 자동 추가 되기 때문에 해당 코드를 삭제해도 된다.
//        @ModelAttribute 의 역할
//        1. 요청 파라미터 처리
//        2. Model 추가 : 지정한 객체를 자동으로 넣어준다.

        return "basic/item";
    }

//    상품 등록 V3
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

//        model.addAttribute("item", item); -> ModelAttribute 에 이름이 설정되어 있지 않으면, 지정한 타입의 클래스 이름에서 첫글자만 소문자로 바꿔 자동 등록한다.

        return "basic/item";
    }

//    상품 등록 V4
//    @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);
        return "basic/item";
    }

//    상품 등록 V5
//    @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);
        return "redirect:/basic/items/" +item.getId();
    }

//    상품 등록 V6
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);

//        리다이렉트와 관련된 속성 넣기
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
//        {itemId} 에 해당하는 값이 redirectAttribute 속성에 설정한 값으로 바뀐다.
//        남는 Attribute 는 쿼리파라미터 형식으로 들어간다.
    }

//    상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/basic/editForm";
    }

//    상품 수정
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {

        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
