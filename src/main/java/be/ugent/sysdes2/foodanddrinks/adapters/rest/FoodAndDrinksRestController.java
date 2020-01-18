package be.ugent.sysdes2.foodanddrinks.adapters.rest;

import be.ugent.sysdes2.foodanddrinks.domain.Badge;
import be.ugent.sysdes2.foodanddrinks.domain.FoodAndDrinksService;
import be.ugent.sysdes2.foodanddrinks.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food-and-drinks")
@CrossOrigin(origins = "*")
public class FoodAndDrinksRestController {

    @Autowired
    private FoodAndDrinksService foodAndDrinksService;

    @Autowired
    public FoodAndDrinksRestController(FoodAndDrinksService foodAndDrinksService){
        this.foodAndDrinksService = foodAndDrinksService;
    }

    @PostMapping()
    public Badge createOrder(@RequestBody Order order) throws JSONException, JsonProcessingException {
        return this.foodAndDrinksService.createOrder(order);
    }
}
