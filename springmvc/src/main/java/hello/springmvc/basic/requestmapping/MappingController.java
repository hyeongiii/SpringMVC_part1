package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {

        log.info("helloBasic");

        return "ok";
    }

    // HTTP 메서드 매핑
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 경로 변수와 파라미터로 받는 변수명이 같으면, @PathVariable("경로 변수명") 의 경로변수명 생략 가능
     */
    // 경로 변수 매핑
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {  // mappingPath(@PathVariable("userId") String userId
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    @GetMapping("/mappingv2/{userId}")
    public String mappingPathV2(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    // 다중 매핑
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */

    // 특정 파라미터가 있으면 호출되고, 없으면 호출되지 않는다.
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
    * 특정 헤더로 추가 매핑
    * headers="mode",
    * headers="!mode"
    * headers="mode=debug"
    * headers="mode!=debug" (! = )
    */
    // 설정해놓은 헤더 정보가 반드시 포함되어야 한다.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type -> Content-Type 헤더 필요
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    // 미디어 타입 조건 매핑 : HTTP 요청 Content-Type, consume
    // consume : 클라이언트의 요청 시 보내는 데이터 타입
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
/*
        consumes 예시

        consumes = "text/plain"
        consumes = {"text/plain", "application/*"}
        consumes = MediaType.TEXT_PLAIN_VALUE
*/
    }

    /**
     * Accept 헤더 기반 Media Type  -> HTTP 요청 시 Accept 헤더 필요
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    // 클라이언트로 데이터를 보낼 때, 데이터 타입
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
/*
        produces 예시

        produces = "text/palin"
        produces = {"text/plain", "application/*"}
        produces = MediaType.TEXT_PLAIN_VALUE
        produces = "text/plain;charset=UTF-8"


*/
    }



}
