package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//for using get() import below
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;    //second option?
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.Cookie;

//these are the exact ones needed for the JSON testing
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PagesController.class)  //note the name is the class, not the test class
public class PagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIndexEndpoint() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        this.mvc.perform(request)
                .andExpect(status().isOk())     //returns status of 200 if good
                .andExpect(content().string("Hello World"));   //make sure to do correct imports
    //this.mvc.perform(get("/"))
    }
    @Test
    public void testMathPi() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
        //this.mvc.perform(get("/math/pi") ....need to import the get request (proper one ... import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }
    //curl -i localhost:8080/math/pi

    @Test
    public void testPostSum() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }
    @Test
    public void testNoOperation() throws Exception {
        this.mvc.perform(get("/math/calculate?x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void testGetVolume() throws Exception {
        this.mvc.perform(get("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }
    @Test
    public void testPostVolume() throws Exception {
        this.mvc.perform(post("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }
    @Test
    public void testPatchVolume() throws Exception {
        this.mvc.perform(patch("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }



    @Test
    public void testAdd() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }
    @Test
    public void testMultiply() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }
    @Test
    public void testSubtract() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }
    @Test
    public void testDivide() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testCircleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4.0 is 50.26548245743669"));
    }
    @Test
    public void testRectangleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }
    @Test
    public void testInvalidContentArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("radius", "4");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
    @Test
    public void testCookies() throws Exception {
        this.mvc.perform(get("/cookie").cookie(new Cookie("foo", "bar")))
                .andExpect(status().isOk())
                .andExpect(content().string("bar"));
    }
    @Test
    public void testHeaders() throws Exception {
        this.mvc.perform(get("/header").header("Host", "example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("example.com"));
    }
    @Test
    public void testSingleFlightJSON() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")));
    }

}
