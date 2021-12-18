package engine.controller;

import engine.common.FizzBuzz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FizzBuzzController.
 */
@RestController
public class FizzBuzzController {

    /**
     * return strinthe FizzBuzz
     *
     * @param int1  first number
     * @param int2  second number
     * @param limit limit number
     * @param str1  First string to replace numbers multiple of int 1
     * @param str2  Second string to replace numbers multiple of int 2
     * @return
     */
    @GetMapping(value = "/fizzbuzz/{int1}/{int2}/{limit}/{str1}/{str2}")
    public List<String> runFizzbuzz(@PathVariable(value = "int1") int int1, @PathVariable(value = "int2") int int2, @PathVariable(value = "limit") int limit, @PathVariable(value = "str1") String str1, @PathVariable(value = "str2") String str2) {
        return FizzBuzz.fizzBuzz(int1, int2, limit, str1, str2);


    }



}
