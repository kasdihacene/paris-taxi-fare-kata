package org.taxi.paris.api.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;
import org.taxi.paris.api.utils.UtilTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaxiController.class, TaxiUseCase.class})
@WebMvcTest(controllers = TaxiController.class)
public class TaxiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaxiUseCase taxiUseCase;

    @Test
    public void shouldCalculatePriceForRideWhenAsked() throws Exception {
        // ARRANGE
        Double expectedPrice = 6.0;
        Ride ride = new Ride(1, 2, "2020-06-19T13:01:17.031Z", 9000);

        Mockito.when(taxiUseCase.priceOf(ArgumentMatchers.any(Ride.class))).thenReturn(expectedPrice);

        // ACT
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/ride/calculate")
                .content(UtilTest.asJsonString(ride))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        // ASSERT
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expectedPrice));

    }
}
