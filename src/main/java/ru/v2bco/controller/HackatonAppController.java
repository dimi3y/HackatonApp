package ru.v2bco.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.v2bco.entity.EntityExample;
import ru.v2bco.service.EntityService;

/**
 * The <code>HackatonAppController</code> is a controller class for
 * {@link EntityExample entity
 *
 *
 * @author Dmitry Bogdanov
 *
 */
@RestController
@RequestMapping("/hackaton_app")
public class HackatonAppController {

    private final EntityService EntityExampleService;

    private static final Logger logger = LoggerFactory.getLogger(HackatonAppController.class);

    @Autowired
    public HackatonAppController(EntityService EntityExampleService) {
        this.EntityExampleService = EntityExampleService;
    }

    /**
     * Метод get контроллера {@link HackatonAppController} entity
//     * @param id идентификатор считываемого объекта.
     * @return EntityExample
     */
    @GetMapping(value = "/")
    @ResponseBody
    public String read(@PathVariable("id") long id) {
        logger.info("calling controller GET method");
//        EntityExample ot = null;
//        ot = EntityExampleService.readEntity(id);
        logger.debug("EntityExampleController controller read entity", "");
        return "Hello";
    }

    /**
     * Метод put контроллера {@link HackatonAppController} entity
//     * @param entityExample создаваемый объект.
     * @return EntityExample
     */
    @PutMapping(value = "/fb")
    @ResponseBody
    public String create(@RequestBody String param) {//@RequestBody EntityExample entityExample) {
        logger.info("calling controller PUT method");
        String res = EntityExampleService.publishEntity(param);
        logger.debug("EntityExampleController controller created entity", res);
        return res;
    }

}
