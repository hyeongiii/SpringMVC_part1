package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static (실제로는 HashMap을 사용하면 안된다.) ##1
    private static long sequence = 0L; // static -> 객체를 생성할 때마다 레포지토리가 생성되지 않고 공유 변수로 사용된다.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {

        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    public void clearStore() {
        store.clear();
    }
}

