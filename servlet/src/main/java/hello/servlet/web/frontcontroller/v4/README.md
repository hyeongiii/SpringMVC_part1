V4 - 단순하고 실용적인 컨트롤러
---
***

### V3의 한계점
- 항상 ModelView 객체를 생성하고 반환해야하는 부분이 번거롭다. (실용성 부족)

<br>

### V4의 구조
> 기본적인 구조는 V3와 같으나, 컨트롤러가 <code>ModelView</code>를 반환하지 않고 
> <code>ViewName</code>만을 반환한다.

<br>

### ControllerV4
- 이번 버전에서는 인터페이스에 ModelView를 반환하지 않고, 뷰의 이름을 직접 반환해준다.
- model 객체는 파라미터로 전달되며, 이때 전달되는 Model 객체 내부는 텅 비어있다.