package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;


// 뷰의 이름과 뷰를 렌더링할 때 필요한 Model 객체를 가진다.
// Model 은 단순히 map 으로 되어있기 때문에, 뷰에 필요한 데이터는 컨트롤러에서 key, value 로 넣어주면 된다.
public class ModelView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
